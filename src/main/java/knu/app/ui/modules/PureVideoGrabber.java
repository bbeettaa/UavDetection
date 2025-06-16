package knu.app.ui.modules;

import imgui.ImGui;
import imgui.type.ImBoolean;
import imgui.type.ImInt;
import knu.app.bll.utils.LocalizationManager;
import knu.app.bll.utils.Utils;
import knu.app.bll.utils.grabbers.PlaybackControlVideoSource;
import org.bytedeco.javacv.Frame;

import javax.swing.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

public class PureVideoGrabber implements UIModule<Frame> {
    private static final Logger logger = Logger.getLogger(PureVideoGrabber.class.getName());
    private final PlaybackControlVideoSource reader;
    private String videoFilePath;
    private boolean isPlaying;
    private final ImBoolean isOp;

    private final ImInt framerate = new ImInt(30);
    private final ImInt width = new ImInt(1920);
    private final ImInt height = new ImInt(1080);

    public static final String GRABBER_ID = "Video Grabber Controls";

//    private final ReentrantLock lock = new ReentrantLock();
//    private final Condition pauseLock = lock.newCondition();

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
        videoGrabberDialog();
        videoInformation();
        videoParameters();
        timeLineControl();
        playbackControl();
        ImGui.end();
    }

    private void playbackControl() {
        if (ImGui.button(!isPlaying ? LocalizationManager.tr("status.play") : LocalizationManager.tr("status.pause"))) {
            if (!isPlaying) play();
            else pause();
        }
        ImGui.sameLine();
        if (ImGui.button(LocalizationManager.tr("status.stop"))) {
            stop();
        }
    }

    private void timeLineControl() {
        long currentMs = reader.getCurrentPosition();
        long durationMs = reader.getDuration();

        ImGui.newLine();
        ImGui.text(Utils.formatTimestamp(currentMs));
        ImGui.sameLine();

        float[] progress = new float[]{(float) currentMs / durationMs};
        if (ImGui.sliderFloat("##timeline", progress, 0.0f, 1.0f, "")) {
            try {
                boolean isPlaying = this.isPlaying;
                reader.seek((long) (progress[0] * durationMs));
                if (isPlaying) play();
            } catch (IOException e) {
                logger.warning(Arrays.toString(e.getStackTrace()));
            }
        }
        ImGui.sameLine();
        ImGui.text(Utils.formatTimestamp(durationMs));

        ImGui.setCursorPosX(ImGui.getWindowSizeX() * 0.4f);
    }

    private void videoGrabberDialog() {
        if (ImGui.button(LocalizationManager.tr("videograbber.videosource.open"))) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle(LocalizationManager.tr("videograbber.videosource.select"));
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                videoFilePath = fileChooser.getSelectedFile().getAbsolutePath();
                loadVideoFile();
            }
        }
    }

    private void videoInformation() {
        ImGui.sameLine();
        ImGui.text(
                LocalizationManager.tr("status.status") +
                        (reader.isRunning() ?
                                (reader.isPaused() ? LocalizationManager.tr("status.paused") : LocalizationManager.tr("status.playing")) :
                                LocalizationManager.tr("status.stopped")));
    }

    private void videoParameters() {
        ImGui.newLine();
        ImGui.pushItemWidth(100);
        ImGui.beginDisabled(reader.isRunning());
        ImGui.inputInt(LocalizationManager.tr("video.attr.width"), width, 20, 100);
        ImGui.sameLine();
        ImGui.inputInt(LocalizationManager.tr("video.attr.height"), height, 10, 100);
        ImGui.sameLine();
        ImGui.inputInt(LocalizationManager.tr("video.attr.framerate"), framerate);
        ImGui.popItemWidth();
        ImGui.endDisabled();
    }

    private void play() {
        if (videoFilePath == null) return;
//        lock.lock();
        try {
            if (!isPlaying) {
                isPlaying = true;
//                pauseLock.signalAll();
            }
        } finally {
//            lock.unlock();
        }

    }

    Frame l;
    @Override
    public Frame execute(Frame o) {
//        lock.lock();
        try {
            while (!isPlaying) {
//                pauseLock.await();
                Thread.sleep(500);
                return l;
            }
//            Frame l =  reader.grab();
            l = reader.grab();
            return l;
        } catch (Exception e) {
            logger.warning(Arrays.toString(e.getStackTrace()));
            stop();
        } finally {
//            lock.unlock();
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
            logger.warning(Arrays.toString(e.getStackTrace()));
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
            logger.warning(Arrays.toString(e.getStackTrace()));
        }
    }

    public long getCurrentFrameIndex(){
        return reader.getFrameNumber();
    }

}