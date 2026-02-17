package knu.app.ui.processings.trackers;

import imgui.ImGui;
import imgui.type.ImFloat;
import imgui.type.ImInt;
import imgui.type.ImBoolean;
import imgui.type.ImString;
import knu.app.bll.processors.tracker.multi.DeepSortGrpcTracker;
import knu.app.bll.processors.tracker.single.ObjectTracker;
import knu.app.bll.utils.LocalizationManager;
import knu.app.bll.utils.registry.MultiObjectTrackerFactory.TrackerType;

public class DeepSortTrackerUi implements TrackerUI {

    private final DeepSortGrpcTracker tracker;

    // Основные параметры
    private final ImFloat maxIouDistance = new ImFloat(0.7f);
    private final ImInt maxAge = new ImInt(30);
    private final ImInt nInit = new ImInt(3);
    private final ImFloat nmsMaxOverlap = new ImFloat(1.0f);
    private final ImFloat maxCosineDistance = new ImFloat(0.2f);
    private final ImInt nnBudget = new ImInt(100);
    private final ImBoolean gatingOnlyPosition = new ImBoolean(false);

    // Дополнительно для DeepSort
    private final ImString overrideTrackClass = new ImString("");
    private final ImString embedder = new ImString("mobilenet");
    private final ImBoolean half = new ImBoolean(true);
    private final ImBoolean bgr = new ImBoolean(true);
    private final ImBoolean embedderGpu = new ImBoolean(true);
    private final ImString embedderModelName = new ImString("");
    private final ImString embedderWts = new ImString("");
    private final ImBoolean polygon = new ImBoolean(false);
    private final ImString today = new ImString("");

    public DeepSortTrackerUi(DeepSortGrpcTracker tracker) {
        this.tracker = tracker;
    }

    @Override
    public String getName() {
        return LocalizationManager.tr("processor.tracker.deepsort");
    }

    @Override
    public void renderSettings() {
        boolean changed = false;

        changed |= ImGui.sliderFloat(
                LocalizationManager.tr("processor.trackers.deepsort.max_iou_distance") + "##ds",
                maxIouDistance.getData(), 0.0f, 1.0f);
        changed |= ImGui.sliderInt(
                LocalizationManager.tr("processor.trackers.deepsort.max_age") + "##ds",
                maxAge.getData(), 1, 300);
        changed |= ImGui.sliderInt(
                LocalizationManager.tr("processor.trackers.deepsort.n_init") + "##ds",
                nInit.getData(), 1, 20);
        changed |= ImGui.sliderFloat(
                LocalizationManager.tr("processor.trackers.deepsort.nms_max_overlap") + "##ds",
                nmsMaxOverlap.getData(), 0.0f, 1.0f);
        changed |= ImGui.sliderFloat(
                LocalizationManager.tr("processor.trackers.deepsort.max_cosine_distance") + "##ds",
                maxCosineDistance.getData(), 0.0f, 1.0f);
        changed |= ImGui.sliderInt(
                LocalizationManager.tr("processor.trackers.deepsort.nn_budget") + "##ds",
                nnBudget.getData(), 0, 500);
        changed |= ImGui.checkbox(
                LocalizationManager.tr("processor.trackers.deepsort.gating_only_position") + "##ds",
                gatingOnlyPosition
        );

        // Дополнительно
        changed |= ImGui.inputText(
                LocalizationManager.tr("processor.trackers.deepsort.override_track_class") + "##ds",
                overrideTrackClass
        );
        changed |= ImGui.inputText(
                LocalizationManager.tr("processor.trackers.deepsort.embedder") + "##ds",
                embedder
        );
        changed |= ImGui.checkbox(
                LocalizationManager.tr("processor.trackers.deepsort.half") + "##ds",
                half
        );
        changed |= ImGui.checkbox(
                LocalizationManager.tr("processor.trackers.deepsort.bgr") + "##ds",
                bgr
        );
        changed |= ImGui.checkbox(
                LocalizationManager.tr("processor.trackers.deepsort.embedder_gpu") + "##ds",
                embedderGpu
        );
        changed |= ImGui.inputText(
                LocalizationManager.tr("processor.trackers.deepsort.embedder_model_name") + "##ds",
                embedderModelName
        );
        changed |= ImGui.inputText(
                LocalizationManager.tr("processor.trackers.deepsort.embedder_wts") + "##ds",
                embedderWts
        );
        changed |= ImGui.checkbox(
                LocalizationManager.tr("processor.trackers.deepsort.polygon") + "##ds",
                polygon
        );
        changed |= ImGui.inputText(
                LocalizationManager.tr("processor.trackers.deepsort.today") + "##ds",
                today
        );

        if (changed) {
            tracker.init(
                    maxIouDistance.get(),
                    maxAge.get(),
                    nInit.get(),
                    nmsMaxOverlap.get(),
                    maxCosineDistance.get(),
                    nnBudget.get(),
                    gatingOnlyPosition.get(),
                    overrideTrackClass.get(),
                    embedder.get(),
                    half.get(),
                    bgr.get(),
                    embedderGpu.get(),
                    embedderModelName.get(),
                    embedderWts.get(),
                    polygon.get(),
                    today.get()
            );
        }
    }

    @Override
    public ObjectTracker getTracker() {
        return null;
    }

    @Override
    public String getKey() {
        return TrackerType.DEEPSORT.name();
    }
}
