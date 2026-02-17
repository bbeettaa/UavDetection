//package knu.app.ui.processings.trackers;
//
//import imgui.ImGui;
//import imgui.type.ImFloat;
//import imgui.type.ImInt;
//import knu.app.bll.processors.tracker.multi.MultiObjectTracker;
//import knu.app.bll.processors.tracker.single.ObjectTracker;
//import knu.app.bll.utils.LocalizationManager;
//import knu.app.bll.utils.registry.MultiObjectTrackerFactory.TrackerType;
//
//public class StrongSortTrackerUi implements TrackerUI {
//
//    private final MultiObjectTracker tracker;
//
//    private final ImFloat ssTrackThresh = new ImFloat(0.5f);
//    private final ImFloat ssHighThresh = new ImFloat(0.7f);
//    private final ImFloat ssMatchThresh = new ImFloat(0.3f);
//    private final ImInt ssTrackBuffer = new ImInt(30);
//
//    public StrongSortTrackerUi(MultiObjectTracker tracker) {
//        this.tracker = tracker;
//    }
//
//    @Override
//    public String getName() {
//        return LocalizationManager.tr("processor.tracker.strongsort");
//    }
//
//    @Override
//    public void renderSettings() {
//        boolean a = ImGui.sliderFloat(
//                LocalizationManager.tr("processor.trackers.strongsort.track_thresh") + "##ss",
//                ssTrackThresh.getData(), 0.0f, 1.0f);
//
//        boolean b = ImGui.sliderFloat(
//                LocalizationManager.tr("processor.trackers.strongsort.high_thresh") + "##ss",
//                ssHighThresh.getData(), 0.0f, 1.0f);
//
//        boolean c = ImGui.sliderFloat(
//                LocalizationManager.tr("processor.trackers.strongsort.match_thresh") + "##ss",
//                ssMatchThresh.getData(), 0.0f, 1.0f);
//
//        boolean d = ImGui.sliderInt(
//                LocalizationManager.tr("processor.trackers.strongsort.track_buffer") + "##ss",
//                ssTrackBuffer.getData(), 1, 500);
//
//        if (a || b || c || d) {
//            tracker.init(ssTrackThresh.get(), ssHighThresh.get(), ssMatchThresh.get(), ssTrackBuffer.get());
//        }
//    }
//
//    @Override
//    public ObjectTracker getTracker() {
//        return null;
//    }
//
//    @Override
//    public String getKey() {
//        return TrackerType.STRONGSORT.name();
//    }
//}
