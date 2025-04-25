package knu.app.ui.tools;

import imgui.ImGui;
import imgui.flag.ImGuiCol;
import imgui.flag.ImGuiSliderFlags;
import imgui.type.ImBoolean;
import imgui.type.ImFloat;
import knu.app.ui.LocalizationManager;
import org.bytedeco.javacv.Frame;

public class PlaybackControlUI implements UIModule<Void> {
    private VideoGrabber<?> videoGrabber;
    private VideoRenderer<?> videoRenderer;
    private float[] playbackSpeed = new float[]{1.0f};
    private boolean isPlaying = true;
    private final ImBoolean isOp;

    public PlaybackControlUI(VideoGrabber<Frame> grabber, VideoRenderer<?> renderer) {
        this.videoGrabber = grabber;
        this.videoRenderer = renderer;

        this.isOp = new ImBoolean(false);
    }

    @Override
    public String getName() {
        return LocalizationManager.tr("menu.playback.name");
    }

    @Override
    public void render() {
        if(!isOp.get()) return;
        ImGui.begin(LocalizationManager.tr("video.playback.name"), isOp);

        if (ImGui.button(isPlaying ? "Pause" : "Play")) {
            togglePlayPause();
        }

        ImGui.sameLine();
        if (ImGui.button("Stop")) {
            stopPlayback();
        }

        // Навигация по кадрам
        ImGui.sameLine();
        if (ImGui.button("<<")) {
            stepBackward();
        }

        ImGui.sameLine();
        if (ImGui.button(">>")) {
            stepForward();
        }

        ImGui.sameLine();
        ImGui.setNextItemWidth(100);
        if (ImGui.sliderFloat("Speed", playbackSpeed, 0.1f, 2.0f, "%.1fx")) {
            setPlaybackSpeed(playbackSpeed[0]);
        }


        if (ImGui.sliderInt("Time Position", new int[]{1}, 0, 1000)) {
//            currentTime = currentTimeFloat.get();
        }

        if (ImGui.sliderFloat("##timeVertical", new float[]{1.0f}, 0.0f, 1.0f, "%.2f", ImGuiSliderFlags.NoRoundToFormat)) {
            // Обновление времени
        }

        ImGui.end();
    }

    @Override
    public Void execute(Void o) {
        return null;
    }

    @Override
    public void show() {
        isOp.set(true);
    }

    @Override
    public void toggle() {

    }

    @Override
    public boolean isOpened() {
        return isOp.get();
    }

    private void togglePlayPause() {
        isPlaying = !isPlaying;
        if (isPlaying) {
            videoGrabber.resume();
            videoRenderer.resume();
        } else {
            videoGrabber.pause();
            videoRenderer.pause();
        }
    }

    private void stopPlayback() {
        isPlaying = false;
        videoGrabber.stop();
        videoRenderer.stop();
    }

    private void stepForward() {
        videoGrabber.pause();
        videoRenderer.pause();
        isPlaying = false;
        videoGrabber.nextFrame();
    }

    private void stepBackward() {
        videoGrabber.pause();
        videoRenderer.pause();
        isPlaying = false;
        videoGrabber.previousFrame();
    }

    private void setPlaybackSpeed(float speed) {
//        videoGrabber.setPlaybackSpeed(speed);
//        videoRenderer.setPlaybackSpeed(speed);
    }

    private float getCurrentProgress() {
        // Заглушка - нужно реализовать получение текущей позиции
        // Возможно, через videoGrabber.getCurrentPosition() / videoGrabber.getDuration()
        return 0.5f; // временное значение
    }
}