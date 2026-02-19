package knu.app.bll.processors.draw;

import static org.bytedeco.opencv.global.opencv_imgproc.*;
import static org.opencv.imgproc.Imgproc.FONT_HERSHEY_SIMPLEX;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import knu.app.bll.algorithms.feature.ObjectState;
import knu.app.bll.algorithms.motion.ObjectMotionInfo;
import knu.app.bll.algorithms.trajectory.TrajectoryManager;
import knu.app.bll.utils.processors.TrackedObject;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.Scalar;

public class TrajectoryRenderer implements DetectionRenderer {

    private final TrajectoryManager trajectoryManager;

    private final Scalar defaultColor = new Scalar(255, 255, 0, 150);
    private final Scalar directionColor = new Scalar(28, 255, 119, 254);
    private final Scalar anomalyColor = new Scalar(0, 165, 255, 255); // Оранжевий для аномалій (BGR)
    // private final Scalar directionColor = new Scalar(82, 184, 231, 150);
    private int defaultThickness = 2;

    public TrajectoryRenderer(TrajectoryManager trajectoryManager) {
        this.trajectoryManager = trajectoryManager;
    }

    public void renderSpeed(Mat frame) {
        drawSpeed(frame, directionColor, defaultThickness, LINE_8);
    }

    public void renderDirection(Mat frame) {
        drawDirection(frame, directionColor, defaultThickness, LINE_8);
    }

    public void update(Mat frame, List<TrackedObject> trackedObjects) {
        trajectoryManager.updateTrajectory(frame, trackedObjects);
    }

    private void drawSpeed(Mat frame, Scalar color, int thickness, int lineType) {
        for (Map.Entry<Integer, CopyOnWriteArrayList<ObjectState>> entry
                : trajectoryManager.getTrajectories().entrySet()) {
            int id = entry.getKey();
            var trajectory = entry.getValue();
            if (trajectory.isEmpty()) continue;
            ObjectMotionInfo motion = trajectoryManager.getMotionInfo(id);
            if (motion == null || (motion.vx == 0 && motion.vy == 0)) continue;
            // Извлекаем center из ObjectState
            Point lastCenter = trajectory.getLast().center;
            String text = String.format("%.1f px/s", motion.speed);
            putText(frame,
                    text,
                    new Point(lastCenter.x() + 5, lastCenter.y() - 5),
                    FONT_HERSHEY_SIMPLEX,
                    0.6,
                    color,
                    thickness,
                    lineType,
                    false);
        }
    }

    private void drawDirection(Mat frame, Scalar color, int thickness, int lineType) {
        for (Map.Entry<Integer, CopyOnWriteArrayList<ObjectState>> entry
                : trajectoryManager.getTrajectories().entrySet()) {
            int id = entry.getKey();
            var trajectory = entry.getValue();
            if (trajectory.isEmpty()) continue;
            ObjectMotionInfo motion = trajectoryManager.getMotionInfo(id);
            if (motion == null) continue;
            // Извлекаем center из ObjectState
            Point lastCenter = trajectory.getLast().center;
            double vx = motion.vx;
            double vy = motion.vy;
            double norm = Math.sqrt(vx * vx + vy * vy);
            if (norm < 1e-3) continue;
            vx /= norm;
            vy /= norm;
            int arrowLength = 40;
            Point end = new Point(
                    (int) (lastCenter.x() + vx * arrowLength),
                    (int) (lastCenter.y() + vy * arrowLength)
            );
            arrowedLine(
                    frame,
                    lastCenter,
                    end,
                    color,
                    thickness,
                    lineType,
                    0,
                    0.2
            );
        }
    }

    public void renderAnomalies(Mat frame) {
        for (Map.Entry<Integer, CopyOnWriteArrayList<ObjectState>> entry :
                trajectoryManager.getTrajectories().entrySet()) {
            CopyOnWriteArrayList<ObjectState> trajectory = entry.getValue();
            if (trajectory.isEmpty()) continue;
            ObjectState current = trajectory.getLast();
            if (current.isAnomalous) {
                rectangle(frame, current.boundingBox, anomalyColor, 3, LINE_8, 0);
                putText(frame,
                        (current.anomalyDescription != null ? current.anomalyDescription : ""),
                        new Point(current.boundingBox.x(), current.boundingBox.y() + current.boundingBox.height() + 20), // Позиція знизу
                        FONT_HERSHEY_SIMPLEX,
                        0.6,
                        anomalyColor,
                        2, LINE_8, false);
            }
        }
    }

    public void renderTrajectory(Mat frame) {
        for (Map.Entry<Integer, CopyOnWriteArrayList<ObjectState>> entry :
                trajectoryManager.getTrajectories().entrySet()) {
            CopyOnWriteArrayList<ObjectState> trajectory = entry.getValue();
            if (trajectory.size() < 2) continue;
            ObjectState prev = trajectory.getFirst();
            boolean isAnomalous = trajectory.getLast().isAnomalous;
            Scalar color = isAnomalous ? anomalyColor : defaultColor;
            for (ObjectState state : trajectory) {
                if (prev != state) {
                    line(frame, prev.center, state.center, color, defaultThickness, LINE_8, 0);
                }
                prev = state;
            }
        }
    }

    @Override
    public void render(Mat frame, List<TrackedObject> trackedObjects, boolean renderScores) {
    }

    @Override
    public void render(Mat frame, List<TrackedObject> trackedObjects, boolean renderScores,
                       Scalar color, int thickness, int lineType) {
    }

    @Override
    public void render(Mat frame, List<Point2f> result) {
    }

    @Override
    public void render(Mat frame, List<Point2f> result, Scalar color, int thick,
                       int type) {
    }

    @Override
    public void render(Mat frame, List<Rect> rects, List<Double> scores,
                       boolean renderScores) {
    }

    @Override
    public void render(Mat frame, List<Rect> rects, List<Double> scores, boolean renderScores,
                       Scalar color, int thick, int lineType) {
    }

    @Override
    public void render(Mat frame, List<Rect> rects, List<String> names) { /* Не используется */ }

    @Override
    public void render(Mat frame, List<Rect> rects, List<String> names, List<Double> scores,
                       boolean renderScores) {
    }

    @Override
    public void render(Mat frame, List<Rect> rects, List<String> names, List<Double> scores,
                       boolean renderScores, Scalar color, int thick, int type) {
    }

    @Override
    public Scalar getScalar() {
        return defaultColor;
    }

    @Override
    public int getThick() {
        return defaultThickness;
    }

    @Override
    public int getType() {
        return LINE_8;
    }

    public int getMaxLength() {
        return trajectoryManager.getMaxLength();
    }

    public void setDefaultThickness(int defaultThickness) {
        this.defaultThickness = defaultThickness;
    }

    public void setMaxHistoryFrames(int maxHistoryFrames) {
        this.trajectoryManager.setMaxLength(maxHistoryFrames);
    }

    public TrajectoryManager getTrajectoryManager() {
        return trajectoryManager;
    }
}