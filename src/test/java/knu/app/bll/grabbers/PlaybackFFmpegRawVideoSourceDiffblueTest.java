package knu.app.bll.grabbers;

import static org.mockito.Mockito.mock;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import knu.app.bll.events.EventModelListener;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class PlaybackFFmpegRawVideoSourceDiffblueTest {

  /**
   * Test {@link PlaybackFFmpegRawVideoSource#PlaybackFFmpegRawVideoSource(int, int, int, int)}.
   *
   * <p>Method under test: {@link PlaybackFFmpegRawVideoSource#PlaybackFFmpegRawVideoSource(int,
   * int, int, int)}
   */
  @Test
  @DisplayName("Test new PlaybackFFmpegRawVideoSource(int, int, int, int)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PlaybackFFmpegRawVideoSource.<init>(int, int, int, int)"})
  void testNewPlaybackFFmpegRawVideoSource() {
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
    int width = 0;
    int height = 0;
    int framerate = 0;
    int ffmpegThreads = 0;

    // Act
    PlaybackFFmpegRawVideoSource actualPlaybackFFmpegRawVideoSource =
        new PlaybackFFmpegRawVideoSource(width, height, framerate, ffmpegThreads);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link PlaybackFFmpegRawVideoSource#PlaybackFFmpegRawVideoSource()}.
   *
   * <p>Method under test: {@link PlaybackFFmpegRawVideoSource#PlaybackFFmpegRawVideoSource()}
   */
  @Test
  @DisplayName("Test new PlaybackFFmpegRawVideoSource()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PlaybackFFmpegRawVideoSource.<init>()"})
  void testNewPlaybackFFmpegRawVideoSource2() {
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
    PlaybackFFmpegRawVideoSource actualPlaybackFFmpegRawVideoSource =
        new PlaybackFFmpegRawVideoSource();

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test
   * {@link PlaybackFFmpegRawVideoSource#PlaybackFFmpegRawVideoSource(int, int, String, int, int)}.
   *
   * <p>Method under test: {@link PlaybackFFmpegRawVideoSource#PlaybackFFmpegRawVideoSource(int,
   * int, String, int, int)}
   */
  @Test
  @DisplayName("Test new PlaybackFFmpegRawVideoSource(int, int, String, int, int)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PlaybackFFmpegRawVideoSource.<init>(int, int, String, int, int)"})
  void testNewPlaybackFFmpegRawVideoSource3() {
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
    int width = 0;
    int height = 0;
    String inputSource = "";
    int framerate = 0;
    int ffmpegThreads = 0;

    // Act
    PlaybackFFmpegRawVideoSource actualPlaybackFFmpegRawVideoSource =
        new PlaybackFFmpegRawVideoSource(width, height, inputSource, framerate, ffmpegThreads);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link PlaybackFFmpegRawVideoSource#PlaybackFFmpegRawVideoSource(String, int, int)}.
   *
   * <p>Method under test: {@link PlaybackFFmpegRawVideoSource#PlaybackFFmpegRawVideoSource(String,
   * int, int)}
   */
  @Test
  @DisplayName("Test new PlaybackFFmpegRawVideoSource(String, int, int)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PlaybackFFmpegRawVideoSource.<init>(String, int, int)"})
  void testNewPlaybackFFmpegRawVideoSource4() {
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
    String inputSource = "";
    int framerate = 0;
    int ffmpegThreads = 0;

    // Act
    PlaybackFFmpegRawVideoSource actualPlaybackFFmpegRawVideoSource =
        new PlaybackFFmpegRawVideoSource(inputSource, framerate, ffmpegThreads);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link PlaybackFFmpegRawVideoSource#PlaybackFFmpegRawVideoSource(String, int)}.
   *
   * <p>Method under test: {@link PlaybackFFmpegRawVideoSource#PlaybackFFmpegRawVideoSource(String,
   * int)}
   */
  @Test
  @DisplayName("Test new PlaybackFFmpegRawVideoSource(String, int)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PlaybackFFmpegRawVideoSource.<init>(String, int)"})
  void testNewPlaybackFFmpegRawVideoSource5() {
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
    String inputSource = "";
    int framerate = 0;

    // Act
    PlaybackFFmpegRawVideoSource actualPlaybackFFmpegRawVideoSource =
        new PlaybackFFmpegRawVideoSource(inputSource, framerate);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link PlaybackFFmpegRawVideoSource#PlaybackFFmpegRawVideoSource(int)}.
   *
   * <p>Method under test: {@link PlaybackFFmpegRawVideoSource#PlaybackFFmpegRawVideoSource(int)}
   */
  @Test
  @DisplayName("Test new PlaybackFFmpegRawVideoSource(int)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PlaybackFFmpegRawVideoSource.<init>(int)"})
  void testNewPlaybackFFmpegRawVideoSource6() {
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
    int framerate = 0;

    // Act
    PlaybackFFmpegRawVideoSource actualPlaybackFFmpegRawVideoSource =
        new PlaybackFFmpegRawVideoSource(framerate);

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link PlaybackFFmpegRawVideoSource#grab()}.
   *
   * <p>Method under test: {@link PlaybackFFmpegRawVideoSource#grab()}
   */
  @Test
  @DisplayName("Test grab()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"org.bytedeco.javacv.Frame PlaybackFFmpegRawVideoSource.grab()"})
  void testGrab() {
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
    new PlaybackFFmpegRawVideoSource().grab();

    // Assert
  }

  /**
   * Test {@link PlaybackFFmpegRawVideoSource#grabRowVideo()}.
   *
   * <p>Method under test: {@link PlaybackFFmpegRawVideoSource#grabRowVideo()}
   */
  @Test
  @DisplayName("Test grabRowVideo()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"org.bytedeco.javacv.Frame PlaybackFFmpegRawVideoSource.grabRowVideo()"})
  void testGrabRowVideo() throws Exception {
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
    new PlaybackFFmpegRawVideoSource().grabRowVideo();

    // Assert
  }

  /**
   * Test {@link PlaybackFFmpegRawVideoSource#getFrameRate()}.
   *
   * <p>Method under test: {@link PlaybackFFmpegRawVideoSource#getFrameRate()}
   */
  @Test
  @DisplayName("Test getFrameRate()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"double PlaybackFFmpegRawVideoSource.getFrameRate()"})
  void testGetFrameRate() {
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
    new PlaybackFFmpegRawVideoSource().getFrameRate();

    // Assert
  }

  /**
   * Test {@link PlaybackFFmpegRawVideoSource#start()}.
   *
   * <ul>
   *   <li>Given {@link PlaybackFFmpegRawVideoSource#PlaybackFFmpegRawVideoSource(int)} with
   *       framerate is one seek one.
   * </ul>
   *
   * <p>Method under test: {@link PlaybackFFmpegRawVideoSource#start()}
   */
  @Test
  @DisplayName(
      "Test start(); given PlaybackFFmpegRawVideoSource(int) with framerate is one seek one")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PlaybackFFmpegRawVideoSource.start()"})
  void testStart_givenPlaybackFFmpegRawVideoSourceWithFramerateIsOneSeekOne() throws IOException {
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
    PlaybackFFmpegRawVideoSource playbackFFmpegRawVideoSource = new PlaybackFFmpegRawVideoSource(1);
    playbackFFmpegRawVideoSource.seek(1L);
    playbackFFmpegRawVideoSource.setPlaybackSpeed(1.0f);

    // Act
    playbackFFmpegRawVideoSource.start();

    // Assert
  }

  /**
   * Test {@link PlaybackFFmpegRawVideoSource#play()}.
   *
   * <ul>
   *   <li>Given {@link PlaybackFFmpegRawVideoSource#PlaybackFFmpegRawVideoSource(int)} with
   *       framerate is one seek one.
   * </ul>
   *
   * <p>Method under test: {@link PlaybackFFmpegRawVideoSource#play()}
   */
  @Test
  @DisplayName(
      "Test play(); given PlaybackFFmpegRawVideoSource(int) with framerate is one seek one")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PlaybackFFmpegRawVideoSource.play()"})
  void testPlay_givenPlaybackFFmpegRawVideoSourceWithFramerateIsOneSeekOne() throws IOException {
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
    PlaybackFFmpegRawVideoSource playbackFFmpegRawVideoSource = new PlaybackFFmpegRawVideoSource(1);
    playbackFFmpegRawVideoSource.seek(1L);
    playbackFFmpegRawVideoSource.setPlaybackSpeed(1.0f);

    // Act
    playbackFFmpegRawVideoSource.play();

    // Assert
  }

  /**
   * Test {@link PlaybackFFmpegRawVideoSource#pause()}.
   *
   * <p>Method under test: {@link PlaybackFFmpegRawVideoSource#pause()}
   */
  @Test
  @DisplayName("Test pause()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PlaybackFFmpegRawVideoSource.pause()"})
  void testPause() {
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
    new PlaybackFFmpegRawVideoSource().pause();

    // Assert
  }

  /**
   * Test {@link PlaybackFFmpegRawVideoSource#resume()}.
   *
   * <p>Method under test: {@link PlaybackFFmpegRawVideoSource#resume()}
   */
  @Test
  @DisplayName("Test resume()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PlaybackFFmpegRawVideoSource.resume()"})
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

    // Arrange and Act
    new PlaybackFFmpegRawVideoSource().resume();

    // Assert
  }

  /**
   * Test {@link PlaybackFFmpegRawVideoSource#stop()}.
   *
   * <p>Method under test: {@link PlaybackFFmpegRawVideoSource#stop()}
   */
  @Test
  @DisplayName("Test stop()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PlaybackFFmpegRawVideoSource.stop()"})
  void testStop() throws IOException {
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
    new PlaybackFFmpegRawVideoSource().stop();

    // Assert
  }

  /**
   * Test {@link PlaybackFFmpegRawVideoSource#seek(long)}.
   *
   * <p>Method under test: {@link PlaybackFFmpegRawVideoSource#seek(long)}
   */
  @Test
  @DisplayName("Test seek(long)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PlaybackFFmpegRawVideoSource.seek(long)"})
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

    // Arrange and Act
    new PlaybackFFmpegRawVideoSource().seek(10L);

    // Assert
  }

  /**
   * Test {@link PlaybackFFmpegRawVideoSource#stepForward()}.
   *
   * <p>Method under test: {@link PlaybackFFmpegRawVideoSource#stepForward()}
   */
  @Test
  @DisplayName("Test stepForward()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PlaybackFFmpegRawVideoSource.stepForward()"})
  void testStepForward() {
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
    new PlaybackFFmpegRawVideoSource().stepForward();

    // Assert
  }

  /**
   * Test {@link PlaybackFFmpegRawVideoSource#stepBackward()}.
   *
   * <p>Method under test: {@link PlaybackFFmpegRawVideoSource#stepBackward()}
   */
  @Test
  @DisplayName("Test stepBackward()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PlaybackFFmpegRawVideoSource.stepBackward()"})
  void testStepBackward() {
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
    new PlaybackFFmpegRawVideoSource().stepBackward();

    // Assert
  }

  /**
   * Test {@link PlaybackFFmpegRawVideoSource#setPlaybackSpeed(float)}.
   *
   * <ul>
   *   <li>When {@code 1.0E-5}.
   * </ul>
   *
   * <p>Method under test: {@link PlaybackFFmpegRawVideoSource#setPlaybackSpeed(float)}
   */
  @Test
  @DisplayName("Test setPlaybackSpeed(float); when '1.0E-5'")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PlaybackFFmpegRawVideoSource.setPlaybackSpeed(float)"})
  void testSetPlaybackSpeed_when10e5() {
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
    new PlaybackFFmpegRawVideoSource().setPlaybackSpeed(1.0E-5f);

    // Assert
  }

  /**
   * Test {@link PlaybackFFmpegRawVideoSource#getCurrentPosition()}.
   *
   * <p>Method under test: {@link PlaybackFFmpegRawVideoSource#getCurrentPosition()}
   */
  @Test
  @DisplayName("Test getCurrentPosition()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long PlaybackFFmpegRawVideoSource.getCurrentPosition()"})
  void testGetCurrentPosition() {
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
    new PlaybackFFmpegRawVideoSource().getCurrentPosition();

    // Assert
  }

  /**
   * Test {@link PlaybackFFmpegRawVideoSource#getDuration()}.
   *
   * <p>Method under test: {@link PlaybackFFmpegRawVideoSource#getDuration()}
   */
  @Test
  @DisplayName("Test getDuration()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"long PlaybackFFmpegRawVideoSource.getDuration()"})
  void testGetDuration() {
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
    new PlaybackFFmpegRawVideoSource().getDuration();

    // Assert
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PlaybackFFmpegRawVideoSource#setFramerate(int)}
   *   <li>{@link PlaybackFFmpegRawVideoSource#setInputSource(String)}
   *   <li>{@link PlaybackFFmpegRawVideoSource#getFrameNumber()}
   *   <li>{@link PlaybackFFmpegRawVideoSource#getFramerate()}
   *   <li>{@link PlaybackFFmpegRawVideoSource#getHeight()}
   *   <li>{@link PlaybackFFmpegRawVideoSource#getInputSource()}
   *   <li>{@link PlaybackFFmpegRawVideoSource#getWidth()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
      "long PlaybackFFmpegRawVideoSource.getFrameNumber()",
      "int PlaybackFFmpegRawVideoSource.getFramerate()",
      "int PlaybackFFmpegRawVideoSource.getHeight()",
      "String PlaybackFFmpegRawVideoSource.getInputSource()",
      "int PlaybackFFmpegRawVideoSource.getWidth()",
      "void PlaybackFFmpegRawVideoSource.setFramerate(int)",
      "void PlaybackFFmpegRawVideoSource.setInputSource(String)"
  })
  void testGettersAndSetters() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: Exception in arrange section.
    //   Diffblue Cover was unable to construct an instance of the class under test using
    //   knu.app.bll.grabbers.PlaybackFFmpegRawVideoSource.setFramerate(int).
    //   The arrange section threw
    //   java.lang.NoClassDefFoundError: Could not initialize class
    // org.bytedeco.openblas.global.openblas_nolapack
    //       at java.base/java.lang.Class.forName0(Native Method)
    //       at java.base/java.lang.Class.forName(Class.java:534)
    //       at java.base/java.lang.Class.forName(Class.java:513)
    //       at org.bytedeco.javacpp.Loader.load(Loader.java:1289)
    //       at org.bytedeco.javacpp.Loader.load(Loader.java:1234)
    //       at org.bytedeco.javacpp.Loader.load(Loader.java:1226)
    //       at org.bytedeco.javacv.OpenCVFrameConverter.<clinit>(OpenCVFrameConverter.java:43)
    //       at
    // knu.app.bll.grabbers.PlaybackFFmpegRawVideoSource.<init>(PlaybackFFmpegRawVideoSource.java:42)
    //       at
    // knu.app.bll.grabbers.PlaybackFFmpegRawVideoSource.<init>(PlaybackFFmpegRawVideoSource.java:79)
    //   See https://diff.blue/R081 to resolve this issue.

    // Arrange
    // TODO: Populate arranged inputs
    PlaybackFFmpegRawVideoSource playbackFFmpegRawVideoSource = null;
    int framerate = 0;

    // Act
    playbackFFmpegRawVideoSource.setFramerate(framerate);
    String inputSource = "";
    playbackFFmpegRawVideoSource.setInputSource(inputSource);
    long actualFrameNumber = playbackFFmpegRawVideoSource.getFrameNumber();
    int actualFramerate = playbackFFmpegRawVideoSource.getFramerate();
    int actualHeight = playbackFFmpegRawVideoSource.getHeight();
    String actualInputSource = playbackFFmpegRawVideoSource.getInputSource();
    int actualWidth = playbackFFmpegRawVideoSource.getWidth();

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link PlaybackFFmpegRawVideoSource#setWidth(int)}.
   *
   * <p>Method under test: {@link PlaybackFFmpegRawVideoSource#setWidth(int)}
   */
  @Test
  @DisplayName("Test setWidth(int)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PlaybackFFmpegRawVideoSource.setWidth(int)"})
  void testSetWidth() {
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
    new PlaybackFFmpegRawVideoSource().setWidth(1);

    // Assert
  }

  /**
   * Test {@link PlaybackFFmpegRawVideoSource#setHeight(int)}.
   *
   * <p>Method under test: {@link PlaybackFFmpegRawVideoSource#setHeight(int)}
   */
  @Test
  @DisplayName("Test setHeight(int)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PlaybackFFmpegRawVideoSource.setHeight(int)"})
  void testSetHeight() {
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
    new PlaybackFFmpegRawVideoSource().setHeight(1);

    // Assert
  }

  /**
   * Test {@link PlaybackFFmpegRawVideoSource#isRunning()}.
   *
   * <p>Method under test: {@link PlaybackFFmpegRawVideoSource#isRunning()}
   */
  @Test
  @DisplayName("Test isRunning()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PlaybackFFmpegRawVideoSource.isRunning()"})
  void testIsRunning() {
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
    new PlaybackFFmpegRawVideoSource().isRunning();

    // Assert
  }

  /**
   * Test {@link PlaybackFFmpegRawVideoSource#isPaused()}.
   *
   * <p>Method under test: {@link PlaybackFFmpegRawVideoSource#isPaused()}
   */
  @Test
  @DisplayName("Test isPaused()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PlaybackFFmpegRawVideoSource.isPaused()"})
  void testIsPaused() {
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
    new PlaybackFFmpegRawVideoSource().isPaused();

    // Assert
  }

  /**
   * Test {@link PlaybackFFmpegRawVideoSource#addListener(EventModelListener)}.
   *
   * <p>Method under test: {@link PlaybackFFmpegRawVideoSource#addListener(EventModelListener)}
   */
  @Test
  @DisplayName("Test addListener(EventModelListener)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void PlaybackFFmpegRawVideoSource.addListener(EventModelListener)"})
  void testAddListener() {
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
    new PlaybackFFmpegRawVideoSource().addListener(mock(EventModelListener.class));

    // Assert
  }
}
