package knu.app.bll.algorithms.feature.anomaly;

import knu.app.bll.algorithms.feature.ObjectState;

import java.util.List;

public interface AnomalyClassifier {
    boolean detectAnomaly(List<ObjectState> history);
    String getMethodName();
}