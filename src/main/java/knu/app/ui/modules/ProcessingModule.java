package knu.app.ui.modules;

import imgui.ImGui;
import imgui.flag.ImGuiTreeNodeFlags;
import imgui.type.ImBoolean;
import imgui.type.ImInt;
import knu.app.bll.utils.ObjectTrackerFactory;
import knu.app.bll.mot.TrackingManager;
import knu.app.bll.processors.detection.HogSvmDetector;
import knu.app.bll.processors.detection.ORBObjectDetector;
import knu.app.bll.processors.detection.SIFTObjectDetector;
import knu.app.bll.processors.detection.SURFObjectDetector;
import knu.app.bll.processors.tracker.CSRTTracker;
import knu.app.bll.processors.tracker.KalmanObjectTracker;
import knu.app.bll.processors.tracker.MilTracker;
import knu.app.bll.processors.tracker.OpticalFlowTracker;
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
import knu.app.ui.processings.trackers.*;
import org.bytedeco.opencv.opencv_core.Mat;

import java.util.ArrayList;
import java.util.List;

public class ProcessingModule implements UIModule<MatWrapper> {
    //    private final DetectionTrackingManager detectionTrackingManager;
    private final TrackingManager trackingManager;

    private final ImBoolean isOp = new ImBoolean(true);

    private DetectionResult detectionResult = new DetectionResult();

    private final ImBoolean useDetectors = new ImBoolean(false);
    private final ImBoolean useTrackers = new ImBoolean(false);

    private final ImInt selectedDetector = new ImInt(-1);
    private final ImInt selectedTracker = new ImInt(-1);
    private final List<DetectorUI> detectors = new ArrayList<>();
    private final List<TrackerUI> trackers = new ArrayList<>();

    private final ImBoolean drawDetections = new ImBoolean(true);
    private final ImBoolean drawTracking = new ImBoolean(true);

    private final List<RendererUI> renderers = new ArrayList<>();
    private final ImInt selectedDetectorRendererIndex = new ImInt(2);
    private final ImInt selectedTrackerRendererIndex = new ImInt(1);
    private String[] rendererNames;

    private final int[] bufferCapacity;
    private final float[] iouThreshold;
    private final int[] nOfMConfirmation;
    private final int[] maxMissedDeleting;

    private MetricsEvaluator metrics;

    public ProcessingModule(TrackingManager trackingManager, Mat templateImg, String hogDescriptorFile, HogSvmDetectorConfig hogSvmDetectorConfig, MetricsEvaluator metrics) {
        init(templateImg, hogDescriptorFile, hogSvmDetectorConfig);
        this.metrics = metrics;
        this.trackingManager = trackingManager;
        this.bufferCapacity = new int[]{trackingManager.getBufferCapacity()};

        this.iouThreshold = new float[]{(float) trackingManager.getAssociationAlgorithm().getIouThreshold()};
        this.nOfMConfirmation = new int[]{trackingManager.getConfirmationAlgorithm().getN(), trackingManager.getConfirmationAlgorithm().getM()};
        this.maxMissedDeleting = new int[]{trackingManager.getDeletingConfirmationAlgorithm().getMaxMissed()};
    }

    public static final String PROCESSOR_ID = LocalizationManager.tr("menu.processor.name");

    public void init(Mat templateImg, String hogDescriptorFile, HogSvmDetectorConfig hogSvmDetectorConfig) {
        detectors.add(new ORBDetectorUI(new ORBObjectDetector(templateImg)));
        detectors.add(new SIFTDetectorUI(new SIFTObjectDetector(templateImg)));
        detectors.add(new SURFDetectorUI(new SURFObjectDetector(templateImg)));
        detectors.add(new HogDetectorUI(new HogSvmDetector(HogSvmUtils.loadDescriptorFromFile(hogDescriptorFile), hogSvmDetectorConfig)));


        ObjectTrackerFactory trackerFactory = ObjectTrackerFactory.getInstance();
        trackers.add(new KalmanTrackerUI(new KalmanObjectTracker()));
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
        renderTrackers();
        renderTrackingManager();
        renderDrawingSettings();

        ImGui.popItemWidth();
        ImGui.end();
    }

    private void renderDetectors() {
        if (ImGui.collapsingHeader(LocalizationManager.tr("processor.detectors.name"), ImGuiTreeNodeFlags.DefaultOpen)) {
            ImGui.checkbox(LocalizationManager.tr("processor.detectors.enable"), useDetectors);
            ImGui.beginDisabled(!useDetectors.get());
            for (int i = 0; i < detectors.size(); i++) {
                ImGui.radioButton(detectors.get(i).getName(), selectedDetector, i);
                if (i == selectedDetector.get()) detectors.get(selectedDetector.get()).renderSettings();
            }
            ImGui.endDisabled();
        }
    }

    private void renderTrackers() {
        if (ImGui.collapsingHeader(LocalizationManager.tr("processor.tracker.name"), ImGuiTreeNodeFlags.DefaultOpen)) {
        ImGui.checkbox(LocalizationManager.tr("processor.tracker.enable"), useTrackers);
        ImGui.beginDisabled(!useTrackers.get());

        for (int i = 0; i < trackers.size(); i++) {
            if (ImGui.radioButton(trackers.get(i).getName(), selectedTracker, i)) {
                trackingManager.setObjectTracker(trackers.get(i).getKey());
            }
            if (i == selectedTracker.get()) {
                trackers.get(selectedTracker.get()).renderSettings();
            }
        }
        ImGui.endDisabled();
        }
    }

    private void renderTrackingManager() {
        if (ImGui.collapsingHeader(LocalizationManager.tr("processor.tracker.manager.name"), ImGuiTreeNodeFlags.DefaultOpen)) {

            ImGui.bulletText(String.format("%s: %s", LocalizationManager.tr("processor.tracker.manager.buffer.size"), trackingManager.getBufferSize()));
            if (ImGui.sliderInt(LocalizationManager.tr("processor.tracker.manager.buffer.capacity"), bufferCapacity, 0, 250))
                trackingManager.setBuffCapacity(bufferCapacity[0]);
            if (ImGui.button(LocalizationManager.tr("processor.tracker.manager.buffer.clear"))) trackingManager.reset();
            ImGui.newLine();
            if (ImGui.sliderFloat(LocalizationManager.tr("processor.tracker.manager.iouthreshold"), iouThreshold, 0, 1, "%.2f"))
                trackingManager.getAssociationAlgorithm().setIouThreshold(iouThreshold[0]);
            ImGui.newLine();
            if (ImGui.sliderInt2(LocalizationManager.tr("processor.tracker.manager.conformation.NofM"), nOfMConfirmation, 0, 32)) {
                trackingManager.getConfirmationAlgorithm().setN(nOfMConfirmation[0]);
                trackingManager.getConfirmationAlgorithm().setM(nOfMConfirmation[1]);
            }
            ImGui.newLine();
            if (ImGui.sliderInt(LocalizationManager.tr("processor.tracker.manager.maxmissed"), maxMissedDeleting, 0, 60))
                trackingManager.getDeletingConfirmationAlgorithm().setMaxMissed(maxMissedDeleting[0]);

        }
    }

    private void renderDrawingSettings() {
        if (ImGui.collapsingHeader(LocalizationManager.tr("processor.draw.name"), ImGuiTreeNodeFlags.DefaultOpen)) {
            ImGui.checkbox(LocalizationManager.tr("processor.draw.enable"), drawDetections);
            ImGui.beginDisabled(!drawDetections.get());
            ImGui.combo(LocalizationManager.tr("processor.draw.choose") + "##" + "detection", selectedDetectorRendererIndex, rendererNames, rendererNames.length);
            renderers.get(selectedDetectorRendererIndex.get()).renderSettings("detect");
            ImGui.endDisabled();

            imGuiSeparate();

            ImGui.checkbox(LocalizationManager.tr("processor.draw.enable") + "##" + "tracling", drawTracking);
            ImGui.beginDisabled(!drawTracking.get());
            ImGui.combo(LocalizationManager.tr("processor.draw.choose") + "##" + "tracking", selectedTrackerRendererIndex, rendererNames, rendererNames.length);
            renderers.get(selectedTrackerRendererIndex.get()).renderSettings("track");
            ImGui.endDisabled();
        }
    }

    private static void imGuiSeparate() {
        ImGui.newLine();
        ImGui.separator();
        ImGui.newLine();
    }

    @Override
    public MatWrapper execute(MatWrapper matWrapper) {
        Mat mat = matWrapper.mat();
        executeDetectors(mat);
        List<TrackedObject> trackedObjects = null;
        if (useTrackers.get() && selectedTracker.get() >= 0 && selectedTracker.get() < trackers.size()) {
            trackedObjects = trackingManager.update(mat, detectionResult);
        }

        if (drawDetections.get() && detectionResult != null) {
            if (!detectionResult.getRects().isEmpty()) {
                renderers.get(selectedDetectorRendererIndex.get()).render(mat, detectionResult.getRects(), detectionResult.getScores(), true);
            }
        }

        if (drawTracking.get()) {
            if (trackedObjects != null)
                renderers.get(selectedTrackerRendererIndex.get()).render(mat, trackedObjects, true);
        }

//        if(!detectionResult.getRects().isEmpty())
//        metrics.evaluate(matWrapper.frameIndex(), detectionResult.getRects());
        if(trackedObjects != null)
        metrics.evaluate(matWrapper.frameIndex(), trackedObjects.stream().map(TrackedObject::getRect).toList());

        return matWrapper;
    }


    private void executeDetectors(Mat mat) {
        if (useDetectors.get() && selectedDetector.get() >= 0 && selectedDetector.get() < detectors.size()) {
            detectionResult = detectors.get(selectedDetector.get()).detect(mat);
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
