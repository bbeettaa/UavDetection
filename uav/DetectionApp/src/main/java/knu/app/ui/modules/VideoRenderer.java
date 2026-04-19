package knu.app.ui.modules;

import imgui.ImGui;
import imgui.ImVec2;
import imgui.flag.ImGuiStyleVar;
import imgui.flag.ImGuiWindowFlags;
import imgui.type.ImBoolean;
import imgui.type.ImFloat;

import knu.app.bll.utils.textures.FastOpenGLTexture;
import knu.app.bll.utils.textures.UiTexturable;
import knu.app.bll.buffers.BufferElement;
import knu.app.bll.buffers.BufferableQueue;
import knu.app.bll.buffers.OverwritingQueueFrameBuffer;
import knu.app.bll.utils.LocalizationManager;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.annotation.Nullable;
import java.awt.image.BufferedImage;

import imgui.flag.ImGuiCol;

public class VideoRenderer implements UIModule<Frame> {
    private final UiTexturable texture;
    private final Java2DFrameConverter converter;
    private final ImBoolean keepAspectRatio;
    private final ImBoolean keepCentered;
    private final ImFloat aspectX;
    private final ImFloat aspectY;
    private final ImBoolean isOp;
    private PureVideoGrabber reader;
    private final BufferableQueue<BufferedImage> imageBuffer;
    private volatile boolean isRendering = false;

    private static final int VIDEO_WINDOW_FLAGS = ImGuiWindowFlags.NoDecoration | ImGuiWindowFlags.NoMove |
            ImGuiWindowFlags.NoScrollbar | ImGuiWindowFlags.NoScrollWithMouse;

    public static final String VIDEORENDERE_ID = LocalizationManager.tr("output.video.name");
    public static final String VEDIO_OUTPUT_ID = "Video Output";

    public VideoRenderer(PureVideoGrabber reader) {
        this.isOp = new ImBoolean(false);
        this.keepAspectRatio = new ImBoolean(true);
        this.keepCentered = new ImBoolean(true);
        this.aspectX = new ImFloat(16);
        this.aspectY = new ImFloat(9);
        this.texture = new FastOpenGLTexture();
        this.converter = new Java2DFrameConverter();
        this.imageBuffer = new OverwritingQueueFrameBuffer<>(2);
        this.reader = reader;
    }

    @Override
    public void render() {
        if (!isRendering) isRendering = true;
        if (isOp.get()) renderControls();
        renderVideoOutput();
    }

    private void renderVideoOutput() {
        if (!ImGui.begin(VEDIO_OUTPUT_ID, VIDEO_WINDOW_FLAGS)) {
            ImGui.end();
            return;
        }

        try {
            renderTopStatus();
            ImGui.separator();

            renderVideoArea();

            ImGui.separator();
            renderBottomControls();

        } finally {
            ImGui.end();
        }
    }

    private void renderTopStatus() {
        long currentMs = reader.getCurrentPosition();
        long durationMs = reader.getDuration();

        ImGui.textDisabled("VIDEO");
        ImGui.sameLine();
        ImGui.text(reader.isPlaying() ? "PLAYING" : "PAUSED");
        ImGui.sameLine();
        ImGui.textDisabled(formatTimestamp(currentMs) + " / " + formatTimestamp(durationMs));
    }

    private void renderBottomControls() {
        ImGui.pushStyleVar(ImGuiStyleVar.FrameRounding, 7f);
        ImGui.pushStyleVar(ImGuiStyleVar.GrabRounding, 7f);
        ImGui.pushStyleVar(ImGuiStyleVar.ItemSpacing, 8f, 8f);

        try {
            float fullW = ImGui.getContentRegionAvailX();
            float rowH = 32f;

            renderSideAndCenterRow(fullW, rowH);
            ImGui.spacing();
            renderSeekBar();
            ImGui.spacing();
            renderTimeRow();

        } finally {
            ImGui.popStyleVar(3);
        }
    }

    private void renderSideAndCenterRow(float fullW, float rowH) {

        float leftW = 180f;
        float rightW = 140f;

        // LEFT
        ImGui.beginGroup();
        renderLeftGroup(rowH);
        ImGui.endGroup();

        ImGui.sameLine();

        // CENTER (flex)
        float centerSpace = fullW - leftW - rightW;

        float offset = Math.max(0, centerSpace * 0.5f - 120f);
        ImGui.setCursorPosX(ImGui.getCursorPosX() + offset);

        ImGui.beginGroup();
        renderCenterGroup(rowH);
        ImGui.endGroup();

        ImGui.sameLine();

        // RIGHT
        ImGui.setCursorPosX(fullW - rightW);

        ImGui.beginGroup();
        renderRightGroup(rowH);
        ImGui.endGroup();
    }

    private void renderLeftGroup(float rowH) {
        ImGui.textDisabled("VIEW");

        ImGui.sameLine();
        ImGui.checkbox("AR", keepAspectRatio);

        ImGui.sameLine();
        ImGui.checkbox("CENTER", keepCentered);
    }

    private void renderCenterGroup(float rowH) {
        float h = 34f;

        float spacing = ImGui.getStyle().getItemSpacingX();

        float wBack = 44f;
        float wPlay = 60f;
        float wFwd = 44f;
        float wStop = 44f;

        float total =
                wBack + wPlay + wFwd + wStop
                        + spacing * 3;

        float avail = ImGui.getContentRegionAvailX();

        float offset = (avail - total) * 0.0f;

        if (offset > 0) {
            ImGui.invisibleButton("##spacer", offset, 1f);
            ImGui.sameLine();
        }

        if (ImGui.button("\uf048", wBack, h)) seekByFrames(-1);

        ImGui.sameLine();
        if (ImGui.button(reader.isPlaying() ? "\uf04c" : "\uf04b", wPlay, h)) {
            if (reader.isPlaying()) reader.pause();
            else reader.play();
        }

        ImGui.sameLine();
        if (ImGui.button("\uf051", wFwd, h)) seekByFrames(1);

        ImGui.sameLine();
        if (ImGui.button("\uf04d", wStop, h)) reader.stop();
    }

    private void renderRightGroup(float rowH) {
    }

    private void renderSeekBar() {
        long cur = reader.getCurrentPosition();
        long dur = reader.getDuration();
        float p = dur > 0 ? (float) cur / dur : 0f;

        ImVec2 start = ImGui.getCursorScreenPos();
        float w = ImGui.getContentRegionAvailX();
        float h = 22f;

        ImGui.invisibleButton("##seek", w, h);

        boolean hovered = ImGui.isItemHovered();
        boolean active = ImGui.isItemActive();

        if ((hovered || active) && ImGui.isMouseDown(0)) {
            float t = (ImGui.getMousePosX() - start.x) / w;
            seekToFraction(Math.max(0f, Math.min(1f, t)));
            p = t;
        }

        var dl = ImGui.getWindowDrawList();

        float y = start.y + 6f;

        int bg = ImGui.getColorU32(ImGuiCol.FrameBg);
        int fill = ImGui.getColorU32(ImGuiCol.PlotHistogram);
        int text = ImGui.getColorU32(ImGuiCol.TextDisabled);

        // background
        dl.addRectFilled(start.x, y, start.x + w, y + 10, bg, 6f);

        // progress
        dl.addRectFilled(start.x, y, start.x + w * p, y + 10, fill, 6f);

        // knob
        float kx = start.x + w * p;
        dl.addCircleFilled(kx, y + 5f, hovered ? 6f : 4.5f, text);

        // TIME OVERLAY (ВАЖНО)
        String left = formatTimestamp(cur);
        String right = formatTimestamp(dur);

        float pad = 6f;

        dl.addText(start.x + pad, y - 14f, text, left);
        dl.addText(start.x + w - ImGui.calcTextSize(right).x - pad, y - 14f, text, right);

        ImGui.dummy(0, h + 10f);
    }

    private void renderTimeRow() {
        long currentMs = reader.getCurrentPosition();
        long durationMs = reader.getDuration();

        ImGui.textDisabled(formatTimestamp(currentMs));
        ImGui.sameLine();
        ImGui.textDisabled("/");
        ImGui.sameLine();
        ImGui.textDisabled(formatTimestamp(durationMs));
    }

    private void seekToFraction(float fraction) {
        long durationMs = reader.getDuration();
        if (durationMs <= 0) {
            return;
        }
        long target = (long) (fraction * durationMs);
        reader.seek(target);
    }

    private void seekByFrames(int frames) {
        int fps = Math.max(1, reader.getFramerate());
        long frameMs = Math.max(1L, Math.round(1000.0 / fps));

        long currentMs = reader.getCurrentPosition();
        long durationMs = reader.getDuration();

        long target = currentMs + (frames * frameMs);
        if (target < 0) {
            target = 0;
        }
        if (durationMs > 0 && target > durationMs) {
            target = durationMs;
        }

        reader.seek(target);
    }

    private void renderVideoArea() {
        ImGui.pushStyleVar(ImGuiStyleVar.WindowPadding, 0f, 0f);
        float bottomReserve = 92f;
        ImGui.beginChild("##video_area", 0, -bottomReserve, false);

        try {
            BufferElement<BufferedImage> element = imageBuffer.get();
            if (element != null && element.getData() != null) {
                texture.upload(element.getData());
            }

            ImVec2 size = ImGui.getContentRegionAvail();
            float areaW = size.x;
            float areaH = size.y;

            if (areaW <= 1 || areaH <= 1) {
                ImGui.textDisabled("No video frame");
                return;
            }

            float drawW = areaW;
            float drawH = areaH;

            if (keepAspectRatio.get()) {
                float ratio = aspectX.get() / aspectY.get();
                float currentRatio = drawW / drawH;

                if (currentRatio > ratio) {
                    drawW = drawH * ratio;
                } else {
                    drawH = drawW / ratio;
                }
            }

            if (keepCentered.get()) {
                ImGui.setCursorPosX(ImGui.getCursorPosX() + (areaW - drawW) * 0.5f);
                ImGui.setCursorPosY(ImGui.getCursorPosY() + (areaH - drawH) * 0.5f);
            }

            texture.renderImGui(drawW, drawH);

        } finally {
            ImGui.endChild();
            ImGui.popStyleVar();
        }
    }

    @Override
    public String getName() {
        return VIDEORENDERE_ID;
    }

    private void renderControls() {
        if (ImGui.begin("Video Render Controls", isOp)) {
            ImGui.pushItemWidth(100f);
            ImGui.checkbox("Keep Aspect Ratio", keepAspectRatio);
            ImGui.inputFloat("X", aspectX, 0.1f, 0.1f, "%.1f");
            ImGui.sameLine();
            ImGui.inputFloat("Y", aspectY, 0.1f, 0.1f, "%.1f");
            ImGui.popItemWidth();

            ImGui.checkbox("Keep Centered", keepCentered);
        }
        ImGui.end();
    }

    @Nullable
    @Override
    public Frame execute(Frame o) {
        BufferedImage image = converter.getBufferedImage(o);
        imageBuffer.put(new BufferElement<>(image));
        return null;
    }

    @Override
    public void show() {
        isOp.set(true);
    }

    @Override
    public void toggle() {
        isOp.set(!isOp.get());
    }

    @Override
    public boolean isOpened() {
        return isOp.get();
    }

    private String formatTimestamp(long ms) {
        if (ms < 0) {
            ms = 0;
        }

        long totalSeconds = ms / 1000;
        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;
        long millis = ms % 1000;

        if (hours > 0) {
            return String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, millis);
        }
        return String.format("%02d:%02d.%03d", minutes, seconds, millis);
    }

}


