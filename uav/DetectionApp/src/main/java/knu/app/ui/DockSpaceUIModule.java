package knu.app.ui;

import imgui.ImGui;
import imgui.ImGuiViewport;
import imgui.ImVec2;
import imgui.flag.ImGuiDir;
import imgui.flag.ImGuiStyleVar;
import imgui.flag.ImGuiWindowFlags;
import imgui.type.ImBoolean;
import imgui.type.ImInt;
import java.util.concurrent.CompletableFuture;
import knu.app.ui.modules.UIModule;


public class DockSpaceUIModule implements UIModule<Object> {
    private boolean firstRun = true;
    private  int mainDockID ;
    private final String mainWindowId;
    private final String leftWindowId;
    private final String bottomWindowId;

    public DockSpaceUIModule(String mainWindowId, String leftWindowId, String bottomWindowId) {
        this.mainWindowId = mainWindowId;
        this.leftWindowId = leftWindowId;
        this.bottomWindowId = bottomWindowId;
    }

    @Override
    public String getName() {
        return "DockSpace";
    }

    @Override
    public void render() {
        ImGuiViewport viewport = ImGui.getMainViewport();
        ImGui.setNextWindowPos(viewport.getPosX(), viewport.getPosY());
        ImGui.setNextWindowSize(viewport.getSizeX(), viewport.getSizeY());
        ImGui.setNextWindowViewport(viewport.getID());

        ImGui.pushStyleVar(ImGuiStyleVar.WindowRounding, 0.0f);
        ImGui.pushStyleVar(ImGuiStyleVar.WindowBorderSize, 0.0f);

        ImGui.setNextWindowPos(0, 18);
        ImVec2 viewportSize = ImGui.getMainViewport().getSize();
        ImGui.setNextWindowSize(viewportSize.x, viewportSize.y - 18);

        ImGui.begin("MainDockSpace", new ImBoolean(true),
                ImGuiWindowFlags.NoTitleBar | ImGuiWindowFlags.NoCollapse |
                        ImGuiWindowFlags.NoResize | ImGuiWindowFlags.NoMove |
                        ImGuiWindowFlags.NoBringToFrontOnFocus | ImGuiWindowFlags.NoNavFocus);

        mainDockID = ImGui.getID("MainDockSpace");
        ImGui.dockSpace(mainDockID);


        ImGui.popStyleVar(2);

        if (firstRun) {
            firstRun = false;

            int dockspaceID = ImGui.getID(mainWindowId);
            ImGui.setNextWindowDockID(mainDockID, dockspaceID);

            ImInt leftNode  = new ImInt();
            ImInt rightNode = new ImInt();

            int leftID =  imgui.internal.ImGui.dockBuilderSplitNode(
                    mainDockID,
                    ImGuiDir.Left,
                    0.25f,
                    leftNode,
                    rightNode
            );

            ImInt bottomNode = new ImInt();
            ImInt topNode    = new ImInt();
            int bottomID =  imgui.internal.ImGui.dockBuilderSplitNode(
                    rightNode.get(),
                    ImGuiDir.Down,
                    0.25f,
                    bottomNode,
                    topNode
            );

            imgui.internal.ImGui.dockBuilderDockWindow(leftWindowId, leftID);
            imgui.internal.ImGui.dockBuilderDockWindow(bottomWindowId, bottomID);

        }

        ImGui.end();
    }

    @Override
    public Object execute(Object o) {
        return null;
    }

    @Override
    public void show() {

    }

    @Override
    public void toggle() {

    }

    @Override
    public boolean isOpened() {
        return false;
    }
}

