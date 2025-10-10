package knu.app.samples;

import knu.app.bll.utils.Utils;
import knu.app.bll.utils.processors.DetectionResult;
import knu.app.bll.processors.detection.ORBObjectDetector;
import knu.app.bll.processors.detection.ObjectDetector;
import knu.app.bll.processors.draw.DetectionRenderer;
import knu.app.bll.processors.draw.KeypointsRenderer;
import knu.app.bll.processors.tracker.MilTracker;
import knu.app.bll.processors.tracker.ObjectTracker;
import knu.app.bll.grabbers.PlaybackFFmpegRawVideoSource;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.opencv_core.*;

import java.util.List;

import static org.bytedeco.opencv.global.opencv_imgcodecs.*;
import static org.bytedeco.opencv.global.opencv_imgproc.*;

public class ObjectDetectionTrackingSample {

    public static void main(String[] args) throws Exception {
        String videoPath = "/home/bedu/Документы/input.mp4";
        String templatePath = "/home/bedu/Документы/drone.jpg";

//        ObjectDetector detector = new SURFObjectDetector(imread(templatePath, IMREAD_GRAYSCALE), 200, 4, 4, true, true);
//        ObjectDetector detector = new SIFTObjectDetector(imread(templatePath, IMREAD_GRAYSCALE));
        ObjectDetector detector = new ORBObjectDetector(imread(templatePath, IMREAD_GRAYSCALE));

//        DetectionRenderer renderer = new ROIRenderer();
//        DetectionRenderer renderer = new CenterPointRenderer();
        DetectionRenderer renderer = new KeypointsRenderer();
        DetectionRenderer trackRenderer = new KeypointsRenderer(10);


//        ObjectTracker tracker = new KalmanObjectTracker();
        ObjectTracker tracker = new MilTracker();
//        ObjectTracker tracker = SimpleTrackers.withKcfTracker();

        PlaybackFFmpegRawVideoSource grabber = new PlaybackFFmpegRawVideoSource(videoPath,30);
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

            DetectionResult result = detector.detect(mat);
            for(Rect r : result.getRects()) {
                List<Point2f> trackedPoints = tracker.update(mat, Utils.rectToPoints(r));
                trackRenderer.render(mat, trackedPoints, Scalar.YELLOW, 4, -1);
            }
            renderer.render(mat, result.getRects(), result.getScores(), true);

            canvas.showImage(converter.convert(mat));
        }

        grabber.stop();
        canvas.dispose();
    }



}
