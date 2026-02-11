package knu.app.bll.processors.tracker.single;

import knu.app.bll.utils.Utils;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_video.Tracker;
import org.bytedeco.opencv.opencv_video.TrackerMIL;

import java.util.ArrayList;
import java.util.List;

public class MilTracker implements ObjectTracker {
    private Tracker tracker;
    private boolean initialized = false;

    public MilTracker() {
        tracker = TrackerMIL.create();
    }

    @Override
    public boolean init(Mat frame, List<Point2f> roiPoints) {
        if (frame.empty() || roiPoints == null || roiPoints.isEmpty()) {
            return false;
        }
        tracker = TrackerMIL.create();
        tracker.init(frame, Utils.pointsToRect(roiPoints));
        initialized = true;
        return initialized;
    }

    @Override
    public boolean init(Mat frame, Rect roi) {
        if (frame.empty() || roi == null || roi.area() == 0) {
            return false;
        }
        tracker = TrackerMIL.create();
        tracker.init(frame, roi);
        initialized = true;
        return initialized;
    }

    @Override
    public List<Point2f> update(Mat frame, List<Point2f> detectionPoints) {
        List<Point2f> result = new ArrayList<>();
        if (!initialized || frame.empty()) {
            return result;
        }
        Rect bbox = new Rect();
        if (detectionPoints != null)
            bbox = Utils.pointsToRect(detectionPoints);
        boolean ok = tracker.update(frame, bbox);
        if (ok) {
            float cx = (float) (bbox.x() + bbox.width() / 2.0);
            float cy = (float) (bbox.y() + bbox.height() / 2.0);
            result.add(new Point2f(cx, cy));
        }
        return result;
    }

    @Override
    public void close() {
        if (tracker != null) {
            tracker.close();
            tracker = null;
        }
        initialized = false;
    }

}
