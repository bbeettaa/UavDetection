package knu.app.bll.utils;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import knu.app.ui.DockSpaceUIModule;
import knu.app.ui.modules.UIModule;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VideoProcessingUIDiffblueTest {

  @Mock
  private List<UIModule<?>> list;

  @InjectMocks
  private VideoProcessingUI videoProcessingUI;

  /**
   * Test {@link VideoProcessingUI#VideoProcessingUI(List)}.
   *
   * <p>Method under test: {@link VideoProcessingUI#VideoProcessingUI(List)}
   */
  @Test
  @DisplayName("Test new VideoProcessingUI(List)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void VideoProcessingUI.<init>(List)"})
  void testNewVideoProcessingUI() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: Missing observers.
    //   Diffblue Cover was unable to create an assertion.
    //   Add getters for the following fields or make them package-private:
    //   VideoProcessingUI.height
    //   VideoProcessingUI.imGuiGl3
    //   VideoProcessingUI.imGuiGlfw
    //   VideoProcessingUI.uiModules
    //   VideoProcessingUI.width
    //   VideoProcessingUI.window

    // Arrange
    ArrayList<UIModule<?>> uiModules = new ArrayList<>();
    DockSpaceUIModule dockSpaceUIModule = new DockSpaceUIModule("42", "42", "42");
    uiModules.add(dockSpaceUIModule);
    DockSpaceUIModule dockSpaceUIModule2 = new DockSpaceUIModule("42", "42", "42");
    uiModules.add(dockSpaceUIModule2);

    // Act
    new VideoProcessingUI(uiModules);

    // Assert
  }

  /**
   * Test {@link VideoProcessingUI#run()}.
   *
   * <p>Method under test: {@link VideoProcessingUI#run()}
   */
  @Test
  @DisplayName("Test run()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void VideoProcessingUI.run()"})
  void testRun() {
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
    VideoProcessingUI videoProcessingUI = null;

    // Act
    videoProcessingUI.run();

    // Assert
    // TODO: Add assertions on result
  }
}
