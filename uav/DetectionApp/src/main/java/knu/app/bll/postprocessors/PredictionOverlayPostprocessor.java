package knu.app.bll.postprocessors;

import knu.app.bll.algorithms.feature.ObjectState;
import knu.app.bll.algorithms.kalman.AccelerationKalmanFilter;
import knu.app.bll.utils.processors.TrackedObject;
import org.bytedeco.opencv.opencv_core.*;
import java.util.*;
import static org.bytedeco.opencv.global.opencv_imgproc.*;

public class PredictionOverlayPostprocessor implements FramePostprocessorValue<List<TrackedObject>> {

    List<TrackedObject> trackedObjects;
//    private final IKalmanFilter kalman;

    private final Scalar COLOR_ACTUAL = new Scalar(255, 255, 0, 0);
    private final Scalar COLOR_PRED = new Scalar(255, 0, 0, 0);
    private final Scalar COLOR_ANOM = new Scalar(0, 165, 255, 0);
    private  int predictionHorizon = 20;

    public PredictionOverlayPostprocessor(
//            IKalmanFilter kalman
    ) {
//        this.kalman = kalman;
    }

    @Override
    public void setValue(List<TrackedObject> trackedObjects) {
        this.trackedObjects = trackedObjects;
    }

    @Override
    public Mat process(Mat input) {
        if (input == null || trackedObjects == null || trackedObjects.isEmpty()) {
            return input;
        }

        Map<Integer, List<Point2f>> predicted = new HashMap<>(trackedObjects.size());

        for (TrackedObject obj : trackedObjects) {
            AccelerationKalmanFilter kalman = obj.getPredictor();

            List<ObjectState> traj = safeCopy(obj);
            if (traj.size() < 2) {
                continue;
            }

            try {
                ObjectState first = traj.getFirst();
                kalman.reset(new Point2f(first.center.x(), first.center.y()));
//                kalman.setDt(0.033f);

                for (int i = 1; i < traj.size(); i++) {
                    ObjectState st = traj.get(i);
                    kalman.update(new Point2f(st.center.x(), st.center.y()));
                }

                List<Point2f> future = new ArrayList<>(predictionHorizon);

                for (int s = 0; s < predictionHorizon; s++) {
                    Point2f next = kalman.predict();
                    if (next == null) break;
                    future.add(new Point2f(next.x(), next.y()));
                    kalman.update(null);
                }

                if (!future.isEmpty()) {
                    predicted.put(obj.getId(), future);
                }

            } catch (Exception e) {
                System.err.println("Prediction error for ID "
                        + obj.getId() + ": " + e.getMessage());
            }
        }

        drawActual(input, trackedObjects);
        drawPredicted(input, trackedObjects, predicted);

        return input;
    }




    private void drawActual(Mat frame, List<TrackedObject> tracked) {
        for (TrackedObject obj : tracked) {

            Rect r = obj.getRect();
            if (r != null) {
                rectangle(frame, r, COLOR_ACTUAL, 2, LINE_AA, 0);
            }

            List<ObjectState> traj = safeCopy(obj);

            if (!traj.isEmpty()) {
                ObjectState last = traj.get(traj.size() - 1);
                circle(frame, last.center, 4, COLOR_ACTUAL, -1, LINE_AA, 0);

                if (last.isAnomalous) {
                    Rect bbox = last.boundingBox != null ? last.boundingBox : r;
                    if (bbox != null) {
                        rectangle(frame, bbox, COLOR_ANOM, 3, LINE_AA, 0);
                        putText(frame,"ANOMALY", new Point(bbox.x(), Math.max(0, bbox.y() - 5)),
                                FONT_HERSHEY_SIMPLEX, 0.5, COLOR_ANOM,2, LINE_AA, false);
                    }
                }
            }
        }
    }



    private void drawPredicted(Mat frame,
                               List<TrackedObject> tracked,
                               Map<Integer, List<Point2f>> predicted) {
        if (predicted == null) return;

        for (Map.Entry<Integer, List<Point2f>> e : predicted.entrySet()) {
            int id = e.getKey();
            List<Point2f> predPoints = e.getValue();
            if (predPoints == null || predPoints.isEmpty()) continue;

            // Знаходимо оригінальний об'єкт для отримання розмірів рамки
            TrackedObject obj = tracked.stream()
                    .filter(o -> o.getId() == id)
                    .findFirst()
                    .orElse(null);

            if (obj == null) continue;
            Rect currentRect = obj.getRect();

            // Малюємо рамки для кожного кроку прогнозу
            for (int i = 0; i < predPoints.size(); i++) {
                Point2f p = predPoints.get(i);

                // Розрахунок координат рамки (центруємо по передбаченій точці)
                int predX = (int) (p.x() - currentRect.width() / 2.0);
                int predY = (int) (p.y() - currentRect.height() / 2.0);

                Rect predRect = new Rect(predX, predY, currentRect.width(), currentRect.height());

                // Чим далі прогноз — тим прозоріша або тонша лінія (для наочності)
                int thickness = (i == predPoints.size() - 1) ? 2 : 1;

                // Малюємо передбачену рамку (зеленим)
                rectangle(frame, predRect, COLOR_ACTUAL, thickness, LINE_AA, 0);

                // Опціонально: ID зверху останньої передбаченої рамки
                if (i == predPoints.size() - 1) {
                    putText(frame, "P-ID" + id, new Point(predX, predY - 5),
                            FONT_HERSHEY_SIMPLEX, 0.4, COLOR_ACTUAL, 1, LINE_AA, false);
                }
            }
        }
    }



    private List<ObjectState> safeCopy(TrackedObject obj) {
        List<ObjectState> src = obj.getTrajectory();
        if (src == null) return Collections.emptyList();
        synchronized (src) {
            return new ArrayList<>(src);
        }
    }

    private Point toPoint(Point2f p) {
        return new Point((int) p.x(), (int) p.y());
    }

    private void drawDashedLine(Mat frame,
                                Point p0,
                                Point p1,
                                Scalar color,
                                int thickness) {

        double dx = p1.x() - p0.x();
        double dy = p1.y() - p0.y();
        double len = Math.hypot(dx, dy);

        if (len < 1) return;

        double vx = dx / len;
        double vy = dy / len;

        int dash = 8;
        int gap = 6;

        double pos = 0;
        boolean draw = true;

        while (pos < len) {
            double segment = draw ? dash : gap;
            double end = Math.min(len, pos + segment);

            if (draw) {
                Point a = new Point(
                        (int) (p0.x() + vx * pos),
                        (int) (p0.y() + vy * pos));

                Point b = new Point(
                        (int) (p0.x() + vx * end),
                        (int) (p0.y() + vy * end));

                line(frame, a, b, color, thickness, LINE_AA, 0);
            }

            draw = !draw;
            pos += segment;
        }
    }

    private void drawLegend(Mat frame) {
        int x = 10;
        int y = 10;

        putText(frame, "Green = Actual", new Point(x, y + 15),
                FONT_HERSHEY_SIMPLEX, 0.5,
                COLOR_ACTUAL, 1, LINE_AA, false);

        putText(frame, "Blue dashed = Predicted", new Point(x, y + 30),
                FONT_HERSHEY_SIMPLEX, 0.5,
                COLOR_PRED, 1, LINE_AA, false);

        putText(frame, "Orange = Anomaly", new Point(x, y + 45),
                FONT_HERSHEY_SIMPLEX, 0.5,
                COLOR_ANOM, 1, LINE_AA, false);
    }

    public void setPredictionHorizon(int predictionHorizon) {
        this.predictionHorizon = predictionHorizon;
    }

    public int getPredictionHorizon() {
        return predictionHorizon;
    }
}