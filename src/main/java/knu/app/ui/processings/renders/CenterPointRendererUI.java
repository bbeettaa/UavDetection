package knu.app.ui.processings.renders;

import imgui.ImGui;
import imgui.type.ImInt;
import knu.app.bll.processors.draw.CenterPointRenderer;
import knu.app.bll.utils.LocalizationManager;

public class CenterPointRendererUI extends RendererUI {
    private final ImInt radius;
    private final CenterPointRenderer renderer;

    public CenterPointRendererUI() {
        super(new CenterPointRenderer(), LocalizationManager.tr("processor.draw.type.point"));
        renderer = (CenterPointRenderer) super.getRenderer();
        radius = new ImInt(renderer.getR());
    }

    @Override
    public void renderSettings(String id) {
        if (ImGui.sliderInt(LocalizationManager.tr("processor.draw.point.radius")  + "##" + id,
                radius.getData(), 1, 50))
            renderer.setR(radius.get());
        super.renderSettings("c1");
    }
}
