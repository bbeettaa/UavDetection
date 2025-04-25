package knu.app.detection.draw;

import org.bytedeco.opencv.opencv_core.*;

import java.util.LinkedList;

import static org.bytedeco.opencv.global.opencv_imgproc.*;

public class KeypointsRenderer implements DetectionRenderer {
    private final int r;

    public KeypointsRenderer(int r) {
        this.r = r;
    }

    public KeypointsRenderer() {
        this.r = 3;
    }

    @Override
    public void render(Mat frame, LinkedList<Point2f> result) {
        render(frame, result, Scalar.RED, -1, -1);
    }

    @Override
    public void render(Mat frame, LinkedList<Point2f> result, Scalar color, int thick, int type) {
        if (result.isEmpty()) return;

        for (Point2f pt : result) {
            circle(frame, new Point((int) pt.x(), (int) pt.y()), r,color, thick, type, 0);
        }
    }
}
