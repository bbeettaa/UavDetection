package knu.app.detection.detection;

import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2f;

import java.nio.FloatBuffer;
import java.util.LinkedList;

import static org.bytedeco.opencv.global.opencv_core.CV_32FC1;
import static org.bytedeco.opencv.global.opencv_video.calcOpticalFlowFarneback;

public class FarnebackMotionDetector implements ObjectDetector {
    private Mat prevGray = null;
    private final double motionThreshold;

    public FarnebackMotionDetector(double motionThreshold) {
        this.motionThreshold = motionThreshold;
    }

    @Override
    public DetectionResult detect(Mat frameGray) {
        if (prevGray == null) {
            prevGray = new Mat();
            frameGray.copyTo(prevGray);
            return new DetectionResult();
        }

        Mat flow = new Mat();
        calcOpticalFlowFarneback(prevGray, frameGray, flow, 0.5, 3, 15, 3, 5, 1.2, 0);

        Mat magnitude = new Mat(frameGray.rows(), frameGray.cols(), CV_32FC1);
        FloatBuffer in = flow.createBuffer();
        FloatBuffer out = magnitude.createBuffer();

        for (int y = 0; y < frameGray.rows(); y++) {
            for (int x = 0; x < frameGray.cols(); x++) {
                float xVel = in.get();
                float yVel = in.get();
                float vel = (float) Math.sqrt(xVel * xVel + yVel * yVel);
                out.put(vel);
            }
        }

        LinkedList<Point2f> motionPoints = new LinkedList<>();
        FloatBuffer mag = magnitude.createBuffer();
        for (int y = 0; y < frameGray.rows(); y++) {
            for (int x = 0; x < frameGray.cols(); x++) {
                float val = mag.get();
                if (val > motionThreshold) {
                    motionPoints.add(new Point2f(x, y));
                }
            }
        }

        frameGray.copyTo(prevGray);
        return new DetectionResult(motionPoints);
    }
}
