package knu.app.ui.modules;

import imgui.ImGui;
import imgui.ImVec2;
import imgui.flag.ImGuiWindowFlags;
import imgui.type.ImBoolean;
import imgui.type.ImFloat;
import java.util.concurrent.CompletableFuture;
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

public class VideoRenderer implements UIModule<Frame> {
    private final UiTexturable texture;
    private final Java2DFrameConverter converter;

    private final ImBoolean keepAspectRatio;
    private final ImBoolean keepCentered;
    private final ImFloat aspectX;
    private final ImFloat aspectY;
    private final ImBoolean isOp;

    private final BufferableQueue<BufferedImage> imageBuffer;
    private volatile boolean isRendering = false;


    private static final int VIDEO_WINDOW_FLAGS =
            ImGuiWindowFlags.NoDecoration |
                    ImGuiWindowFlags.NoMove |
                    ImGuiWindowFlags.NoScrollbar |
                    ImGuiWindowFlags.NoScrollWithMouse |
                    ImGuiWindowFlags.NoBringToFrontOnFocus;

    public static final String VIDEORENDERE_ID = LocalizationManager.tr("output.video.name");
public static final String VEDIO_OUTPUT_ID = "Video Output";

    public VideoRenderer( ) {
        this.isOp = new ImBoolean(false);
        this.keepAspectRatio = new ImBoolean(true);
        this.keepCentered = new ImBoolean(true);
        this.aspectX = new ImFloat(16);
        this.aspectY = new ImFloat(9);
//        this.texture = new CpuImageTexture();
        this.texture = new FastOpenGLTexture();
        this.converter = new Java2DFrameConverter();
        this.imageBuffer = new OverwritingQueueFrameBuffer<>( 2);
    }

    @Override
    public String getName() {
        return VIDEORENDERE_ID;
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

        if (ImGui.begin(VEDIO_OUTPUT_ID, VIDEO_WINDOW_FLAGS)) {
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
                    ImGui.setCursorPosY((contentSize.y - displayHeight) * 0.5f + 18);
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




}