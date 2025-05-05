package knu.app;


import knu.app.bll.buffers.BufferElement;
import knu.app.bll.buffers.Bufferable;
import knu.app.bll.buffers.OverwritingQueueBlockedFrameBuffer;
import knu.app.bll.postprocessors.FPSOverlayPostprocessorValue;
import knu.app.bll.postprocessors.FramePostprocessorValue;
import knu.app.bll.preprocessors.FramePreprocessor;
import knu.app.bll.preprocessors.KMeansPreprocessor;
import knu.app.bll.utils.displayer.CanvasDisplayer;
import knu.app.bll.utils.displayer.FrameDisplayer;
import knu.app.bll.utils.grabbers.PlaybackFFmpegRawVideoSource;
import knu.app.bll.utils.grabbers.VideoSource;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.opencv_core.Mat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class PipelinesDemoSample {
    public static void main(String[] args) {
        String inputFile = "input.mp4";
        opencv_core.setNumThreads(0);


        FramePostprocessorValue<Long> fpsPostprocessor = new FPSOverlayPostprocessorValue();

        VideoSource reader = new PlaybackFFmpegRawVideoSource(inputFile, 60, 3);
        OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat();

        Bufferable<Mat> frameReaderBuffer = new OverwritingQueueBlockedFrameBuffer<>(20);
        Bufferable<Mat> frameWriterBuffer = new OverwritingQueueBlockedFrameBuffer<>( 20);

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
                throw new RuntimeException(e);
            }
        };
    }

    private static Runnable createFrameWriteThread(Bufferable<Mat> frameWriterBuffer, FramePostprocessorValue<Long> fpsPostprocessor, OpenCVFrameConverter.ToMat converter) {
        return () -> {

        FrameDisplayer displayer = new CanvasDisplayer("Canny + FPS", 1.0);
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
            FramePreprocessor kmean = new KMeansPreprocessor(3);

            BufferElement<Mat> element;
            Mat mat;
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    element = frameReaderBuffer.get();

                    if (element != null) {
                        mat = element.getData();

                        mat = kmean.process(mat);

                        frameWriterBuffer.put(new BufferElement<>(mat));
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}