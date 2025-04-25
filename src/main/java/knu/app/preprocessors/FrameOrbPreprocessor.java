//package knu.app.preprocessors;
//
//
//import org.bytedeco.opencv.opencv_core.Mat;
//import org.bytedeco.opencv.opencv_core.Rect2d;
//import org.bytedeco.opencv.opencv_tracking.TrackerKCF;
//import org.bytedeco.opencv.opencv_video.Tracker;
//
//public class FrameOrbPreprocessor implements FramePreprocessor {
//    @Override
//    public Mat process(Mat input) {
//        // ORB детектор
//        Tracker tracker = TrackerKCF.create(); // или TrackerCSRT.create(), и т.д.
//        tracker.init(input, new Rect2d(x, y, width, height));
//        tracker.update(nextFrame, outputRect);
//
//
//// SIFT детектор
////        SIFT sift = SIFT.create();
////        sift.detectAndCompute(grayFrame, new Mat(), keypoints, descriptors);
////
////// Сопоставление особенностей
////        BFMatcher matcher = BFMatcher.create(NORM_HAMMING, true);
////        matcher.match(descriptors1, descriptors2, matches);
//        return null;
//    }
//}
