package knu.app.ui.processings.detectors;

import imgui.ImGui;
import imgui.type.ImFloat;
import imgui.type.ImInt;
import knu.app.bll.processors.detection.SsdObjectDetector;
import knu.app.bll.utils.LocalizationManager;
import knu.app.bll.utils.processors.DetectionResult;
import org.bytedeco.opencv.opencv_core.Mat;

public class SsdDetectorUI implements DetectorUI {

    private final SsdObjectDetector detector;

    private final ImFloat confThreshold;
    private final ImFloat iouThreshold;
    private final ImInt inputWidth;
    private final ImInt inputHeight;

    public SsdDetectorUI(SsdObjectDetector detector) {
        this.detector = detector;
        this.confThreshold = new ImFloat(0.5f);
        this.iouThreshold = new ImFloat(0.4f);
        this.inputWidth = new ImInt(300);
        this.inputHeight = new ImInt(300);
    }

    @Override
    public void renderSettings() {
        ImGui.separator();
        ImGui.newLine();

        if (ImGui.button("Start Stream")) {
            detector.startStreaming();
        }
        ImGui.sameLine();
        if (ImGui.button("Stop Stream")) {
            detector.stopStreaming();
        }
        ImGui.sameLine();
        if (ImGui.button("Restart")) {
            detector.restartConnection();
        }
        boolean updated = false;

        updated |= ImGui.sliderFloat(LocalizationManager.tr("processor.detectors.ssd.conf_threshold"), confThreshold.getData(), 0f, 1f);
        updated |= ImGui.sliderFloat(LocalizationManager.tr("processor.detectors.ssd.iou_thres") , iouThreshold.getData(), 0f, 1f);
        updated |= ImGui.inputInt(LocalizationManager.tr("video.attr.width") + "###ssd", inputWidth);
        updated |= ImGui.inputInt(LocalizationManager.tr("video.attr.height") + "###ssd", inputHeight);

        if (updated) {
            detector.setConfig(
                    detector.getConfig().toBuilder()
                            .setConfidenceThreshold(confThreshold.get())
                            .setNmsThreshold(iouThreshold.get())
                            .setInputWidth(inputWidth.get())
                            .setInputHeight(inputHeight.get())
                            .build()
            );
        }
    }

    @Override
    public DetectionResult detect(Mat mat)  {
        return detector.detect(mat);
    }

    @Override
    public SsdObjectDetector getDetector() {
        return detector;
    }

    @Override
    public String getName() {
        return LocalizationManager.tr("processor.detectors.ssd");
    }
}
