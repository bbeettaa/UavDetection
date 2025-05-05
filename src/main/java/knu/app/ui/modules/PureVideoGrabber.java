package knu.app.ui.modules;

import imgui.ImGui;
import imgui.type.ImBoolean;
import imgui.type.ImFloat;
import imgui.type.ImInt;
import knu.app.bll.utils.Utils;
import knu.app.bll.utils.grabbers.PlaybackControlVideoSource;
import org.bytedeco.javacv.Frame;

import javax.swing.*;
import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PureVideoGrabber implements UIModule<Frame> {
    //    private final VideoSource reader;
    private final PlaybackControlVideoSource reader;
    private String videoFilePath;
    private boolean isPlaying;
    private final ImBoolean isOp;

    private final ImInt framerate = new ImInt(30);
    private final ImFloat speed = new ImFloat(1);
    private final ImInt width = new ImInt(1920);
    private final ImInt height = new ImInt(1080);

    public static String GRABBER_ID = "Video Grabber Controls";

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition pauseLock = lock.newCondition();

    public PureVideoGrabber(PlaybackControlVideoSource reader) {
        this.reader = reader;
        this.isOp = new ImBoolean(true);
    }

    @Override
    public String getName() {
        return GRABBER_ID;
    }

    @Override
    public void render() {
        if (!isOp.get()) return;
        ImGui.begin(GRABBER_ID, isOp);

        if (ImGui.button("Open Video File")) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select Video File");
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                videoFilePath = fileChooser.getSelectedFile().getAbsolutePath();
                loadVideoFile();
//                play();
//                pause();
            }
        }
        // 2. Video Information
        ImGui.sameLine();
        ImGui.text("Status: " + (reader.isRunning() ? (reader.isPaused() ? "Paused" : "Playing") : "Stopped"));

        // 5. Video Parameters (disabled during playback)
        ImGui.newLine();
        ImGui.pushItemWidth(100);
        ImGui.beginDisabled(reader.isRunning());
        ImGui.inputInt("Width", width, 20, 100);
        ImGui.sameLine();
        ImGui.inputInt("Height", height, 10, 100);
        ImGui.sameLine();
        ImGui.inputInt("Framerate", framerate);
        ImGui.popItemWidth();
        ImGui.endDisabled();

        // 3. Timeline Control
        long currentMs = reader.getCurrentPosition() / 1000;
        long durationMs = reader.getDuration();

        ImGui.newLine();
        ImGui.text(Utils.formatTimestamp(currentMs));
        ImGui.sameLine();

        float[] progress = new float[]{(float) currentMs / durationMs};
        if (ImGui.sliderFloat("##timeline", progress, 0.0f, 1.0f, "")) {
            try {
                boolean isPlaying = this.isPlaying;
                reader.seek((long) (progress[0] * durationMs));
                if (isPlaying)
                    play();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ImGui.sameLine();
        ImGui.text(Utils.formatTimestamp(durationMs));

        ImGui.setCursorPosX(ImGui.getWindowSizeX() * 0.4f);
        // 4. Playback Controls
        if (ImGui.button(!isPlaying ? "Play" : "Pause")) {
            if (!isPlaying) play();
            else pause();
        }
        ImGui.sameLine();
        if (ImGui.button("Stop")) {
            stop();
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
            e.printStackTrace();
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

    }

    public void stop() {
        isPlaying = false;
        try {
            reader.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        isPlaying = true;
        reader.resume();
    }

    public boolean isPlaying() {
        return isPlaying;
    }
}