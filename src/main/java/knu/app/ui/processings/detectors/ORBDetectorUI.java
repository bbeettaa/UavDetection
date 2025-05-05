package knu.app.ui.processings.detectors;

import imgui.ImGui;
import imgui.type.ImFloat;
import imgui.type.ImInt;
import knu.app.bll.processors.detection.DetectionResult;
import knu.app.bll.processors.detection.ORBObjectDetector;
import knu.app.bll.utils.LocalizationManager;
import org.bytedeco.opencv.opencv_core.Mat;

public class ORBDetectorUI implements DetectorUI {
    private final ORBObjectDetector detector;

    ImInt nfeatures = new ImInt(500);
    ImFloat scaleFactor = new ImFloat(1.2f);
    ImInt nlevels = new ImInt(8);
    ImInt edgeThreshold = new ImInt(31);
    ImInt firstlevel = new ImInt(0);
    ImInt wtak = new ImInt(2);
    ImInt scoreType = new ImInt(0);
    ImInt patchcSize = new ImInt(31);
    ImInt fastThreshold = new ImInt(20);


    public ORBDetectorUI(ORBObjectDetector detector) {
        this.detector = detector;
    }

    @Override
    public String getName() {
        return LocalizationManager.tr("processor.detectors.orb");
    }

    @Override
    public void renderSettings() {
        boolean nf = ImGui.sliderInt(LocalizationManager.tr("processor.detectors.orb.nfeatures") + "##ORBDetectorUI", nfeatures.getData(), 1, 1000);
        boolean sf = ImGui.sliderFloat(LocalizationManager.tr("processor.detectors.orb.scalefactor") + "##ORBDetectorUI", scaleFactor.getData(), 1, 1.49f);
        boolean nl = ImGui.sliderInt(LocalizationManager.tr("processor.detectors.orb.nlevels") + "##ORBDetectorUI", nlevels.getData(), 1, 20);
        boolean eth = ImGui.sliderInt(LocalizationManager.tr("processor.detectors.orb.edgethreshold") + "##ORBDetectorUI", edgeThreshold.getData(), 0, 100);
        boolean fl = ImGui.sliderInt(LocalizationManager.tr("processor.detectors.orb.firstlevel") + "##ORBDetectorUI", firstlevel.getData(), 0, 10);
        boolean wtk = ImGui.sliderInt(LocalizationManager.tr("processor.detectors.orb.wtak") + "##ORBDetectorUI", wtak.getData(), 2, 4);
        boolean st = ImGui.sliderInt(LocalizationManager.tr("processor.detectors.orb.scoretype") + "##ORBDetectorUI", scoreType.getData(), 0, 1);
        boolean ps = ImGui.sliderInt(LocalizationManager.tr("processor.detectors.orb.patchcsize") + "##ORBDetectorUI", patchcSize.getData(), 2, 100);
        boolean fth = ImGui.sliderInt(LocalizationManager.tr("processor.detectors.orb.fastthreshold") + "##ORBDetectorUI", fastThreshold.getData(), 0, 100);

        if (nf || sf || nl || eth || fl|| wtk|| st ||ps|| fth) {
            detector.init(nfeatures.get(), scaleFactor.get(), nlevels.get(), edgeThreshold.get(), firstlevel.get(),
                    wtak.get(), scoreType.get(), patchcSize.get(), fastThreshold.get());
        }
    }

    @Override
    public DetectionResult detect(Mat mat) {
        return detector.detect(mat);
    }
}
