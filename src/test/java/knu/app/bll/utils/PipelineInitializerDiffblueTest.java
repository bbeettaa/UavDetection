package knu.app.bll.utils;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import knu.app.bll.utils.processors.hog.HogSvmDetectorConfig;
import knu.app.ui.modules.UIModule;
import org.bytedeco.opencv.opencv_core.Mat;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class PipelineInitializerDiffblueTest {

  /**
   * Test {@link PipelineInitializer#PipelineInitializer(int, Mat, String, HogSvmDetectorConfig)}.
   *
   * <p>Method under test: {@link PipelineInitializer#PipelineInitializer(int, Mat, String,
   * HogSvmDetectorConfig)}
   */
  @Test
  @DisplayName("Test new PipelineInitializer(int, Mat, String, HogSvmDetectorConfig)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PipelineInitializer.<init>(int, Mat, String, HogSvmDetectorConfig)"})
  void testNewPipelineInitializer() {
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
    int bufferCapacity = 0;
    Mat singleDescriptor = null;
    String hogDescriptorFile = "";
    HogSvmDetectorConfig hogSvmDetectorConfig = null;

    // Act
    PipelineInitializer actualPipelineInitializer =
        new PipelineInitializer(
            bufferCapacity, singleDescriptor, hogDescriptorFile, hogSvmDetectorConfig);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link PipelineInitializer#getUiModules()}.
   *
   * <p>Method under test: {@link PipelineInitializer#getUiModules()}
   */
  @Test
  @DisplayName("Test getUiModules()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"List PipelineInitializer.getUiModules()"})
  void testGetUiModules() {
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
    PipelineInitializer pipelineInitializer = null;

    // Act
    List<UIModule<?>> actualUiModules = pipelineInitializer.getUiModules();

    // Assert
    // TODO: Add assertions on result
  }
}
