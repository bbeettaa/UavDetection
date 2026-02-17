import os

import threading
import time
from collections import defaultdict

import cv2
import numpy as np
import onnxruntime as ort

from google.protobuf import empty_pb2 as google_dot_protobuf_dot_empty__pb2

from proto import ssd_detector_pb2_grpc, ssd_detector_pb2

print(ort.get_available_providers())


# ============================================================
# SESSION CONTEXT
# ============================================================

class SessionContext:
    def __init__(self):
        self.active = False
        self.lock = threading.Lock()


# ============================================================
# SSD SERVICE
# ============================================================

class SsdDetectionService(
    ssd_detector_pb2_grpc.SsdDetectionServiceServicer
):

    def __init__(self):
        self.sessions = defaultdict(SessionContext)
        self.config = ssd_detector_pb2.SsdConfig()
        self.session = None
        self.input_name = None

    # --------------------------------------------------------
    # MODEL LOAD
    # --------------------------------------------------------

    def load_model(self, config: ssd_detector_pb2.SsdConfig):
        if not config.model_path:
            raise ValueError("SsdConfig.model_path is empty!")

        if not os.path.exists(config.model_path):
            raise FileNotFoundError(f"ONNX model not found: {config.model_path}")

        providers = ["CPUExecutionProvider"]
        if getattr(config, "use_gpu", False):
            providers = ["CUDAExecutionProvider", "CPUExecutionProvider"]

        # Создаём сессию ONNX
        self.session = ort.InferenceSession(config.model_path, providers=providers)

        # Получаем имя входного тензора
        self.input_name = self.session.get_inputs()[0].name
        print(f"SSD model loaded: {config.model_path}")

    # --------------------------------------------------------
    # CONFIG
    # --------------------------------------------------------

    def SetConfig(self, request, context):
        self.config = request
        self.load_model(request)
        return google_dot_protobuf_dot_empty__pb2.Empty()

    def GetConfig(self, request, context):
        return ssd_detector_pb2.GetConfigResponse(
            config=self.config
        )

    # --------------------------------------------------------
    # SESSION CONTROL
    # --------------------------------------------------------

    def StartStreaming(self, request, context):
        session = self.sessions[request.session_id]
        with session.lock:
            session.active = True

        return ssd_detector_pb2.StreamStatus(
            success=True,
            message="Streaming started"
        )

    def StopStreaming(self, request, context):
        session = self.sessions[request.session_id]
        with session.lock:
            session.active = False

        return ssd_detector_pb2.StreamStatus(
            success=True,
            message="Streaming stopped"
        )

    def RestartConnection(self, request, context):
        session = self.sessions[request.session_id]
        with session.lock:
            session.active = False
            time.sleep(0.1)
            session.active = True

        return ssd_detector_pb2.StreamStatus(
            success=True,
            message="Streaming restarted"
        )

    # --------------------------------------------------------
    # STREAM DETECT
    # --------------------------------------------------------

    def StreamDetect(self, request_iterator, context):

        for frame in request_iterator:

            session = self.sessions[frame.session_id]
            if not session.active:
                continue

            detections = self.run_inference(frame.image)

            yield ssd_detector_pb2.TrackingResult(
                timestamp=frame.timestamp,
                detections=detections
            )

    # --------------------------------------------------------
    # INFERENCE
    # --------------------------------------------------------

    def run_inference(self, image_bytes):
        if self.session is None:
            print("Model not loaded!")
            return []

        # 1. Decode image
        np_arr = np.frombuffer(image_bytes, np.uint8)
        frame = cv2.imdecode(np_arr, cv2.IMREAD_COLOR)

        if frame is None:
            return []

        # 2. Resize
        # frame_resized = cv2.resize(
        #     frame,
        #     (self.config.input_width, self.config.input_height)
        # )

        # 3. Normalize (SSD usually expects 0-1)
        input_tensor = frame.astype(np.float32) / 255.0
        input_tensor = np.transpose(input_tensor, (2, 0, 1))  # HWC → CHW
        input_tensor = np.expand_dims(input_tensor, axis=0)

        # 4. Inference
        outputs = self.session.run(None, {self.input_name: input_tensor})

        # ВАЖНО:
        # Предполагается SSD ONNX output:
        # boxes, scores, class_ids
        # Нужно адаптировать под конкретную модель

        boxes = outputs[0][0]
        scores = outputs[1][0]
        class_ids = outputs[2][0]

        detections = []

        for box, score, cls_id in zip(boxes, scores, class_ids):

            if score < self.config.confidence_threshold:
                continue

            x1, y1, x2, y2 = box

            detections.append(
                ssd_detector_pb2.Detection(
                    class_name=str(int(cls_id)),
                    class_id=int(cls_id),
                    confidence=float(score),
                    x=float(x1),
                    y=float(y1),
                    width=float(x2 - x1),
                    height=float(y2 - y1),
                )
            )

        return detections



