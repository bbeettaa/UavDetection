package knu.app.bll.utils.processors;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.bytedeco.opencv.opencv_core.Rect;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class DetectionResultDiffblueTest {

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DetectionResult#DetectionResult()}
   *   <li>{@link DetectionResult#getRects()}
   *   <li>{@link DetectionResult#getScores()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
      "void DetectionResult.<init>()",
      "void DetectionResult.<init>(List, List)",
      "List DetectionResult.getRects()",
      "List DetectionResult.getScores()"
  })
  void testGettersAndSetters() {
    // Arrange and Act
    DetectionResult actualDetectionResult = new DetectionResult();
    List<Rect> actualRects = actualDetectionResult.getRects();
    List<Double> actualScores = actualDetectionResult.getScores();

    // Assert
    assertTrue(actualRects.isEmpty());
    assertTrue(actualScores.isEmpty());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return Rects is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DetectionResult#DetectionResult(List, List)}
   *   <li>{@link DetectionResult#getRects()}
   *   <li>{@link DetectionResult#getScores()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters; when ArrayList(); then return Rects is ArrayList()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
      "void DetectionResult.<init>()",
      "void DetectionResult.<init>(List, List)",
      "List DetectionResult.getRects()",
      "List DetectionResult.getScores()"
  })
  void testGettersAndSetters_whenArrayList_thenReturnRectsIsArrayList() {
    // Arrange
    ArrayList<Rect> rects = new ArrayList<>();
    ArrayList<Double> scores = new ArrayList<>();

    // Act
    DetectionResult actualDetectionResult = new DetectionResult(rects, scores);
    List<Rect> actualRects = actualDetectionResult.getRects();
    List<Double> actualScores = actualDetectionResult.getScores();

    // Assert
    assertTrue(actualRects.isEmpty());
    assertTrue(actualScores.isEmpty());
    assertSame(rects, actualRects);
    assertSame(scores, actualScores);
  }
}
