package knu.app.ui.processings.trackers;

import imgui.ImGui;
import imgui.type.ImFloat;
import imgui.type.ImInt;
import knu.app.bll.processors.tracker.multi.MultiObjectTracker;
import knu.app.bll.processors.tracker.multi.SortGrpcTracker;
import knu.app.bll.processors.tracker.single.ObjectTracker;
import knu.app.bll.utils.LocalizationManager;
import knu.app.bll.utils.registry.MultiObjectTrackerFactory.TrackerType;

public class SortTrackerUi implements TrackerUI {

    private final SortGrpcTracker tracker;

    private final ImInt lostTrackBuffer = new ImInt(30);
    private final ImFloat frameRate = new ImFloat(30.0f);
    private final ImFloat trackActivationThreshold = new ImFloat(0.25f);
    private final ImInt minimumConsecutiveFrames = new ImInt(3);
    private final ImFloat minimumIouThreshold = new ImFloat(0.3f);

    public SortTrackerUi(SortGrpcTracker tracker) {
        this.tracker = tracker;
    }

    @Override
    public String getName() {
        return LocalizationManager.tr("processor.tracker.sort");
    }

    @Override
    public void renderSettings() {

        boolean a = ImGui.sliderInt(
                LocalizationManager.tr("processor.trackers.sort.lost_track_buffer") + "##sort",
                lostTrackBuffer.getData(), 1, 500);

//        boolean b = ImGui.sliderFloat(
//                LocalizationManager.tr("processor.trackers.sort.frame_rate") + "##sort",
//                frameRate.getData(), 1.0f, 120.0f);

        boolean c = ImGui.sliderFloat(
                LocalizationManager.tr("processor.trackers.sort.track_activation_threshold") + "##sort",
                trackActivationThreshold.getData(), 0.0f, 1.0f);

        boolean d = ImGui.sliderInt(
                LocalizationManager.tr("processor.trackers.sort.minimum_consecutive_frames") + "##sort",
                minimumConsecutiveFrames.getData(), 1, 30);

        boolean e = ImGui.sliderFloat(
                LocalizationManager.tr("processor.trackers.sort.minimum_iou_threshold") + "##sort",
                minimumIouThreshold.getData(), 0.0f, 1.0f);

        if (a || c || d || e) {
            tracker.init(
                    lostTrackBuffer.get(),
//                    frameRate.get(),
                    trackActivationThreshold.get(),
                    minimumConsecutiveFrames.get(),
                    minimumIouThreshold.get()
            );
        }
    }

    @Override
    public ObjectTracker getTracker() {
        return null;
    }

    @Override
    public String getKey() {
        return TrackerType.SORT.name();
    }
}
