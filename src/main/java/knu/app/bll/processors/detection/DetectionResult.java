package knu.app.bll.processors.detection;

import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Rect;

import java.util.LinkedList;
import java.util.List;

public class DetectionResult {
    private final List<Rect> rects;
    private final List<Point2f> points;
    private final List<Double> scores;

    public DetectionResult() {
        this(new LinkedList<>(), new LinkedList<>(), new LinkedList<>());
    }

    public DetectionResult(List<Point2f> points) {
        this(points, new LinkedList<>(), new LinkedList<>());
    }

    public DetectionResult(List<Rect> rects, List<Double> scores) {
        this.points = new LinkedList<>();
        this.rects = rects;
        this.scores = scores;
    }

    public DetectionResult(List<Point2f> points, List<Rect> rects, List<Double> scores) {
        this.points = points;
        this.rects = rects;
        this.scores = scores;
    }

    public List<Point2f> getPoints() {
        return points;
    }

    public List<Double> getScores() {
        return scores;
    }

    public List<Rect> getRects() {
        return rects;
    }
}
