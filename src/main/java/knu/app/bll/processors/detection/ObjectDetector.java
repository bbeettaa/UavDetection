package knu.app.bll.processors.detection;

import knu.app.bll.utils.processors.DetectionResult;
import org.bytedeco.opencv.opencv_core.Mat;

public interface ObjectDetector {
    DetectionResult detect(Mat frameGray);
}

