package knu.app;


import knu.app.postprocessors.FPSOverlayPostprocessorValue;
import knu.app.postprocessors.FramePostprocessor;
import knu.app.postprocessors.FramePostprocessorValue;
import knu.app.preprocessors.*;
import knu.app.utils.displayer.FrameDisplayer;
import knu.app.utils.video.NativeFFmpegVideoSource;
import knu.app.utils.video.VideoSource;
import knu.app.utils.displayer.SwingFrameDisplayer;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.opencv_core.Mat;


public class CannyEdgeApp {
    public static void main(String[] args) throws Exception {
        String inputFile = "/home/bedu/Документы/input.mp4";
        opencv_core.setNumThreads(0);

        FramePreprocessor gray = new GrayColorPreprocessor();
        FramePreprocessor blur = new BlurPreprocessor(1.5);
        FramePreprocessor canny = new CannyPreprocessor(25, 35);


        FramePreprocessor kmean = new KMeansPreprocessor(6);

        FramePostprocessorValue<Long> fpsPostProcessor = new FPSOverlayPostprocessorValue();
//        FrameDisplayer displayer = new CanvasDisplayer("", 0.5);
        FrameDisplayer displayer = new SwingFrameDisplayer();

        VideoSource reader = new NativeFFmpegVideoSource(1920,1080 ,inputFile, 30, 3);

        OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat();


        Mat mat;

        try {
            Frame frame;

            reader.start();

            while (((frame = reader.grab()) != null)) {
                mat = converter.convert(frame);
                if (mat == null) continue;

                fpsPostProcessor.setValue(System.nanoTime());

                mat = gray.process(mat);
                mat = blur.process(mat);
                mat = canny.process(mat);
//                mat = kmean.process(mat);
                mat = fpsPostProcessor.process(mat);

                displayer.show(converter.convert(mat));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reader.stop();
            displayer.close();
        }

    }
}

