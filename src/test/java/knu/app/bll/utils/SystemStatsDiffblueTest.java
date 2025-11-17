package knu.app.bll.utils;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class SystemStatsDiffblueTest {

  /**
   * Test new {@link SystemStats} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link SystemStats}
   */
  @Test
  @DisplayName("Test new SystemStats (default constructor)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void SystemStats.<init>()"})
  void testNewSystemStats() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: JNI sandbox policy violation.
    //   Diffblue Cover ran code in your project that tried to load a JNI library.
    //   Library: /home/bedu/.intellij-idea/lib/jna/amd64/libjnidispatch.so
    //   The default sandboxing policy disallows this in order to prevent your code
    //   from damaging your system environment. If you are sure that the library is
    //   safe then use
    //   Diffblue Cover Settings > Sandboxed Environment > Allowed JNI prefixes
    //   to add a comma separated list of library prefixes to allow.
    //   See https://diff.blue/R012 to resolve this issue.

    // Arrange and Act
    // TODO: Populate arranged inputs
    SystemStats actualSystemStats = new SystemStats();

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link SystemStats#getCpuLoadPercent()}.
   *
   * <p>Method under test: {@link SystemStats#getCpuLoadPercent()}
   */
  @Test
  @DisplayName("Test getCpuLoadPercent()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"double SystemStats.getCpuLoadPercent()"})
  void testGetCpuLoadPercent() {
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
    new SystemStats().getCpuLoadPercent();

    // Assert
  }

  /**
   * Test {@link SystemStats#getUsedMemoryMB()}.
   *
   * <p>Method under test: {@link SystemStats#getUsedMemoryMB()}
   */
  @Test
  @DisplayName("Test getUsedMemoryMB()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long SystemStats.getUsedMemoryMB()"})
  void testGetUsedMemoryMB() {
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
    new SystemStats().getUsedMemoryMB();

    // Assert
  }

  /**
   * Test {@link SystemStats#getTotalMemoryMB()}.
   *
   * <p>Method under test: {@link SystemStats#getTotalMemoryMB()}
   */
  @Test
  @DisplayName("Test getTotalMemoryMB()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long SystemStats.getTotalMemoryMB()"})
  void testGetTotalMemoryMB() {
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
    new SystemStats().getTotalMemoryMB();

    // Assert
  }
}
