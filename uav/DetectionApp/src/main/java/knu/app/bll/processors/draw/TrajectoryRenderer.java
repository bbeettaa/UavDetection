package knu.app.bll.processors.draw;

import static org.bytedeco.opencv.global.opencv_imgproc.*;
import static org.opencv.imgproc.Imgproc.FONT_HERSHEY_SIMPLEX;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import knu.app.bll.algorithms.feature.ObjectState;
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
    private final Scalar anomalyColor = new Scalar(0, 165, 255, 255);
    private int defaultThickness = 2;

    private Map<Integer, Point2f> smoothedDirections = new HashMap<>();
    private double alpha = 0.2;


    public TrajectoryRenderer(TrajectoryManager trajectoryManager) {
        this.trajectoryManager = trajectoryManager;
    }

    public void renderSpeed(Mat frame, List<TrackedObject> trackedObjects) {
        for (TrackedObject obj : trackedObjects) {
            if (obj.isDeleted() || obj.getTrajectory().isEmpty()) continue;

            ObjectState last = obj.getTrajectory().getLast();
            if (last.speed < 0.1) continue;

            String text = String.format("%.1f px/f", last.speed);
            putText(frame, text, new Point(last.center.x() + 5, last.center.y() - 5),
                    FONT_HERSHEY_SIMPLEX, 0.5, directionColor, defaultThickness, LINE_AA, false);
        }
    }

    public void renderDirection(Mat frame, List<TrackedObject> trackedObjects) {
        for (TrackedObject obj : trackedObjects) {
            if (obj.isDeleted() || obj.getTrajectory().size() < 2) continue;

            ObjectState current = obj.getTrajectory().getLast();
            ObjectState prev = obj.getTrajectory().get(obj.getTrajectory().size() - 2);

            double vx = current.center.x() - prev.center.x();
            double vy = current.center.y() - prev.center.y();
            double norm = Math.sqrt(vx * vx + vy * vy);
            if (norm < 1e-3) continue;

            Point2f prevSmooth = smoothedDirections.getOrDefault(obj.getId(), new Point2f((float) (vx / norm), (float) (vy / norm)));
            float svx = (float) (alpha * (vx / norm) + (1 - alpha) * prevSmooth.x());
            float svy = (float) (alpha * (vy / norm) + (1 - alpha) * prevSmooth.y());
            smoothedDirections.put(obj.getId(), new Point2f(svx, svy));

            Point end = new Point((int) (current.center.x() + svx * 40), (int) (current.center.y() + svy * 40));
            arrowedLine(frame, current.center, end, directionColor, defaultThickness, LINE_AA, 0, 0.2);
        }
    }

    public void update(Mat frame, List<TrackedObject> trackedObjects) {
        trajectoryManager.updateTrajectory(frame, trackedObjects);
    }

    public void renderAnomalies(Mat frame, List<TrackedObject> trackedObjects) {
        for (TrackedObject obj : trackedObjects) {
            if (obj.isDeleted() || obj.getTrajectory().isEmpty()) continue;

            ObjectState current = obj.getTrajectory().getLast();
            if (current.isAnomalous) {
                rectangle(frame, current.boundingBox, anomalyColor, 3, LINE_AA, 0);
                String desc = current.anomalyDescription != null ? current.anomalyDescription : "ANOMALY";
                putText(frame, desc, new Point(current.boundingBox.x(), current.boundingBox.y() + current.boundingBox.height() + 20),
                        FONT_HERSHEY_SIMPLEX, 0.6, anomalyColor, 2, LINE_AA, false);
            }
        }
    }

    public void renderTrajectory(Mat frame, List<TrackedObject> trackedObjects) {
        for (TrackedObject obj : trackedObjects) {
            LinkedList<ObjectState> trajectory = obj.getTrajectory();
            if (obj.isDeleted() || trajectory.size() < 2) continue;

            boolean isAnomalous = trajectory.getLast().isAnomalous;
            Scalar color = isAnomalous ? anomalyColor : defaultColor;

            for (int i = 1; i < trajectory.size(); i++) {
                line(frame, trajectory.get(i - 1).center, trajectory.get(i).center,
                        color, defaultThickness, LINE_AA, 0);
            }
        }
    }

    @Override
    public void render(Mat frame, List<TrackedObject> trackedObjects, boolean renderScores) {
        renderTrajectory(frame, trackedObjects);
        renderAnomalies(frame, trackedObjects);
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