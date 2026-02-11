package knu.app.bll.algorithms.trajectory;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.bytedeco.opencv.opencv_core.Point;

public class TrajectoryManager {

//  private final Map<Integer, LinkedList<Point>> trajectories = new ConcurrentHashMap<>();
  private final Map<Integer, CopyOnWriteArrayList<Point>> trajectories = new ConcurrentHashMap<>();
  private final Map<Integer, Integer> deadFramesCount = new ConcurrentHashMap<>(); // Лічильник мертвих кадрів

  private int maxLength;
  private int maxDeadFrames; // Максимально дозволена кількість дублювань точки
  private int selectedTrack = -1;


  public TrajectoryManager(int maxLength) {
    this.maxLength = maxLength;
  }

  /**
   * Обновляет все траектории в один проход:
   * - для пришедших объектов → добавляем новую точку
   * - для отсутствующих → дублируем последнюю точку (продолжаем линию)
   */
  public void updateTrajectory(Map<Integer, Point> detectedObjectsCenters) {

    // --- 1. Обновляем и Удаляем существующие траектории ---
    Iterator<Entry<Integer, CopyOnWriteArrayList<Point>>> iterator = trajectories.entrySet().iterator();


    while (iterator.hasNext()) {
      Map.Entry<Integer, CopyOnWriteArrayList<Point>> entry = iterator.next();
      int objectId = entry.getKey();
      CopyOnWriteArrayList<Point> trajectory = entry.getValue();

      Point newPoint = detectedObjectsCenters.get(objectId);

      if (newPoint != null) {
        trajectory.add(newPoint);
        deadFramesCount.put(objectId, 0); // сброс счетчика "мертвых кадров"
      } else {
        int currentDeadFrames = deadFramesCount.getOrDefault(objectId, 0) + 1;
        deadFramesCount.put(objectId, currentDeadFrames);

        if (!trajectory.isEmpty()) {
          // вместо копирования последней точки, можно просто оставить последнюю точку
          trajectory.add(trajectory.get(trajectory.size() - 1));
        }

        // если объект "мертвый" слишком долго
        if (currentDeadFrames > maxDeadFrames) {
          // постепенно укорачиваем траекторию на 1 точку за кадр
          if (!trajectory.isEmpty()) {
            trajectory.remove(0);
          }
          // если траектория полностью исчезла — удаляем объект
          if (trajectory.isEmpty()) {
            iterator.remove();
            deadFramesCount.remove(objectId);
            continue;
          }
        }
      }

      trimToMaxLength(trajectory);
    }

    // --- 2. Добавляем новые объекты, которых раньше не было ---
    Set<Integer> existingIds = trajectories.keySet();
    for (Map.Entry<Integer, Point> obj : detectedObjectsCenters.entrySet()) {
      if (!existingIds.contains(obj.getKey())) {
        CopyOnWriteArrayList<Point> tr = new CopyOnWriteArrayList<>();
        tr.add(obj.getValue());
        trajectories.put(obj.getKey(), tr);
        deadFramesCount.put(obj.getKey(), 0); // Инициализируем счетчик
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

    // пересжимаем существующие траектории
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

}
