package knu.app.bll.processors.detection;

import knu.app.bll.utils.Utils;
import knu.app.bll.utils.processors.DetectionResult;
import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.opencv_features2d.BFMatcher;
import org.bytedeco.opencv.opencv_features2d.ORB;

import java.util.LinkedList;

import static org.bytedeco.opencv.global.opencv_core.NORM_HAMMING;

public class ORBObjectDetector implements ObjectDetector {
    private ORB orb;
    private final BFMatcher matcher;
    private final Mat templateDescriptors;

    public ORBObjectDetector(Mat templateImage) {
        init(500, 1.2f, 8, 31, 0, 2, ORB.HARRIS_SCORE, 31, 20);
        this.matcher = BFMatcher.create(NORM_HAMMING, true);
        this.templateDescriptors = new Mat();
        orb.detectAndCompute(templateImage, new Mat(), new KeyPointVector(), templateDescriptors);
    }

    public void init(int nfeatures, float scaleFactor, int nlevels, int edgeThreshold,
                     int firstLevel, int WTA_K, int scoreType, int patchSize, int fastThreshold) {
        this.orb = ORB.create(nfeatures, scaleFactor, nlevels, edgeThreshold,
                firstLevel, WTA_K, scoreType, patchSize, fastThreshold);
    }

    @Override
    public DetectionResult detect(Mat frameGray) {
        KeyPointVector keypoints = new KeyPointVector();
        Mat descriptors = new Mat();
        orb.detectAndCompute(frameGray, new Mat(), keypoints, descriptors);

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

        return Utils.clusterPoints(matchedPoints, 30.0, 3);
    }

}
