package knu.app.bll.preprocessors;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class FrameSizerPreprocessorDiffblueTest {

  /**
   * Test {@link FrameSizerPreprocessor#FrameSizerPreprocessor(int, int)}.
   *
   * <p>Method under test: {@link FrameSizerPreprocessor#FrameSizerPreprocessor(int, int)}
   */
  @Test
  @DisplayName("Test new FrameSizerPreprocessor(int, int)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FrameSizerPreprocessor.<init>(int, int)"})
  void testNewFrameSizerPreprocessor() {
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
    int width = 0;
    int height = 0;

    // Act
    FrameSizerPreprocessor actualFrameSizerPreprocessor = new FrameSizerPreprocessor(width, height);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link FrameSizerPreprocessor#process(Mat)}.
   *
   * <p>Method under test: {@link FrameSizerPreprocessor#process(Mat)}
   */
  @Test
  @DisplayName("Test process(Mat)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Mat FrameSizerPreprocessor.process(Mat)"})
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
    FrameSizerPreprocessor frameSizerPreprocessor = new FrameSizerPreprocessor(1, 1);

    // Act
    frameSizerPreprocessor.process(new Mat());

    // Assert
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link FrameSizerPreprocessor#setSize(Size)}
   *   <li>{@link FrameSizerPreprocessor#getSize()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
      "Size FrameSizerPreprocessor.getSize()",
      "void FrameSizerPreprocessor.setSize(Size)"
  })
  void testGettersAndSetters() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: Exception in arrange section.
    //   Diffblue Cover was unable to construct an instance of the class under test using
    //   knu.app.bll.preprocessors.FrameSizerPreprocessor.setSize(Size).
    //   The arrange section threw
    //   java.lang.NoClassDefFoundError: Could not initialize class
    // org.bytedeco.opencv.opencv_core.Size
    //       at
    // knu.app.bll.preprocessors.FrameSizerPreprocessor.<init>(FrameSizerPreprocessor.java:14)
    //   See https://diff.blue/R081 to resolve this issue.

    // Arrange
    // TODO: Populate arranged inputs
    FrameSizerPreprocessor frameSizerPreprocessor = null;
    Size size = null;

    // Act
    frameSizerPreprocessor.setSize(size);
    Size actualSize = frameSizerPreprocessor.getSize();

    // Assert
    // TODO: Add assertions on result
  }
}
