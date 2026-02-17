package knu.app.bll.algorithms.kalman;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class TransitionMatrixKalmanFilterDiffblueTest {

  /**
   * Test {@link TransitionMatrixIKalmanFilter#TransitionMatrixIKalmanFilter()}.
   *
   * <p>Method under test: {@link TransitionMatrixIKalmanFilter#TransitionMatrixIKalmanFilter()}
   */
  @Test
  @DisplayName("Test new TransitionMatrixKalmanFilter()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TransitionMatrixKalmanFilter.<init>()"})
  void testNewTransitionMatrixKalmanFilter() {
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
    TransitionMatrixIKalmanFilter actualTransitionMatrixKalmanFilter =
        new TransitionMatrixIKalmanFilter();

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link TransitionMatrixIKalmanFilter#TransitionMatrixIKalmanFilter(float)}.
   *
   * <p>Method under test: {@link TransitionMatrixIKalmanFilter#TransitionMatrixIKalmanFilter(float)}
   */
  @Test
  @DisplayName("Test new TransitionMatrixKalmanFilter(float)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TransitionMatrixKalmanFilter.<init>(float)"})
  void testNewTransitionMatrixKalmanFilter2() {
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
    float gatingThreshold = 0.0f;

    // Act
    TransitionMatrixIKalmanFilter actualTransitionMatrixKalmanFilter =
        new TransitionMatrixIKalmanFilter(gatingThreshold);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link TransitionMatrixIKalmanFilter#reset(Point2f)}.
   *
   * <p>Method under test: {@link TransitionMatrixIKalmanFilter#reset(Point2f)}
   */
  @Test
  @DisplayName("Test reset(Point2f)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TransitionMatrixKalmanFilter.reset(Point2f)"})
  void testReset() {
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
    TransitionMatrixIKalmanFilter transitionMatrixKalmanFilter = new TransitionMatrixIKalmanFilter();

    // Act
    transitionMatrixKalmanFilter.reset(new Point2f());

    // Assert
  }

  /**
   * Test {@link TransitionMatrixIKalmanFilter#update(Point2f)}.
   *
   * <p>Method under test: {@link TransitionMatrixIKalmanFilter#update(Point2f)}
   */
  @Test
  @DisplayName("Test update(Point2f)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Point2f TransitionMatrixKalmanFilter.update(Point2f)"})
  void testUpdate() {
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
    TransitionMatrixIKalmanFilter transitionMatrixKalmanFilter = new TransitionMatrixIKalmanFilter();

    // Act
    transitionMatrixKalmanFilter.update(new Point2f());

    // Assert
  }

  /**
   * Test {@link TransitionMatrixIKalmanFilter#releaseResources()}.
   *
   * <p>Method under test: {@link TransitionMatrixIKalmanFilter#releaseResources()}
   */
  @Test
  @DisplayName("Test releaseResources()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TransitionMatrixKalmanFilter.releaseResources()"})
  void testReleaseResources() {
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
    new TransitionMatrixIKalmanFilter().releaseResources();

    // Assert
  }

  /**
   * Test {@link TransitionMatrixIKalmanFilter#getState()}.
   *
   * <p>Method under test: {@link TransitionMatrixIKalmanFilter#getState()}
   */
  @Test
  @DisplayName("Test getState()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Mat TransitionMatrixKalmanFilter.getState()"})
  void testGetState() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: Exception in arrange section.
    //   Diffblue Cover was unable to construct an instance of the class under test using
    //   knu.app.bll.algorithms.kalman.TransitionMatrixKalmanFilter.getState().
    //   The arrange section threw
    //   com.diffblue.cover.sandbox.execution.ForbiddenByPolicyException: JNI sandbox policy
    // violation
    //   See https://diff.blue/R081 to resolve this issue.

    // Arrange
    // TODO: Populate arranged inputs
    TransitionMatrixIKalmanFilter transitionMatrixKalmanFilter = null;

    // Act
    Mat actualState = transitionMatrixKalmanFilter.getState();

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link TransitionMatrixIKalmanFilter#setDt(float)}.
   *
   * <ul>
   *   <li>When zero.
   * </ul>
   *
   * <p>Method under test: {@link TransitionMatrixIKalmanFilter#setDt(float)}
   */
  @Test
  @DisplayName("Test setDt(float); when zero")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TransitionMatrixKalmanFilter.setDt(float)"})
  void testSetDt_whenZero() {
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
    new TransitionMatrixIKalmanFilter().setDt(0.0f);

    // Assert
  }
}
