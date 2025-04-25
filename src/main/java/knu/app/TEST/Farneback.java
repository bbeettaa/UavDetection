package knu.app.TEST;

import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.opencv_core.*;

import static org.bytedeco.opencv.global.opencv_core.*;
import static org.bytedeco.opencv.global.opencv_highgui.imshow;
import static org.bytedeco.opencv.global.opencv_highgui.waitKey;
import static org.bytedeco.opencv.global.opencv_imgproc.*;
import static org.bytedeco.opencv.global.opencv_video.calcOpticalFlowFarneback;
import static org.bytedeco.opencv.global.opencv_video.calcOpticalFlowPyrLK;

import java.nio.FloatBuffer;

public class Farneback {
    public static void main(String[] args) throws Exception {
//        String videoPath = "/home/bedu/Документы/cam.mp4";
        String videoPath = "/home/bedu/Документы/clip.mp4";
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoPath);
        grabber.start();

        CanvasFrame canvas = new CanvasFrame("Farneback");
        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);


        OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat();

        Frame nextFrame;
        Frame prevFrame;
        Mat prevMat, nextMat;
        Mat prevGray = new Mat(), nextGray = new Mat(), flow = new Mat();

        // Перший кадр
        prevFrame = grabber.grab();
        prevMat = converter.convert(prevFrame);
        cvtColor(prevMat, prevGray, COLOR_BGR2GRAY);
        prevGray.convertTo(prevGray, CV_32FC1);


        while ((nextFrame = grabber.grab()) != null) {
            nextMat = converter.convert(nextFrame);
            cvtColor(nextMat, nextGray, COLOR_BGR2GRAY);
            nextGray.convertTo(nextGray, CV_32FC1);

//            resize(prevGray, prevGray, new Size(640, 480));
//            resize(nextGray, nextGray, new Size(640, 480));

            calcOpticalFlowFarneback(prevGray, nextGray, flow, 0.5, 3, 15, 3, 5, 1.2, 0);


            Mat magnitude = new Mat(prevGray.rows(), prevGray.cols(), CV_32FC1);
            FloatBuffer in = flow.createBuffer();
            FloatBuffer out = magnitude.createBuffer();

            for (int y = 0; y < prevGray.rows(); y++) {
                for (int x = 0; x < prevGray.cols(); x++) {
                    float xVel = in.get();
                    float yVel = in.get();
                    float vel = (float) Math.sqrt(xVel * xVel + yVel * yVel);
                    out.put(vel);
                }
            }

            Mat display = new Mat();
            normalize(magnitude, display, 0.0, 255.0, NORM_MINMAX, -1, null);
            display.convertTo(display, CV_8UC1);



            canvas.showImage(converter.convert(display));

            nextGray.copyTo(prevGray);
        }

        grabber.stop();
        System.out.println("Done computing optical flow.");
    }
}
