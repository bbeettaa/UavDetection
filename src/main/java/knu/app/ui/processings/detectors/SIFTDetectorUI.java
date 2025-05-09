package knu.app.ui.processings.detectors;

import imgui.ImGui;
import imgui.type.ImFloat;
import imgui.type.ImInt;
import knu.app.bll.utils.processors.DetectionResult;
import knu.app.bll.processors.detection.SIFTObjectDetector;
import knu.app.bll.utils.LocalizationManager;
import org.bytedeco.opencv.opencv_core.Mat;

public class SIFTDetectorUI implements DetectorUI {
    private final SIFTObjectDetector detector;
    private final ImInt hessianThreshold = new ImInt(0);
    private final ImInt octaves = new ImInt(3);
    private final ImFloat octaveLayers = new ImFloat(0.04f);
    private final ImFloat edgeThreshold = new ImFloat(10f);
    private final ImFloat sigma = new ImFloat(1.6f);

    public SIFTDetectorUI(SIFTObjectDetector detector) {
        this.detector = detector;
    }

    @Override
    public String getName() {
        return LocalizationManager.tr("processor.detectors.sift");
    }

    @Override
    public void renderSettings() {
        boolean hb = ImGui.sliderInt(LocalizationManager.tr("processor.detectors.sift.nfeatures") + "##SIFTDetectorUI", hessianThreshold.getData(), 0, 200);
        boolean ob = ImGui.sliderInt(LocalizationManager.tr("processor.detectors.surf.octaves") + "##SIFTDetectorUI", octaves.getData(), 1, 8);
        boolean olb = ImGui.sliderFloat(LocalizationManager.tr("processor.detectors.sift.contrast") + "##SIFTDetectorUI", octaveLayers.getData(), 0, 1);
        boolean eb = ImGui.sliderFloat(LocalizationManager.tr("processor.detectors.sift.edgethreshold") + "##SIFTDetectorUI", edgeThreshold.getData(), 0, 30);
        boolean ub = ImGui.sliderFloat(LocalizationManager.tr("processor.detectors.sift.sigma") + "##SIFTDetectorUI", sigma.getData(), 0, 6);
        if (hb || ob || olb || eb || ub) {
            detector.init(hessianThreshold.get(), octaves.get(), octaveLayers.get(), edgeThreshold.get(), sigma.get());
        }
    }

    @Override
    public DetectionResult detect(Mat mat) {
        return detector.detect(mat);
    }
}
