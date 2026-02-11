package knu.app.ui.processings.renders;

import imgui.ImGui;
import imgui.type.ImInt;
import knu.app.bll.algorithms.trajectory.TrajectoryManager;
import knu.app.bll.processors.draw.TrajectoryRenderer;
import knu.app.bll.utils.LocalizationManager;

/**
 * Модуль UI для настройки параметров отрисовщика траекторий.
 * Позволяет настроить толщину линии и глубину истории (количество кадров).
 */
public class TrajectoryRendererUI extends RendererUI {

  private final ImInt thickness;
  private final ImInt historyLength;
  private TrajectoryRenderer renderer;


  public TrajectoryRendererUI(TrajectoryRenderer renderer) {
    super(renderer, LocalizationManager.tr("processor.draw.type.trajectory"));
    this.renderer = renderer;
    thickness = new ImInt(renderer.getThick());
    historyLength = new ImInt(renderer.getMaxLength());
  }

  @Override
  public void renderSettings(String id) {

    // --- 1. Настройка толщины линии траектории ---
    if (ImGui.sliderInt(LocalizationManager.tr("processor.draw.trajectory.thickness") + "##" + id + "_thick",
        thickness.getData(), 1, 10)) {
      renderer.setDefaultThickness(thickness.get());
    }

    // --- 2. Настройка глубины истории траектории ---
    if (ImGui.sliderInt(LocalizationManager.tr("processor.draw.trajectory.history")  + "##" + id + "_history",
        historyLength.getData(), 10, 300)) {
      // Предполагаем, что у TrajectoryRenderer есть метод setMaxHistoryFrames(int)
      renderer.setMaxHistoryFrames(historyLength.get());
    }

    // --- 3. Базовые настройки цвета/типа линии (наследуются от RendererUI) ---
    super.renderSettings("tr1");
  }

  public TrajectoryManager getTrajectoryManager() {
    return renderer.getTrajectoryManager();
  }
}