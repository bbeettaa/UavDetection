package knu.app.ui.menu;

import imgui.ImGui;
import knu.app.ui.modules.UIModule;

public class ActionMenuSection implements MenuSection {
    private final String label;
    private final Runnable action;

    public ActionMenuSection(String label, Runnable action) {
        this.label = label;
        this.action = action;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void render() {
        if (ImGui.menuItem(label)) {
            action.run();
        }
    }
}