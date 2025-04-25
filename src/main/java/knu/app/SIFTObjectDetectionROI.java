//package knu.app;
//
//import org.bytedeco.javacv.*;
//import org.bytedeco.opencv.opencv_core.*;
//import org.bytedeco.opencv.opencv_features2d.*;
//
//import static org.bytedeco.opencv.global.opencv_core.*;
//import static org.bytedeco.opencv.global.opencv_imgproc.*;
//import static org.bytedeco.opencv.global.opencv_imgcodecs.*;
//
//import java.util.LinkedList;
//
//public class SIFTObjectDetectionROI {
//
//    private static final double CLUSTERING_RADIUS = 30.0;
//    private static final int MIN_NEIGHBORS = 3;
//    private static final Size size = new Size(1280, 720);
////    private static final Size size = new Size(640, 480);
//
//    public static void main(String[] args) throws Exception {
//        String videoPath = "/home/bedu/Документы/uav1.mp4";
//        String templatePath = "/home/bedu/Документы/drone.jpg";
//
//        Mat templateImage = loadTemplateImage(templatePath);
//        if (templateImage == null) return;
//
//        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoPath);
//        grabber.start();
//
//        SIFT sift = SIFT.create();
//        BFMatcher matcher = BFMatcher.create(NORM_L2, true);
//
//        KeyPointVector templateKeypoints = new KeyPointVector();
//        Mat templateDescriptors = new Mat();
//        sift.detectAndCompute(templateImage, new Mat(), templateKeypoints, templateDescriptors);
//
//        CanvasFrame canvas = new CanvasFrame("ROI Detection");
//        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
//
//        processVideoFrames(grabber, sift, matcher, templateDescriptors, canvas);
//
//        grabber.stop();
//        canvas.dispose();
//    }
//
//    private static Mat loadTemplateImage(String path) {
//        Mat image = imread(path, IMREAD_GRAYSCALE);
//        if (image.empty()) {
//            System.err.println("Could not load template image!");
//            return null;
//        }
//        return image;
//    }
//
//    private static void processVideoFrames(FFmpegFrameGrabber grabber, SIFT sift, BFMatcher matcher, Mat templateDescriptors, CanvasFrame canvas) throws Exception {
//
//        OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat();
//        Frame frame;
//
//        while ((frame = grabber.grab()) != null) {
//            Mat frameMat = converter.convert(frame);
//            if (frameMat == null || frameMat.empty()) continue;
//
//            resize(frameMat, frameMat, size);
//
//            Mat gray = new Mat();
//            cvtColor(frameMat, gray, COLOR_BGR2GRAY);
//
//            KeyPointVector frameKeypoints = new KeyPointVector();
//            Mat frameDescriptors = new Mat();
//            sift.detectAndCompute(gray, new Mat(), frameKeypoints, frameDescriptors);
//
//            if (!frameDescriptors.empty()) {
//                LinkedList<Point2f> clusteredPoints = findClusteredMatchPoints(matcher, templateDescriptors, frameDescriptors, frameKeypoints);
////                drawROI(frameMat, clusteredPoints);
//                drawPoint(frameMat, clusteredPoints);
//            }
//
//            canvas.showImage(converter.convert(frameMat));
//        }
//    }
//
//    private static LinkedList<Point2f> findClusteredMatchPoints(BFMatcher matcher, Mat templateDescriptors, Mat frameDescriptors, KeyPointVector frameKeypoints) {
//        DMatchVector matches = new DMatchVector();
//        matcher.match(templateDescriptors, frameDescriptors, matches);
//
//        DMatch[] matchesArray = matches.get();
//        double minDist = Double.MAX_VALUE;
//        for (DMatch m : matchesArray) {
//            minDist = Math.min(minDist, m.distance());
//        }
//
//        LinkedList<Point2f> matchedPoints = new LinkedList<>();
//        for (DMatch m : matchesArray) {
//            if (m.distance() <= Math.max(2 * minDist, 0.02)) {
//                matchedPoints.add(frameKeypoints.get(m.trainIdx()).pt());
//            }
//        }
//
//        return clusterPoints(matchedPoints, CLUSTERING_RADIUS, MIN_NEIGHBORS);
//    }
//
//    private static LinkedList<Point2f> clusterPoints(LinkedList<Point2f> points, double radius, int minNeighbors) {
//        LinkedList<Point2f> result = new LinkedList<>();
//        for (Point2f pt : points) {
//            int neighbors = 0;
//            for (Point2f other : points) {
//                double dx = pt.x() - other.x();
//                double dy = pt.y() - other.y();
//                if (dx * dx + dy * dy <= radius * radius) {
//                    neighbors++;
//                }
//            }
//            if (neighbors >= minNeighbors) {
//                result.add(pt);
//            }
//        }
//        return result;
//    }
//
//    private static void drawROI(Mat frame, LinkedList<Point2f> points) {
//        if (points.size() < 4) return;
//
//        float minX = Float.MAX_VALUE, minY = Float.MAX_VALUE;
//        float maxX = Float.MIN_VALUE, maxY = Float.MIN_VALUE;
//
//        for (Point2f pt : points) {
//            float x = pt.x(), y = pt.y();
//            if (x < minX) minX = x;
//            if (y < minY) minY = y;
//            if (x > maxX) maxX = x;
//            if (y > maxY) maxY = y;
//        }
//
//        Rect roi = new Rect((int) minX, (int) minY, (int) (maxX - minX), (int) (maxY - minY));
//        rectangle(frame, roi, Scalar.RED, 2, LINE_8, 0);
//    }
//
//    private static void drawPoint(Mat frame, LinkedList<Point2f> points) {
//        if (points.size() < 4) return;
//
//        float sumX = 0, sumY = 0;
//        for (Point2f pt : points) {
//            sumX += pt.x();
//            sumY += pt.y();
//        }
//
//        int centerX = Math.round(sumX / points.size());
//        int centerY = Math.round(sumY / points.size());
//
//        int radius = 5;
//        circle(frame, new Point(centerX, centerY), radius, Scalar.GREEN, FILLED, LINE_8, 0);
//    }
//
//
//
//
//}
//
