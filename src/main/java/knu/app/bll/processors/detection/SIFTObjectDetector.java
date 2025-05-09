package knu.app.bll.processors.detection;

import knu.app.bll.utils.Utils;
import knu.app.bll.utils.processors.DetectionResult;
import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.opencv_features2d.BFMatcher;
import org.bytedeco.opencv.opencv_features2d.SIFT;

import java.util.LinkedList;

import static org.bytedeco.opencv.global.opencv_core.NORM_L2;

public class SIFTObjectDetector implements ObjectDetector {
    private SIFT sift;
    private BFMatcher matcher;
    private Mat templateDescriptors;
    private final Mat templateImage;

    public SIFTObjectDetector(Mat templateImage) {
        this.templateImage = templateImage;
        init(0, 3, 0.04, 10, 1.6);
    }

    public void init(int nfeatures, int nOctaves, double contrastThreshold, double edgeThreshold, double sigma) {
        this.sift = SIFT.create(nfeatures, nOctaves, contrastThreshold, edgeThreshold, sigma);
        this.matcher = BFMatcher.create(NORM_L2, true);
        this.templateDescriptors = new Mat();
        if (sift != null) {
            sift.detectAndCompute(templateImage, new Mat(), new KeyPointVector(), templateDescriptors);
        }
    }

    @Override
    public DetectionResult detect(Mat frameGray) {
        KeyPointVector keypoints = new KeyPointVector();
        Mat descriptors = new Mat();
        sift.detectAndCompute(frameGray, new Mat(), keypoints, descriptors);

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
            if (m.distance() <= Math.max(2 * minDist, 0.02)) {
                matchedPoints.add(frameKeypoints.get(m.trainIdx()).pt());
            }
        }

        return Utils.clusterPoints(matchedPoints, 30.0, 3);
    }

}
