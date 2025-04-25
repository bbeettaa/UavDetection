package knu.app;

import imgui.*;
import imgui.flag.ImGuiConfigFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import knu.app.buffers.BufferElement;
import knu.app.buffers.Bufferable;
import knu.app.buffers.OverwritingQueueBlockedFrameBuffer;
import knu.app.buffers.OverwritingQueueFrameBuffer;
import knu.app.preprocessors.BlurPreprocessor;
import knu.app.preprocessors.CannyPreprocessor;
import knu.app.preprocessors.GrayColorPreprocessor;
import knu.app.preprocessors.KMeansPreprocessor;
import knu.app.ui.LocalizationManager;
import knu.app.ui.MainMenuUI;
import knu.app.ui.menu.*;
import knu.app.ui.tools.*;
//import knu.app.ui.menu.ToolsMenuSection;
//import knu.app.ui.tools.VideoOutputUI;
import knu.app.utils.video.NativeFFmpegVideoSource;
import knu.app.utils.video.VideoSource;
import org.bytedeco.javacv.Frame;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL33;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VideoProcessingUI {
    private final int width = 1280;
    private final int height = 720;
    private long window;
    private final ImGuiImplGlfw imGuiGlfw = new ImGuiImplGlfw();
    private final ImGuiImplGl3 imGuiGl3 = new ImGuiImplGl3();

    private final List<UIModule<?>> uiModules = new ArrayList<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(8);

    public VideoProcessingUI() {
        initModules();
    }

    public void initModules() {
        Bufferable<Frame> frameReaderBuffer = new OverwritingQueueBlockedFrameBuffer<>("frameReaderBuffer", 100);
        Bufferable<Frame> processingBuffer = new OverwritingQueueBlockedFrameBuffer<>("processingBuffer", 100);
        Bufferable<Frame> frameWriterBuffer = new OverwritingQueueBlockedFrameBuffer<>("frameWriterBuffer", 100);

        StatisticDisplayUI stat = new StatisticDisplayUI();

        VideoSource videoSource = new NativeFFmpegVideoSource();
        PureVideoGrabber videoGrabber = new PureVideoGrabber(videoSource, frameReaderBuffer, stat);
        VideoRenderer videoRenderer = new VideoRenderer(frameWriterBuffer, stat);

//        PlaybackControlVideoSource videoSource = new NativePlaybackFFmpegVideoSource();
//        VideoGrabber<Frame> videoGrabber = new VideoGrabber<>(videoSource, frameReaderBuffer, executor);
//        UIModule<Void> playbackUI = new PlaybackControlUI(videoGrabber, videoRenderer);

        UIModule<Frame> preprocessingUi = new PreprocessorUiModule(new GrayColorPreprocessor(), new BlurPreprocessor(), new CannyPreprocessor());
        UIModule<Frame> processingUi = new ProcessingUiModule(new KMeansPreprocessor(), executor, processingBuffer);


        executor.submit(() -> {
            Frame frame;
            while (!Thread.interrupted()) {
                frame = videoGrabber.execute(null);
                if (frame != null) {
                    frameReaderBuffer.put(new BufferElement<>(frame));
                }
            }
        });
        executor.submit(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                videoRenderer.execute(null);
            }
        });


        executor.submit(() -> {
            while (!Thread.interrupted()) {
                try {
                    BufferElement<Frame> o = frameReaderBuffer.get();
                    if (o != null) {
                        Frame f = o.getData();
                        f = preprocessingUi.execute(f);
                        processingBuffer.put(new BufferElement<>(f));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        executor.submit(() -> {
            while (!Thread.interrupted()) {
                try {
                    BufferElement<Frame> o = processingBuffer.get();
                    if (o != null) {
                        Frame f = o.getData();
                        f = processingUi.execute(f);
                        frameWriterBuffer.put(new BufferElement<>(f));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        // Upper Menu
        MainMenuUI mainMenu = new MainMenuUI(
                new MainMenuSection(LocalizationManager.tr("menu.section.instruments.name"),
                        new ToggleMenuSection(videoGrabber),
                        new ToggleMenuSection(videoRenderer),
//                        new ToggleMenuSection(playbackUI),
                        new ToggleMenuSection(preprocessingUi),
                        new ToggleMenuSection(processingUi)
                ),
                new MainMenuSection(LocalizationManager.tr("menu.section.statistics.name"), new StatisticMenuSection(stat))
        );

        // Rendering
        uiModules.add(videoRenderer);
        uiModules.add(videoGrabber);
        uiModules.add(mainMenu);
        uiModules.add(stat);
//        uiModules.add(playbackUI);
        uiModules.add(preprocessingUi);
        uiModules.add(processingUi);
    }

    public void run() {
        initGLFW();
        initImGui();
        renderLoop();
        cleanup();
    }

    private void initGLFW() {
        if (!GLFW.glfwInit()) throw new IllegalStateException("GLFW init failed");

        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);

        window = GLFW.glfwCreateWindow(width, height, "Video UI", 0, 0);
        if (window == 0) throw new RuntimeException("GLFW window creation failed");

        GLFW.glfwMakeContextCurrent(window);
        GLFW.glfwSwapInterval(1); // V-Sync
        GL.createCapabilities();
    }

    private void initImGui() {
        ImGui.createContext();
        final ImGuiIO io = ImGui.getIO();
        io.setIniFilename(null); // Без .ini-файлу
//        io.addConfigFlags(ImGuiConfigFlags.DockingEnable);
//        io.addConfigFlags(ImGuiConfigFlags.ViewportsEnable);

        imGuiGlfw.init(window, false);
        imGuiGl3.init("#version 330 core");
    }

    private void renderLoop() {
        while (!GLFW.glfwWindowShouldClose(window)) {
            GLFW.glfwPollEvents();
            imGuiGlfw.newFrame();
            ImGui.newFrame();

            showGui();
            ImGui.render();
            processing();


            GL33.glViewport(0, 0, width, height);
            GL33.glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
            GL33.glClear(GL33.GL_COLOR_BUFFER_BIT);

            imGuiGl3.renderDrawData(ImGui.getDrawData());

            if (ImGui.getIO().hasConfigFlags(ImGuiConfigFlags.ViewportsEnable)) {
                final long backupWindow = GLFW.glfwGetCurrentContext();
                ImGui.updatePlatformWindows();
                ImGui.renderPlatformWindowsDefault();
                GLFW.glfwMakeContextCurrent(backupWindow);
            }

            GLFW.glfwSwapBuffers(window);
        }
    }

    private void showGui() {
        for (UIModule<?> module : uiModules) {
            module.render();
        }
    }

    private void cleanup() {
        imGuiGl3.dispose();
        imGuiGlfw.dispose();
        ImGui.destroyContext();
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }


    private void processing() {
//        try {
//            BufferElement<Frame> o = frameReaderBuffer.get();
//            if (o != null) {
//                Frame f = o.getData();
//                f = preprocessingUi.execute(f);
//                f = processingUi.execute(f);
//                frameWriterBuffer.put(new BufferElement<>(f));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        executor.submit(() -> {
//            while (!Thread.interrupted()) {
//                try {
//                    BufferElement<Frame> o = frameReaderBuffer.get();
//                    if (o != null) {
//                        Frame f = o.getData();
//                        f = preprocessingUi.execute(f);
//                        f = processingUi.execute(f);
//                        frameWriterBuffer.put(new BufferElement<>(f));
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
    }

    public static void main(String[] args) {
        new VideoProcessingUI().run();
    }
}
