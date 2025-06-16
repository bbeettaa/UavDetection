package knu.app.bll.utils;

public class EvaluationMetrics {
    private double precision;
    private double recall;
    private int TP;
    private int FP;
    private int FN;

    public void update(int TP, int FP, int FN) {
        this.TP += TP;
        this.FP += FP;
        this.FN += FN;
    }

    public double getPrecision() {
        int denom = TP + FP;
        return denom == 0 ? 0.0 : (double) TP / denom;
    }

    public double getRecall() {
        int denom = TP + FN;
        return denom == 0 ? 0.0 : (double) TP / denom;
    }

    public int getTP() { return TP; }
    public int getFP() { return FP; }
    public int getFN() { return FN; }
}
