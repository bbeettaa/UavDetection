package knu.app;

public class Main {
    public static void main(String[] args) {
        PipelineInitializer initializer = new PipelineInitializer(
                "/home/bedu/Документы/drone.jpg",
                "src/main/resources/HOGDescriptorEsp2");
        VideoProcessingUI ui = new VideoProcessingUI(initializer.getUiModules());
        ui.run();
    }
}
