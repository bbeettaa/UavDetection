package knu.app.bll.processors.tracker;

import knu.app.bll.KalmanFilter;
import knu.app.bll.utils.Utils;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Rect;

import java.util.ArrayList;
import java.util.List;

public class KalmanObjectTracker implements ObjectTracker {
    private boolean isInitialized;
    private final KalmanFilter kalmanFilter;

    public KalmanObjectTracker(float gatingTh) {
        this.kalmanFilter = new KalmanFilter(gatingTh);
        this.isInitialized = false;
    }

    public KalmanObjectTracker() {
        this(32);
    }

    @Override
    public boolean init(Mat frame, List<Point2f> initialPoints) {
        if (initialPoints == null || initialPoints.isEmpty()) {
            return false;
        }
        Point2f center = Utils.rectCenter(Utils.pointsToRect(initialPoints));
        kalmanFilter.reset(center);
        isInitialized = true;
        return true;
    }

    @Override
    public boolean init(Mat frame, Rect roi) {
        if (roi == null || roi.area() == 0) {
            return false;
        }
        Point2f center = Utils.rectCenter(roi);
        kalmanFilter.reset(center);
        isInitialized = true;
        return true;
    }

    @Override
    public List<Point2f> update(Mat frame, List<Point2f> detectionPoints) {
        List<Point2f> result = new ArrayList<>();
        if (!isInitialized) {
            return result;
        }
        Point2f meas = null;
        if (detectionPoints != null && !detectionPoints.isEmpty())
            meas = Utils.rectCenter(Utils.pointsToRect(detectionPoints));
        Point2f state = kalmanFilter.update(meas);
        result.add(state);
        return result;
    }

    @Override
    public void close() {
        kalmanFilter.releaseResources();
        isInitialized = false;
    }



}

