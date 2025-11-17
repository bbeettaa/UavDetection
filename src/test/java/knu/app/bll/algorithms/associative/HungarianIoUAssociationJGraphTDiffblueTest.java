package knu.app.bll.algorithms.associative;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import knu.app.bll.utils.processors.DetectionResult;
import knu.app.bll.utils.processors.TrackedObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class HungarianIoUAssociationJGraphTDiffblueTest {

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link HungarianIoUAssociationJGraphT#HungarianIoUAssociationJGraphT(double)}
   *   <li>{@link HungarianIoUAssociationJGraphT#getIouThreshold()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
      "void HungarianIoUAssociationJGraphT.<init>(double)",
      "double HungarianIoUAssociationJGraphT.getIouThreshold()",
      "void HungarianIoUAssociationJGraphT.setIouThreshold(double)"
  })
  void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new HungarianIoUAssociationJGraphT(10.0d).getIouThreshold());
  }

  /**
   * Test {@link HungarianIoUAssociationJGraphT#associate(List, DetectionResult)}.
   *
   * <ul>
   *   <li>When {@link DetectionResult#DetectionResult()}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link HungarianIoUAssociationJGraphT#associate(List, DetectionResult)}
   */
  @Test
  @DisplayName("Test associate(List, DetectionResult); when DetectionResult(); then return Empty")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
      "java.util.Map HungarianIoUAssociationJGraphT.associate(List, DetectionResult)"
  })
  void testAssociate_whenDetectionResult_thenReturnEmpty() {
    // Arrange
    HungarianIoUAssociationJGraphT hungarianIoUAssociationJGraphT =
        new HungarianIoUAssociationJGraphT(10.0d);
    ArrayList<TrackedObject> tracks = new ArrayList<>();

    // Act and Assert
    assertTrue(hungarianIoUAssociationJGraphT.associate(tracks, new DetectionResult()).isEmpty());
  }
}
