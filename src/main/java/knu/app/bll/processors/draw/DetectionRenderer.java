package knu.app.bll.processors.draw;

import knu.app.bll.utils.processors.TrackedObject;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.Scalar;

import java.util.List;

public interface DetectionRenderer {

    Scalar getScalar();
    int getThick();
    int getType();

    void render(Mat frame, List<Point2f> result);
    void render(Mat frame, List<Point2f> result, Scalar color, int thick, int type);

    void render(Mat frame, List<Rect> rects, List<Double> scores, boolean renderScores);
    void render(Mat frame, List<Rect> rects, List<Double> scores, boolean renderScores, Scalar color, int thick, int type);

    void render(Mat frame, List<TrackedObject> trackedObjects, boolean renderScores);
    void render(Mat frame, List<TrackedObject> trackedObjects, boolean renderScores, Scalar color, int thick, int type);
}
