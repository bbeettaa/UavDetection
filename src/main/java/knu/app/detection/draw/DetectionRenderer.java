package knu.app.detection.draw;

import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Scalar;

import java.util.LinkedList;

public interface DetectionRenderer {
    void render(Mat frame, LinkedList<Point2f> result);
    void render(Mat frame, LinkedList<Point2f> result, Scalar color, int thick, int type);
}
