package knu.app.ui.processings.trackers;


import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2f;

import java.util.List;

public interface TrackerUI {
    String getName();
    void renderSettings();
    List<Point2f> track(Mat mat, List<Point2f> detections);
}
