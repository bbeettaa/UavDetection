package knu.app.detection.detection;

import org.bytedeco.opencv.opencv_core.Point2f;

import java.util.LinkedList;

public class DetectionResult {
    private final LinkedList<Point2f> points;

    public DetectionResult() {
        this.points = new LinkedList<>();
    }

    public DetectionResult(LinkedList<Point2f> points) {
        this.points = points;
    }

    public LinkedList<Point2f> getPoints() {
        return points;
    }

    public boolean isValid() {
        return points != null && points.size() >= 4;
    }
}
