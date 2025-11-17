package knu.app.bll.processors.detection;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import knu.app.bll.utils.processors.DetectionResult;
import knu.app.bll.utils.processors.hog.HogSvmDetectorConfig;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_objdetect.HOGDescriptor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class HogSvmDetectorDiffblueTest {

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link HogSvmDetector#HogSvmDetector(HOGDescriptor, HogSvmDetectorConfig)}
   *   <li>{@link HogSvmDetector#getConfig()}
   *   <li>{@link HogSvmDetector#getHog()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
      "void HogSvmDetector.<init>(HOGDescriptor, HogSvmDetectorConfig)",
      "HogSvmDetectorConfig HogSvmDetector.getConfig()",
      "HOGDescriptor HogSvmDetector.getHog()"
  })
  void testGettersAndSetters() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: Exception in arrange section.
    //   Diffblue Cover was unable to construct an instance of the class under test using
    //   knu.app.bll.processors.detection.HogSvmDetector.<init>(HOGDescriptor,
    // HogSvmDetectorConfig).
    //   The arrange section threw
    //   java.lang.IllegalStateException
    //       at java.base/java.lang.Runtime.load0(Runtime.java:845)
    //       at java.base/java.lang.System.load(System.java:2025)
    //       at org.bytedeco.javacpp.Loader.loadLibrary(Loader.java:1779)
    //       at org.bytedeco.javacpp.Loader.load(Loader.java:1423)
    //       at org.bytedeco.javacpp.Loader.load(Loader.java:1234)
    //       at org.bytedeco.javacpp.Loader.load(Loader.java:1210)
    //       at org.bytedeco.javacpp.Loader.<clinit>(Loader.java:1973)
    //       at org.bytedeco.javacpp.Pointer.<clinit>(Pointer.java:521)
    //       at java.base/jdk.internal.misc.Unsafe.ensureClassInitialized0(Native Method)
    //       at java.base/jdk.internal.misc.Unsafe.ensureClassInitialized(Unsafe.java:1160)
    //   See https://diff.blue/R081 to resolve this issue.

    // Arrange
    // TODO: Populate arranged inputs
    HOGDescriptor hog = null;
    HogSvmDetectorConfig c = null;

    // Act
    HogSvmDetector actualHogSvmDetector = new HogSvmDetector(hog, c);
    HogSvmDetectorConfig actualConfig = actualHogSvmDetector.getConfig();
    HOGDescriptor actualHog = actualHogSvmDetector.getHog();

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link HogSvmDetector#detect(Mat)}.
   *
   * <p>Method under test: {@link HogSvmDetector#detect(Mat)}
   */
  @Test
  @DisplayName("Test detect(Mat)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"DetectionResult HogSvmDetector.detect(Mat)"})
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
    HogSvmDetector hogSvmDetector = null;
    Mat frame = null;

    // Act
    DetectionResult actualDetectResult = hogSvmDetector.detect(frame);

    // Assert
    // TODO: Add assertions on result
  }
}
