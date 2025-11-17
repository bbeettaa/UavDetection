package knu.app.bll.processors.detection;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import knu.app.bll.utils.processors.DetectionResult;
import org.bytedeco.opencv.opencv_core.Mat;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SURFObjectDetectorDiffblueTest {

  /**
   * Test {@link SURFObjectDetector#SURFObjectDetector(Mat)}.
   *
   * <p>Method under test: {@link SURFObjectDetector#SURFObjectDetector(Mat)}
   */
  @Test
  @DisplayName("Test new SURFObjectDetector(Mat)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SURFObjectDetector.<init>(Mat)"})
  void testNewSURFObjectDetector() {
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
    Mat templateImage = null;

    // Act
    SURFObjectDetector actualSurfObjectDetector = new SURFObjectDetector(templateImage);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link SURFObjectDetector#SURFObjectDetector(Mat, int, int, int, boolean, boolean)}.
   *
   * <p>Method under test:
   * {@link SURFObjectDetector#SURFObjectDetector(Mat, int, int, int, boolean,
   * boolean)}
   */
  @Test
  @DisplayName("Test new SURFObjectDetector(Mat, int, int, int, boolean, boolean)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SURFObjectDetector.<init>(Mat, int, int, int, boolean, boolean)"})
  void testNewSURFObjectDetector2() {
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
    Mat templateImage = null;
    int hessianThreshold = 0;
    int nOctaves = 0;
    int nOctaveLayers = 0;
    boolean extended = false;
    boolean upright = false;

    // Act
    SURFObjectDetector actualSurfObjectDetector =
        new SURFObjectDetector(
            templateImage, hessianThreshold, nOctaves, nOctaveLayers, extended, upright);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link SURFObjectDetector#init(int, int, int, boolean, boolean)}.
   *
   * <p>Method under test: {@link SURFObjectDetector#init(int, int, int, boolean, boolean)}
   */
  @Test
  @DisplayName("Test init(int, int, int, boolean, boolean)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SURFObjectDetector.init(int, int, int, boolean, boolean)"})
  void testInit() {
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
    SURFObjectDetector surfObjectDetector = null;
    int hessianThreshold = 0;
    int nOctaves = 0;
    int nOctaveLayers = 0;
    boolean extended = false;
    boolean upright = false;

    // Act
    surfObjectDetector.init(hessianThreshold, nOctaves, nOctaveLayers, extended, upright);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link SURFObjectDetector#detect(Mat)}.
   *
   * <p>Method under test: {@link SURFObjectDetector#detect(Mat)}
   */
  @Test
  @DisplayName("Test detect(Mat)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"DetectionResult SURFObjectDetector.detect(Mat)"})
  void testDetect() {
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
    SURFObjectDetector surfObjectDetector = null;
    Mat frameGray = null;

    // Act
    DetectionResult actualDetectResult = surfObjectDetector.detect(frameGray);

    // Assert
    // TODO: Add assertions on result
  }
}
