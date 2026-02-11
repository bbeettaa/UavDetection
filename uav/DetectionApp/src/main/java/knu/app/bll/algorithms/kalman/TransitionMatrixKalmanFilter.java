package knu.app.bll.algorithms.kalman;

import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2f;

import static org.bytedeco.opencv.global.opencv_core.CV_32F;
import static org.bytedeco.opencv.global.opencv_core.memopTypeToStr;

public class TransitionMatrixKalmanFilter implements KalmanFilter {
    private final org.bytedeco.opencv.opencv_video.KalmanFilter kalmanFilter;
    private final Mat state;
    private final Mat measurement;

    private final float GATING_THRESHOLD ;

    public TransitionMatrixKalmanFilter(){
        this(16.0f);
    }

    public TransitionMatrixKalmanFilter(float gatingThreshold) {
        this.GATING_THRESHOLD = gatingThreshold;
        // 4 states: [x, y, vx, vy], 2 dimensions: [x, y]
        kalmanFilter = new org.bytedeco.opencv.opencv_video.KalmanFilter(4, 2, 0, CV_32F);
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

    public Point2f update(Point2f measurement) {
        if(state.isNull())
            throw new IllegalStateException();

        Mat prior = kalmanFilter.predict();
        float priorX = prior.ptr(0).getFloat();
        float priorY = prior.ptr(1).getFloat();
        boolean didCorrect = false;

        if (measurement != null) {
            if(Float.isNaN(measurement.x()) || Float.isNaN(measurement.y())) throw new IllegalArgumentException();

            float innovX = measurement.x() - priorX;
            float innovY = measurement.y() - priorY;
            float innovNorm = (float) Math.hypot(innovX, innovY);

            if (innovNorm < GATING_THRESHOLD) {
                this.measurement.ptr(0).putFloat(measurement.x());
                this.measurement.ptr(1).putFloat(measurement.y());
                kalmanFilter.correct(this.measurement);
                didCorrect = true;
            }
        }

        if (didCorrect) {
            Mat statePost = kalmanFilter.statePost();
            return new Point2f(statePost.ptr(0).getFloat(), statePost.ptr(1).getFloat());
        } else {
            return new Point2f(priorX, priorY);
        }
    }

    public void releaseResources() {
        kalmanFilter.close();
        state.close();
        measurement.close();
    }

    public Mat getState() {
        return state;
    }

    public void setDt(float dt) {
        if(dt == 0) throw new IllegalArgumentException();

        Mat transitionMatrix = Mat.eye(4, 4, CV_32F).asMat();
        transitionMatrix.ptr(0, 2).putFloat(dt);  // x += vx * dt
        transitionMatrix.ptr(1, 3).putFloat(dt);  // y += vy * dt
        kalmanFilter.transitionMatrix(transitionMatrix);
    }
}
