package knu.app.ui.processings.trackers;

import imgui.ImGui;
import imgui.type.ImFloat;
import imgui.type.ImInt;
import knu.app.bll.processors.tracker.multi.MultiObjectTracker;
import knu.app.bll.processors.tracker.single.ObjectTracker;
import knu.app.bll.utils.LocalizationManager;
import knu.app.bll.utils.registry.MultiObjectTrackerFactory.TrackerType;

public class DeepSortTrackerUi implements TrackerUI {

  private final MultiObjectTracker tracker;

  private final ImFloat dsMaxCosineDist = new ImFloat(0.2f);
  private final ImInt dsNNBudget = new ImInt(100);
  private final ImFloat dsMaxIouDist = new ImFloat(0.7f);
  private final ImInt dsMaxAge = new ImInt(30);

  public DeepSortTrackerUi(MultiObjectTracker tracker) {
    this.tracker = tracker;
  }

  @Override
  public String getName() {
    return LocalizationManager.tr("processor.tracker.deepsort");
  }

  @Override
  public void renderSettings() {
    boolean a = ImGui.sliderFloat(
        LocalizationManager.tr("processor.trackers.deepsort.max_cosine_distance") + "##ds",
        dsMaxCosineDist.getData(), 0.0f, 1.0f);

    boolean b = ImGui.sliderInt(
        LocalizationManager.tr("processor.trackers.deepsort.nn_budget") + "##ds",
        dsNNBudget.getData(), 0, 500);

    boolean c = ImGui.sliderFloat(
        LocalizationManager.tr("processor.trackers.deepsort.max_iou_distance") + "##ds",
        dsMaxIouDist.getData(), 0.0f, 1.0f);

    boolean d = ImGui.sliderInt(
        LocalizationManager.tr("processor.trackers.deepsort.max_age") + "##ds",
        dsMaxAge.getData(), 1, 300);

    if (a || b || c || d) {
      tracker.init(
          dsMaxCosineDist.get(),
          dsNNBudget.get(),
          dsMaxIouDist.get(),
          dsMaxAge.get()
      );
    }
  }

  @Override
  public ObjectTracker getTracker() {
    return null;
  }

  @Override
  public String getKey() {
    return TrackerType.DEEPSORT.name();
  }
}
