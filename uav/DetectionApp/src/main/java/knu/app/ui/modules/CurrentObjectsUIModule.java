package knu.app.ui.modules;

import imgui.ImGui;
import imgui.flag.ImGuiCol;
import imgui.type.ImBoolean;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import knu.app.bll.algorithms.feature.ObjectState;
import knu.app.bll.algorithms.motion.ComputeMotion;
import knu.app.bll.algorithms.motion.ObjectMotionInfo;
import knu.app.bll.algorithms.trajectory.TrajectoryManager;
import knu.app.bll.utils.processors.TrackedObject;
import org.bytedeco.opencv.opencv_core.Point;

public class CurrentObjectsUIModule implements UIModule<List<TrackedObject>> {

  private final ImBoolean isOpen = new ImBoolean(false);
  private final TrajectoryManager trajectoryManager;
  private boolean windowOpen = false;
  private int hoveredId = -1;
  private int selectedId = -1;

  public CurrentObjectsUIModule(TrajectoryManager trajectoryManager) {
    this.trajectoryManager = trajectoryManager;
  }

  @Override
  public String getName() {
    return "Current Objects";
  }

  private List<TrackedObject> currentObjects = new CopyOnWriteArrayList<>();

  @Override
  public void render() {
    if (!windowOpen) return;

    ImGui.setNextWindowSize(600, 400, imgui.flag.ImGuiCond.FirstUseEver);

    if (!ImGui.begin(getName())) {
      ImGui.end();
      return;
    }

    ImGui.text("Tracked objects:");
    ImGui.separator();
    ImGui.beginChild("ObjectList", 0, 250, true);

    hoveredId = -1;

    for (TrackedObject obj : currentObjects) {

      if (obj.isDeleted()) continue;

      int id = obj.getId();
      ObjectState last = obj.getLastState();
      if (last == null) continue;

      String label = String.format(
              "ID %d | Pos(%d,%d) | Speed: %.2f",
              id,
              last.center.x(),
              last.center.y(),
              last.speed
      );

      boolean isSelected = (trajectoryManager.getSelectedTrack() == id);
      ImGui.selectable(label, isSelected);

      if (ImGui.isItemClicked()) {
        trajectoryManager.setSelectedTrack(id);
        selectedId = id;
      }

      if (ImGui.isItemHovered()) {
        hoveredId = id;
      }

      ImGui.sameLine(450);
      ImGui.text(String.format("Angle: %.2f  %s",
              last.angleDirection,
              last.isAnomalous ? "ANOMALY" : ""
      ));
    }

    ImGui.endChild();

    ImGui.separator();
    ImGui.text("Hovered ID: " + (hoveredId != -1 ? hoveredId : "None"));
    ImGui.text("Selected ID: " + (trajectoryManager.getSelectedTrack() != -1
            ? trajectoryManager.getSelectedTrack() : "None"));

    ImGui.end();
  }

  @Override
  public List<TrackedObject> execute(List<TrackedObject> objects) {
    if (objects != null) {
      currentObjects = new CopyOnWriteArrayList<>(objects);
    }
    return objects;
  }

  @Override
  public void show() {
    windowOpen =(!windowOpen);
  }

  @Override
  public void toggle() {
      windowOpen =(!windowOpen);
    }

  @Override
  public boolean isOpened() {
    return isOpen.get();
  }

  public int getHoveredId() {
    return hoveredId;
  }

  public int getSelectedId() {
    return selectedId;
  }
}
