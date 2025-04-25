package knu.app.detection.draw;


import org.bytedeco.opencv.opencv_core.*;

import java.util.LinkedList;

import static org.bytedeco.opencv.global.opencv_imgproc.*;

public class ROIRenderer implements DetectionRenderer {

    @Override
    public void render(Mat frame, LinkedList<Point2f> result) {
        render(frame, result, Scalar.RED, 2, LINE_8);
    }

    @Override
    public void render(Mat frame, LinkedList<Point2f> result, Scalar color, int thick, int type) {
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
        rectangle(frame, roi, color, thick, LINE_8, 0);
    }
}

