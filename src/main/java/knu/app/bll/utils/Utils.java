package knu.app.bll.utils;

import org.bytedeco.opencv.opencv_core.Point2f;

import java.util.LinkedList;

public class Utils {
    public static String formatTimestamp(long timestampMillis) {
        long hours = timestampMillis / (1000 * 60 * 60);
        long minutes = (timestampMillis / (1000 * 60)) % 60;
        long seconds = (timestampMillis / 1000) % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public static LinkedList<Point2f> clusterPoints(LinkedList<Point2f> points, double r, int minNeighbors) {
        LinkedList<Point2f> result = new LinkedList<>();
        for (Point2f pt : points) {
            int neighbors = 0;
            for (Point2f other : points) {
                double dx = pt.x() - other.x();
                double dy = pt.y() - other.y();
                if (dx * dx + dy * dy <= r * r) neighbors++;
            }
            if (neighbors >= minNeighbors) result.add(pt);
        }
        return result;
    }

}
