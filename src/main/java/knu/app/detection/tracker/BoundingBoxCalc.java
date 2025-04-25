package knu.app.detection.tracker;

import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Rect;

import java.util.LinkedList;

public class BoundingBoxCalc {
    static  public Rect calculateBoundingBox(LinkedList<Point2f> points) {
        if (points.size() < 4) return null;

        float minX = Float.MAX_VALUE, minY = Float.MAX_VALUE;
        float maxX = Float.MIN_VALUE, maxY = Float.MIN_VALUE;

        for (Point2f pt : points) {
            float x = pt.x(), y = pt.y();
            if (x < minX) minX = x;
            if (y < minY) minY = y;
            if (x > maxX) maxX = x;
            if (y > maxY) maxY = y;
        }

        return new Rect((int) minX, (int) minY, (int) (maxX - minX), (int) (maxY - minY));
    }
}
