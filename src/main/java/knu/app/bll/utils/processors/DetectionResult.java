package knu.app.bll.utils.processors;

import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Rect;

import java.util.LinkedList;
import java.util.List;

public class DetectionResult {
    private final List<Rect> rects;
    private final List<Double> scores;

    public DetectionResult() {
        this( new LinkedList<>(), new LinkedList<>());
    }

    public DetectionResult(List<Rect> rects, List<Double> scores) {
        this.rects = rects;
        this.scores = scores;
    }

    public List<Double> getScores() {
        return scores;
    }

    public List<Rect> getRects() {
        return rects;
    }
}
