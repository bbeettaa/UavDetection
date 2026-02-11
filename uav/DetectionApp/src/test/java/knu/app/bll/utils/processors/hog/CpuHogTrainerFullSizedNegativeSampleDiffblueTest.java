package knu.app.bll.utils.processors.hog;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.bytedeco.opencv.opencv_objdetect.HOGDescriptor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CpuHogTrainerFullSizedNegativeSampleDiffblueTest {

  /**
   * Test
   * {@link CpuHogTrainerFullSizedNegativeSample#CpuHogTrainerFullSizedNegativeSample(HOGDescriptor,
   * int)}.
   *
   * <p>Method under test: {@link
   * CpuHogTrainerFullSizedNegativeSample#CpuHogTrainerFullSizedNegativeSample(HOGDescriptor, int)}
   */
  @Test
  @DisplayName("Test new CpuHogTrainerFullSizedNegativeSample(HOGDescriptor, int)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CpuHogTrainerFullSizedNegativeSample.<init>(HOGDescriptor, int)"})
  void testNewCpuHogTrainerFullSizedNegativeSample() {
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
    HOGDescriptor hog = null;
    int negativePatchesStep = 0;

    // Act
    CpuHogTrainerFullSizedNegativeSample actualCpuHogTrainerFullSizedNegativeSample =
        new CpuHogTrainerFullSizedNegativeSample(hog, negativePatchesStep);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link CpuHogTrainerFullSizedNegativeSample#CpuHogTrainerFullSizedNegativeSample()}.
   *
   * <p>Method under test: {@link
   * CpuHogTrainerFullSizedNegativeSample#CpuHogTrainerFullSizedNegativeSample()}
   */
  @Test
  @DisplayName("Test new CpuHogTrainerFullSizedNegativeSample()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CpuHogTrainerFullSizedNegativeSample.<init>()"})
  void testNewCpuHogTrainerFullSizedNegativeSample2() {
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
    CpuHogTrainerFullSizedNegativeSample actualCpuHogTrainerFullSizedNegativeSample =
        new CpuHogTrainerFullSizedNegativeSample();

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link CpuHogTrainerFullSizedNegativeSample#train(String, String)}.
   *
   * <p>Method under test: {@link CpuHogTrainerFullSizedNegativeSample#train(String, String)}
   */
  @Test
  @DisplayName("Test train(String, String)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"HOGDescriptor CpuHogTrainerFullSizedNegativeSample.train(String, String)"})
  void testTrain() {
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
    new CpuHogTrainerFullSizedNegativeSample().train("Pos Dir", "Neg Dir");

    // Assert
  }
}
