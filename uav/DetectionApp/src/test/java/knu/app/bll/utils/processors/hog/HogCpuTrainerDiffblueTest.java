package knu.app.bll.utils.processors.hog;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.bytedeco.opencv.opencv_objdetect.HOGDescriptor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class HogCpuTrainerDiffblueTest {

  /**
   * Test {@link HogCpuTrainer#HogCpuTrainer(HOGDescriptor)}.
   *
   * <p>Method under test: {@link HogCpuTrainer#HogCpuTrainer(HOGDescriptor)}
   */
  @Test
  @DisplayName("Test new HogCpuTrainer(HOGDescriptor)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void HogCpuTrainer.<init>(HOGDescriptor)"})
  void testNewHogCpuTrainer() {
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

    // Act
    HogCpuTrainer actualHogCpuTrainer = new HogCpuTrainer(hog);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link HogCpuTrainer#HogCpuTrainer()}.
   *
   * <p>Method under test: {@link HogCpuTrainer#HogCpuTrainer()}
   */
  @Test
  @DisplayName("Test new HogCpuTrainer()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void HogCpuTrainer.<init>()"})
  void testNewHogCpuTrainer2() {
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
    HogCpuTrainer actualHogCpuTrainer = new HogCpuTrainer();

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link HogCpuTrainer#train(String, String)}.
   *
   * <p>Method under test: {@link HogCpuTrainer#train(String, String)}
   */
  @Test
  @DisplayName("Test train(String, String)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"HOGDescriptor HogCpuTrainer.train(String, String)"})
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
    new HogCpuTrainer().train("Pos Dir", "Neg Dir");

    // Assert
  }
}
