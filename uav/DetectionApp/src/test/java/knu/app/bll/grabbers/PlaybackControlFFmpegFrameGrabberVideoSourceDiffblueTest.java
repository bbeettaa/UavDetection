package knu.app.bll.grabbers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import knu.app.bll.events.EventModelListener;
import org.bytedeco.javacv.Frame;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class PlaybackControlFFmpegFrameGrabberVideoSourceDiffblueTest {

  /**
   * Test {@link PlaybackControlFFmpegFrameGrabberVideoSource#play()}.
   *
   * <p>Method under test: {@link PlaybackControlFFmpegFrameGrabberVideoSource#play()}
   */
  @Test
  @DisplayName("Test play()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PlaybackControlFFmpegFrameGrabberVideoSource.play()"})
  void testPlay() {
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
    PlaybackControlFFmpegFrameGrabberVideoSource playbackControlFFmpegFrameGrabberVideoSource =
        null;

    // Act
    playbackControlFFmpegFrameGrabberVideoSource.play();

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link PlaybackControlFFmpegFrameGrabberVideoSource#start()}.
   *
   * <p>Method under test: {@link PlaybackControlFFmpegFrameGrabberVideoSource#start()}
   */
  @Test
  @DisplayName("Test start()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PlaybackControlFFmpegFrameGrabberVideoSource.start()"})
  void testStart() {
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
    PlaybackControlFFmpegFrameGrabberVideoSource playbackControlFFmpegFrameGrabberVideoSource =
        null;

    // Act
    playbackControlFFmpegFrameGrabberVideoSource.start();

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link PlaybackControlFFmpegFrameGrabberVideoSource#stop()}.
   *
   * <ul>
   *   <li>Given {@link EventModelListener} {@link EventModelListener#onEvent()} does nothing.
   *   <li>Then calls {@link EventModelListener#onEvent()}.
   * </ul>
   *
   * <p>Method under test: {@link PlaybackControlFFmpegFrameGrabberVideoSource#stop()}
   */
  @Test
  @DisplayName("Test stop(); given EventModelListener onEvent() does nothing; then calls onEvent()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PlaybackControlFFmpegFrameGrabberVideoSource.stop()"})
  void testStop_givenEventModelListenerOnEventDoesNothing_thenCallsOnEvent() {
    // Arrange
    EventModelListener listener = mock(EventModelListener.class);
    doNothing().when(listener).onEvent();

    PlaybackControlFFmpegFrameGrabberVideoSource playbackControlFFmpegFrameGrabberVideoSource =
        new PlaybackControlFFmpegFrameGrabberVideoSource();
    playbackControlFFmpegFrameGrabberVideoSource.addListener(listener);

    // Act
    playbackControlFFmpegFrameGrabberVideoSource.stop();

    // Assert
    verify(listener).onEvent();
  }

  /**
   * Test {@link PlaybackControlFFmpegFrameGrabberVideoSource#stop()}.
   *
   * <ul>
   *   <li>Given {@link PlaybackControlFFmpegFrameGrabberVideoSource} (default constructor).
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link PlaybackControlFFmpegFrameGrabberVideoSource#stop()}
   */
  @Test
  @DisplayName(
      "Test stop(); given PlaybackControlFFmpegFrameGrabberVideoSource (default constructor); then does not throw")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PlaybackControlFFmpegFrameGrabberVideoSource.stop()"})
  void testStop_givenPlaybackControlFFmpegFrameGrabberVideoSource_thenDoesNotThrow() {
    // Arrange, Act and Assert
    assertDoesNotThrow(() -> new PlaybackControlFFmpegFrameGrabberVideoSource().stop());
  }

  /**
   * Test {@link PlaybackControlFFmpegFrameGrabberVideoSource#stop()}.
   *
   * <ul>
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link PlaybackControlFFmpegFrameGrabberVideoSource#stop()}
   */
  @Test
  @DisplayName("Test stop(); then throw RuntimeException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PlaybackControlFFmpegFrameGrabberVideoSource.stop()"})
  void testStop_thenThrowRuntimeException() {
    // Arrange
    EventModelListener listener = mock(EventModelListener.class);
    doThrow(new RuntimeException()).when(listener).onEvent();

    PlaybackControlFFmpegFrameGrabberVideoSource playbackControlFFmpegFrameGrabberVideoSource =
        new PlaybackControlFFmpegFrameGrabberVideoSource();
    playbackControlFFmpegFrameGrabberVideoSource.addListener(listener);

    // Act and Assert
    assertThrows(RuntimeException.class, () -> playbackControlFFmpegFrameGrabberVideoSource.stop());
    verify(listener).onEvent();
  }

  /**
   * Test {@link PlaybackControlFFmpegFrameGrabberVideoSource#resume()}.
   *
   * <p>Method under test: {@link PlaybackControlFFmpegFrameGrabberVideoSource#resume()}
   */
  @Test
  @DisplayName("Test resume()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PlaybackControlFFmpegFrameGrabberVideoSource.resume()"})
  void testResume() {
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
    PlaybackControlFFmpegFrameGrabberVideoSource playbackControlFFmpegFrameGrabberVideoSource =
        null;

    // Act
    playbackControlFFmpegFrameGrabberVideoSource.resume();

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link PlaybackControlFFmpegFrameGrabberVideoSource#seek(long)}.
   *
   * <p>Method under test: {@link PlaybackControlFFmpegFrameGrabberVideoSource#seek(long)}
   */
  @Test
  @DisplayName("Test seek(long)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PlaybackControlFFmpegFrameGrabberVideoSource.seek(long)"})
  void testSeek() throws IOException {
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
    PlaybackControlFFmpegFrameGrabberVideoSource playbackControlFFmpegFrameGrabberVideoSource =
        null;
    long timestamp = 0L;

    // Act
    playbackControlFFmpegFrameGrabberVideoSource.seek(timestamp);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link PlaybackControlFFmpegFrameGrabberVideoSource#stepForward()}.
   *
   * <p>Method under test: {@link PlaybackControlFFmpegFrameGrabberVideoSource#stepForward()}
   */
  @Test
  @DisplayName("Test stepForward()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PlaybackControlFFmpegFrameGrabberVideoSource.stepForward()"})
  void testStepForward() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.RuntimeException: java.lang.NullPointerException: Cannot invoke
    // "org.bytedeco.javacv.FFmpegFrameGrabber.grabImage()" because "this.grabber" is null
    //       at
    // knu.app.bll.grabbers.PlaybackControlFFmpegFrameGrabberVideoSource.stepForward(PlaybackControlFFmpegFrameGrabberVideoSource.java:92)
    //   java.lang.NullPointerException: Cannot invoke
    // "org.bytedeco.javacv.FFmpegFrameGrabber.grabImage()" because "this.grabber" is null
    //       at
    // knu.app.bll.grabbers.PlaybackControlFFmpegFrameGrabberVideoSource.stepForward(PlaybackControlFFmpegFrameGrabberVideoSource.java:90)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange and Act
    new PlaybackControlFFmpegFrameGrabberVideoSource().stepForward();

    // Assert
  }

  /**
   * Test {@link PlaybackControlFFmpegFrameGrabberVideoSource#stepBackward()}.
   *
   * <p>Method under test: {@link PlaybackControlFFmpegFrameGrabberVideoSource#stepBackward()}
   */
  @Test
  @DisplayName("Test stepBackward()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PlaybackControlFFmpegFrameGrabberVideoSource.stepBackward()"})
  void testStepBackward() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke
    // "org.bytedeco.javacv.FFmpegFrameGrabber.getTimestamp()" because "this.grabber" is null
    //       at
    // knu.app.bll.grabbers.PlaybackControlFFmpegFrameGrabberVideoSource.stepBackward(PlaybackControlFFmpegFrameGrabberVideoSource.java:98)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange and Act
    new PlaybackControlFFmpegFrameGrabberVideoSource().stepBackward();

    // Assert
  }

  /**
   * Test {@link PlaybackControlFFmpegFrameGrabberVideoSource#getCurrentPosition()}.
   *
   * <p>Method under test:
   * {@link PlaybackControlFFmpegFrameGrabberVideoSource#getCurrentPosition()}
   */
  @Test
  @DisplayName("Test getCurrentPosition()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long PlaybackControlFFmpegFrameGrabberVideoSource.getCurrentPosition()"})
  void testGetCurrentPosition() {
    // Arrange, Act and Assert
    assertEquals(0L, new PlaybackControlFFmpegFrameGrabberVideoSource().getCurrentPosition());
  }

  /**
   * Test {@link PlaybackControlFFmpegFrameGrabberVideoSource#getDuration()}.
   *
   * <p>Method under test: {@link PlaybackControlFFmpegFrameGrabberVideoSource#getDuration()}
   */
  @Test
  @DisplayName("Test getDuration()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long PlaybackControlFFmpegFrameGrabberVideoSource.getDuration()"})
  void testGetDuration() {
    // Arrange, Act and Assert
    assertEquals(0L, new PlaybackControlFFmpegFrameGrabberVideoSource().getDuration());
  }

  /**
   * Test {@link PlaybackControlFFmpegFrameGrabberVideoSource#getFrameNumber()}.
   *
   * <p>Method under test: {@link PlaybackControlFFmpegFrameGrabberVideoSource#getFrameNumber()}
   */
  @Test
  @DisplayName("Test getFrameNumber()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long PlaybackControlFFmpegFrameGrabberVideoSource.getFrameNumber()"})
  void testGetFrameNumber() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke
    // "org.bytedeco.javacv.FFmpegFrameGrabber.getFrameNumber()" because "this.grabber" is null
    //       at
    // knu.app.bll.grabbers.PlaybackControlFFmpegFrameGrabberVideoSource.getFrameNumber(PlaybackControlFFmpegFrameGrabberVideoSource.java:127)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange and Act
    new PlaybackControlFFmpegFrameGrabberVideoSource().getFrameNumber();

    // Assert
  }

  /**
   * Test {@link PlaybackControlFFmpegFrameGrabberVideoSource#grab()}.
   *
   * <p>Method under test: {@link PlaybackControlFFmpegFrameGrabberVideoSource#grab()}
   */
  @Test
  @DisplayName("Test grab()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Frame PlaybackControlFFmpegFrameGrabberVideoSource.grab()"})
  void testGrab() throws Exception {
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
    PlaybackControlFFmpegFrameGrabberVideoSource playbackControlFFmpegFrameGrabberVideoSource =
        null;

    // Act
    Frame actualGrabResult = playbackControlFFmpegFrameGrabberVideoSource.grab();

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link PlaybackControlFFmpegFrameGrabberVideoSource#addListener(EventModelListener)}.
   *
   * <p>Method under test: {@link
   * PlaybackControlFFmpegFrameGrabberVideoSource#addListener(EventModelListener)}
   */
  @Test
  @DisplayName("Test addListener(EventModelListener)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
      "void PlaybackControlFFmpegFrameGrabberVideoSource.addListener(EventModelListener)"
  })
  void testAddListener() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange and Act
    new PlaybackControlFFmpegFrameGrabberVideoSource().addListener(mock(EventModelListener.class));

    // Assert
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PlaybackControlFFmpegFrameGrabberVideoSource#setFramerate(int)}
   *   <li>{@link PlaybackControlFFmpegFrameGrabberVideoSource#setHeight(int)}
   *   <li>{@link PlaybackControlFFmpegFrameGrabberVideoSource#setInputSource(String)}
   *   <li>{@link PlaybackControlFFmpegFrameGrabberVideoSource#setWidth(int)}
   *   <li>{@link PlaybackControlFFmpegFrameGrabberVideoSource#setPlaybackSpeed(float)}
   *   <li>{@link PlaybackControlFFmpegFrameGrabberVideoSource#pause()}
   *   <li>{@link PlaybackControlFFmpegFrameGrabberVideoSource#getFramerate()}
   *   <li>{@link PlaybackControlFFmpegFrameGrabberVideoSource#getHeight()}
   *   <li>{@link PlaybackControlFFmpegFrameGrabberVideoSource#getInputSource()}
   *   <li>{@link PlaybackControlFFmpegFrameGrabberVideoSource#getWidth()}
   *   <li>{@link PlaybackControlFFmpegFrameGrabberVideoSource#isPaused()}
   *   <li>{@link PlaybackControlFFmpegFrameGrabberVideoSource#isRunning()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
      "int PlaybackControlFFmpegFrameGrabberVideoSource.getFramerate()",
      "int PlaybackControlFFmpegFrameGrabberVideoSource.getHeight()",
      "String PlaybackControlFFmpegFrameGrabberVideoSource.getInputSource()",
      "int PlaybackControlFFmpegFrameGrabberVideoSource.getWidth()",
      "boolean PlaybackControlFFmpegFrameGrabberVideoSource.isPaused()",
      "boolean PlaybackControlFFmpegFrameGrabberVideoSource.isRunning()",
      "void PlaybackControlFFmpegFrameGrabberVideoSource.pause()",
      "void PlaybackControlFFmpegFrameGrabberVideoSource.setFramerate(int)",
      "void PlaybackControlFFmpegFrameGrabberVideoSource.setHeight(int)",
      "void PlaybackControlFFmpegFrameGrabberVideoSource.setInputSource(String)",
      "void PlaybackControlFFmpegFrameGrabberVideoSource.setPlaybackSpeed(float)",
      "void PlaybackControlFFmpegFrameGrabberVideoSource.setWidth(int)"
  })
  void testGettersAndSetters() {
    // Arrange
    PlaybackControlFFmpegFrameGrabberVideoSource playbackControlFFmpegFrameGrabberVideoSource =
        new PlaybackControlFFmpegFrameGrabberVideoSource();

    // Act
    playbackControlFFmpegFrameGrabberVideoSource.setFramerate(1);
    playbackControlFFmpegFrameGrabberVideoSource.setHeight(1);
    playbackControlFFmpegFrameGrabberVideoSource.setInputSource("Input Source");
    playbackControlFFmpegFrameGrabberVideoSource.setWidth(1);
    playbackControlFFmpegFrameGrabberVideoSource.setPlaybackSpeed(10.0f);
    playbackControlFFmpegFrameGrabberVideoSource.pause();
    int actualFramerate = playbackControlFFmpegFrameGrabberVideoSource.getFramerate();
    int actualHeight = playbackControlFFmpegFrameGrabberVideoSource.getHeight();
    String actualInputSource = playbackControlFFmpegFrameGrabberVideoSource.getInputSource();
    int actualWidth = playbackControlFFmpegFrameGrabberVideoSource.getWidth();
    boolean actualIsPausedResult = playbackControlFFmpegFrameGrabberVideoSource.isPaused();

    // Assert
    assertEquals("Input Source", actualInputSource);
    assertEquals(1, actualFramerate);
    assertEquals(1, actualHeight);
    assertEquals(1, actualWidth);
    assertFalse(playbackControlFFmpegFrameGrabberVideoSource.isRunning());
    assertTrue(actualIsPausedResult);
  }

  /**
   * Test {@link PlaybackControlFFmpegFrameGrabberVideoSource#getFrameRate()}.
   *
   * <p>Method under test: {@link PlaybackControlFFmpegFrameGrabberVideoSource#getFrameRate()}
   */
  @Test
  @DisplayName("Test getFrameRate()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"double PlaybackControlFFmpegFrameGrabberVideoSource.getFrameRate()"})
  void testGetFrameRate() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException: Cannot invoke
    // "org.bytedeco.javacv.FFmpegFrameGrabber.getFrameRate()" because "this.grabber" is null
    //       at
    // knu.app.bll.grabbers.PlaybackControlFFmpegFrameGrabberVideoSource.getFrameRate(PlaybackControlFFmpegFrameGrabberVideoSource.java:217)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange and Act
    new PlaybackControlFFmpegFrameGrabberVideoSource().getFrameRate();

    // Assert
  }
}
