package knu.app.bll.processors.detection;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import knu.app.bll.utils.processors.DetectionResult;
import org.bytedeco.opencv.opencv_core.Mat;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ORBObjectDetectorDiffblueTest {

  /**
   * Test {@link ORBObjectDetector#ORBObjectDetector(Mat)}.
   *
   * <p>Method under test: {@link ORBObjectDetector#ORBObjectDetector(Mat)}
   */
  @Test
  @DisplayName("Test new ORBObjectDetector(Mat)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ORBObjectDetector.<init>(Mat)"})
  void testNewORBObjectDetector() {
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
    ORBObjectDetector actualOrbObjectDetector = new ORBObjectDetector(templateImage);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link ORBObjectDetector#init(int, float, int, int, int, int, int, int, int)}.
   *
   * <p>Method under test: {@link ORBObjectDetector#init(int, float, int, int, int, int, int, int,
   * int)}
   */
  @Test
  @DisplayName("Test init(int, float, int, int, int, int, int, int, int)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ORBObjectDetector.init(int, float, int, int, int, int, int, int, int)"})
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
    ORBObjectDetector orbObjectDetector = null;
    int nfeatures = 0;
    float scaleFactor = 0.0f;
    int nlevels = 0;
    int edgeThreshold = 0;
    int firstLevel = 0;
    int WTA_K = 0;
    int scoreType = 0;
    int patchSize = 0;
    int fastThreshold = 0;

    // Act
    orbObjectDetector.init(
        nfeatures,
        scaleFactor,
        nlevels,
        edgeThreshold,
        firstLevel,
        WTA_K,
        scoreType,
        patchSize,
        fastThreshold);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link ORBObjectDetector#detect(Mat)}.
   *
   * <p>Method under test: {@link ORBObjectDetector#detect(Mat)}
   */
  @Test
  @DisplayName("Test detect(Mat)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"DetectionResult ORBObjectDetector.detect(Mat)"})
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
    ORBObjectDetector orbObjectDetector = null;
    Mat frameGray = null;

    // Act
    DetectionResult actualDetectResult = orbObjectDetector.detect(frameGray);

    // Assert
    // TODO: Add assertions on result
  }
}
