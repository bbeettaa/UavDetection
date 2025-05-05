package knu.app.bll.processors.tracker;

import org.bytedeco.opencv.opencv_core.*;

import java.util.List;

public interface ObjectTracker {
    List<Point2f> track(Mat frameGray, List<Point2f> roiPoints);
}
