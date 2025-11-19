package knu.app.ui.processings.trackers;

import knu.app.bll.processors.tracker.single.MilTracker;
import knu.app.bll.utils.LocalizationManager;
import knu.app.bll.utils.registry.ObjectTrackerFactory;

public class MILTrackerUI implements TrackerUI {
    private final MilTracker tracker;

    public MILTrackerUI(MilTracker tracker){
        this.tracker = tracker;
    }

    @Override
    public String getName() {
        return LocalizationManager.tr("processor.tracker.mil");
    }

    @Override
    public void renderSettings() {
    }

    @Override
    public MilTracker getTracker() {
        return tracker;
    }

    @Override
    public String getKey() {
        return ObjectTrackerFactory.TrackerType.MIL.name();
    }
}
