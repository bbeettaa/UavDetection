//package knu.app.ui.tools;
//
//import imgui.ImGui;
//import imgui.type.ImBoolean;
//import knu.app.pipes.PipeProcessor;
//import knu.app.pipes.Pipeable;
//import knu.app.ui.LocalizationManager;
//import org.bytedeco.javacv.Frame;
//import org.bytedeco.opencv.opencv_core.Mat;
//
//public class PreprocessingUiModule implements UIModule<Frame> {
//    private final PipeProcessor<Mat>[] processors;
//    private final Pipeable<Frame> pipe;
//    private final ImBoolean isOp = new ImBoolean(false);
//    private final ImBoolean[] processorStates;
//
//    public PreprocessingUiModule(Pipeable<Frame> pipe) {
//        this.pipe = pipe;
//        this.processors = pipe.getProcessors();
//        this.processorStates = new ImBoolean[processors.length];
//
//        for (int i = 0; i < processors.length; i++) {
//            processorStates[i] = new ImBoolean(processors[i].isRunning());
//        }
//    }
//
//    @Override
//    public String getName() {
//        return LocalizationManager.tr("menu.preprocessor.name");
//    }
//
//    @Override
//    public void render() {
//        if (!isOp.get()) return;
//
//        ImGui.begin(LocalizationManager.tr("video.playback.name"), isOp);
//
//        for (int i = 0; i < processors.length; i++) {
//            boolean prevState = processorStates[i].get();
//
//            if (ImGui.checkbox(processors[i].getName(), processorStates[i])) processors[i].setRunning(processorStates[i].get());
//
//            if (prevState != processors[i].isRunning()) processorStates[i].set(processors[i].isRunning());
//
//        }
//
//        ImGui.end();
//    }
//
//    @Override
//    public Frame execute(Frame o) {
//        return null;
//    }
//
//    @Override
//    public void show() {
//        isOp.set(true);
//    }
//
//    @Override
//    public void toggle() {
//        isOp.set(!isOp.get());
//    }
//
//    @Override
//    public boolean isOpened() {
//        return isOp.get();
//    }
//}