package knu.app.ui;

import imgui.ImGui;
import knu.app.ui.menu.MainMenuSection;
import knu.app.ui.menu.MenuSection;
import knu.app.ui.modules.UIModule;

public class MainMenuUI implements UIModule<Void> {
    private final MainMenuSection[] mainSections;

    public MainMenuUI(MainMenuSection... mainSections) {
        this.mainSections = mainSections;
    }

    @Override
    public String getName() {
        return "Main Menu";
    }

    @Override
    public Void execute(Void unused) {
        return null;
    }

    @Override
    public void show() {

    }

    @Override
    public void toggle() {

    }

    @Override
    public boolean isOpened() {
        return true;
    }

    @Override
    public void render() {
        if (ImGui.beginMainMenuBar()) {
            for (MainMenuSection s : mainSections) {
                if (ImGui.beginMenu(s.sectionName())) {
                    for (MenuSection section : s.sections()) {
                        section.render();
                    }
                    ImGui.endMenu();
                }
            }
            ImGui.endMainMenuBar();
        }
    }

}
