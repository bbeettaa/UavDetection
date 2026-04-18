package knu.app.ui.modules;

import imgui.ImGui;
import imgui.flag.ImGuiTreeNodeFlags;
import imgui.type.ImBoolean;
import imgui.type.ImInt;

import java.util.ArrayList;
import java.util.List;

import knu.app.Main;
import knu.app.bll.mot.TrackingManager;
import knu.app.bll.postprocessors.PredictionOverlayPostprocessor;
import knu.app.bll.processors.detection.*;
import knu.app.bll.utils.LocalizationManager;
import knu.app.bll.utils.MatWrapper;
import knu.app.bll.utils.MetricsEvaluator;
import knu.app.bll.utils.processors.DetectionResult;
import knu.app.bll.utils.processors.TrackedObject;
import knu.app.bll.utils.processors.hog.HogSvmDetectorConfig;
import knu.app.bll.utils.processors.hog.HogSvmUtils;
import knu.app.ui.processings.detectors.*;
import knu.app.ui.processings.renders.CenterPointRendererUI;
import knu.app.ui.processings.renders.FeaturedPointsRendererUI;
import knu.app.ui.processings.renders.RendererUI;
import knu.app.ui.processings.renders.RoiRendererUI;
import knu.app.ui.processings.renders.TrajectoryRendererUI;
import org.bytedeco.opencv.opencv_core.Mat;

public class ProcessingModule implements UIModule<MatWrapper> {

    public static final String PROCESSOR_ID = LocalizationManager.tr("menu.processor.name");
    private final List<TrackingManager> trackingManagers;
    private final ImBoolean isOp = new ImBoolean(true);
    private final ImBoolean useDetectors = new ImBoolean(false);
    private final ImInt selectedDetector = new ImInt(-1);
    private final ImInt selectedTrackerManager = new ImInt(1);
    private final List<DetectorUI> detectors = new ArrayList<>();
    private final int[] predHorizont = new int[1];

    private final ImBoolean drawDetections = new ImBoolean(true);
    private final ImBoolean drawTracking = new ImBoolean(true);
    private final ImBoolean drawTrajectory = new ImBoolean(false);
    private final ImBoolean drawSpeedTrajectory = new ImBoolean(false);
    private final ImBoolean drawDirectionTrajectory = new ImBoolean(false);
    private final ImBoolean drawPrediction = new ImBoolean(false);

    private final List<RendererUI> renderers = new ArrayList<>();
    private final ImInt selectedDetectorRendererIndex = new ImInt(2);
    private final ImInt selectedTrackerRendererIndex = new ImInt(1);

    private final TrajectoryRendererUI trajectoryRender;

    private TrackingManager trackingManager;
    private DetectionResult detectionResult = new DetectionResult();
    private String[] rendererNames;

    private MetricsEvaluator metrics;
    private AnalyticsUIModule analyticsUIModule;
    private final PredictionOverlayPostprocessor predictionOverlay;


    public ProcessingModule(List<TrackingManager> trackingManagers, Mat templateImg,
                            String hogDescriptorFile, HogSvmDetectorConfig hogSvmDetectorConfig,
                            MetricsEvaluator metrics, AnalyticsUIModule analyticsUIModule,
                            TrajectoryRendererUI trajectoryRender, PredictionOverlayPostprocessor predictionOverlay) {
        init(templateImg, hogDescriptorFile, hogSvmDetectorConfig);
        this.metrics = metrics;
        this.trackingManagers = trackingManagers;
        this.trackingManager = trackingManagers.get(selectedTrackerManager.get());
        this.analyticsUIModule = analyticsUIModule;
        this.trajectoryRender = trajectoryRender;
        this.predictionOverlay = predictionOverlay;

        predHorizont[0] = predictionOverlay.getPredictionHorizon();
    }

    private static void imGuiSeparate() {
        ImGui.newLine();
        ImGui.separator();
        ImGui.newLine();
    }

    public void init(Mat templateImg, String hogDescriptorFile,
                     HogSvmDetectorConfig hogSvmDetectorConfig) {
        detectors.add(new ORBDetectorUI(new ORBObjectDetector(templateImg)));
        detectors.add(new SIFTDetectorUI(new SIFTObjectDetector(templateImg)));
        detectors.add(new SURFDetectorUI(new SURFObjectDetector(templateImg)));
        detectors.add(new HogDetectorUI(
                new HogSvmDetector(hogDescriptorFile,
                        hogSvmDetectorConfig)));

        detectors.add(new YoloDetectorUi(new YoloObjectDetector(Main.HOST, Main.YOLO_PORT)));
        detectors.add(new SsdDetectorUI(new SsdObjectDetector(Main.HOST, Main.SSD_PORT)));

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
        if (!isOp.get()) {
            return;
        }
        ImGui.begin(getName(), isOp);
        ImGui.pushItemWidth(150);

        renderDetectors();
        ImGui.newLine();
        renderTrackingManager();
        ImGui.newLine();
        renderPredictionSettings();
        ImGui.newLine();
        renderDrawingSettings();
        ImGui.newLine();

        ImGui.popItemWidth();
        ImGui.end();
    }

    private void renderDetectors() {
        if (ImGui.collapsingHeader(LocalizationManager.tr("processor.detectors.name"),
                ImGuiTreeNodeFlags.DefaultOpen)) {
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
    }

    private void renderTrackingManager() {
        if (ImGui.collapsingHeader(trackingManager.getName(),
                ImGuiTreeNodeFlags.DefaultOpen)) {
            for (int i = 0; i < trackingManagers.size(); i++) {
                if (ImGui.radioButton(trackingManagers.get(i).getName(),
                        (selectedTrackerManager.get() == i))) {
                    trackingManager = trackingManagers.get(i);
                    selectedTrackerManager.set(i);
                }
            }
            ImGui.separator();

            trackingManager.renderSettings();
            trackingManager.renderTrackers();
        }

    }

    private void renderDrawingSettings() {
        if (ImGui.collapsingHeader(LocalizationManager.tr("processor.draw.name") + "##detection",
                ImGuiTreeNodeFlags.DefaultOpen)) {
            ImGui.checkbox(LocalizationManager.tr("processor.draw.enable") + "##detection",
                    drawDetections);
            ImGui.beginDisabled(!drawDetections.get());
            ImGui.combo(LocalizationManager.tr("processor.draw.choose") + "##detection",
                    selectedDetectorRendererIndex, rendererNames, rendererNames.length);
            renderers.get(selectedDetectorRendererIndex.get()).renderSettings("detect");
            ImGui.endDisabled();

            imGuiSeparate();

            ImGui.checkbox(LocalizationManager.tr("processor.draw.enable") + "##" + "tracking",
                    drawTracking);
            ImGui.beginDisabled(!drawTracking.get());
            ImGui.combo(LocalizationManager.tr("processor.draw.choose") + "##" + "tracking",
                    selectedTrackerRendererIndex, rendererNames, rendererNames.length);
            renderers.get(selectedTrackerRendererIndex.get()).renderSettings("track");
            ImGui.endDisabled();

            imGuiSeparate();

            ImGui.checkbox(LocalizationManager.tr("processor.draw.direction.enable") + "##" + "trajectory", drawDirectionTrajectory);
            ImGui.checkbox(LocalizationManager.tr("processor.draw.speed.enable") + "##" + "trajectory", drawSpeedTrajectory);
            ImGui.checkbox(LocalizationManager.tr("processor.draw.trajectory.enable") + "##" + "trajectory",
                    drawTrajectory);
            trajectoryRender.renderSettings("trajectory");
        }
    }

    private void renderPredictionSettings(){
        if (ImGui.collapsingHeader("Prediction" + "##prediction", ImGuiTreeNodeFlags.DefaultOpen)) {
            ImGui.checkbox("Prediction Enable" + "##" + "trajectory", drawPrediction);
            if(ImGui.sliderInt("Prediction Horizont", predHorizont, 0, 100))
                predictionOverlay.setPredictionHorizon(predHorizont[0]);
        }
    }

    @Override
    public MatWrapper execute(MatWrapper matWrapper) {
        Mat mat = matWrapper.mat;
        if (useDetectors.get() && selectedDetector.get() >= 0 && selectedDetector.get() < detectors.size()) {
            detectionResult = detectors.get(selectedDetector.get()).detect(matWrapper);
        }

        List<TrackedObject> trackedObjects = null;
        if (useDetectors.get())
            trackedObjects = trackingManager.update(mat, detectionResult);

        if (drawDetections.get() && detectionResult != null) {
            if (!detectionResult.getRects().isEmpty()) {
                renderers.get(selectedDetectorRendererIndex.get())
                        .render(mat, detectionResult.getRects(), detectionResult.getScores()
                                , detectionResult.getNames(), true);
            }
        }

        if (drawTracking.get()) {
            if (trackedObjects != null) {
                renderers.get(selectedTrackerRendererIndex.get()).render(mat, trackedObjects, true);
            }
        }

        if (trackedObjects != null) trajectoryRender.getRenderer().update(mat, trackedObjects);

        if (trackedObjects != null) {
            if (drawTrajectory.get()) {
                trajectoryRender.getRenderer().renderTrajectory(mat, trackedObjects);
            }
            if (drawSpeedTrajectory.get()) {
                trajectoryRender.getRenderer().renderSpeed(mat, trackedObjects);
            }
            if (drawDirectionTrajectory.get()) {
                trajectoryRender.getRenderer().renderDirection(mat, trackedObjects);
            }
            if (drawDirectionTrajectory.get()) {
                trajectoryRender.getRenderer().renderAnomalies(mat, trackedObjects);
            }
        }

        if (trackedObjects != null && drawPrediction.get()) {
            predictionOverlay.setValue(trackedObjects);
            predictionOverlay.process(mat);
        }


        if (trackedObjects != null) {
            metrics.evaluate(matWrapper.frameIndex,
                    trackedObjects.stream().map(TrackedObject::getRect).toList());
            analyticsUIModule.execute(trackedObjects);
        }

        return matWrapper;
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
