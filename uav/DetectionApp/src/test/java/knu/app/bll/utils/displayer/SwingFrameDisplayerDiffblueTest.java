package knu.app.bll.utils.displayer;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.Buffer;
import org.bytedeco.javacv.Frame;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SwingFrameDisplayerDiffblueTest {

  /**
   * Test new {@link SwingFrameDisplayer} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link SwingFrameDisplayer}
   */
  @Test
  @DisplayName("Test new SwingFrameDisplayer (default constructor)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SwingFrameDisplayer.<init>()"})
  void testNewSwingFrameDisplayer() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.ArithmeticException: / by zero
    //       at org.bytedeco.javacv.Frame.<init>(Frame.java:128)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange and Act
    SwingFrameDisplayer actualSwingFrameDisplayer = new SwingFrameDisplayer();
    Frame frame = new Frame(1, 1, 2, 1);
    actualSwingFrameDisplayer.show(frame);

    // Assert
  }

  /**
   * Test {@link SwingFrameDisplayer#show(Frame)}.
   *
   * <p>Method under test: {@link SwingFrameDisplayer#show(Frame)}
   */
  @Test
  @DisplayName("Test show(Frame)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SwingFrameDisplayer.show(Frame)"})
  void testShow() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.awt.HeadlessException
    //       at
    // java.desktop/java.awt.GraphicsEnvironment.checkHeadless(GraphicsEnvironment.java:164)
    //       at java.desktop/java.awt.Window.<init>(Window.java:553)
    //       at java.desktop/java.awt.Frame.<init>(Frame.java:428)
    //       at java.desktop/javax.swing.JFrame.<init>(JFrame.java:224)
    //       at knu.app.bll.utils.displayer.SwingFrameDisplayer.<init>(SwingFrameDisplayer.java:17)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange
    SwingFrameDisplayer swingFrameDisplayer = new SwingFrameDisplayer();
    Frame frame = new Frame(1, 1, Frame.DEPTH_DOUBLE, 4);
    frame.image = new Buffer[]{null};

    // Act
    swingFrameDisplayer.show(frame);

    // Assert
  }
}
