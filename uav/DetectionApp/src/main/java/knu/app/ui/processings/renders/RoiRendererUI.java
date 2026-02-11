package knu.app.ui.processings.renders;

import knu.app.bll.processors.draw.ROIRenderer;
import knu.app.bll.utils.LocalizationManager;

public class RoiRendererUI extends RendererUI {

    public RoiRendererUI() {
        super(new ROIRenderer(), LocalizationManager.tr("processor.draw.type.roi"));
    }
}
