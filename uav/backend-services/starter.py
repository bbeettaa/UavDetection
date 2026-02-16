import time
from concurrent import futures
import grpc  # <-- обязательно!

from generated import yolo_detector_pb2_grpc
from server.detector.server import YoloService


# ==============================
# SERVER BOOT
# ==============================
def serve():
    server = grpc.server(
        futures.ThreadPoolExecutor(max_workers=8),
        options=[
            ("grpc.max_receive_message_length", 50 * 1024 * 1024),
            ("grpc.max_send_message_length", 50 * 1024 * 1024),
        ]
    )

    yolo_detector_pb2_grpc.add_YoloDetectionServiceServicer_to_server(
        YoloService(), server
    )

    server.add_insecure_port("[::]:50051")
    server.start()

    print("YOLO gRPC server running on port 50051")

    try:
        while True:
            time.sleep(86400)
    except KeyboardInterrupt:
        server.stop(0)



if __name__ == "__main__":
    serve()
