package knu.app.ui.processings.detectors;

import imgui.ImGui;
import imgui.type.ImFloat;
import knu.app.bll.processors.detection.DetectionResult;
import knu.app.bll.processors.detection.FarnebackMotionDetector;
import knu.app.bll.utils.LocalizationManager;
import org.bytedeco.opencv.opencv_core.Mat;

public class FMDDetectorUI implements DetectorUI {
    private final FarnebackMotionDetector fmd;
    private final ImFloat threshold = new ImFloat(0.5f);

    public FMDDetectorUI(FarnebackMotionDetector fmd) {
        this.fmd = fmd;
    }

    @Override
    public String getName() {
        return LocalizationManager.tr("processor.detectors.farne");
    }

    @Override
    public void renderSettings() {
        if (ImGui.sliderFloat(LocalizationManager.tr("processor.detectors.farne.threshold"),
                threshold.getData(), 0.0f, 5.0f, "%.1f"))
            fmd.setMotionThreshold(threshold.get());
    }

    @Override
    public DetectionResult detect(Mat mat) {
        return fmd.detect(mat);
    }
}
