package knu.app.bll.confirmation;

import knu.app.bll.utils.processors.TrackedObject;

public class MaxMissedDeletingAlgorithm implements ConfirmationAlgorithm {
    private  int maxMissed;

    public MaxMissedDeletingAlgorithm(int maxMissed) {
        this.maxMissed = maxMissed;
    }

    @Override
    public boolean isConfirmed(TrackedObject candidate) {
        return candidate.getMissed() >= maxMissed;
    }

    public int getMaxMissed() {
        return maxMissed;
    }

    public void setMaxMissed(int maxMissed) {
        this.maxMissed = maxMissed;
    }
}
