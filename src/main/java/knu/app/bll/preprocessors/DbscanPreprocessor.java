package knu.app.bll.preprocessors;

import org.bytedeco.javacpp.indexer.UByteIndexer;
import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.global.opencv_imgproc;
import smile.clustering.DBSCAN;

import java.util.*;

import static org.bytedeco.opencv.global.opencv_ximgproc.*;

public class DbscanPreprocessor implements FramePreprocessor {

  private  double eps = 1;
  private  int minPts = 1;
  private  double alpha;


  @Override
  public Mat process(Mat input) {
    return input;
  }

  public void setMinPts(int minPts) {
    this.minPts = minPts;
  }

  public void setEps(double eps) {
    this.eps = eps;
  }

  public double getEps() {
    return eps;
  }

  public int getMinPts() {
    return minPts;
  }
}
