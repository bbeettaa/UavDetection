package knu.app.bll.utils.processors;

import knu.app.bll.algorithms.feature.ObjectState;
import knu.app.bll.algorithms.kalman.AccelerationKalmanFilter;
import knu.app.bll.processors.tracker.single.ObjectTracker;
import org.bytedeco.opencv.opencv_core.Rect;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class TrackedObject   {
    private  int id;
    private Rect rect;
    private float score;
    private String className;
    private int hits;
    private int missed;
    private TrackState state;
    private final ObjectTracker tracker;
    private  AccelerationKalmanFilter predictor = new AccelerationKalmanFilter();
    private final List<Boolean> hitHistory;

    private final LinkedList<ObjectState> trajectory = new LinkedList<>();

    public enum TrackState {
        Tentative, Confirmed, Deleted
    }

    public TrackedObject(Rect rect, float score, ObjectTracker tracker) {
        this.id = -1;
        this.rect = rect;
        this.score = score;
        this.hits = 1;
        this.missed = 0;
        this.state = TrackState.Tentative;
        this.tracker = tracker;
        this.hitHistory = new ArrayList<>();
        this.className = "";
    }

    public TrackedObject(Rect rect) {
        this(rect, -1, null);
    }

    // getters & setters
    public int getId() {
        return id;
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getHits() {
        return hits;
    }

    public void increaseHints() {
        hitHistory.add(true);
        this.hits++;
        missed = 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMissed() {
        return missed;
    }

    public void increaseMissed() {
        hitHistory.add(false);
        this.missed++;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void addState(ObjectState state, int maxLen) {
        if (state == null) return;
        this.trajectory.add(state);

        while (this.trajectory.size() > maxLen && !this.trajectory.isEmpty()) {
            this.trajectory.remove(0);
        }
    }

    public LinkedList<ObjectState> getTrajectory() {
        return trajectory;
    }

    public ObjectState getLastState() {
        if (trajectory.isEmpty()) return null;
        return trajectory.getLast();
    }

    public void resetMissed() {
        this.missed = 0;
    }

    public TrackState getState() {
        return state;
    }

    public void setState(TrackState state) {
        this.state = state;
    }

    public boolean isDeleted() {
        return state == TrackState.Deleted;
    }

    public boolean isConfirmed() {
        return state == TrackState.Confirmed;
    }
 

    public boolean isTentative(){
        return state == TrackState.Tentative;
    }

    public ObjectTracker getTracker() {
        return tracker;
    }

    public List<Boolean> getHitHistory() {
        return hitHistory;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TrackedObject that)) return false;
        return Objects.equals(rect, that.rect);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(rect);
    }

    public AccelerationKalmanFilter getPredictor() {
        return predictor;
    }
}
