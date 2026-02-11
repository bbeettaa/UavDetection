package knu.app.bll.processors.tracker;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import knu.app.bll.processors.tracker.single.OpticalFlowTracker;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Rect;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class OpticalFlowTrackerDiffblueTest {

  /**
   * Test {@link OpticalFlowTracker#init(Mat, List)} with {@code frameGray}, {@code initialPoints}.
   *
   * <p>Method under test: {@link OpticalFlowTracker#init(Mat, List)}
   */
  @Test
  @DisplayName("Test init(Mat, List) with 'frameGray', 'initialPoints'")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OpticalFlowTracker.init(Mat, List)"})
  void testInitWithFrameGrayInitialPoints() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    // TODO: Populate arranged inputs
    OpticalFlowTracker opticalFlowTracker = null;
    Mat frameGray = null;
    List<Point2f> initialPoints = null;

    // Act
    boolean actualInitResult = opticalFlowTracker.init(frameGray, initialPoints);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link OpticalFlowTracker#init(Mat, Rect)} with {@code frameGray}, {@code roi}.
   *
   * <p>Method under test: {@link OpticalFlowTracker#init(Mat, Rect)}
   */
  @Test
  @DisplayName("Test init(Mat, Rect) with 'frameGray', 'roi'")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OpticalFlowTracker.init(Mat, Rect)"})
  void testInitWithFrameGrayRoi() {
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
    OpticalFlowTracker opticalFlowTracker = new OpticalFlowTracker();
    Mat frameGray = new Mat();

    // Act
    opticalFlowTracker.init(frameGray, new Rect());

    // Assert
  }

  /**
   * Test {@link OpticalFlowTracker#update(Mat, List)}.
   *
   * <p>Method under test: {@link OpticalFlowTracker#update(Mat, List)}
   */
  @Test
  @DisplayName("Test update(Mat, List)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List OpticalFlowTracker.update(Mat, List)"})
  void testUpdate() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    // TODO: Populate arranged inputs
    OpticalFlowTracker opticalFlowTracker = null;
    Mat frameGray = null;
    List<Point2f> newPoints = null;

    // Act
    List<Point2f> actualUpdateResult = opticalFlowTracker.update(frameGray, newPoints);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link OpticalFlowTracker#close()}.
   *
   * <p>Method under test: {@link OpticalFlowTracker#close()}
   */
  @Test
  @DisplayName("Test close()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void OpticalFlowTracker.close()"})
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
    new OpticalFlowTracker().close();

    // Assert
  }
}
