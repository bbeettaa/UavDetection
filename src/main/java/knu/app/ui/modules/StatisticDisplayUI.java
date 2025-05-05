package knu.app.ui.modules;

import imgui.ImGui;
import imgui.flag.ImGuiCond;
import imgui.flag.ImGuiWindowFlags;
import imgui.type.ImBoolean;
import knu.app.bll.utils.LocalizationManager;

public class StatisticDisplayUI implements UIModule<Void> {
    private final ImBoolean windowOpen = new ImBoolean(false);

    public StatisticDisplayUI() {
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

        ImGui.setNextWindowPos(0, 18, ImGuiCond.Always);
        ImGui.setNextWindowBgAlpha(0.35f);
        ImGui.begin(LocalizationManager.tr("statistic.menu.name"), windowOpen,
                ImGuiWindowFlags.NoDecoration | ImGuiWindowFlags.AlwaysAutoResize);
        ImGui.text(String.format("%s: %.0f", LocalizationManager.tr("statistic.framerate.name"), ImGui.getIO().getFramerate()));
        ImGui.end();

    }


}

