package knu.app.ui.processings.renders;

import imgui.ImGui;
import imgui.type.ImFloat;
import imgui.type.ImInt;
import knu.app.bll.processors.draw.DetectionRenderer;
import knu.app.bll.utils.LocalizationManager;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.Scalar;

import java.util.List;

public abstract class RendererUI {
    private final DetectionRenderer renderer;
    private final String name;
    private final ImFloat trackingColorR;
    private final ImFloat trackingColorG;
    private final ImFloat trackingColorB;
    private final ImInt thickness;
    private final ImInt lineType;
    private Scalar scalar;


    public RendererUI(DetectionRenderer renderer, String name) {
        this.renderer = renderer;
        this.name = name;

        this.trackingColorR = new ImFloat((float) renderer.getScalar().red());
        this.trackingColorG = new ImFloat((float) renderer.getScalar().green());
        this.trackingColorB = new ImFloat((float) renderer.getScalar().blue());
        this.thickness = new ImInt(renderer.getThick());
        this.lineType = new ImInt(renderer.getType());
        this.scalar = renderer.getScalar();
    }

    public String getName(){
        return name;
    }

    public void renderSettings(String id) {
        ImGui.sliderInt(LocalizationManager.tr("processor.draw.thickness") + "##" + id, thickness.getData(), -1, 32);
        ImGui.sliderInt(LocalizationManager.tr("processor.draw.linetype") + "##" + id, lineType.getData(), -1, 32);

        if(ImGui.sliderFloat(LocalizationManager.tr("processor.draw.color.r") + "##" + id, trackingColorR.getData(), 0, 255))
            scalar = new Scalar(trackingColorB.get(), trackingColorG.get(), trackingColorR.get(), 1);
        if(ImGui.sliderFloat(LocalizationManager.tr("processor.draw.color.g") + "##" + id, trackingColorG.getData(), 0, 250))
            scalar = new Scalar(trackingColorB.get(), trackingColorG.get(), trackingColorR.get(), 1);
        if(ImGui.sliderFloat(LocalizationManager.tr("processor.draw.color.b") + "##" + id, trackingColorB.getData(), 0, 255))
            scalar = new Scalar(trackingColorB.get(), trackingColorG.get(), trackingColorR.get(), 1);
    }

    public void render(Mat mat, List<Point2f> detections) {
        renderer.render(mat, detections, scalar, thickness.get(), lineType.get());
    }

    public void render(Mat mat, List<Rect> rects, List<Double> scores, boolean renderScores) {
        renderer.render(mat, rects, scores, renderScores, scalar, thickness.get(), lineType.get());
    }

    public DetectionRenderer getRenderer() {
        return renderer;
    }
}
