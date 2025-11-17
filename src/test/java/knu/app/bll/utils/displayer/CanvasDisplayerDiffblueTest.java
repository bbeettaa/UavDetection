package knu.app.bll.utils.displayer;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.Buffer;
import org.bytedeco.javacv.Frame;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class CanvasDisplayerDiffblueTest {

  /**
   * Test {@link CanvasDisplayer#CanvasDisplayer(String)}.
   *
   * <p>Method under test: {@link CanvasDisplayer#CanvasDisplayer(String)}
   */
  @Test
  @DisplayName("Test new CanvasDisplayer(String)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CanvasDisplayer.<init>(String)"})
  void testNewCanvasDisplayer() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.ArithmeticException: / by zero
    //       at org.bytedeco.javacv.Frame.<init>(Frame.java:128)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange and Act
    CanvasDisplayer actualCanvasDisplayer = new CanvasDisplayer("Dr");
    Frame frame = new Frame(1, 1, 2, 1);
    actualCanvasDisplayer.show(frame);

    // Assert
  }

  /**
   * Test {@link CanvasDisplayer#show(Frame)}.
   *
   * <p>Method under test: {@link CanvasDisplayer#show(Frame)}
   */
  @Test
  @DisplayName("Test show(Frame)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void CanvasDisplayer.show(Frame)"})
  void testShow() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.awt.HeadlessException
    //       at
    // java.desktop/sun.java2d.HeadlessGraphicsEnvironment.getDefaultScreenDevice(HeadlessGraphicsEnvironment.java:58)
    //       at org.bytedeco.javacv.CanvasFrame.getDefaultScreenDevice(CanvasFrame.java:117)
    //       at org.bytedeco.javacv.CanvasFrame.getDefaultGamma(CanvasFrame.java:91)
    //       at knu.app.bll.utils.displayer.CanvasDisplayer.<init>(CanvasDisplayer.java:13)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange
    CanvasDisplayer canvasDisplayer = new CanvasDisplayer("Dr");
    Frame frame = new Frame(1, 1, Frame.DEPTH_DOUBLE, 4);
    frame.image = new Buffer[]{null};

    // Act
    canvasDisplayer.show(frame);

    // Assert
  }
}
