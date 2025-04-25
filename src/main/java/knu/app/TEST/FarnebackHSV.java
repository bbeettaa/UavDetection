package knu.app.TEST;

import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.opencv_core.*;

import java.nio.FloatBuffer;

import static org.bytedeco.opencv.global.opencv_core.*;
import static org.bytedeco.opencv.global.opencv_imgproc.*;
import static org.bytedeco.opencv.global.opencv_video.calcOpticalFlowFarneback;

public class FarnebackHSV {
    public static void main(String[] args) throws Exception {
        String videoPath = "/home/bedu/Документы/clip.mp4";
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoPath);
        grabber.start();

        CanvasFrame canvas = new CanvasFrame("Оптичний потік");
        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat();

        Frame prevFrame = grabber.grab();
        Mat prevMat = converter.convert(prevFrame);
        Mat prevGray = new Mat(), nextGray = new Mat(), flow = new Mat();

        cvtColor(prevMat, prevGray, COLOR_BGR2GRAY);
        resize(prevGray, prevGray, new Size(1280, 720));
        prevGray.convertTo(prevGray, CV_32FC1);

        Frame nextFrame;
        while ((nextFrame = grabber.grab()) != null) {
            Mat nextMat = converter.convert(nextFrame);
            cvtColor(nextMat, nextGray, COLOR_BGR2GRAY);

            resize(nextGray, nextGray, new Size(1280, 720));
            nextGray.convertTo(nextGray, CV_32FC1);

            calcOpticalFlowFarneback(prevGray, nextGray, flow, 0.5, 3, 15, 3, 5, 1.2, 0);

            // HSV-візуалізація
            Mat hsv = new Mat(prevGray.size(), CV_8UC3);
            FloatBuffer flowBuf = flow.createBuffer();

            Mat angle = new Mat(prevGray.size(), CV_32FC1);
            Mat magnitude = new Mat(prevGray.size(), CV_32FC1);
            FloatBuffer angleBuf = angle.createBuffer();
            FloatBuffer magBuf = magnitude.createBuffer();

            for (int i = 0; i < angle.rows() * angle.cols(); i++) {
                float dx = flowBuf.get();
                float dy = flowBuf.get();

                float mag = (float) Math.sqrt(dx * dx + dy * dy);
                float ang = (float) Math.atan2(dy, dx); // в радіанах

                magBuf.put(mag);
                angleBuf.put(ang);
            }

            Mat hsvChannels[] = new Mat[3];
            hsvChannels[0] = new Mat(); // Hue (напрямок)
            hsvChannels[1] = new Mat(); // Saturation
            hsvChannels[2] = new Mat(); // Value (швидкість)

            // Перетворити радіани у градуси [0, 180] (OpenCV Hue)
            angle.convertTo(hsvChannels[0], CV_8UC1, 90.0 / Math.PI, 90.0); // [0,180]
            hsvChannels[1] = new Mat(prevGray.size(), CV_8UC1, new Scalar(255)); // постійне
            normalize(magnitude, hsvChannels[2], 0, 255, NORM_MINMAX, CV_8UC1, null); // яскравість

            merge(new MatVector(hsvChannels), hsv);
            Mat bgr = new Mat();
            cvtColor(hsv, bgr, COLOR_HSV2BGR);

            canvas.showImage(converter.convert(bgr));
            nextGray.copyTo(prevGray);
        }

        grabber.stop();
        System.out.println("Done computing optical flow.");
    }
}
