package knu.app.ui.processings.trackers;

import imgui.ImGui;
import imgui.type.ImFloat;
import imgui.type.ImInt;
import knu.app.bll.processors.tracker.multi.MultiObjectTracker;
import knu.app.bll.processors.tracker.single.ObjectTracker;
import knu.app.bll.utils.LocalizationManager;
import knu.app.bll.utils.registry.MultiObjectTrackerFactory;
import knu.app.bll.utils.registry.MultiObjectTrackerFactory.TrackerType;
import knu.app.bll.utils.registry.ObjectTrackerFactory;

public class ByteTrackTrackerUi implements TrackerUI {

  private final MultiObjectTracker tracker;
  private final ImFloat btTrackThresh = new ImFloat(0.4f);
  private final ImFloat btHighThresh = new ImFloat(0.6f);
  private final ImFloat btMatchThresh = new ImFloat(0.5f);
  private final ImInt btTrackBuffer = new ImInt(90);

  public ByteTrackTrackerUi(MultiObjectTracker tracker) {
    this.tracker = tracker;
  }

  @Override
  public String getName() {
    return LocalizationManager.tr("processor.tracker.bytetrack");
  }

  @Override
  public void renderSettings() {
    boolean changed = false;

    changed |= ImGui.sliderFloat(LocalizationManager.tr("processor.trackers.bytetrack.track_thresh") + "##bt", btTrackThresh.getData(), 0.0f, 1.0f);
    changed |= ImGui.sliderFloat(LocalizationManager.tr("processor.trackers.bytetrack.high_thresh") + "##bt", btHighThresh.getData(), 0.0f, 1.0f);
    changed |= ImGui.sliderFloat(LocalizationManager.tr("processor.trackers.bytetrack.match_thresh") + "##bt", btMatchThresh.getData(), 0.0f, 1.0f);
    changed |= ImGui.sliderInt(LocalizationManager.tr("processor.trackers.bytetrack.track_buffer") + "##bt", btTrackBuffer.getData(), 1, 200);

    if (changed) {
      tracker.init(
          btTrackThresh.get(),
          btHighThresh.get(),
          btMatchThresh.get(),
          btTrackBuffer.get()
      );
    }
  }

  @Override
  public ObjectTracker getTracker() {
    return null;
  }

  @Override
  public String getKey() {
    return TrackerType.BYTETRACK.name();
  }
}
