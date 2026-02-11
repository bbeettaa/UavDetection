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

class KeypointsRendererDiffblueTest {

  /**
   * Test {@link KeypointsRenderer#KeypointsRenderer(int)}.
   *
   * <p>Method under test: {@link KeypointsRenderer#KeypointsRenderer(int)}
   */
  @Test
  @DisplayName("Test new KeypointsRenderer(int)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void KeypointsRenderer.<init>(int)"})
  void testNewKeypointsRenderer() {
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
    int r = 0;

    // Act
    KeypointsRenderer actualKeypointsRenderer = new KeypointsRenderer(r);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link KeypointsRenderer#KeypointsRenderer()}.
   *
   * <p>Method under test: {@link KeypointsRenderer#KeypointsRenderer()}
   */
  @Test
  @DisplayName("Test new KeypointsRenderer()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void KeypointsRenderer.<init>()"})
  void testNewKeypointsRenderer2() {
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

    // Arrange and Act
    // TODO: Populate arranged inputs
    KeypointsRenderer actualKeypointsRenderer = new KeypointsRenderer();

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link KeypointsRenderer#render(Mat, List, List, boolean)} with {@code frame},
   * {@code rects}, {@code scores}, {@code renderScores}.
   *
   * <p>Method under test: {@link KeypointsRenderer#render(Mat, List, List, boolean)}
   */
  @Test
  @DisplayName(
      "Test render(Mat, List, List, boolean) with 'frame', 'rects', 'scores', 'renderScores'")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void KeypointsRenderer.render(Mat, List, List, boolean)"})
  void testRenderWithFrameRectsScoresRenderScores() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    // TODO: Populate arranged inputs
    KeypointsRenderer keypointsRenderer = null;
    Mat frame = null;
    List<Rect> rects = null;
    List<Double> scores = null;
    boolean renderScores = false;

    // Act
    keypointsRenderer.render(frame, rects, scores, renderScores);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link KeypointsRenderer#render(Mat, List, List, boolean, Scalar, int, int)} with
   * {@code frame}, {@code rects}, {@code scores}, {@code renderScores}, {@code color},
   * {@code thick}, {@code type}.
   *
   * <p>Method under test: {@link KeypointsRenderer#render(Mat, List, List, boolean, Scalar, int,
   * int)}
   */
  @Test
  @DisplayName(
      "Test render(Mat, List, List, boolean, Scalar, int, int) with 'frame', 'rects', 'scores', 'renderScores', 'color', 'thick', 'type'")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void KeypointsRenderer.render(Mat, List, List, boolean, Scalar, int, int)"})
  void testRenderWithFrameRectsScoresRenderScoresColorThickType() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    // TODO: Populate arranged inputs
    KeypointsRenderer keypointsRenderer = null;
    Mat frame = null;
    List<Rect> rects = null;
    List<Double> scores = null;
    boolean renderScores = false;
    Scalar color = null;
    int thick = 0;
    int type = 0;

    // Act
    keypointsRenderer.render(frame, rects, scores, renderScores, color, thick, type);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link KeypointsRenderer#render(Mat, List)} with {@code frame}, {@code result}.
   *
   * <p>Method under test: {@link KeypointsRenderer#render(Mat, List)}
   */
  @Test
  @DisplayName("Test render(Mat, List) with 'frame', 'result'")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void KeypointsRenderer.render(Mat, List)"})
  void testRenderWithFrameResult() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    // TODO: Populate arranged inputs
    KeypointsRenderer keypointsRenderer = null;
    Mat frame = null;
    List<Point2f> result = null;

    // Act
    keypointsRenderer.render(frame, result);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link KeypointsRenderer#render(Mat, List, Scalar, int, int)} with {@code frame},
   * {@code result}, {@code color}, {@code thick}, {@code type}.
   *
   * <p>Method under test: {@link KeypointsRenderer#render(Mat, List, Scalar, int, int)}
   */
  @Test
  @DisplayName(
      "Test render(Mat, List, Scalar, int, int) with 'frame', 'result', 'color', 'thick', 'type'")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void KeypointsRenderer.render(Mat, List, Scalar, int, int)"})
  void testRenderWithFrameResultColorThickType() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    // TODO: Populate arranged inputs
    KeypointsRenderer keypointsRenderer = null;
    Mat frame = null;
    List<Point2f> result = null;
    Scalar color = null;
    int thick = 0;
    int type = 0;

    // Act
    keypointsRenderer.render(frame, result, color, thick, type);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link KeypointsRenderer#render(Mat, List, boolean)} with {@code frame},
   * {@code trackedObjects}, {@code renderScores}.
   *
   * <p>Method under test: {@link KeypointsRenderer#render(Mat, List, boolean)}
   */
  @Test
  @DisplayName("Test render(Mat, List, boolean) with 'frame', 'trackedObjects', 'renderScores'")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void KeypointsRenderer.render(Mat, List, boolean)"})
  void testRenderWithFrameTrackedObjectsRenderScores() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    // TODO: Populate arranged inputs
    KeypointsRenderer keypointsRenderer = null;
    Mat frame = null;
    List<TrackedObject> trackedObjects = null;
    boolean renderScores = false;

    // Act
    keypointsRenderer.render(frame, trackedObjects, renderScores);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link KeypointsRenderer#render(Mat, List, boolean, Scalar, int, int)} with {@code frame},
   * {@code trackedObjects}, {@code renderScores}, {@code color}, {@code thick}, {@code type}.
   *
   * <p>Method under test: {@link KeypointsRenderer#render(Mat, List, boolean, Scalar, int, int)}
   */
  @Test
  @DisplayName(
      "Test render(Mat, List, boolean, Scalar, int, int) with 'frame', 'trackedObjects', 'renderScores', 'color', 'thick', 'type'")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void KeypointsRenderer.render(Mat, List, boolean, Scalar, int, int)"})
  void testRenderWithFrameTrackedObjectsRenderScoresColorThickType() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    // TODO: Populate arranged inputs
    KeypointsRenderer keypointsRenderer = null;
    Mat frame = null;
    List<TrackedObject> trackedObjects = null;
    boolean renderScores = false;
    Scalar color = null;
    int thick = 0;
    int type = 0;

    // Act
    keypointsRenderer.render(frame, trackedObjects, renderScores, color, thick, type);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link KeypointsRenderer#setR(int)}
   *   <li>{@link KeypointsRenderer#getR()}
   *   <li>{@link KeypointsRenderer#getScalar()}
   *   <li>{@link KeypointsRenderer#getThick()}
   *   <li>{@link KeypointsRenderer#getType()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
      "int KeypointsRenderer.getR()",
      "Scalar KeypointsRenderer.getScalar()",
      "int KeypointsRenderer.getThick()",
      "int KeypointsRenderer.getType()",
      "void KeypointsRenderer.setR(int)"
  })
  void testGettersAndSetters() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: Exception in arrange section.
    //   Diffblue Cover was unable to construct an instance of the class under test using
    //   knu.app.bll.processors.draw.KeypointsRenderer.setR(int).
    //   The arrange section threw
    //   java.lang.NoClassDefFoundError: Could not initialize class
    // org.bytedeco.opencv.opencv_core.AbstractScalar
    //       at knu.app.bll.processors.draw.KeypointsRenderer.<init>(KeypointsRenderer.java:20)
    //       at knu.app.bll.processors.draw.KeypointsRenderer.<init>(KeypointsRenderer.java:24)
    //   See https://diff.blue/R081 to resolve this issue.

    // Arrange
    // TODO: Populate arranged inputs
    KeypointsRenderer keypointsRenderer = null;
    int r = 0;

    // Act
    keypointsRenderer.setR(r);
    int actualR = keypointsRenderer.getR();
    Scalar actualScalar = keypointsRenderer.getScalar();
    int actualThick = keypointsRenderer.getThick();
    int actualType = keypointsRenderer.getType();

    // Assert
    // TODO: Add assertions on result
  }
}
