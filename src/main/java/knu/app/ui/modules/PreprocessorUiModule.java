package knu.app.ui.modules;

import imgui.ImGui;
import imgui.type.ImBoolean;
import imgui.type.ImFloat;
import imgui.type.ImInt;
import java.util.concurrent.CompletableFuture;
import knu.app.bll.preprocessors.*;
import knu.app.bll.utils.LocalizationManager;
import knu.app.bll.utils.MatWrapper;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;

import java.util.Arrays;

public class PreprocessorUiModule implements UIModule<MatWrapper> {
    private final ImBoolean isOp = new ImBoolean(false);

    private final ImBoolean grayscalef = new ImBoolean(false);
    private final ImBoolean blurf = new ImBoolean(false);
    private final ImBoolean cannyf = new ImBoolean(false);
    private final ImBoolean sizerf = new ImBoolean(false);
    private final ImBoolean stabf = new ImBoolean(false);
    private final ImBoolean featuref = new ImBoolean(false);
    private final ImBoolean smoothingf = new ImBoolean(false);

    private final ImFloat blurd = new ImFloat(1.0f);
    private final ImInt blurk = new ImInt(5);
    private final ImInt canny1 = new ImInt(25);
    private final ImInt canny2 = new ImInt(35);
    private final ImInt swidth ;
    private final ImInt sheight;
    private final ImInt reschoise = new ImInt(0);

    private final GrayColorPreprocessor gray;
    private final BlurPreprocessor blur;
    private final CannyPreprocessor canny;
    private final FrameSizerPreprocessor sizer;

    private final FramePreprocessor stabilization;
    private final FeatureBasedStabilizer featureBasedStabilizer;

    private final Resolution[] resolutions;

    public PreprocessorUiModule(GrayColorPreprocessor gray, BlurPreprocessor blur, CannyPreprocessor canny,
                                FrameSizerPreprocessor sizer, StabilizationFramePreprocessor stabilization,
                                FeatureBasedStabilizer featureBasedStabilizer) {
        this.gray = gray;
        this.blur = blur;
        this.canny = canny;
        this.sizer = sizer;
        this.stabilization = stabilization;
        this.featureBasedStabilizer = featureBasedStabilizer;

        this.swidth = new ImInt(sizer.getSize().width());
        this.sheight = new ImInt(sizer.getSize().height());

        resolutions = new Resolution[]{
                new Resolution("1920x1080", 1920, 1080),
                new Resolution("1280x720", 1280, 720),
                new Resolution("640x480", 640, 480)
        };
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
        ImGui.sameLine();
        ImGui.inputInt(LocalizationManager.tr("preprocessor.gaussian.kernel"), blurk, 2);
        ImGui.sameLine();
        ImGui.inputFloat(LocalizationManager.tr("preprocessor.gaussian.delta"), blurd, 0.05f, 1f, "%.2f");
        ImGui.separator();

//        ImGui.newLine();
//        ImGui.sameLine();
//        ImGui.checkbox(LocalizationManager.tr("preprocessor.canny.name"), cannyf);
//        ImGui.sameLine();
//        ImGui.inputInt(LocalizationManager.tr("preprocessor.canny.min"), canny1);
//        ImGui.sameLine();
//        ImGui.inputInt(LocalizationManager.tr("preprocessor.canny.max"), canny2);

        ImGui.newLine();
        ImGui.checkbox(LocalizationManager.tr("preprocessor.stabilization.name"), stabf);

        ImGui.newLine();
        ImGui.checkbox(("featuref"), featuref);

        ImGui.newLine();

        ImGui.separator();
        ImGui.newLine();
        ImGui.sameLine();
        ImGui.checkbox(LocalizationManager.tr("preprocessor.sizer.name"), sizerf);
        ImGui.sameLine();
        if(ImGui.combo(LocalizationManager.tr("frame.resolution.name"), reschoise, Arrays.stream(resolutions).map(Resolution::toString).toArray(String[]::new) )){
            Resolution r = resolutions[reschoise.get()];
            sizer.setSize(new Size(r.w, r.h));
            swidth.set(r.w);
            sheight.set(r.h);
        }
        if(ImGui.inputInt(LocalizationManager.tr("frame.width.name"), swidth, 20, 100)){
            sizer.setSize(new Size(swidth.get(), sheight.get()));
        }
        ImGui.sameLine();
        if(ImGui.inputInt(LocalizationManager.tr("frame.height.name"), sheight, 10, 100)){
            sizer.setSize(new Size(swidth.get(), sheight.get()));
        }

        ImGui.popItemWidth();
        ImGui.end();
    }

    @Override
    public MatWrapper execute(MatWrapper mat) {
        Mat m = mat.mat();
        if (sizerf.get())
            sizer.process(m);

        if (grayscalef.get())
             m = gray.process(m);

        if (blurf.get()) {
            blur.setD(blurd.get());
            blur.setKernel(blurk.get());
            m = blur.process(m);
        }
        if (cannyf.get()) {
            canny.setV(canny1.get());
            canny.setV1(canny2.get());
            m =  canny.process(m);
        }
        if (stabf.get())
            m = stabilization.process(m);

        if (featuref.get())
            m = featureBasedStabilizer.process(m);

        return new MatWrapper(mat.frameIndex(), m);
//        if (sizerf.get()) mat = sizer.process(mat.mat());
//
//        if (grayscalef.get()) mat = gray.process(mat);
//
//        if (blurf.get()) {
//            blur.setD(blurd.get());
//            blur.setKernel(blurk.get());
//            mat = blur.process(mat);
//        }
//        if (cannyf.get()) {
//            canny.setV(canny1.get());
//            canny.setV1(canny2.get());
//            mat = canny.process(mat);
//        }
//        if (stabf.get()) mat = stabilization.process(mat);
//
//        if (featuref.get()) mat = featureBasedStabilizer.process(mat);
//
//        return mat;
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


    private record Resolution(String n, int w, int h) {

        @Override
            public String toString() {
                return n;
            }
        }
}
