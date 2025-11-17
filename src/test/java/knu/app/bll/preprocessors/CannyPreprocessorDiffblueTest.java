package knu.app.bll.preprocessors;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.bytedeco.opencv.opencv_core.Mat;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CannyPreprocessorDiffblueTest {

  /**
   * Test {@link CannyPreprocessor#CannyPreprocessor(int, int)}.
   *
   * <p>Method under test: {@link CannyPreprocessor#CannyPreprocessor(int, int)}
   */
  @Test
  @DisplayName("Test new CannyPreprocessor(int, int)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CannyPreprocessor.<init>(int, int)"})
  void testNewCannyPreprocessor() {
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
    int v = 0;
    int v1 = 0;

    // Act
    CannyPreprocessor actualCannyPreprocessor = new CannyPreprocessor(v, v1);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link CannyPreprocessor#CannyPreprocessor()}.
   *
   * <p>Method under test: {@link CannyPreprocessor#CannyPreprocessor()}
   */
  @Test
  @DisplayName("Test new CannyPreprocessor()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CannyPreprocessor.<init>()"})
  void testNewCannyPreprocessor2() {
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
    CannyPreprocessor actualCannyPreprocessor = new CannyPreprocessor();

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link CannyPreprocessor#process(Mat)}.
   *
   * <p>Method under test: {@link CannyPreprocessor#process(Mat)}
   */
  @Test
  @DisplayName("Test process(Mat)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Mat CannyPreprocessor.process(Mat)"})
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
    CannyPreprocessor cannyPreprocessor = new CannyPreprocessor();

    // Act
    cannyPreprocessor.process(new Mat());

    // Assert
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CannyPreprocessor#setV(int)}
   *   <li>{@link CannyPreprocessor#setV1(int)}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CannyPreprocessor.setV(int)", "void CannyPreprocessor.setV1(int)"})
  void testGettersAndSetters() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: Exception in arrange section.
    //   Diffblue Cover was unable to construct an instance of the class under test using
    //   knu.app.bll.preprocessors.CannyPreprocessor.setV1(int).
    //   The arrange section threw
    //   java.lang.NoClassDefFoundError: Could not initialize class
    // org.bytedeco.opencv.opencv_core.Mat
    //       at knu.app.bll.preprocessors.CannyPreprocessor.<init>(CannyPreprocessor.java:16)
    //       at knu.app.bll.preprocessors.CannyPreprocessor.<init>(CannyPreprocessor.java:23)
    //   See https://diff.blue/R081 to resolve this issue.

    // Arrange
    // TODO: Populate arranged inputs
    CannyPreprocessor cannyPreprocessor = null;
    int v = 0;

    // Act
    cannyPreprocessor.setV(v);
    int v1 = 0;
    cannyPreprocessor.setV1(v1);

    // Assert
    // TODO: Add assertions on result
  }
}
