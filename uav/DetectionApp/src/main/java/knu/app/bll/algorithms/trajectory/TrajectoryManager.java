package knu.app.bll.algorithms.trajectory;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import knu.app.bll.algorithms.motion.ComputeMotion;
import knu.app.bll.algorithms.motion.ObjectMotionInfo;
import org.bytedeco.opencv.opencv_core.Point;

public class TrajectoryManager {

  private final Map<Integer, CopyOnWriteArrayList<Point>> trajectories = new ConcurrentHashMap<>();
  private final Map<Integer, Integer> deadFramesCount = new ConcurrentHashMap<>(); // Лічильник мертвих кадрів

  private int maxLength;
  private int maxDeadFrames = 120;
  private int selectedTrack = -1;

  public TrajectoryManager(int maxLength) {
    this.maxLength = maxLength;
  }

  public void updateTrajectory(Map<Integer, Point> detectedObjectsCenters) {
    Iterator<Entry<Integer, CopyOnWriteArrayList<Point>>> iterator = trajectories.entrySet().iterator();

    while (iterator.hasNext()) {
      Map.Entry<Integer, CopyOnWriteArrayList<Point>> entry = iterator.next();
      int objectId = entry.getKey();
      CopyOnWriteArrayList<Point> trajectory = entry.getValue();

      Point newPoint = detectedObjectsCenters.get(objectId);

      if (newPoint != null) {
        trajectory.add(newPoint);
        deadFramesCount.put(objectId, 0);
      } else {
        int currentDeadFrames = deadFramesCount.getOrDefault(objectId, 0) + 1;
        deadFramesCount.put(objectId, currentDeadFrames);

        if (!trajectory.isEmpty()) {
//          trajectory.add(trajectory.get(trajectory.size() - 1));
        }

        if (currentDeadFrames > maxDeadFrames) {
          iterator.remove();
          deadFramesCount.remove(objectId);
          continue;
        }

      }

      trimToMaxLength(trajectory);
    }

    Set<Integer> existingIds = trajectories.keySet();
    for (Map.Entry<Integer, Point> obj : detectedObjectsCenters.entrySet()) {
      if (!existingIds.contains(obj.getKey())) {
        CopyOnWriteArrayList<Point> tr = new CopyOnWriteArrayList<>();
        tr.add(obj.getValue());
        trajectories.put(obj.getKey(), tr);
        deadFramesCount.put(obj.getKey(), 0);
      }
    }
  }

  private void trimToMaxLength(CopyOnWriteArrayList<Point> trajectory) {
    while (trajectory.size() > maxLength) {
      trajectory.removeFirst();
    }
  }

  public Map<Integer, CopyOnWriteArrayList<Point>> getTrajectories() {
    return trajectories;
  }

  public int getMaxLength() {
    return maxLength;
  }

  public void setMaxLength(int maxLength) {
    this.maxLength = maxLength;

    for (CopyOnWriteArrayList<Point> tr : trajectories.values()) {
      trimToMaxLength(tr);
    }
  }


  public void setSelectedTrack(int selectedTrack) {
    this.selectedTrack = selectedTrack;
  }

  public int getSelectedTrack() {
    return selectedTrack;
  }

  public boolean isAlive(int id) {
    CopyOnWriteArrayList<Point> traj = trajectories.get(id);
    return traj != null && !traj.isEmpty();
  }

  public ObjectMotionInfo getMotionInfo(int id){
    CopyOnWriteArrayList<Point> traj = trajectories.get(id);
    return ComputeMotion.computeMotion(id, traj );
  }


}
