package knu.app.bll.processors.draw;

import knu.app.bll.utils.processors.TrackedObject;
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
        lineType = 8;
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
    public void render(Mat frame, List<Rect> rects, List<Double> scores, boolean renderScores, Scalar color, int thick, int lineType) {
        if (rects.isEmpty()) return;

        for (int i = 0; i < rects.size(); i++) {
            Rect rect = rects.get(i);
            circle(frame, new Point((int) (rect.x() + rect.width() / 2.0), (int) (rect.y() + rect.height() / 2.0)), this.r, color, thick, 4, 0);

            if (renderScores && i < scores.size()) {
                String text = String.format("%.2f", scores.get(i));
                int[] baseLine = new int[1];
                Size textSize = getTextSize(text, FONT_HERSHEY_SIMPLEX, 0.5, thick, baseLine);

                Point textOrg = new Point(rect.x(), Math.max(rect.y(), textSize.height() + baseLine[0] + 2));
                rectangle(frame, new Point(textOrg.x(), textOrg.y() - textSize.height() - baseLine[0]), new Point(textOrg.x() + textSize.width(), textOrg.y() + baseLine[0]), new Scalar(0, 0, 0, 0), FILLED, 4, 0);

                putText(frame, text, textOrg, FONT_HERSHEY_SIMPLEX, 0.5, color, thick, lineType, false);
            }
        }
    }


    @Override
    public void render(Mat frame, List<TrackedObject> trackedObjects, boolean renderScores) {
        render(frame, trackedObjects, renderScores, scalar, thick, lineType);
    }

    @Override
    public void render(Mat frame, List<TrackedObject> trackedObjects, boolean renderScores, Scalar color, int thickness, int lineType) {

        if (trackedObjects.isEmpty()) return;

        for (TrackedObject obj : trackedObjects) {
            int cx = obj.getRect().x() + obj.getRect().width() / 2;
            int cy = obj.getRect().y() + obj.getRect().height() / 2;
            Point center = new Point(cx, cy);

            circle(frame, center, r, color, thickness, lineType, 0);

            if (!renderScores) continue;

            String text = String.format("%d | %.2f",obj.getId() , obj.getScore());

            int fontFace = FONT_HERSHEY_SIMPLEX;
            double fontScale = 0.6;
            int textThickness = thickness - 1;
            int[] baseLine = new int[1];
            Size textSize = getTextSize(text, fontFace, fontScale, textThickness, baseLine);


            int textX = cx - textSize.width() / 2;
            int textY = cy - r - 5;

            if (textY - textSize.height() - baseLine[0] < 0) {
                textY = cy + r + textSize.height() + baseLine[0] + 5;
            }

            Point bgTL = new Point(textX, textY - textSize.height() - baseLine[0]);
            Point bgBR = new Point(textX + textSize.width(), textY + baseLine[0]);

            rectangle(frame, bgTL, bgBR, new Scalar(0, 0, 0, 255), FILLED, lineType, 0);

            putText(frame, text, new Point(textX, textY), fontFace, fontScale, new Scalar(0, 0, 0, 255),
                    textThickness + 2, lineType, false);

            putText(frame, text, new Point(textX, textY), fontFace, fontScale, color, textThickness, lineType, false);
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
