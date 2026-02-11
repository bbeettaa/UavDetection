package knu.app.bll.utils;

import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Rect;

import java.util.*;

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

    public static List<Point2f> rectToPoints(Rect r) {
        return Arrays.asList(
                new Point2f(r.x(), r.y()),
                new Point2f(r.x() + r.width(), r.y()),
                new Point2f(r.x() + r.width(), r.y() + r.height()),
                new Point2f(r.x(), r.y() + r.height())
        );
    }

    public static Rect pointsToRect(List<Point2f> pts) {
        float[] xs = new float[pts.size()];
        float[] ys = new float[pts.size()];

        for (int i = 0; i < pts.size(); i++) {
            xs[i] = pts.get(i).x();
            ys[i] = pts.get(i).y();
        }

        Arrays.sort(xs);
        Arrays.sort(ys);

        return new Rect(
                (int) xs[0],
                (int) ys[0],
                (int) (xs[xs.length-1] - xs[0]),
                (int) (ys[ys.length-1] - ys[0])
        );
    }

    public static Point2f rectCenter(Rect rect) {
        float centerX = rect.x() + rect.width()  / 2.0f;
        float centerY = rect.y() + rect.height() / 2.0f;
        return new Point2f(centerX, centerY);
    }

    public static Rect centerToRect(Point2f center, int width, int height) {
        int x = Math.round(center.x() - width  / 2.0f);
        int y = Math.round(center.y() - height / 2.0f);

        return new Rect(x, y, width, height);
    }

    public static class RectWithScore {
        public Rect rect;
        public double score;

        public RectWithScore(Rect rect, double score) {
            this.rect  = rect;
            this.score = score;
        }
    }

    public static List<RectWithScore> clusterToRects(LinkedList<Point2f> points, float distanceThreshold) {
        List<RectWithScore> results = new ArrayList<>();
        int n = points.size();
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;

            Queue<Integer> queue   = new LinkedList<>();
            List<Point2f> cluster  = new ArrayList<>();
            visited[i] = true;
            queue.add(i);

            while (!queue.isEmpty()) {
                int idx = queue.poll();
                Point2f p = points.get(idx);
                cluster.add(p);

                for (int j = 0; j < n; j++) {
                    if (!visited[j]) {
                        Point2f q = points.get(j);
                        float dx = p.x() - q.x();
                        float dy = p.y() - q.y();
                        if (dx*dx + dy*dy <= distanceThreshold*distanceThreshold) {
                            visited[j] = true;
                            queue.add(j);
                        }
                    }
                }
            }

            // bounding-box
            float minX = Float.MAX_VALUE, minY = Float.MAX_VALUE;
            float maxX = Float.MIN_VALUE, maxY = Float.MIN_VALUE;
            for (Point2f pt : cluster) {
                minX = Math.min(minX, pt.x());
                minY = Math.min(minY, pt.y());
                maxX = Math.max(maxX, pt.x());
                maxY = Math.max(maxY, pt.y());
            }
            float width  = maxX - minX;
            float height = maxY - minY;
            float side   = Math.max(width, height);

            if (width < side) {
                minX -= (side - width) / 2;
            } else {
                minY -= (side - height) / 2;
            }

            Rect square = new Rect(
                    Math.round(minX),
                    Math.round(minY),
                    Math.round(side),
                    Math.round(side)
            );

            int score = cluster.size();

            results.add(new RectWithScore(square, score));
        }

        return results;
    }

}
