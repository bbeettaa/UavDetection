package knu.app.bll.algorithms.feature.anomaly;

import knu.app.bll.algorithms.feature.ObjectState;

import java.util.List;

public class MovingAverageAnomaly implements AnomalyClassifier {

    // Окно анализа
    private static final int WINDOW_SIZE = 15;
    // Коэффициент чувствительности
    private static final double SENSITIVITY_FACTOR = 2.0;

    @Override
    public boolean detectAnomaly(List<ObjectState> history) {
        // Нужно накопить историю для вычисления среднего
        if (history.size() < WINDOW_SIZE) return false;

        ObjectState current = history.getLast();

        // Вычисляем среднюю скорость по окну
        double sumSpeed = 0;
        double sumAspect = 0;
        int count = 0;

        for (int i = history.size() - WINDOW_SIZE; i < history.size() - 1; i++) {
            sumSpeed += history.get(i).speed;
            sumAspect += history.get(i).aspectRatio;
            count++;
        }

        double avgSpeed = sumSpeed / count;
        double avgAspect = sumAspect / count;

        if (current.speed > avgSpeed * SENSITIVITY_FACTOR && current.speed > 5.0) {
            current.anomalyDescription = String.format("MA_SPEED: %.1f > AVG: %.1f", current.speed, avgSpeed);
            return true;
        }

        if (Math.abs(current.aspectRatio - avgAspect) > 0.3) {
            current.anomalyDescription = "MA_FORM_DRIFT";
            return true;
        }

        return false;
    }

    @Override
    public String getMethodName() {
        return "Moving Average";
    }
}