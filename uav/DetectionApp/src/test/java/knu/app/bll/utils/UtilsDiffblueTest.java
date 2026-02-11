package knu.app.bll.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.LinkedList;
import java.util.List;
import knu.app.bll.utils.Utils.RectWithScore;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Rect;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class UtilsDiffblueTest {

  /**
   * Test {@link Utils#formatTimestamp(long)}.
   *
   * <p>Method under test: {@link Utils#formatTimestamp(long)}
   */
  @Test
  @DisplayName("Test formatTimestamp(long)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String Utils.formatTimestamp(long)"})
  void testFormatTimestamp() {
    // Arrange, Act and Assert
    assertEquals("00:00:00", Utils.formatTimestamp(10L));
  }

  /**
   * Test {@link Utils#clusterPoints(LinkedList, double, int)}.
   *
   * <p>Method under test: {@link Utils#clusterPoints(LinkedList, double, int)}
   */
  @Test
  @DisplayName("Test clusterPoints(LinkedList, double, int)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"LinkedList Utils.clusterPoints(LinkedList, double, int)"})
  void testClusterPoints() {
    // Arrange
    LinkedList<Point2f> points = new LinkedList<>();

    // Act
    LinkedList<Point2f> actualClusterPointsResult = Utils.clusterPoints(points, 10.0d, 1);

    // Assert
    assertEquals(points, actualClusterPointsResult);
  }

  /**
   * Test {@link Utils#rectToPoints(Rect)}.
   *
   * <p>Method under test: {@link Utils#rectToPoints(Rect)}
   */
  @Test
  @DisplayName("Test rectToPoints(Rect)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List Utils.rectToPoints(Rect)"})
  void testRectToPoints() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: JNI sandbox policy violation.
    //   Diffblue Cover ran code in your project that tried to load a JNI library.
    //   Library:
    // /home/bedu/.javacpp/cache/javacpp-1.5.9-linux-x86_64.jar/org/bytedeco/javacpp/linux-x86_64/libjnijavacpp.so
    //   The default sandboxing policy disallows this in order to prevent your code
    //   from damaging your system environment. If you are sure that the library is
    //   safe then use
    //   Diffblue Cover Settings > Sandboxed Environment > Allowed JNI prefixes
    //   to add a comma separated list of library prefixes to allow.
    //   See https://diff.blue/R012 to resolve this issue.

    // Arrange
    // TODO: Populate arranged inputs
    Rect r = null;

    // Act
    List<Point2f> actualRectToPointsResult = Utils.rectToPoints(r);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link Utils#pointsToRect(List)}.
   *
   * <p>Method under test: {@link Utils#pointsToRect(List)}
   */
  @Test
  @DisplayName("Test pointsToRect(List)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Rect Utils.pointsToRect(List)"})
  void testPointsToRect() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: JNI sandbox policy violation.
    //   Diffblue Cover ran code in your project that tried to load a JNI library.
    //   Library:
    // /home/bedu/.javacpp/cache/javacpp-1.5.9-linux-x86_64.jar/org/bytedeco/javacpp/linux-x86_64/libjnijavacpp.so
    //   The default sandboxing policy disallows this in order to prevent your code
    //   from damaging your system environment. If you are sure that the library is
    //   safe then use
    //   Diffblue Cover Settings > Sandboxed Environment > Allowed JNI prefixes
    //   to add a comma separated list of library prefixes to allow.
    //   See https://diff.blue/R012 to resolve this issue.

    // Arrange
    // TODO: Populate arranged inputs
    List<Point2f> pts = null;

    // Act
    Rect actualPointsToRectResult = Utils.pointsToRect(pts);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link Utils#rectCenter(Rect)}.
   *
   * <p>Method under test: {@link Utils#rectCenter(Rect)}
   */
  @Test
  @DisplayName("Test rectCenter(Rect)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Point2f Utils.rectCenter(Rect)"})
  void testRectCenter() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: JNI sandbox policy violation.
    //   Diffblue Cover ran code in your project that tried to load a JNI library.
    //   Library:
    // /home/bedu/.javacpp/cache/javacpp-1.5.9-linux-x86_64.jar/org/bytedeco/javacpp/linux-x86_64/libjnijavacpp.so
    //   The default sandboxing policy disallows this in order to prevent your code
    //   from damaging your system environment. If you are sure that the library is
    //   safe then use
    //   Diffblue Cover Settings > Sandboxed Environment > Allowed JNI prefixes
    //   to add a comma separated list of library prefixes to allow.
    //   See https://diff.blue/R012 to resolve this issue.

    // Arrange
    // TODO: Populate arranged inputs
    Rect rect = null;

    // Act
    Point2f actualRectCenterResult = Utils.rectCenter(rect);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link Utils#centerToRect(Point2f, int, int)}.
   *
   * <p>Method under test: {@link Utils#centerToRect(Point2f, int, int)}
   */
  @Test
  @DisplayName("Test centerToRect(Point2f, int, int)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Rect Utils.centerToRect(Point2f, int, int)"})
  void testCenterToRect() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: JNI sandbox policy violation.
    //   Diffblue Cover ran code in your project that tried to load a JNI library.
    //   Library:
    // /home/bedu/.javacpp/cache/javacpp-1.5.9-linux-x86_64.jar/org/bytedeco/javacpp/linux-x86_64/libjnijavacpp.so
    //   The default sandboxing policy disallows this in order to prevent your code
    //   from damaging your system environment. If you are sure that the library is
    //   safe then use
    //   Diffblue Cover Settings > Sandboxed Environment > Allowed JNI prefixes
    //   to add a comma separated list of library prefixes to allow.
    //   See https://diff.blue/R012 to resolve this issue.

    // Arrange
    // TODO: Populate arranged inputs
    Point2f center = null;
    int width = 0;
    int height = 0;

    // Act
    Rect actualCenterToRectResult = Utils.centerToRect(center, width, height);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link Utils#clusterToRects(LinkedList, float)}.
   *
   * <p>Method under test: {@link Utils#clusterToRects(LinkedList, float)}
   */
  @Test
  @DisplayName("Test clusterToRects(LinkedList, float)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List Utils.clusterToRects(LinkedList, float)"})
  void testClusterToRects() {
    // Arrange and Act
    List<RectWithScore> actualClusterToRectsResult =
        Utils.clusterToRects(new LinkedList<>(), 10.0f);

    // Assert
    assertTrue(actualClusterToRectsResult.isEmpty());
  }

  /**
   * Test RectWithScore {@link RectWithScore#RectWithScore(Rect, double)}.
   *
   * <p>Method under test: {@link RectWithScore#RectWithScore(Rect, double)}
   */
  @Test
  @DisplayName("Test RectWithScore new RectWithScore(Rect, double)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void RectWithScore.<init>(Rect, double)"})
  void testRectWithScoreNewRectWithScore() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: Exception in arrange section.
    //   Diffblue Cover was unable to construct an instance of the class under test using
    //   knu.app.bll.utils.Utils$RectWithScore.<init>(Rect, double).
    //   The arrange section threw
    //   java.lang.IllegalStateException
    //       at java.base/java.lang.Runtime.load0(Runtime.java:845)
    //       at java.base/java.lang.System.load(System.java:2025)
    //       at org.bytedeco.javacpp.Loader.loadLibrary(Loader.java:1779)
    //       at org.bytedeco.javacpp.Loader.load(Loader.java:1423)
    //       at org.bytedeco.javacpp.Loader.load(Loader.java:1234)
    //       at org.bytedeco.javacpp.Loader.load(Loader.java:1210)
    //       at org.bytedeco.javacpp.Loader.<clinit>(Loader.java:1973)
    //       at org.bytedeco.javacpp.Pointer.<clinit>(Pointer.java:521)
    //       at java.base/jdk.internal.misc.Unsafe.ensureClassInitialized0(Native Method)
    //       at java.base/jdk.internal.misc.Unsafe.ensureClassInitialized(Unsafe.java:1160)
    //   See https://diff.blue/R081 to resolve this issue.

    // Arrange
    // TODO: Populate arranged inputs
    Rect rect = null;
    double score = 0.0d;

    // Act
    RectWithScore actualRectWithScore = new RectWithScore(rect, score);

    // Assert
    // TODO: Add assertions on result
  }
}
