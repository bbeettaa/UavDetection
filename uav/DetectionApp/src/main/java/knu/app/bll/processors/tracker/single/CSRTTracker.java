package knu.app.bll.processors.tracker.single;

import knu.app.bll.utils.Utils;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_tracking.TrackerCSRT;

import java.util.ArrayList;
import java.util.List;

public class CSRTTracker implements ObjectTracker {
    private TrackerCSRT tracker;
    private boolean initialized = false;

    public CSRTTracker() {
        tracker = TrackerCSRT.create();
    }

    @Override
    public boolean init(Mat frame, List<Point2f> roiPoints) {
        if (frame.empty() || roiPoints == null || roiPoints.isEmpty()) {
            return false;
        }
        Rect bbox = Utils.pointsToRect(roiPoints);

        if (bbox.area() <= 1 || bbox.size().empty() || frame.size().area() == 0 || bbox.x() <= 0 || bbox.y() <= 0)
            return false;

        tracker = TrackerCSRT.create();
        tracker.init(frame, bbox);
        initialized = true;
        return true;
    }

    @Override
    public boolean init(Mat frame, Rect roi) {
        if (frame.empty() || roi == null || roi.area() == 0) {
            return false;
        }
        tracker = TrackerCSRT.create();
        tracker.init(frame, roi);
        initialized = true;
        return true;
    }

    @Override
    public List<Point2f> update(Mat frame, List<Point2f> detectionPoints) {
        List<Point2f> result = new ArrayList<>();
        if (!initialized || frame.empty()) {
            return result;
        }
        Rect bbox2d = new Rect();
        if (detectionPoints != null)
            bbox2d = Utils.pointsToRect(detectionPoints);
        boolean ok = tracker.update(frame, bbox2d);
        if (ok) {
            float cx = (float) (bbox2d.x() + bbox2d.width() / 2.0);
            float cy = (float) (bbox2d.y() + bbox2d.height() / 2.0);
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
