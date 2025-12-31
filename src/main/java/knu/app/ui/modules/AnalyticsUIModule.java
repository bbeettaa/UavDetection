package knu.app.ui.modules;

import imgui.ImGui;
import imgui.type.ImBoolean;
import java.util.concurrent.CopyOnWriteArrayList;
import knu.app.bll.utils.LocalizationManager;
import knu.app.bll.utils.processors.TrackedObject;
import knu.app.bll.algorithms.trajectory.TrajectoryManager;
import org.bytedeco.opencv.opencv_core.Point;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Set;

/**
 * AnalyticsUIModule — расширенная версия.
 * Поддерживает:
 *  - сбор per-frame bbox -> CSV buffer
 *  - уникальный подсчёт объектов (по id)
 *  - экспорт CSV/JSON для детекций и траекторий (полные точки)
 *  - определение направления движения (LEFT/RIGHT/UP/DOWN/STATIC)
 *  - неблокирующий экспорт (в фоне)
 *
 * Для экспорта траекторий требуется передать TrajectoryManager (тот же, что у TrajectoryRenderer).
 */
public class AnalyticsUIModule implements UIModule<List<TrackedObject>> {

  private final ImBoolean windowOpen = new ImBoolean(false);
  private final ImBoolean drawTrajectories = new ImBoolean(true);
  private final ImBoolean enableDataCollection = new ImBoolean(false);

  // current objects snapshot
  private final Object currentObjectsLock = new Object();
  private final List<TrackedObject> currentObjects = new ArrayList<>();

  // уникальные id
  private final Set<Long> uniqueIdsSeen = ConcurrentHashMap.newKeySet();
  private final AtomicLong uniqueObjectsCount = new AtomicLong(0);

  // буфер детекций (CSV-строки). Используем очередь, чтобы не копировать большие списки.
  private final Queue<String> exportDetectionsBuffer = new ConcurrentLinkedQueue<>();
  private final AtomicLong exportDetectionsCount = new AtomicLong(0);

  // TrajectoryManager (shared instance) — если null, экспорт траекторий недоступен
  private final TrajectoryManager trajectoryManager;

  // Executor для фоновых IO-операций
  private final ExecutorService ioExecutor = Executors.newSingleThreadExecutor(r -> {
    Thread t = new Thread(r, "analytics-export");
    t.setDaemon(true);
    return t;
  });

  private static final String CSV_DETECTIONS_HEADER = "TimestampMs,ObjectID,Class,BBox_X,BBox_Y,BBox_W,BBox_H\n";
  private static final String CSV_TRAJECTORY_HEADER = "ObjectID,StartTsMs,EndTsMs,Direction,Points\n";

  private final int directionMinDelta = 5; // пиксели — порог для определения статичности

  public AnalyticsUIModule(TrajectoryManager trajectoryManager) {
    this.trajectoryManager = trajectoryManager;
  }

  @Override
  public String getName() {
    return LocalizationManager.tr("analytic.module.name");
  }

  @Override
  public List<TrackedObject> execute(List<TrackedObject> trackedObjects) {
    if (trackedObjects == null) return null;

    // обновляем текущие объекты (для UI)
    synchronized (currentObjectsLock) {
      currentObjects.clear();
      currentObjects.addAll(trackedObjects);
    }

    if (enableDataCollection.get()) {
      collectDetectionsForExport(trackedObjects);
    }

    return trackedObjects;
  }

  // --- сбор per-frame bbox-строк в очередь (CSV) ---
  private void collectDetectionsForExport(List<TrackedObject> trackedObjects) {
    long tsMs = Instant.now().toEpochMilli();

    for (TrackedObject obj : trackedObjects) {
      long id = obj.getId();
      if (uniqueIdsSeen.add(id)) {
        uniqueObjectsCount.incrementAndGet();
      }

      int bx = obj.getRect().x();
      int by = obj.getRect().y();
      int bw = obj.getRect().width();
      int bh = obj.getRect().height();

      String entry = String.format("%d,%d,%d,%d,%d,%d\n",
          tsMs,
          id,
          bx, by, bw, bh
      );
      exportDetectionsBuffer.add(entry);
      exportDetectionsCount.incrementAndGet();
    }
  }

  // --- Экспорт детекций (CSV) ---
  public void exportDetectionsCsv(String filename) {
    if (exportDetectionsBuffer.isEmpty()) {
      System.out.println("Экспорт (detections): буфер пуст.");
      return;
    }
    ioExecutor.submit(() -> {
      try (FileWriter writer = new FileWriter(filename)) {
        writer.append(CSV_DETECTIONS_HEADER);
        String line;
        while ((line = exportDetectionsBuffer.poll()) != null) {
          writer.append(line);
        }
        exportDetectionsCount.set(0);
        System.out.println("Detections CSV exported: " + filename);
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }

  // --- Экспорт детекций (JSON) ---
  public void exportDetectionsJson(String filename) {
    if (exportDetectionsBuffer.isEmpty()) {
      System.out.println("Экспорт (detections JSON): буфер пуст.");
      return;
    }
    ioExecutor.submit(() -> {
      List<Object> rows = new ArrayList<>();
      String line;
      while ((line = exportDetectionsBuffer.poll()) != null) {
        // Парсим строку CSV вида: ts,id,class,x,y,w,h
        String[] parts = line.split(",", 7);
        if (parts.length >= 7) {
          long ts = Long.parseLong(parts[0]);
          long id = Long.parseLong(parts[1]);
          String cls = parts[2];
          int x = Integer.parseInt(parts[3]);
          int y = Integer.parseInt(parts[4]);
          int w = Integer.parseInt(parts[5]);
          int h = Integer.parseInt(parts[6].trim());
          java.util.Map<String, Object> m = new java.util.HashMap<>();
          m.put("timestamp_ms", ts);
          m.put("id", id);
          m.put("class", cls);
          m.put("bbox", java.util.Map.of("x", x, "y", y, "w", w, "h", h));
          rows.add(m);
        }
      }
      try (FileWriter writer = new FileWriter(filename)) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(rows, writer);
        exportDetectionsCount.set(0);
        System.out.println("Detections JSON exported: " + filename);
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }

  // --- Экспорт траекторий (CSV) ---
  public void exportTrajectoriesCsv(String filename) {
    if (trajectoryManager == null) {
      System.out.println("TrajectoryManager not provided — экспорт траекторий невозможен.");
      return;
    }
    ioExecutor.submit(() -> {
      try (FileWriter writer = new FileWriter(filename)) {
        writer.append(CSV_TRAJECTORY_HEADER);
        // snapshot trajectories
        Map<Integer, LinkedList<Point>> snapshot = getTrajectoriesSnapshot();
        for (Map.Entry<Integer, LinkedList<Point>> e : snapshot.entrySet()) {
          int id = e.getKey();
          LinkedList<Point> traj = e.getValue();
          if (traj == null || traj.isEmpty()) continue;
          long startTs = 0; // нет per-point timestamp в TrajectoryManager — можно записывать ts при хранении
          long endTs = 0;
          String dir = computeDirection(traj, directionMinDelta);
          // points as "(x1;y1)|(x2;y2)|..."
          StringBuilder pts = new StringBuilder();
          boolean first = true;
          for (Point p : traj) {
            if (!first) pts.append("|");
            pts.append(p.x()).append(";").append(p.y());
            first = false;
          }
          // экранируем запятые/специальные символы — помещаем весь поле в кавычки
          String pointsField = "\"" + pts.toString() + "\"";
          writer.append(String.format("%d,%d,%s,%s\n", id, startTs, endTs, pointsField));
        }
        System.out.println("Trajectories CSV exported: " + filename);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    });
  }

  // --- Экспорт траекторий (JSON) ---
  public void exportTrajectoriesJson(String filename) {
    if (trajectoryManager == null) {
      System.out.println("TrajectoryManager not provided — экспорт траекторий невозможен.");
      return;
    }
    ioExecutor.submit(() -> {
      try (FileWriter writer = new FileWriter(filename)) {
        List<Object> out = new ArrayList<>();
        Map<Integer, LinkedList<Point>> snapshot = getTrajectoriesSnapshot();
        for (Map.Entry<Integer, LinkedList<Point>> e : snapshot.entrySet()) {
          int id = e.getKey();
          LinkedList<Point> traj = e.getValue();
          if (traj == null || traj.isEmpty()) continue;
          String dir = computeDirection(traj, directionMinDelta);
          List<int[]> pts = new ArrayList<>(traj.size());
          for (Point p : traj) {
            pts.add(new int[]{p.x(), p.y()});
          }
          java.util.Map<String, Object> m = new java.util.HashMap<>();
          m.put("id", id);
          m.put("direction", dir);
          m.put("points", pts);
          out.add(m);
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(out, writer);
        System.out.println("Trajectories JSON exported: " + filename);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    });
  }

  // --- Получить "снимок" траекторий (копия) для безопасной записи ---
  private Map<Integer, LinkedList<Point>> getTrajectoriesSnapshot() {
    Map<Integer, LinkedList<Point>> out = new java.util.HashMap<>();
    // предполагаем, что TrajectoryManager.getTrajectories() возвращает Map<Integer,LinkedList<Point>>
    Map<Integer, CopyOnWriteArrayList<Point>> src = trajectoryManager.getTrajectories();
    for (Map.Entry<Integer, CopyOnWriteArrayList<Point>> e : src.entrySet()) {
      out.put(e.getKey(), new LinkedList<>(e.getValue())); // копия списка
    }
    return out;
  }

  // --- Простая эвристика направления по первым и последним точкам траектории ---
  private String computeDirection(LinkedList<Point> traj, int minDelta) {
    if (traj == null || traj.size() < 2) return "UNKNOWN";
    Point first = traj.getFirst();
    Point last = traj.getLast();
    int dx = last.x() - first.x();
    int dy = last.y() - first.y();
    if (Math.abs(dx) < minDelta && Math.abs(dy) < minDelta) return "STATIC";
    if (Math.abs(dx) >= Math.abs(dy)) {
      return dx > 0 ? "RIGHT" : "LEFT";
    } else {
      return dy > 0 ? "DOWN" : "UP";
    }
  }

  // --- UI ---
  @Override
  public void render() {
    if (!windowOpen.get()) return;
    ImGui.begin(getName(), windowOpen);

    if (ImGui.collapsingHeader(LocalizationManager.tr("statistic.name"))) {
      int curSize;
      synchronized (currentObjectsLock) {
        curSize = currentObjects.size();
      }
      ImGui.text(LocalizationManager.tr("analytic.current_count") + ": " + curSize);
      ImGui.text(LocalizationManager.tr("analytic.unique_count") + ": " + uniqueObjectsCount.get());
    }

    if (ImGui.collapsingHeader(LocalizationManager.tr("analytic.export.title"))) {
      ImGui.checkbox(LocalizationManager.tr("analytic.enable_data_collection"), enableDataCollection);
      ImGui.text("Размер буфера детекций: " + exportDetectionsCount.get() + " записей.");

      if (ImGui.button("Export Detections CSV")) {
        exportDetectionsCsv("detections_" + Instant.now().toEpochMilli() + ".csv");
      }
      ImGui.sameLine();
      if (ImGui.button("Export Detections JSON")) {
        exportDetectionsJson("detections_" + Instant.now().toEpochMilli() + ".json");
      }

      ImGui.separator();
      ImGui.text("Траектории:");
      ImGui.text("Trajectories manager: " + (trajectoryManager != null ? "present" : "missing"));
      if (ImGui.button("Export Trajectories CSV")) {
        exportTrajectoriesCsv("trajectories_" + Instant.now().toEpochMilli() + ".csv");
      }
      ImGui.sameLine();
      if (ImGui.button("Export Trajectories JSON")) {
        exportTrajectoriesJson("trajectories_" + Instant.now().toEpochMilli() + ".json");
      }
    }

    ImGui.end();
  }

  @Override public void show() { windowOpen.set(true); }
  @Override public void toggle() { windowOpen.set(!windowOpen.get()); }
  @Override public boolean isOpened() { return windowOpen.get(); }

  public boolean shouldDrawTrajectories() { return drawTrajectories.get(); }

  // полезные get'еры
  public long getUniqueObjectsCount() { return uniqueObjectsCount.get(); }
  public long getPendingDetectionsCount() { return exportDetectionsCount.get(); }

  public void shutdown() {
    ioExecutor.shutdownNow();
  }
}
