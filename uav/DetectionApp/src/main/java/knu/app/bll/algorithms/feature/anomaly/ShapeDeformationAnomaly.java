package knu.app.bll.algorithms.feature.anomaly;

import knu.app.bll.algorithms.feature.ObjectState;

import java.util.List;

public class ShapeDeformationAnomaly implements AnomalyClassifier {

    private static final double AREA_CHANGE_THRESHOLD = 0.5;
    private static final double ASPECT_RATIO_THRESHOLD = 0.4;

    @Override
    public boolean detectAnomaly(List<ObjectState> history) {
        if (history.size() < 2) return false;

        ObjectState current = history.getLast();
        ObjectState previous = history.get(history.size() - 2);

        double areaDiff = Math.abs(current.area - previous.area) / previous.area;

        if (areaDiff > AREA_CHANGE_THRESHOLD) {
            current.anomalyDescription = String.format("SHAPE_AREA: %.1f%%", areaDiff * 100);
            return true;
        }

        double ratioDiff = Math.abs(current.aspectRatio - previous.aspectRatio);

        if (ratioDiff > ASPECT_RATIO_THRESHOLD) {
            current.anomalyDescription = "SHAPE_DEFORM";
            return true;
        }

        return false;
    }

    @Override
    public String getMethodName() {
        return "Shape Deformation";
    }
}