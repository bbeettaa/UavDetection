package knu.app.bll.processors.detection;

import knu.app.bll.utils.Utils;
import knu.app.bll.utils.processors.DetectionResult;
import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.opencv_features2d.BFMatcher;
import org.bytedeco.opencv.opencv_xfeatures2d.SURF;

import java.util.LinkedList;
import java.util.List;

import static org.bytedeco.opencv.global.opencv_core.NORM_L2;

public class SURFObjectDetector implements ObjectDetector {
    private SURF surf;
    private BFMatcher matcher;
    private Mat templateDescriptors;
    private final Mat templateImage;

    public SURFObjectDetector(Mat templateImage) {
        this(templateImage, 100, 4, 3, false, false);
    }

    public SURFObjectDetector(Mat templateImage, int hessianThreshold, int nOctaves, int nOctaveLayers, boolean extended, boolean upright) {
        this.templateImage = templateImage;
        init(hessianThreshold, nOctaves, nOctaveLayers, extended, upright);
    }

    public void init(int hessianThreshold, int nOctaves, int nOctaveLayers, boolean extended, boolean upright) {
        this.surf = SURF.create(hessianThreshold, nOctaves, nOctaveLayers, extended, upright);
        this.matcher = BFMatcher.create(NORM_L2, true);
        KeyPointVector templateKeypoints = new KeyPointVector();
        this.templateDescriptors = new Mat();
        if (surf != null) {
            surf.detectAndCompute(templateImage, new Mat(), templateKeypoints, templateDescriptors);
        }
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
        List<Utils.RectWithScore> rects= Utils.clusterToRects(matchedPoints, 50f);
        return new DetectionResult(
                rects.stream().map(e->e.rect).toList(),
                rects.stream().map(e->e.score).toList()
        );
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
