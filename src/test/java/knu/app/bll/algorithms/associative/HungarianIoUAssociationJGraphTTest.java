package knu.app.bll.algorithms.associative;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import knu.app.bll.utils.processors.DetectionResult;
import knu.app.bll.utils.processors.TrackedObject;
import org.bytedeco.opencv.opencv_core.Rect;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HungarianIoUAssociationJGraphTTest {

  @Test
  @DisplayName("associate returns empty when tracks list is empty")
  void associate_emptyTracks_returnsEmpty() {
    HungarianIoUAssociationJGraphT algo = new HungarianIoUAssociationJGraphT(0.5);
    DetectionResult detections = new DetectionResult(new ArrayList<>(), new ArrayList<>());

    Map<TrackedObject, Rect> result = algo.associate(Collections.emptyList(), detections);

    assertTrue(result.isEmpty());
  }

  @Test
  @DisplayName("associate returns empty when detections list is empty")
  void associate_emptyDetections_returnsEmpty() {
    HungarianIoUAssociationJGraphT algo = new HungarianIoUAssociationJGraphT(0.5);

    List<TrackedObject> tracks = new ArrayList<>();
    tracks.add(new TrackedObject(new Rect(0, 0, 10, 10)));

    DetectionResult detections = new DetectionResult(new ArrayList<>(), new ArrayList<>());

    Map<TrackedObject, Rect> result = algo.associate(tracks, detections);

    assertTrue(result.isEmpty());
  }

  @Test
  @DisplayName("associate returns single match when one pair exceeds threshold")
  void associate_singleMatchAboveThreshold() {
    HungarianIoUAssociationJGraphT algo = new HungarianIoUAssociationJGraphT(0.5);

    TrackedObject t1 = new TrackedObject(new Rect(0, 0, 20, 20));
    List<TrackedObject> tracks = new ArrayList<>();
    tracks.add(t1);

    List<Rect> detRects = new ArrayList<>();
    Rect d1 = new Rect(0, 0, 20, 20); // IoU 1.0 with t1
    detRects.add(d1);
    DetectionResult detections = new DetectionResult(detRects, new ArrayList<>());

    Map<TrackedObject, Rect> result = algo.associate(tracks, detections);

    assertEquals(1, result.size());
    assertTrue(result.containsKey(t1));
    assertSame(d1, result.get(t1));
  }

  @Test
  @DisplayName("associate returns empty when IoU below threshold")
  void associate_noMatchBelowThreshold() {
    HungarianIoUAssociationJGraphT algo = new HungarianIoUAssociationJGraphT(1.1); // impossible

    TrackedObject t1 = new TrackedObject(new Rect(0, 0, 10, 10));
    List<TrackedObject> tracks = new ArrayList<>();
    tracks.add(t1);

    List<Rect> detRects = new ArrayList<>();
    Rect d1 = new Rect(0, 0, 10, 10);
    detRects.add(d1);
    DetectionResult detections = new DetectionResult(detRects, new ArrayList<>());

    Map<TrackedObject, Rect> result = algo.associate(tracks, detections);

    assertTrue(result.isEmpty());
  }

  @Test
  @DisplayName("associate returns one-to-one mapping for two exact matches")
  void associate_twoIndependentMatches() {
    HungarianIoUAssociationJGraphT algo = new HungarianIoUAssociationJGraphT(0.5);

    TrackedObject t1 = new TrackedObject(new Rect(0, 0, 10, 10));
    TrackedObject t2 = new TrackedObject(new Rect(100, 100, 10, 10));
    List<TrackedObject> tracks = new ArrayList<>();
    tracks.add(t1);
    tracks.add(t2);

    List<Rect> detRects = new ArrayList<>();
    Rect d1 = new Rect(0, 0, 10, 10);
    Rect d2 = new Rect(100, 100, 10, 10);
    detRects.add(d1);
    detRects.add(d2);
    DetectionResult detections = new DetectionResult(detRects, new ArrayList<>());

    Map<TrackedObject, Rect> result = algo.associate(tracks, detections);

    assertEquals(2, result.size());
    assertSame(d1, result.get(t1));
    assertSame(d2, result.get(t2));
  }

  @Test
  @DisplayName("setIouThreshold affects association outcome")
  void setIouThreshold_affectsAssociation() {
    HungarianIoUAssociationJGraphT algo = new HungarianIoUAssociationJGraphT(1.0);

    TrackedObject t1 = new TrackedObject(new Rect(0, 0, 10, 10));
    Rect d1 = new Rect(0, 0, 10, 10);
    List<TrackedObject> tracks = new ArrayList<>();
    tracks.add(t1);
    DetectionResult detections = new DetectionResult(Collections.singletonList(d1), new ArrayList<>());

    Map<TrackedObject, Rect> result1 = algo.associate(tracks, detections);
    assertEquals(1, result1.size());

    algo.setIouThreshold(1.1);
    Map<TrackedObject, Rect> result2 = algo.associate(tracks, detections);
    assertTrue(result2.isEmpty());
  }
}
