package knu.app.ui.processings.trackers;

import knu.app.bll.processors.tracker.MilTracker;
import knu.app.bll.utils.LocalizationManager;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2f;

import java.util.List;

public class MILTrackerUI implements TrackerUI {
    private final MilTracker tracker;

    public MILTrackerUI(MilTracker tracker){
        this.tracker = tracker;
    }

    @Override
    public String getName() {
        return LocalizationManager.tr("processor.tracker.mil");
    }

    @Override
    public void renderSettings() {
    }

    @Override
    public List<Point2f> track(Mat mat, List<Point2f> detections) {
        return tracker.track(mat, detections);
    }
}
