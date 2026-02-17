import threading
from collections import defaultdict
from google.protobuf import empty_pb2 as google_dot_protobuf_dot_empty__pb2
from proto import sort_tracker_pb2_grpc, sort_tracker_pb2

import numpy as np

from server.tracker.sort.sort.sort import Sort


# ============================================================
# SESSION STORAGE
# ============================================================

class TrackerSession:
    def __init__(self):
        self.lock = threading.Lock()
        self.active = False
        self.tracker = None
        self.config = None



class SortWrapper:

    def __init__(self, config: sort_tracker_pb2.TrackerConfig):
        self.config = config

        self.tracker = Sort(
            max_age=int(config.lost_track_buffer),
            min_hits=int(config.minimum_consecutive_frames),
            iou_threshold=float(config.minimum_iou_threshold),
        )

    def update(self, detections_proto) -> list[sort_tracker_pb2.TrackedObject]:
        detections = []
        for det in detections_proto:
            x1 = float(det.x)
            y1 = float(det.y)
            x2 = x1 + float(det.width)
            y2 = y1 + float(det.height)
            score = float(det.confidence)
            detections.append([x1, y1, x2, y2, score])

        detections_np = np.array(detections) if detections else np.empty((0, 5), dtype=np.float32)

        # Обновляем трекер — получаем только активные треки
        tracked = self.tracker.update(detections_np)  # np.array [N, 5]: x1,y1,x2,y2,id

        proto_tracks = []
        for row in tracked:
            x1, y1, x2, y2, track_id = row
            track_id = int(track_id)

            proto_tracks.append(
                sort_tracker_pb2.TrackedObject(
                    id=str(track_id),
                    class_name="object",
                    class_id=0,
                    confidence=1.0,
                    x=float(x1),
                    y=float(y1),
                    width=float(x2 - x1),
                    height=float(y2 - y1),
                )
            )

        return proto_tracks

    def clear(self):
        self.__init__(self.config)

    def reset(self):
        self.__init__(self.config)

    def get_buffer_capacity(self) -> int:
        return self.config.lost_track_buffer


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
            tracks = session.tracker.update(request.detections)
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