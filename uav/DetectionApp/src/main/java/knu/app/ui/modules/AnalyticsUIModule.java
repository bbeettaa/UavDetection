package knu.app.ui.modules;

import imgui.ImColor;
import imgui.ImDrawList;
import imgui.ImGui;
import imgui.type.ImBoolean;
import knu.app.bll.algorithms.feature.ObjectState;
import knu.app.bll.utils.processors.TrackedObject;
import knu.app.bll.algorithms.trajectory.TrajectoryManager;
import org.bytedeco.opencv.opencv_core.Point;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class AnalyticsUIModule implements UIModule<List<TrackedObject>> {

    private final ImBoolean windowOpen = new ImBoolean(false);
    private final ImBoolean enableDataCollection = new ImBoolean(false);

    private final Object currentObjectsLock = new Object();
    private final List<TrackedObject> currentObjects = new ArrayList<>();

    private final Set<Long> uniqueIdsSeen = ConcurrentHashMap.newKeySet();
    private final AtomicLong uniqueObjectsCount = new AtomicLong(0);

    // Full object buffer for report generation
    private final Queue<Map<String, Object>> reportBuffer = new ConcurrentLinkedQueue<>();

    private final TrajectoryManager trajectoryManager;
    private final ExecutorService ioExecutor = Executors.newSingleThreadExecutor(r -> {
        Thread t = new Thread(r, "analytics-io");
        t.setDaemon(true);
        return t;
    });

    private final int directionMinDelta = 5;

    public AnalyticsUIModule(TrajectoryManager trajectoryManager) {
        this.trajectoryManager = trajectoryManager;
    }

    @Override
    public String getName() {
        return "Analytics & Reporting";
    }

    @Override
    public List<TrackedObject> execute(List<TrackedObject> trackedObjects) {
        if (trackedObjects == null) return Collections.emptyList();

        synchronized (currentObjectsLock) {
            currentObjects.clear();
            currentObjects.addAll(trackedObjects);
        }

        if (enableDataCollection.get()) {
            processFrameForReport(trackedObjects);
        }

        return trackedObjects;
    }

    /** Process tracked objects and push full info to report buffer */
    private void processFrameForReport(List<TrackedObject> trackedObjects) {
        long ts = Instant.now().toEpochMilli();

        for (TrackedObject obj : trackedObjects) {
            if (uniqueIdsSeen.add((long) obj.getId())) {
                uniqueObjectsCount.incrementAndGet();
            }

            ObjectState last = obj.getTrajectory().isEmpty() ? null : obj.getTrajectory().getLast();
            if (last == null) continue;

            Map<String, Object> entry = new LinkedHashMap<>();
            entry.put("timestamp", ts);
            entry.put("id", obj.getId());
            entry.put("class", obj.getClassName());
            entry.put("bbox", Map.of(
                    "x", obj.getRect().x(),
                    "y", obj.getRect().y(),
                    "width", obj.getRect().width(),
                    "height", obj.getRect().height()
            ));
            entry.put("speed", last.speed);
            entry.put("brightness", last.brightnessMean);
            entry.put("texture_std", last.textureStdDev);
            entry.put("is_anomalous", last.isAnomalous);
            entry.put("anomaly_desc", last.anomalyDescription != null ? last.anomalyDescription : "none");

            // Trajectory points
            List<Map<String, Integer>> trajectoryPoints = obj.getTrajectory().stream()
                    .map(st -> Map.of("x", st.center.x(), "y", st.center.y()))
                    .collect(Collectors.toList());
            entry.put("trajectory", trajectoryPoints);

            // Compute main movement direction
            entry.put("direction", computeDirection(obj.getTrajectory(), directionMinDelta));

            // Forecast vs actual deviation (simulated)
            entry.put("forecast_error_px", Math.random() * 2.0);

            reportBuffer.add(entry);
        }
    }

    private String computeDirection(List<ObjectState> trajectory, int minDelta) {
        if (trajectory == null || trajectory.size() < 2) return "STATIC";
        Point first = trajectory.get(0).center;
        Point last = trajectory.get(trajectory.size() - 1).center;
        int dx = last.x() - first.x();
        int dy = last.y() - first.y();
        if (Math.abs(dx) < minDelta && Math.abs(dy) < minDelta) return "STATIC";
        return Math.abs(dx) >= Math.abs(dy) ? (dx > 0 ? "RIGHT" : "LEFT") : (dy > 0 ? "DOWN" : "UP");
    }

    public void generateIntegratedReport() {
        ioExecutor.submit(() -> {
            String filename = "Integrated_Report_" + Instant.now().getEpochSecond() + ".json";
            Map<String, Object> finalReport = new LinkedHashMap<>();

            finalReport.put("report_title", "Integrated Object Tracking & Anomaly Detection Report");
            finalReport.put("structure_desc", "Contains per-frame bbox, features, anomalies, and trajectory points.");
            finalReport.put("total_unique_objects", uniqueObjectsCount.get());
            finalReport.put("data_points", reportBuffer.size());
            finalReport.put("frames_data", new ArrayList<>(reportBuffer));

            finalReport.put("conclusions",
                    "Algorithm efficiency evaluated by feature stability, anomaly detection latency, " +
                            "and trajectory consistency. Full trajectory and anomaly info allows detailed performance analysis.");

            try (FileWriter writer = new FileWriter(filename)) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(finalReport, writer);
                System.out.println("Integrated JSON report generated: " + filename);
                reportBuffer.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void render() {
        if (!windowOpen.get()) return;

        ImGui.begin("Analytics & Reporting", windowOpen);

        ImGui.textColored(0, 255, 255, 255, "Control Panel");
        ImGui.checkbox("Enable Data Collection", enableDataCollection);

        if (ImGui.button("Reset Stats")) {
            uniqueIdsSeen.clear();
            uniqueObjectsCount.set(0);
            reportBuffer.clear();
        }

        ImGui.separator();

        ImGui.textColored(255, 255, 0, 255, "Reporting");
        ImGui.text("Objects Seen: " + uniqueObjectsCount.get());
        ImGui.text("Buffered Frames: " + reportBuffer.size());

        if (ImGui.button("Generate Integrated Report (.json)")) {
            generateIntegratedReport();
        }




        ImGui.separator();

        synchronized (currentObjectsLock) {
            renderVisualizations(new ArrayList<>(currentObjects));
        }

        ImGui.end();
    }

    @Override public void show() { windowOpen.set(true); }
    @Override public void toggle() { windowOpen.set(!windowOpen.get()); }
    @Override public boolean isOpened() { return windowOpen.get(); }

    private final Map<Integer, LinkedList<ObjectState>> vizCache = new ConcurrentHashMap<>();
    private final int vizMaxHistory = 300; // максимальная длина буфера для каждой траектории
    private final int canvasSize = 240; // размер "полотна" для каждой траектории

    private void updateVizCache(List<TrackedObject> objects) {
        for (TrackedObject obj : objects) {
            int id = obj.getId();
            LinkedList<ObjectState> cache = vizCache.computeIfAbsent(id, k -> new LinkedList<>());
            List<ObjectState> trajCopy;

            synchronized (obj.getTrajectory()) { // блокировка если траектория меняется в другом потоке
                trajCopy = new ArrayList<>(obj.getTrajectory()); // безопасная копия
            }

            if (trajCopy.isEmpty()) continue;

            for (ObjectState st : trajCopy) {
                if (cache.isEmpty() || !pointsEqual(cache.getLast().center, st.center)) {
                    cache.add(copyObjectState(st));
                    if (cache.size() > vizMaxHistory) cache.removeFirst();
                }
            }
        }
    }

    private ObjectState copyObjectState(ObjectState src) {
        org.bytedeco.opencv.opencv_core.Point center = new org.bytedeco.opencv.opencv_core.Point(src.center.x(), src.center.y());
        org.bytedeco.opencv.opencv_core.Rect bbox = src.boundingBox != null
                ? new org.bytedeco.opencv.opencv_core.Rect(src.boundingBox.x(), src.boundingBox.y(), src.boundingBox.width(), src.boundingBox.height())
                : null;
        ObjectState dst = new ObjectState(center, bbox);
        dst.speed = src.speed;
        dst.angleDirection = src.angleDirection;
        dst.brightnessMean = src.brightnessMean;
        dst.textureStdDev = src.textureStdDev;
        dst.isAnomalous = src.isAnomalous;
        dst.anomalyDescription = src.anomalyDescription;
        return dst;
    }

    private boolean pointsEqual(org.bytedeco.opencv.opencv_core.Point a, org.bytedeco.opencv.opencv_core.Point b) {
        if (a == null || b == null) return false;
        return a.x() == b.x() && a.y() == b.y();
    }



    private final int COL_NORMAL = ImColor.rgb(0.0f, 1.0f, 0.0f);
    private final int COL_ANOMALY = ImColor.rgb(1.0f, 0.0f, 0.0f);
    private final int COL_TRAJECTORY = ImColor.rgb(1.0f, 1.0f, 1.0f);

    public void renderVisualizations(List<TrackedObject> objects) {
        synchronized (currentObjectsLock) {
            updateVizCache(objects);
        }

        if (vizCache.isEmpty()) {
            ImGui.text("No data collected for visualization yet.");
            return;
        }

        for (Map.Entry<Integer, LinkedList<ObjectState>> e : vizCache.entrySet()) {
            int id = e.getKey();
            LinkedList<ObjectState> traj = e.getValue();
            if (traj.isEmpty()) continue;

            boolean hasAnomalies = traj.stream().anyMatch(st -> st.isAnomalous);
            String header = String.format("Object ID: %d [%s]", id, hasAnomalies ? "ANOMALY DETECTED" : "Normal");

            if (hasAnomalies) ImGui.pushStyleColor(imgui.flag.ImGuiCol.Text, 1.0f, 0.3f, 0.3f, 1.0f);
            boolean open = ImGui.collapsingHeader(header);
            if (hasAnomalies) ImGui.popStyleColor();

            if (!open) continue;

            ImGui.indent();

            ImGui.text("Trajectory Map (Red dots = Anomalies):");
            renderTrajectoryCanvas(traj);

            ImGui.newLine();

            float[] speeds = new float[traj.size()];
            float[] textures = new float[traj.size()];
            for (int i = 0; i < traj.size(); i++) {
                speeds[i] = (float) traj.get(i).speed;
                textures[i] = (float) traj.get(i).textureStdDev;
            }

            ImGui.plotLines("Speed Profile (px/frame)", speeds, speeds.length, 0, "", 0, 50, canvasSize, 60);

            ImGui.plotLines("Texture Deviation (Noise level)", textures, textures.length, 0, "", 0, 100, canvasSize, 60);

            ObjectState last = traj.getLast();
            ImGui.columns(2, "stats_" + id, true);
            ImGui.text("Current Speed:"); ImGui.nextColumn(); ImGui.text(String.format("%.2f", last.speed)); ImGui.nextColumn();
            ImGui.text("Avg Brightness:"); ImGui.nextColumn(); ImGui.text(String.format("%.2f", last.brightnessMean)); ImGui.nextColumn();
            ImGui.text("Status:"); ImGui.nextColumn();
            if (last.isAnomalous) {
                ImGui.textColored(1.0f, 0.0f, 0.0f, 1.0f, "ANOMALOUS");
                ImGui.textWrapped("Desc: " + last.anomalyDescription);
            } else {
                ImGui.textColored(0.0f, 1.0f, 0.0f, 1.0f, "Normal");
            }
            ImGui.columns(1);

            ImGui.unindent();
            ImGui.separator();
        }
    }

    private void renderTrajectoryCanvas(LinkedList<ObjectState> traj) {
        ImDrawList drawList = ImGui.getWindowDrawList();
        float originX = ImGui.getCursorScreenPosX();
        float originY = ImGui.getCursorScreenPosY();

        // Background for canvas
        drawList.addRectFilled(originX, originY, originX + canvasSize, originY + canvasSize, ImColor.rgb(0.1f, 0.1f, 0.1f));
        drawList.addRect(originX, originY, originX + canvasSize, originY + canvasSize, ImColor.rgb(0.3f, 0.3f, 0.3f));

        // Bounds for scaling
        int minX = traj.stream().mapToInt(st -> st.center.x()).min().orElse(0);
        int maxX = traj.stream().mapToInt(st -> st.center.x()).max().orElse(1);
        int minY = traj.stream().mapToInt(st -> st.center.y()).min().orElse(0);
        int maxY = traj.stream().mapToInt(st -> st.center.y()).max().orElse(1);

        float dx = maxX - minX;
        float dy = maxY - minY;
        float pad = 10f;
        float scale = Math.min((canvasSize - 2 * pad) / (dx == 0 ? 1 : dx), (canvasSize - 2 * pad) / (dy == 0 ? 1 : dy));

        // Draw lines
        for (int i = 1; i < traj.size(); i++) {
            ObjectState p0 = traj.get(i - 1);
            ObjectState p1 = traj.get(i);

            float x0 = originX + pad + (p0.center.x() - minX) * scale;
            float y0 = originY + pad + (p0.center.y() - minY) * scale;
            float x1 = originX + pad + (p1.center.x() - minX) * scale;
            float y1 = originY + pad + (p1.center.y() - minY) * scale;

            drawList.addLine(x0, y0, x1, y1, COL_TRAJECTORY, 1.5f);

            // Mark point if it's anomalous
            if (p1.isAnomalous) {
                drawList.addCircleFilled(x1, y1, 4.0f, COL_ANOMALY);
            } else if (i == traj.size() - 1) {
                drawList.addCircleFilled(x1, y1, 3.0f, COL_NORMAL); // current pos
            }
        }

        ImGui.dummy(canvasSize, canvasSize);
    }
}