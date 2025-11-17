package knu.app.bll.utils.processors.hog;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.bytedeco.opencv.opencv_core.Mat;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class HogTrainerDiffblueTest {

  /**
   * Test {@link HogTrainer#loadAndPreparePositives(String)}.
   *
   * <p>Method under test: {@link HogTrainer#loadAndPreparePositives(String)}
   */
  @Test
  @DisplayName("Test loadAndPreparePositives(String)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List HogTrainer.loadAndPreparePositives(String)"})
  void testLoadAndPreparePositives() {
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
    new CpuHogTrainerFullSizedNegativeSample().loadAndPreparePositives("Dir Path");

    // Assert
  }

  /**
   * Test {@link HogTrainer#generateNegativePatches(String, int)}.
   *
   * <p>Method under test: {@link HogTrainer#generateNegativePatches(String, int)}
   */
  @Test
  @DisplayName("Test generateNegativePatches(String, int)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List HogTrainer.generateNegativePatches(String, int)"})
  void testGenerateNegativePatches() {
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
    new CpuHogTrainerFullSizedNegativeSample().generateNegativePatches("Dir Path", 1);

    // Assert
  }

  /**
   * Test {@link HogTrainer#preprocessImage(Mat)}.
   *
   * <p>Method under test: {@link HogTrainer#preprocessImage(Mat)}
   */
  @Test
  @DisplayName("Test preprocessImage(Mat)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Mat HogTrainer.preprocessImage(Mat)"})
  void testPreprocessImage() {
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
    CpuHogTrainerFullSizedNegativeSample cpuHogTrainerFullSizedNegativeSample =
        new CpuHogTrainerFullSizedNegativeSample();

    // Act
    cpuHogTrainerFullSizedNegativeSample.preprocessImage(new Mat());

    // Assert
  }

  /**
   * Test {@link HogTrainer#prepareLabels(int, int)}.
   *
   * <p>Method under test: {@link HogTrainer#prepareLabels(int, int)}
   */
  @Test
  @DisplayName("Test prepareLabels(int, int)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Mat HogTrainer.prepareLabels(int, int)"})
  void testPrepareLabels() {
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
    new CpuHogTrainerFullSizedNegativeSample().prepareLabels(3, 3);

    // Assert
  }

  /**
   * Test {@link HogTrainer#trainSVM(Mat, Mat)}.
   *
   * <p>Method under test: {@link HogTrainer#trainSVM(Mat, Mat)}
   */
  @Test
  @DisplayName("Test trainSVM(Mat, Mat)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Mat HogTrainer.trainSVM(Mat, Mat)"})
  void testTrainSVM() {
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
    CpuHogTrainerFullSizedNegativeSample cpuHogTrainerFullSizedNegativeSample =
        new CpuHogTrainerFullSizedNegativeSample();
    Mat samples = new Mat();

    // Act
    cpuHogTrainerFullSizedNegativeSample.trainSVM(samples, new Mat());

    // Assert
  }

  /**
   * Test {@link HogTrainer#releaseMats(List)}.
   *
   * <p>Method under test: {@link HogTrainer#releaseMats(List)}
   */
  @Test
  @DisplayName("Test releaseMats(List)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void HogTrainer.releaseMats(List)"})
  void testReleaseMats() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    // TODO: Populate arranged inputs
    HogTrainer hogTrainer = null;
    List<Mat> mats = null;

    // Act
    hogTrainer.releaseMats(mats);

    // Assert
    // TODO: Add assertions on result
  }
}
