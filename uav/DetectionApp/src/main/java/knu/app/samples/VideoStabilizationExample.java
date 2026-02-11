package knu.app.samples;

import knu.app.bll.preprocessors.FramePreprocessor;
import knu.app.bll.preprocessors.StabilizationFramePreprocessor;
import knu.app.bll.grabbers.PlaybackFFmpegRawVideoSource;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.opencv_core.Mat;

import static org.bytedeco.opencv.global.opencv_calib3d.estimateAffine2D;
import static org.bytedeco.opencv.global.opencv_video.calcOpticalFlowPyrLK;

public class VideoStabilizationExample {
    public static void main(String[] args) throws Exception {
        String videoPath = "/home/bedu/Документы/Clip_34.mov";
        String templatePath = "/home/bedu/Документы/drone.jpg";

        FramePreprocessor stabilizer = new StabilizationFramePreprocessor();

        PlaybackFFmpegRawVideoSource grabber = new PlaybackFFmpegRawVideoSource(videoPath, 30);
        grabber.start();

        CanvasFrame canvas = new CanvasFrame("Object Detection");
        OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat();

        Frame frame;
        while ((frame = grabber.grab()) != null) {
            Mat mat = converter.convert(frame);
            if (mat == null || mat.empty()) continue;

            Mat stabilized = stabilizer.process(mat);

            canvas.showImage(converter.convert(stabilized));
        }

        grabber.stop();
        canvas.dispose();
    }


}


