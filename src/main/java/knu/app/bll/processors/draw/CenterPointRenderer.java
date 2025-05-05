package knu.app.bll.processors.draw;

import org.bytedeco.opencv.opencv_core.*;

import java.util.List;

import static org.bytedeco.opencv.global.opencv_imgproc.*;

public class CenterPointRenderer implements DetectionRenderer {
    private int r;
    private final Scalar scalar;
    private final int thick;
    private final int lineType;

    public CenterPointRenderer(int r) {
        this.r = r;

        scalar = Scalar.BLUE;
        thick = 3;
        lineType = -1;
    }

    public CenterPointRenderer() {
        this(30);
    }


    @Override
    public void render(Mat frame, List<Point2f> result) {
        render(frame, result, scalar, thick, lineType);
    }

    @Override
    public void render(Mat frame, List<Point2f> result, Scalar color, int thick, int type) {
        if (result.isEmpty()) return;

        float sumX = 0, sumY = 0;
        for (Point2f pt : result) {
            sumX += pt.x();
            sumY += pt.y();
        }

        int centerX = Math.round(sumX / result.size());
        int centerY = Math.round(sumY / result.size());

        circle(frame, new Point(centerX, centerY), r, color, thick, type, 0);
    }

    @Override
    public void render(Mat frame, List<Rect> rects, List<Double> scores, boolean renderScores) {
        render(frame, rects, scores, renderScores, scalar, thick, lineType);

    }

    @Override
    public void render(Mat frame, List<Rect> rects, List<Double> scores, boolean renderScores, Scalar color, int thick, int type) {
        if (rects.isEmpty()) return;
//        List<Point2f> points = new LinkedList<>();

        for (int i = 0; i < rects.size(); i++) {
//            points.add(new Point2f(r.x(), r.y()));
            circle(frame, new Point(rects.get(i).x(), rects.get(i).y()), this.r, color, thick, type, 0);

            if (renderScores && i < scores.size()) {
                String text = String.format("%.2f", scores.get(i));
                int[] baseLine = new int[1];
                var textSize = getTextSize(text, FONT_HERSHEY_SIMPLEX, 0.5, thick, baseLine);
                Point textOrg = new Point(rects.get(i).x(), Math.max(rects.get(i).y(), textSize.height() + baseLine[0] + 2));
                rectangle(frame, new Point(textOrg.x(), textOrg.y() - textSize.height() - baseLine[0]),
                        new Point(textOrg.x() + textSize.width(), textOrg.y() + baseLine[0]), new Scalar(0, 0, 0, 0),
                        FILLED, lineType, 0);
                putText(frame, text, textOrg, FONT_HERSHEY_SIMPLEX, 0.5,
                        color, thick, lineType, false
                );
            }
        }
    }


    @Override
    public Scalar getScalar() {
        return scalar;
    }

    @Override
    public int getThick() {
        return thick;
    }

    @Override
    public int getType() {
        return lineType;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getR() {
        return r;
    }
}
