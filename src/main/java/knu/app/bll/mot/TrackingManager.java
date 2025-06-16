package knu.app.bll.mot;

import knu.app.bll.ObjectTrackerFactory;
import knu.app.bll.buffers.BufferableList;
import knu.app.bll.buffers.FilterBufferableLinkedList;
import knu.app.bll.confirmation.MaxMissedDeletingAlgorithm;
import knu.app.bll.confirmation.NOutOfMConfirmation;
import knu.app.bll.processors.tracker.ObjectTracker;
import knu.app.bll.utils.Utils;
import knu.app.bll.utils.processors.DetectionResult;
import knu.app.bll.utils.processors.TrackedObject;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Rect;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class TrackingManager {
    private String trackerKey;
    private final AssociationAlgorithm associationAlgorithm;
    private final NOutOfMConfirmation confirmationAlgorithm;
    private final MaxMissedDeletingAlgorithm deletingConfirmationAlgorithm;
    private final AtomicInteger nextId = new AtomicInteger(0);
    private final BufferableList<TrackedObject> buffer;

    public TrackingManager(AssociationAlgorithm associationAlgorithm) {
        this.associationAlgorithm = associationAlgorithm;
        this.confirmationAlgorithm = new NOutOfMConfirmation(5, 8);
        this.deletingConfirmationAlgorithm = new MaxMissedDeletingAlgorithm(25);
        buffer = new FilterBufferableLinkedList<>(100, TrackedObject::isTentative);
    }

    public List<TrackedObject> update(Mat mat, DetectionResult detResult) {
        predictFuturePosition(mat);
        Map<TrackedObject, Rect> matches = associationAlgorithm.associate(buffer.get(), detResult);
        updateTracks(mat, detResult, matches);
        spawnNewTracks(mat, detResult, matches.values());
        removeStatusDeletedTracks();
        return getConfirmedTrackedObjects();
    }

    private void updateTracks(Mat mat, DetectionResult detResult, Map<TrackedObject, Rect> matches) {
        for (TrackedObject t : buffer.get()) {
            if (matches.containsKey(t)) {
                updateAssociativeTracks(mat, detResult, t, matches);
            } else {
                updateOcclusion(mat, t);
            }
        }
    }

    private void updateAssociativeTracks(Mat mat, DetectionResult detResult, TrackedObject t, Map<TrackedObject, Rect> matches) {
        Rect det = matches.get(t);

        int idx = detResult.getRects().indexOf(det);
        t.setScore(idx >= 0 ? detResult.getScores().get(idx).floatValue() : t.getScore());
        t.increaseHints();
        t.resetMissed();

        if (t.isTentative() && confirmationAlgorithm.isConfirmed(t)) {
            t.setState(TrackedObject.TrackState.Confirmed);
            t.setId(nextId.getAndIncrement());
        }
        if (t.isConfirmed()) {
            if (det.area() == 0) return;
            List<Point2f> upd = t.getTracker().update(mat, Utils.rectToPoints(det));
            if (!upd.isEmpty()) det = Utils.centerToRect(upd.getFirst(), det.width(), det.height());
            t.setRect(det);
        }
    }

    private void updateOcclusion(Mat mat, TrackedObject t) {
        if (t.isConfirmed()) {
            List<Point2f> upd = t.getTracker().update(mat, null);
            if (!upd.isEmpty()) {
                Rect det = Utils.centerToRect(upd.getFirst(), t.getRect().width(), t.getRect().height());
                if (det.area() > 0) t.setRect(det);
            }
        }

        t.increaseMissed();
        if (deletingConfirmationAlgorithm.isConfirmed(t)) {
            t.setState(TrackedObject.TrackState.Deleted);
        }
    }

    private void removeStatusDeletedTracks() {
        buffer.get().removeIf(t -> {
            if (t.isDeleted()) {
                t.getTracker().close();
                return true;
            }
            return false;
        });
    }

    private List<TrackedObject> getConfirmedTrackedObjects() {
        List<TrackedObject> res = new LinkedList<>();
        for (TrackedObject t : buffer.get()) {
            if (t.isConfirmed()) res.add(t);
        }
        return res;
    }

    private void predictFuturePosition(Mat mat) {
        for (TrackedObject t : buffer.get()) {
            if (t.isConfirmed()) {
                List<Point2f> predCenter = t.getTracker().update(mat, null);
                if (predCenter.isEmpty()) continue;
                Rect predRect = Utils.centerToRect(predCenter.getFirst(), t.getRect().width(), t.getRect().height());
                t.setRect(predRect);
            }
        }
    }

    private void spawnNewTracks(Mat mat, DetectionResult detResult, Collection<Rect> used) {
        List<Rect> dets = detResult.getRects();
        List<Float> scores = detResult.getScores().stream().map(Double::floatValue).toList();

        for (int i = 0; i < dets.size(); i++) {
            Rect det = dets.get(i);
            if (!used.contains(det)) {
                ObjectTracker tracker = ObjectTrackerFactory.getInstance().create(trackerKey);
                TrackedObject newTrack = new TrackedObject(det, scores.get(i), tracker);
                tracker.init(mat, Utils.rectToPoints(det));
                newTrack.setState(TrackedObject.TrackState.Tentative);

                buffer.put(newTrack);
            }
        }
    }

    public void setObjectTracker(String trackerKey) {
        this.trackerKey = trackerKey;
    }

    public void reset() {
        this.nextId.set(0);
        this.buffer.clearAll();
    }


    public AssociationAlgorithm getAssociationAlgorithm() {
        return associationAlgorithm;
    }

    public NOutOfMConfirmation getConfirmationAlgorithm() {
        return confirmationAlgorithm;
    }

    public MaxMissedDeletingAlgorithm getDeletingConfirmationAlgorithm() {
        return deletingConfirmationAlgorithm;
    }

    public int getBufferCapacity() {
        return buffer.getCapacity();
    }

    public int getBufferSize() {
        return buffer.getSize();
    }

    public void setBuffCapacity(int c) {
        this.buffer.setNewCapacity(c);
    }


}


