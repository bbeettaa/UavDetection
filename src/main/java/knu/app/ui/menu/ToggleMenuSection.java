package knu.app.ui.menu;

import imgui.ImGui;
import knu.app.ui.tools.UIModule;

public class ToggleMenuSection implements MenuSection {
    private final UIModule<?> ui;
    public ToggleMenuSection(UIModule<?> ui){
        this.ui = ui;
    }

    @Override
    public String getLabel() {
        return ui.getName();
    }

    @Override
    public void render() {
        if (ImGui.menuItem(getLabel())) {
            ui.show();
        }
    }
}

