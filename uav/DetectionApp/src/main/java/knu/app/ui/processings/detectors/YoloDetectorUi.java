package knu.app.ui.processings.detectors;

import imgui.ImGui;
import imgui.flag.ImGuiTreeNodeFlags;
import imgui.type.ImBoolean;
import imgui.type.ImFloat;
import imgui.type.ImInt;

import java.util.List;

import knu.app.bll.processors.detection.YoloObjectDetector;
import knu.app.bll.utils.LocalizationManager;
import knu.app.bll.utils.processors.DetectionResult;
import knu.app.bll.utils.processors.TrackedObject;
import knu.app.grpc.yolo.YoloConfig;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;

public class YoloDetectorUi implements DetectorUI {

    private final YoloObjectDetector detector;
    private final ImFloat confTh = new ImFloat(0.25F);
    private final ImFloat iouTh = new ImFloat(0.25F);
    private final ImBoolean isAugment = new ImBoolean(false);
    private final ImBoolean isHalf = new ImBoolean(false);

    private final ImInt swidth;
    private final ImInt sheight;
//    private final ImInt jpegCompr;


    public YoloDetectorUi(YoloObjectDetector detector) {
        this.detector = detector;
        this.swidth = new ImInt(detector.getConfig().getInputWidth());
        this.sheight = new ImInt(detector.getConfig().getInputHeight());
//        this.jpegCompr = new ImInt(detector.getJpegQuality());
    }

    @Override
    public String getName() {
        return LocalizationManager.tr("processor.detectors.yolo");
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

        ImGui.separator();

        if (ImGui.sliderFloat(LocalizationManager.tr("processor.detectors.yolo.conf_threshold") + "##YOLODetectorUI",
                confTh.getData(), 0, 1)) {
            updateConfig();
        }
        if (ImGui.sliderFloat(LocalizationManager.tr("processor.detectors.yolo.iou_thres") + "##YOLODetectorUI",
                iouTh.getData(), 0, 1)) {
            updateConfig();
        }
//        if (ImGui.checkbox(LocalizationManager.tr("processor.detectors.yolo.augment") + "##YOLODetectorUI", isAugment);
        if (ImGui.checkbox(LocalizationManager.tr("processor.detectors.yolo.half") + "##YOLODetectorUI", isHalf)) {
            updateConfig();
        }

        ImGui.separator();
        ImGui.newLine();

        if (ImGui.collapsingHeader(LocalizationManager.tr("processor.network.settings") + "##YOLODetectorUI",
                ImGuiTreeNodeFlags.DefaultOpen)) {
//            if (ImGui.sliderInt(LocalizationManager.tr(
//                    "processor.network.settings.jpeg.quality") + "##YOLODetectorUI", jpegCompr.getData(), 0, 100)) {
//                updateConfig();
//            }
            if (ImGui.inputInt(LocalizationManager.tr("frame.width.name"), swidth, 5, 50)) {
                updateConfig();
            }
            ImGui.sameLine();
            if (ImGui.inputInt(LocalizationManager.tr("frame.height.name"), sheight, 5, 50)) {
                updateConfig();
            }
        }
    }

    private void updateConfig() {
        YoloConfig config = YoloConfig.newBuilder()
                .setConfidenceThreshold(confTh.get())
                .setIouThreshold(iouTh.get())
                .setHalfPrecision(isHalf.get())
                .setInputWidth(swidth.get())
                .setInputHeight(sheight.get())
                .build();
        detector.sendConfig(config);
    }

    @Override
    public DetectionResult detect(Mat mat) {
//        long startTime = System.nanoTime();
        DetectionResult result = detector.detect(mat);
//        double durationMs = (double) (System.nanoTime() - startTime) / 1_000_000.0;
//        System.out.printf("Tracker update execution time: %.3f ms%n", durationMs);
        return result;
    }

    @Override
    public YoloObjectDetector getDetector() {
        return detector;
    }
}
