package knu.app.bll.preprocessors;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.bytedeco.opencv.opencv_core.Mat;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class GrayColorPreprocessorDiffblueTest {

  /**
   * Test new {@link GrayColorPreprocessor} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link GrayColorPreprocessor}
   */
  @Test
  @DisplayName("Test new GrayColorPreprocessor (default constructor)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void GrayColorPreprocessor.<init>()"})
  void testNewGrayColorPreprocessor() {
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
    GrayColorPreprocessor actualGrayColorPreprocessor = new GrayColorPreprocessor();

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link GrayColorPreprocessor#process(Mat)}.
   *
   * <p>Method under test: {@link GrayColorPreprocessor#process(Mat)}
   */
  @Test
  @DisplayName("Test process(Mat)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Mat GrayColorPreprocessor.process(Mat)"})
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
    GrayColorPreprocessor grayColorPreprocessor = new GrayColorPreprocessor();

    // Act
    grayColorPreprocessor.process(new Mat());

    // Assert
  }
}
