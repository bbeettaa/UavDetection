package knu.app.bll.pipeline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import knu.app.Main;
import knu.app.bll.algorithms.associative.AssociationAlgorithm;
import knu.app.bll.algorithms.associative.HungarianIoUAssociationJGraphT;
import knu.app.bll.algorithms.trajectory.TrajectoryManager;
import knu.app.bll.buffers.BufferElement;
import knu.app.bll.buffers.BufferableQueue;
import knu.app.bll.buffers.OverwritingQueueBlockedFrameBuffer;
import knu.app.bll.events.EventModelListener;
import knu.app.bll.grabbers.PlaybackControlFFmpegFrameGrabberVideoSource;
import knu.app.bll.grabbers.PlaybackControlVideoSource;
import knu.app.bll.mot.AssociativeTrackingManager;
import knu.app.bll.mot.ImplementedTrackingManager;
import knu.app.bll.mot.TrackingManager;
import knu.app.bll.preprocessors.BlurPreprocessor;
import knu.app.bll.preprocessors.CannyPreprocessor;
import knu.app.bll.preprocessors.DbscanPreprocessor;
import knu.app.bll.preprocessors.FrameSizerPreprocessor;
import knu.app.bll.preprocessors.GrayColorPreprocessor;
import knu.app.bll.preprocessors.KMeansPreprocessor;
import knu.app.bll.processors.draw.TrajectoryRenderer;
import knu.app.bll.processors.tracker.multi.DeepSortGrpcTracker;
import knu.app.bll.processors.tracker.multi.SortGrpcTracker;
import knu.app.bll.processors.tracker.single.CSRTTracker;
import knu.app.bll.processors.tracker.single.KalmanObjectTracker;
import knu.app.bll.processors.tracker.single.MilTracker;
import knu.app.bll.processors.tracker.single.OpticalFlowTracker;
import knu.app.bll.utils.LocalizationManager;
import knu.app.bll.utils.MatWrapper;
import knu.app.bll.utils.processors.hog.HogSvmDetectorConfig;
import knu.app.bll.utils.registry.MultiObjectTrackerFactory;
import knu.app.bll.utils.registry.MultiObjectTrackerFactory.TrackerType;
import knu.app.bll.utils.registry.ObjectTrackerFactory;
import knu.app.ui.DockSpaceUIModule;
import knu.app.ui.MainMenuUI;
import knu.app.ui.menu.MainMenuSection;
import knu.app.ui.menu.StatisticMenuSection;
import knu.app.ui.menu.ToggleMenuSection;
import knu.app.ui.modules.AnalyticsUIModule;
import knu.app.ui.modules.CurrentObjectsUIModule;
import knu.app.ui.modules.MetricsUIModule;
import knu.app.ui.modules.PipelineControlUI;
import knu.app.ui.modules.PreprocessorUiModule;
import knu.app.ui.modules.ProcessingModule;
import knu.app.ui.modules.PureVideoGrabber;
import knu.app.ui.modules.StatisticDisplayUI;
import knu.app.ui.modules.UIModule;
import knu.app.ui.modules.VideoRenderer;
import knu.app.ui.modules.VideoSaverUIModule;
import knu.app.ui.processings.renders.TrajectoryRendererUI;
import knu.app.ui.processings.trackers.DeepSortTrackerUi;
import knu.app.ui.processings.trackers.SortTrackerUi;
import knu.app.ui.processings.trackers.TrackerUI;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.opencv_core.Mat;

public class Pipeline {

    private static final Logger logger = Logger.getLogger(Pipeline.class.getName());

    // Separate executors for different task types
    private final ExecutorService ioExecutor = Executors.newCachedThreadPool(); // For IO-heavy: grab, render, save
    private final ExecutorService cpuExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()); // For CPU-heavy: preprocess, processing

    private final List<Future<?>> grabTasks = new ArrayList<>();
    private final List<Future<?>> preprocessTasks = new ArrayList<>();
    private final List<Future<?>> processingTasks = new ArrayList<>();
    private final List<Future<?>> renderTasks = new ArrayList<>();
    private final Map<String, Object> pipelineComponents = new HashMap<>();
    private final OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat();
    private final List<UIModule<?>> uiModules = new ArrayList<>();
    private final StatisticDisplayUI stat = StatisticDisplayUI.getEntity();
    private final MetricsUIModule metrics = new MetricsUIModule();
    TrajectoryManager trajectoryManager = new TrajectoryManager(60);
    private final CurrentObjectsUIModule currentObjectsUIModule = new CurrentObjectsUIModule(trajectoryManager);
    private volatile int currentGrabThreads = 1; // IO-heavy, keep at 1 as source may not be thread-safe
    private volatile int currentPreprocessThreads = Runtime.getRuntime().availableProcessors() / 2; // CPU-heavy, adjust based on cores
    private volatile int currentProcessingThreads = 1; // CPU/network-heavy (gRPC), start with 1 to avoid server overload
    private PureVideoGrabber videoGrabber;
    private UIModule<MatWrapper> preprocessingUi;
    private UIModule<MatWrapper> processingUi;
    private UIModule<Frame> videoRenderer;
    private VideoSaverUIModule videoSaverUIModule;

    // Buffers - use small capacity to reduce latency (e.g., 5 instead of large values)
    // Large bufferCapacity can cause lag = bufferCapacity / FPS seconds
    private BufferableQueue<MatWrapper> frameReaderBuffer;
    private BufferableQueue<MatWrapper> processingBuffer;
    private BufferableQueue<MatWrapper> frameWriterBuffer;

    public Pipeline(int bufferCapacity, Mat singleDescriptor, String hogDescriptorFile,
                    HogSvmDetectorConfig hogSvmDetectorConfig) {
        registrationFactoryTrackers();

        // Suggest using small bufferCapacity (e.g., 5-10) to minimize latency
        // If latency is 5s, and FPS=30, bufferCapacity ~150 would cause it; reduce it
        initModules(bufferCapacity, singleDescriptor, hogDescriptorFile, hogSvmDetectorConfig);

        // Warm-up the processing UI (e.g., YOLO gRPC) with a dummy frame to avoid initial delay
        warmUpProcessing();
    }

    private void warmUpProcessing() {
        try {
            // Create a small dummy Mat (e.g., 1x1)
            Mat dummyMat = new Mat(1, 1, org.bytedeco.opencv.global.opencv_core.CV_8UC3);
            MatWrapper dummyWrapper = new MatWrapper(0, dummyMat);
            processingUi.execute(dummyWrapper);
            logger.info("Processing UI warmed up with dummy frame.");
        } catch (Exception e) {
            logger.warning("Failed to warm up processing: " + e.getMessage());
        }
    }

    private static void registrationFactoryTrackers() {
        ObjectTrackerFactory trackerFactory = ObjectTrackerFactory.getInstance();
        trackerFactory.registry(ObjectTrackerFactory.TrackerType.KALMAN.name(),
                KalmanObjectTracker::new);
        trackerFactory.registry(ObjectTrackerFactory.TrackerType.OPTICALFLOW.name(),
                OpticalFlowTracker::new);
        trackerFactory.registry(ObjectTrackerFactory.TrackerType.CSRT.name(), CSRTTracker::new);
        trackerFactory.registry(ObjectTrackerFactory.TrackerType.MIL.name(), MilTracker::new);
    }

    private void initModules(int bufferCapacity, Mat singleDescriptor, String hogDescriptorFile,
                             HogSvmDetectorConfig hogSvmDetectorConfig) {
        frameReaderBuffer = new OverwritingQueueBlockedFrameBuffer<>(bufferCapacity);
        processingBuffer = new OverwritingQueueBlockedFrameBuffer<>(bufferCapacity);
        frameWriterBuffer = new OverwritingQueueBlockedFrameBuffer<>(bufferCapacity);

        TrajectoryRenderer renderer = new TrajectoryRenderer(trajectoryManager);

        PlaybackControlVideoSource videoSource = new PlaybackControlFFmpegFrameGrabberVideoSource();
        videoGrabber = new PureVideoGrabber(videoSource);
        videoRenderer = new VideoRenderer();

        AnalyticsUIModule analyticsUIModule = new AnalyticsUIModule(trajectoryManager);
        TrajectoryRendererUI trajectoryRendererUI = new TrajectoryRendererUI(renderer);

        List<TrackerUI> trackers = new ArrayList<>();

        SortGrpcTracker sortGrpcTracker = new SortGrpcTracker(Main.HOST, Main.SORT_PORT);
        MultiObjectTrackerFactory.getInstance().registry(TrackerType.SORT.name(), sortGrpcTracker);
        trackers.add(new SortTrackerUi(sortGrpcTracker));

        DeepSortGrpcTracker deepSortGrpcTracker = new DeepSortGrpcTracker(Main.HOST, Main.DEEPSORT_PORT);
        MultiObjectTrackerFactory.getInstance().registry(TrackerType.DEEPSORT.name(), deepSortGrpcTracker);
        trackers.add(new DeepSortTrackerUi(deepSortGrpcTracker));

        TrackingManager multiTrackingManager = new ImplementedTrackingManager(
                LocalizationManager.tr("processor.tracker.manager.name2"), trackers);

        AssociationAlgorithm associationAlgorithm = new HungarianIoUAssociationJGraphT(0.2);
        TrackingManager trackingManager = new AssociativeTrackingManager(associationAlgorithm,
                LocalizationManager.tr("processor.tracker.manager.name1"));
        EventModelListener stopVideoListener = trackingManager::reset;
        videoSource.addListener(stopVideoListener);

        this.videoSaverUIModule = new VideoSaverUIModule(stat);

        preprocessingUi = new PreprocessorUiModule(
                new GrayColorPreprocessor(),
                new BlurPreprocessor(),
                new CannyPreprocessor(),
                new FrameSizerPreprocessor(1920, 1080),
                new KMeansPreprocessor(2),
                new DbscanPreprocessor()
        );

        List<TrackingManager> trackingManagers = List.of(trackingManager, multiTrackingManager);

        processingUi = new ProcessingModule(trackingManagers, singleDescriptor,
                hogDescriptorFile, hogSvmDetectorConfig, metrics.getEvaluator(), analyticsUIModule,
                trajectoryRendererUI);

        createPipeline(videoGrabber, frameReaderBuffer, frameWriterBuffer, videoRenderer,
                preprocessingUi, processingBuffer, processingUi);

        // Main menu
        MainMenuUI mainMenu = new MainMenuUI(
                new MainMenuSection(LocalizationManager.tr("menu.section.instruments.name"),
                        new ToggleMenuSection(videoGrabber),
                        new ToggleMenuSection(videoRenderer),
                        new ToggleMenuSection(preprocessingUi),
                        new ToggleMenuSection(processingUi),
                        new ToggleMenuSection(analyticsUIModule),
                        new ToggleMenuSection(videoSaverUIModule)
                ),
                new MainMenuSection(LocalizationManager.tr("menu.section.statistics.name"),
                        new StatisticMenuSection(stat),
                        new ToggleMenuSection(currentObjectsUIModule)
                )
        );

        uiModules.add(
                new DockSpaceUIModule(VideoRenderer.VEDIO_OUTPUT_ID, ProcessingModule.PROCESSOR_ID,
                        PureVideoGrabber.GRABBER_ID));
        uiModules.add(videoRenderer);
        uiModules.add(videoGrabber);
        uiModules.add(mainMenu);
        uiModules.add(stat);
        uiModules.add(currentObjectsUIModule);
        uiModules.add(preprocessingUi);
        uiModules.add(processingUi);
        uiModules.add(analyticsUIModule);
        uiModules.add(videoSaverUIModule);
    }

    private void createPipeline(PureVideoGrabber videoGrabber,
                                BufferableQueue<MatWrapper> frameReaderBuffer, BufferableQueue<MatWrapper> frameWriterBuffer,
                                UIModule<Frame> videoRenderer, UIModule<MatWrapper> preprocessingUi,
                                BufferableQueue<MatWrapper> processingBuffer,
                                UIModule<MatWrapper> processingUi) {
        this.videoGrabber = videoGrabber;
        this.preprocessingUi = preprocessingUi;
        this.processingUi = processingUi;
        this.videoRenderer = videoRenderer;
        this.frameReaderBuffer = frameReaderBuffer;
        this.processingBuffer = processingBuffer;
        this.frameWriterBuffer = frameWriterBuffer;

        startGrabThreads();
        startPreprocessThreads();
        startProcessingThreads();
        startRenderThread();
    }

    private synchronized void grabAndPutToBuffer(BufferableQueue<MatWrapper> buffer) {
        long start = System.currentTimeMillis();
        try {
            Frame frame = videoGrabber.execute(null);
            if (frame != null) {
                MatWrapper matWrapper = new MatWrapper(videoGrabber.getCurrentFrameIndex(),
                        converter.convert(frame));
                buffer.put(new BufferElement<>(matWrapper));
            }
        } catch (Exception e) {
            logger.warning(Arrays.toString(e.getStackTrace()));
        }
        long end = System.currentTimeMillis();
        if (end - start > 100) { // Log if >100ms
            logger.info("Grab time: " + (end - start) + "ms");
        }
    }

    private void preprocessFrameFromAndPut(BufferableQueue<MatWrapper> inputBuffer,
                                           UIModule<MatWrapper> preprocessingUi, BufferableQueue<MatWrapper> outputBuffer) {
        long start = System.currentTimeMillis();
        try {
            BufferElement<MatWrapper> element = inputBuffer.get();
            if (element != null) {
                MatWrapper input = element.getData();
                MatWrapper output = preprocessingUi.execute(input);
                outputBuffer.put(new BufferElement<>(output));
            }
        } catch (Exception e) {
            logger.warning(Arrays.toString(e.getStackTrace()));
        }
        long end = System.currentTimeMillis();
        if (end - start > 100) {
            logger.info("Preprocess time: " + (end - start) + "ms");
        }
    }

    private void processFrameFromAndPut(BufferableQueue<MatWrapper> outputBuffer,
                                        BufferableQueue<MatWrapper> inputBuffer, UIModule<MatWrapper> processingUi,
                                        VideoSaverUIModule videoSaverUIModule) {
        long start = System.currentTimeMillis();
        try {
            BufferElement<MatWrapper> element = inputBuffer.get();
            if (element != null && element.getData() != null) {
                Mat originalMat = element.getData().mat();
                if (originalMat != null) {
                    Mat f = originalMat.clone(); // Use clone() for efficiency instead of new + copyTo
                    MatWrapper copy = new MatWrapper(element.getData().frameIndex(), f);
                    MatWrapper result = processingUi.execute(copy);
                    videoSaverUIModule.execute(result);
                    outputBuffer.put(new BufferElement<>(result));
                }
            }
        } catch (Exception e) {
            logger.warning("Exception in processFrameFromAndPut: " + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
        long end = System.currentTimeMillis();
        if (end - start > 100) {
            logger.info("Processing time: " + (end - start) + "ms");
        }
    }

    private void renderFrameFromBuffer(BufferableQueue<MatWrapper> inputBuffer,
                                       UIModule<Frame> videoRenderer) {
        long start = System.currentTimeMillis();
        try {
            BufferElement<MatWrapper> element = inputBuffer.get();
            if (element != null) {
                Frame frame = converter.convert(element.getData().mat());
                videoRenderer.execute(frame);
                stat.processFPS();
                stat.updateLatency();
            }
        } catch (Exception e) {
            logger.warning(Arrays.toString(e.getStackTrace()));
        }
        long end = System.currentTimeMillis();
        if (end - start > 100) {
            logger.info("Render time: " + (end - start) + "ms");
        }
    }

    public List<UIModule<?>> getUiModules() {
        return uiModules;
    }

    private synchronized void startGrabThreads() {
        stopGrabThreads();

        for (int i = 0; i < currentGrabThreads; i++) {
            grabTasks.add(ioExecutor.submit(() -> {
                while (!Thread.interrupted()) {
                    stat.checkMs();
                    grabAndPutToBuffer(frameReaderBuffer);
                }
            }));
        }
        logger.info("Started Grab tasks. Count: " + currentGrabThreads);
    }

    private synchronized void startPreprocessThreads() {
        stopPreprocessThreads();

        for (int i = 0; i < currentPreprocessThreads; i++) {
            preprocessTasks.add(cpuExecutor.submit(() -> {
                while (!Thread.interrupted()) {
                    preprocessFrameFromAndPut(frameReaderBuffer, preprocessingUi, processingBuffer);
                }
            }));
        }
        logger.info("Started Preprocess tasks. Count: " + currentPreprocessThreads);
    }

    private synchronized void startProcessingThreads() {
        stopProcessingThreads();

        for (int i = 0; i < currentProcessingThreads; i++) {
            processingTasks.add(cpuExecutor.submit(() -> {
                while (!Thread.interrupted()) {
                    processFrameFromAndPut(frameWriterBuffer, processingBuffer, processingUi, videoSaverUIModule);
                }
            }));
        }
        logger.info("Started Processing tasks. Count: " + currentProcessingThreads);
    }

    private synchronized void startRenderThread() {
        stopRenderThread();

        renderTasks.add(ioExecutor.submit(() -> {
            while (!Thread.interrupted()) {
                renderFrameFromBuffer(frameWriterBuffer, videoRenderer);
            }
        }));
        logger.info("Started Render thread. Count: 1");
    }

    private synchronized void stopGrabThreads() {
        for (Future<?> task : grabTasks) {
            task.cancel(true);
        }
        grabTasks.clear();
        logger.info("Stopped all grab tasks.");
    }

    private synchronized void stopPreprocessThreads() {
        for (Future<?> task : preprocessTasks) {
            task.cancel(true);
        }
        preprocessTasks.clear();
        logger.info("Stopped all preprocess tasks.");
    }

    private synchronized void stopProcessingThreads() {
        for (Future<?> task : processingTasks) {
            task.cancel(true);
        }
        processingTasks.clear();
        logger.info("Stopped all processing tasks.");
    }

    private synchronized void stopRenderThread() {
        for (Future<?> task : renderTasks) {
            task.cancel(true);
        }
        renderTasks.clear();
        logger.info("Stopped render thread.");
    }

    public int getGrabThreadCount() {
        return currentGrabThreads;
    }

    public synchronized void setGrabThreadCount(int newCount) {
        if (newCount > 0 && newCount != currentGrabThreads) {
            if (newCount > 1) {
                logger.warning("Multiple grab threads may cause issues if video source is not thread-safe.");
            }
            currentGrabThreads = newCount;
            startGrabThreads();
        }
    }

    public int getPreprocessThreadCount() {
        return currentPreprocessThreads;
    }

    public synchronized void setPreprocessThreadCount(int newCount) {
        if (newCount > 0 && newCount != currentPreprocessThreads) {
            int maxCores = Runtime.getRuntime().availableProcessors();
            if (newCount > maxCores) {
                logger.warning("Preprocess threads exceed available cores (" + maxCores + ").");
            }
            currentPreprocessThreads = newCount;
            startPreprocessThreads();
        }
    }

    public int getProcessingThreadCount() {
        return currentProcessingThreads;
    }

    public synchronized void setProcessingThreadCount(int newCount) {
        if (newCount > 0 && newCount != currentProcessingThreads) {
            int maxCores = Runtime.getRuntime().availableProcessors();
            if (newCount > 1) {
                logger.warning("Multiple processing threads may overload gRPC server if not concurrent-capable.");
            }
            if (newCount > maxCores) {
                logger.warning("Processing threads exceed available cores (" + maxCores + ").");
            }
            currentProcessingThreads = newCount;
            startProcessingThreads();
        }
    }
}