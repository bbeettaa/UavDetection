package knu.app.ui.processings.renders;

import imgui.ImGui;
import imgui.type.ImInt;
import knu.app.bll.processors.draw.KeypointsRenderer;
import knu.app.bll.utils.LocalizationManager;

public class FeaturedPointsRendererUI extends RendererUI {
    private final ImInt radius;
    private final KeypointsRenderer renderer;

    public FeaturedPointsRendererUI() {
        super(new KeypointsRenderer(), LocalizationManager.tr("processor.draw.type.keypoints"));
        renderer = (KeypointsRenderer) super.getRenderer();
        radius = new ImInt(renderer.getR());
    }

    @Override
    public void renderSettings(String id) {
        if (ImGui.sliderInt(LocalizationManager.tr("processor.draw.point.radius") + "##" + id ,
                radius.getData(), 1, 50))
            renderer.setR(radius.get());
        super.renderSettings("r1");
    }
}
