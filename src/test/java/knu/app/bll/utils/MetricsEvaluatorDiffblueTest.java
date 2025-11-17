package knu.app.bll.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class MetricsEvaluatorDiffblueTest {

  /**
   * Test {@link MetricsEvaluator#MetricsEvaluator(String, double)}.
   *
   * <p>Method under test: {@link MetricsEvaluator#MetricsEvaluator(String, double)}
   */
  @Test
  @DisplayName("Test new MetricsEvaluator(String, double)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void MetricsEvaluator.<init>(String, double)"})
  void testNewMetricsEvaluator() {
    // Arrange, Act and Assert
    EvaluationMetrics metrics = new MetricsEvaluator("Json Path", 10.0d).getMetrics();
    assertEquals(0, metrics.getFN());
    assertEquals(0, metrics.getFP());
    assertEquals(0, metrics.getTP());
    assertEquals(0.0d, metrics.getPrecision());
    assertEquals(0.0d, metrics.getRecall());
  }

  /**
   * Test {@link MetricsEvaluator#loadGroundTruth(String)}.
   *
   * <ul>
   *   <li>When {@code Json Path}.
   * </ul>
   *
   * <p>Method under test: {@link MetricsEvaluator#loadGroundTruth(String)}
   */
  @Test
  @DisplayName("Test loadGroundTruth(String); when 'Json Path'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void MetricsEvaluator.loadGroundTruth(String)"})
  void testLoadGroundTruth_whenJsonPath() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange and Act
    new MetricsEvaluator("Json Path", 10.0d).loadGroundTruth("Json Path");

    // Assert
  }

  /**
   * Test {@link MetricsEvaluator#loadGroundTruth(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link MetricsEvaluator#loadGroundTruth(String)}
   */
  @Test
  @DisplayName("Test loadGroundTruth(String); when 'null'")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void MetricsEvaluator.loadGroundTruth(String)"})
  void testLoadGroundTruth_whenNull() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: name can't be null
    //       at java.base/java.io.FilePermission.init(FilePermission.java:323)
    //       at java.base/java.io.FilePermission.<init>(FilePermission.java:490)
    //       at java.base/java.lang.SecurityManager.checkRead(SecurityManager.java:742)
    //       at java.base/java.io.FileInputStream.<init>(FileInputStream.java:141)
    //       at java.base/java.io.FileInputStream.<init>(FileInputStream.java:106)
    //       at java.base/java.io.FileReader.<init>(FileReader.java:60)
    //       at knu.app.bll.utils.MetricsEvaluator.loadGroundTruth(MetricsEvaluator.java:30)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange and Act
    new MetricsEvaluator("Json Path", 10.0d).loadGroundTruth(null);

    // Assert
  }

  /**
   * Test {@link MetricsEvaluator#evaluate(long, List)}.
   *
   * <p>Method under test: {@link MetricsEvaluator#evaluate(long, List)}
   */
  @Test
  @DisplayName("Test evaluate(long, List)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void MetricsEvaluator.evaluate(long, List)"})
  void testEvaluate() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    MetricsEvaluator metricsEvaluator = new MetricsEvaluator("Json Path", 10.0d);
    metricsEvaluator.loadGroundTruth("Json Path");

    // Act
    metricsEvaluator.evaluate(1L, new ArrayList<>());

    // Assert
  }

  /**
   * Test {@link MetricsEvaluator#reset()}.
   *
   * <p>Method under test: {@link MetricsEvaluator#reset()}
   */
  @Test
  @DisplayName("Test reset()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void MetricsEvaluator.reset()"})
  void testReset() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange and Act
    new MetricsEvaluator("Json Path", 10.0d).reset();

    // Assert
  }

  /**
   * Test {@link MetricsEvaluator#getMetrics()}.
   *
   * <p>Method under test: {@link MetricsEvaluator#getMetrics()}
   */
  @Test
  @DisplayName("Test getMetrics()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"EvaluationMetrics MetricsEvaluator.getMetrics()"})
  void testGetMetrics() {
    // Arrange and Act
    EvaluationMetrics actualMetrics = new MetricsEvaluator("Json Path", 10.0d).getMetrics();

    // Assert
    assertEquals(0, actualMetrics.getFN());
    assertEquals(0, actualMetrics.getFP());
    assertEquals(0, actualMetrics.getTP());
    assertEquals(0.0d, actualMetrics.getPrecision());
    assertEquals(0.0d, actualMetrics.getRecall());
  }
}
