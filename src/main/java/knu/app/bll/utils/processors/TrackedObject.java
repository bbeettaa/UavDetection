package knu.app.bll.utils.processors;

import knu.app.bll.processors.tracker.ObjectTracker;
import org.bytedeco.opencv.opencv_core.Rect;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TrackedObject   {
    private  int id;
    private Rect rect;
    private float score;
    private int hits;
    private int missed;
    private TrackState state;
    private final ObjectTracker tracker;
    private final List<Boolean> hitHistory;

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
}
