package knu.app.bll.processors.tracker;

import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.opencv_tracking.TrackerCSRT;
import java.util.LinkedList;
import java.util.List;

import static org.bytedeco.opencv.global.opencv_core.CV_8U;

public class CSRTTracker implements ObjectTracker {
    private TrackerCSRT tracker;
    private boolean isInitialized = false;
    private Rect lastTrackedRect;
    private int failedFramesCount = 0;
    private static final int MAX_FAILED_FRAMES = 30;
    private static final int MIN_ROI_SIZE = 15;
    private static final double SIZE_CHANGE_THRESHOLD = 0.3;
    private int framesSinceInit = 0;

    public CSRTTracker() {
        createCsrt();
    }

    private void createCsrt(){
        this.tracker = TrackerCSRT.create();
    }

    @Override
    public List<Point2f> track(Mat frame, List<Point2f> roiPoints) {
        LinkedList<Point2f> resultPoints = new LinkedList<>();

        // Validate input frame
        if (frame.empty() || frame.channels() != 3 || frame.depth() != CV_8U) {
            handleInvalidFrame();
            return resultPoints;
        }

        // Handle initialization or reinitialization
        if (!isInitialized || shouldReinitialize(roiPoints)) {
            if (!initializeTracker(frame, roiPoints)) {
                return resultPoints;
            }
        }

        // Perform tracking
        Rect trackedRect = new Rect();
        boolean success = tracker.update(frame, trackedRect);

        // Validate tracking result
        if (!success || !isValidTrackingResult(trackedRect, frame)) {
            handleTrackingFailure();
            return resultPoints;
        }

        // Handle successful tracking
        handleSuccessfulTracking(trackedRect, resultPoints);
        framesSinceInit++;

        return resultPoints;
    }

    private boolean initializeTracker(Mat frame, List<Point2f> roiPoints) {
        if (roiPoints == null || roiPoints.isEmpty()) {
            return false;
        }

        Rect roiRect = getAdjustedBoundingBox(roiPoints, frame);
        if (roiRect.width() < MIN_ROI_SIZE || roiRect.height() < MIN_ROI_SIZE) {
            return false;
        }

        try {
            createCsrt();
            tracker.init(frame, roiRect);
            isInitialized = true;
            lastTrackedRect = roiRect;
            failedFramesCount = 0;
            framesSinceInit = 0;
            return true;
        } catch (Exception e) {
            isInitialized = false;
            return false;
        }
    }

    private boolean shouldReinitialize(List<Point2f> roiPoints) {
        // Reinitialize if we have new ROI points and tracking was failing
        return !roiPoints.isEmpty() &&
                (failedFramesCount > 0 || framesSinceInit > 30);
    }

    private boolean isValidTrackingResult(Rect rect, Mat frame) {
        if (rect == null) return false;

        // Check basic rectangle validity
        if (rect.width() <= 0 || rect.height() <= 0 ||
                rect.x() < 0 || rect.y() < 0 ||
                rect.x() + rect.width() > frame.cols() ||
                rect.y() + rect.height() > frame.rows()) {
            return false;
        }

        // Check for sudden size changes (possible tracking failure)
        if (lastTrackedRect != null) {
            double areaRatio = (rect.width() * rect.height()) /
                    (double)(lastTrackedRect.width() * lastTrackedRect.height());
            return !(areaRatio < SIZE_CHANGE_THRESHOLD) && !(areaRatio > 1 / SIZE_CHANGE_THRESHOLD);
        }

        return true;
    }

    private void handleTrackingFailure() {
        failedFramesCount++;
        if (failedFramesCount >= MAX_FAILED_FRAMES) {
            isInitialized = false;
        }
    }

    private void handleSuccessfulTracking(Rect trackedRect, LinkedList<Point2f> resultPoints) {
        lastTrackedRect = trackedRect;
        failedFramesCount = 0;

        // Calculate center point
        Point2f center = new Point2f(
                (float)(trackedRect.x() + trackedRect.width() / 2),
                (float)(trackedRect.y() + trackedRect.height() / 2)
        );
        resultPoints.add(center);
    }

    private void handleInvalidFrame() {
        failedFramesCount++;
        if (failedFramesCount >= MAX_FAILED_FRAMES) {
            isInitialized = false;
        }
    }

    private Rect getAdjustedBoundingBox(List<Point2f> points, Mat frame) {
        if (points == null || points.isEmpty()) {
            return new Rect(0, 0, 0, 0);
        }

        // Calculate bounding box
        float[] bounds = calculateBounds(points);
        Rect rect = new Rect(
                (int)bounds[0], (int)bounds[1],
                (int)(bounds[2] - bounds[0]), (int)(bounds[3] - bounds[1])
        );

        // Adjust to frame boundaries
        return adjustRectToFrame(rect, frame);
    }

    private float[] calculateBounds(List<Point2f> points) {
        float minX = Float.POSITIVE_INFINITY, minY = Float.POSITIVE_INFINITY;
        float maxX = Float.NEGATIVE_INFINITY, maxY = Float.NEGATIVE_INFINITY;

        for (Point2f pt : points) {
            minX = Math.min(minX, pt.x());
            minY = Math.min(minY, pt.y());
            maxX = Math.max(maxX, pt.x());
            maxY = Math.max(maxY, pt.y());
        }

        return new float[]{minX, minY, maxX, maxY};
    }

    private Rect adjustRectToFrame(Rect rect, Mat frame) {
        int x = Math.max(0, Math.min(rect.x(), frame.cols() - 2));
        int y = Math.max(0, Math.min(rect.y(), frame.rows() - 2));
        int width = Math.max(MIN_ROI_SIZE, Math.min(rect.width(), frame.cols() - x));
        int height = Math.max(MIN_ROI_SIZE, Math.min(rect.height(), frame.rows() - y));
        return new Rect(x, y, width, height);
    }

}