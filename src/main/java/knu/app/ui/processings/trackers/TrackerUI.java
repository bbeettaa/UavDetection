package knu.app.ui.processings.trackers;


import knu.app.bll.processors.tracker.ObjectTracker;

public interface TrackerUI {
    String getName();
    void renderSettings();
    ObjectTracker getTracker();
    String getKey();
}
