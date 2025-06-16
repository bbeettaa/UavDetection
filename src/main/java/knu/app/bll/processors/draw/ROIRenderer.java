package knu.app.bll.processors.draw;


import knu.app.bll.utils.processors.TrackedObject;
import org.bytedeco.opencv.opencv_core.*;

import java.util.List;

import static org.bytedeco.opencv.global.opencv_imgproc.*;

public class ROIRenderer implements DetectionRenderer {
    private Scalar scalar = Scalar.RED;
    private int thick = 2;
    private int type = LINE_8;

    @Override
    public void render(Mat frame, List<Point2f> result) {
        render(frame, result, scalar, thick, type);
    }

    @Override
    public void render(Mat frame, List<Point2f> result, Scalar color, int thick, int type) {
        if (result.isEmpty()) return;

        float minX = Float.MAX_VALUE, minY = Float.MAX_VALUE;
        float maxX = Float.MIN_VALUE, maxY = Float.MIN_VALUE;

        for (Point2f pt : result) {
            float x = pt.x(), y = pt.y();
            if (x < minX) minX = x;
            if (y < minY) minY = y;
            if (x > maxX) maxX = x;
            if (y > maxY) maxY = y;
        }

        Rect roi = new Rect((int) minX, (int) minY, (int) (maxX - minX), (int) (maxY - minY));
        rectangle(frame, roi, color, thick, type, 0);
    }

    @Override
    public void render(Mat frame, List<Rect> rects, List<Double> scores, boolean renderScores) {
        render(frame, rects, scores, renderScores, scalar, thick, type);
    }

    @Override
    public void render(Mat frame, List<Rect> rects, List<Double> scores, boolean renderScores, Scalar color, int thick, int lineType) {
        if (rects == null || rects.isEmpty()) return;

        if (renderScores && (scores == null || scores.size() < rects.size())) {
            throw new IllegalArgumentException("Scores list is invalid when renderScores is true");
        }

        for (int i = 0; i < rects.size(); i++) {
            Rect r = rects.get(i);
            rectangle(frame, r, color, thick, lineType, 0);

            if (renderScores) {
                double score = scores.get(i);
                String text = String.format("%.2f", score);
                int[] baseLine = new int[1];
                Size textSize = getTextSize(text, FONT_HERSHEY_SIMPLEX, 0.4, thick, baseLine);

                Point textOrg = new Point(r.x(), Math.max(r.y(), textSize.height() + baseLine[0] + 2));

                rectangle(frame,
                        new Point(textOrg.x(), textOrg.y() - textSize.height() - baseLine[0]),
                        new Point(textOrg.x() + textSize.width(), textOrg.y() + baseLine[0]),
                        new Scalar(0, 0, 0, 0), CV_FILLED, lineType, 0);
                putText(frame, text, textOrg, FONT_HERSHEY_SIMPLEX, 0.5, color, thick, lineType, false);
            }
        }
    }


    @Override
    public void render(Mat frame, List<TrackedObject> trackedObjects, boolean renderScores) {
        render(frame, trackedObjects, renderScores, scalar, thick, type);
    }

    @Override
    public void render(Mat frame, List<TrackedObject> trackedObjects, boolean renderScores, Scalar color, int thick, int type) {
        if (trackedObjects.isEmpty()) return;

        TrackedObject obj;
        for (TrackedObject trackedObject : trackedObjects) {
            obj = trackedObject;
            rectangle(frame, obj.getRect(), color, thick, type, 0);

            String text = String.format("%.2f", obj.getScore());
            int[] baseLine = new int[1];
            var textSize = getTextSize(text, FONT_HERSHEY_SIMPLEX, 0.5, thick, baseLine);
            Point textOrg = new Point(obj.getRect().x(), Math.max(obj.getRect().y(), textSize.height() + baseLine[0] + 2));
            rectangle(frame, new Point(textOrg.x(), textOrg.y() - textSize.height() - baseLine[0]), new Point(textOrg.x() + textSize.width(), textOrg.y() + baseLine[0]), new Scalar(0, 0, 0, 0), 8, type, 0);
            putText(frame, text, textOrg, FONT_HERSHEY_SIMPLEX, 0.5, color, thick, type, false);
        }
    }


    public void setScalar(Scalar scalar) {
        this.scalar = scalar;
    }

    public void setThick(int thick) {
        this.thick = thick;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getThick() {
        return thick;
    }

    public int getType() {
        return type;
    }

    public Scalar getScalar() {
        return scalar;
    }
}

