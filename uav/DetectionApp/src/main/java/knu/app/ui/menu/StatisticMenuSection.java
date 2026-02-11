package knu.app.ui.menu;

import imgui.ImGui;
import knu.app.bll.utils.LocalizationManager;
import knu.app.ui.modules.UIModule;

public class StatisticMenuSection implements MenuSection {
    private final UIModule<?> ui;

    public StatisticMenuSection(UIModule<?> ui) {
        this.ui = ui;
    }

    @Override
    public String getLabel() {
        return ui.getName();
    }

    @Override
    public void render() {
        if (ImGui.menuItem(LocalizationManager.tr("statistic.show"), "", ui.isOpened())) {
            ui.toggle();
        }
    }
}
