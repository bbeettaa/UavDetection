package knu.app.detection.tracker;

import org.bytedeco.opencv.opencv_core.*;

import java.util.LinkedList;


import org.bytedeco.opencv.opencv_core.Mat;

public interface ObjectTracker {
    LinkedList<Point2f> track(Mat frameGray, LinkedList<Point2f> roiPoints);
//    void init(Mat frameGray, LinkedList<Point2f> roiPoints);
}
