package knu.app.bll.utils.processors.hog;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.bytedeco.opencv.opencv_objdetect.HOGDescriptor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class HogSvmUtilsDiffblueTest {

  /**
   * Test.
   *
   * <p>Method under test: {@link HogSvmUtils#createHog()}
   */
  @Test
  @DisplayName("Test")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
      "HOGDescriptor HogSvmUtils.createHog()",
      "HOGDescriptor HogSvmUtils.createHog(org.bytedeco.opencv.opencv_core.Size, org.bytedeco.opencv.opencv_core.Size, org.bytedeco.opencv.opencv_core.Size, org.bytedeco.opencv.opencv_core.Size, int)",
      "HOGDescriptor HogSvmUtils.loadDescriptorFromFile(java.lang.String)",
      "void HogSvmUtils.saveDescriptorToFile(java.lang.String, HOGDescriptor)"
  })
  void test() {
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
    HOGDescriptor actualCreateHogResult = HogSvmUtils.createHog();

    // Assert
    // TODO: Add assertions on result
  }
}
