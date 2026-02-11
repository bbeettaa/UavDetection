package knu.app.bll.mot;

import imgui.ImGui;
import imgui.type.ImBoolean;
import imgui.type.ImInt;
import java.util.ArrayList;
import java.util.List;
import knu.app.bll.algorithms.associative.AssociationAlgorithm;
import knu.app.bll.confirmation.MaxMissedDeletingAlgorithm;
import knu.app.bll.confirmation.NOutOfMConfirmation;
import knu.app.bll.processors.tracker.multi.MultiObjectTracker;
import knu.app.bll.utils.LocalizationManager;
import knu.app.bll.utils.registry.MultiObjectTrackerFactory;
import knu.app.bll.utils.processors.DetectionResult;
import knu.app.bll.utils.processors.TrackedObject;
import knu.app.bll.utils.registry.MultiObjectTrackerFactory.TrackerType;
import knu.app.ui.processings.trackers.ByteTrackTrackerUi;
import knu.app.ui.processings.trackers.DeepSortTrackerUi;
import knu.app.ui.processings.trackers.TrackerUI;
import org.apache.commons.lang3.NotImplementedException;
import org.bytedeco.opencv.opencv_core.Mat;

public class ImplementedTrackingManager implements TrackingManager{
  MultiObjectTracker tracker;
  final String name;
  private final ImBoolean useTrackers = new ImBoolean(false);
  private final List<TrackerUI> trackers;
  private final ImInt selectedTracker = new ImInt(-1);



  public ImplementedTrackingManager(String name, List<TrackerUI> trackers) {
    this.name = name;

    this.trackers=trackers;
  }


  @Override
  public List<TrackedObject> update(Mat mat, DetectionResult detResult) {
    if (!useTrackers.get() || tracker == null) {
      return List.of();
    }
    long startTime = System.nanoTime();
    List<TrackedObject> trackedObjects = tracker.update(mat, List.of(detResult));
    double durationMs = (double) (System.nanoTime() - startTime) / 1_000_000.0;

    System.out.printf("Tracker update execution time: %.3f ms%n", durationMs);
    return trackedObjects != null ? trackedObjects : new ArrayList<>();
  }

  @Override
  public void setObjectTracker(String trackerKey) {
    tracker = MultiObjectTrackerFactory.getInstance().create(trackerKey);
  }

  @Override
  public void reset() {
//    if (tracker != null) {
////      tracker.();
//    }
  }

  @Override
  public AssociationAlgorithm getAssociationAlgorithm() {
    throw new NotImplementedException();
  }

  @Override
  public NOutOfMConfirmation getConfirmationAlgorithm() {
    throw new NotImplementedException();
  }

  @Override
  public MaxMissedDeletingAlgorithm getDeletingConfirmationAlgorithm() {
    throw new NotImplementedException();
  }

  @Override
  public int getBufferCapacity() {
    return tracker.getBufferCapacity();
  }

  @Override
  public int getBufferSize() {
    if(tracker == null)
      return -1;
    return tracker.getBufferCapacity();
  }

  @Override
  public void setBuffCapacity(int c) {

  }

  @Override
  public void renderSettings(){
//    if (ImGui.collapsingHeader(LocalizationManager.tr("processor.tracker.manager.name2"), ImGuiTreeNodeFlags.DefaultOpen)) {

      ImGui.bulletText(String.format("%s: %s", LocalizationManager.tr("processor.tracker.manager.buffer.size"),
          getBufferSize()));
      if (ImGui.button(LocalizationManager.tr("processor.tracker.manager.buffer.clear"))) reset();
      ImGui.newLine();


//    }
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void renderTrackers() {
//      if (ImGui.collapsingHeader(LocalizationManager.tr("processor.tracker.name")+"###2",
//          ImGuiTreeNodeFlags.DefaultOpen)) {
        ImGui.checkbox(LocalizationManager.tr("processor.tracker.enable")+"###2", useTrackers);
        ImGui.beginDisabled(!useTrackers.get());

        for (int i = 0; i < trackers.size(); i++) {
          if (ImGui.radioButton(trackers.get(i).getName(), selectedTracker, i)) {
            setObjectTracker(trackers.get(i).getKey());
          }
          if (i == selectedTracker.get()) {
            trackers.get(selectedTracker.get()).renderSettings();
          }
        }
        ImGui.endDisabled();
//    }
  }
}
