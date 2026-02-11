package knu.app.ui.modules;

import imgui.ImGui;
import imgui.flag.ImGuiCol;
import imgui.type.ImBoolean;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import knu.app.bll.algorithms.motion.ComputeMotion;
import knu.app.bll.algorithms.motion.ObjectMotionInfo;
import knu.app.bll.algorithms.trajectory.TrajectoryManager;
import org.bytedeco.opencv.opencv_core.Point;

public class CurrentObjectsUIModule implements UIModule<Void> {

  private final ImBoolean isOpen = new ImBoolean(false);
  // сюда прокидывай извне
  private final TrajectoryManager trajectoryManager;
  private boolean windowOpen = false;
  private int hoveredId = -1;   // ID объекта под мышью
  private int selectedId = -1;  // Выбранный объект (кликнутый)

  public CurrentObjectsUIModule(TrajectoryManager trajectoryManager) {
    this.trajectoryManager = trajectoryManager;
  }

  @Override
  public String getName() {
    return "Current Objects";
  }

  @Override
  public void render() {
    if (!windowOpen) return;

    float windowWidth = 600;
    float windowHeight = 400;
    float screenWidth = ImGui.getIO().getDisplaySizeX();
    float screenHeight = ImGui.getIO().getDisplaySizeY();

    ImGui.setNextWindowSize(windowWidth, windowHeight, imgui.flag.ImGuiCond.FirstUseEver);
    ImGui.setNextWindowPos((screenWidth - windowWidth) / 2,
        (screenHeight - windowHeight) / 2,
        imgui.flag.ImGuiCond.FirstUseEver);

    if (!ImGui.begin(getName())) {
      ImGui.end();
      return;
    }

    ImGui.text("Tracked objects:");
    ImGui.separator();
    ImGui.beginChild("ObjectList", 0, 200, true);

    hoveredId = -1;

    // snapshot траекторий + фильтр мертвых треков
    Map<Integer, LinkedList<Point>> snapshot = new HashMap<>();
    Map<Integer, CopyOnWriteArrayList<Point>> src = trajectoryManager.getTrajectories();

    for (Map.Entry<Integer, CopyOnWriteArrayList<Point>> entry : src.entrySet()) {
      int id = entry.getKey();
      if (!trajectoryManager.isAlive(id)) continue;

      LinkedList<Point> safeCopy = new LinkedList<>();
      for (Point p : entry.getValue()) {
        safeCopy.add(p);
      }
      snapshot.put(id, safeCopy);
    }

    int currentSelected = trajectoryManager.getSelectedTrack();
    final imgui.ImDrawList drawList = ImGui.getWindowDrawList();

    for (Map.Entry<Integer, LinkedList<Point>> entry : snapshot.entrySet()) {
      int id = entry.getKey();
      LinkedList<Point> traj = entry.getValue();
      if (traj.isEmpty()) continue;

      Point last = traj.getLast();
      ObjectMotionInfo motionInfo = ComputeMotion.computeMotion(id, traj );
      String dir = motionInfo != null ? motionInfo.direction : "-";
      double speed = motionInfo != null ? motionInfo.speed : 0.0;

      String label = String.format("ID %d: Pos (%d,%d)", id, last.x(), last.y());

      boolean isSelected = (trajectoryManager.getSelectedTrack() == id);
      ImGui.selectable(label, isSelected);

      if (ImGui.isItemClicked()) {
        trajectoryManager.setSelectedTrack(id);
        selectedId = id;
      }


      // hover detection
      if (ImGui.isItemHovered()) {
        hoveredId = id;
      }

      // вывод скорости и направления справа
      ImGui.sameLine();
      ImGui.text(String.format("Speed: %.1f px/s  Dir: %s", speed, dir));
    }

    ImGui.endChild();

    ImGui.separator();
    ImGui.text("Hovered ID: " + (hoveredId != -1 ? hoveredId : "None"));
    ImGui.text("Selected ID: " + (trajectoryManager.getSelectedTrack() != -1
        ? trajectoryManager.getSelectedTrack() : "None"));

    ImGui.end();
  }


  @Override
  public Void execute(Void o) {
    return null;
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
