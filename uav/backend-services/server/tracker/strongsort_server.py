import grpc
from concurrent import futures
import threading
from collections import defaultdict
import numpy as np
import cv2

from google.protobuf import empty_pb2 as google_dot_protobuf_dot_empty__pb2
from proto import strongsort_tracker_pb2_grpc, strongsort_tracker_pb2
from trackers import SORTTracker  # используем ту же библиотеку SORT для StrongSORT


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

class StrongSortWrapper:

    def __init__(self, config: strongsort_tracker_pb2.TrackerConfig):
        self.config = config
        self.tracker = SORTTracker(
            max_age=config.track_buffer,       # аналог track_buffer
            min_hits=1,
            iou_threshold=0.3
        )

    def update(self, frame_bytes, detections_proto):
        np_arr = np.frombuffer(frame_bytes, np.uint8)
        frame = cv2.imdecode(np_arr, cv2.IMREAD_COLOR)
        if frame is None:
            return []

        dets = []
        for det in detections_proto:
            x1, y1 = det.x, det.y
            x2, y2 = det.x + det.width, det.y + det.height
            dets.append([x1, y1, x2, y2, det.confidence])

        tracks = self.tracker.update(np.array(dets))
        proto_tracks = []
        for t in tracks:
            track_id, x1, y1, x2, y2 = t[:5]
            proto_tracks.append(
                strongsort_tracker_pb2.TrackedObject(
                    id=str(int(track_id)),
                    class_name="",  # StrongSORT не хранит класс
                    class_id=0,
                    confidence=float(t[4]),
                    x=float(x1),
                    y=float(y1),
                    width=float(x2 - x1),
                    height=float(y2 - y1)
                )
            )
        return proto_tracks

    def get_buffer_capacity(self):
        return self.config.track_buffer

    def clear(self):
        self.tracker.reset()


# ============================================================
# gRPC SERVICE
# ============================================================

class StrongSortService(strongsort_tracker_pb2_grpc.StrongSortServiceServicer):

    def __init__(self):
        self.sessions = defaultdict(TrackerSession)

    def Init(self, request, context):
        session = TrackerSession()
        session.tracker = StrongSortWrapper(request)
        session.config = request
        session.active = True
        self.sessions["default"] = session
        return strongsort_tracker_pb2.InitResponse(success=True)

    def Update(self, request, context):
        session = self.sessions["default"]
        with session.lock:
            if not session.active or session.tracker is None:
                return strongsort_tracker_pb2.UpdateResponse(tracks=[])
            tracks = session.tracker.update(request.image, request.detections)
            return strongsort_tracker_pb2.UpdateResponse(tracks=tracks)

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
        return strongsort_tracker_pb2.BufferCapacityResponse(capacity=capacity)

    def Clear(self, request, context):
        session = self.sessions["default"]
        if session.tracker:
            session.tracker.clear()
        return google_dot_protobuf_dot_empty__pb2.Empty()



