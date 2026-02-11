package knu.app.bll.processors.detection;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import knu.app.bll.utils.processors.DetectionResult;
import org.bytedeco.opencv.opencv_core.Mat;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SIFTObjectDetectorDiffblueTest {

  /**
   * Test {@link SIFTObjectDetector#SIFTObjectDetector(Mat)}.
   *
   * <p>Method under test: {@link SIFTObjectDetector#SIFTObjectDetector(Mat)}
   */
  @Test
  @DisplayName("Test new SIFTObjectDetector(Mat)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SIFTObjectDetector.<init>(Mat)"})
  void testNewSIFTObjectDetector() {
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
    SIFTObjectDetector actualSiftObjectDetector = new SIFTObjectDetector(templateImage);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link SIFTObjectDetector#init(int, int, double, double, double)}.
   *
   * <p>Method under test: {@link SIFTObjectDetector#init(int, int, double, double, double)}
   */
  @Test
  @DisplayName("Test init(int, int, double, double, double)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SIFTObjectDetector.init(int, int, double, double, double)"})
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
    SIFTObjectDetector siftObjectDetector = null;
    int nfeatures = 0;
    int nOctaves = 0;
    double contrastThreshold = 0.0d;
    double edgeThreshold = 0.0d;
    double sigma = 0.0d;

    // Act
    siftObjectDetector.init(nfeatures, nOctaves, contrastThreshold, edgeThreshold, sigma);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link SIFTObjectDetector#detect(Mat)}.
   *
   * <p>Method under test: {@link SIFTObjectDetector#detect(Mat)}
   */
  @Test
  @DisplayName("Test detect(Mat)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"DetectionResult SIFTObjectDetector.detect(Mat)"})
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
    SIFTObjectDetector siftObjectDetector = null;
    Mat frameGray = null;

    // Act
    DetectionResult actualDetectResult = siftObjectDetector.detect(frameGray);

    // Assert
    // TODO: Add assertions on result
  }
}
