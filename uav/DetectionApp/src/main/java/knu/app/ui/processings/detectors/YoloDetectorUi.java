package knu.app.ui.processings.detectors;

import imgui.ImGui;
import imgui.flag.ImGuiTreeNodeFlags;
import imgui.type.ImBoolean;
import imgui.type.ImFloat;
import imgui.type.ImInt;

import knu.app.bll.processors.detection.YoloObjectDetector;
import knu.app.bll.utils.LocalizationManager;
import knu.app.bll.utils.MatWrapper;
import knu.app.bll.utils.processors.DetectionResult;
import knu.app.grpc.yolo.YoloConfig;

public class YoloDetectorUi implements DetectorUI {

    private final YoloObjectDetector detector;
    private final ImFloat confTh = new ImFloat(0.25F);
    private final ImFloat iouTh = new ImFloat(0.25F);
    private final ImBoolean isAugment = new ImBoolean(false);
    private final ImBoolean isHalf = new ImBoolean(false);
    private final ImInt swidth;
    private final ImInt sheight;
    private final ImInt jpegQuality = new ImInt(85);
    private final ImInt waitDuration = new ImInt(500);
    private final ImInt modelIndex = new ImInt(1);
    private final String[] modelOptions = {
            "yolov8n.pt",
            "yolov8s.pt",
            "yolov8m.pt",
            "yolov8l.pt"
    };


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

        if (ImGui.combo("YOLO Model", modelIndex, modelOptions)) {
            updateConfig();
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
            if (ImGui.sliderInt(LocalizationManager.tr("processor.network.settings.jpeg.quality")+"##YOLODetectorUI",
                    jpegQuality.getData(), 0, 100)) {
                detector.setJpegQuality(jpegQuality.get());
            }
            ImGui.separator();
            if (ImGui.sliderInt("Wait Duration ms",
                    waitDuration.getData(), 0, 2000)) {
                detector.setWaitDuration(waitDuration.get());
            }
            ImGui.separator();
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
        String modelPath = "server/detector/yolo/" + modelOptions[modelIndex.get()];
        YoloConfig config = YoloConfig.newBuilder()
                .setModelPath(modelPath)
                .setConfidenceThreshold(confTh.get())
                .setIouThreshold(iouTh.get())
                .setHalfPrecision(isHalf.get())
                .setInputWidth(swidth.get())
                .setInputHeight(sheight.get())
                .build();
        detector.sendConfig(config);
    }

    @Override
    public DetectionResult detect(MatWrapper matWrapper) {
//        long startTime = System.nanoTime();
        DetectionResult result = detector.detect(matWrapper);
//        double durationMs = (double) (System.nanoTime() - startTime) / 1_000_000.0;
//        System.out.printf("Tracker update execution time: %.3f ms%n", durationMs);
        return result;
    }

    @Override
    public YoloObjectDetector getDetector() {
        return detector;
    }
}
