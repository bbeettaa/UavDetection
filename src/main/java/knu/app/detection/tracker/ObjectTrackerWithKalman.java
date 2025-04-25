package knu.app.detection.tracker;

import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2f;

import java.util.LinkedList;

import org.bytedeco.opencv.opencv_core.*;


import org.bytedeco.opencv.opencv_tracking.TrackerKCF;
import org.bytedeco.opencv.opencv_video.*;

import static org.bytedeco.opencv.global.opencv_core.CV_32F;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.opencv_core.Mat.eye;
import static org.opencv.imgproc.Imgproc.COLOR_BGR2GRAY;

public class ObjectTrackerWithKalman implements ObjectTracker {

    private Tracker tracker;
    private boolean isInitialized;
    private KalmanFilterWrapper kalmanFilter; // Объект фильтра Калмана

    public ObjectTrackerWithKalman() {
        this.kalmanFilter = new KalmanFilterWrapper();
    }


    @Override
    public LinkedList<Point2f> track(Mat frame, LinkedList<Point2f> roiPoints) {
        if (frame == null || frame.empty() || frame.cols() <= 0 && frame.rows() <= 0)
            return new LinkedList<>();
        if (roiPoints.isEmpty())
            return new LinkedList<>();
        if (!isInitialized) {
            init(frame, roiPoints);
            return new LinkedList<>();
        }

        Rect trackedObject = new Rect();
        Mat grayFrame = new Mat();
        cvtColor(frame, grayFrame, COLOR_BGR2GRAY);

        boolean isTracked = tracker.update(frame, trackedObject);
        if (!isTracked)
            init(frame, roiPoints);

        LinkedList<Point2f> points = new LinkedList<>();

        Point2f point = new Point2f((float) (trackedObject.x() + trackedObject.width() / 2),
                (float) (trackedObject.y() + trackedObject.height() / 2));

        Point2f filteredPoint = kalmanFilter.predict(point);
        points.add(filteredPoint);

        return points;

    }

    private void init(Mat frame, LinkedList<Point2f> roiPoints) {
        Rect roi = getBoundingBoxFromPoints(roiPoints);
        this.tracker = TrackerKCF.create();
        this.isInitialized = false;
        this.tracker.init(frame, roi);
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


class KalmanFilterWrapper {
    private final KalmanFilter kalmanFilter;
    private final Mat state;
    private final Mat measurement;


    public KalmanFilterWrapper() {
        kalmanFilter = new KalmanFilter(4, 2, 0, CV_32F);

        state = new Mat(4, 1, CV_32F);
        measurement = new Mat(2, 1, CV_32F);

        // Transition matrix (A)
        Mat transitionMatrix = Mat.eye(4, 4, CV_32F).asMat();
        transitionMatrix.ptr(0, 2).putFloat(1.0f); // [0][2] = 1
        transitionMatrix.ptr(1, 3).putFloat(1.0f); // [1][3] = 1
        kalmanFilter.transitionMatrix(transitionMatrix);

        // Measurement matrix (H)
        Mat measurementMatrix = Mat.zeros(2, 4, CV_32F).asMat();
        measurementMatrix.ptr(0, 0).putFloat(1.0f); // [0][0] = 1
        measurementMatrix.ptr(1, 1).putFloat(1.0f); // [1][1] = 1
        kalmanFilter.measurementMatrix(measurementMatrix);

        // Process noise covariance (Q)
        Mat processNoiseCov = Mat.eye(4, 4, CV_32F).asMat();
        if (processNoiseCov.rows() == 4 && processNoiseCov.cols() == 4) {
            processNoiseCov.convertTo(processNoiseCov, CV_32F, 1e-2, 0);
            kalmanFilter.processNoiseCov(processNoiseCov);
        }

        // Measurement noise covariance (R)
        Mat measurementNoiseCov = Mat.eye(2, 2, CV_32F).asMat();
        if (measurementNoiseCov.rows() == 2 && measurementNoiseCov.cols() == 2) {
            measurementNoiseCov.convertTo(measurementNoiseCov, CV_32F, 1e-1, 0);
            kalmanFilter.measurementNoiseCov(measurementNoiseCov);
        }

        // Posteriori error estimate covariance matrix (P)
        kalmanFilter.errorCovPost(Mat.eye(4, 4, CV_32F).asMat());

        // Initial state
        kalmanFilter.statePost(state);
    }

    public Point2f predict(Point2f measurementPoint) {
        measurement.ptr(0).putFloat(measurementPoint.x());
        measurement.ptr(1).putFloat(measurementPoint.y());

        kalmanFilter.correct(measurement);
        Mat prediction = kalmanFilter.predict();
        return new Point2f(prediction.ptr(0).getFloat(), prediction.ptr(1).getFloat());
    }
}
