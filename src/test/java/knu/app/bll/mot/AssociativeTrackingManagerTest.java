//package knu.app.bll.mot;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.lang.reflect.Field;
//import java.util.concurrent.atomic.AtomicInteger;
//import knu.app.bll.algorithms.associative.AssociationAlgorithm;
//import knu.app.bll.buffers.BufferableList;
//import knu.app.bll.confirmation.MaxMissedDeletingAlgorithm;
//import knu.app.bll.confirmation.NOutOfMConfirmation;
//import knu.app.bll.processors.tracker.single.ObjectTracker;
//import knu.app.bll.utils.registry.ObjectTrackerFactory;
//import knu.app.bll.utils.processors.DetectionResult;
//import knu.app.bll.utils.processors.TrackedObject;
//import org.bytedeco.opencv.opencv_core.Mat;
//import org.bytedeco.opencv.opencv_core.Point2f;
//import org.bytedeco.opencv.opencv_core.Rect;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.MockedStatic;
//import org.mockito.Mockito;
//
//
//import static org.mockito.Mockito.*;
//
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//
//class AssociativeTrackingManagerTest {
//
//  private AssociativeTrackingManager trackingManager;
//  private AssociationAlgorithm mockAssoc;
//  private Mat dummyMat;
//  private DetectionResult dummyDetResult;
//  private Rect sampleDet;
//
//  @BeforeEach
//  void setUp() {
//    // Create mock objects
//    mockAssoc = mock(AssociationAlgorithm.class);
//    trackingManager = new AssociativeTrackingManager(mockAssoc);
//
//    // Initialize dummy objects
//    dummyMat = mock(Mat.class);
//    sampleDet = new Rect(0, 0, 10, 10);
//
//    // Create mock detection result
//    dummyDetResult = mock(DetectionResult.class);
//    when(dummyDetResult.getRects()).thenReturn(Collections.singletonList(sampleDet));
//    when(dummyDetResult.getScores()).thenReturn(Collections.singletonList(1.0));
//  }
//
//  //3
//  @Test
//  void update_WithOcclusion_ShouldIncreaseMissedAndDeleteIfMaxMissed() {
//    try (MockedStatic<ObjectTrackerFactory> factory = Mockito.mockStatic(ObjectTrackerFactory.class)) {
//      // Mock tracker factory
//      ObjectTracker mockTracker = mock(ObjectTracker.class);
//      ObjectTrackerFactory factoryInstance = mock(ObjectTrackerFactory.class);
//      when(factoryInstance.create(anyString())).thenReturn(mockTracker);
//      factory.when(ObjectTrackerFactory::getInstance).thenReturn(factoryInstance);
//
//      // Set tracker type
//      trackingManager.setObjectTracker("dummyTracker");
//
//      Mat mat = new Mat();
//      Rect det1 = new Rect(0, 0, 10, 10);
//      List<Rect> detRects = Collections.singletonList(det1);
//      List<Double> scores = Collections.singletonList(0.9);
//      DetectionResult detResult = new DetectionResult(detRects, scores);
//
//      // First frame: create track
//      when(mockAssoc.associate(anyList(), eq(detResult))).thenReturn(Collections.emptyMap());
//      trackingManager.update(mat, detResult);
//
//      // Mock association: object "disappeared"
//      when(mockAssoc.associate(anyList(), any(DetectionResult.class))).thenReturn(Collections.emptyMap());
//
//      // Simulate frames without detection to increase missed count
//      for (int i = 0; i < trackingManager.getDeletingConfirmationAlgorithm().getMaxMissed(); i++) {
//        trackingManager.update(mat, new DetectionResult(Collections.emptyList(), Collections.emptyList()));
//      }
//
//      // Verify track is deleted
//      assertEquals(0, trackingManager.getBufferSize(), "Track should be deleted after exceeding MaxMissed threshold");
//      verify(mockTracker).close();
//    }
//  }
//
//  //4
//  @Test
//  void resetAndSetBufferCapacity_ShouldClearAndReconfigureBuffer() {
//    try (MockedStatic<ObjectTrackerFactory> factory = Mockito.mockStatic(ObjectTrackerFactory.class)) {
//      // Mock tracker factory
//      ObjectTracker mockTracker = mock(ObjectTracker.class);
//      ObjectTrackerFactory factoryInstance = mock(ObjectTrackerFactory.class);
//      when(factoryInstance.create(anyString())).thenReturn(mockTracker);
//      factory.when(ObjectTrackerFactory::getInstance).thenReturn(factoryInstance);
//
//      // Set tracker type
//      trackingManager.setObjectTracker("dummyTracker");
//
//      Mat mat = new Mat();
//      Rect det = new Rect(0, 0, 10, 10);
//      List<Rect> detRects = Collections.singletonList(det);
//      List<Double> scores = Collections.singletonList(0.9);
//      DetectionResult detResult = new DetectionResult(detRects, scores);
//
//      // Create track
//      when(mockAssoc.associate(anyList(), eq(detResult))).thenReturn(Collections.emptyMap());
//      trackingManager.update(mat, detResult);
//
//      assertTrue(trackingManager.getBufferSize() > 0);
//
//      // Test buffer capacity reconfiguration
//      trackingManager.setBuffCapacity(50);
//      assertEquals(50, trackingManager.getBufferCapacity(), "Buffer capacity should be updated");
//
//      assertTrue(trackingManager.getBufferSize() > 0);
//    }
//  }
//
//  //5
//  @Test
//  void creation_ShouldSpawnTentativeTracks_and_callTrackerInit() {
//    try (MockedStatic<ObjectTrackerFactory> factory = Mockito.mockStatic(ObjectTrackerFactory.class)) {
//      // Mock tracker factory
//      ObjectTracker mockTracker = mock(ObjectTracker.class);
//      ObjectTrackerFactory factoryInstance = mock(ObjectTrackerFactory.class);
//      when(factoryInstance.create(anyString())).thenReturn(mockTracker);
//      factory.when(ObjectTrackerFactory::getInstance).thenReturn(factoryInstance);
//
//      // Set tracker type
//      trackingManager.setObjectTracker("dummyTracker");
//
//      Mat mat = new Mat();
//      Rect r1 = new Rect(0, 0, 10, 10);
//      Rect r2 = new Rect(50, 50, 10, 10);
//      List<Rect> rects = List.of(r1, r2);
//      List<Double> scores = List.of(0.9, 0.8);
//      DetectionResult detResult = new DetectionResult(rects, scores);
//
//      // Mock association to return empty map (all detections are new)
//      when(mockAssoc.associate(anyList(), eq(detResult))).thenReturn(Collections.emptyMap());
//
//      List<TrackedObject> confirmed = trackingManager.update(mat, detResult);
//
//      assertTrue(confirmed.isEmpty(), "No confirmed tracks should exist on first frame");
//      assertEquals(2, trackingManager.getBufferSize(), "Two tentative tracks should be created");
//
//      // Verify tracker initialization was called twice
//      verify(factoryInstance, times(2)).create(anyString());
//      verify(mockTracker, times(2)).init(any(Mat.class), anyList());
//    }
//  }
//
//  //8
//  @Test
//  void confirmedTrack_IsDeleted_AfterMaxMissedUpdates() {
//    try (MockedStatic<ObjectTrackerFactory> factory = Mockito.mockStatic(ObjectTrackerFactory.class)) {
//      // Mock tracker factory
//      ObjectTracker mockTracker = mock(ObjectTracker.class);
//      ObjectTrackerFactory factoryInstance = mock(ObjectTrackerFactory.class);
//      when(factoryInstance.create(anyString())).thenReturn(mockTracker);
//      factory.when(ObjectTrackerFactory::getInstance).thenReturn(factoryInstance);
//
//      // Set tracker type
//      trackingManager.setObjectTracker("dummyTracker");
//
//      Mat mat = new Mat(480, 640, 0); // CV_8UC3 = 0
//      Rect rect = new Rect(0, 0, 10, 10);
//      DetectionResult detResult = new DetectionResult(List.of(rect), List.of(0.95));
//
//      // Create confirmed track
//      when(mockAssoc.associate(anyList(), eq(detResult))).thenReturn(Collections.emptyMap());
//      trackingManager.update(mat, detResult);
//
//      // Mock association to match track with detection
//      when(mockAssoc.associate(anyList(), eq(detResult))).thenAnswer(invocation -> {
//        List<TrackedObject> tracks = invocation.getArgument(0);
//        return Map.of(tracks.get(0), rect);
//      });
//
//      // Process empty frames to exceed missed threshold
//      DetectionResult emptyDet = new DetectionResult(Collections.emptyList(), Collections.emptyList());
//      when(mockAssoc.associate(anyList(), eq(emptyDet))).thenReturn(Collections.emptyMap());
//
//      int maxMissed = trackingManager.getDeletingConfirmationAlgorithm().getMaxMissed();
//      for (int i = 0; i < maxMissed; i++) {
//        trackingManager.update(mat, emptyDet);
//      }
//
//      assertEquals(0, trackingManager.getBufferSize(), "Track should be deleted after max missed frames");
//      verify(mockTracker, times(1)).close();
//    }
//  }
//
//  //7
//  @Test
//  void shouldCreateNewTracksForUnmatchedDetections() {
//    AssociationAlgorithm algorithm = mock(AssociationAlgorithm.class);
//    when(algorithm.associate(anyList(), any())).thenReturn(Collections.emptyMap());
//
//    ObjectTracker tracker = mock(ObjectTracker.class);
//
//    // Mock static factory method
//    try (MockedStatic<ObjectTrackerFactory> factory = Mockito.mockStatic(ObjectTrackerFactory.class)) {
//      ObjectTrackerFactory factoryInstance = mock(ObjectTrackerFactory.class);
//      when(factoryInstance.create(anyString())).thenReturn(tracker);
//      factory.when(ObjectTrackerFactory::getInstance).thenReturn(factoryInstance);
//
//      AssociativeTrackingManager manager = new AssociativeTrackingManager(algorithm);
//      manager.setObjectTracker("CSRT"); // Set tracker key
//
//      Mat frame = new Mat();
//      DetectionResult detections = new DetectionResult(
//          List.of(new Rect(0, 0, 10, 10)),
//          List.of(0.9)
//      );
//
//      manager.update(frame, detections);
//
//      assertEquals(1, manager.getBufferSize(), "New track should be created for unmatched detection");
//    }
//  }
//
//  //6
//  @Test
//  void shouldConfirmTentativeTrackAndAssignIdWhenAssociated() throws Exception {
//    AssociationAlgorithm mockAssoc = mock(AssociationAlgorithm.class);
//    AssociativeTrackingManager manager = new AssociativeTrackingManager(mockAssoc);
//
//    // Replace confirmation algorithm with mock
//    NOutOfMConfirmation fakeConf = mock(NOutOfMConfirmation.class);
//    when(fakeConf.isConfirmed(any())).thenReturn(true);
//
//    Field confField = AssociativeTrackingManager.class.getDeclaredField("confirmationAlgorithm");
//    confField.setAccessible(true);
//    confField.set(manager, fakeConf);
//
//    // Create mock track
//    TrackedObject tentativeTrack = mock(TrackedObject.class);
//    when(tentativeTrack.isTentative()).thenReturn(true);
//    when(tentativeTrack.isConfirmed()).thenReturn(false);
//    when(tentativeTrack.getRect()).thenReturn(new Rect(0, 0, 10, 10));
//
//    ObjectTracker tracker = mock(ObjectTracker.class);
//    when(tentativeTrack.getTracker()).thenReturn(tracker);
//    when(tracker.update(any(), any())).thenReturn(List.of(new Point2f(5, 5)));
//
//    // Add track to buffer via reflection
//    Field bufferField = AssociativeTrackingManager.class.getDeclaredField("buffer");
//    bufferField.setAccessible(true);
//    BufferableList<TrackedObject> buffer = (BufferableList<TrackedObject>) bufferField.get(manager);
//    buffer.put(tentativeTrack);
//
//    // Configure association
//    Rect sampleDet = new Rect(0, 0, 10, 10);
//    Map<TrackedObject, Rect> assocMap = Collections.singletonMap(tentativeTrack, sampleDet);
//    when(mockAssoc.associate(anyList(), any())).thenReturn(assocMap);
//
//    // Execute update
//    Mat dummyMat = new Mat();
//    DetectionResult dummyDetResult = new DetectionResult(List.of(sampleDet), List.of(0.9));
//    manager.update(dummyMat, dummyDetResult);
//
//    // Verify track is confirmed and assigned ID
//    verify(tentativeTrack).setState(TrackedObject.TrackState.Confirmed);
//    verify(tentativeTrack).setId(anyInt());
//  }
//
//  //10
//  @Test
//  void shouldSpawnNewTentativeTracksForUnmatchedDetections() throws Exception {
//    AssociationAlgorithm mockAssoc = mock(AssociationAlgorithm.class);
//    when(mockAssoc.associate(anyList(), any())).thenReturn(Collections.emptyMap());
//
//    AssociativeTrackingManager manager = new AssociativeTrackingManager(mockAssoc);
//    manager.setObjectTracker("CSRT");
//
//    ObjectTracker mockTracker = mock(ObjectTracker.class);
//
//    // Mock static factory method
//    try (MockedStatic<ObjectTrackerFactory> factory = Mockito.mockStatic(ObjectTrackerFactory.class)) {
//      ObjectTrackerFactory factoryInstance = mock(ObjectTrackerFactory.class);
//      when(factoryInstance.create(anyString())).thenReturn(mockTracker);
//      factory.when(ObjectTrackerFactory::getInstance).thenReturn(factoryInstance);
//
//      Mat dummyMat = new Mat();
//      DetectionResult dummyDetResult = new DetectionResult(
//          List.of(new Rect(0, 0, 10, 10)),
//          List.of(0.9)
//      );
//
//      // Execute update
//      manager.update(dummyMat, dummyDetResult);
//
//      // Check buffer via reflection
//      Field bufferField = AssociativeTrackingManager.class.getDeclaredField("buffer");
//      bufferField.setAccessible(true);
//      BufferableList<TrackedObject> buffer = (BufferableList<TrackedObject>) bufferField.get(manager);
//
//      assertEquals(1, buffer.get().size(), "New tentative track should be created");
//      TrackedObject newTrack = buffer.get().get(0);
//      assertEquals(TrackedObject.TrackState.Tentative, newTrack.getState(), "New track should be tentative");
//
//      // Verify tracker was initialized
//      verify(mockTracker).init(any(), anyList());
//    }
//  }
//
//  //11
//  @Test
//  void shouldIncreaseMissedAndDeleteAfterThreshold() throws Exception {
//    AssociationAlgorithm mockAssoc = mock(AssociationAlgorithm.class);
//    when(mockAssoc.associate(anyList(), any())).thenReturn(Collections.emptyMap());
//
//    AssociativeTrackingManager manager = new AssociativeTrackingManager(mockAssoc);
//
//    // Replace deletion algorithm with mock
//    MaxMissedDeletingAlgorithm fakeDel = mock(MaxMissedDeletingAlgorithm.class);
//    when(fakeDel.isConfirmed(any())).thenReturn(true); // Always confirm deletion
//
//    Field delField = AssociativeTrackingManager.class.getDeclaredField("deletingConfirmationAlgorithm");
//    delField.setAccessible(true);
//    delField.set(manager, fakeDel);
//
//    // Create mock confirmed track
//    TrackedObject confirmedTrack = mock(TrackedObject.class);
//    when(confirmedTrack.isTentative()).thenReturn(false);
//    when(confirmedTrack.isConfirmed()).thenReturn(true);
//    when(confirmedTrack.isDeleted()).thenReturn(false);
//    when(confirmedTrack.getRect()).thenReturn(new Rect(0, 0, 10, 10));
//
//    ObjectTracker mockTracker = mock(ObjectTracker.class);
//    when(confirmedTrack.getTracker()).thenReturn(mockTracker);
//    when(mockTracker.update(any(), isNull())).thenReturn(Collections.emptyList());
//
//    // Add track to buffer
//    Field bufferField = AssociativeTrackingManager.class.getDeclaredField("buffer");
//    bufferField.setAccessible(true);
//    BufferableList<TrackedObject> buffer = (BufferableList<TrackedObject>) bufferField.get(manager);
//    buffer.put(confirmedTrack);
//
//    // Execute update (track should be marked as deleted)
//    Mat dummyMat = new Mat();
//    // We cannot set internal variables on a mock, so we simulate "deleted" by returning true
//    // Then we verify that the setState(Deleted) method is called as expected
//    when(confirmedTrack.isDeleted()).thenReturn(true);
//    manager.update(dummyMat, new DetectionResult(Collections.emptyList(), Collections.emptyList()));
//
//    // Verify track was marked as deleted and tracker was closed
//    verify(confirmedTrack).setState(TrackedObject.TrackState.Deleted);
//    verify(mockTracker).close();
//
//    // Verify buffer is empty
//    assertEquals(0, buffer.get().size(), "Buffer should be empty after deletion");
//  }
//
//  @Test
//  void resetShouldClearBufferAndResetIds() throws Exception {
//    AssociationAlgorithm mockAssoc = mock(AssociationAlgorithm.class);
//    when(mockAssoc.associate(anyList(), any())).thenReturn(Collections.emptyMap());
//
//    AssociativeTrackingManager manager = new AssociativeTrackingManager(mockAssoc);
//    manager.setObjectTracker("CSRT");
//
//    ObjectTracker mockTracker = mock(ObjectTracker.class);
//
//    // Mock static factory method
//    try (MockedStatic<ObjectTrackerFactory> factory = Mockito.mockStatic(ObjectTrackerFactory.class)) {
//      ObjectTrackerFactory factoryInstance = mock(ObjectTrackerFactory.class);
//      when(factoryInstance.create(anyString())).thenReturn(mockTracker);
//      factory.when(ObjectTrackerFactory::getInstance).thenReturn(factoryInstance);
//
//      // Create several tracks
//      Mat dummyMat = new Mat();
//      DetectionResult dummyDetResult = new DetectionResult(
//          List.of(new Rect(0, 0, 10, 10)),
//          List.of(0.9)
//      );
//
//      manager.update(dummyMat, dummyDetResult);
//      manager.update(dummyMat, dummyDetResult);
//      manager.update(dummyMat, dummyDetResult);
//
//      // Verify buffer is not empty
//      Field bufferField = AssociativeTrackingManager.class.getDeclaredField("buffer");
//      bufferField.setAccessible(true);
//      BufferableList<TrackedObject> buffer = (BufferableList<TrackedObject>) bufferField.get(manager);
//      assertTrue(buffer.get().size() > 0, "Buffer should not be empty before reset");
//
//      // Execute reset
//      manager.reset();
//
//      // Verify buffer is empty
//      assertEquals(0, buffer.get().size(), "Buffer should be empty after reset");
//
//      // Verify nextId is reset
//      Field nextIdField = AssociativeTrackingManager.class.getDeclaredField("nextId");
//      nextIdField.setAccessible(true);
//      AtomicInteger nextId = (AtomicInteger) nextIdField.get(manager);
//      assertEquals(0, nextId.get(), "Next ID should be reset to 0");
//    }
//  }
//}