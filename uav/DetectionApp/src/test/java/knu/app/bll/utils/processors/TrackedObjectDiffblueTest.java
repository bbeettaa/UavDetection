package knu.app.bll.utils.processors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import knu.app.bll.processors.tracker.single.ObjectTracker;
import knu.app.bll.utils.processors.TrackedObject.TrackState;
import org.bytedeco.opencv.opencv_core.Rect;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class TrackedObjectDiffblueTest {

  /**
   * Test {@link TrackedObject#TrackedObject(Rect)}.
   *
   * <p>Method under test: {@link TrackedObject#TrackedObject(Rect)}
   */
  @Test
  @DisplayName("Test new TrackedObject(Rect)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TrackedObject.<init>(Rect)"})
  void testNewTrackedObject() {
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
    Rect rect = null;

    // Act
    TrackedObject actualTrackedObject = new TrackedObject(rect);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link TrackedObject#TrackedObject(Rect, float, ObjectTracker)}.
   *
   * <p>Method under test: {@link TrackedObject#TrackedObject(Rect, float, ObjectTracker)}
   */
  @Test
  @DisplayName("Test new TrackedObject(Rect, float, ObjectTracker)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TrackedObject.<init>(Rect, float, ObjectTracker)"})
  void testNewTrackedObject2() {
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
    Rect rect = null;
    float score = 0.0f;
    ObjectTracker tracker = null;

    // Act
    TrackedObject actualTrackedObject = new TrackedObject(rect, score, tracker);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link TrackedObject#increaseHints()}.
   *
   * <p>Method under test: {@link TrackedObject#increaseHints()}
   */
  @Test
  @DisplayName("Test increaseHints()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TrackedObject.increaseHints()"})
  void testIncreaseHints() {
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
    TrackedObject trackedObject = null;

    // Act
    trackedObject.increaseHints();

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link TrackedObject#increaseMissed()}.
   *
   * <p>Method under test: {@link TrackedObject#increaseMissed()}
   */
  @Test
  @DisplayName("Test increaseMissed()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void TrackedObject.increaseMissed()"})
  void testIncreaseMissed() {
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
    TrackedObject trackedObject = null;

    // Act
    trackedObject.increaseMissed();

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link TrackedObject#isDeleted()}.
   *
   * <p>Method under test: {@link TrackedObject#isDeleted()}
   */
  @Test
  @DisplayName("Test isDeleted()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TrackedObject.isDeleted()"})
  void testIsDeleted() {
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
    TrackedObject trackedObject = null;

    // Act
    boolean actualIsDeletedResult = trackedObject.isDeleted();

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link TrackedObject#isConfirmed()}.
   *
   * <p>Method under test: {@link TrackedObject#isConfirmed()}
   */
  @Test
  @DisplayName("Test isConfirmed()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TrackedObject.isConfirmed()"})
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
    TrackedObject trackedObject = null;

    // Act
    boolean actualIsConfirmedResult = trackedObject.isConfirmed();

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link TrackedObject#isTentative()}.
   *
   * <p>Method under test: {@link TrackedObject#isTentative()}
   */
  @Test
  @DisplayName("Test isTentative()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TrackedObject.isTentative()"})
  void testIsTentative() {
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
    TrackedObject trackedObject = null;

    // Act
    boolean actualIsTentativeResult = trackedObject.isTentative();

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link TrackedObject#setId(int)}
   *   <li>{@link TrackedObject#setRect(Rect)}
   *   <li>{@link TrackedObject#setScore(float)}
   *   <li>{@link TrackedObject#setState(TrackState)}
   *   <li>{@link TrackedObject#getHitHistory()}
   *   <li>{@link TrackedObject#getHits()}
   *   <li>{@link TrackedObject#getId()}
   *   <li>{@link TrackedObject#getMissed()}
   *   <li>{@link TrackedObject#getRect()}
   *   <li>{@link TrackedObject#getScore()}
   *   <li>{@link TrackedObject#getState()}
   *   <li>{@link TrackedObject#getTracker()}
   *   <li>{@link TrackedObject#resetMissed()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
      "List TrackedObject.getHitHistory()",
      "int TrackedObject.getHits()",
      "int TrackedObject.getId()",
      "int TrackedObject.getMissed()",
      "Rect TrackedObject.getRect()",
      "float TrackedObject.getScore()",
      "TrackState TrackedObject.getState()",
      "ObjectTracker TrackedObject.getTracker()",
      "void TrackedObject.resetMissed()",
      "void TrackedObject.setId(int)",
      "void TrackedObject.setRect(Rect)",
      "void TrackedObject.setScore(float)",
      "void TrackedObject.setState(TrackState)"
  })
  void testGettersAndSetters() {
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
    TrackedObject trackedObject = null;
    int id = 0;

    // Act
    trackedObject.setId(id);
    Rect rect = null;
    trackedObject.setRect(rect);
    float score = 0.0f;
    trackedObject.setScore(score);
    trackedObject.setState(TrackState.Tentative);
    List<Boolean> actualHitHistory = trackedObject.getHitHistory();
    int actualHits = trackedObject.getHits();
    int actualId = trackedObject.getId();
    int actualMissed = trackedObject.getMissed();
    Rect actualRect = trackedObject.getRect();
    float actualScore = trackedObject.getScore();
    TrackState actualState = trackedObject.getState();
    ObjectTracker actualTracker = trackedObject.getTracker();
    trackedObject.resetMissed();

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link TrackedObject#equals(Object)}, and {@link TrackedObject#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link TrackedObject#equals(Object)}
   *   <li>{@link TrackedObject#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is equal; then return equal")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TrackedObject.equals(Object)", "int TrackedObject.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
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
    TrackedObject trackedObject = null;
    TrackedObject trackedObject2 = null;

    // Act and Assert

    // TODO: Add assertions on result
    assertEquals(trackedObject, trackedObject2);
    TrackedObject trackedObject3 = null;
    assertEquals(trackedObject3.hashCode(), trackedObject.hashCode());
  }

  /**
   * Test {@link TrackedObject#equals(Object)}, and {@link TrackedObject#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link TrackedObject#equals(Object)}
   *   <li>{@link TrackedObject#hashCode()}
   * </ul>
   */
  @Test
  @DisplayName("Test equals(Object), and hashCode(); when other is same; then return equal")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TrackedObject.equals(Object)", "int TrackedObject.hashCode()"})
  void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
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
    TrackedObject trackedObject = null;

    // Act and Assert

    // TODO: Add assertions on result
    assertEquals(trackedObject, trackedObject);
    int actualHashCodeResult = trackedObject.hashCode();
    assertEquals(trackedObject.hashCode(), actualHashCodeResult);
  }

  /**
   * Test {@link TrackedObject#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link TrackedObject#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is different; then return not equal")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TrackedObject.equals(Object)", "int TrackedObject.hashCode()"})
  void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
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
    TrackedObject trackedObject = null;
    TrackedObject trackedObject2 = null;

    // Act and Assert

    // TODO: Add assertions on result
    assertNotEquals(trackedObject, trackedObject2);
  }

  /**
   * Test {@link TrackedObject#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link TrackedObject#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is 'null'; then return not equal")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TrackedObject.equals(Object)", "int TrackedObject.hashCode()"})
  void testEquals_whenOtherIsNull_thenReturnNotEqual() {
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
    TrackedObject trackedObject = null;
    Object object = null;

    // Act and Assert

    // TODO: Add assertions on result
    assertNotEquals(trackedObject, object);
  }

  /**
   * Test {@link TrackedObject#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link TrackedObject#equals(Object)}
   */
  @Test
  @DisplayName("Test equals(Object); when other is wrong type; then return not equal")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TrackedObject.equals(Object)", "int TrackedObject.hashCode()"})
  void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
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
    TrackedObject trackedObject = null;
    String string = "Different type to TrackedObject";

    // Act and Assert

    // TODO: Add assertions on result
    assertNotEquals(trackedObject, string);
  }
}
