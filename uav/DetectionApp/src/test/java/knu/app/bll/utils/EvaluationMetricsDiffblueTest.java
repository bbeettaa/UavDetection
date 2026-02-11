package knu.app.bll.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class EvaluationMetricsDiffblueTest {

  /**
   * Test {@link EvaluationMetrics#update(int, int, int)}.
   *
   * <p>Method under test: {@link EvaluationMetrics#update(int, int, int)}
   */
  @Test
  @DisplayName("Test update(int, int, int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void EvaluationMetrics.update(int, int, int)"})
  void testUpdate() {
    // Arrange
    EvaluationMetrics evaluationMetrics = new EvaluationMetrics();

    // Act
    evaluationMetrics.update(1, 1, 1);

    // Assert
    assertEquals(0.5d, evaluationMetrics.getPrecision());
    assertEquals(0.5d, evaluationMetrics.getRecall());
    assertEquals(1, evaluationMetrics.getFN());
    assertEquals(1, evaluationMetrics.getFP());
    assertEquals(1, evaluationMetrics.getTP());
  }

  /**
   * Test {@link EvaluationMetrics#getPrecision()}.
   *
   * <p>Method under test: {@link EvaluationMetrics#getPrecision()}
   */
  @Test
  @DisplayName("Test getPrecision()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"double EvaluationMetrics.getPrecision()"})
  void testGetPrecision() {
    // Arrange, Act and Assert
    assertEquals(0.0d, new EvaluationMetrics().getPrecision());
  }

  /**
   * Test {@link EvaluationMetrics#getRecall()}.
   *
   * <p>Method under test: {@link EvaluationMetrics#getRecall()}
   */
  @Test
  @DisplayName("Test getRecall()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"double EvaluationMetrics.getRecall()"})
  void testGetRecall() {
    // Arrange, Act and Assert
    assertEquals(0.0d, new EvaluationMetrics().getRecall());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link EvaluationMetrics#getFN()}
   *   <li>{@link EvaluationMetrics#getFP()}
   *   <li>{@link EvaluationMetrics#getTP()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
      "int EvaluationMetrics.getFN()",
      "int EvaluationMetrics.getFP()",
      "int EvaluationMetrics.getTP()"
  })
  void testGettersAndSetters() {
    // Arrange
    EvaluationMetrics evaluationMetrics = new EvaluationMetrics();

    // Act
    int actualFN = evaluationMetrics.getFN();
    int actualFP = evaluationMetrics.getFP();

    // Assert
    assertEquals(0, actualFN);
    assertEquals(0, actualFP);
    assertEquals(0, evaluationMetrics.getTP());
  }
}
