package knu.app.bll.preprocessors;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.bytedeco.opencv.opencv_core.Mat;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class KMeansPreprocessorDiffblueTest {

  /**
   * Test {@link KMeansPreprocessor#KMeansPreprocessor(int)}.
   *
   * <p>Method under test: {@link KMeansPreprocessor#KMeansPreprocessor(int)}
   */
  @Test
  @DisplayName("Test new KMeansPreprocessor(int)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void KMeansPreprocessor.<init>(int)"})
  void testNewKMeansPreprocessor() {
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
    int k = 0;

    // Act
    KMeansPreprocessor actualKMeansPreprocessor = new KMeansPreprocessor(k);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link KMeansPreprocessor#process(Mat)}.
   *
   * <p>Method under test: {@link KMeansPreprocessor#process(Mat)}
   */
  @Test
  @DisplayName("Test process(Mat)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Mat KMeansPreprocessor.process(Mat)"})
  void testProcess() {
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
    KMeansPreprocessor kMeansPreprocessor = new KMeansPreprocessor(1);

    // Act
    kMeansPreprocessor.process(new Mat());

    // Assert
  }

  /**
   * Test {@link KMeansPreprocessor#setK(int)}.
   *
   * <p>Method under test: {@link KMeansPreprocessor#setK(int)}
   */
  @Test
  @DisplayName("Test setK(int)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void KMeansPreprocessor.setK(int)"})
  void testSetK() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: Exception in arrange section.
    //   Diffblue Cover was unable to construct an instance of the class under test using
    //   knu.app.bll.preprocessors.KMeansPreprocessor.setK(int).
    //   The arrange section threw
    //   java.lang.NoClassDefFoundError: Could not initialize class
    // org.bytedeco.opencv.opencv_core.Mat
    //       at knu.app.bll.preprocessors.KMeansPreprocessor.<init>(KMeansPreprocessor.java:12)
    //   See https://diff.blue/R081 to resolve this issue.

    // Arrange
    // TODO: Populate arranged inputs
    KMeansPreprocessor kMeansPreprocessor = null;
    int k = 0;

    // Act
    kMeansPreprocessor.setK(k);

    // Assert
    // TODO: Add assertions on result
  }
}
