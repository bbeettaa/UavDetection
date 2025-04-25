package knu.app.ui.tools;

import imgui.ImGui;
import imgui.flag.ImGuiCond;
import imgui.flag.ImGuiWindowFlags;
import imgui.type.ImBoolean;
import knu.app.ui.LocalizationManager;

public class StatisticDisplayUI implements UIModule<Void> {
    private final ImBoolean windowOpen = new ImBoolean(true);

    private long startMs;
    private float totalMs;
    int frameCount = 0;
    int currentFPS = 0;

//    сделать не фпс а статистику - и в нее включить фпс и состояния буферов

    @Override
    public String getName() {
        return LocalizationManager.tr("fps.name");
    }

    @Override
    public void toggle() {
        windowOpen.set(!windowOpen.get());
    }

    @Override
    public boolean isOpened(){
        return windowOpen.get();
    }


    @Override
    public Void execute(Void o) {
        return null;
    }

    @Override
    public void show() {

    }

    @Override
    public void render() {
        if (!windowOpen.get()) return;

        ImGui.setNextWindowPos(0, 18, ImGuiCond.Always);
        ImGui.setNextWindowBgAlpha(0.35f);

        ImGui.begin(LocalizationManager.tr("fps.menu.name"), windowOpen
                , ImGuiWindowFlags.NoDecoration | ImGuiWindowFlags.AlwaysAutoResize
//                                |
//                        ImGuiWindowFlags.NoFocusOnAppearing |
//                        ImGuiWindowFlags.NoNav |
//                        ImGuiWindowFlags.NoMove |
//                        ImGuiWindowFlags.NoInputs |
//                        ImGuiWindowFlags.NoBringToFrontOnFocus |
//                        ImGuiWindowFlags.NoDocking
        );
        ImGui.text(String.format("%s: %.0f", LocalizationManager.tr("fps.framerate.name"), ImGui.getIO().getFramerate()));
        ImGui.text(String.format("%s: %d", LocalizationManager.tr("fps.menu.name"), currentFPS));
        ImGui.end();

    }

    public void measure(){
        this.startMs = System.nanoTime();
        frameCount++;
    }

    public void calcMs(){
//         totalMs = (System.nanoTime() - startMs) / 1_000_000f;
        if (System.nanoTime() - startMs >= 1_000_000L) {
            currentFPS = frameCount;
            frameCount = 0;
            startMs = System.nanoTime();
            System.out.println("FPS: " + currentFPS);
        }
    }

}

