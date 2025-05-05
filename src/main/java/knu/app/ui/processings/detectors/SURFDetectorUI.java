package knu.app.ui.processings.detectors;

import imgui.ImGui;
import imgui.type.ImBoolean;
import imgui.type.ImInt;
import knu.app.bll.processors.detection.DetectionResult;
import knu.app.bll.processors.detection.SURFObjectDetector;
import knu.app.bll.utils.LocalizationManager;
import org.bytedeco.opencv.opencv_core.Mat;

public class SURFDetectorUI implements DetectorUI {
    private final SURFObjectDetector detector;
    private final ImInt hessianThreshold = new ImInt(100);
    private final ImInt octaves = new ImInt(4);
    private final ImInt octaveLayers = new ImInt(3);
    private final ImBoolean extended = new ImBoolean(false);
    private final ImBoolean upright = new ImBoolean(false);

    public SURFDetectorUI(SURFObjectDetector detector) {
        this.detector = detector;
    }

    @Override
    public String getName() {
        return LocalizationManager.tr("processor.detectors.surf");
    }

    @Override
    public void renderSettings() {
        boolean hb = ImGui.sliderInt(LocalizationManager.tr("processor.detectors.surf.hessianth") + "##SURFDetectorUI", hessianThreshold.getData(), 10, 1000);
        boolean ob = ImGui.sliderInt(LocalizationManager.tr("processor.detectors.surf.octaves") + "##SURFDetectorUI", octaves.getData(), 1, 8);
        boolean olb = ImGui.sliderInt(LocalizationManager.tr("processor.detectors.surf.octlayer") + "##SURFDetectorUI", octaveLayers.getData(), 1, 8);
        boolean eb = ImGui.checkbox(LocalizationManager.tr("processor.detectors.surf.extend") + "##SURFDetectorUI", extended);
        boolean ub = ImGui.checkbox(LocalizationManager.tr("processor.detectors.surf.upright") + "##SURFDetectorUI", upright);
        if (hb || ob || olb || eb || ub) {
            detector.init(hessianThreshold.get(), octaves.get(), octaveLayers.get(), extended.get(), upright.get());
        }
    }

    @Override
    public DetectionResult detect(Mat mat) {
        return detector.detect(mat);
    }

}
