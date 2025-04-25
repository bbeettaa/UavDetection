package knu.app.ui.tools;

import imgui.ImGui;
import imgui.ImVec2;
import imgui.flag.ImGuiWindowFlags;
import imgui.type.ImBoolean;
import imgui.type.ImFloat;
import knu.app.utils.textures.CpuImageTexture;
import knu.app.utils.textures.FastOpenGLTexture;
import knu.app.utils.textures.UiTexturable;
import knu.app.buffers.BufferElement;
import knu.app.buffers.Bufferable;
import knu.app.buffers.OverwritingQueueFrameBuffer;
import knu.app.ui.LocalizationManager;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.annotation.Nullable;
import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;

public class VideoRenderer implements UIModule<Frame> {
    private final Bufferable<Frame> frameBuffer;
    private final UiTexturable texture;
    private final Java2DFrameConverter converter;

    private final ImBoolean keepAspectRatio;
    private final ImBoolean keepCentered;
    private final ImFloat aspectX;
    private final ImFloat aspectY;
    private final ImBoolean isOp;

    private final Bufferable<BufferedImage> imageBuffer;
    private volatile boolean isRendering = false;

    private final StatisticDisplayUI stat;


    private static final int VIDEO_WINDOW_FLAGS =
            ImGuiWindowFlags.NoDecoration |
                    ImGuiWindowFlags.NoMove |
                    ImGuiWindowFlags.NoScrollbar |
                    ImGuiWindowFlags.NoScrollWithMouse |
                    ImGuiWindowFlags.NoBringToFrontOnFocus;

    public VideoRenderer(Bufferable<Frame> frameBuffer,  StatisticDisplayUI stat) {
        this.frameBuffer = frameBuffer;
        this.isOp = new ImBoolean(false);
        this.keepAspectRatio = new ImBoolean(true);
        this.keepCentered = new ImBoolean(true);
        this.aspectX = new ImFloat(16);
        this.aspectY = new ImFloat(9);
//        this.texture = new CpuImageTexture();
        this.texture = new FastOpenGLTexture();
        this.converter = new Java2DFrameConverter();
        this.imageBuffer = new OverwritingQueueFrameBuffer<>("", 2);
        this.stat = stat;
    }

    @Override
    public String getName() {
        return LocalizationManager.tr("output.video.name");
    }

    @Override
    public void render() {
        if (!isRendering) {
            isRendering = true;
        }

        if (isOp.get()) {
            renderControls();
        }
        renderVideoOutput();
    }



    private void renderVideoOutput() {
        ImGui.setNextWindowPos(0, 18);
        ImVec2 viewportSize = ImGui.getMainViewport().getSize();
        ImGui.setNextWindowSize(viewportSize.x, viewportSize.y - 18);

        // Safe begin/end block
        if (ImGui.begin("Video Output", VIDEO_WINDOW_FLAGS)) {
            try {
                BufferElement<BufferedImage> element = imageBuffer.get();

                if (element != null) {
                    BufferedImage frame = element.getData();
                    texture.upload(frame);
                }

                // Calculate display dimensions
                ImVec2 contentSize = ImGui.getContentRegionAvail();
                float displayWidth = contentSize.x;
                float displayHeight = contentSize.y;

                if (keepAspectRatio.get()) {
                    float aspectRatio = aspectX.get() / aspectY.get();
                    if (contentSize.x / contentSize.y > aspectRatio) {
                        displayWidth = contentSize.y * aspectRatio;
                    } else {
                        displayHeight = contentSize.x / aspectRatio;
                    }
                }

                if (keepCentered.get()) {
                    ImGui.setCursorPosX((contentSize.x - displayWidth) * 0.5f);
                    ImGui.setCursorPosY((contentSize.y - displayHeight) * 0.5f);
                }
                texture.renderImGui(displayWidth, displayHeight);
            } finally {
                ImGui.end();
            }
        }
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
            BufferElement<Frame> element = frameBuffer.get();
            if (element != null) {
//                try {
                    BufferedImage image = converter.getBufferedImage(element.getData());
                    imageBuffer.put(new BufferElement<>(image));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
//        isRendering = false;
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

    public void resume() {
//        isPlaying = true;
//        reader.resume();
    }
    public void pause() {

    }

    public void stop() {

    }

    public void setPlaybackSpeed(float speed){

    }





}