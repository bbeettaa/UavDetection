package knu.app;


import knu.app.buffers.BufferElement;
import knu.app.buffers.Bufferable;
import knu.app.buffers.OverwritingQueueBlockedFrameBuffer;
import knu.app.postprocessors.FPSOverlayPostprocessorValue;
import knu.app.postprocessors.FramePostprocessorValue;
import knu.app.preprocessors.*;
import knu.app.utils.displayer.FrameDisplayer;
import knu.app.utils.video.NativeFFmpegVideoSource;
import knu.app.utils.video.NativePlaybackFFmpegVideoSource;
import knu.app.utils.video.VideoSource;
import knu.app.utils.displayer.SwingFrameDisplayer;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.opencv_core.Mat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CannyEdgePipeApp {
    public static void main(String[] args) throws Exception {
        String inputFile = "/home/bedu/Документы/input.mp4";
        opencv_core.setNumThreads(0);


        FramePostprocessorValue<Long> fpsPostprocessor = new FPSOverlayPostprocessorValue();

        VideoSource reader = new NativeFFmpegVideoSource(640,480,inputFile, 60, 3);
//        VideoSource reader = new NativePlaybackFFmpegVideoSource(1920,1080,inputFile, 60, 3);
//        FrameReader reader = new GrabberFrameReader(inputFile);
        OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat();

        Bufferable<Mat> frameReaderBuffer = new OverwritingQueueBlockedFrameBuffer<>("reader buff", 100);
        Bufferable<Mat> frameWriterBuffer = new OverwritingQueueBlockedFrameBuffer<>("preprocess buff", 100);

        int availableProcessors = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(availableProcessors);

        executorService.submit(createFrameReaderThread(reader, fpsPostprocessor, converter, frameReaderBuffer));

        executorService.submit(createPrerocessingThread(frameReaderBuffer, frameWriterBuffer));

        executorService.submit(createFrameWriteThread(frameWriterBuffer, fpsPostprocessor, converter));

    }

    private static Runnable createFrameReaderThread(VideoSource reader, FramePostprocessorValue<Long> fpsPostprocessor, OpenCVFrameConverter.ToMat converter, Bufferable<Mat> frameReaderBuffer) {
        return () -> {
            try {
                reader.start();

                Frame frame;
                while (((frame = reader.grab()) != null)) {
                    fpsPostprocessor.setValue(System.nanoTime());
                    Mat mat = converter.convert(frame);
                    if (mat != null) {
                        frameReaderBuffer.put(new BufferElement<>(mat));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        };
    }

    private static Runnable createFrameWriteThread(Bufferable<Mat> frameWriterBuffer, FramePostprocessorValue<Long> fpsPostprocessor, OpenCVFrameConverter.ToMat converter) {
        return () -> {

//        FrameDisplayer displayer = new CanvasDisplayer("Canny + FPS", 1.0);
            FrameDisplayer displayer = new SwingFrameDisplayer();
            try {
                Mat mat;
                while (!Thread.currentThread().isInterrupted()) {
                    BufferElement<Mat> element = frameWriterBuffer.get();
                    if (element != null) {
                        mat = element.getData();
                        mat = fpsPostprocessor.process(mat);
                        displayer.show(converter.convert(mat));
                    }


                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }

    private static Runnable createPrerocessingThread(Bufferable<Mat> frameReaderBuffer, Bufferable<Mat> frameWriterBuffer) {
        return () -> {
            FramePreprocessor gray = new GrayColorPreprocessor();
            FramePreprocessor blur = new BlurPreprocessor(1.5);
            FramePreprocessor canny = new CannyPreprocessor(25, 35);
            FramePreprocessor kmean = new KMeansPreprocessor(6);
            FramePreprocessor framePreprocessor = new FPSLimiterPreprocessor(30);
//            FramePreprocessor framePreprocessor = new FPSLimiterPreprocessor(30);

            BufferElement<Mat> element;
            Mat mat;
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    element = frameReaderBuffer.get();

                    if (element != null) {
                        mat = element.getData();

//                        mat = framePreprocessor.process(mat);
//                        mat = gray.process(mat);
//                        mat = blur.process(mat);
//                        mat = canny.process(mat);
//                        mat = kmean.process(mat);
//                        mat = orb.process(mat);

                        frameWriterBuffer.put(new BufferElement<>(mat));
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}
