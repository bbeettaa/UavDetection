package knu.app;

import knu.app.bll.buffers.BufferElement;
import knu.app.bll.buffers.Bufferable;
import knu.app.bll.buffers.OverwritingQueueBlockedFrameBuffer;
import knu.app.bll.preprocessors.BlurPreprocessor;
import knu.app.bll.preprocessors.CannyPreprocessor;
import knu.app.bll.preprocessors.FrameSizerPreprocessor;
import knu.app.bll.preprocessors.GrayColorPreprocessor;
import knu.app.bll.utils.LocalizationManager;
import knu.app.bll.utils.grabbers.PlaybackControlVideoSource;
import knu.app.bll.utils.grabbers.PlaybackFFmpegRawVideoSource;
import knu.app.ui.DockSpaceUIModule;
import knu.app.ui.MainMenuUI;
import knu.app.ui.menu.MainMenuSection;
import knu.app.ui.menu.StatisticMenuSection;
import knu.app.ui.menu.ToggleMenuSection;
import knu.app.ui.modules.*;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.opencv_core.Mat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.bytedeco.opencv.global.opencv_imgcodecs.IMREAD_GRAYSCALE;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;

public class PipelineInitializer {
    private final ExecutorService executor;
    private final OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat();
    private final List<UIModule<?>> uiModules = new ArrayList<>();

    public PipelineInitializer(String descriptorFile, String hogDescriptorFile) {
        int th1 = 4;
        int th2 = Runtime.getRuntime().availableProcessors() - th1;
        executor = Executors.newFixedThreadPool(th1);
        opencv_core.setNumThreads(th2);

        initModules(descriptorFile, hogDescriptorFile);
    }

    private void initModules(String descriptorFile, String hogDescriptorFile) {
        Bufferable<Mat> frameReaderBuffer = new OverwritingQueueBlockedFrameBuffer<>(4);
        Bufferable<Mat> processingBuffer = new OverwritingQueueBlockedFrameBuffer<>(4);
        Bufferable<Mat> frameWriterBuffer = new OverwritingQueueBlockedFrameBuffer<>(4);

        PlaybackControlVideoSource videoSource = new PlaybackFFmpegRawVideoSource();
        PureVideoGrabber videoGrabber = new PureVideoGrabber(videoSource);
        VideoRenderer videoRenderer = new VideoRenderer();

        Mat image = imread(descriptorFile, IMREAD_GRAYSCALE);

        UIModule<Mat> preprocessingUi = new PreprocessorUiModule(
                new GrayColorPreprocessor(),
                new BlurPreprocessor(),
                new CannyPreprocessor(),
                new FrameSizerPreprocessor(1920, 1080)
        );

        UIModule<Mat> processingUi = new ProcessingModule(image, hogDescriptorFile);
        StatisticDisplayUI stat = new StatisticDisplayUI();

        // Main menu
        MainMenuUI mainMenu = new MainMenuUI(
                new MainMenuSection(LocalizationManager.tr("menu.section.instruments.name"),
                        new ToggleMenuSection(videoGrabber),
                        new ToggleMenuSection(videoRenderer),
                        new ToggleMenuSection(preprocessingUi),
                        new ToggleMenuSection(processingUi)
                ),
                new MainMenuSection(LocalizationManager.tr("menu.section.statistics.name"),
                        new StatisticMenuSection(stat)
                )
        );

        createPipeline(videoGrabber, frameReaderBuffer, frameWriterBuffer, videoRenderer,
                preprocessingUi, processingBuffer, processingUi);

        uiModules.add(new DockSpaceUIModule(VideoRenderer.VEDIO_OUTPUT_ID, ProcessingModule.PROCESSOR_ID, PureVideoGrabber.GRABBER_ID));
        uiModules.add(videoRenderer);
        uiModules.add(videoGrabber);
        uiModules.add(mainMenu);
        uiModules.add(stat);
        uiModules.add(preprocessingUi);
        uiModules.add(processingUi);
    }

    private void createPipeline(UIModule<Frame> videoGrabber, Bufferable<Mat> frameReaderBuffer, Bufferable<Mat> frameWriterBuffer,
                                UIModule<Frame> videoRenderer, UIModule<Mat> preprocessingUi, Bufferable<Mat> processingBuffer,
                                UIModule<Mat> processingUi) {

        executor.submit(() -> {
            while (!Thread.interrupted()) {
                Frame frame = videoGrabber.execute(null);
                if (frame != null) {
                    frameReaderBuffer.put(new BufferElement<>(converter.convert(frame)));
                }
            }
        });

        executor.submit(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                BufferElement<Mat> element = frameWriterBuffer.get();
                if (element != null) {
                    Frame fr = converter.convert(element.getData());
                    videoRenderer.execute(fr);
                }
            }
        });

        executor.submit(() -> {
            while (!Thread.interrupted()) {
                try {
                    BufferElement<Mat> o = frameReaderBuffer.get();
                    if (o != null) {
                        Mat f = o.getData();
                        f = preprocessingUi.execute(f);
                        processingBuffer.put(new BufferElement<>(f));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        executor.submit(() -> {
            while (!Thread.interrupted()) {
                try {
                    BufferElement<Mat> o = processingBuffer.get();
                    if (o != null && o.getData() != null) {
                        Mat f = new Mat();
                        o.getData().copyTo(f);
                        f = processingUi.execute(f);
                        frameWriterBuffer.put(new BufferElement<>(f));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public List<UIModule<?>> getUiModules() {
        return uiModules;
    }
}
