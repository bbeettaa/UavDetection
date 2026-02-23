package knu.app.bll.algorithms.feature.anomaly;

import knu.app.bll.algorithms.feature.ObjectState;
import java.util.List;

public class TextureChangeAnomaly implements AnomalyClassifier {

    private static final double BRIGHTNESS_THRESHOLD = 50.0;
    private static final double CONTRAST_THRESHOLD = 20.0;

    @Override
    public boolean detectAnomaly(List<ObjectState> history) {
        if (history.size() < 5) return false;

        ObjectState current = history.getLast();

        double avgBrightness = 0;
        double avgStdDev = 0;
        int window = Math.min(history.size() - 1, 10);

        for (int i = history.size() - window - 1; i < history.size() - 1; i++) {
            avgBrightness += history.get(i).brightnessMean;
            avgStdDev += history.get(i).textureStdDev;
        }
        avgBrightness /= window;
        avgStdDev /= window;

        if (Math.abs(current.brightnessMean - avgBrightness) > BRIGHTNESS_THRESHOLD) {
            current.anomalyDescription = "TEXTURE_BRIGHTNESS";
            return true;
        }

        if (Math.abs(current.textureStdDev - avgStdDev) > CONTRAST_THRESHOLD) {
            current.anomalyDescription = "TEXTURE_CONTRAST";
            return true;
        }

        return false;
    }

    @Override
    public String getMethodName() {
        return "Texture Analysis";
    }
}