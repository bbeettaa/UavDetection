package knu.app.ui.tools;

import imgui.ImGui;
import imgui.flag.ImGuiInputTextFlags;
import imgui.type.ImBoolean;
import imgui.type.ImInt;
import knu.app.buffers.BufferElement;
import knu.app.buffers.Bufferable;
import knu.app.utils.video.PlaybackControlVideoSource;
import knu.app.utils.video.VideoSource;
import org.bytedeco.javacv.Frame;

import javax.swing.*;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PureVideoGrabber implements UIModule<Frame> {
    private final VideoSource reader;
    private final Bufferable<Frame> frameBuffer;
    private String videoFilePath;
    private boolean isPlaying;
    private final ImBoolean isOp;

    private final ImInt framerate = new ImInt(30);
    private final ImInt width = new ImInt(1920);
    private final ImInt height = new ImInt(1080);

    private final StatisticDisplayUI stat;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition pauseLock = lock.newCondition();

    public PureVideoGrabber(VideoSource reader, Bufferable<Frame> frameBuffer,  StatisticDisplayUI stat) {
        this.reader = reader;
        this.frameBuffer = frameBuffer;
        this.isOp = new ImBoolean(false);
        this.stat = stat;
    }

    @Override
    public String getName() {
        return "Video Grabber";
    }

    @Override
    public void render() {
        if (!isOp.get()) return;
        if (ImGui.begin("Video Grabber Controls", isOp)) {

            if (ImGui.button("Load Video File")) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Select Video File");
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    videoFilePath = fileChooser.getSelectedFile().getAbsolutePath();
                    loadVideoFile();
                }
            }

            if (videoFilePath != null) {
                ImGui.text("File: " + videoFilePath);
            }

            if (ImGui.button(isPlaying ? "Pause" : "Play")) {
                if (isPlaying) pause();
                else play();
            }

            ImGui.sameLine();
            if (ImGui.button("Stop")) {
                stop();
            }

            ImGui.pushItemWidth(100f);
            ImGui.inputInt("Framerate", framerate);
            ImGui.inputInt("Width", width, 10, 100, ImGuiInputTextFlags.EnterReturnsTrue);
            ImGui.inputInt("Height", height, 10, 100);
            ImGui.popItemWidth();
        }
        ImGui.end();
    }

    private void play() {
        if (videoFilePath == null) return;
        lock.lock();
        try {
            if (!isPlaying) {
                isPlaying = true;
                pauseLock.signalAll();
            }
        } finally {
            lock.unlock();
        }

    }

    @Override
    public Frame execute(Frame o) {
        lock.lock();
        try {
            while (!isPlaying) {
                pauseLock.await();
            }
            return reader.grab();
        } catch (Exception e) {
            e.printStackTrace();
            stop();
        } finally {
            lock.unlock();
        }

        return null;
    }

    private void loadVideoFile() {
        try {
            reader.stop();
            reader.setInputSource(videoFilePath);
            reader.setFramerate(framerate.get());
            reader.setWidth(width.get());
            reader.setHeight(height.get());
            reader.start();
            isPlaying = false;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load video file", e);
        }
    }

    public void toggle() {
        isOp.set(!isOp.get());
    }

    public void show() {
        isOp.set(!isOp.get());
    }

    public boolean isOpened() {
        return isOp.get();
    }


    public void pause() {
        isPlaying = false;
        try {
            reader.pause();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        isPlaying = false;
        try {
            reader.stop();
            frameBuffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        isPlaying = true;
        reader.resume();
    }


}