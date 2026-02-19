package knu.app.bll.processors.draw;

import static org.bytedeco.opencv.global.opencv_imgproc.*;
import static org.opencv.imgproc.Imgproc.FONT_HERSHEY_SIMPLEX;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

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
//    private final Scalar directionColor = new Scalar(82, 184, 231, 150);
    private int defaultThickness = 2;

    public TrajectoryRenderer(TrajectoryManager trajectoryManager) {
        this.trajectoryManager = trajectoryManager;
    }


    public void renderTrajectory(Mat frame) {
        drawTrajectory(frame, defaultColor, defaultThickness, LINE_8);
    }

    public void renderSpeed(Mat frame) {
        drawSpeed(frame, directionColor, defaultThickness, LINE_8);
    }

    public void renderDirection(Mat frame) {
        drawDirection(frame, directionColor, defaultThickness, LINE_8);
    }


    public void update(Mat frame, List<TrackedObject> trackedObjects) {
        Map<Integer, Point> centers = new HashMap<>(trackedObjects.size());
        for (TrackedObject obj : trackedObjects) {
            int cx = obj.getRect().x() + obj.getRect().width() / 2;
            int cy = obj.getRect().y() + obj.getRect().height() / 2;
            centers.put(obj.getId(), new Point(cx, cy));

            if (obj.getId() == trajectoryManager.getSelectedTrack()) {
                rectangle(frame, obj.getRect(), new Scalar(0, 255, 0, 0), 3, 8, 0);
            }
        }

        trajectoryManager.updateTrajectory(centers);
    }

    private void drawSpeed(Mat frame, Scalar color, int thickness, int lineType) {
        for (Map.Entry<Integer, CopyOnWriteArrayList<Point>> entry
                : trajectoryManager.getTrajectories().entrySet()) {

            int id = entry.getKey();
            CopyOnWriteArrayList<Point> trajectory = entry.getValue();
            if (trajectory.isEmpty()) continue;

            ObjectMotionInfo motion = trajectoryManager.getMotionInfo(id);
            if (motion == null) continue;

            if(motion.vx == 0 && motion.vy == 0)
                continue;

            Point last = trajectory.getLast();

            String text = String.format("Speed: %.1f px/s", motion.speed);

            putText(frame,
                    text,
                    new Point(last.x() + 5, last.y() - 5),
                    FONT_HERSHEY_SIMPLEX,
                    0.5,
                    color,
                    thickness,
                    lineType,
                    false);
        }
    }


    private void drawDirection(Mat frame, Scalar color, int thickness, int lineType) {
        for (Map.Entry<Integer, CopyOnWriteArrayList<Point>> entry
                : trajectoryManager.getTrajectories().entrySet()) {

            int id = entry.getKey();
            CopyOnWriteArrayList<Point> trajectory = entry.getValue();
            if (trajectory.isEmpty()) continue;

            ObjectMotionInfo motion = trajectoryManager.getMotionInfo(id);
            if (motion == null) continue;

            Point last = trajectory.getLast();

            double vx = motion.vx;
            double vy = motion.vy;

            double norm = Math.sqrt(vx * vx + vy * vy);
            if (norm < 1e-3)
                continue;

            vx /= norm;
            vy /= norm;

            int arrowLength = 40;

            Point end = new Point(
                    (int)(last.x() + vx * arrowLength),
                    (int)(last.y() + vy * arrowLength)
            );

//            line(frame, last, end, color, thickness, lineType, 0);
            arrowedLine(
                    frame,
                    last,
                    end,
                    color,
                    thickness,
                    lineType,
                    0,
                    0.2   // длина наконечника (20% от стрелки)
            );

        }
    }


    private void drawTrajectory(Mat frame, Scalar color, int thickness, int lineType) {
        for (Map.Entry<Integer, CopyOnWriteArrayList<Point>> entry : trajectoryManager.getTrajectories().entrySet()) {
            CopyOnWriteArrayList<Point> trajectory = entry.getValue();
            if (trajectory.size() < 2) continue;

            Point prev = trajectory.getFirst();

            for (Point p : trajectory) {
                if (prev != p) {
                    line(frame, prev, p, color, thickness, lineType, 0);
                }
                prev = p;
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
