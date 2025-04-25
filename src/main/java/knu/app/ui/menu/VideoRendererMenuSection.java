package knu.app.ui.menu;

import imgui.ImGui;
import knu.app.ui.LocalizationManager;
import knu.app.ui.tools.UIModule;
import knu.app.ui.tools.VideoGrabber;
import knu.app.ui.tools.VideoRenderer;
import org.bytedeco.javacv.Frame;

public class VideoRendererMenuSection implements MenuSection {

    private final UIModule<?> ui;
    public VideoRendererMenuSection(UIModule<?> ui){
        this.ui = ui;
    }

    @Override
    public String getLabel() {
        return LocalizationManager.tr("menu.renderer.name");
    }

    @Override
    public void render() {
        if (ImGui.menuItem(getLabel())) {
            ui.show();
        }
    }
}
