package knu.app.bll.utils;

import knu.app.bll.processors.tracker.ObjectTracker;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ObjectTrackerFactory {
    private final Map<String, Supplier<ObjectTracker>> registry = new HashMap<>();
    private static final ObjectTrackerFactory instance = new ObjectTrackerFactory();

    public static ObjectTrackerFactory getInstance() {
        return instance;
    }

    private ObjectTrackerFactory() {
    }

    public void registry(String key, Supplier<ObjectTracker> supplier) {
        registry.put(key, supplier);
    }

    public ObjectTracker create(String key){
        Supplier<ObjectTracker> obj = registry.get(key);
        if (obj == null) {
            throw new IllegalArgumentException("Unknown type: " + key);
        }
        return obj.get();
    }

    public enum TrackerType{
        CSRT, MIL, KALMAN, OPTICALFLOW;
    }
}
