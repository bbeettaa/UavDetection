package knu.app.bll.processors.tracker;

import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.opencv_video.Tracker;
import org.bytedeco.opencv.opencv_video.TrackerMIL;

import java.util.LinkedList;
import java.util.List;

import static org.bytedeco.opencv.global.opencv_core.CV_8U;

public class MilTracker implements ObjectTracker {
    private final Tracker tracker;
    private boolean isInitialized = false;
    private Rect lastTrackedRect;
    private int failedFramesCount = 0;
    private int successfulFramesCount = 0;

    // Конфигурация
    private static final int MAX_FAILED_FRAMES = 15;
    private static final int MIN_SUCCESS_FRAMES = 5;
    private static final int MIN_ROI_SIZE = 20;
    private static final double EDGE_THRESHOLD = 0.1; // 10% от размера кадра
    private static final double SIZE_CHANGE_THRESHOLD = 0.3;

    // Состояние
    private enum TrackerState {
        INITIALIZING,
        TRACKING,
        LOST,
        EDGE_OCCLUSION
    }
    private TrackerState state = TrackerState.INITIALIZING;

    public MilTracker() {
        this.tracker = TrackerMIL.create();
    }

    @Override
    public synchronized List<Point2f> track(Mat frame, List<Point2f> roiPoints) {
        LinkedList<Point2f> resultPoints = new LinkedList<>();

        // Проверка кадра
        if (invalidFrame(frame)) {
            handleInvalidFrame();
            return resultPoints;
        }

        // Обработка состояний
        switch (state) {
            case INITIALIZING:
                if (!roiPoints.isEmpty()) {
                    initialize(frame, roiPoints);
                }
                break;

            case TRACKING:
                resultPoints = processTracking(frame);
                break;

            case LOST:
                if (!roiPoints.isEmpty() && canReinitialize(roiPoints)) {
                    initialize(frame, roiPoints);
                }
                break;

            case EDGE_OCCLUSION:
                resultPoints = handleEdgeOcclusion(frame);
                break;
        }

        return resultPoints;
    }

    private LinkedList<Point2f> processTracking(Mat frame) {
        LinkedList<Point2f> points = new LinkedList<>();
        Rect trackedRect = new Rect();

        try {
            boolean success = tracker.update(frame, trackedRect);

            if (!success || !isValidRect(trackedRect)) {
                handleTrackingFailure();
                return points;
            }

            // Проверка на выход за границы
            if (isNearEdge(trackedRect, frame)) {
                state = TrackerState.EDGE_OCCLUSION;
                System.out.println("Object near edge, entering EDGE_OCCLUSION state");
                points.add(calculateCenter(trackedRect));
                return points;
            }

            // Проверка резкого изменения размера
            if (lastTrackedRect != null &&
                    sizeChangeTooLarge(trackedRect, lastTrackedRect)) {
                handleTrackingFailure();
                return points;
            }

            handleTrackingSuccess(trackedRect);
            points.add(calculateCenter(trackedRect));

        } catch (Exception e) {
            handleTrackingError(e);
        }

        return points;
    }

    private LinkedList<Point2f> handleEdgeOcclusion(Mat frame) {
        LinkedList<Point2f> points = new LinkedList<>();
        Rect trackedRect = new Rect();

        try {
            boolean success = tracker.update(frame, trackedRect);

            if (!success) {
                state = TrackerState.LOST;
                System.out.println("Object lost after edge occlusion");
                failedFramesCount++;
                return points;
            }

            // Если объект вернулся в кадр
            if (!isNearEdge(trackedRect, frame)) {
                state = TrackerState.TRACKING;
                System.out.println("Object returned from edge occlusion");
                handleTrackingSuccess(trackedRect);
                points.add(calculateCenter(trackedRect));
            } else {
                // Продолжаем трекинг у края
                points.add(calculateCenter(trackedRect));
            }

        } catch (Exception e) {
            handleTrackingError(e);
        }

        return points;
    }

    private boolean invalidFrame(Mat frame) {
        return frame.empty() || frame.channels() != 3 || frame.depth() != CV_8U;
    }

    private boolean isNearEdge(Rect rect, Mat frame) {
        double edgeThresholdX = frame.cols() * EDGE_THRESHOLD;
        double edgeThresholdY = frame.rows() * EDGE_THRESHOLD;

        return rect.x() < edgeThresholdX ||
                rect.y() < edgeThresholdY ||
                rect.x() + rect.width() > frame.cols() - edgeThresholdX ||
                rect.y() + rect.height() > frame.rows() - edgeThresholdY;
    }

    private boolean sizeChangeTooLarge(Rect newRect, Rect oldRect) {
        double widthChange = Math.abs(newRect.width() - oldRect.width()) / (double)oldRect.width();
        double heightChange = Math.abs(newRect.height() - oldRect.height()) / (double)oldRect.height();

        return widthChange > SIZE_CHANGE_THRESHOLD || heightChange > SIZE_CHANGE_THRESHOLD;
    }

    private void initialize(Mat frame, List<Point2f> roiPoints) {
        Rect roi = getBoundingBoxFromPoints(roiPoints);
        roi = adjustRectToFrame(roi, frame);

        if (roi.width() < MIN_ROI_SIZE || roi.height() < MIN_ROI_SIZE) {
            System.err.println("ROI too small for initialization");
            return;
        }

        try {
            tracker.init(frame, roi);
            lastTrackedRect = roi;
            successfulFramesCount = 0;
            failedFramesCount = 0;
            state = TrackerState.TRACKING;
            isInitialized = true;
            System.out.println("Tracker initialized with ROI: " + roi);
        } catch (Exception e) {
            System.err.println("Initialization failed: " + e.getMessage());
            state = TrackerState.INITIALIZING;
        }
    }

    private boolean canReinitialize(List<Point2f> roiPoints) {
        Rect newRoi = getBoundingBoxFromPoints(roiPoints);
        return newRoi.width() >= MIN_ROI_SIZE && newRoi.height() >= MIN_ROI_SIZE;
    }

    private void handleTrackingSuccess(Rect trackedRect) {
        lastTrackedRect = trackedRect;
        successfulFramesCount++;
        failedFramesCount = 0;

        if (successfulFramesCount >= MIN_SUCCESS_FRAMES && state != TrackerState.TRACKING) {
            state = TrackerState.TRACKING;
        }
    }

    private void handleTrackingFailure() {
        failedFramesCount++;
        successfulFramesCount = 0;

        if (failedFramesCount >= MAX_FAILED_FRAMES) {
            state = TrackerState.LOST;
            isInitialized = false;
            System.out.println("Tracker switched to LOST state");
        }
    }

    private void handleInvalidFrame() {
        failedFramesCount++;
        if (failedFramesCount >= MAX_FAILED_FRAMES) {
            state = TrackerState.LOST;
            isInitialized = false;
        }
    }

    private void handleTrackingError(Exception e) {
        System.err.println("Tracking error: " + e.getMessage());
        failedFramesCount++;
        if (failedFramesCount >= MAX_FAILED_FRAMES) {
            state = TrackerState.LOST;
            isInitialized = false;
        }
    }

    private Point2f calculateCenter(Rect rect) {
        return new Point2f(
                rect.x() + rect.width() / 2.0f,
                rect.y() + rect.height() / 2.0f
        );
    }

    private Rect adjustRectToFrame(Rect rect, Mat frame) {
        int x = Math.max(0, Math.min(rect.x(), frame.cols() - 2));
        int y = Math.max(0, Math.min(rect.y(), frame.rows() - 2));
        int width = Math.max(MIN_ROI_SIZE, Math.min(rect.width(), frame.cols() - x));
        int height = Math.max(MIN_ROI_SIZE, Math.min(rect.height(), frame.rows() - y));
        return new Rect(x, y, width, height);
    }

    private boolean isValidRect(Rect rect) {
        return rect != null && rect.width() > 0 && rect.height() > 0;
    }

    private Rect getBoundingBoxFromPoints(List<Point2f> points) {
        if (points == null || points.isEmpty()) {
            return new Rect(0, 0, 0, 0);
        }

        float minX = Float.POSITIVE_INFINITY, minY = Float.POSITIVE_INFINITY;
        float maxX = Float.NEGATIVE_INFINITY, maxY = Float.NEGATIVE_INFINITY;

        for (Point2f pt : points) {
            minX = Math.min(minX, pt.x());
            minY = Math.min(minY, pt.y());
            maxX = Math.max(maxX, pt.x());
            maxY = Math.max(maxY, pt.y());
        }

        return new Rect((int) minX, (int) minY,
                (int) (maxX - minX), (int) (maxY - minY));
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public boolean isTracking() {
        return state == TrackerState.TRACKING;
    }

    public boolean isLost() {
        return state == TrackerState.LOST;
    }
}