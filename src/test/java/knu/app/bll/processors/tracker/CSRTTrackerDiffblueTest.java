package knu.app.bll.processors.tracker;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import knu.app.bll.processors.tracker.single.CSRTTracker;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Rect;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CSRTTrackerDiffblueTest {

  /**
   * Test new {@link CSRTTracker} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link CSRTTracker}
   */
  @Test
  @DisplayName("Test new CSRTTracker (default constructor)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CSRTTracker.<init>()"})
  void testNewCSRTTracker() {
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
    CSRTTracker actualCsrtTracker = new CSRTTracker();

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link CSRTTracker#init(Mat, List)} with {@code frame}, {@code roiPoints}.
   *
   * <p>Method under test: {@link CSRTTracker#init(Mat, List)}
   */
  @Test
  @DisplayName("Test init(Mat, List) with 'frame', 'roiPoints'")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CSRTTracker.init(Mat, List)"})
  void testInitWithFrameRoiPoints() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    // TODO: Populate arranged inputs
    CSRTTracker csrtTracker = null;
    Mat frame = null;
    List<Point2f> roiPoints = null;

    // Act
    boolean actualInitResult = csrtTracker.init(frame, roiPoints);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link CSRTTracker#init(Mat, Rect)} with {@code frame}, {@code roi}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link CSRTTracker#init(Mat, Rect)}
   */
  @Test
  @DisplayName("Test init(Mat, Rect) with 'frame', 'roi'; when 'null'")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CSRTTracker.init(Mat, Rect)"})
  void testInitWithFrameRoi_whenNull() {
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
    CSRTTracker csrtTracker = new CSRTTracker();

    // Act
    csrtTracker.init(new Mat(), (Rect) null);

    // Assert
  }

  /**
   * Test {@link CSRTTracker#update(Mat, List)}.
   *
   * <p>Method under test: {@link CSRTTracker#update(Mat, List)}
   */
  @Test
  @DisplayName("Test update(Mat, List)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List CSRTTracker.update(Mat, List)"})
  void testUpdate() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    // TODO: Populate arranged inputs
    CSRTTracker csrtTracker = null;
    Mat frame = null;
    List<Point2f> detectionPoints = null;

    // Act
    List<Point2f> actualUpdateResult = csrtTracker.update(frame, detectionPoints);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link CSRTTracker#close()}.
   *
   * <p>Method under test: {@link CSRTTracker#close()}
   */
  @Test
  @DisplayName("Test close()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CSRTTracker.close()"})
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
    new CSRTTracker().close();

    // Assert
  }
}
