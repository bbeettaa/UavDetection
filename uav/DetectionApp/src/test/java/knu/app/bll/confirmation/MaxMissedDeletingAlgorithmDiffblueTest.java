package knu.app.bll.confirmation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import knu.app.bll.utils.processors.TrackedObject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class MaxMissedDeletingAlgorithmDiffblueTest {

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link MaxMissedDeletingAlgorithm#MaxMissedDeletingAlgorithm(int)}
   *   <li>{@link MaxMissedDeletingAlgorithm#setMaxMissed(int)}
   *   <li>{@link MaxMissedDeletingAlgorithm#getMaxMissed()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
      "void MaxMissedDeletingAlgorithm.<init>(int)",
      "int MaxMissedDeletingAlgorithm.getMaxMissed()",
      "void MaxMissedDeletingAlgorithm.setMaxMissed(int)"
  })
  void testGettersAndSetters() {
    // Arrange and Act
    MaxMissedDeletingAlgorithm actualMaxMissedDeletingAlgorithm = new MaxMissedDeletingAlgorithm(3);
    actualMaxMissedDeletingAlgorithm.setMaxMissed(3);

    // Assert
    assertEquals(3, actualMaxMissedDeletingAlgorithm.getMaxMissed());
  }

  /**
   * Test {@link MaxMissedDeletingAlgorithm#isConfirmed(TrackedObject)}.
   *
   * <p>Method under test: {@link MaxMissedDeletingAlgorithm#isConfirmed(TrackedObject)}
   */
  @Test
  @DisplayName("Test isConfirmed(TrackedObject)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean MaxMissedDeletingAlgorithm.isConfirmed(TrackedObject)"})
  void testIsConfirmed() {
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
    MaxMissedDeletingAlgorithm maxMissedDeletingAlgorithm = null;
    TrackedObject candidate = null;

    // Act
    boolean actualIsConfirmedResult = maxMissedDeletingAlgorithm.isConfirmed(candidate);

    // Assert
    // TODO: Add assertions on result
  }
}
