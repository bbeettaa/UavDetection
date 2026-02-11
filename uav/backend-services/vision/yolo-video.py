import os
from collections import defaultdict, deque
from typing import Any

import cv2
import random
import time

from PIL.ImageDraw import Outline
from ultralytics import YOLO

# ======================================================
# ======================================================
# yolo = YOLO("./neural/yolov5x.pt")
# yolo = YOLO("./neural/yolov8x.pt")
# yolo = YOLO("./neural/yolov9c.pt")
yolo = YOLO("./neural/yolov9e.pt")
PROCESS_WIDTH = 1280
PROCESS_HEIGHT = 800
CONF_THRES = 0.3
IOU = 0.5
AUGMENT = True
AGNOSTIC = False

# display settings
FONT_SCALE = 0.45
FONT_THICKNESS = 1
LABEL_PADDING = 5
RECT_THICKNESS = 1
OVERLAY_ALPHA = 0.55
LABEL_FONT = font = cv2.QT_FONT_NORMAL

# ================= text outline =======================
OUTLINE_FONT_THICKNESS = FONT_THICKNESS + 3
OUTLINE_COLOR = (0, 0, 0)

# ======================================================


OUTPUT_DIR = "./output"
VIDEO_WIDTH = 1280
VIDEO_HEIGHT = 800
INPUT_FPS = 30

os.makedirs(OUTPUT_DIR, exist_ok=True)



# ======================================================

def get_color_by_id(id_num: int):
    random.seed(int(id_num) + 12345)
    return tuple(random.randint(0, 255) for _ in range(3))


def draw_label(img, text, org, text_font=cv2.FONT_HERSHEY_SIMPLEX,
               font_scale=0.6, thickness=2, padding=6, text_color=(255, 255, 255)):
    (w, h), _ = cv2.getTextSize(text, text_font, font_scale, thickness)
    x, y = org
    bg_x1 = max(x - padding // 2, 0)
    bg_y1 = max(y - h - padding // 2, 0)
    bg_x2 = min(x + w + padding // 2, img.shape[1])
    bg_y2 = min(y + padding // 2, img.shape[0])
    overlay = img.copy()
    cv2.rectangle(overlay, (bg_x1, bg_y1), (bg_x2, bg_y2), (0, 0, 0), -1)
    cv2.addWeighted(overlay, 0.55, img, 0.45, 0, img)
    cv2.putText(img, text, org, text_font, font_scale, OUTLINE_COLOR, OUTLINE_FONT_THICKNESS, cv2.LINE_AA)
    cv2.putText(img, text, org, text_font, font_scale, text_color, thickness, cv2.LINE_AA)


def draw_objects(class_name, cls: int, conf: float, proc_img,
                 x1: int, x2: int, y1: int, y2: int):
    color = get_color_by_id(cls)
    cv2.rectangle(proc_img, (x1, y1), (x2, y2), color, RECT_THICKNESS, cv2.LINE_AA)
    label = f"{class_name} {conf:.2f}"
    draw_label(proc_img, label,
               (x1 + 5, max(y1 - 8, 15)), text_font=LABEL_FONT, font_scale=FONT_SCALE,
               thickness=FONT_THICKNESS, padding=LABEL_PADDING, text_color=color)


def get_objects_coords_and_classname(box, class_names) -> tuple[Any, int, int, int, int, int]:
    x1, y1, x2, y2 = map(int, box.xyxy[0])
    cls = int(box.cls[0])
    class_name = class_names.get(cls, str(cls))
    return class_name, cls, x1, x2, y1, y2


def preprocessing(img):
    """Сжимает изображение, если оно шире PROCESS_WIDTH"""
    return cv2.resize(img, (PROCESS_WIDTH, PROCESS_HEIGHT), interpolation=cv2.INTER_LINEAR)




def prepare_video_recorder(path):
    cap = cv2.VideoCapture(path)
    cap.set(cv2.CAP_PROP_FRAME_WIDTH, VIDEO_WIDTH)
    cap.set(cv2.CAP_PROP_FRAME_HEIGHT, VIDEO_HEIGHT)
    cap.set(cv2.CAP_PROP_FPS, INPUT_FPS)
    return cap

def main(path):
    cap = prepare_video_recorder(path)
    frame_count = 0
    start_time = time.time()

    ret, frame = cap.read()
    if not ret:
        return

    proc_img = preprocessing(frame)
    real_fps = cap.get(cv2.CAP_PROP_FPS)

    h, w = proc_img.shape[:2]
    out = cv2.VideoWriter(
        os.path.join(OUTPUT_DIR, 'yolo_output1.mp4'),
        cv2.VideoWriter_fourcc(*'mp4v'),
        real_fps,
        (w, h)
    )
    # Note: If 'X264' or 'H264' doesn't work, try changing the extension to '.avi' with 'MJPG' or 'XVID'


    while True:
        ret, frame = cap.read()
        if not ret:
            break

        frame_count += 1


        proc_img = preprocessing(frame)

        results = yolo(proc_img, conf=CONF_THRES, augment=AUGMENT, iou=IOU,
                       max_det=1500, agnostic_nms=AGNOSTIC)

        for result in results:
            class_names = result.names
            for box in result.boxes:
                conf = float(box.conf[0])
                # if conf < CONF_THRES:
                #     continue

                class_name, cls, x1, x2, y1, y2 = get_objects_coords_and_classname(box, class_names)

                draw_objects(class_name, cls, conf, proc_img, x1, x2, y1, y2)

        cv2.imshow("Result", proc_img)

        out.write(proc_img)


        if cv2.waitKey(1) & 0xFF == ord('q'):
            break

    cap.release()
    out.release()
    cv2.destroyAllWindows()


main("/home/bedu/Загрузки/HELLDIVERS™ 2 - 2025-09-27 9-11-02 PM.mp4")

cv2.destroyAllWindows()
