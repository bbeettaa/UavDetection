package knu.app;

import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.flag.ImGuiConfigFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import knu.app.ui.modules.UIModule;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL33;

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

    private void initImGui() {
        ImGui.createContext();
        ImGuiIO io = ImGui.getIO();
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

            for (UIModule<?> module : uiModules) {
                module.render();
            }

            ImGui.render();
            GL33.glViewport(0, 0, width, height);
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
