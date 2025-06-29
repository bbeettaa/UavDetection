package knu.app.bll.utils.processors.hog;

import org.bytedeco.opencv.opencv_core.Size;

public class HogSvmDetectorConfig {
    private double hitThreshold;
    private double finalThreshold;
    private double scale;
    private Size winStride;
    private Size padding;
    private boolean useMeanShiftGrouping;
    private double weightThreshold;
    private float scoreThreshold;
    private float nmsThreshold;
    private int groupThreshold;
    private double eps;

    public HogSvmDetectorConfig(double hitThreshold, double finalThreshold, double scale, Size winStride, Size padding,
                                boolean useMeanShiftGrouping, double weightThreshold, float scoreThreshold, float nmsThreshold,
                                int groupThreshold, double eps) {
        this.hitThreshold = hitThreshold;
        this.finalThreshold = finalThreshold;
        this.scale = scale;
        this.winStride = winStride;
        this.padding = padding;
        this.useMeanShiftGrouping = useMeanShiftGrouping;
        this.weightThreshold = weightThreshold;
        this.scoreThreshold = scoreThreshold;
        this.nmsThreshold = nmsThreshold;
        this.groupThreshold = groupThreshold;
        this.eps = eps;
    }

    public static HogSvmDetectorConfig withDefaultConfig() {
        return new HogSvmDetectorConfig(
                2.94,
                0.0,
                1.10,
                new Size(8, 8),
                new Size(32, 32),
                false,
                0.0,
                0.0f,
                1.0f,
                5,
                0.25);
    }

    public static HogSvmDetectorConfig withTestConfig() {
        return new HogSvmDetectorConfig(
                2.0,
                0.0,
                1.1,
                new Size(8, 8),
                new Size(32, 32),
                false,
                5.0,
                0.0f,
                0.4f,
                5,
                0.5);
    }

    public static HogSvmDetectorConfig withTestConfig1() {
        return new HogSvmDetectorConfig(
                2.43,
                0.0,
                1.05,
                new Size(8, 8),
                new Size(32, 32),
                false,
                0.0,
                0.0f,
                0.0f,
                20,
                0.84);
    }



    public double getHitThreshold() {
        return hitThreshold;
    }

    public double getFinalThreshold() {
        return finalThreshold;
    }

    public double getScale() {
        return scale;
    }

    public Size getWinStride() {
        return winStride;
    }

    public Size getPadding() {
        return padding;
    }

    public boolean isUseMeanShiftGrouping() {
        return useMeanShiftGrouping;
    }

    public double getWeightThreshold() {
        return weightThreshold;
    }

    public float getScoreThreshold() {
        return scoreThreshold;
    }

    public float getNmsThreshold() {
        return nmsThreshold;
    }

    public int getGroupThreshold() {
        return groupThreshold;
    }

    public double getEps() {
        return eps;
    }


    public void setHitThreshold(double hitThreshold) {
        this.hitThreshold = hitThreshold;
    }

    public void setFinalThreshold(double finalThreshold) {
        this.finalThreshold = finalThreshold;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public void setWinStride(Size winStride) {
        this.winStride = winStride;
    }

    public void setPadding(Size padding) {
        this.padding = padding;
    }

    public void setUseMeanShiftGrouping(boolean useMeanShiftGrouping) {
        this.useMeanShiftGrouping = useMeanShiftGrouping;
    }

    public void setWeightThreshold(double weightThreshold) {
        this.weightThreshold = weightThreshold;
    }

    public void setScoreThreshold(float scoreThreshold) {
        this.scoreThreshold = scoreThreshold;
    }

    public void setNmsThreshold(float nmsThreshold) {
        this.nmsThreshold = nmsThreshold;
    }

    public void setGroupThreshold(int groupThreshold) {
        this.groupThreshold = groupThreshold;
    }

    public void setEps(double eps) {
        this.eps = eps;
    }
}