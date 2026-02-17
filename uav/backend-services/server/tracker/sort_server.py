import threading
from collections import defaultdict
import numpy as np
import cv2

import supervision as sv
from google.protobuf import empty_pb2 as google_dot_protobuf_dot_empty__pb2
from proto import sort_tracker_pb2_grpc, sort_tracker_pb2
from trackers import SORTTracker


# ============================================================
# SESSION STORAGE
# ============================================================

class TrackerSession:
    def __init__(self):
        self.lock = threading.Lock()
        self.active = False
        self.tracker = None
        self.config = None


# ============================================================
# WRAPPER
# ============================================================

class SortWrapper:

    def __init__(self, config: sort_tracker_pb2.TrackerConfig):
        self.config = config

        self.tracker = SORTTracker(
            lost_track_buffer=int(config.lost_track_buffer),
            track_activation_threshold=float(config.track_activation_threshold),
            minimum_consecutive_frames=int(config.minimum_consecutive_frames),
            minimum_iou_threshold=float(config.minimum_iou_threshold),
        )
        self.tracks = []
        self.tracks_dict = {}

    def update(self, frame_bytes, detections_proto):

        # --------------------------
        # Преобразуем кадр
        # --------------------------
        np_arr = np.frombuffer(frame_bytes, np.uint8)
        frame = cv2.imdecode(np_arr, cv2.IMREAD_COLOR)
        if frame is None:
            return []

        # --------------------------
        # Преобразуем protobuf детекции в sv.Detections
        # --------------------------
        xyxy = []
        confidences = []
        class_ids = []

        for det in detections_proto:
            x1 = det.x
            y1 = det.y
            x2 = det.x + det.width
            y2 = det.y + det.height
            xyxy.append([x1, y1, x2, y2])
            confidences.append(det.confidence)
            class_ids.append(int(det.class_id) if hasattr(det, "class_id") else 0)

        if len(xyxy) == 0:
            detections_sv = sv.Detections(
                xyxy=np.zeros((0, 4)),
                confidence=np.array([]),
                class_id=np.array([])
            )
        else:
            detections_sv = sv.Detections(
                xyxy=np.array(xyxy),
                confidence=np.array(confidences),
                class_id=np.array(class_ids)
            )

        # --------------------------
        # Обновляем трекер
        # --------------------------
        new_tracks = self.tracker.update(detections_sv)
        self.tracks = new_tracks  # теперь они хранятся между кадрами

        for track in new_tracks:
            track_id = int(track[4])
            if track_id not in self.tracks_dict:
                self.tracks_dict[track_id] = track  # добавляем новый трек
            else:
                self.tracks_dict[track_id] = track  # обновляем существующий
        self.tracks = list(self.tracks_dict.values())

        # --------------------------
        # Конвертируем в protobuf
        # --------------------------
        proto_tracks = []
        for track in self.tracks:
            bbox = track[0]
            score = float(track[2])
            class_name = str(track[3])
            track_id = int(track[4])
            x1, y1, x2, y2 = bbox

            proto_tracks.append(
                sort_tracker_pb2.TrackedObject(
                    id=str(track_id),
                    class_name=class_name,
                    class_id=0,
                    confidence=score,
                    x=float(x1),
                    y=float(y1),
                    width=float(x2 - x1),
                    height=float(y2 - y1),
                )
            )

        return proto_tracks

    def clear(self):
        self.__init__(self.config)

    def get_buffer_capacity(self):
        return self.config.lost_track_buffer




# ============================================================
# gRPC SERVICE
# ============================================================

class SortService(sort_tracker_pb2_grpc.SortServiceServicer):

    def __init__(self):
        self.sessions = defaultdict(TrackerSession)

    def Init(self, request, context):
        session = TrackerSession()
        session.tracker = SortWrapper(request)
        session.config = request
        session.active = True
        self.sessions["default"] = session
        return sort_tracker_pb2.InitResponse(success=True)

    def Update(self, request, context):
        session = self.sessions["default"]
        with session.lock:
            if not session.active or session.tracker is None:
                return sort_tracker_pb2.UpdateResponse(tracks=[])
            tracks = session.tracker.update(request.image, request.detections)
            return sort_tracker_pb2.UpdateResponse(tracks=tracks)

    def Close(self, request, context):
        session = self.sessions["default"]
        with session.lock:
            if session.tracker:
                session.tracker.clear()
            session.active = False
        return google_dot_protobuf_dot_empty__pb2.Empty()

    def GetBufferCapacity(self, request, context):
        session = self.sessions["default"]
        capacity = session.tracker.get_buffer_capacity() if session.tracker else 0
        return sort_tracker_pb2.BufferCapacityResponse(capacity=capacity)

    def Clear(self, request, context):
        session = self.sessions["default"]
        if session.tracker:
            session.tracker.clear()
        return google_dot_protobuf_dot_empty__pb2.Empty()

    def reset(self):
        session = self.sessions.get("default")
        session.tracker.reset()  # пересоздаём DeepSort
        # self._init_tracker()