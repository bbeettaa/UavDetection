package knu.app.bll.algorithms.feature.anomaly;

import knu.app.bll.algorithms.feature.ObjectState;

import java.util.List;

public class DirectionCurvatureAnomaly implements AnomalyClassifier {

    // (≈ 60 градусов)
    private static final double DIRECTION_CHANGE_THRESHOLD = Math.PI / 3;
    private static final double MIN_SPEED_FOR_ANALYSIS = 3.0;

    @Override
    public boolean detectAnomaly(List<ObjectState> history) {
        if (history.size() < 3) return false;

        ObjectState current = history.getLast();
        ObjectState previous = history.get(history.size() - 2);

        if (current.speed < MIN_SPEED_FOR_ANALYSIS || previous.speed < MIN_SPEED_FOR_ANALYSIS) {
            return false;
        }

        double angleDiff = Math.abs(current.angleDirection - previous.angleDirection);

        if (angleDiff > Math.PI) {
            angleDiff = 2 * Math.PI - angleDiff;
        }

        if (angleDiff > DIRECTION_CHANGE_THRESHOLD) {
            current.anomalyDescription = String.format("CURVATURE: %.1f deg", Math.toDegrees(angleDiff));
            return true;
        }

        return false;
    }

    @Override
    public String getMethodName() {
        return "Direction Curvature";
    }
}