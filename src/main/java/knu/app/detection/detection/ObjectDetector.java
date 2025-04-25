package knu.app.detection.detection;

import org.bytedeco.opencv.opencv_core.Mat;

public interface ObjectDetector {
    DetectionResult detect(Mat frameGray);
}

