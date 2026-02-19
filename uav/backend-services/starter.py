import threading
from concurrent import futures
import grpc
import torch

from proto import ssd_detector_pb2_grpc, yolo_detector_pb2_grpc, bytetrack_tracker_pb2_grpc, deepsort_tracker_pb2_grpc, \
    sort_tracker_pb2_grpc, strongsort_tracker_pb2_grpc
from proto.deepsort_tracker_pb2_grpc import DeepSortService
from server.detector.async_yolo_service import YoloService
from server.detector.ssd_server import SsdDetectionService
# from server.detector.yolo_server import YoloService
from server.tracker.deepsort_server import DeepSortServicerImpl
from server.tracker.sort_server import SortService
from server.tracker.strongsort_server import StrongSortService

import multiprocessing as mp
import grpc
from concurrent import futures
# from server.tracker.bytetrack_yolo import ByteTrackService

MAX_MESSAGE_SIZE = 50 * 1024 * 1024






# ============================================================
# YOLO SERVER
# ============================================================


#
# def start_yolo_server_sync():
#     server = grpc.server(
#         futures.ThreadPoolExecutor(max_workers=12),
#         options=[
#             ("grpc.max_receive_message_length", MAX_MESSAGE_SIZE),
#             ("grpc.max_send_message_length", MAX_MESSAGE_SIZE),
#         ],
#     )
#
#     yolo_detector_pb2_grpc.add_YoloDetectionServiceServicer_to_server(
#         YoloProcessPoolService(model_path="server/detector/yolo/yolov8n.pt"),
#         server,
#     )
#
#     server.add_insecure_port("[::]:50051")
#     server.start()
#     print("YOLO gRPC server running on port 50051")
#     server.wait_for_termination()



#
# def start_yolo_server_sync_one():
#     server = grpc.server(
#         futures.ThreadPoolExecutor(max_workers=12),
#         options=[
#             ("grpc.max_receive_message_length", MAX_MESSAGE_SIZE),
#             ("grpc.max_send_message_length", MAX_MESSAGE_SIZE),
#         ],
#     )
#     yolo_detector_pb2_grpc.add_YoloDetectionServiceServicer_to_server(
#         YoloService(model_path="server/detector/yolo/yolov8n.pt"),
#         server,
#     )
#     server.add_insecure_port("[::]:50051")
#     server.start()
#     print("YOLO gRPC server running on port 50051")
#     server.wait_for_termination()
#
def start_yolo_server():
    from concurrent import futures
    import grpc

    print(f"ROCm version: {torch.version.hip}")  # Должно вывести версию (например, 6.0/6.2)
    print(f"Is ROCm/CUDA available: {torch.cuda.is_available()}")
    print(f"Device name: {torch.cuda.get_device_name(0)}")
    device = "cuda" if torch.cuda.is_available() else "cpu"
    print(f"Using device: {device}")

    MAX_MESSAGE_SIZE = 200 * 1024 * 1024

    server = grpc.server(
        futures.ThreadPoolExecutor(max_workers=12),
        options=[
            ("grpc.max_receive_message_length", MAX_MESSAGE_SIZE),
            ("grpc.max_send_message_length", MAX_MESSAGE_SIZE),
        ],
    )
    yolo_detector_pb2_grpc.add_YoloDetectionServiceServicer_to_server(
        YoloService(model_path="server/detector/yolo/yolov8s.pt", device=device, num_models=12),
        server,
    )
    server.add_insecure_port("[::]:50051")
    server.start()
    print("YOLO gRPC server running on port 50051")
    server.wait_for_termination()
# ============================================================
# SSD SERVER
# ============================================================

def start_ssd_server():
    server = grpc.server(
        futures.ThreadPoolExecutor(max_workers=8),
        options=[
            ("grpc.max_receive_message_length", MAX_MESSAGE_SIZE),
            ("grpc.max_send_message_length", MAX_MESSAGE_SIZE),
        ],
    )

    ssd_detector_pb2_grpc.add_SsdDetectionServiceServicer_to_server(
        SsdDetectionService(),
        server,
    )

    server.add_insecure_port("[::]:50052")
    server.start()

    print("SSD gRPC server running on port 50052")
    server.wait_for_termination()




# =====================================
# SERVER BOOT
# =====================================
# def start_bytetrack_server():
#     server = grpc.server(
#         futures.ThreadPoolExecutor(max_workers=8),
#         options=[
#             ("grpc.max_receive_message_length", 50 * 1024 * 1024),
#             ("grpc.max_send_message_length", 50 * 1024 * 1024),
#         ]
#     )
#
#     bytetrack_tracker_pb2_grpc.add_ByteTrackServiceServicer_to_server(ByteTrackService(), server)
#     server.add_insecure_port("[::]:50061")
#     server.start()
#     print("ByteTrack gRPC server running on port 50061")
#     server.wait_for_termination()


# ============================================================
# SERVER START
# ============================================================
def start_deepsort_server():
    server = grpc.server(
        futures.ThreadPoolExecutor(max_workers=8),
        options=[
            ("grpc.max_receive_message_length", 50 * 1024 * 1024),
            ("grpc.max_send_message_length", 50 * 1024 * 1024),
        ],
    )

    deepsort_tracker_pb2_grpc.add_DeepSortServiceServicer_to_server(
        DeepSortServicerImpl(),
        server
    )

    server.add_insecure_port("[::]:50062")
    server.start()

    print("DeepSORT gRPC server running on port 50062")
    server.wait_for_termination()


def start_sort_server():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=8))
    sort_tracker_pb2_grpc.add_SortServiceServicer_to_server(SortService(), server)
    server.add_insecure_port("[::]:50063")
    server.start()
    print("Sort gRPC server running on port 50063")
    server.wait_for_termination()

def start_strongsort_server():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=8))
    strongsort_tracker_pb2_grpc.add_StrongSortServiceServicer_to_server(StrongSortService(), server)
    server.add_insecure_port("[::]:50064")
    server.start()
    print("StrongSORT gRPC server running on port 50064")
    server.wait_for_termination()



# ============================================================
# MAIN
# ============================================================

if __name__ == "__main__":
    yolo_thread = threading.Thread(target=start_yolo_server)
    ssd_thread = threading.Thread(target=start_ssd_server)
    # bytetrack_thread = threading.Thread(target=start_bytetrack_server)
    deepsort_thread = threading.Thread(target=start_deepsort_server)
    sort_thread = threading.Thread(target=start_sort_server)
    strongsort_thread = threading.Thread(target=start_strongsort_server)

    yolo_thread.start()
    ssd_thread.start()
    # bytetrack_thread.start()
    deepsort_thread.start()
    sort_thread.start()
    strongsort_thread.start()

    yolo_thread.join()
    ssd_thread.join()
    # bytetrack_thread.join()
    deepsort_thread.join()
    sort_thread.join()
    strongsort_thread.join()
