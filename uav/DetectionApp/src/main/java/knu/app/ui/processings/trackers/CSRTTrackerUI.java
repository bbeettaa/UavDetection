package knu.app.ui.processings.trackers;

import knu.app.bll.processors.tracker.single.CSRTTracker;
import knu.app.bll.processors.tracker.single.ObjectTracker;
import knu.app.bll.utils.LocalizationManager;
import knu.app.bll.utils.registry.ObjectTrackerFactory;

public class CSRTTrackerUI implements TrackerUI {
    private final ObjectTracker tracker;

    public CSRTTrackerUI(CSRTTracker tracker) {
        this.tracker = tracker;
    }

    @Override
    public String getName() {
        return LocalizationManager.tr("processor.tracker.csrt");
    }

    @Override
    public void renderSettings() {
    }

    @Override
    public ObjectTracker getTracker() {
        return tracker;
    }

    @Override
    public String getKey() {
        return ObjectTrackerFactory.TrackerType.CSRT.name();
    }
}
