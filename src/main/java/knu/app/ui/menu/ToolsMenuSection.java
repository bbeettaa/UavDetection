//package knu.app.ui.menu;
//
//import imgui.ImGui;
//import knu.app.ui.LocalizationManager;
//import knu.app.ui.tools.UIModule;
//
//import java.util.List;
//
//public class ToolsMenuSection implements MenuSection {
//    private final List<MenuSection> tools;
//
//    public ToolsMenuSection(List<MenuSection> tools) {
//        this.tools = tools;
//    }
//
//    @Override
//    public String getLabel() {
//        return LocalizationManager.tr("menu.instruments.name");
//    }
//
//    @Override
//    public void render() {
//        for (MenuSection tool : tools) {
//
//            if (ImGui.menuItem(tool.getLabel())) {
//
//            }
//        }
//    }
//}
