package knu.app.detection.tracker;


import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.Rect2d;

public class TrackingResult {
    private final Rect roi;

    public TrackingResult(Rect roi) {
        this.roi = roi;
    }

    public Rect getRoi() {
        return roi;
    }
}


