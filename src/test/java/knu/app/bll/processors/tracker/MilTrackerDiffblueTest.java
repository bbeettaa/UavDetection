package knu.app.bll.processors.tracker;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Rect;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class MilTrackerDiffblueTest {

  /**
   * Test new {@link MilTracker} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link MilTracker}
   */
  @Test
  @DisplayName("Test new MilTracker (default constructor)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void MilTracker.<init>()"})
  void testNewMilTracker() {
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
    MilTracker actualMilTracker = new MilTracker();

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link MilTracker#init(Mat, List)} with {@code frame}, {@code roiPoints}.
   *
   * <p>Method under test: {@link MilTracker#init(Mat, List)}
   */
  @Test
  @DisplayName("Test init(Mat, List) with 'frame', 'roiPoints'")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean MilTracker.init(Mat, List)"})
  void testInitWithFrameRoiPoints() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    // TODO: Populate arranged inputs
    MilTracker milTracker = null;
    Mat frame = null;
    List<Point2f> roiPoints = null;

    // Act
    boolean actualInitResult = milTracker.init(frame, roiPoints);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link MilTracker#init(Mat, Rect)} with {@code frame}, {@code roi}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link MilTracker#init(Mat, Rect)}
   */
  @Test
  @DisplayName("Test init(Mat, Rect) with 'frame', 'roi'; when 'null'")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean MilTracker.init(Mat, Rect)"})
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
    MilTracker milTracker = new MilTracker();

    // Act
    milTracker.init(new Mat(), (Rect) null);

    // Assert
  }

  /**
   * Test {@link MilTracker#update(Mat, List)}.
   *
   * <p>Method under test: {@link MilTracker#update(Mat, List)}
   */
  @Test
  @DisplayName("Test update(Mat, List)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List MilTracker.update(Mat, List)"})
  void testUpdate() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    // TODO: Populate arranged inputs
    MilTracker milTracker = null;
    Mat frame = null;
    List<Point2f> detectionPoints = null;

    // Act
    List<Point2f> actualUpdateResult = milTracker.update(frame, detectionPoints);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link MilTracker#close()}.
   *
   * <p>Method under test: {@link MilTracker#close()}
   */
  @Test
  @DisplayName("Test close()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void MilTracker.close()"})
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
    new MilTracker().close();

    // Assert
  }
}
