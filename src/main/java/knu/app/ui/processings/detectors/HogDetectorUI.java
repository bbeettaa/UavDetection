package knu.app.ui.processings.detectors;


import imgui.type.ImFloat;
import knu.app.bll.processors.detection.DetectionResult;
import knu.app.bll.processors.detection.HogSvmDetector;
import knu.app.bll.processors.detection.ObjectDetector;
import knu.app.bll.utils.LocalizationManager;
import org.bytedeco.opencv.opencv_core.Mat;

public class HogDetectorUI implements DetectorUI {
    private final ObjectDetector hog;

    public HogDetectorUI(HogSvmDetector hog) {
        this.hog = hog;
    }

    @Override
    public String getName() {
        return LocalizationManager.tr("processor.detectors.hog");
    }

    @Override
    public void renderSettings() {
    }

    @Override
    public DetectionResult detect(Mat mat) {
        return hog.detect(mat);
    }
}
