package knu.app.bll.processors.tracker.multi;

import java.util.List;
import knu.app.bll.utils.processors.DetectionResult;
import knu.app.bll.utils.processors.TrackedObject;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Rect;

public interface MultiObjectTracker {

  List<TrackedObject> update(Mat frame, List<DetectionResult> detResult);

  void close();

  int getBufferCapacity();

  boolean init(float trackThresh, float highThresh, float matchThresh, int trackBuffer);
}
