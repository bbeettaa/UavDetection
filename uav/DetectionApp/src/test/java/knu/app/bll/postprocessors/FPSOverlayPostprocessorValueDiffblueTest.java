package knu.app.bll.postprocessors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.bytedeco.opencv.opencv_core.Mat;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class FPSOverlayPostprocessorValueDiffblueTest {

  /**
   * Test {@link FPSOverlayPostprocessorValue#setValue(Long)} with {@code val}.
   *
   * <p>Method under test: {@link FPSOverlayPostprocessorValue#setValue(Long)}
   */
  @Test
  @DisplayName("Test setValue(Long) with 'val'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FPSOverlayPostprocessorValue.setValue(Long)"})
  void testSetValueWithVal() {
    // Arrange
    FPSOverlayPostprocessorValue fpsOverlayPostprocessorValue = new FPSOverlayPostprocessorValue();

    // Act
    fpsOverlayPostprocessorValue.setValue(42L);

    // Assert
    assertEquals(42L, fpsOverlayPostprocessorValue.currentTime);
  }

  /**
   * Test {@link FPSOverlayPostprocessorValue#process(Mat)}.
   *
   * <p>Method under test: {@link FPSOverlayPostprocessorValue#process(Mat)}
   */
  @Test
  @DisplayName("Test process(Mat)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Mat FPSOverlayPostprocessorValue.process(Mat)"})
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
    // TODO: Populate arranged inputs
    FPSOverlayPostprocessorValue fpsOverlayPostprocessorValue = null;
    Mat input = null;

    // Act
    Mat actualProcessResult = fpsOverlayPostprocessorValue.process(input);

    // Assert
    // TODO: Add assertions on result
  }
}
