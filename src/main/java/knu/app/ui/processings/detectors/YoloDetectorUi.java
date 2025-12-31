package knu.app.ui.processings.detectors;

import imgui.ImGui;
import imgui.flag.ImGuiTreeNodeFlags;
import imgui.type.ImBoolean;
import imgui.type.ImFloat;
import imgui.type.ImInt;
import java.util.List;
import knu.app.bll.processors.detection.YoloObjectDetector;
import knu.app.bll.utils.LocalizationManager;
import knu.app.bll.utils.processors.DetectionResult;
import knu.app.bll.utils.processors.TrackedObject;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;

public class YoloDetectorUi implements DetectorUI {

  private final YoloObjectDetector detector;
  private final ImFloat confTh = new ImFloat(0.25F);
  private final ImFloat iouTh = new ImFloat(0.25F);
  private final ImBoolean isAugment = new ImBoolean(false);
  private final ImBoolean isHalf = new ImBoolean(false);

  private final ImInt swidth ;
  private final ImInt sheight;
  private final ImInt jpegCompr;

  public YoloDetectorUi(YoloObjectDetector detector) {
    this.detector = detector;
    this.swidth = new ImInt(detector.getWidthResize());
    this.sheight = new ImInt(detector.getHeightResize());
    this.jpegCompr = new ImInt(detector.getJpegQuality());
  }

  @Override
  public String getName() {
    return LocalizationManager.tr("processor.detectors.yolo");
  }

  @Override
  public void renderSettings() {
    boolean hb = ImGui.sliderFloat(
        LocalizationManager.tr("processor.detectors.yolo.conf_threshold") + "##YOLODetectorUI",
        confTh.getData(), 0, 1);
    boolean ob = ImGui.sliderFloat(
        LocalizationManager.tr("processor.detectors.yolo.iou_thres") + "##YOLODetectorUI",
        iouTh.getData(), 0, 1);
    boolean eb = ImGui.checkbox(
        LocalizationManager.tr("processor.detectors.yolo.augment") + "##YOLODetectorUI", isAugment);
    boolean ub = ImGui.checkbox(
        LocalizationManager.tr("processor.detectors.yolo.half") + "##YOLODetectorUI", isHalf);
    if (hb || ob || eb || ub) {
      detector.init(confTh.get(), iouTh.get(), isAugment.get(), isHalf.get());
    }

    ImGui.separator();
    ImGui.newLine();

    if (ImGui.collapsingHeader(LocalizationManager.tr("processor.network.settings")+"##YOLODetectorUI",
        ImGuiTreeNodeFlags.DefaultOpen)){
      if(ImGui.sliderInt(LocalizationManager.tr(
              "processor.network.settings.jpeg.quality") + "##YOLODetectorUI", jpegCompr.getData(), 0, 100)){
        detector.setJpegQuality(jpegCompr.get());
      }
      if(ImGui.inputInt(LocalizationManager.tr("frame.width.name"), swidth, 5, 50)){
        detector.setWidthResize(swidth.get());
      }
      ImGui.sameLine();
      if(ImGui.inputInt(LocalizationManager.tr("frame.height.name"), sheight, 5, 50)){
        detector.setHeightResize(sheight.get());
      }


    }

  }

  @Override
  public DetectionResult detect(Mat mat) {
    long startTime = System.nanoTime();
    DetectionResult result = detector.detect(mat);
    double durationMs = (double) (System.nanoTime() - startTime) / 1_000_000.0;
    System.out.printf("Tracker update execution time: %.3f ms%n", durationMs);
    return result;
  }

  @Override
  public YoloObjectDetector getDetector() {
    return detector;
  }
}
