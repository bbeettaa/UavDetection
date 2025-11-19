package knu.app.ui.processings.trackers;

import knu.app.bll.processors.tracker.single.ObjectTracker;
//import knu.app.detection.tracker.SimpleTrackers;
import knu.app.bll.processors.tracker.single.OpticalFlowTracker;
import knu.app.bll.utils.LocalizationManager;
import knu.app.bll.utils.registry.ObjectTrackerFactory;

public class LucasKanadeTrackerUI implements TrackerUI {
    private final ObjectTracker tracker;

    public LucasKanadeTrackerUI(OpticalFlowTracker tracker) {
        this.tracker = tracker;
    }

    @Override
    public String getName() {
        return LocalizationManager.tr("processor.tracker.lucaskanade");
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
        return ObjectTrackerFactory.TrackerType.OPTICALFLOW.name();
    }
}
