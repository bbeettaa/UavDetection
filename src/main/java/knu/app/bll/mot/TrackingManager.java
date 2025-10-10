package knu.app.bll.mot;

import java.util.List;
import knu.app.bll.algorithms.associative.AssociationAlgorithm;
import knu.app.bll.confirmation.MaxMissedDeletingAlgorithm;
import knu.app.bll.confirmation.NOutOfMConfirmation;
import knu.app.bll.utils.processors.DetectionResult;
import knu.app.bll.utils.processors.TrackedObject;
import org.bytedeco.opencv.opencv_core.Mat;

public interface TrackingManager {

  List<TrackedObject> update(Mat mat, DetectionResult detResult);

  void setObjectTracker(String trackerKey);

  void reset();

  AssociationAlgorithm getAssociationAlgorithm();

  NOutOfMConfirmation getConfirmationAlgorithm();

  MaxMissedDeletingAlgorithm getDeletingConfirmationAlgorithm();

  int getBufferCapacity();

  int getBufferSize();

  void setBuffCapacity(int c);

}
