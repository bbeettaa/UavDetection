package knu.app.detection.tracker;

import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_tracking.TrackerCSRT;
import org.bytedeco.opencv.opencv_tracking.TrackerKCF;
import org.bytedeco.opencv.opencv_video.Tracker;
import org.bytedeco.opencv.opencv_video.TrackerMIL;

import java.util.LinkedList;


public class SimpleTrackers implements ObjectTracker {
    private final Tracker tracker;
    private boolean isInitialized = false;

    private SimpleTrackers(Tracker tracker) {
        this.tracker = tracker;
    }

    public static SimpleTrackers withKcfTracker() {
        return new SimpleTrackers(TrackerKCF.create());
    }

    public static SimpleTrackers withMilTracker() {
        return new SimpleTrackers(TrackerMIL.create());
    }

//    public static SimpleTracker withGoturnTracker() {
//        return new SimpleTracker(TrackerGOTURN.create());
//    }
//
//    public static SimpleTracker withDaSiamTracker() {
//        return new SimpleTracker(TrackerDaSiamRPN.create());
//    }
//
    public static SimpleTrackers withCsrtTracker() {
        return new SimpleTrackers(TrackerCSRT.create());
    }


    @Override
    public LinkedList<Point2f> track(Mat frame, LinkedList<Point2f> roiPoints) {
        if (roiPoints.isEmpty()) {
            return new LinkedList<>();
        }
        if (!isInitialized)
            init(frame, roiPoints);

        Rect trackedObject = new Rect();
        boolean isTracked = tracker.update(frame, trackedObject);

        if (!isTracked) init(frame, roiPoints);

        LinkedList<Point2f> points = new LinkedList<>();

        if (isTracked)
            points.add(new Point2f((float) (trackedObject.x() + trackedObject.width() / 2),
                    (float) (trackedObject.y() + trackedObject.height() / 2)));


        return points;
    }

    private void init(Mat frame, LinkedList<Point2f> roiPoints) {
        Rect roi = getBoundingBoxFromPoints(roiPoints);
        if (roi.width() <= 0 || roi.height() <= 0) {
//            throw new IllegalArgumentException("Invalid ROI: width and height must be > 0");
            return;
        }
        tracker.init(frame, roi);
        this.isInitialized = true;
    }

    private static Rect getBoundingBoxFromPoints(LinkedList<Point2f> points) {
        float minX = Float.MAX_VALUE, minY = Float.MAX_VALUE;
        float maxX = Float.MIN_VALUE, maxY = Float.MIN_VALUE;

        for (Point2f pt : points) {
            float x = pt.x(), y = pt.y();
            if (x < minX) minX = x;
            if (y < minY) minY = y;
            if (x > maxX) maxX = x;
            if (y > maxY) maxY = y;
        }

        return new Rect((int) minX, (int) minY, (int) (maxX - minX), (int) (maxY - minY));
    }


}
