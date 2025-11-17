package knu.app.bll.confirmation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import knu.app.bll.utils.processors.TrackedObject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class NOutOfMConfirmationDiffblueTest {

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link NOutOfMConfirmation#NOutOfMConfirmation(int, int)}
   *   <li>{@link NOutOfMConfirmation#setM(int)}
   *   <li>{@link NOutOfMConfirmation#setN(int)}
   *   <li>{@link NOutOfMConfirmation#getM()}
   *   <li>{@link NOutOfMConfirmation#getN()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
      "void NOutOfMConfirmation.<init>(int, int)",
      "int NOutOfMConfirmation.getM()",
      "int NOutOfMConfirmation.getN()",
      "void NOutOfMConfirmation.setM(int)",
      "void NOutOfMConfirmation.setN(int)"
  })
  void testGettersAndSetters() {
    // Arrange and Act
    NOutOfMConfirmation actualNOutOfMConfirmation = new NOutOfMConfirmation(1, 1);
    actualNOutOfMConfirmation.setM(1);
    actualNOutOfMConfirmation.setN(1);
    int actualM = actualNOutOfMConfirmation.getM();

    // Assert
    assertEquals(1, actualM);
    assertEquals(1, actualNOutOfMConfirmation.getN());
  }

  /**
   * Test {@link NOutOfMConfirmation#isConfirmed(TrackedObject)}.
   *
   * <p>Method under test: {@link NOutOfMConfirmation#isConfirmed(TrackedObject)}
   */
  @Test
  @DisplayName("Test isConfirmed(TrackedObject)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NOutOfMConfirmation.isConfirmed(TrackedObject)"})
  void testIsConfirmed() {
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
    NOutOfMConfirmation nOutOfMConfirmation = null;
    TrackedObject candidate = null;

    // Act
    boolean actualIsConfirmedResult = nOutOfMConfirmation.isConfirmed(candidate);

    // Assert
    // TODO: Add assertions on result
  }
}
