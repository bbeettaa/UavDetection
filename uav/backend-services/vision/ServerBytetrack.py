import grpc
from concurrent import futures
import time
import numpy as np # <-- Импорт numpy здесь
import cv2
from yolox.tracker.byte_tracker import BYTETracker
from collections import namedtuple

# =================================================================
# === НАЧАЛО: ПАТЧ ДЛЯ ОШИБКИ 'module 'numpy' has no attribute 'float'' ===
# Восстанавливаем устаревшие алиасы, которые были удалены в последних версиях numpy.
try:
    # Восстанавливаем np.float -> float64
    np.float = np.float64
except AttributeError:
    pass

try:
    # Восстанавливаем np.int -> np.int_ (или int)
    np.int = np.int_
except AttributeError:
    pass

# Импортируем сгенерированные файлы
import vision.protogen.bytetrack_objecttracker_pb2 as tracker_pb2
import vision.protogen.bytetrack_objecttracker_pb2_grpc as tracker_pb2_grpc

MockArgs = namedtuple('MockArgs', ['track_thresh', 'high_thresh', 'match_thresh', 'track_buffer', 'frame_rate', 'mot20'])

class ByteTrackerServicer(tracker_pb2_grpc.TrackerServiceServicer):

    def __init__(self):
        self.tracker = None
        self.frame_id = 0
        args = MockArgs(
            track_thresh=0.4,
            high_thresh=0.6,
            match_thresh=0.5,
            track_buffer=90,
            frame_rate=15,
            mot20=False
        )
        self.tracker = BYTETracker(args=args, frame_rate=args.frame_rate)
        self.frame_id = 0


    def InitTracker(self, request, context):
        args = MockArgs(
            track_thresh=request.track_thresh,
            high_thresh=request.high_thresh,
            match_thresh=request.match_thresh,
            track_buffer=request.track_buffer,
            frame_rate=30,
            mot20=False
        )
        self.tracker = BYTETracker(args=args, frame_rate=args.frame_rate)
        self.frame_id = 0
        print(f"ByteTracker инициализирован с параметрами: {args}")
        return tracker_pb2.InitResponse(ok=True)

    def UpdateTracks(self, request, context):
        if self.tracker is None:
            context.abort(grpc.StatusCode.FAILED_PRECONDITION,
                          "Трекер не инициализирован. Сначала вызовите InitTracker.")

        self.frame_id += 1

        # Детекции -> numpy
        if not request.dets:
            dets = np.empty((0, 5), dtype=np.float32)
        else:
            dets = np.array([
                [d.x1, d.y1, d.x2, d.y2, d.score]
                for d in request.dets
            ], dtype=np.float32)

        h = request.img_height
        w = request.img_width

        img_info = (h, w, 1.0)
        img_size = (h, w)

        tracked_objects = self.tracker.update(dets, img_info, img_size)

        resp = tracker_pb2.TracksResponse()
        for t in tracked_objects:
            x1, y1, x2, y2 = t.tlbr.astype(int)
            resp.tracks.append(tracker_pb2.Track(
                id=t.track_id,
                x1=x1, y1=y1, x2=x2, y2=y2
            ))

        for lost in self.tracker.lost_stracks:
            x1, y1, x2, y2 = lost.tlbr.astype(int)
            resp.tracks.append(tracker_pb2.Track(
                id=lost.track_id,
                x1=x1, y1=y1, x2=x2, y2=y2
            ))

        print(f"Frame {self.frame_id}: tracks={len(resp.tracks)}")
        return resp


def serve(port = 50061):
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=2))
    tracker_pb2_grpc.add_TrackerServiceServicer_to_server(ByteTrackerServicer(), server)
    server.add_insecure_port(f"[::]:{port}")
    server.start()
    print(f"ByteTrack gRPC server started on port {port}")
    try:
        server.wait_for_termination()
    except KeyboardInterrupt:
        server.stop(0)

if __name__ == "__main__":
    serve()
