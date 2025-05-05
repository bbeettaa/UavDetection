package knu.app.bll.processors.draw;


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

        for (int i = 0; i < rects.size(); i++) {
            Rect r = rects.get(i);
            rectangle(frame, r, color, thick, lineType, 0);

            if (renderScores && i < scores.size()) {
                String text = String.format("%.2f", scores.get(i));
                int[] baseLine = new int[1];
                var textSize = getTextSize(text, FONT_HERSHEY_SIMPLEX, 0.4, thick, baseLine);
                Point textOrg = new Point(r.x(), Math.max(r.y(), textSize.height() + baseLine[0] + 2));
                rectangle(frame, new Point(textOrg.x(), textOrg.y() - textSize.height() - baseLine[0]),
                        new Point(textOrg.x() + textSize.width(), textOrg.y() + baseLine[0]), new Scalar(0, 0, 0, 0),
                        FILLED, lineType, 0);
                putText(frame, text, textOrg, FONT_HERSHEY_SIMPLEX, 0.5,
                        color, thick, lineType, false
                );
            }
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

