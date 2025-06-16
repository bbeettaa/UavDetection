package knu.app.bll.utils;

import knu.app.bll.ObjectTrackerFactory;
import knu.app.bll.buffers.BufferElement;
import knu.app.bll.buffers.BufferableQueue;
import knu.app.bll.buffers.OverwritingQueueBlockedFrameBuffer;
import knu.app.bll.events.EventModelListener;
import knu.app.bll.mot.AssociationAlgorithm;
import knu.app.bll.mot.HungarianIoUAssociationJGraphT;
import knu.app.bll.mot.TrackingManager;
import knu.app.bll.preprocessors.*;
import knu.app.bll.processors.tracker.CSRTTracker;
import knu.app.bll.processors.tracker.KalmanObjectTracker;
import knu.app.bll.processors.tracker.MilTracker;
import knu.app.bll.processors.tracker.OpticalFlowTracker;
import knu.app.bll.utils.grabbers.PlaybackControlFFmpegFrameGrabberVideoSource;
import knu.app.bll.utils.grabbers.PlaybackControlVideoSource;
import knu.app.bll.utils.processors.hog.HogSvmDetectorConfig;
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
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class PipelineInitializer {
    private static final Logger logger = Logger.getLogger(PipelineInitializer.class.getName());
    private final ExecutorService executor;
    private final OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat();
    private final List<UIModule<?>> uiModules = new ArrayList<>();
    private StatisticDisplayUI stat = StatisticDisplayUI.getEntity();

    private MetricsUIModule metrics = new MetricsUIModule();

    public PipelineInitializer(int bufferCapacity, Mat singleDescriptor, String hogDescriptorFile, HogSvmDetectorConfig hogSvmDetectorConfig) {
        int th1 = 3;
        int th2 = Runtime.getRuntime().availableProcessors() - th1;
        executor = Executors.newFixedThreadPool(th1);
        opencv_core.setNumThreads(th2);

        initModules(bufferCapacity, singleDescriptor, hogDescriptorFile, hogSvmDetectorConfig);
        registrationFactoryTrackers();
    }

    private static void registrationFactoryTrackers() {
        ObjectTrackerFactory trackerFactory = ObjectTrackerFactory.getInstance();
        trackerFactory.registry(ObjectTrackerFactory.TrackerType.KALMAN.name(), KalmanObjectTracker::new);
        trackerFactory.registry(ObjectTrackerFactory.TrackerType.OPTICALFLOW.name(), OpticalFlowTracker::new);
        trackerFactory.registry(ObjectTrackerFactory.TrackerType.CSRT.name(), CSRTTracker::new);
        trackerFactory.registry(ObjectTrackerFactory.TrackerType.MIL.name(), MilTracker::new);
    }

    private void initModules(int bufferCapacity, Mat descriptorFile, String hogDescriptorFile, HogSvmDetectorConfig hogSvmDetectorConfig) {
        BufferableQueue<MatWrapper> frameReaderBuffer = new OverwritingQueueBlockedFrameBuffer<>(bufferCapacity);
        BufferableQueue<MatWrapper> processingBuffer = new OverwritingQueueBlockedFrameBuffer<>(bufferCapacity);
        BufferableQueue<MatWrapper> frameWriterBuffer = new OverwritingQueueBlockedFrameBuffer<>(bufferCapacity);

//        PlaybackControlVideoSource videoSource = new PlaybackFFmpegRawVideoSource();
        PlaybackControlVideoSource videoSource = new PlaybackControlFFmpegFrameGrabberVideoSource();
        PureVideoGrabber videoGrabber = new PureVideoGrabber(videoSource);
        VideoRenderer videoRenderer = new VideoRenderer();


        AssociationAlgorithm associationAlgorithm = new HungarianIoUAssociationJGraphT(0.2);
        TrackingManager trackingManager = new TrackingManager(associationAlgorithm);
        EventModelListener stopVideoListener = trackingManager::reset;
        videoSource.addListener(stopVideoListener);

        UIModule<MatWrapper> preprocessingUi = new PreprocessorUiModule(
                new GrayColorPreprocessor(),
                new BlurPreprocessor(),
                new CannyPreprocessor(),
                new FrameSizerPreprocessor(1920, 1080),
                new StabilizationFramePreprocessor(),
                new FeatureBasedStabilizer()
        );

        UIModule<MatWrapper> processingUi = new ProcessingModule(trackingManager, descriptorFile, hogDescriptorFile, hogSvmDetectorConfig, metrics.getEvaluator());

        createPipeline(videoGrabber, frameReaderBuffer, frameWriterBuffer, videoRenderer,
                preprocessingUi, processingBuffer, processingUi);


        // Main menu
        MainMenuUI mainMenu = new MainMenuUI(
                new MainMenuSection(LocalizationManager.tr("menu.section.instruments.name"),
                        new ToggleMenuSection(videoGrabber),
                        new ToggleMenuSection(videoRenderer),
                        new ToggleMenuSection(preprocessingUi),
                        new ToggleMenuSection(processingUi)
                ),
                new MainMenuSection(LocalizationManager.tr("menu.section.statistics.name"),
                        new StatisticMenuSection(stat),
                        new StatisticMenuSection(metrics)
                )
        );

        uiModules.add(new DockSpaceUIModule(VideoRenderer.VEDIO_OUTPUT_ID, ProcessingModule.PROCESSOR_ID, PureVideoGrabber.GRABBER_ID));
        uiModules.add(videoRenderer);
        uiModules.add(videoGrabber);
        uiModules.add(mainMenu);
        uiModules.add(stat);
        uiModules.add(metrics);
        uiModules.add(preprocessingUi);
        uiModules.add(processingUi);
    }

    private void createPipeline(PureVideoGrabber videoGrabber, BufferableQueue<MatWrapper> frameReaderBuffer, BufferableQueue<MatWrapper> frameWriterBuffer,
                                UIModule<Frame> videoRenderer, UIModule<MatWrapper> preprocessingUi, BufferableQueue<MatWrapper> processingBuffer,
                                UIModule<MatWrapper> processingUi) {

        executor.submit(() -> {
            while (!Thread.interrupted()) {
                MatWrapper m = grabFrameAndPut(videoGrabber);
                preprocessFrameFromAndPut(m, preprocessingUi, processingBuffer);
            }
        });

//        executor.submit(() -> {
//            while (!Thread.interrupted()) {
//                preprocessFrameFromAndPut(frameReaderBuffer, preprocessingUi, processingBuffer);
//            }
//        });

        executor.submit(() -> {
            while (!Thread.interrupted()) {
                processFrameFromAndPut(frameWriterBuffer, processingBuffer, processingUi);
            }
        });

        executor.submit(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                renderFrameFromBuffer(frameWriterBuffer, videoRenderer);
            }
        });
    }


    private MatWrapper grabFrameAndPut(PureVideoGrabber videoGrabber) {
        try {
            Frame frame = videoGrabber.execute(null);
            if (frame != null) {
                long indx = videoGrabber.getCurrentFrameIndex();
                Mat mat = converter.convert(frame);
                MatWrapper matWrapper = new MatWrapper(indx, mat);
                return matWrapper;
            }
        } catch (Exception e) {
            logger.warning(Arrays.toString(e.getStackTrace()));
        }
        return null;
    }

    private void grabFrameAndPut(PureVideoGrabber videoGrabber, BufferableQueue<MatWrapper> frameReaderBuffer) {
        try {
            Frame frame = videoGrabber.execute(null);
            MatWrapper matWrapper = new MatWrapper(videoGrabber.getCurrentFrameIndex(), converter.convert(frame));
            if (frame != null) {
                frameReaderBuffer.put(new BufferElement<>(matWrapper));
            }
        } catch (Exception e) {
            logger.warning(Arrays.toString(e.getStackTrace()));
        }
    }

    private void preprocessFrameFromAndPut(MatWrapper o, UIModule<MatWrapper> preprocessingUi, BufferableQueue<MatWrapper> processingBuffer) {
        try {
            if (o != null) {
                stat.setCurrentTime();
//                MatWrapper f = o;
//                f = preprocessingUi.execute(f);
                preprocessingUi.execute(o);
                processingBuffer.put(new BufferElement<>(o));
            }
        } catch (Exception e) {
            logger.warning(Arrays.toString(e.getStackTrace()));
        }
    }

    private void preprocessFrameFromAndPut(BufferableQueue<MatWrapper> frameReaderBuffer, UIModule<MatWrapper> preprocessingUi, BufferableQueue<MatWrapper> processingBuffer) {
        try {
            BufferElement<MatWrapper> o = frameReaderBuffer.get();
            if (o != null) {
                stat.setCurrentTime();
                MatWrapper f = o.getData();
                f = preprocessingUi.execute(f);
                processingBuffer.put(new BufferElement<>(f));
            }
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    private void processFrameFromAndPut(BufferableQueue<MatWrapper> frameWriterBuffer, BufferableQueue<MatWrapper> processingBuffer, UIModule<MatWrapper> processingUi) {
        try {
            BufferElement<MatWrapper> o = processingBuffer.get();
            if (o != null && o.getData() != null) {
                Mat originalMat = o.getData().mat();
               if(originalMat != null){
                   Mat f = new Mat();
//                try (Mat m = o.getData().mat()) {
//                    m.copyTo(f);
//                    var res = processingUi.execute(new MatWrapper(o.getTimestamp(), f));
//                    frameWriterBuffer.put(new BufferElement<>(res));
//                }
                   MatWrapper copy = new MatWrapper(o.getData().frameIndex(), f);
//                try {/
                   originalMat.copyTo(f);
                   var result = processingUi.execute(copy);
                   frameWriterBuffer.put(new BufferElement<>(result));
//                } catch (Exception)
               }

            }
        } catch (Exception e) {
            logger.warning("Exception in processFrameFromAndPut: " + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
    }


    private void renderFrameFromBuffer(BufferableQueue<MatWrapper> frameWriterBuffer, UIModule<Frame> videoRenderer) {
        try {
            BufferElement<MatWrapper> element = frameWriterBuffer.get();
            if (element != null) {
                Frame fr = converter.convert(element.getData().mat());
                videoRenderer.execute(fr);
                stat.processFPS();
            }
        } catch (Exception e) {
            logger.warning(Arrays.toString(e.getStackTrace()));
        }
    }

    public List<UIModule<?>> getUiModules() {
        return uiModules;
    }
}
