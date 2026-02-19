package knu.app.ui.processings.detectors;

import knu.app.bll.processors.detection.ObjectDetector;
import knu.app.bll.utils.MatWrapper;
import knu.app.bll.utils.processors.DetectionResult;
import org.bytedeco.opencv.opencv_core.Mat;

public interface DetectorUI {
    String getName();

    void renderSettings();

    DetectionResult detect(MatWrapper matWrapper);

    ObjectDetector getDetector();
}
