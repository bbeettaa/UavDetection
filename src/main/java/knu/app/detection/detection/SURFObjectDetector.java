package knu.app.detection.detection;

import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.opencv_features2d.BFMatcher;
import org.bytedeco.opencv.opencv_xfeatures2d.SURF;

import java.util.LinkedList;

import static org.bytedeco.opencv.global.opencv_core.NORM_L2;
import static org.bytedeco.opencv.global.opencv_core.fastAtan2;

public class SURFObjectDetector implements ObjectDetector {
    private final SURF surf;
    private final BFMatcher matcher;
    private final Mat templateDescriptors;
    private final KeyPointVector templateKeypoints;

    public SURFObjectDetector(Mat templateImage) {
        this(templateImage,100,4, 3, false, false);
    }

    public SURFObjectDetector(Mat templateImage, int hessianThreshold, int nOctaves, int nOctaveLayers, boolean extended, boolean upright) {
        this.surf = SURF.create(hessianThreshold, nOctaves, nOctaveLayers, extended, upright);
        this.matcher = BFMatcher.create(NORM_L2, true);
        this.templateKeypoints = new KeyPointVector();
        this.templateDescriptors = new Mat();
        surf.detectAndCompute(templateImage, new Mat(), templateKeypoints, templateDescriptors);
    }

    @Override
    public DetectionResult detect(Mat frameGray) {
        KeyPointVector keypoints = new KeyPointVector();
        Mat descriptors = new Mat();
        surf.detectAndCompute(frameGray, new Mat(), keypoints, descriptors);

        if (descriptors.empty()) return new DetectionResult();

        DMatchVector matches = new DMatchVector();
        matcher.match(templateDescriptors, descriptors, matches);

        LinkedList<Point2f> matchedPoints = filterAndCluster(matches, keypoints);
        return new DetectionResult(matchedPoints);
    }

    private LinkedList<Point2f> filterAndCluster(DMatchVector matches, KeyPointVector frameKeypoints) {
        DMatch[] matchesArray = matches.get();
        double minDist = Double.MAX_VALUE;
        for (DMatch m : matchesArray)
            minDist = Math.min(minDist, m.distance());

        LinkedList<Point2f> matchedPoints = new LinkedList<>();
        for (DMatch m : matchesArray) {
            if (m.distance() <= Math.max(2 * minDist, 30.0)) {
                matchedPoints.add(frameKeypoints.get(m.trainIdx()).pt());
            }
        }

        return clusterPoints(matchedPoints, 30.0, 3);
    }

    private LinkedList<Point2f> clusterPoints(LinkedList<Point2f> points, double radius, int minNeighbors) {
        LinkedList<Point2f> result = new LinkedList<>();
        for (Point2f pt : points) {
            int neighbors = 0;
            for (Point2f other : points) {
                double dx = pt.x() - other.x();
                double dy = pt.y() - other.y();
                if (dx * dx + dy * dy <= radius * radius) neighbors++;
            }
            if (neighbors >= minNeighbors) result.add(pt);
        }
        return result;
    }
}
