package knu.app.bll.utils.registry;

import java.util.HashMap;
import java.util.Map;
import knu.app.bll.processors.tracker.multi.MultiObjectTracker;

public class MultiObjectTrackerFactory {

  private final Map<String, MultiObjectTracker> registry = new HashMap<>();
  private static final MultiObjectTrackerFactory instance = new MultiObjectTrackerFactory();

  public static MultiObjectTrackerFactory getInstance() {
    return instance;
  }

  private MultiObjectTrackerFactory() {
  }


  public MultiObjectTracker create(String key){
    MultiObjectTracker obj = registry.get(key);
    if (obj == null) {
      throw new IllegalArgumentException("Unknown type: " + key);
    }
    return obj;
  }

  public void registry(String trackerType, MultiObjectTracker multiObjectTracker) {
    registry.put(trackerType, multiObjectTracker);
  }

  public enum TrackerType{
    BYTETRACK, DEEPSORT;
  }
}
