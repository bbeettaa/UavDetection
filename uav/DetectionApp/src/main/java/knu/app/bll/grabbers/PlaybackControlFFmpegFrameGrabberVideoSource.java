package knu.app.bll.grabbers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import knu.app.bll.events.EventModelListener;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;

public class PlaybackControlFFmpegFrameGrabberVideoSource implements PlaybackControlVideoSource {

  private final List<EventModelListener> listeners = new ArrayList<>();
  long lastTimestamp = 0;
  private FFmpegFrameGrabber grabber;
  private String inputSource;
  private int width, height, framerate;
  private volatile boolean running = false;
  private volatile boolean paused = false;
  private long durationMicro;
  private long lastGrabTimeNanos = 0;

  @Override
  public void play() {
    if (running) {
      return;
    }
    if (grabber == null) {
      start();
    }
    running = true;
    paused = false;
  }

  @Override
  public void start() {
    if (grabber != null) {
      return;
    }
    grabber = new FFmpegFrameGrabber(inputSource);
    if (width > 0 && height > 0) {
      grabber.setImageWidth(width);
      grabber.setImageHeight(height);
    }
    if (framerate > 0) {
      grabber.setFrameRate(framerate);
    }
    try {
      grabber.start();
      durationMicro = grabber.getLengthInTime();
    } catch (Exception e) {
      throw new RuntimeException("Error while starting grabber", e);
    }
  }

  @Override
  public void stop() {
    running = false;
    paused = false;
    grabber = null;
    notifyListeners();
  }


  @Override
  public void pause() {
    paused = true;
  }

  @Override
  public void resume() {
    if (!running) {
      play();
    } else {
      paused = false;
    }
  }

  @Override
  public void seek(long timestamp) throws IOException {
    if (grabber == null) {
      start();
    }
    try {
      notifyListeners();
      grabber.setTimestamp(timestamp * 1000);
    } catch (Exception e) {
      throw new IOException(e);
    }
  }

  @Override
  public void stepForward() {
    try {
      grabber.grabImage();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void stepBackward() {
    long pos = grabber.getTimestamp();
    long back = (long) (1_000_000 / grabber.getFrameRate());
    long target = Math.max(0, pos - back);
    try {
      seek(target);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void setPlaybackSpeed(float speed) {
//        if (running) {
//            play();
//        }
  }

  @Override
  public long getCurrentPosition() {
    return grabber != null ? grabber.getTimestamp() / 1000 : 0;
  }

  @Override
  public long getDuration() {
    return durationMicro / 1000;
  }

  @Override
  public long getFrameNumber() {
    if (grabber != null) {
      return grabber.getFrameNumber();
    } else {
      return 0;
    }
  }

  @Override
  public Frame grab() throws Exception {
    if (grabber == null) {
      start();
    }
//        return grabWithSleep();
    return grabWithTimestamps();
  }

  private Frame grabWithTimestamps() throws FFmpegFrameGrabber.Exception, InterruptedException {
    Frame frame = grabber.grabImage();
    if (frame == null) {
      return null;
    }

    long currentTimestamp = frame.timestamp;  // В микросекундах (PTS from FFmpeg)
    if (lastTimestamp < 0) {
      lastTimestamp = currentTimestamp;  // Инициализация на первом кадре
      return frame;
    }

    // Рассчитываем реальный wait на основе timestamp'ов кадров (для real-time playback)
    long deltaMicros = currentTimestamp - lastTimestamp;
    if (deltaMicros > 0) {
      long waitNanos = deltaMicros * 1000;  // Микросекунды в наносекунды
      long millis = waitNanos / 1_000_000;
      int nanos = (int) (waitNanos % 1_000_000);

      // Hybrid sleep для точности
      if (millis > 0 || nanos > 50_000) {
        Thread.sleep(millis, nanos);
      } else {
        long startSpin = System.nanoTime();
        while (System.nanoTime() - startSpin < waitNanos) {
          Thread.onSpinWait();
        }
      }
    }

    lastTimestamp = currentTimestamp;
    return frame;
  }

  private Frame grabWithSleep() throws InterruptedException, FFmpegFrameGrabber.Exception {
    double effectiveFps = (framerate > 0 ? framerate : grabber.getFrameRate());
    long periodNanos = (long) (1_000_000_000L / effectiveFps);

    long now = System.nanoTime();
    if (lastGrabTimeNanos > 0) {
      long elapsed = now - lastGrabTimeNanos;
      long waitNanos = periodNanos - elapsed;
      if (waitNanos > 0) {
        long millis = waitNanos / 1_000_000;
        int nanos = (int) (waitNanos % 1_000_000);
        Thread.sleep(millis, nanos);
      }
    }

    Frame frame = grabber.grabImage();
    lastGrabTimeNanos = System.nanoTime();
    return frame;
  }

  @Override
  public boolean isRunning() {
    return running;
  }

  @Override
  public boolean isPaused() {
    return paused;
  }

  @Override
  public void addListener(EventModelListener listener) {
    listeners.add(listener);
  }

  private void notifyListeners() {
    for (EventModelListener l : listeners) {
      l.onEvent();
    }
  }

  @Override
  public String getInputSource() {
    return inputSource;
  }

  @Override
  public void setInputSource(String inputSource) {
    this.inputSource = inputSource;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public void setWidth(int width) {
    this.width = width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public void setHeight(int height) {
    this.height = height;
  }

  @Override
  public int getFramerate() {
    return framerate;
  }

  @Override
  public void setFramerate(int framerate) {
    this.framerate = framerate;
    if (this.grabber != null) {
      this.grabber.setFrameRate(framerate);
    }
  }

  @Override
  public double getFrameRate() {
    return grabber.getFrameRate();
  }
}
