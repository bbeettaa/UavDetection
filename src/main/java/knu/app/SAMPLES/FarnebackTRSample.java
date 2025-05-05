package knu.app.SAMPLES;

import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.opencv_core.*;

import java.nio.FloatBuffer;

import static org.bytedeco.opencv.global.opencv_core.*;
import static org.bytedeco.opencv.global.opencv_imgproc.*;
import static org.bytedeco.opencv.global.opencv_video.*;

public class FarnebackTRSample {
    public static void main(String[] args) throws Exception {
        String videoPath = "/home/bedu/Документы/cam.mp4";
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoPath);
        grabber.start();

        CanvasFrame canvas = new CanvasFrame("FarnebackTR");
        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat();

        Frame nextFrame;
        Frame prevFrame;
        Mat prevMat, nextMat;
        Mat prevGray = new Mat(), nextGray = new Mat(), flow = new Mat();

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

            Mat vis = new Mat(nextMat);

            // Візуалізація векторів
            FloatBuffer flowBuffer = flow.createBuffer();
            int step = 8; // Прорідження поля для візуалізації

            for (int y = 0; y < vis.rows(); y += step) {
                for (int x = 0; x < vis.cols(); x += step) {
                    int index = 2 * (y * vis.cols() + x);
                    float dx = flowBuffer.get(index);
                    float dy = flowBuffer.get(index + 1);

                    Point p1 = new Point(x, y);
                    Point p2 = new Point(Math.round(x + dx), Math.round(y + dy));
                    arrowedLine(vis, p1, p2, Scalar.RED, 1, 32, 0, 0.1);
                }
            }

            canvas.showImage(converter.convert(vis));
            nextGray.copyTo(prevGray);
        }

        grabber.stop();
        System.out.println("Done computing optical flow.");
    }
}
