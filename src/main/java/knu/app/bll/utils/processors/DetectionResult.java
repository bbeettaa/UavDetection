package knu.app.bll.utils.processors;

import com.example.yolo.BoundingBox;
import java.util.LinkedList;
import java.util.List;
import org.bytedeco.opencv.opencv_core.Rect;

public class DetectionResult {

  private final List<Rect> rects;
  private final List<Double> scores;
  private final List<String> names;

  public DetectionResult() {
    this(new LinkedList<>(), new LinkedList<>(), new LinkedList<>());
  }

  public DetectionResult(List<Rect> rects, List<Double> scores, List<String> names) {
    this.rects = rects;
    this.scores = scores;
    this.names = names;
  }

  public DetectionResult(List<Rect> rects, List<Double> scores) {
    this.rects = rects;
    this.scores = scores;
    this.names = new LinkedList<>();
  }

  public List<Double> getScores() {
    return scores;
  }

  public List<Rect> getRects() {
    return rects;
  }

  public List<String> getNames() {
    return names;
  }
}
