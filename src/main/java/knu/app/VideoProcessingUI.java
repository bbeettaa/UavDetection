package knu.app;

import imgui.*;
import imgui.flag.*;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import imgui.type.ImBoolean;
import imgui.type.ImInt;
import knu.app.bll.buffers.BufferElement;
import knu.app.bll.buffers.Bufferable;
import knu.app.bll.buffers.OverwritingQueueBlockedFrameBuffer;
import knu.app.bll.preprocessors.BlurPreprocessor;
import knu.app.bll.preprocessors.CannyPreprocessor;
import knu.app.bll.preprocessors.FrameSizerPreprocessor;
import knu.app.bll.preprocessors.GrayColorPreprocessor;
import knu.app.bll.utils.LocalizationManager;
import knu.app.bll.utils.grabbers.PlaybackControlVideoSource;
import knu.app.bll.utils.grabbers.PlaybackFFmpegRawVideoSource;
import knu.app.ui.DockSpaceUIModule;
import knu.app.ui.MainMenuUI;
import knu.app.ui.menu.*;
import knu.app.ui.modules.*;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.opencv_core.Mat;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL33;
import org.opencv.core.Core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.bytedeco.opencv.global.opencv_imgcodecs.IMREAD_GRAYSCALE;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;

public class VideoProcessingUI {
    private final int width = 1280;
    private final int height = 720;
    private long window;
    private final ImGuiImplGlfw imGuiGlfw = new ImGuiImplGlfw();
    private final ImGuiImplGl3 imGuiGl3 = new ImGuiImplGl3();

    private final List<UIModule<?>> uiModules = new ArrayList<>();
    private final ExecutorService executor;
    private final OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat();

    public VideoProcessingUI() {
        int th1 = 4;
        int th2 = Runtime.getRuntime().availableProcessors() - th1;
        executor = Executors.newFixedThreadPool(th1);
        opencv_core.setNumThreads(th2);
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME); // CUDA

        initModules();
    }

    public void initModules() {
        Bufferable<Mat> frameReaderBuffer = new OverwritingQueueBlockedFrameBuffer<>(4);
        Bufferable<Mat> processingBuffer = new OverwritingQueueBlockedFrameBuffer<>(4);
        Bufferable<Mat> frameWriterBuffer = new OverwritingQueueBlockedFrameBuffer<>(4);

        PlaybackControlVideoSource videoSource = new PlaybackFFmpegRawVideoSource();

        PureVideoGrabber videoGrabber = new PureVideoGrabber(videoSource);
        VideoRenderer videoRenderer = new VideoRenderer();

        String imageDescription = "/home/bedu/Документы/drone.jpg";
        Mat image = imread(imageDescription, IMREAD_GRAYSCALE);
        UIModule<Mat> preprocessingUi = new PreprocessorUiModule(new GrayColorPreprocessor(), new BlurPreprocessor(), new CannyPreprocessor(), new FrameSizerPreprocessor(1920, 1080));
        UIModule<Mat> processingUi = new ProcessingModule(image);

        StatisticDisplayUI stat = new StatisticDisplayUI();

        createPipeline(videoGrabber, frameReaderBuffer, frameWriterBuffer, videoRenderer, preprocessingUi, processingBuffer, processingUi);

        // Upper Menu
        MainMenuUI mainMenu = new MainMenuUI(
                new MainMenuSection(LocalizationManager.tr("menu.section.instruments.name"),
                        new ToggleMenuSection(videoGrabber),
                        new ToggleMenuSection(videoRenderer),
                        new ToggleMenuSection(preprocessingUi),
                        new ToggleMenuSection(processingUi)
                ),
                new MainMenuSection(LocalizationManager.tr("menu.section.statistics.name"), new StatisticMenuSection(stat))
        );

        // Rendering
        uiModules.add(new DockSpaceUIModule(VideoRenderer.VEDIO_OUTPUT_ID, ProcessingModule.PROCESSOR_ID, PureVideoGrabber.GRABBER_ID));
        uiModules.add(videoRenderer);
        uiModules.add(videoGrabber);
        uiModules.add(mainMenu);
        uiModules.add(stat);
        uiModules.add(preprocessingUi);
        uiModules.add(processingUi);
    }

    private void createPipeline(PureVideoGrabber videoGrabber, Bufferable<Mat> frameReaderBuffer, Bufferable<Mat> frameWriterBuffer,
                                VideoRenderer videoRenderer, UIModule<Mat> preprocessingUi, Bufferable<Mat> processingBuffer,
                                UIModule<Mat> processingUi) {
        executor.submit(() -> {
            while (!Thread.interrupted()) {
                Frame frame = videoGrabber.execute(null);
                if (frame != null) {
                    frameReaderBuffer.put(new BufferElement<>(converter.convert(frame)));
                }
            }
        });
        executor.submit(() -> {

            while (!Thread.currentThread().isInterrupted()) {
                BufferElement<Mat> element = frameWriterBuffer.get();
                if (element != null) {
                    Frame fr = converter.convert(element.getData());
                    videoRenderer.execute(fr);
//                    canvas.showImage(converter.convert(element.getData()));
                }
            }
        });


        executor.submit(() -> {
            while (!Thread.interrupted()) {
                try {
                    BufferElement<Mat> o = frameReaderBuffer.get();
                    if (o != null) {
                        Mat f = o.getData();
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
                    BufferElement<Mat> o = processingBuffer.get();
                    if (o != null && o.getData() != null) {
                        Mat f = new Mat();
                        o.getData().copyTo(f);
                        f = processingUi.execute(f);
                        frameWriterBuffer.put(new BufferElement<>(f));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void run() throws IOException {
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
        io.setIniFilename(null);
        io.addConfigFlags(ImGuiConfigFlags.DockingEnable);

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

    public static void main(String[] args) throws IOException {
        new VideoProcessingUI().run();
    }
}
