# import grpc
# from concurrent import futures
# import threading
# from collections import defaultdict
# import cv2
# import numpy as np
#
# from google.protobuf import empty_pb2 as google_dot_protobuf_dot_empty__pb2
# from proto import bytetrack_tracker_pb2_grpc, bytetrack_tracker_pb2
#
# # ==========================
# # ByteTrack PyTorch
# # ==========================
# from bytetrack.byte_tracker import BYTETracker
#
# # =====================================
# # SESSION / TRACKER STORAGE
# # =====================================
# class TrackerSession:
#     def __init__(self):
#         self.active = False
#         self.lock = threading.Lock()
#         self.tracker = None  # BYTETracker
#         self.config = None
#
# # =====================================
# # REAL BYTE TRACKER WRAPPER
# # =====================================
# class ByteTrackWrapper:
#     def __init__(self, config: bytetrack_tracker_pb2.TrackerConfig):
#         self.config = config
#         self.tracker = BYTETracker(
#             track_thresh=config.track_thresh,
#             high_thresh=config.high_thresh,
#             match_thresh=config.match_thresh,
#             track_buffer=config.track_buffer,
#             frame_rate=30
#         )
#
#     def update(self, frame_bytes, det_result):
#         """
#         Принимает JPEG frame и DetectionResult (список детекций)
#         Возвращает список TrackedObject
#         """
#         np_arr = np.frombuffer(frame_bytes, np.uint8)
#         frame = cv2.imdecode(np_arr, cv2.IMREAD_COLOR)
#         if frame is None:
#             return []
#
#         # Подготовка детекций для BYTETracker
#         # ожидается массив [x1, y1, x2, y2, score, class_id]
#         dets = []
#         for det in det_result.detections:
#             dets.append([det.x, det.y, det.x + det.width, det.y + det.height,
#                          det.confidence, int(det.class_id)])
#
#         dets = np.array(dets, dtype=np.float32)
#         # треки: список [track_id, x1, y1, x2, y2, score, class_id]
#         tracks = self.tracker.update(dets, frame.shape[:2])
#
#         # конвертируем в proto
#         proto_tracks = []
#         for t in tracks:
#             track_id, x1, y1, x2, y2, score, cls_id = t
#             proto_tracks.append(
#                 bytetrack_tracker_pb2.TrackedObject(
#                     id=str(int(track_id)),
#                     class_name=str(int(cls_id)),
#                     confidence=float(score),
#                     x=float(x1),
#                     y=float(y1),
#                     width=float(x2 - x1),
#                     height=float(y2 - y1)
#                 )
#             )
#         return proto_tracks
#
#     def close(self):
#         # BYTETracker не требует явного закрытия
#         pass
#
#     def get_buffer_capacity(self):
#         return self.config.track_buffer
#
# # =====================================
# # BYTE TRACK SERVICE
# # =====================================
# class ByteTrackService(bytetrack_tracker_pb2_grpc.ByteTrackServiceServicer):
#
#     def __init__(self):
#         self.sessions = defaultdict(TrackerSession)
#
#     def Init(self, request, context):
#         session = TrackerSession()
#         session.tracker = ByteTrackWrapper(request.config)
#         session.config = request.config
#         session.active = True
#         self.sessions["default"] = session
#         return bytetrack_tracker_pb2.InitResponse(success=True)
#
#     def Update(self, request, context):
#         session = self.sessions["default"]
#         with session.lock:
#             if not session.active or session.tracker is None:
#                 return bytetrack_tracker_pb2.UpdateResponse(tracks=[])
#             tracks = session.tracker.update(request.frame, request.det_result)
#             return bytetrack_tracker_pb2.UpdateResponse(tracks=tracks)
#
#     def Close(self, request, context):
#         session = self.sessions["default"]
#         with session.lock:
#             if session.tracker:
#                 session.tracker.close()
#             session.active = False
#         return google_dot_protobuf_dot_empty__pb2.Empty()
#
#     def GetBufferCapacity(self, request, context):
#         session = self.sessions["default"]
#         capacity = session.tracker.get_buffer_capacity() if session.tracker else 0
#         return bytetrack_tracker_pb2.BufferCapacityResponse(capacity=capacity)
