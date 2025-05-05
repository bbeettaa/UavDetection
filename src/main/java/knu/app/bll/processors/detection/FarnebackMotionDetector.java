package knu.app.bll.processors.detection;

import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Size;

import java.nio.FloatBuffer;
import java.util.LinkedList;

import static org.bytedeco.opencv.global.opencv_core.CV_32FC1;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGR2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.resize;
import static org.bytedeco.opencv.global.opencv_video.calcOpticalFlowFarneback;

public class FarnebackMotionDetector implements ObjectDetector {
    private Mat prevGray = null;
    private double motionThreshold;

    public FarnebackMotionDetector(double motionThreshold) {
        this.motionThreshold = motionThreshold;
    }

    public FarnebackMotionDetector() {
        this.motionThreshold = 0.5;
    }

    @Override
    public DetectionResult detect(Mat frame) {
        // Конвертируем в grayscale
        Mat frameGray = new Mat();
        cvtColor(frame, frameGray, COLOR_BGR2GRAY);

        if (prevGray == null) {
            prevGray = new Mat();
            frameGray.copyTo(prevGray);
            return new DetectionResult();
        }

        // Проверяем размеры и при необходимости приводим к одному размеру
        if (prevGray.rows() != frameGray.rows() || prevGray.cols() != frameGray.cols()) {
            Size newSize = new Size(frameGray.cols(), frameGray.rows());
            resize(prevGray, prevGray, newSize);
        }

        Mat flow = new Mat();
        calcOpticalFlowFarneback(
                prevGray, frameGray, flow,
                0.5,    // pyrScale
                3,      // levels
                15,      // winsize
                3,       // iterations
                5,       // polyN
                1.2,     // polySigma
                0        // flags
        );

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
                if (val >= motionThreshold) {
                    motionPoints.add(new Point2f(x, y));
                }
            }
        }

        // Обновляем предыдущий кадр
        frameGray.copyTo(prevGray);
        return new DetectionResult(motionPoints);
    }

    public void setMotionThreshold(double motionThreshold) {
        this.motionThreshold = motionThreshold;
    }
}