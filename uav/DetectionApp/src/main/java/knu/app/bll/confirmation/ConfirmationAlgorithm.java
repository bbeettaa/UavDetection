package knu.app.bll.confirmation;

import knu.app.bll.utils.processors.TrackedObject;

public interface ConfirmationAlgorithm {
    boolean isConfirmed(TrackedObject candidate);
}