package knu.app.ui.processings.trackers;

import knu.app.bll.processors.tracker.ObjectTracker;
//import knu.app.detection.tracker.SimpleTrackers;
import knu.app.bll.processors.tracker.OpticalFlowTracker;
import knu.app.bll.utils.LocalizationManager;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2f;

import java.util.List;

public class LucasKanadeTrackerUI implements TrackerUI {
    private final ObjectTracker tracker;

    public LucasKanadeTrackerUI(OpticalFlowTracker tracker) {
        this.tracker = tracker;
    }

    @Override
    public String getName() {
        return LocalizationManager.tr("processor.tracker.lucaskanade");
    }

    @Override
    public void renderSettings() {
    }

    @Override
    public List<Point2f> track(Mat mat, List<Point2f> detections) {
        return tracker.track(mat, detections);
    }

}
