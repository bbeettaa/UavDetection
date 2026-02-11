package knu.app.bll.utils.processors.hog;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.bytedeco.opencv.opencv_core.Size;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class HogSvmDetectorConfigDiffblueTest {

  /**
   * Test
   * {@link HogSvmDetectorConfig#HogSvmDetectorConfig(double, double, double, Size, Size, boolean,
   * double, float, float, int, double)}.
   *
   * <p>Method under test: {@link HogSvmDetectorConfig#HogSvmDetectorConfig(double, double, double,
   * Size, Size, boolean, double, float, float, int, double)}
   */
  @Test
  @DisplayName(
      "Test new HogSvmDetectorConfig(double, double, double, Size, Size, boolean, double, float, float, int, double)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
      "void HogSvmDetectorConfig.<init>(double, double, double, Size, Size, boolean, double, float, float, int, double)"
  })
  void testNewHogSvmDetectorConfig() {
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
    double hitThreshold = 0.0d;
    double finalThreshold = 0.0d;
    double scale = 0.0d;
    Size winStride = null;
    Size padding = null;
    boolean useMeanShiftGrouping = false;
    double weightThreshold = 0.0d;
    float scoreThreshold = 0.0f;
    float nmsThreshold = 0.0f;
    int groupThreshold = 0;
    double eps = 0.0d;

    // Act
    HogSvmDetectorConfig actualHogSvmDetectorConfig =
        new HogSvmDetectorConfig(
            hitThreshold,
            finalThreshold,
            scale,
            winStride,
            padding,
            useMeanShiftGrouping,
            weightThreshold,
            scoreThreshold,
            nmsThreshold,
            groupThreshold,
            eps);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link HogSvmDetectorConfig#withDefaultConfig()}.
   *
   * <p>Method under test: {@link HogSvmDetectorConfig#withDefaultConfig()}
   */
  @Test
  @DisplayName("Test withDefaultConfig()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"HogSvmDetectorConfig HogSvmDetectorConfig.withDefaultConfig()"})
  void testWithDefaultConfig() {
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
    HogSvmDetectorConfig actualWithDefaultConfigResult = HogSvmDetectorConfig.withDefaultConfig();

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link HogSvmDetectorConfig#withTestConfig()}.
   *
   * <p>Method under test: {@link HogSvmDetectorConfig#withTestConfig()}
   */
  @Test
  @DisplayName("Test withTestConfig()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"HogSvmDetectorConfig HogSvmDetectorConfig.withTestConfig()"})
  void testWithTestConfig() {
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
    HogSvmDetectorConfig actualWithTestConfigResult = HogSvmDetectorConfig.withTestConfig();

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link HogSvmDetectorConfig#withTestConfig1()}.
   *
   * <p>Method under test: {@link HogSvmDetectorConfig#withTestConfig1()}
   */
  @Test
  @DisplayName("Test withTestConfig1()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"HogSvmDetectorConfig HogSvmDetectorConfig.withTestConfig1()"})
  void testWithTestConfig1() {
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
    HogSvmDetectorConfig actualWithTestConfig1Result = HogSvmDetectorConfig.withTestConfig1();

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link HogSvmDetectorConfig#setEps(double)}
   *   <li>{@link HogSvmDetectorConfig#setFinalThreshold(double)}
   *   <li>{@link HogSvmDetectorConfig#setGroupThreshold(int)}
   *   <li>{@link HogSvmDetectorConfig#setHitThreshold(double)}
   *   <li>{@link HogSvmDetectorConfig#setNmsThreshold(float)}
   *   <li>{@link HogSvmDetectorConfig#setPadding(Size)}
   *   <li>{@link HogSvmDetectorConfig#setScale(double)}
   *   <li>{@link HogSvmDetectorConfig#setScoreThreshold(float)}
   *   <li>{@link HogSvmDetectorConfig#setUseMeanShiftGrouping(boolean)}
   *   <li>{@link HogSvmDetectorConfig#setWeightThreshold(double)}
   *   <li>{@link HogSvmDetectorConfig#setWinStride(Size)}
   *   <li>{@link HogSvmDetectorConfig#getEps()}
   *   <li>{@link HogSvmDetectorConfig#getFinalThreshold()}
   *   <li>{@link HogSvmDetectorConfig#getGroupThreshold()}
   *   <li>{@link HogSvmDetectorConfig#getHitThreshold()}
   *   <li>{@link HogSvmDetectorConfig#getNmsThreshold()}
   *   <li>{@link HogSvmDetectorConfig#getPadding()}
   *   <li>{@link HogSvmDetectorConfig#getScale()}
   *   <li>{@link HogSvmDetectorConfig#getScoreThreshold()}
   *   <li>{@link HogSvmDetectorConfig#getWeightThreshold()}
   *   <li>{@link HogSvmDetectorConfig#getWinStride()}
   *   <li>{@link HogSvmDetectorConfig#isUseMeanShiftGrouping()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
      "double HogSvmDetectorConfig.getEps()",
      "double HogSvmDetectorConfig.getFinalThreshold()",
      "int HogSvmDetectorConfig.getGroupThreshold()",
      "double HogSvmDetectorConfig.getHitThreshold()",
      "float HogSvmDetectorConfig.getNmsThreshold()",
      "Size HogSvmDetectorConfig.getPadding()",
      "double HogSvmDetectorConfig.getScale()",
      "float HogSvmDetectorConfig.getScoreThreshold()",
      "double HogSvmDetectorConfig.getWeightThreshold()",
      "Size HogSvmDetectorConfig.getWinStride()",
      "boolean HogSvmDetectorConfig.isUseMeanShiftGrouping()",
      "void HogSvmDetectorConfig.setEps(double)",
      "void HogSvmDetectorConfig.setFinalThreshold(double)",
      "void HogSvmDetectorConfig.setGroupThreshold(int)",
      "void HogSvmDetectorConfig.setHitThreshold(double)",
      "void HogSvmDetectorConfig.setNmsThreshold(float)",
      "void HogSvmDetectorConfig.setPadding(Size)",
      "void HogSvmDetectorConfig.setScale(double)",
      "void HogSvmDetectorConfig.setScoreThreshold(float)",
      "void HogSvmDetectorConfig.setUseMeanShiftGrouping(boolean)",
      "void HogSvmDetectorConfig.setWeightThreshold(double)",
      "void HogSvmDetectorConfig.setWinStride(Size)"
  })
  void testGettersAndSetters() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: Exception in arrange section.
    //   Diffblue Cover was unable to construct an instance of the class under test using
    //   knu.app.bll.utils.processors.hog.HogSvmDetectorConfig.setEps(double).
    //   The arrange section threw
    //   java.lang.NoClassDefFoundError: Could not initialize class
    // org.bytedeco.opencv.opencv_core.Size
    //       at
    // knu.app.bll.utils.processors.hog.HogSvmDetectorConfig.withDefaultConfig(HogSvmDetectorConfig.java:35)
    //   See https://diff.blue/R081 to resolve this issue.

    // Arrange
    // TODO: Populate arranged inputs
    HogSvmDetectorConfig hogSvmDetectorConfig = null;
    double eps = 0.0d;

    // Act
    hogSvmDetectorConfig.setEps(eps);
    double finalThreshold = 0.0d;
    hogSvmDetectorConfig.setFinalThreshold(finalThreshold);
    int groupThreshold = 0;
    hogSvmDetectorConfig.setGroupThreshold(groupThreshold);
    double hitThreshold = 0.0d;
    hogSvmDetectorConfig.setHitThreshold(hitThreshold);
    float nmsThreshold = 0.0f;
    hogSvmDetectorConfig.setNmsThreshold(nmsThreshold);
    Size padding = null;
    hogSvmDetectorConfig.setPadding(padding);
    double scale = 0.0d;
    hogSvmDetectorConfig.setScale(scale);
    float scoreThreshold = 0.0f;
    hogSvmDetectorConfig.setScoreThreshold(scoreThreshold);
    boolean useMeanShiftGrouping = false;
    hogSvmDetectorConfig.setUseMeanShiftGrouping(useMeanShiftGrouping);
    double weightThreshold = 0.0d;
    hogSvmDetectorConfig.setWeightThreshold(weightThreshold);
    Size winStride = null;
    hogSvmDetectorConfig.setWinStride(winStride);
    double actualEps = hogSvmDetectorConfig.getEps();
    double actualFinalThreshold = hogSvmDetectorConfig.getFinalThreshold();
    int actualGroupThreshold = hogSvmDetectorConfig.getGroupThreshold();
    double actualHitThreshold = hogSvmDetectorConfig.getHitThreshold();
    float actualNmsThreshold = hogSvmDetectorConfig.getNmsThreshold();
    Size actualPadding = hogSvmDetectorConfig.getPadding();
    double actualScale = hogSvmDetectorConfig.getScale();
    float actualScoreThreshold = hogSvmDetectorConfig.getScoreThreshold();
    double actualWeightThreshold = hogSvmDetectorConfig.getWeightThreshold();
    Size actualWinStride = hogSvmDetectorConfig.getWinStride();
    boolean actualIsUseMeanShiftGroupingResult = hogSvmDetectorConfig.isUseMeanShiftGrouping();

    // Assert
    // TODO: Add assertions on result
  }
}
