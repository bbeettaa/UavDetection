package knu.app.bll.processors.tracker;

import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_video.KalmanFilter;

import java.util.LinkedList;
import java.util.List;

import static org.bytedeco.opencv.global.opencv_core.CV_32F;

public class ObjectTrackerWithKalman implements ObjectTracker {
    private boolean isInitialized;
    private final KalmanFilterWrapper kalmanFilter;

    private int missedDetections = 0;
    private final int maxMissedDetections;

    public ObjectTrackerWithKalman(int maxMissedDetections) {
        this.maxMissedDetections = maxMissedDetections;
        this.kalmanFilter = new KalmanFilterWrapper();
        this.isInitialized = false;
    }

    public ObjectTrackerWithKalman() {
        this(60);
    }

    @Override
    public List<Point2f> track(Mat frame, List<Point2f> detectionPoints) {
        LinkedList<Point2f> trackedPoints = new LinkedList<>();

        Point2f lastPredictedPoint;
        if (detectionPoints.isEmpty()) {
            if (isInitialized) {
                missedDetections++;
                if (missedDetections >= maxMissedDetections) {
                    // Object lost
                    isInitialized = false;
                    return trackedPoints;
                }
                // Continue to predict consist on model of movement
                lastPredictedPoint = kalmanFilter.predict(null);
                trackedPoints.add(lastPredictedPoint);
            }
            return trackedPoints;
        }

        Point2f currentDetection = detectionPoints.getFirst();

        if (!isInitialized) {
            kalmanFilter.reset(currentDetection);
            isInitialized = true;
            missedDetections = 0;
            trackedPoints.add(currentDetection);
        } else {
            lastPredictedPoint = kalmanFilter.predict(currentDetection);
            trackedPoints.add(lastPredictedPoint);
            missedDetections = 0;
        }

        return trackedPoints;
    }

}


class KalmanFilterWrapper {
    private final KalmanFilter kalmanFilter;
    private final Mat state;
    private final Mat measurement;

    public KalmanFilterWrapper() {
        // 4 states: [x, y, vx, vy], 2 dimensions: [x, y]
        kalmanFilter = new KalmanFilter(4, 2, 0, CV_32F);
        state = new Mat(4, 1, CV_32F);
        measurement = new Mat(2, 1, CV_32F);

        setupKalmanFilter();
    }

    private void setupKalmanFilter() {
        Mat transitionMatrix = Mat.eye(4, 4, CV_32F).asMat();
        transitionMatrix.ptr(0, 2).putFloat(1.0f);  // x += vx * dt
        transitionMatrix.ptr(1, 3).putFloat(1.0f);  // y += vy * dt
        kalmanFilter.transitionMatrix(transitionMatrix);

        Mat measurementMatrix = Mat.zeros(2, 4, CV_32F).asMat();
        measurementMatrix.ptr(0, 0).putFloat(1.0f);  // x
        measurementMatrix.ptr(1, 1).putFloat(1.0f);  // y
        kalmanFilter.measurementMatrix(measurementMatrix);

        Mat processNoiseCov = Mat.eye(4, 4, CV_32F).asMat();
        processNoiseCov.convertTo(processNoiseCov, CV_32F, 1e-2, 0);
        kalmanFilter.processNoiseCov(processNoiseCov);

        Mat measurementNoiseCov = Mat.eye(2, 2, CV_32F).asMat();
        measurementNoiseCov.convertTo(measurementNoiseCov, CV_32F, 1e-1, 0);
        kalmanFilter.measurementNoiseCov(measurementNoiseCov);

        kalmanFilter.errorCovPost(Mat.eye(4, 4, CV_32F).asMat());
    }

    public void reset(Point2f initialPosition) {
        kalmanFilter.errorCovPost(Mat.eye(4, 4, CV_32F).asMat());
        state.ptr(0).putFloat(initialPosition.x());
        state.ptr(1).putFloat(initialPosition.y());
        state.ptr(2).putFloat(0.0f);  // vx = 0
        state.ptr(3).putFloat(0.0f);  // vy = 0
        kalmanFilter.statePost(state);
    }

    public Point2f predict(Point2f measurement) {
        if (measurement != null) {
            this.measurement.ptr(0).putFloat(measurement.x());
            this.measurement.ptr(1).putFloat(measurement.y());
            kalmanFilter.correct(this.measurement);
        }

        Mat prediction = kalmanFilter.predict();
        return new Point2f(prediction.ptr(0).getFloat(), prediction.ptr(1).getFloat());
    }
}
