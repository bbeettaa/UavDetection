package knu.app.bll.processors.tracker;

import org.bytedeco.opencv.opencv_core.*;

import java.util.List;

public interface ObjectTracker {

    boolean init(Mat frame, List<Point2f> roiPoints);
    boolean init(Mat frame, Rect roi);
    List<Point2f> update(Mat frameGray, List<Point2f> detectionPoints);
    void close();
}
