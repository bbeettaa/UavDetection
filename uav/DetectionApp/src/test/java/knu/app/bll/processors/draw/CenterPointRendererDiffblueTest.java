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

class CenterPointRendererDiffblueTest {

  /**
   * Test {@link CenterPointRenderer#CenterPointRenderer()}.
   *
   * <p>Method under test: {@link CenterPointRenderer#CenterPointRenderer()}
   */
  @Test
  @DisplayName("Test new CenterPointRenderer()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CenterPointRenderer.<init>()"})
  void testNewCenterPointRenderer() {
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
    CenterPointRenderer actualCenterPointRenderer = new CenterPointRenderer();

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link CenterPointRenderer#CenterPointRenderer(int)}.
   *
   * <p>Method under test: {@link CenterPointRenderer#CenterPointRenderer(int)}
   */
  @Test
  @DisplayName("Test new CenterPointRenderer(int)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CenterPointRenderer.<init>(int)"})
  void testNewCenterPointRenderer2() {
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
    CenterPointRenderer actualCenterPointRenderer = new CenterPointRenderer(r);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link CenterPointRenderer#render(Mat, List, List, boolean)} with {@code frame},
   * {@code rects}, {@code scores}, {@code renderScores}.
   *
   * <p>Method under test: {@link CenterPointRenderer#render(Mat, List, List, boolean)}
   */
  @Test
  @DisplayName(
      "Test render(Mat, List, List, boolean) with 'frame', 'rects', 'scores', 'renderScores'")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CenterPointRenderer.render(Mat, List, List, boolean)"})
  void testRenderWithFrameRectsScoresRenderScores() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    // TODO: Populate arranged inputs
    CenterPointRenderer centerPointRenderer = null;
    Mat frame = null;
    List<Rect> rects = null;
    List<Double> scores = null;
    boolean renderScores = false;

    // Act
    centerPointRenderer.render(frame, rects, scores, renderScores);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link CenterPointRenderer#render(Mat, List, List, boolean, Scalar, int, int)} with
   * {@code frame}, {@code rects}, {@code scores}, {@code renderScores}, {@code color},
   * {@code thick}, {@code lineType}.
   *
   * <p>Method under test: {@link CenterPointRenderer#render(Mat, List, List, boolean, Scalar, int,
   * int)}
   */
  @Test
  @DisplayName(
      "Test render(Mat, List, List, boolean, Scalar, int, int) with 'frame', 'rects', 'scores', 'renderScores', 'color', 'thick', 'lineType'")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CenterPointRenderer.render(Mat, List, List, boolean, Scalar, int, int)"})
  void testRenderWithFrameRectsScoresRenderScoresColorThickLineType() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    // TODO: Populate arranged inputs
    CenterPointRenderer centerPointRenderer = null;
    Mat frame = null;
    List<Rect> rects = null;
    List<Double> scores = null;
    boolean renderScores = false;
    Scalar color = null;
    int thick = 0;
    int lineType = 0;

    // Act
    centerPointRenderer.render(frame, rects, scores, renderScores, color, thick, lineType);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link CenterPointRenderer#render(Mat, List)} with {@code frame}, {@code result}.
   *
   * <p>Method under test: {@link CenterPointRenderer#render(Mat, List)}
   */
  @Test
  @DisplayName("Test render(Mat, List) with 'frame', 'result'")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CenterPointRenderer.render(Mat, List)"})
  void testRenderWithFrameResult() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    // TODO: Populate arranged inputs
    CenterPointRenderer centerPointRenderer = null;
    Mat frame = null;
    List<Point2f> result = null;

    // Act
    centerPointRenderer.render(frame, result);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link CenterPointRenderer#render(Mat, List, Scalar, int, int)} with {@code frame},
   * {@code result}, {@code color}, {@code thick}, {@code type}.
   *
   * <p>Method under test: {@link CenterPointRenderer#render(Mat, List, Scalar, int, int)}
   */
  @Test
  @DisplayName(
      "Test render(Mat, List, Scalar, int, int) with 'frame', 'result', 'color', 'thick', 'type'")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CenterPointRenderer.render(Mat, List, Scalar, int, int)"})
  void testRenderWithFrameResultColorThickType() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    // TODO: Populate arranged inputs
    CenterPointRenderer centerPointRenderer = null;
    Mat frame = null;
    List<Point2f> result = null;
    Scalar color = null;
    int thick = 0;
    int type = 0;

    // Act
    centerPointRenderer.render(frame, result, color, thick, type);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link CenterPointRenderer#render(Mat, List, boolean)} with {@code frame},
   * {@code trackedObjects}, {@code renderScores}.
   *
   * <p>Method under test: {@link CenterPointRenderer#render(Mat, List, boolean)}
   */
  @Test
  @DisplayName("Test render(Mat, List, boolean) with 'frame', 'trackedObjects', 'renderScores'")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CenterPointRenderer.render(Mat, List, boolean)"})
  void testRenderWithFrameTrackedObjectsRenderScores() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    // TODO: Populate arranged inputs
    CenterPointRenderer centerPointRenderer = null;
    Mat frame = null;
    List<TrackedObject> trackedObjects = null;
    boolean renderScores = false;

    // Act
    centerPointRenderer.render(frame, trackedObjects, renderScores);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link CenterPointRenderer#render(Mat, List, boolean, Scalar, int, int)} with
   * {@code frame}, {@code trackedObjects}, {@code renderScores}, {@code color}, {@code thickness},
   * {@code lineType}.
   *
   * <p>Method under test: {@link CenterPointRenderer#render(Mat, List, boolean, Scalar, int, int)}
   */
  @Test
  @DisplayName(
      "Test render(Mat, List, boolean, Scalar, int, int) with 'frame', 'trackedObjects', 'renderScores', 'color', 'thickness', 'lineType'")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CenterPointRenderer.render(Mat, List, boolean, Scalar, int, int)"})
  void testRenderWithFrameTrackedObjectsRenderScoresColorThicknessLineType() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    // TODO: Populate arranged inputs
    CenterPointRenderer centerPointRenderer = null;
    Mat frame = null;
    List<TrackedObject> trackedObjects = null;
    boolean renderScores = false;
    Scalar color = null;
    int thickness = 0;
    int lineType = 0;

    // Act
    centerPointRenderer.render(frame, trackedObjects, renderScores, color, thickness, lineType);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CenterPointRenderer#setR(int)}
   *   <li>{@link CenterPointRenderer#getR()}
   *   <li>{@link CenterPointRenderer#getScalar()}
   *   <li>{@link CenterPointRenderer#getThick()}
   *   <li>{@link CenterPointRenderer#getType()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
      "int CenterPointRenderer.getR()",
      "Scalar CenterPointRenderer.getScalar()",
      "int CenterPointRenderer.getThick()",
      "int CenterPointRenderer.getType()",
      "void CenterPointRenderer.setR(int)"
  })
  void testGettersAndSetters() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: Exception in arrange section.
    //   Diffblue Cover was unable to construct an instance of the class under test using
    //   knu.app.bll.processors.draw.CenterPointRenderer.setR(int).
    //   The arrange section threw
    //   java.lang.NoClassDefFoundError: Could not initialize class
    // org.bytedeco.openblas.global.openblas_nolapack
    //       at java.base/java.lang.Class.forName0(Native Method)
    //       at java.base/java.lang.Class.forName(Class.java:534)
    //       at java.base/java.lang.Class.forName(Class.java:513)
    //       at org.bytedeco.javacpp.Loader.load(Loader.java:1289)
    //       at org.bytedeco.javacpp.Loader.load(Loader.java:1234)
    //       at org.bytedeco.javacpp.Loader.load(Loader.java:1210)
    //       at org.bytedeco.opencv.opencv_core.AbstractScalar.<clinit>(AbstractScalar.java:10)
    //       at knu.app.bll.processors.draw.CenterPointRenderer.<init>(CenterPointRenderer.java:19)
    //       at knu.app.bll.processors.draw.CenterPointRenderer.<init>(CenterPointRenderer.java:25)
    //   See https://diff.blue/R081 to resolve this issue.

    // Arrange
    // TODO: Populate arranged inputs
    CenterPointRenderer centerPointRenderer = null;
    int r = 0;

    // Act
    centerPointRenderer.setR(r);
    int actualR = centerPointRenderer.getR();
    Scalar actualScalar = centerPointRenderer.getScalar();
    int actualThick = centerPointRenderer.getThick();
    int actualType = centerPointRenderer.getType();

    // Assert
    // TODO: Add assertions on result
  }
}
