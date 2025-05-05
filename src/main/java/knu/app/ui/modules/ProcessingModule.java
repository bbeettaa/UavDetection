package knu.app.ui.modules;

import imgui.ImGui;
import imgui.type.ImBoolean;
import imgui.type.ImInt;
import knu.app.bll.utils.HogSvmUtils;
import knu.app.bll.processors.detection.*;
import knu.app.bll.processors.tracker.CSRTTracker;
import knu.app.bll.processors.tracker.MilTracker;
import knu.app.bll.processors.tracker.ObjectTrackerWithKalman;
import knu.app.bll.processors.tracker.OpticalFlowTracker;
import knu.app.bll.utils.LocalizationManager;
import knu.app.ui.processings.detectors.*;
import knu.app.ui.processings.renders.CenterPointRendererUI;
import knu.app.ui.processings.renders.FeaturedPointsRendererUI;
import knu.app.ui.processings.renders.RendererUI;
import knu.app.ui.processings.renders.RoiRendererUI;
import knu.app.ui.processings.trackers.*;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2f;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProcessingModule implements UIModule<Mat> {
    private final ImBoolean isOp = new ImBoolean(true);

    private final ImBoolean useDetectors = new ImBoolean(false);
    private final ImBoolean useTrackers = new ImBoolean(false);

    private DetectionResult detectionResult = new DetectionResult();
    private List<Point2f> trackingResult = new LinkedList<>();

    private final ImBoolean drawDetections = new ImBoolean(true);
    private final ImBoolean drawTracking = new ImBoolean(true);


    private final List<DetectorUI> detectors = new ArrayList<>();
    private final List<TrackerUI> trackers = new ArrayList<>();

    private final ImInt selectedDetector = new ImInt(-1);
    private final ImInt selectedTracker = new ImInt(-1);

    private final List<RendererUI> renderers = new ArrayList<>();
    private final ImInt selectedDetectorRendererIndex = new ImInt(0);
    private final ImInt selectedTrackerRendererIndex = new ImInt(1);
    private String[] rendererNames;

    public ProcessingModule(Mat templateImg) {
        init(templateImg);
    }

    public static final String PROCESSOR_ID = LocalizationManager.tr("menu.processor.name");

    public void init(Mat templateImg) {
        String file = "src/main/resources/HOGDescriptorEsp1";
//        detectors.add(new FMDDetectorUI(new FarnebackMotionDetector()));
        detectors.add(new ORBDetectorUI(new ORBObjectDetector(templateImg)));
        detectors.add(new SIFTDetectorUI(new SIFTObjectDetector(templateImg)));
        detectors.add(new SURFDetectorUI(new SURFObjectDetector(templateImg)));
        detectors.add(new HogDetectorUI(new HogSvmDetector(new HogSvmUtils().loadDescriptorFromFile(file))));

        trackers.add(new KalmanTrackerUI(new ObjectTrackerWithKalman()));
        trackers.add(new LucasKanadeTrackerUI(new OpticalFlowTracker()));
        trackers.add(new CSRTTrackerUI(new CSRTTracker()));
        trackers.add(new MILTrackerUI(new MilTracker()));

        renderers.add(new FeaturedPointsRendererUI());
        renderers.add(new CenterPointRendererUI());
        renderers.add(new RoiRendererUI());
        rendererNames = new String[renderers.size()];
        rendererNames = renderers.stream()
                .map(rendererUI -> rendererUI.getRenderer().getClass().getSimpleName())
                .toArray(String[]::new);
    }


    @Override
    public String getName() {
        return PROCESSOR_ID;
    }

    @Override
    public void render() {
        if (!isOp.get()) return;
        ImGui.begin(getName(), isOp);
        ImGui.pushItemWidth(150);

        renderDetectors();

        ImGui.newLine();
        ImGui.separator();
        ImGui.newLine();

        renderTrackers();

        ImGui.newLine();
        ImGui.separator();
        ImGui.newLine();

        renderDrawingSettings();


        ImGui.popItemWidth();
        ImGui.end();
    }

    private void renderDetectors() {
        ImGui.checkbox(LocalizationManager.tr("processor.detectors.enable"), useDetectors);
        ImGui.beginDisabled(!useDetectors.get());

        for (int i = 0; i < detectors.size(); i++) {
            ImGui.radioButton(detectors.get(i).getName(), selectedDetector, i);
            if (i == selectedDetector.get()) {
                detectors.get(selectedDetector.get()).renderSettings();
            }
        }
        ImGui.endDisabled();
    }

    private void renderTrackers() {
        ImGui.checkbox(LocalizationManager.tr("processor.tracker.enable"), useTrackers);
        ImGui.beginDisabled(!useTrackers.get());

        for (int i = 0; i < trackers.size(); i++) {
            ImGui.radioButton(trackers.get(i).getName(), selectedTracker, i);
            if (i == selectedTracker.get()) {
                trackers.get(selectedTracker.get()).renderSettings();
            }
        }
        ImGui.endDisabled();
    }

    private void renderDrawingSettings() {
        ImGui.checkbox(LocalizationManager.tr("processor.draw.enable"), drawDetections);
        ImGui.beginDisabled(!drawDetections.get());
        ImGui.combo(LocalizationManager.tr("processor.draw.choose") + "##" + "detection", selectedDetectorRendererIndex,
                rendererNames, rendererNames.length);
        renderers.get(selectedDetectorRendererIndex.get()).renderSettings("detect");
        ImGui.endDisabled();

        ImGui.newLine();
        ImGui.separator();
        ImGui.newLine();

        ImGui.checkbox(LocalizationManager.tr("processor.draw.enable"), drawTracking);
        ImGui.beginDisabled(!drawTracking.get());
        ImGui.combo(LocalizationManager.tr("processor.draw.choose") + "##" + "tracking", selectedTrackerRendererIndex,
                rendererNames, rendererNames.length);
        renderers.get(selectedTrackerRendererIndex.get()).renderSettings("track");
        ImGui.endDisabled();
    }

    @Override
    public Mat execute(Mat mat) {
        executeDetectors(mat);
        executeTrackers(mat);

        if (drawDetections.get() && detectionResult != null) {
            if (!detectionResult.getPoints().isEmpty()) {
                renderers.get(selectedDetectorRendererIndex.get()).render(mat, detectionResult.getPoints());
            } else if (!detectionResult.getRects().isEmpty()) {
                renderers.get(selectedDetectorRendererIndex.get()).render(mat, detectionResult.getRects(), detectionResult.getScores(), true);
            }
        }
        if (drawTracking.get() && trackingResult != null)
            renderers.get(selectedTrackerRendererIndex.get()).render(mat, trackingResult);

        return mat;
    }

    private void executeDetectors(Mat mat) {
        if (useDetectors.get() && selectedDetector.get() >= 0 && selectedDetector.get() < detectors.size()) {
            detectionResult = detectors.get(selectedDetector.get()).detect(mat);
        }
    }

    private void executeTrackers(Mat mat) {
        if (useTrackers.get() && selectedTracker.get() >= 0 && selectedTracker.get() < trackers.size()) {
            trackingResult = trackers.get(selectedTracker.get()).track(mat, detectionResult.getPoints());
        }
    }


    @Override
    public void show() {
        isOp.set(true);
    }

    @Override
    public void toggle() {
        isOp.set(!isOp.get());
    }

    @Override
    public boolean isOpened() {
        return isOp.get();
    }

}
