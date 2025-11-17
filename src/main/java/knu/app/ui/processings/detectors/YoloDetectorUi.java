package knu.app.ui.processings.detectors;

import imgui.ImGui;
import imgui.type.ImBoolean;
import imgui.type.ImFloat;
import imgui.type.ImInt;
import knu.app.bll.processors.detection.ObjectDetector;
import knu.app.bll.processors.detection.SURFObjectDetector;
import knu.app.bll.processors.detection.YoloObjectDetector;
import knu.app.bll.utils.LocalizationManager;
import knu.app.bll.utils.processors.DetectionResult;
import org.bytedeco.opencv.opencv_core.Mat;

public class YoloDetectorUi implements DetectorUI  {
  private final YoloObjectDetector detector;
  private final ImFloat confTh = new ImFloat(0.25F);
  private final ImFloat iouTh = new ImFloat(0.25F);
  private final ImBoolean isAugment = new ImBoolean(false);
  private final ImBoolean isHalf = new ImBoolean(false);

  public YoloDetectorUi(YoloObjectDetector detector) {
    this.detector = detector;
  }

  @Override
  public String getName() {
    return LocalizationManager.tr("processor.detectors.yolo");
  }

  @Override
  public void renderSettings() {
    boolean hb = ImGui.sliderFloat(LocalizationManager.tr("processor.detectors.yolo.conf_threshold") + "##YOLODetectorUI", confTh.getData(), 0, 1);
    boolean ob = ImGui.sliderFloat(LocalizationManager.tr("processor.detectors.yolo.iou_thres") + "##YOLODetectorUI", iouTh.getData(), 0, 1);
    boolean eb = ImGui.checkbox(LocalizationManager.tr("processor.detectors.yolo.augment") + "##YOLODetectorUI", isAugment);
    boolean ub = ImGui.checkbox(LocalizationManager.tr("processor.detectors.yolo.half") + "##YOLODetectorUI", isHalf);
    if (hb || ob || eb || ub) {
      detector.init(confTh.get(), iouTh.get(), isAugment.get(), isHalf.get());
    }
  }

  @Override
  public DetectionResult detect(Mat mat) {
    return detector.detect(mat);
  }

  @Override
  public YoloObjectDetector getDetector() {
    return detector;
  }
}
