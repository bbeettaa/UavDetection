import grpc
from concurrent import futures
import time
import cv2
import numpy as np
from ultralytics import YOLO

from vision import yolo_pb2, yolo_pb2_grpc

model = YOLO("yolov8n.pt").to('cuda')
# model = YOLO("yolov8s.pt").to('cuda')
# model = YOLO("yolov8m.pt").to('cuda')
PORT = 50051

class YoloServiceServicer(yolo_pb2_grpc.YoloServiceServicer):
    def DetectObjects(self, request, context):
        nparr = np.frombuffer(request.image, np.uint8)
        img = cv2.imdecode(nparr, cv2.IMREAD_COLOR)

        conf_thres = request.conf_threshold if request.conf_threshold else 0.25
        iou_thres = request.iou_threshold if request.iou_threshold else 0.45
        classes = [int(c) for c in request.classes] if request.classes else None
        max_det = request.max_det if request.max_det else 100
        augment = request.augment if request.augment else False
        img_size = request.img_size if request.img_size else 640
        half = request.half if request.half else True

        results = model.predict(
            source=img,
            conf=conf_thres,
            iou=iou_thres,
            classes=classes,
            max_det=max_det,
            augment=augment,
            imgsz=img_size,
            half=half
        )

        response = yolo_pb2.ImageResponse()
        for r in results:
            for box in r.boxes:
                bb = yolo_pb2.BoundingBox(
                    class_name=r.names[int(box.cls)],
                    x=float(box.xywh[0][0] / img.shape[1]),
                    y=float(box.xywh[0][1] / img.shape[0]),
                    width=float(box.xywh[0][2] / img.shape[1]),
                    height=float(box.xywh[0][3] / img.shape[0]),
                    confidence=float(box.conf[0])
                )
                response.boxes.append(bb)
        return response

def serve():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=12))
    yolo_pb2_grpc.add_YoloServiceServicer_to_server(YoloServiceServicer(), server)
    server.add_insecure_port(f'[::]:{PORT}')
    server.start()
    print(f"YOLO gRPC server started on {PORT}")
    try:
        server.wait_for_termination()
    except KeyboardInterrupt:
        server.stop(0)

if __name__ == "__main__":
    serve()
