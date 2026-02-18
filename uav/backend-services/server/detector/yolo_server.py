import threading
import time
from collections import defaultdict

import cv2
import numpy as np
from ultralytics import YOLO

from google.protobuf import empty_pb2 as google_dot_protobuf_dot_empty__pb2

from proto import yolo_detector_pb2_grpc, yolo_detector_pb2


# ==============================
# SESSION STORAGE
# ==============================
class SessionContext:
    def __init__(self):
        self.active = False
        self.config = None
        self.lock = threading.Lock()


# ==============================
# YOLO SERVICE
# ==============================
class YoloService(yolo_detector_pb2_grpc.YoloDetectionServiceServicer):

    def __init__(self, model_path="server/detector/yolo/yolov8s.pt", device=None):
        if device is None:
            device = "cpu"
        self.device = device

        self.sessions = defaultdict(SessionContext)
        self.global_config = yolo_detector_pb2.YoloConfig()
        # --- загружаем YOLO один раз ---
        self.model = YOLO(model_path)
        self.model.to(self.device)
        print(f"YOLO model loaded on {self.device} from {model_path}")

    # --------------------------
    # CONFIG
    # --------------------------
    def SetConfig(self, request, context):
        self.global_config = request.config
        if(self.global_config.model_path != ""):
            self.model = YOLO(self.global_config.model_path)
        session = self.sessions[request.session_id]
        with session.lock:
            session.active = True
            session.config = self.global_config
        print("Config updated:", self.global_config)
        return google_dot_protobuf_dot_empty__pb2.Empty()

    def GetConfig(self, request, context):
        return yolo_detector_pb2.GetConfigResponse(config=self.global_config)

    # --------------------------
    # STREAM CONTROL
    # --------------------------
    def StartStreaming(self, request, context):
        session = self.sessions[request.session_id]
        with session.lock:
            session.active = True
            session.config = self.global_config
        print(f"Streaming started: {request.session_id}")
        return yolo_detector_pb2.StreamStatus(success=True, message="Started")

    def StopStreaming(self, request, context):
        session = self.sessions[request.session_id]
        with session.lock:
            session.active = False
        print(f"Streaming stopped: {request.session_id}")
        return yolo_detector_pb2.StreamStatus(success=True, message="Stopped")

    def RestartConnection(self, request, context):
        session = self.sessions[request.session_id]
        with session.lock:
            session.active = False
            time.sleep(0.1)
            session.active = True
        print(f"Streaming restarted: {request.session_id}")
        return yolo_detector_pb2.StreamStatus(success=True, message="Restarted")

    def DetectSingle(self, request, context):
        detections = self.run_inference(request.image, self.global_config)
        return yolo_detector_pb2.TrackingResult(
            timestamp=request.timestamp,
            detections=detections
        )

    # --------------------------
    # STREAM TRACK
    # --------------------------
    def StreamTrack(self, request_iterator, context):
        for frame in request_iterator:
            session = self.sessions[frame.session_id]
            if not session.active:
                continue

            # --- YOLO INFERENCE ---
            detections = self.run_inference(frame.image, session.config)

            yield yolo_detector_pb2.TrackingResult(
                timestamp=frame.timestamp,
                detections=detections
            )

    # --------------------------
    # REAL YOLO INFERENCE
    # --------------------------
    def run_inference(self, image_bytes, config):
        # 1. decode JPEG
        np_arr = np.frombuffer(image_bytes, np.uint8)
        frame = cv2.imdecode(np_arr, cv2.IMREAD_COLOR)
        if frame is None:
            return []

        # 2. resize если нужно
        if config.input_width > 0 and config.input_height > 0:
            frame = cv2.resize(frame, (config.input_width, config.input_height))

        # 3. run YOLO
        results = self.model(
            frame,
            imgsz=config.input_width if config.input_width > 0 else 640,
            conf=config.confidence_threshold if config.confidence_threshold > 0 else 0.25,
            iou=config.iou_threshold if config.iou_threshold > 0 else 0.45,
            max_det=config.max_detections if config.max_detections > 0 else 100,
            verbose=False
        )

        detections = []

        # 4. parse results
        for r in results:
            for box in r.boxes:
                x1, y1, x2, y2 = box.xyxy[0].tolist()
                conf = float(box.conf[0])
                cls_id = int(box.cls[0])
                class_name = self.model.names[cls_id]

                detections.append(
                    yolo_detector_pb2.Detection(
                        class_name=class_name,
                        confidence=conf,
                        x=float(x1),
                        y=float(y1),
                        width=float(x2 - x1),
                        height=float(y2 - y1),
                    )
                )

        return detections


