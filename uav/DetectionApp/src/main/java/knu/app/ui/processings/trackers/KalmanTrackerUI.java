package knu.app.ui.processings.trackers;

import knu.app.bll.processors.tracker.single.KalmanObjectTracker;
import knu.app.bll.processors.tracker.single.ObjectTracker;
import knu.app.bll.utils.LocalizationManager;
import knu.app.bll.utils.registry.ObjectTrackerFactory;

public class KalmanTrackerUI implements TrackerUI {
    private final KalmanObjectTracker kalman;

    public KalmanTrackerUI(KalmanObjectTracker kalman) {
        this.kalman = kalman;
    }

    @Override
    public String getName() {
        return LocalizationManager.tr("processor.tracker.kalman");
    }

    @Override
    public void renderSettings() {
    }

    @Override
    public ObjectTracker getTracker() {
        return kalman;
    }

    @Override
    public String getKey() {
        return ObjectTrackerFactory.TrackerType.KALMAN.name();
    }
}

