package knu.app.ui.modules;

import imgui.ImGui;
import imgui.flag.ImGuiCond;
import imgui.flag.ImGuiWindowFlags;
import imgui.type.ImBoolean;
import java.util.concurrent.CompletableFuture;
import knu.app.bll.utils.LocalizationManager;
import knu.app.bll.utils.SystemStats;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;

public class StatisticDisplayUI implements UIModule<Void> {
    private final ImBoolean windowOpen = new ImBoolean(false);
    private static final StatisticDisplayUI ent = new StatisticDisplayUI();

    private double fps = 0;
    long frameCount = 0;
    SystemStats stats = new SystemStats();

    private long lastTime = System.nanoTime();
    long currentTime;

    public static StatisticDisplayUI getEntity() {
        return ent;
    }

    private StatisticDisplayUI() {
    }

    @Override
    public String getName() {
        return LocalizationManager.tr("statistic.name");
    }

    @Override
    public void toggle() {
        windowOpen.set(!windowOpen.get());
    }

    @Override
    public boolean isOpened() {
        return windowOpen.get();
    }


    @Override
    public Void execute(Void o) {
        return null;
    }

    @Override
    public void show() {

    }

    @Override
    public void render() {
        if (!windowOpen.get()) return;


        ImGui.setNextWindowPos(ImGui.getWindowSizeX() - 70, 18 * 3, ImGuiCond.Always);
        ImGui.setNextWindowBgAlpha(0.35f);
        ImGui.begin(LocalizationManager.tr("statistic.menu.name"), windowOpen,
                ImGuiWindowFlags.NoDecoration | ImGuiWindowFlags.AlwaysAutoResize);

        ImGui.text(String.format("%s: %.0f", LocalizationManager.tr("statistic.framerate.name"), ImGui.getIO().getFramerate()));
        ImGui.text(String.format("%s: %.0f", LocalizationManager.tr("statistic.fps.name"), fps));
        ImGui.text(String.format("CPU: %.1f%%", stats.getCpuLoadPercent()));
        ImGui.text(String.format("RAM: %d / %d MB", stats.getUsedMemoryMB(), stats.getTotalMemoryMB()));

        ImGui.end();

    }


    public void setCurrentTime() {
        currentTime = System.nanoTime();
    }

    public void processFPS() {
        frameCount++;
        double elapsedTime = (currentTime - lastTime) / 1_000_000_000.0;
        if (elapsedTime >= 1.0) {
            fps = frameCount / elapsedTime;
            lastTime = currentTime;
            frameCount = 0;
        }
    }



}

