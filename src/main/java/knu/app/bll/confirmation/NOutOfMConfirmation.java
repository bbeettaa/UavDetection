package knu.app.bll.confirmation;

import knu.app.bll.utils.processors.TrackedObject;

import java.util.List;

public class NOutOfMConfirmation implements ConfirmationAlgorithm {
    private  int n;
    private  int m;

    public NOutOfMConfirmation(int n, int m) {
        this.n = n;
        this.m = m;
    }

    @Override
    public boolean isConfirmed(TrackedObject candidate) {
        List<Boolean> history = candidate.getHitHistory();
        int start = Math.max(0, history.size() - m);
        int hits = 0;
        for (int i = start; i < history.size(); i++) {
            if (history.get(i)) {
                hits++;
            }
        }
        return hits >= n;
    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setM(int m) {
        this.m = m;
    }
}