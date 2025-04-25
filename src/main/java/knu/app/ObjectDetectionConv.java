package knu.app;

import knu.app.detection.detection.DetectionResult;
import knu.app.detection.detection.ObjectDetector;
import knu.app.detection.detection.SURFObjectDetector;
import knu.app.detection.draw.DetectionRenderer;
import knu.app.detection.draw.KeypointsRenderer;
import knu.app.detection.tracker.ObjectTracker;
import knu.app.detection.tracker.ObjectTrackerWithKalman;
import knu.app.detection.tracker.SimpleTrackers;
import knu.app.utils.video.NativeFFmpegVideoSource;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.opencv_core.*;

import java.util.LinkedList;

import static org.bytedeco.opencv.global.opencv_imgcodecs.*;
import static org.bytedeco.opencv.global.opencv_imgproc.*;

public class ObjectDetectionConv {

    public static void main(String[] args) throws Exception {
//        String videoPath = "/home/bedu/Документы/cam.mp4";
        String videoPath = "/home/bedu/Документы/cam1c.mp4";
//        String videoPath = "/home/bedu/Документы/clip.mp4";
//        String videoPath = "/home/bedu/Документы/bird.mp4";
        String templatePath = "/home/bedu/Документы/drone.jpg";


        ObjectDetector detector = new SURFObjectDetector(imread(templatePath, IMREAD_GRAYSCALE), 200, 4, 4, true, true);
//        ObjectDetector detector = new SIFTObjectDetector(imread(templatePath, IMREAD_GRAYSCALE));
//        ObjectDetector detector = new ORBObjectDetector(imread(templatePath, IMREAD_GRAYSCALE));

//        DetectionRenderer renderer = new ROIRenderer();
//        DetectionRenderer renderer = new CenterPointRenderer();
        DetectionRenderer renderer = new KeypointsRenderer();
        DetectionRenderer trackRenderer = new KeypointsRenderer(10);


        ObjectTracker tracker = new ObjectTrackerWithKalman();
//        ObjectTracker tracker = SimpleTrackers.withCsrtTracker();
//        ObjectTracker tracker = SimpleTrackers.withMilTracker();

        NativeFFmpegVideoSource grabber = new NativeFFmpegVideoSource(videoPath,30);
        grabber.start();

        CanvasFrame canvas = new CanvasFrame("Object Detection");
        OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat();

        Frame frame;
        while ((frame = grabber.grab()) != null) {
            Mat mat = converter.convert(frame);
            if (mat == null || mat.empty()) continue;

            resize(mat, mat, new Size(1280, 720));
            Mat gray = new Mat();
            cvtColor(mat, gray, COLOR_BGR2GRAY);

            DetectionResult result = detector.detect(gray);

//            if (!trackerInitialized) {
//                tracker.init(mat, result.getPoints());
//                trackerInitialized=true;
//            }

            LinkedList<Point2f> trackedPoints = tracker.track(mat, result.getPoints());

            renderer.render(mat, result.getPoints());
            trackRenderer.render(mat, trackedPoints, Scalar.YELLOW, 4, -1);

            canvas.showImage(converter.convert(mat));
        }

        grabber.stop();
        canvas.dispose();
    }



}
