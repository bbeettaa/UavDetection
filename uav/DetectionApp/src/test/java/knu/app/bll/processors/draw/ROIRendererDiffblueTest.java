package knu.app.bll.processors.draw;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import knu.app.bll.utils.processors.TrackedObject;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ROIRendererDiffblueTest {

  /**
   * Test {@link ROIRenderer#render(Mat, List, List, boolean)} with {@code frame}, {@code rects},
   * {@code scores}, {@code renderScores}.
   *
   * <p>Method under test: {@link ROIRenderer#render(Mat, List, List, boolean)}
   */
  @Test
  @DisplayName(
      "Test render(Mat, List, List, boolean) with 'frame', 'rects', 'scores', 'renderScores'")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ROIRenderer.render(Mat, List, List, boolean)"})
  void testRenderWithFrameRectsScoresRenderScores() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    // TODO: Populate arranged inputs
    ROIRenderer roiRenderer = null;
    Mat frame = null;
    List<Rect> rects = null;
    List<Double> scores = null;
    boolean renderScores = false;

    // Act
    roiRenderer.render(frame, rects, scores, renderScores);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link ROIRenderer#render(Mat, List, List, boolean, Scalar, int, int)} with {@code frame},
   * {@code rects}, {@code scores}, {@code renderScores}, {@code color}, {@code thick},
   * {@code lineType}.
   *
   * <p>Method under test: {@link ROIRenderer#render(Mat, List, List, boolean, Scalar, int, int)}
   */
  @Test
  @DisplayName(
      "Test render(Mat, List, List, boolean, Scalar, int, int) with 'frame', 'rects', 'scores', 'renderScores', 'color', 'thick', 'lineType'")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ROIRenderer.render(Mat, List, List, boolean, Scalar, int, int)"})
  void testRenderWithFrameRectsScoresRenderScoresColorThickLineType() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    // TODO: Populate arranged inputs
    ROIRenderer roiRenderer = null;
    Mat frame = null;
    List<Rect> rects = null;
    List<Double> scores = null;
    boolean renderScores = false;
    Scalar color = null;
    int thick = 0;
    int lineType = 0;

    // Act
    roiRenderer.render(frame, rects, scores, renderScores, color, thick, lineType);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link ROIRenderer#render(Mat, List)} with {@code frame}, {@code result}.
   *
   * <p>Method under test: {@link ROIRenderer#render(Mat, List)}
   */
  @Test
  @DisplayName("Test render(Mat, List) with 'frame', 'result'")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ROIRenderer.render(Mat, List)"})
  void testRenderWithFrameResult() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    // TODO: Populate arranged inputs
    ROIRenderer roiRenderer = null;
    Mat frame = null;
    List<Point2f> result = null;

    // Act
    roiRenderer.render(frame, result);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link ROIRenderer#render(Mat, List, Scalar, int, int)} with {@code frame},
   * {@code result}, {@code color}, {@code thick}, {@code type}.
   *
   * <p>Method under test: {@link ROIRenderer#render(Mat, List, Scalar, int, int)}
   */
  @Test
  @DisplayName(
      "Test render(Mat, List, Scalar, int, int) with 'frame', 'result', 'color', 'thick', 'type'")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ROIRenderer.render(Mat, List, Scalar, int, int)"})
  void testRenderWithFrameResultColorThickType() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    // TODO: Populate arranged inputs
    ROIRenderer roiRenderer = null;
    Mat frame = null;
    List<Point2f> result = null;
    Scalar color = null;
    int thick = 0;
    int type = 0;

    // Act
    roiRenderer.render(frame, result, color, thick, type);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link ROIRenderer#render(Mat, List, boolean)} with {@code frame}, {@code trackedObjects},
   * {@code renderScores}.
   *
   * <p>Method under test: {@link ROIRenderer#render(Mat, List, boolean)}
   */
  @Test
  @DisplayName("Test render(Mat, List, boolean) with 'frame', 'trackedObjects', 'renderScores'")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ROIRenderer.render(Mat, List, boolean)"})
  void testRenderWithFrameTrackedObjectsRenderScores() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    // TODO: Populate arranged inputs
    ROIRenderer roiRenderer = null;
    Mat frame = null;
    List<TrackedObject> trackedObjects = null;
    boolean renderScores = false;

    // Act
    roiRenderer.render(frame, trackedObjects, renderScores);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link ROIRenderer#render(Mat, List, boolean, Scalar, int, int)} with {@code frame},
   * {@code trackedObjects}, {@code renderScores}, {@code color}, {@code thick}, {@code type}.
   *
   * <p>Method under test: {@link ROIRenderer#render(Mat, List, boolean, Scalar, int, int)}
   */
  @Test
  @DisplayName(
      "Test render(Mat, List, boolean, Scalar, int, int) with 'frame', 'trackedObjects', 'renderScores', 'color', 'thick', 'type'")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ROIRenderer.render(Mat, List, boolean, Scalar, int, int)"})
  void testRenderWithFrameTrackedObjectsRenderScoresColorThickType() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    // TODO: Populate arranged inputs
    ROIRenderer roiRenderer = null;
    Mat frame = null;
    List<TrackedObject> trackedObjects = null;
    boolean renderScores = false;
    Scalar color = null;
    int thick = 0;
    int type = 0;

    // Act
    roiRenderer.render(frame, trackedObjects, renderScores, color, thick, type);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ROIRenderer#setScalar(Scalar)}
   *   <li>{@link ROIRenderer#setThick(int)}
   *   <li>{@link ROIRenderer#setType(int)}
   *   <li>{@link ROIRenderer#getScalar()}
   *   <li>{@link ROIRenderer#getThick()}
   *   <li>{@link ROIRenderer#getType()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
      "Scalar ROIRenderer.getScalar()",
      "int ROIRenderer.getThick()",
      "int ROIRenderer.getType()",
      "void ROIRenderer.setScalar(Scalar)",
      "void ROIRenderer.setThick(int)",
      "void ROIRenderer.setType(int)"
  })
  void testGettersAndSetters() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: Exception in arrange section.
    //   Diffblue Cover was unable to construct an instance of the class under test using
    //   knu.app.bll.processors.draw.ROIRenderer.setScalar(Scalar).
    //   The arrange section threw
    //   java.lang.NoClassDefFoundError: Could not initialize class
    // org.bytedeco.opencv.opencv_core.AbstractScalar
    //       at knu.app.bll.processors.draw.ROIRenderer.<init>(ROIRenderer.java:12)
    //   See https://diff.blue/R081 to resolve this issue.

    // Arrange
    // TODO: Populate arranged inputs
    ROIRenderer roiRenderer = null;
    Scalar scalar = null;

    // Act
    roiRenderer.setScalar(scalar);
    int thick = 0;
    roiRenderer.setThick(thick);
    int type = 0;
    roiRenderer.setType(type);
    Scalar actualScalar = roiRenderer.getScalar();
    int actualThick = roiRenderer.getThick();
    int actualType = roiRenderer.getType();

    // Assert
    // TODO: Add assertions on result
  }
}
