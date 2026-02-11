package knu.app.bll.algorithms;

import org.bytedeco.opencv.opencv_core.Rect;

public class IouComputer {

  public static double computeIoU(Rect a, Rect b) {
    if (a.width() <= 0 || a.height() <= 0 || b.width() <= 0 || b.height() <= 0) {
      return 0;
    }

    int x1 = Math.max(a.x(), b.x());
    int y1 = Math.max(a.y(), b.y());
    int x2 = Math.min(a.x() + a.width(), b.x() + b.width());
    int y2 = Math.min(a.y() + a.height(), b.y() + b.height());

    int interArea = Math.max(0, x2 - x1) * Math.max(0, y2 - y1);
    double unionArea = a.width() * a.height() + b.width() * b.height() - interArea;

    return interArea / unionArea;
  }
}
