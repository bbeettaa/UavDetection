package knu.app.bll.utils;

import imgui.ImFontAtlas;
import imgui.ImFontConfig;
import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.flag.ImGuiConfigFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import knu.app.ui.modules.UIModule;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL33;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class VideoProcessingUI {
    private final int width = 1280;
    private final int height = 720;
    private long window;
    private final ImGuiImplGlfw imGuiGlfw = new ImGuiImplGlfw();
    private final ImGuiImplGl3 imGuiGl3 = new ImGuiImplGl3();
    private final List<UIModule<?>> uiModules;

    public VideoProcessingUI(List<UIModule<?>> uiModules) {
        this.uiModules = uiModules;
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
        GLFW.glfwSwapInterval(1);
        GL.createCapabilities();
    }

    private static final short[] ICON_RANGE = new short[] {
            (short) 0xF000, (short) 0xF8FF,
            0
    };

    private void initImGui() {
        ImGui.createContext();

        ImGuiIO io = ImGui.getIO();
        ImFontAtlas fontAtlas = io.getFonts();

        fontAtlas.addFontDefault();

        try {
            String fontPath = extractFontToTemp("/Font Awesome 7 Free-Solid-900.otf");

            ImFontConfig iconConfig = new ImFontConfig();
            iconConfig.setMergeMode(true);
            iconConfig.setPixelSnapH(true);
            iconConfig.setGlyphOffset(0.0f, 2.0f);

            fontAtlas.addFontFromFileTTF(fontPath, 14.0f, iconConfig, ICON_RANGE);
            fontAtlas.build();

            iconConfig.destroy();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load icon font", e);
        }

        io.setIniFilename(null);
        io.addConfigFlags(ImGuiConfigFlags.DockingEnable);
        io.setWantCaptureKeyboard(true);

        imGuiGlfw.init(window, true);
        imGuiGl3.init("#version 330 core");
    }

    private String extractFontToTemp(String resourcePath) throws IOException {
        try (InputStream in = getClass().getResourceAsStream(resourcePath)) {
            if (in == null) {
                throw new FileNotFoundException("Resource not found: " + resourcePath);
            }

            Path temp = Files.createTempFile("imgui-font-", ".otf");
            Files.copy(in, temp, StandardCopyOption.REPLACE_EXISTING);
            temp.toFile().deleteOnExit();
            return temp.toAbsolutePath().toString();
        }
    }

    private void renderLoop() {
        while (!GLFW.glfwWindowShouldClose(window)) {
            GLFW.glfwPollEvents();
            imGuiGlfw.newFrame();
            ImGui.newFrame();

            for (UIModule<?> module : uiModules) {
                module.render();
            }

            ImGui.render();

            int[] w = new int[1];
            int[] h = new int[1];
            GLFW.glfwGetFramebufferSize(window, w, h);
            GL33.glViewport(0, 0, w[0], h[0]);

//            GL33.glViewport(0, 0, width, height);
            GL33.glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
            GL33.glClear(GL33.GL_COLOR_BUFFER_BIT);
            imGuiGl3.renderDrawData(ImGui.getDrawData());

            if (ImGui.getIO().hasConfigFlags(ImGuiConfigFlags.ViewportsEnable)) {
                long backupWindow = GLFW.glfwGetCurrentContext();
                ImGui.updatePlatformWindows();
                ImGui.renderPlatformWindowsDefault();
                GLFW.glfwMakeContextCurrent(backupWindow);
            }

            GLFW.glfwSwapBuffers(window);
        }
    }

    private void cleanup() {
        imGuiGl3.dispose();
        imGuiGlfw.dispose();
        ImGui.destroyContext();
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }
}
