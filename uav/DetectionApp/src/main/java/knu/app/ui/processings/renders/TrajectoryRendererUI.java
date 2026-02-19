package knu.app.ui.processings.renders;

import imgui.ImGui;
import imgui.type.ImFloat;
import imgui.type.ImInt;
import knu.app.bll.algorithms.trajectory.TrajectoryManager;
import knu.app.bll.processors.draw.TrajectoryRenderer;
import knu.app.bll.utils.LocalizationManager;

public class TrajectoryRendererUI extends RendererUI {

  private final ImInt thickness;
  private final ImInt historyLength;
  private TrajectoryRenderer renderer;
  private final ImFloat sensitivity = new ImFloat(1.0f);

  public TrajectoryRendererUI(TrajectoryRenderer renderer) {
    super(renderer, LocalizationManager.tr("processor.draw.type.trajectory"));
    this.renderer = renderer;
    thickness = new ImInt(renderer.getThick());
    historyLength = new ImInt(renderer.getMaxLength());
  }

  @Override
  public void renderSettings(String id) {
    TrajectoryManager manager = getTrajectoryManager();
    ImGui.text("Anomaly Detection Settings:");
    String[] methodNames = manager.getDetectorNames();
    int currentIndex = manager.getActiveDetectorIndex();

    if (ImGui.beginCombo("Active Method##" + id, methodNames[currentIndex])) {
      for (int i = 0; i < methodNames.length; i++) {
        boolean isSelected = (currentIndex == i);
        if (ImGui.selectable(methodNames[i] + "##" + id + i, isSelected)) {
          manager.setActiveDetectorIndex(i);
        }
        if (isSelected) ImGui.setItemDefaultFocus();
      }
      ImGui.endCombo();
    }

    if (ImGui.sliderFloat("Sensitivity##" + id, sensitivity.getData(), 0.1f, 5.0f)) {
      manager.setSensitivity(sensitivity.get());
    }

    ImGui.separator();


    ImGui.separator();
    if (ImGui.sliderInt(LocalizationManager.tr("processor.draw.trajectory.thickness") + "##" + id + "_thick",
        thickness.getData(), 1, 10)) {
      renderer.setDefaultThickness(thickness.get());
    }
    if (ImGui.sliderInt(LocalizationManager.tr("processor.draw.trajectory.history")  + "##" + id + "_history",
        historyLength.getData(), 10, 300)) {
      // Предполагаем, что у TrajectoryRenderer есть метод setMaxHistoryFrames(int)
      renderer.setMaxHistoryFrames(historyLength.get());
    }

    super.renderSettings("tr1");
  }

    public TrajectoryRenderer getRenderer() {
        return renderer;
    }

  public TrajectoryManager getTrajectoryManager() {
    return renderer.getTrajectoryManager();
  }
}