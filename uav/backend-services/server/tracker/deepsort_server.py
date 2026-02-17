# deepsort_server.py
import grpc
from concurrent import futures
import threading
from collections import defaultdict
import numpy as np
import cv2
import traceback
import logging

from google.protobuf import empty_pb2 as google_dot_protobuf_dot_empty__pb2

from proto import deepsort_tracker_pb2_grpc, deepsort_tracker_pb2

from deep_sort_realtime.deepsort_tracker import DeepSort

LOG = logging.getLogger("deepsort")
logging.basicConfig(level=logging.INFO)


# ============================================================
# SESSION STORAGE
# ============================================================
class TrackerSession:
    def __init__(self):
        self.lock = threading.Lock()
        self.active = False
        self.tracker = None
        self.config = None





class DeepSortWrapper:

    def __init__(self, config: deepsort_tracker_pb2.TrackerConfig):
        self.config = config

        # Преобразование optional значений
        nn_budget = None if config.nn_budget == 0 else int(config.nn_budget)
        override_class = None if config.override_track_class == "" else config.override_track_class
        embedder_model_name = None if config.embedder_model_name == "" else config.embedder_model_name
        embedder_wts = None if config.embedder_wts == "" else config.embedder_wts
        today = None if config.today == "" else config.today

        self.tracker = DeepSort(
            max_iou_distance=float(config.max_iou_distance),
            max_age=int(config.max_age),
            n_init=int(config.n_init),
            nms_max_overlap=float(config.nms_max_overlap),
            max_cosine_distance=float(config.max_cosine_distance),
            nn_budget=nn_budget,
            gating_only_position=bool(config.gating_only_position),
            override_track_class=override_class,
            embedder=config.embedder,
            half=bool(config.half),
            bgr=bool(config.bgr),
            embedder_gpu=bool(config.embedder_gpu),
            embedder_model_name=embedder_model_name,
            embedder_wts=embedder_wts,
            polygon=bool(config.polygon),
            today=today,
        )

    def update(self, frame_bytes, detections_proto):

        np_arr = np.frombuffer(frame_bytes, np.uint8)
        frame = cv2.imdecode(np_arr, cv2.IMREAD_COLOR)

        if frame is None:
            return []

        detections = []

        for det in detections_proto:
            bbox = [
                float(det.x),
                float(det.y),
                float(det.width),
                float(det.height),
            ]
            score = float(det.confidence)
            class_name = det.class_name

            detections.append((bbox, score, class_name))

        tracks = self.tracker.update_tracks(detections, frame=frame)

        proto_tracks = []

        for tr in tracks:
            if not tr.is_confirmed():
                continue

            l, t, r, b = tr.to_ltrb()

            proto_tracks.append(
                deepsort_tracker_pb2.TrackedObject(
                    id=str(tr.track_id),
                    class_name=str(getattr(tr, "det_class", "") or ""),
                    class_id=-1,
                    confidence=float(getattr(tr, "score", 1.0) or 1.0),
                    x=float(l),
                    y=float(t),
                    width=float(r - l),
                    height=float(b - t),
                )
            )

        return proto_tracks

    def clear(self):
        self.__init__(self.config)

    def get_buffer_capacity(self):
        return self.config.max_age



# ============================================================
# gRPC SERVICE IMPLEMENTATION (имя сервиса — без конфликта)
# ============================================================
class DeepSortServicerImpl(deepsort_tracker_pb2_grpc.DeepSortServiceServicer):

    def __init__(self):
        self.sessions = defaultdict(TrackerSession)

    # INIT: ожидается request.config
    def Init(self, request, context):
        try:
            cfg = request  # <- проверь имя поля в proto
            session = TrackerSession()
            session.tracker = DeepSortWrapper(cfg)
            session.config = cfg
            session.active = True
            self.sessions["default"] = session
            LOG.info("DeepSort Init OK")
            return deepsort_tracker_pb2.InitResponse(success=True)
        except Exception:
            LOG.error("Init failed:\n" + traceback.format_exc())
            return deepsort_tracker_pb2.InitResponse(success=False)

    # UPDATE
    def Update(self, request, context):
        try:
            session = self.sessions.get("default")
            if session is None:
                return deepsort_tracker_pb2.UpdateResponse(tracks=[])

            with session.lock:
                if not session.active or session.tracker is None:
                    return deepsort_tracker_pb2.UpdateResponse(tracks=[])

                tracks = session.tracker.update(request.image, request.detections)
                return deepsort_tracker_pb2.UpdateResponse(tracks=tracks)
        except Exception:
            LOG.error("Update failed:\n" + traceback.format_exc())
            return deepsort_tracker_pb2.UpdateResponse(tracks=[])

    # CLOSE
    def Close(self, request, context):
        try:
            session = self.sessions.get("default")
            if session:
                with session.lock:
                    if session.tracker:
                        session.tracker.close()
                    session.active = False
            return google_dot_protobuf_dot_empty__pb2.Empty()
        except Exception:
            LOG.error("Close failed:\n" + traceback.format_exc())
            return google_dot_protobuf_dot_empty__pb2.Empty()

    # GetBufferCapacity
    def GetBufferCapacity(self, request, context):
        try:
            session = self.sessions.get("default")
            capacity = 0
            if session and session.tracker:
                capacity = session.tracker.get_buffer_capacity()
            return deepsort_tracker_pb2.BufferCapacityResponse(capacity=capacity)
        except Exception:
            LOG.error("GetBufferCapacity failed:\n" + traceback.format_exc())
            return deepsort_tracker_pb2.BufferCapacityResponse(capacity=0)

    def Clear(self, request, context):
        session = self.sessions.get("default")
        if session and session.tracker:
            session.tracker.reset()  # пересоздаём DeepSort
        return google_dot_protobuf_dot_empty__pb2.Empty()

    def reset(self):
        session = self.sessions.get("default")
        session.tracker.reset()  # пересоздаём DeepSort
        # self._init_tracker()