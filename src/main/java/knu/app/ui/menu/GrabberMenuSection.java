package knu.app.ui.menu;

import imgui.ImGui;
import knu.app.ui.LocalizationManager;
import knu.app.ui.tools.UIModule;
import knu.app.ui.tools.VideoGrabber;
import org.bytedeco.javacv.Frame;

public class GrabberMenuSection implements MenuSection {

    private final UIModule<Frame> ui;
    public GrabberMenuSection(UIModule<Frame> ui){
        this.ui = ui;
    }

    @Override
    public String getLabel() {
        return LocalizationManager.tr("menu.grabber.name");
    }

    @Override
    public void render() {
        if (ImGui.menuItem(getLabel())) {
            ui.show();
        }
    }
}
