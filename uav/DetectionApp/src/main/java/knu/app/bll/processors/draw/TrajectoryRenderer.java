package knu.app.bll.processors.draw;

import static org.bytedeco.opencv.global.opencv_imgproc.LINE_8;
import static org.bytedeco.opencv.global.opencv_imgproc.line;
import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import knu.app.bll.algorithms.trajectory.TrajectoryManager;
import knu.app.bll.utils.processors.TrackedObject;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.Scalar;

/**
 * Рендерер, специализирующийся исключительно на отрисовке траекторий (линий движения) отслеживаемых
 * объектов.
 */
public class TrajectoryRenderer implements DetectionRenderer {

  private final TrajectoryManager trajectoryManager;

  private final Scalar defaultColor = new Scalar(255, 255, 0, 150);
  private int defaultThickness = 2;

  public TrajectoryRenderer(TrajectoryManager trajectoryManager) {
    this.trajectoryManager = trajectoryManager;
  }

  @Override
  public void render(Mat frame, List<TrackedObject> trackedObjects, boolean renderScores) {
    render(frame, trackedObjects, false, defaultColor, defaultThickness, LINE_8);
  }

  @Override
  public void render(Mat frame, List<TrackedObject> trackedObjects, boolean renderScores,
      Scalar color, int thickness, int lineType) {

    Map<Integer, Point> centers = new HashMap<>(trackedObjects.size());
    for (TrackedObject obj : trackedObjects) {
      int cx = obj.getRect().x() + obj.getRect().width() / 2;
      int cy = obj.getRect().y() + obj.getRect().height() / 2;
      centers.put(obj.getId(), new Point(cx, cy));

      if (obj.getId() == trajectoryManager.getSelectedTrack()) {
        rectangle(frame, obj.getRect(), new Scalar(0,255,0,0), 3 , 8, 0);
      }
    }

    trajectoryManager.updateTrajectory(centers);

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
