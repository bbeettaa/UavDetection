package knu.app;

public class Main {
    public static void main(String[] args) {
        PipelineInitializer initializer = new PipelineInitializer();
        VideoProcessingUI ui = new VideoProcessingUI(initializer.getUiModules());
        ui.run();
    }
}
