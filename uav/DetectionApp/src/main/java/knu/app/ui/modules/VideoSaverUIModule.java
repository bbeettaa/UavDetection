package knu.app.ui.modules;

import imgui.ImGui;
import imgui.type.ImBoolean;
import knu.app.bll.utils.LocalizationManager;
import knu.app.bll.utils.MatWrapper;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.opencv_core.Mat;

import javax.swing.*;
import java.io.File;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;


public class VideoSaverUIModule implements UIModule<MatWrapper> {

  private final ImBoolean isOpen = new ImBoolean(false);
  private final ImBoolean windowOpen = new ImBoolean(false);

  // queue capacity — ограничение, чтобы не сломать память
  private static final int DEFAULT_QUEUE_CAPACITY = 300;
  private final LinkedBlockingQueue<Mat> queue = new LinkedBlockingQueue<>(DEFAULT_QUEUE_CAPACITY);
  private final AtomicLong droppedCount = new AtomicLong(0);

  // recording state
  private final AtomicBoolean recordingRequested = new AtomicBoolean(false);
  private final AtomicBoolean running = new AtomicBoolean(false); // worker running
  private final AtomicBoolean recorderActive = new AtomicBoolean(false); // recorder started

  private Thread worker;
  private FFmpegFrameRecorder recorder;
  private final OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat();

  private final StatisticDisplayUI statisticDisplayUI;

  // UI state
  private String selectedFilePath = null;
  private volatile String status = "Idle";
  private volatile double recordedFps = 0.0;

  public VideoSaverUIModule(StatisticDisplayUI statisticDisplayUI) {
    this.statisticDisplayUI = statisticDisplayUI;
  }

  @Override
  public String getName() {
    return "Video Saver";
  }

  @Override
  public void render() {
    if (!windowOpen.get()) return;

    if (!ImGui.begin(getName(), isOpen)) {
      ImGui.end();
      return;
    }

    ImGui.text("Simple Live Video Saver");
    ImGui.separator();

    ImGui.text("Output file: " + (selectedFilePath != null ? selectedFilePath : "None"));
    ImGui.sameLine();

    if (ImGui.button("Choose file")) {
      SwingUtilities.invokeLater(() -> {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Select output file (mp4)");
        int res = fc.showSaveDialog(null);
        if (res == JFileChooser.APPROVE_OPTION) {
          File f = fc.getSelectedFile();
          // ensure extension
          if (!f.getName().toLowerCase().endsWith(".mp4")) {
            f = new File(f.getParentFile(), f.getName() + ".mp4");
          }
          selectedFilePath = f.getAbsolutePath();
        }
      });
    }

    ImGui.separator();

    boolean rec = recordingRequested.get();
    if (!rec) {
      if (ImGui.button("Start Recording")) {
        if (selectedFilePath == null) {
          status = "Select output file first.";
        } else {
          startWorker();
          recordingRequested.set(true);
          status = "Recording requested (waiting for frames)...";
        }
      }
    } else {
      if (ImGui.button("Stop Recording")) {
        stopRecording();
      }
    }

    ImGui.sameLine();
    if (ImGui.button("Clear Drops")) {
      droppedCount.set(0);
    }

    ImGui.separator();

    ImGui.text("Status: " + status);
    ImGui.text("Queue size: " + queue.size() + " / " + DEFAULT_QUEUE_CAPACITY);
    ImGui.text("Dropped frames: " + droppedCount.get());
    ImGui.text(String.format("Recorded FPS (est): %.1f", recordedFps));

    ImGui.end();
  }

  @Override
  public MatWrapper execute(MatWrapper record) {
    if (record == null || record.mat == null) return null;

    if (recordingRequested.get()) {
      Mat copy = record.mat.clone();
      boolean offered = queue.offer(copy);
      if (!offered) {
        Mat dropped = queue.poll();
        if (dropped != null) {
          dropped.release();
        }
        boolean offered2 = queue.offer(copy);
        if (!offered2) {
          copy.release();
          droppedCount.incrementAndGet();
        }
      }
    }
    return null;
  }

  @Override
  public void show() {
    windowOpen.set(true);
  }

  @Override
  public void toggle() {
    windowOpen.set(!windowOpen.get());
  }

  @Override
  public boolean isOpened() {
    return windowOpen.get();
  }


  private synchronized void startWorker() {
    if (running.get()) return;
    running.set(true);
    worker = new Thread(this::workerLoop, "video-saver-worker");
    worker.setDaemon(true);
    worker.start();
  }

  private synchronized void stopRecording() {
    recordingRequested.set(false);
    status = "Stopping...";
  }

  private void workerLoop() {
    try {
      long framesWritten = 0;
      long startTimeNs = System.nanoTime();

      while (running.get()) {
        if (!recordingRequested.get()) {
          drainQueueToRecorder();
          if (recorderActive.get()) {
            stopAndReleaseRecorder();
          }
          running.set(false);
          status = "Idle";
          break;
        }

        Mat mat = queue.poll(200, TimeUnit.MILLISECONDS);
        if (mat == null) {
          continue;
        }

        if (!recorderActive.get()) {
          boolean ok = initRecorderFromMat(mat);
          if (!ok) {
            mat.release();
            droppedCount.incrementAndGet();
            continue;
          }
        }

        try {
          org.bytedeco.javacv.Frame frame = converter.convert(mat);
          recorder.record(frame);
          framesWritten++;
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          mat.release();
        }

        long now = System.nanoTime();
        double elapsed = (now - startTimeNs) / 1_000_000_000.0;
        if (elapsed >= 1.0) {
          recordedFps = framesWritten / elapsed;
          startTimeNs = now;
          framesWritten = 0;
        }
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    } finally {
      if (recorderActive.get()) {
        try {
          stopAndReleaseRecorder();
        } catch (Exception ignored) {}
      }
      Mat leftover;
      while ((leftover = queue.poll()) != null) {
        leftover.release();
      }
      running.set(false);
      recordingRequested.set(false);
      status = "Stopped";
    }
  }

  private boolean initRecorderFromMat(Mat mat) {
    try {
      int width = 1920;
      int height = 1080;
      double fps = Math.max(statisticDisplayUI.getFps(), 15);

      recorder = new FFmpegFrameRecorder(selectedFilePath, width, height);
      recorder.setVideoCodec(org.bytedeco.ffmpeg.global.avcodec.AV_CODEC_ID_H264);
      recorder.setFormat("mp4");
      recorder.setFrameRate(fps);
      recorder.setPixelFormat(org.bytedeco.ffmpeg.global.avutil.AV_PIX_FMT_YUV420P);
      recorder.setVideoOption("preset", "ultrafast");
      recorder.setVideoBitrate(2_000_000);
      recorder.start();
      recorderActive.set(true);
      status = "Recording";
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      status = "Recorder init failed: " + e.getMessage();
      recorderActive.set(false);
      return false;
    }
  }

  private void drainQueueToRecorder() {
    Mat mat;
    while ((mat = queue.poll()) != null) {
      if (!recorderActive.get()) {
        mat.release();
        continue;
      }
      try {
        org.bytedeco.javacv.Frame frame = converter.convert(mat);
        recorder.record(frame);
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        mat.release();
      }
    }
  }

  private void stopAndReleaseRecorder() {
    try {
      if (recorder != null) {
        recorder.stop();
        recorder.release();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      recorderActive.set(false);
      recorder = null;
      status = "File saved";
    }
  }
}
