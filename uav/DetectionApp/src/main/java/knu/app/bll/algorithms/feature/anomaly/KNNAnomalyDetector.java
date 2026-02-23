package knu.app.bll.algorithms.feature.anomaly;

import knu.app.bll.algorithms.feature.ObjectState;
import java.util.List;
import java.util.PriorityQueue;

public class KNNAnomalyDetector implements AnomalyClassifier {

    private final int K = 5;
    private final double DISTANCE_THRESHOLD = 50.0;

    @Override
    public boolean detectAnomaly(List<ObjectState> history) {
        if (history.size() < 10) return false;

        ObjectState current = history.getLast();
        double[] target = {current.speed, current.aspectRatio * 10, current.brightnessMean / 5};

        PriorityQueue<Double> nearestDistances = new PriorityQueue<>(Double::compare);

        for (int i = 0; i < history.size() - 5; i++) {
            ObjectState neighbor = history.get(i);
            double[] neighborFeat = {neighbor.speed, neighbor.aspectRatio * 10, neighbor.brightnessMean / 5};

            double dist = euclideanDistance(target, neighborFeat);
            nearestDistances.add(dist);
        }

        double sumKDist = 0;
        for (int i = 0; i < K && !nearestDistances.isEmpty(); i++) {
            sumKDist += nearestDistances.poll();
        }
        double avgKDist = sumKDist / K;

        if (avgKDist > DISTANCE_THRESHOLD) {
            current.anomalyDescription = String.format("KNN: Dist %.1f", avgKDist);
            return true;
        }

        return false;
    }

    private double euclideanDistance(double[] a, double[] b) {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += Math.pow(a[i] - b[i], 2);
        }
        return Math.sqrt(sum);
    }

    @Override
    public String getMethodName() {
        return "k-Nearest Neighbors";
    }
}