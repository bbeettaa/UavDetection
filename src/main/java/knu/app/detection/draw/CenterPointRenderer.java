package knu.app.detection.draw;

import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Scalar;

import java.util.LinkedList;

import static org.bytedeco.opencv.global.opencv_imgproc.*;

public class CenterPointRenderer implements DetectionRenderer {
    @Override
    public void render(Mat frame, LinkedList<Point2f> result) {
        render(frame, result, Scalar.RED,  FILLED, LINE_8);
    }

    @Override
    public void render(Mat frame, LinkedList<Point2f> result, Scalar color,  int thick, int type) {
        if (result.isEmpty()) return;

        float sumX = 0, sumY = 0;
        for (Point2f pt : result) {
            sumX += pt.x();
            sumY += pt.y();
        }

        int centerX = Math.round(sumX / result.size());
        int centerY = Math.round(sumY / result.size());

        circle(frame, new Point(centerX, centerY), 5, color, thick, type, 0);
    }
}
