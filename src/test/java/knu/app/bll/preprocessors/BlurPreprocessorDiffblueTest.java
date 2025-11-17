package knu.app.bll.preprocessors;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.bytedeco.opencv.opencv_core.Mat;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BlurPreprocessorDiffblueTest {

  /**
   * Test {@link BlurPreprocessor#BlurPreprocessor()}.
   *
   * <p>Method under test: {@link BlurPreprocessor#BlurPreprocessor()}
   */
  @Test
  @DisplayName("Test new BlurPreprocessor()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void BlurPreprocessor.<init>()"})
  void testNewBlurPreprocessor() {
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
    BlurPreprocessor actualBlurPreprocessor = new BlurPreprocessor();

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link BlurPreprocessor#BlurPreprocessor(double)}.
   *
   * <p>Method under test: {@link BlurPreprocessor#BlurPreprocessor(double)}
   */
  @Test
  @DisplayName("Test new BlurPreprocessor(double)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void BlurPreprocessor.<init>(double)"})
  void testNewBlurPreprocessor2() {
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
    double d = 0.0d;

    // Act
    BlurPreprocessor actualBlurPreprocessor = new BlurPreprocessor(d);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link BlurPreprocessor#process(Mat)}.
   *
   * <p>Method under test: {@link BlurPreprocessor#process(Mat)}
   */
  @Test
  @DisplayName("Test process(Mat)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Mat BlurPreprocessor.process(Mat)"})
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
    BlurPreprocessor blurPreprocessor = new BlurPreprocessor();

    // Act
    blurPreprocessor.process(new Mat());

    // Assert
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link BlurPreprocessor#setD(double)}
   *   <li>{@link BlurPreprocessor#setKernel(int)}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void BlurPreprocessor.setD(double)", "void BlurPreprocessor.setKernel(int)"})
  void testGettersAndSetters() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: Exception in arrange section.
    //   Diffblue Cover was unable to construct an instance of the class under test using
    //   knu.app.bll.preprocessors.BlurPreprocessor.setD(double).
    //   The arrange section threw
    //   java.lang.NoClassDefFoundError: Could not initialize class
    // org.bytedeco.opencv.opencv_core.Mat
    //       at knu.app.bll.preprocessors.BlurPreprocessor.<init>(BlurPreprocessor.java:15)
    //       at knu.app.bll.preprocessors.BlurPreprocessor.<init>(BlurPreprocessor.java:21)
    //   See https://diff.blue/R081 to resolve this issue.

    // Arrange
    // TODO: Populate arranged inputs
    BlurPreprocessor blurPreprocessor = null;
    double d = 0.0d;

    // Act
    blurPreprocessor.setD(d);
    int kernel = 0;
    blurPreprocessor.setKernel(kernel);

    // Assert
    // TODO: Add assertions on result
  }
}
