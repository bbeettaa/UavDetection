package knu.app.ui.modules;

import imgui.ImGui;
import imgui.flag.ImGuiTreeNodeFlags;
import imgui.type.ImBoolean;
import imgui.type.ImInt;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import knu.app.Main;
import knu.app.bll.mot.TrackingManager;
import knu.app.bll.processors.detection.HogSvmDetector;
import knu.app.bll.processors.detection.ORBObjectDetector;
import knu.app.bll.processors.detection.SIFTObjectDetector;
import knu.app.bll.processors.detection.SURFObjectDetector;
import knu.app.bll.processors.detection.YoloObjectDetector;
import knu.app.bll.utils.LocalizationManager;
import knu.app.bll.utils.MatWrapper;
import knu.app.bll.utils.MetricsEvaluator;
import knu.app.bll.utils.processors.DetectionResult;
import knu.app.bll.utils.processors.TrackedObject;
import knu.app.bll.utils.processors.hog.HogSvmDetectorConfig;
import knu.app.bll.utils.processors.hog.HogSvmUtils;
import knu.app.ui.processings.detectors.DetectorUI;
import knu.app.ui.processings.detectors.HogDetectorUI;
import knu.app.ui.processings.detectors.ORBDetectorUI;
import knu.app.ui.processings.detectors.SIFTDetectorUI;
import knu.app.ui.processings.detectors.SURFDetectorUI;
import knu.app.ui.processings.detectors.YoloDetectorUi;
import knu.app.ui.processings.renders.CenterPointRendererUI;
import knu.app.ui.processings.renders.FeaturedPointsRendererUI;
import knu.app.ui.processings.renders.RendererUI;
import knu.app.ui.processings.renders.RoiRendererUI;
import org.bytedeco.opencv.opencv_core.Mat;

public class ProcessingModule implements UIModule<MatWrapper> {

  public static final String PROCESSOR_ID = LocalizationManager.tr("menu.processor.name");
  //    private final DetectionTrackingManager detectionTrackingManager;
  private final List<TrackingManager> trackingManagers;
  private final ImBoolean isOp = new ImBoolean(true);
  private final ImBoolean useDetectors = new ImBoolean(false);
  private final ImInt selectedDetector = new ImInt(-1);
  //  private final ImBoolean useTrackers = new ImBoolean(false);
//  private final ImInt selectedTracker = new ImInt(-1);
  private final ImInt selectedTrackerManager = new ImInt(1);
  private final List<DetectorUI> detectors = new ArrayList<>();
  private final ImBoolean drawDetections = new ImBoolean(true);
  //  private final List<TrackerUI> trackers = new ArrayList<>();
  private final ImBoolean drawTracking = new ImBoolean(true);
  private final List<RendererUI> renderers = new ArrayList<>();
  private final ImInt selectedDetectorRendererIndex = new ImInt(2);
  private final ImInt selectedTrackerRendererIndex = new ImInt(1);

  private final ExecutorService processingExecutor = Executors.newFixedThreadPool(
      Math.max(2, Runtime.getRuntime().availableProcessors()));
  // очередь для результатов (должна быть у тебя, или используем frameWriterBuffer напрямую)
  private final BlockingQueue<MatWrapper> completedQueue = new LinkedBlockingQueue<>();

  private TrackingManager trackingManager;
  private DetectionResult detectionResult = new DetectionResult();
  private String[] rendererNames;
  private MetricsEvaluator metrics;

  public ProcessingModule(List<TrackingManager> trackingManagers, Mat templateImg,
      String hogDescriptorFile, HogSvmDetectorConfig hogSvmDetectorConfig,
      MetricsEvaluator metrics) {
    init(templateImg, hogDescriptorFile, hogSvmDetectorConfig);
    this.metrics = metrics;
    this.trackingManagers = trackingManagers;
    this.trackingManager = trackingManagers.get(selectedTrackerManager.get());
  }

  private static void imGuiSeparate() {
    ImGui.newLine();
    ImGui.separator();
    ImGui.newLine();
  }

  public void init(Mat templateImg, String hogDescriptorFile,
      HogSvmDetectorConfig hogSvmDetectorConfig) {
    detectors.add(new ORBDetectorUI(new ORBObjectDetector(templateImg)));
    detectors.add(new SIFTDetectorUI(new SIFTObjectDetector(templateImg)));
    detectors.add(new SURFDetectorUI(new SURFObjectDetector(templateImg)));
    detectors.add(new HogDetectorUI(
        new HogSvmDetector(HogSvmUtils.loadDescriptorFromFile(hogDescriptorFile),
            hogSvmDetectorConfig)));
    detectors.add(new YoloDetectorUi(new YoloObjectDetector(Main.YOLO_HOST, Main.YOLO_PORT)));

//    ObjectTrackerFactory trackerFactory = ObjectTrackerFactory.getInstance();
//    trackers.add(new KalmanTrackerUI(new KalmanObjectTracker()));
//    trackers.add(new LucasKanadeTrackerUI(new OpticalFlowTracker()));
//    trackers.add(new CSRTTrackerUI(new CSRTTracker()));
//    trackers.add(new MILTrackerUI(new MilTracker()));

//    trackers.add(new DeepSortTrackerUi(new PythonImplimentation(DeepSortHost, DeepSortPort)));
//    trackers.add(new ByteTrackTrackerUi(new PythonImplimentation(ByteTrackerHost, ByteTrackerPort)));

    renderers.add(new FeaturedPointsRendererUI());
    renderers.add(new CenterPointRendererUI());
    renderers.add(new RoiRendererUI());
    rendererNames = new String[renderers.size()];
    rendererNames = renderers.stream()
        .map(rendererUI -> rendererUI.getRenderer().getClass().getSimpleName())
        .toArray(String[]::new);
  }

  @Override
  public String getName() {
    return PROCESSOR_ID;
  }

  @Override
  public void render() {
    if (!isOp.get()) {
      return;
    }
    ImGui.begin(getName(), isOp);
    ImGui.pushItemWidth(150);

    renderDetectors();
//    renderTrackers();
    ImGui.newLine();
    renderTrackingManager();
    ImGui.newLine();
    renderDrawingSettings();
    ImGui.newLine();

    ImGui.popItemWidth();
    ImGui.end();
  }

  private void renderDetectors() {
    if (ImGui.collapsingHeader(LocalizationManager.tr("processor.detectors.name"),
        ImGuiTreeNodeFlags.DefaultOpen)) {
      ImGui.checkbox(LocalizationManager.tr("processor.detectors.enable"), useDetectors);
      ImGui.beginDisabled(!useDetectors.get());
      for (int i = 0; i < detectors.size(); i++) {
        ImGui.radioButton(detectors.get(i).getName(), selectedDetector, i);
        if (i == selectedDetector.get()) {
          detectors.get(selectedDetector.get()).renderSettings();
        }
      }
      ImGui.endDisabled();
    }
  }

//  private void renderTrackers() {
//    if (ImGui.collapsingHeader(LocalizationManager.tr("processor.tracker.name"),
//        ImGuiTreeNodeFlags.DefaultOpen)) {
//      ImGui.checkbox(LocalizationManager.tr("processor.tracker.enable"), useTrackers);
//      ImGui.beginDisabled(!useTrackers.get());
//
//      for (int i = 0; i < trackers.size(); i++) {
//        if (ImGui.radioButton(trackers.get(i).getName(), selectedTracker, i)) {
//          trackingManager.setObjectTracker(trackers.get(i).getKey());
//        }
//        if (i == selectedTracker.get()) {
//          trackers.get(selectedTracker.get()).renderSettings();
//        }
//      }
//      ImGui.endDisabled();
//    }
//  }

  private void renderTrackingManager() {
    if (ImGui.collapsingHeader(trackingManager.getName(),
        ImGuiTreeNodeFlags.DefaultOpen)) {
      for (int i = 0; i < trackingManagers.size(); i++) {
        if (ImGui.radioButton(trackingManagers.get(i).getName(),
            (selectedTrackerManager.get() == i))) {
          trackingManager = trackingManagers.get(i);
        }
      }
      ImGui.separator();

      trackingManager.renderSettings();
      trackingManager.renderTrackers();
    }

  }

  private void renderDrawingSettings() {
    if (ImGui.collapsingHeader(LocalizationManager.tr("processor.draw.name") + "##detection",
        ImGuiTreeNodeFlags.DefaultOpen)) {
      ImGui.checkbox(LocalizationManager.tr("processor.draw.enable") + "##detection",
          drawDetections);
      ImGui.beginDisabled(!drawDetections.get());
      ImGui.combo(LocalizationManager.tr("processor.draw.choose") + "##detection",
          selectedDetectorRendererIndex, rendererNames, rendererNames.length);
      renderers.get(selectedDetectorRendererIndex.get()).renderSettings("detect");
      ImGui.endDisabled();

      imGuiSeparate();

      ImGui.checkbox(LocalizationManager.tr("processor.draw.enable") + "##" + "tracking",
          drawTracking);
      ImGui.beginDisabled(!drawTracking.get());
      ImGui.combo(LocalizationManager.tr("processor.draw.choose") + "##" + "tracking",
          selectedTrackerRendererIndex, rendererNames, rendererNames.length);
      renderers.get(selectedTrackerRendererIndex.get()).renderSettings("track");
      ImGui.endDisabled();
    }
  }

  @Override
  public MatWrapper execute(MatWrapper matWrapper) {
    Mat mat = matWrapper.mat();
    executeDetectors(mat);
    List<TrackedObject> trackedObjects = null;
//    if (useTrackers.get() && selectedTracker.get() >= 0
//        && selectedTracker.get() < trackers.size()) {
    if(useDetectors.get())
    trackedObjects = trackingManager.update(mat, detectionResult);
//    }

    if (drawDetections.get() && detectionResult != null) {
      if (!detectionResult.getRects().isEmpty()) {
        renderers.get(selectedDetectorRendererIndex.get())
            .render(mat, detectionResult.getRects(), detectionResult.getScores()
                , detectionResult.getNames(), true);
      }
    }

    if (drawTracking.get()) {
      if (trackedObjects != null) {
        renderers.get(selectedTrackerRendererIndex.get()).render(mat, trackedObjects, true);
      }
    }

//        if(!detectionResult.getRects().isEmpty())
//        metrics.evaluate(matWrapper.frameIndex(), detectionResult.getRects());
    if (trackedObjects != null) {
      metrics.evaluate(matWrapper.frameIndex(),
          trackedObjects.stream().map(TrackedObject::getRect).toList());
    }

    return matWrapper;
  }

  private void executeDetectors(Mat mat) {
    if (useDetectors.get() && selectedDetector.get() >= 0
        && selectedDetector.get() < detectors.size()) {
      detectionResult = detectors.get(selectedDetector.get()).detect(mat);
    }
  }


  @Override
  public void show() {
    isOp.set(true);
  }

  @Override
  public void toggle() {
    isOp.set(!isOp.get());
  }

  @Override
  public boolean isOpened() {
    return isOp.get();
  }

}
