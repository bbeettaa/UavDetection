package knu.app.bll.algorithms.trajectory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import knu.app.bll.algorithms.feature.anomaly.StatisticalThresholdAnomaly;
import knu.app.bll.algorithms.feature.anomaly.*;
import knu.app.bll.algorithms.feature.ObjectState;
import knu.app.bll.algorithms.motion.ComputeMotion;
import knu.app.bll.algorithms.motion.ObjectMotionInfo;
import knu.app.bll.utils.processors.TrackedObject;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Rect;

public class TrajectoryManager {

    private int maxLength;
    private int selectedTrack = -1;

    private final List<AnomalyClassifier> detectors = List.of(
            new StatisticalThresholdAnomaly(),
            new MovingAverageAnomaly(),
            new DirectionCurvatureAnomaly(),
            new ShapeDeformationAnomaly(),
            new TextureChangeAnomaly(),
            new KNNAnomalyDetector()
    );
    private int activeDetectorIndex = 0;

    public TrajectoryManager(int maxLength) {
        this.maxLength = maxLength;
    }

    public void updateTrajectory(Mat frame, List<TrackedObject> trackedObjects) {
        if (trackedObjects == null) return;
        for (TrackedObject obj : trackedObjects) {
            if (obj.isDeleted()) continue;
            ObjectState newState = extractFeatures(frame, obj);
            obj.addState(newState, maxLength);
            if (activeDetectorIndex >= 0 && activeDetectorIndex < detectors.size()) {
                newState.isAnomalous = detectors.get(activeDetectorIndex).detectAnomaly(obj.getTrajectory());
            }
        }
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public void setSelectedTrack(int selectedTrack) {
        this.selectedTrack = selectedTrack;
    }

    public int getSelectedTrack() {
        return selectedTrack;
    }

    private ObjectState extractFeatures(Mat frame, TrackedObject obj) {
        Rect rect = obj.getRect();
        var history = obj.getTrajectory();

        Point center = new Point(rect.x() + rect.width() / 2, rect.y() + rect.height() / 2);
        ObjectState state = new ObjectState(center, rect);

        if (!history.isEmpty()) {
            ObjectState prev = history.getLast();
            double dx = center.x() - prev.center.x();
            double dy = center.y() - prev.center.y();
            state.speed = Math.sqrt(dx * dx + dy * dy);
            state.angleDirection = Math.atan2(dy, dx);
        }

        try {
            int x = Math.max(0, rect.x());
            int y = Math.max(0, rect.y());
            int w = Math.min(rect.width(), frame.cols() - x);
            int h = Math.min(rect.height(), frame.rows() - y);

            if (w > 0 && h > 0) {
                try (Mat roi = new Mat(frame, new Rect(x, y, w, h));
                     Mat mean = new Mat();
                     Mat stdDev = new Mat()) {
                    org.bytedeco.opencv.global.opencv_core.meanStdDev(roi, mean, stdDev);
                    state.brightnessMean = mean.ptr().getDouble();
                    state.textureStdDev = stdDev.ptr().getDouble();
                }
            }
        } catch (Exception ignored) {}

        return state;
    }

    public void setActiveDetectorIndex(int index) { this.activeDetectorIndex = index; }
    public int getActiveDetectorIndex() { return activeDetectorIndex; }
    public String[] getDetectorNames() {
        return detectors.stream().map(AnomalyClassifier::getMethodName).toArray(String[]::new);
    }
}
