package knu.app.bll.algorithms.feature.anomaly;

import knu.app.bll.algorithms.feature.ObjectState;

import java.util.List;

public class StatisticalThresholdAnomaly implements AnomalyClassifier {
    private final double MAX_SPEED_THRESHOLD = 150.0;
    private final double MIN_SPEED_THRESHOLD = 0.5;
    private final double MAX_ASPECT_RATIO_CHANGE = 0.4;

    @Override
    public boolean detectAnomaly(List<ObjectState> history) {
        if (history.size() < 5) return false;

        ObjectState current = history.getLast();
        ObjectState previous = history.get(history.size() - 2);

        if (current.speed > MAX_SPEED_THRESHOLD || (current.speed < MIN_SPEED_THRESHOLD && current.speed > 0)) {
            current.anomalyDescription = String.format("SPEED: %.1f", current.speed);
            return true;
        }

        double ratioChange = Math.abs(current.aspectRatio - previous.aspectRatio);
        if (ratioChange > MAX_ASPECT_RATIO_CHANGE) {
            current.anomalyDescription = String.format("FORM: %.1f", ratioChange);
            return true;
        }

        double angleDiff = Math.abs(current.angleDirection - previous.angleDirection);
        if (angleDiff > Math.PI / 2) {
            current.anomalyDescription = String.format("TRAJECTORY: %.1f", angleDiff);
            return true;
        }

        return false;
    }

    @Override
    public String getMethodName() {
        return "Statistical Thresholding";
    }
}