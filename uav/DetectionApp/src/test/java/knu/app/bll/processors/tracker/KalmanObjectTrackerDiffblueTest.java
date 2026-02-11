package knu.app.bll.processors.tracker;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import knu.app.bll.processors.tracker.single.KalmanObjectTracker;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Rect;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class KalmanObjectTrackerDiffblueTest {

  /**
   * Test {@link KalmanObjectTracker#KalmanObjectTracker()}.
   *
   * <p>Method under test: {@link KalmanObjectTracker#KalmanObjectTracker()}
   */
  @Test
  @DisplayName("Test new KalmanObjectTracker()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void KalmanObjectTracker.<init>()"})
  void testNewKalmanObjectTracker() {
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
    KalmanObjectTracker actualKalmanObjectTracker = new KalmanObjectTracker();

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link KalmanObjectTracker#KalmanObjectTracker(float)}.
   *
   * <p>Method under test: {@link KalmanObjectTracker#KalmanObjectTracker(float)}
   */
  @Test
  @DisplayName("Test new KalmanObjectTracker(float)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void KalmanObjectTracker.<init>(float)"})
  void testNewKalmanObjectTracker2() {
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
    float gatingTh = 0.0f;

    // Act
    KalmanObjectTracker actualKalmanObjectTracker = new KalmanObjectTracker(gatingTh);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link KalmanObjectTracker#init(Mat, List)} with {@code frame}, {@code initialPoints}.
   *
   * <p>Method under test: {@link KalmanObjectTracker#init(Mat, List)}
   */
  @Test
  @DisplayName("Test init(Mat, List) with 'frame', 'initialPoints'")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean KalmanObjectTracker.init(Mat, List)"})
  void testInitWithFrameInitialPoints() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    // TODO: Populate arranged inputs
    KalmanObjectTracker kalmanObjectTracker = null;
    Mat frame = null;
    List<Point2f> initialPoints = null;

    // Act
    boolean actualInitResult = kalmanObjectTracker.init(frame, initialPoints);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link KalmanObjectTracker#init(Mat, Rect)} with {@code frame}, {@code roi}.
   *
   * <p>Method under test: {@link KalmanObjectTracker#init(Mat, Rect)}
   */
  @Test
  @DisplayName("Test init(Mat, Rect) with 'frame', 'roi'")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean KalmanObjectTracker.init(Mat, Rect)"})
  void testInitWithFrameRoi() {
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
    KalmanObjectTracker kalmanObjectTracker = new KalmanObjectTracker();
    Mat frame = new Mat();

    // Act
    kalmanObjectTracker.init(frame, new Rect());

    // Assert
  }

  /**
   * Test {@link KalmanObjectTracker#update(Mat, List)}.
   *
   * <p>Method under test: {@link KalmanObjectTracker#update(Mat, List)}
   */
  @Test
  @DisplayName("Test update(Mat, List)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List KalmanObjectTracker.update(Mat, List)"})
  void testUpdate() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    // TODO: Populate arranged inputs
    KalmanObjectTracker kalmanObjectTracker = null;
    Mat frame = null;
    List<Point2f> detectionPoints = null;

    // Act
    List<Point2f> actualUpdateResult = kalmanObjectTracker.update(frame, detectionPoints);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link KalmanObjectTracker#close()}.
   *
   * <p>Method under test: {@link KalmanObjectTracker#close()}
   */
  @Test
  @DisplayName("Test close()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void KalmanObjectTracker.close()"})
  void testClose() {
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
    new KalmanObjectTracker().close();

    // Assert
  }
}
