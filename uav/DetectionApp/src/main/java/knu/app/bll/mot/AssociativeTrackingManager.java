package knu.app.bll.mot;

import imgui.ImGui;
import imgui.flag.ImGuiTreeNodeFlags;
import imgui.type.ImBoolean;
import imgui.type.ImInt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import knu.app.bll.algorithms.associative.AssociationAlgorithm;
import knu.app.bll.buffers.BufferableList;
import knu.app.bll.buffers.FilterBufferableLinkedList;
import knu.app.bll.confirmation.MaxMissedDeletingAlgorithm;
import knu.app.bll.confirmation.NOutOfMConfirmation;
import knu.app.bll.processors.tracker.single.CSRTTracker;
import knu.app.bll.processors.tracker.single.KalmanObjectTracker;
import knu.app.bll.processors.tracker.single.MilTracker;
import knu.app.bll.processors.tracker.single.ObjectTracker;
import knu.app.bll.processors.tracker.single.OpticalFlowTracker;
import knu.app.bll.utils.LocalizationManager;
import knu.app.bll.utils.Utils;
import knu.app.bll.utils.processors.DetectionResult;
import knu.app.bll.utils.processors.TrackedObject;
import knu.app.bll.utils.registry.ObjectTrackerFactory;
import knu.app.ui.processings.trackers.CSRTTrackerUI;
import knu.app.ui.processings.trackers.KalmanTrackerUI;
import knu.app.ui.processings.trackers.LucasKanadeTrackerUI;
import knu.app.ui.processings.trackers.MILTrackerUI;
import knu.app.ui.processings.trackers.TrackerUI;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Rect;

public class AssociativeTrackingManager implements TrackingManager {

  final String name;
  private final AssociationAlgorithm associationAlgorithm;
  private final NOutOfMConfirmation confirmationAlgorithm;
  private final MaxMissedDeletingAlgorithm deletingConfirmationAlgorithm;
  private final AtomicInteger nextId = new AtomicInteger(0);
  private final BufferableList<TrackedObject> buffer;
  private final int[] bufferCapacity;
  private final float[] iouThreshold;
  private final int[] nOfMConfirmation;
  private final int[] maxMissedDeleting;
  private final ImBoolean useTrackers = new ImBoolean(false);
  private final List<TrackerUI> trackers = new ArrayList<>();
  private final ImInt selectedTracker = new ImInt(-1);
  private String trackerKey;

  public AssociativeTrackingManager(AssociationAlgorithm associationAlgorithm, String name) {
    this.associationAlgorithm = associationAlgorithm;
    this.confirmationAlgorithm = new NOutOfMConfirmation(5, 8);
    this.deletingConfirmationAlgorithm = new MaxMissedDeletingAlgorithm(25);
    buffer = new FilterBufferableLinkedList<>(100, TrackedObject::isTentative);

    this.bufferCapacity = new int[]{getBufferCapacity()};
    this.iouThreshold = new float[]{(float) getAssociationAlgorithm().getIouThreshold()};
    this.nOfMConfirmation = new int[]{getConfirmationAlgorithm().getN(),
        getConfirmationAlgorithm().getM()};
    this.maxMissedDeleting = new int[]{getDeletingConfirmationAlgorithm().getMaxMissed()};

    this.name = name;

    trackers.add(new KalmanTrackerUI(new KalmanObjectTracker()));
    trackers.add(new LucasKanadeTrackerUI(new OpticalFlowTracker()));
    trackers.add(new CSRTTrackerUI(new CSRTTracker()));
    trackers.add(new MILTrackerUI(new MilTracker()));
  }

  public List<TrackedObject> update(Mat mat, DetectionResult detResult) {
    if (selectedTracker.get() > -1) {
      predictFuturePosition(mat);
      Map<TrackedObject, Rect> matches = associationAlgorithm.associate(buffer.get(), detResult);
      updateTracks(mat, detResult, matches);
      spawnNewTracks(mat, detResult, matches.values());
      removeStatusDeletedTracks();
    }
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

  private void updateAssociativeTracks(Mat mat, DetectionResult detResult, TrackedObject t,
      Map<TrackedObject, Rect> matches) {
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
      if (det.area() == 0) {
        return;
      }
      List<Point2f> upd = t.getTracker().update(mat, Utils.rectToPoints(det));
      if (!upd.isEmpty()) {
        det = Utils.centerToRect(upd.getFirst(), det.width(), det.height());
      }
      t.setRect(det);
    }
  }

  private void updateOcclusion(Mat mat, TrackedObject t) {
    if (t.isConfirmed()) {
      List<Point2f> upd = t.getTracker().update(mat, null);
      if (!upd.isEmpty()) {
        Rect det = Utils.centerToRect(upd.getFirst(), t.getRect().width(), t.getRect().height());
        if (det.area() > 0) {
          t.setRect(det);
        }
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
      if (t.isConfirmed()) {
        res.add(t);
      }
    }
    return res;
  }

  private void predictFuturePosition(Mat mat) {
    for (TrackedObject t : buffer.get()) {
      if (t.isConfirmed()) {
        List<Point2f> predCenter = t.getTracker().update(mat, null);
        if (predCenter.isEmpty()) {
          continue;
        }
        Rect predRect = Utils.centerToRect(predCenter.getFirst(), t.getRect().width(),
            t.getRect().height());
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

  public BufferableList<TrackedObject> getBuffer() {
    return buffer;
  }

  @Override
  public void renderSettings() {
//    if (ImGui.collapsingHeader(LocalizationManager.tr("processor.tracker.manager.name1"),
//        ImGuiTreeNodeFlags.DefaultOpen)) {

      ImGui.bulletText(
          String.format("%s: %s", LocalizationManager.tr("processor.tracker.manager.buffer.size"),
              getBufferSize()));
      if (ImGui.sliderInt(LocalizationManager.tr("processor.tracker.manager.buffer.capacity"),
          bufferCapacity, 0, 250)) {
        setBuffCapacity(bufferCapacity[0]);
      }
      if (ImGui.button(LocalizationManager.tr("processor.tracker.manager.buffer.clear"))) {
        reset();
      }
      ImGui.newLine();
      if (ImGui.sliderFloat(LocalizationManager.tr("processor.tracker.manager.iouthreshold"),
          iouThreshold, 0, 1, "%.2f")) {
        getAssociationAlgorithm().setIouThreshold(iouThreshold[0]);
      }
      ImGui.newLine();
      if (ImGui.sliderInt2(LocalizationManager.tr("processor.tracker.manager.conformation.NofM"),
          nOfMConfirmation, 0, 32)) {
        getConfirmationAlgorithm().setN(nOfMConfirmation[0]);
        getConfirmationAlgorithm().setM(nOfMConfirmation[1]);
      }
      ImGui.newLine();
      if (ImGui.sliderInt(LocalizationManager.tr("processor.tracker.manager.maxmissed"),
          maxMissedDeleting, 0, 60)) {
        getDeletingConfirmationAlgorithm().setMaxMissed(maxMissedDeleting[0]);
      }
//    }
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void renderTrackers() {
//    if (ImGui.collapsingHeader(LocalizationManager.tr("processor.tracker.name"),
//        ImGuiTreeNodeFlags.DefaultOpen)) {
      ImGui.checkbox(LocalizationManager.tr("processor.tracker.enable"), useTrackers);
      ImGui.beginDisabled(!useTrackers.get());

      for (int i = 0; i < trackers.size(); i++) {
        if (ImGui.radioButton(trackers.get(i).getName(), selectedTracker, i)) {
          setObjectTracker(trackers.get(i).getKey());
        }
        if (i == selectedTracker.get()) {
          trackers.get(selectedTracker.get()).renderSettings();
        }
      }
      ImGui.endDisabled();
//    }
  }
}
