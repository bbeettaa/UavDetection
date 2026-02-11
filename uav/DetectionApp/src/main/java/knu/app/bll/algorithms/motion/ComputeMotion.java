package knu.app.bll.algorithms.motion;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.bytedeco.opencv.opencv_core.Point;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import org.bytedeco.opencv.opencv_core.Point;



import java.util.List;
import org.bytedeco.opencv.opencv_core.Point;

  public class ComputeMotion {
    private static final ConcurrentMap<Integer, Long> lastUpdateNs = new ConcurrentHashMap<>();
    private static final double DEFAULT_DT = 0.1;
    private static final double MIN_DT = 0.1;
    private static final double MAX_SPEED_CAP = 100.0;
    /**
     *
     * Compute motion using a window of last points. If fps>0, dt is computed from fps;
     * otherwise fallback to DEFAULT_DT.
     *
     * @param id   object id (not used currently, kept for compatibility)
     * @param traj trajectory points (old -> new)
     */
    public static ObjectMotionInfo computeMotion(int id, List<Point> traj) {
      if (traj == null || traj.size() < 2) return null;

// 1. Обчислення часу (dt)
      double dt = computeDeltaTimeSec(id);

      // Захист від ділення на нуль або нереалістично малих значень
      if (dt < MIN_DT) {
        dt = MIN_DT;
      }

      // 2. Обчислення зміщення
      Point p1 = traj.get(traj.size() - 2);
      Point p2 = traj.get(traj.size() - 1);

      double dx = (double) (p2.x() - p1.x());
      double dy = (double) (p2.y() - p1.y());

      // 3. Обчислення швидкості

      // Миттєві складові (піксель/сек)
      double vx = dx / dt;
      double vy = dy / dt;

      // Миттєва загальна швидкість (дистанція / час)
      double speed = Math.hypot(vx, vy);

      ObjectMotionInfo info = new ObjectMotionInfo();
      info.speed = speed / 1;
      info.vx = vx;
      info.vy = vy;
      info.direction = calculateDirection(vx, vy);

      return info;
    }







    private static double computeDeltaTimeSec(int id) {
      long now = System.nanoTime();
      // put повертає попереднє значення або null
      Long prev = lastUpdateNs.put(id, now);

      if (prev == null) {
        return DEFAULT_DT;
      }

      return (now - prev) / 1_000_000_000.0;
    }

  private static String calculateDirection(double vx, double vy) {
    // Порогове значення для визначення "значного" руху
    final double THRESHOLD = 0.01;

    String horizontal = "";
    String vertical = "";

    if (Math.abs(vx) > THRESHOLD) {
      horizontal = (vx > 0) ? "RIGHT" : "LEFT";
    }

    if (Math.abs(vy) > THRESHOLD) {
      vertical = (vy > 0) ? "DOWN" : "UP"; // Припускаємо, що вісь Y зростає вниз
    }

    // Комбінуємо горизонтальний і вертикальний напрямки
    if (horizontal.isEmpty() && vertical.isEmpty()) {
      return "STATIONARY";
    } else if (!horizontal.isEmpty() && !vertical.isEmpty()) {
      // Діагональний рух (наприклад, UP_RIGHT)
      return vertical + "_" + horizontal;
    } else if (!horizontal.isEmpty()) {
      // Чисто горизонтальний рух
      return horizontal;
    } else {
      // Чисто вертикальний рух
      return vertical;
    }
  }
}