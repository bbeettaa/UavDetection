package knu.app.bll.algorithms;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.bytedeco.opencv.opencv_core.Rect;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class IouComputerDiffblueTest {

  /**
   * Test {@link IouComputer#computeIoU(Rect, Rect)}.
   *
   * <p>Method under test: {@link IouComputer#computeIoU(Rect, Rect)}
   */
  @Test
  @DisplayName("Test computeIoU(Rect, Rect)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"double IouComputer.computeIoU(Rect, Rect)"})
  void testComputeIoU() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: JNI sandbox policy violation.
    //   Diffblue Cover ran code in your project that tried to load a JNI library.
    //   Library:
    // /home/bedu/.javacpp/cache/openblas-0.3.23-1.5.9-linux-x86_64.jar/org/bytedeco/openblas/linux-x86_64/libgcc_s.so.1
    //   The default sandboxing policy disallows this in order to prevent your code
    //   from damaging your system environment. If you are sure that the library is
    //   safe then use
    //   Diffblue Cover Settings > Sandboxed Environment > Allowed JNI prefixes
    //   to add a comma separated list of library prefixes to allow.
    //   See https://diff.blue/R012 to resolve this issue.

    // Arrange
    // TODO: Populate arranged inputs
    Rect a = null;
    Rect b = null;

    // Act
    double actualComputeIoUResult = IouComputer.computeIoU(a, b);

    // Assert
    // TODO: Add assertions on result
  }
}
