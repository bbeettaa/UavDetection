//package knu.app.bll.mot;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertSame;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import com.diffblue.cover.annotations.ManagedByDiffblue;
//import com.diffblue.cover.annotations.MethodsUnderTest;
//import java.util.List;
//import knu.app.bll.algorithms.associative.AssociationAlgorithm;
//import knu.app.bll.algorithms.associative.HungarianIoUAssociationJGraphT;
//import knu.app.bll.buffers.BufferableList;
//import knu.app.bll.buffers.FilterBufferableLinkedList;
//import knu.app.bll.confirmation.NOutOfMConfirmation;
//import knu.app.bll.utils.processors.DetectionResult;
//import knu.app.bll.utils.processors.TrackedObject;
//import org.bytedeco.opencv.opencv_core.Mat;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Tag;
//import org.junit.jupiter.api.Test;
//
//class AssociativeTrackingManagerDiffblueTest {
//
//  /**
//   * Test {@link AssociativeTrackingManager#AssociativeTrackingManager(AssociationAlgorithm)}.
//   *
//   * <p>Method under test: {@link
//   * AssociativeTrackingManager#AssociativeTrackingManager(AssociationAlgorithm)}
//   */
//  @Test
//  @DisplayName("Test new AssociativeTrackingManager(AssociationAlgorithm)")
//  @Tag("ContributionFromDiffblue")
//  @ManagedByDiffblue
//  @MethodsUnderTest({"void AssociativeTrackingManager.<init>(AssociationAlgorithm)"})
//  void testNewAssociativeTrackingManager() {
//    // Arrange
//    HungarianIoUAssociationJGraphT associationAlgorithm = new HungarianIoUAssociationJGraphT(10.0d);
//
//    // Act
//    AssociativeTrackingManager actualAssociativeTrackingManager =
//        new AssociativeTrackingManager(associationAlgorithm);
//
//    // Assert
//    AssociationAlgorithm associationAlgorithm2 =
//        actualAssociativeTrackingManager.getAssociationAlgorithm();
//    assertTrue(associationAlgorithm2 instanceof HungarianIoUAssociationJGraphT);
//    BufferableList<TrackedObject> buffer = actualAssociativeTrackingManager.getBuffer();
//    assertTrue(buffer instanceof FilterBufferableLinkedList);
//    assertEquals(0, buffer.getSize());
//    assertEquals(0, actualAssociativeTrackingManager.getBufferSize());
//    assertEquals(10.0d, associationAlgorithm2.getIouThreshold());
//    assertEquals(100, buffer.getCapacity());
//    assertEquals(100, actualAssociativeTrackingManager.getBufferCapacity());
//    assertEquals(
//        25, actualAssociativeTrackingManager.getDeletingConfirmationAlgorithm().getMaxMissed());
//    NOutOfMConfirmation confirmationAlgorithm =
//        actualAssociativeTrackingManager.getConfirmationAlgorithm();
//    assertEquals(5, confirmationAlgorithm.getN());
//    assertEquals(8, confirmationAlgorithm.getM());
//    assertFalse(buffer.isEmpty());
//    assertFalse(buffer.isFull());
//    assertTrue(buffer.get().isEmpty());
//    assertSame(associationAlgorithm, associationAlgorithm2);
//  }
//
//  /**
//   * Test {@link AssociativeTrackingManager#update(Mat, DetectionResult)}.
//   *
//   * <p>Method under test: {@link AssociativeTrackingManager#update(Mat, DetectionResult)}
//   */
//  @Test
//  @DisplayName("Test update(Mat, DetectionResult)")
//  @Disabled("TODO: Complete this test")
//  @Tag("ContributionFromDiffblue")
//  @ManagedByDiffblue
//  @MethodsUnderTest({"List AssociativeTrackingManager.update(Mat, DetectionResult)"})
//  void testUpdate() {
//    // TODO: Diffblue Cover was only able to create a partial test for this method:
//    //   Reason: JNI sandbox policy violation.
//    //   Diffblue Cover ran code in your project that tried to load a JNI library.
//    //   Library:
//    // /home/bedu/.javacpp/cache/javacpp-1.5.9-linux-x86_64.jar/org/bytedeco/javacpp/linux-x86_64/libjnijavacpp.so
//    //   The default sandboxing policy disallows this in order to prevent your code
//    //   from damaging your system environment. If you are sure that the library is
//    //   safe then use
//    //   Diffblue Cover Settings > Sandboxed Environment > Allowed JNI prefixes
//    //   to add a comma separated list of library prefixes to allow.
//    //   See https://diff.blue/R012 to resolve this issue.
//
//    // Arrange
//    // TODO: Populate arranged inputs
//    AssociativeTrackingManager associativeTrackingManager = null;
//    Mat mat = null;
//    DetectionResult detResult = null;
//
//    // Act
//    List<TrackedObject> actualUpdateResult = associativeTrackingManager.update(mat, detResult);
//
//    // Assert
//    // TODO: Add assertions on result
//  }
//
//  /**
//   * Test {@link AssociativeTrackingManager#reset()}.
//   *
//   * <p>Method under test: {@link AssociativeTrackingManager#reset()}
//   */
//  @Test
//  @DisplayName("Test reset()")
//  @Tag("ContributionFromDiffblue")
//  @ManagedByDiffblue
//  @MethodsUnderTest({"void AssociativeTrackingManager.reset()"})
//  void testReset() {
//    // TODO: Diffblue Cover was only able to create a partial test for this method:
//    //   Diffblue AI was unable to find a test
//
//    // Arrange and Act
//    new AssociativeTrackingManager(new HungarianIoUAssociationJGraphT(10.0d)).reset();
//
//    // Assert
//  }
//
//  /**
//   * Test getters and setters.
//   *
//   * <p>Methods under test:
//   *
//   * <ul>
//   *   <li>{@link AssociativeTrackingManager#setObjectTracker(String)}
//   *   <li>{@link AssociativeTrackingManager#getAssociationAlgorithm()}
//   *   <li>{@link AssociativeTrackingManager#getBuffer()}
//   *   <li>{@link AssociativeTrackingManager#getConfirmationAlgorithm()}
//   *   <li>{@link AssociativeTrackingManager#getDeletingConfirmationAlgorithm()}
//   * </ul>
//   */
//  @Test
//  @DisplayName("Test getters and setters")
//  @Tag("ContributionFromDiffblue")
//  @ManagedByDiffblue
//  @MethodsUnderTest({
//      "AssociationAlgorithm AssociativeTrackingManager.getAssociationAlgorithm()",
//      "BufferableList AssociativeTrackingManager.getBuffer()",
//      "NOutOfMConfirmation AssociativeTrackingManager.getConfirmationAlgorithm()",
//      "knu.app.bll.confirmation.MaxMissedDeletingAlgorithm AssociativeTrackingManager.getDeletingConfirmationAlgorithm()",
//      "void AssociativeTrackingManager.setObjectTracker(String)"
//  })
//  void testGettersAndSetters() {
//    // Arrange
//    HungarianIoUAssociationJGraphT associationAlgorithm = new HungarianIoUAssociationJGraphT(10.0d);
//    AssociativeTrackingManager associativeTrackingManager =
//        new AssociativeTrackingManager(associationAlgorithm);
//
//    // Act
//    associativeTrackingManager.setObjectTracker("Tracker Key");
//    AssociationAlgorithm actualAssociationAlgorithm =
//        associativeTrackingManager.getAssociationAlgorithm();
//    BufferableList<TrackedObject> actualBuffer = associativeTrackingManager.getBuffer();
//    NOutOfMConfirmation actualConfirmationAlgorithm =
//        associativeTrackingManager.getConfirmationAlgorithm();
//
//    // Assert
//    assertTrue(actualAssociationAlgorithm instanceof HungarianIoUAssociationJGraphT);
//    assertTrue(actualBuffer instanceof FilterBufferableLinkedList);
//    assertEquals(25, associativeTrackingManager.getDeletingConfirmationAlgorithm().getMaxMissed());
//    assertEquals(5, actualConfirmationAlgorithm.getN());
//    assertEquals(8, actualConfirmationAlgorithm.getM());
//    assertSame(associationAlgorithm, actualAssociationAlgorithm);
//  }
//
//  /**
//   * Test {@link AssociativeTrackingManager#getBufferCapacity()}.
//   *
//   * <p>Method under test: {@link AssociativeTrackingManager#getBufferCapacity()}
//   */
//  @Test
//  @DisplayName("Test getBufferCapacity()")
//  @Tag("ContributionFromDiffblue")
//  @ManagedByDiffblue
//  @MethodsUnderTest({"int AssociativeTrackingManager.getBufferCapacity()"})
//  void testGetBufferCapacity() {
//    // Arrange, Act and Assert
//    assertEquals(
//        100,
//        new AssociativeTrackingManager(new HungarianIoUAssociationJGraphT(10.0d))
//            .getBufferCapacity());
//  }
//
//  /**
//   * Test {@link AssociativeTrackingManager#getBufferSize()}.
//   *
//   * <p>Method under test: {@link AssociativeTrackingManager#getBufferSize()}
//   */
//  @Test
//  @DisplayName("Test getBufferSize()")
//  @Tag("ContributionFromDiffblue")
//  @ManagedByDiffblue
//  @MethodsUnderTest({"int AssociativeTrackingManager.getBufferSize()"})
//  void testGetBufferSize() {
//    // Arrange, Act and Assert
//    assertEquals(
//        0,
//        new AssociativeTrackingManager(new HungarianIoUAssociationJGraphT(10.0d)).getBufferSize());
//  }
//
//  /**
//   * Test {@link AssociativeTrackingManager#setBuffCapacity(int)}.
//   *
//   * <p>Method under test: {@link AssociativeTrackingManager#setBuffCapacity(int)}
//   */
//  @Test
//  @DisplayName("Test setBuffCapacity(int)")
//  @Tag("ContributionFromDiffblue")
//  @ManagedByDiffblue
//  @MethodsUnderTest({"void AssociativeTrackingManager.setBuffCapacity(int)"})
//  void testSetBuffCapacity() {
//    // Arrange
//    AssociativeTrackingManager associativeTrackingManager =
//        new AssociativeTrackingManager(new HungarianIoUAssociationJGraphT(10.0d));
//
//    // Act
//    associativeTrackingManager.setBuffCapacity(1);
//
//    // Assert
//    BufferableList<TrackedObject> buffer = associativeTrackingManager.getBuffer();
//    assertTrue(buffer instanceof FilterBufferableLinkedList);
//    assertEquals(1, buffer.getCapacity());
//    assertEquals(1, associativeTrackingManager.getBufferCapacity());
//  }
//}
