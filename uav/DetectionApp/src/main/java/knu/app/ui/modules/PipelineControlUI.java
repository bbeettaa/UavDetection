package knu.app.ui.modules;

import imgui.ImGui;
import imgui.type.ImBoolean;
import imgui.type.ImInt;
import knu.app.bll.buffers.BufferableQueue;
import knu.app.bll.utils.LocalizationManager;
import knu.app.bll.utils.PipelineInitializer;
import knu.app.bll.utils.MatWrapper;
import java.util.Map; // Для хранения ссылок на буферы и UI-модули

/**
 * UI-модуль для настройки количества потоков обработки (Processing) и Препроцессинга.
 */
public class PipelineControlUI implements UIModule<Void> {

  private final ImBoolean windowOpen = new ImBoolean(false);

  // UI-переменные для каждого этапа
  private final ImInt grabThreads;
  private final ImInt preprocessThreads;
  private final ImInt processingThreads;

  // Ссылка на класс, управляющий конвейером
  private final PipelineInitializer initializer;

  // Хранение ссылок на все необходимые для перезапуска компоненты

  private static final int MIN_THREADS = 1;
  private static final int MAX_THREADS = 8;

  public PipelineControlUI(PipelineInitializer initializer) {
    this.initializer = initializer;

    // Получаем начальные значения из PipelineInitializer
    this.grabThreads = new ImInt(initializer.getGrabThreadCount());
    this.preprocessThreads = new ImInt(initializer.getPreprocessThreadCount());
    this.processingThreads = new ImInt(initializer.getProcessingThreadCount());
  }

  @Override
  public String getName() {
    return LocalizationManager.tr("menu.pipeline.settings");
  }

  @Override
  public void render() {
    if (!windowOpen.get()) return;

    ImGui.begin(getName(), windowOpen);

    ImGui.textColored(1.0f, 0.6f, 0.0f, 1.0f, // Желтый цвет
        LocalizationManager.tr("pipeline.settings.header"));
    ImGui.separator();

    // --- 1. Управление Захватом/Препроцессингом 1 (Grab) ---
    if (ImGui.collapsingHeader(LocalizationManager.tr("pipeline.stage.grab"))) {
      ImGui.textWrapped(LocalizationManager.tr("pipeline.info.grab"));

      // Слайдер для Захвата (используем ImInt)
      ImGui.sliderInt(LocalizationManager.tr("pipeline.threads.count") + "##Grab",
          grabThreads.getData(), MIN_THREADS, MAX_THREADS);

      if (ImGui.button(LocalizationManager.tr("pipeline.threads.apply") + "##GrabBtn")) {
        // Чистый вызов: используем новый метод setGrabThreadCount(int)
        initializer.setGrabThreadCount(grabThreads.get());
      }
      ImGui.sameLine();
      ImGui.text(String.format(LocalizationManager.tr("pipeline.threads.current") + ": %d",
          initializer.getGrabThreadCount()));
    }

    // --- 2. Управление Препроцессингом 2 (Preprocess) ---
    if (ImGui.collapsingHeader(LocalizationManager.tr("pipeline.stage.preprocess"))) {
      ImGui.textWrapped(LocalizationManager.tr("pipeline.info.preprocess"));

      // Слайдер для Препроцессинга
      ImGui.sliderInt(LocalizationManager.tr("pipeline.threads.count") + "##Preproc",
          preprocessThreads.getData(), MIN_THREADS, MAX_THREADS);

      if (ImGui.button(LocalizationManager.tr("pipeline.threads.apply") + "##PreprocBtn")) {
        // Чистый вызов: используем новый метод setPreprocessThreadCount(int)
        initializer.setPreprocessThreadCount(preprocessThreads.get());
      }
      ImGui.sameLine();
      ImGui.text(String.format(LocalizationManager.tr("pipeline.threads.current") + ": %d",
          initializer.getPreprocessThreadCount()));
    }

    // --- 3. Управление Обработкой (Processing/YOLO/gRPC) ---
    if (ImGui.collapsingHeader(LocalizationManager.tr("pipeline.stage.processing"))) {
      ImGui.textWrapped(LocalizationManager.tr("pipeline.info.processing"));

      // Слайдер для Обработки/Трекинга
      ImGui.sliderInt(LocalizationManager.tr("pipeline.threads.count") + "##Processing",
          processingThreads.getData(), MIN_THREADS, MAX_THREADS);

      if (ImGui.button(LocalizationManager.tr("pipeline.threads.apply") + "##ProcBtn")) {
        // Чистый вызов: используем новый метод setProcessingThreadCount(int)
        initializer.setProcessingThreadCount(processingThreads.get());
      }
      ImGui.sameLine();
      ImGui.text(String.format(LocalizationManager.tr("pipeline.threads.current") + ": %d",
          initializer.getProcessingThreadCount()));
    }

    ImGui.end();
  }

  // --- Методы интерфейса ---
  @Override
  public Void execute(Void o) { return null; }

  @Override
  public void show() { windowOpen.set(true); }

  @Override
  public void toggle() { windowOpen.set(!windowOpen.get()); }

  @Override
  public boolean isOpened() { return windowOpen.get(); }
}