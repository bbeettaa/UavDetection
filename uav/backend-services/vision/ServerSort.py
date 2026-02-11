import grpc
from concurrent import futures
import time
import numpy as np
import cv2
from collections import namedtuple

from sort import Sort

np.float = np.float64
np.int = np.int_

import vision.protogen.bytetrack_objecttracker_pb2 as tracker_pb2
import vision.protogen.bytetrack_objecttracker_pb2_grpc as tracker_pb2_grpc

MockArgs = namedtuple('MockArgs', ['max_age', 'min_hits', 'iou_threshold'])


class SortTrackerServicer(tracker_pb2_grpc.TrackerServiceServicer):

    def __init__(self):
        self.tracker = None
        self.frame_id = 0

    def InitTracker(self, request, context):
        max_age = request.track_buffer
        iou_threshold = request.match_thresh

        min_hits = 3

        try:
            self.tracker = Sort(max_age=max_age, min_hits=min_hits, iou_threshold=iou_threshold)
            self.frame_id = 0
            print(f"SORT инициализирован с max_age={max_age}, iou_threshold={iou_threshold}")
            return tracker_pb2.InitResponse(ok=True)
        except Exception as e:
            context.abort(grpc.StatusCode.INTERNAL, f"Ошибка при инициализации SORT: {e}")

    def UpdateTracks(self, request, context):
        if self.tracker is None:
            context.abort(grpc.StatusCode.FAILED_PRECONDITION,
                          "Трекер не инициализирован. Сначала вызовите InitTracker.")

        self.frame_id += 1

        detections_list = []
        for det in request.dets:
            detections_list.append([det.x1, det.y1, det.x2, det.y2, det.score])

        if not detections_list:
            detections_np = np.empty((0, 5), dtype=np.float32)
        else:
            detections_np = np.array(detections_list, dtype=np.float32)

        tracked_objects = self.tracker.update(detections_np)
        tracks_response = tracker_pb2.TracksResponse()

        for track_data in tracked_objects:
            x1, y1, x2, y2, track_id = track_data.astype(int).tolist()
            tracks_response.tracks.append(tracker_pb2.Track(
                id=track_id,
                x1=x1,
                y1=y1,
                x2=x2,
                y2=y2
            ))



        print(f"Обработан кадр {self.frame_id}. Обнаружено треков: {len(tracks_response.tracks)}")

        return tracks_response


def serve(port=50062):
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    tracker_pb2_grpc.add_TrackerServiceServicer_to_server(
        SortTrackerServicer(), server)
    server.add_insecure_port(f'[::]:{port}')
    server.start()
    print(f"gRPC сервер SORT запущен на порту {port}...")
    try:
        server.wait_for_termination()
    except KeyboardInterrupt:
        server.stop(0)

if __name__ == '__main__':
    serve()