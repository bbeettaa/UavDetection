package knu.app.ui.processings.trackers;

import knu.app.bll.processors.tracker.ObjectTrackerWithKalman;
import knu.app.bll.utils.LocalizationManager;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2f;

import java.util.List;

public class KalmanTrackerUI implements TrackerUI {
    private final ObjectTrackerWithKalman kalman;

    public KalmanTrackerUI(ObjectTrackerWithKalman kalman) {
        this.kalman = kalman;
    }

    @Override
    public String getName() {
        return LocalizationManager.tr("processor.tracker.kalman");
    }

    @Override
    public void renderSettings() {
    }

    @Override
    public List<Point2f> track(Mat mat, List<Point2f> detections) {
        return kalman.track(mat, detections);
    }
}

