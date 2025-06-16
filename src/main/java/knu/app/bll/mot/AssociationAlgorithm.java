package knu.app.bll.mot;


import knu.app.bll.utils.processors.DetectionResult;
import knu.app.bll.utils.processors.TrackedObject;
import org.bytedeco.opencv.opencv_core.Rect;

import java.util.List;
import java.util.Map;

public interface AssociationAlgorithm {
    Map<TrackedObject, Rect> associate(List<TrackedObject> tracks, DetectionResult detections);

    double getIouThreshold();

    void setIouThreshold(double iou);
}