package knu.app.ui.tools;

import imgui.ImGui;
import imgui.flag.ImGuiInputTextFlags;
import imgui.type.ImBoolean;
import imgui.type.ImInt;
import knu.app.buffers.BufferElement;
import knu.app.buffers.Bufferable;
import knu.app.utils.video.PlaybackControlVideoSource;
import org.bytedeco.javacv.Frame;

import javax.swing.*;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

public class VideoGrabber<T> implements UIModule<T> {
    private final PlaybackControlVideoSource reader;
    private final ExecutorService executor;
    private final Bufferable<Frame> frameBuffer;
    private String videoFilePath;

    // Состояния управления
    private final AtomicBoolean isRunning = new AtomicBoolean(false); // Флаг выполнения потока захвата
    private final AtomicBoolean shouldPlay = new AtomicBoolean(false); // Флаг желаемого состояния (play/pause)
    private final ImBoolean isOp; // Видимость UI

    // Настройки видео
    private final ImInt framerate = new ImInt(30);
    private final ImInt width = new ImInt(1920);
    private final ImInt height = new ImInt(1080);

    // Статус
    private boolean isStreamFinished = false;
    private String statusMessage = "Ready";

    public VideoGrabber(PlaybackControlVideoSource reader, Bufferable<Frame> frameBuffer, ExecutorService executor) {
        this.reader = reader;
        this.frameBuffer = frameBuffer;
        this.executor = executor;
        this.isOp = new ImBoolean(false);
    }

    @Override
    public String getName() {
        return "Video Grabber";
    }

    @Override
    public void render() {
        if (!isOp.get()) return;

        if (ImGui.begin("Video Grabber Controls", isOp)) {
            ImGui.textColored(isStreamFinished ? 0xFF0000FF : 0xFF00FF00, statusMessage);

            if (videoFilePath != null) ImGui.text("File: " + videoFilePath);

            if (ImGui.button("Load Video File")) loadVideoFileWithDialog();

            if (ImGui.button(shouldPlay.get() ? "Pause" : "Play")) togglePlayPause();

            ImGui.sameLine();
            if (ImGui.button("Stop")) stop();

//            ImGui.newLine();
//            if (ImGui.button("Next Frame") && !shouldPlay.get()) nextFrame();
//            ImGui.sameLine();
//            if (ImGui.button("Prev Frame") && !shouldPlay.get()) previousFrame();

            ImGui.separator();
            ImGui.newLine();
            ImGui.pushItemWidth(100f);
            ImGui.inputInt("Framerate", framerate);
            ImGui.newLine();
            ImGui.inputInt("Width", width, 10, 100, ImGuiInputTextFlags.EnterReturnsTrue);
            ImGui.inputInt("Height", height, 10, 100);
            ImGui.popItemWidth();
        }
        ImGui.end();
    }

    private void loadVideoFileWithDialog() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Video File");
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            videoFilePath = fileChooser.getSelectedFile().getAbsolutePath();
            loadVideoFile();
        }
    }

    private void togglePlayPause() {
        if (shouldPlay.get()) {
            pause();
        } else {
            play();
        }
    }

    private void play() {
        if (videoFilePath == null) return;

        if (!isRunning.get()) {
            // Первый запуск
            shouldPlay.set(true);
            isRunning.set(true);
            isStreamFinished = false;
            statusMessage = "Playing...";
            executor.submit(this::grabFrames);
        } else {
            // Продолжение после паузы
            shouldPlay.set(true);
            reader.resume();
            statusMessage = "Playing...";
        }
    }

    private void grabFrames() {
        try {
            while (isRunning.get() && !Thread.currentThread().isInterrupted()) {
                if (shouldPlay.get()) {
                    Frame frame = reader.grab();
                    if (frame != null) {
                        frameBuffer.put(new BufferElement<>(frame));
                    } else {
                        // Конец потока
                        isStreamFinished = true;
                        statusMessage = "Stream finished";
                        stop();
                        break;
                    }
                } else {
                    // Пауза - небольшая задержка чтобы не грузить процессор
                    Thread.sleep(50);
                }
            }
        } catch (Exception e) {
            isStreamFinished = true;
            statusMessage = "Error: " + e.getMessage();
            e.printStackTrace();
            stop();
        } finally {
            isRunning.set(false);
        }
    }

    private void loadVideoFile() {
        try {
            stop();
            reader.setInputSource(videoFilePath);
            reader.setFramerate(framerate.get());
            reader.setWidth(width.get());
            reader.setHeight(height.get());
            reader.start();
            isStreamFinished = false;
            statusMessage = "Video loaded";
        } catch (Exception e) {
            statusMessage = "Load error: " + e.getMessage();
            throw new RuntimeException("Failed to load video file", e);
        }
    }

    public void pause() {
        shouldPlay.set(false);
        try {
            reader.pause();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        statusMessage = "Paused";
    }

    public void stop() {
        shouldPlay.set(false);
        isRunning.set(false);
        try {
            reader.stop();
            frameBuffer.clear();
            statusMessage = "Stopped";
        } catch (IOException e) {
            statusMessage = "Stop error: " + e.getMessage();
            e.printStackTrace();
        }
    }

    public void resume() {
        shouldPlay.set(true);
        reader.resume();
        statusMessage = "Playing...";
    }

    public void nextFrame() {
        if (!shouldPlay.get()) {
            reader.stepForward();
            statusMessage = "Frame forward";
        }
    }

    public void previousFrame() {
        if (!shouldPlay.get()) {
            reader.stepBackward();
            statusMessage = "Frame backward";
        }
    }

    public void toggle() {
        isOp.set(!isOp.get());
    }

    public void show() {
        isOp.set(true);
    }

    public boolean isOpened() {
        return isOp.get();
    }

    @Override
    public T execute(T o) {
        return null;
    }
}