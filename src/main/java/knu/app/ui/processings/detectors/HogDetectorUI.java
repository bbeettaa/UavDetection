package knu.app.ui.processings.detectors;

import imgui.ImGui;
import imgui.type.ImBoolean;
import knu.app.bll.processors.detection.HogSvmDetector;
import knu.app.bll.processors.detection.ObjectDetector;
import knu.app.bll.utils.LocalizationManager;
import knu.app.bll.utils.processors.DetectionResult;
import knu.app.bll.utils.processors.hog.HogSvmDetectorConfig;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;

public class HogDetectorUI implements DetectorUI {
    private final HogSvmDetector hog;

    private final float[] hitThreshold;
    private final float[] finalThreshold;
    private final int[] winStride;
    private final int[] padding;
    private final float[] scale;
    private final ImBoolean meanShift;

    private final int[] groupThreshold;
    private final float[] eps;

    private final float[] scoreThreshold;
    private final float[] nmsThreshold;

    private final float[] weightThreshold;

    public HogDetectorUI(HogSvmDetector hog) {
        this.hog = hog;
        HogSvmDetectorConfig c = this.hog.getConfig();
        hitThreshold = new float[]{(float) c.getHitThreshold()};
        finalThreshold = new float[]{(float) c.getFinalThreshold()};
        winStride = new int[]{c.getWinStride().width(), c.getWinStride().height()};
        padding = new int[]{c.getPadding().width(), c.getPadding().width()};
        scale = new float[]{(float) c.getScale()};
        meanShift = new ImBoolean(c.isUseMeanShiftGrouping());

        groupThreshold = new int[]{c.getGroupThreshold()};
        eps = new float[]{(float) c.getEps()};

        scoreThreshold = new float[]{c.getScoreThreshold()};
        nmsThreshold = new float[]{c.getNmsThreshold()};
        weightThreshold = new float[]{(float) c.getWeightThreshold()};
    }

    @Override
    public void renderSettings() {
        HogSvmDetectorConfig c = this.hog.getConfig();

        ImGui.text(LocalizationManager.tr("processor.detectors.hog.roisettings"));
        if (ImGui.sliderFloat(LocalizationManager.tr("processor.detectors.hog.hitthreshold"), hitThreshold, 0, 10, "%.2f")) c.setHitThreshold(hitThreshold[0]);
        if (ImGui.sliderFloat(LocalizationManager.tr("processor.detectors.hog.finalthreshold"), finalThreshold, 0, 10, "%.2f")) c.setFinalThreshold(finalThreshold[0]);
        if (ImGui.dragInt2(LocalizationManager.tr("processor.detectors.hog.winStride"), winStride, 1, 1, 512)) c.setWinStride(new Size(winStride[0], winStride[1]));
        if (ImGui.dragInt2(LocalizationManager.tr("processor.detectors.hog.padding"), padding, 1, 1, 512)) c.setPadding(new Size(padding[0], padding[1]));
        if (ImGui.sliderFloat(LocalizationManager.tr("processor.detectors.hog.scale"), scale, 1.0f, 2.0f, "%.2f")) c.setScale(scale[0]);
        if (ImGui.checkbox(LocalizationManager.tr("processor.detectors.hog.meanShift"), meanShift)) c.setUseMeanShiftGrouping(meanShift.get());

        ImGui.text(LocalizationManager.tr("processor.detectors.hog.groupsettings"));
        ImGui.text(LocalizationManager.tr("processor.detectors.hog.roisettings"));
        if (ImGui.sliderInt(LocalizationManager.tr("processor.detectors.hog.groupThreshold"), groupThreshold, 0, 20)) c.setGroupThreshold(groupThreshold[0]);
        if (ImGui.sliderFloat(LocalizationManager.tr("processor.detectors.hog.eps"), eps, 0, 2, "%.2f")) c.setEps(eps[0]);

        ImGui.text(LocalizationManager.tr("processor.detectors.hog.nmssettings"));
        if (ImGui.sliderFloat(LocalizationManager.tr("processor.detectors.hog.scoreThreshold"), scoreThreshold, 0, 10, "%.2f")) c.setScoreThreshold(scoreThreshold[0]);
        if (ImGui.sliderFloat(LocalizationManager.tr("processor.detectors.hog.nmsThreshold"), nmsThreshold, 0, 10, "%.2f")) c.setNmsThreshold(nmsThreshold[0]);

        ImGui.text(LocalizationManager.tr("processor.detectors.hog.scorefilter"));
        if (ImGui.sliderFloat(LocalizationManager.tr("processor.detectors.hog.weightThreshold"), weightThreshold, 0, 10, "%.2f")) c.setWeightThreshold(weightThreshold[0]);

    }

    @Override
    public String getName() {
        return LocalizationManager.tr("processor.detectors.hog");
    }

    @Override
    public DetectionResult detect(Mat mat) {
        return hog.detect(mat);
    }

    @Override
    public ObjectDetector getDetector() {
        return hog;
    }
}
