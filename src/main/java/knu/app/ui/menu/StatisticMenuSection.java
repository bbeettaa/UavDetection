package knu.app.ui.menu;

import imgui.ImGui;
import knu.app.ui.LocalizationManager;
import knu.app.ui.tools.UIModule;

public class StatisticMenuSection implements MenuSection {
    private final UIModule ui;

    public StatisticMenuSection(UIModule ui) {
        this.ui = ui;
    }

    @Override
    public String getLabel() {
        return ui.getName();
    }

    @Override
    public void render() {
        if (ImGui.menuItem(LocalizationManager.tr("fps.show"), "", ui.isOpened())) {
            ui.toggle();
        }
    }
}
