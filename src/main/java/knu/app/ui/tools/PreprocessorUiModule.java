package knu.app.ui.tools;

import imgui.ImGui;
import imgui.type.ImBoolean;
import imgui.type.ImFloat;
import imgui.type.ImInt;
import knu.app.pipes.MatPipeProcessor;
import knu.app.pipes.PipeProcessor;
import knu.app.preprocessors.*;
import knu.app.ui.LocalizationManager;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.opencv_core.Mat;

import java.util.concurrent.Executor;

public class PreprocessorUiModule implements UIModule<Frame> {
    private final OpenCVFrameConverter.ToMat converter;
    private final ImBoolean isOp = new ImBoolean(false);


    private final ImBoolean grayscalef = new ImBoolean(false);
    private final ImBoolean blurf = new ImBoolean(false);
    private final ImBoolean cannyf = new ImBoolean(false);
//    private final ImBoolean kmeanf = new ImBoolean(false);

    private final ImFloat blurd = new ImFloat(1.0f);
    private final ImInt blurk = new ImInt(5);
    private final ImInt canny1 = new ImInt(25);
    private final ImInt canny2 = new ImInt(35);

//    private final ImInt klusters = new ImInt(3);

    private final GrayColorPreprocessor gray;
    private final BlurPreprocessor blur;
    private final CannyPreprocessor canny;
//    private final KMeansPreprocessor kmean;

    private Mat mat;

    public PreprocessorUiModule(GrayColorPreprocessor gray, BlurPreprocessor blur, CannyPreprocessor canny) {
        this.gray = gray;
        this.blur = blur;
        this.canny = canny;

        this.converter = new OpenCVFrameConverter.ToMat();
    }

    @Override
    public String getName() {
        return LocalizationManager.tr("menu.preprocessor.name");
    }

    @Override
    public void render() {
        if (!isOp.get()) return;
        ImGui.begin(getName(), isOp);
        ImGui.pushItemWidth(150);

        ImGui.sameLine();
        ImGui.checkbox(LocalizationManager.tr("preprocessor.grayscale.name"), grayscalef);
        ImGui.separator();


        ImGui.newLine();
        ImGui.sameLine();
        ImGui.checkbox(LocalizationManager.tr("preprocessor.gaussian.name"), blurf);
        ImGui.inputInt(LocalizationManager.tr("preprocessor.gaussian.kernel"), blurk, 2);
        ImGui.inputFloat(LocalizationManager.tr("preprocessor.gaussian.delta"), blurd, 0.05f, 1f, "%.2f");
        ImGui.separator();

        ImGui.newLine();
        ImGui.sameLine();
        ImGui.checkbox(LocalizationManager.tr("preprocessor.canny.name"), cannyf);
        ImGui.inputInt(LocalizationManager.tr("preprocessor.canny.min"), canny1);
        ImGui.inputInt(LocalizationManager.tr("preprocessor.canny.max"), canny2);

        ImGui.newLine();
        ImGui.sameLine();
//        ImGui.checkbox(LocalizationManager.tr("preprocessor.kmean.name"), kmeanf);
//        ImGui.inputInt(LocalizationManager.tr("preprocessor.kmean.kluster"), klusters);

        ImGui.popItemWidth();
        ImGui.end();
    }

    @Override
    public Frame execute(Frame o) {
        mat = converter.convert(o);

        if (grayscalef.get()) {
            mat = gray.process(mat);
        }
        if (blurf.get()) {
            blur.setD(blurd.get());
            blur.setKernel(blurk.get());
            mat = blur.process(mat);
        }
        if (cannyf.get()) {
            canny.setV(canny1.get());
            canny.setV1(canny2.get());
            mat = canny.process(mat);
        }
//        if (kmeanf.get()) {
//            kmean.setK(klusters.get());
//            mat = kmean.process(mat);
//        }
        return converter.convert(mat);
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
