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

//    private final Map<Integer, Integer> deadFramesCount = new ConcurrentHashMap<>();
    private final Map<Integer, CopyOnWriteArrayList<ObjectState>> trajectories = new ConcurrentHashMap<>();

    private int maxLength;
    private int selectedTrack = -1;
    private final AnomalyClassifier anomalyClassifier;

    private final List<AnomalyClassifier> detectors = List.of(
            new StatisticalThresholdAnomaly(),
            new MovingAverageAnomaly(),
            new DirectionCurvatureAnomaly(),
            new ShapeDeformationAnomaly(),
            new TextureChangeAnomaly()
    );
    private int activeDetectorIndex = 0;
    private float sensitivity = 1.0f;


    public TrajectoryManager(int maxLength, AnomalyClassifier anomalyClassifier) {
        this.maxLength = maxLength;
        this.anomalyClassifier = anomalyClassifier;
    }

    public void updateTrajectory(Mat frame, List<TrackedObject> trackedObjects) {
        // 1. Маппинг активных объектов
        Map<Integer, TrackedObject> activeNow = new HashMap<>();
        for (TrackedObject obj : trackedObjects) {
            if (!obj.isDeleted()) activeNow.put(obj.getId(), obj);
        }

        // 2. Обновление существующих траекторий
        var iterator = trajectories.entrySet().iterator();
        while (iterator.hasNext()) {
            var entry = iterator.next();
            int id = entry.getKey();
            TrackedObject obj = activeNow.get(id);

            if (obj != null) {
                CopyOnWriteArrayList<ObjectState> history = entry.getValue();
                ObjectState newState = extractFeatures(frame, obj, history);

                // Сначала добавляем, потом детектируем (чтобы методы видели актуальный getLast())
                history.add(newState);
                trimToMaxLength(history);

                newState.isAnomalous = detectors.get(activeDetectorIndex).detectAnomaly(history);

                activeNow.remove(id);
            } else {
                iterator.remove(); // Мгновенное удаление "призраков"
            }
        }

        // 3. Инициализация новых траекторий
        for (TrackedObject newObj : activeNow.values()) {
            CopyOnWriteArrayList<ObjectState> history = new CopyOnWriteArrayList<>();
            ObjectState firstState = extractFeatures(frame, newObj, history);
            history.add(firstState);
            trajectories.put(newObj.getId(), history);
        }
    }

    public Map<Integer, CopyOnWriteArrayList<ObjectState>> getTrajectories() {
        return trajectories;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;

        for (var tr : trajectories.values()) {
            trimToMaxLength(tr);
        }
    }

    public void setSelectedTrack(int selectedTrack) {
        this.selectedTrack = selectedTrack;
    }

    public int getSelectedTrack() {
        return selectedTrack;
    }

    public boolean isAlive(int id) {
        var traj = trajectories.get(id);
        return traj != null && !traj.isEmpty();
    }

    public ObjectMotionInfo getMotionInfo(int id) {
        CopyOnWriteArrayList<ObjectState> traj = trajectories.get(id);
        return ComputeMotion.computeMotion(id, traj);
    }

    private ObjectState extractFeatures(Mat frame, TrackedObject obj, CopyOnWriteArrayList<ObjectState> history) {
        Rect rect = obj.getRect();
        Point center = new Point(rect.x() + rect.width() / 2, rect.y() + rect.height() / 2);
        ObjectState state = new ObjectState(center, rect);

        if (!history.isEmpty()) {
            ObjectState prev = history.getLast();
            double dx = center.x() - prev.center.x();
            double dy = center.y() - prev.center.y();
            state.speed = Math.sqrt(dx * dx + dy * dy);
            state.angleDirection = Math.atan2(dy, dx);
        }

        // ПУНКТ 1 Лабы: Текстурные характеристики
        try {
            // Исправленный расчет безопасного Rect
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

    private void trimToMaxLength(CopyOnWriteArrayList<ObjectState> trajectory) {
        while (trajectory.size() > maxLength) {
            trajectory.removeFirst();
        }
    }

    public void setActiveDetectorIndex(int index) { this.activeDetectorIndex = index; }
    public int getActiveDetectorIndex() { return activeDetectorIndex; }
    public void setSensitivity(float s) { this.sensitivity = s; }
    public String[] getDetectorNames() {
        return detectors.stream().map(AnomalyClassifier::getMethodName).toArray(String[]::new);
    }
}
