package knu.app.bll.algorithms.kalman;

import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2f;

public interface KalmanFilter {

  void reset(Point2f initialPosition);

  Point2f update(Point2f measurement);

  void releaseResources();

  Mat getState();

  void setDt(float dt);

}
