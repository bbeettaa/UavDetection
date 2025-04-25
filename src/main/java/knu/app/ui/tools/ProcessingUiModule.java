package knu.app.ui.tools;

import imgui.ImGui;
import imgui.type.ImBoolean;
import imgui.type.ImFloat;
import imgui.type.ImInt;
import knu.app.buffers.BufferElement;
import knu.app.buffers.Bufferable;
import knu.app.preprocessors.KMeansPreprocessor;
import knu.app.ui.LocalizationManager;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.opencv_core.Mat;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

public class ProcessingUiModule implements UIModule<Frame> {
    private final OpenCVFrameConverter.ToMat converter;
    private final Bufferable<Frame> buffer;
    private final AtomicInteger thCounter;
    private final Executor executor;
    private final ImBoolean isOp = new ImBoolean(false);

    private int[] threadCount;

    private final ImBoolean grayscalef = new ImBoolean(false);
    private final ImBoolean blurf = new ImBoolean(false);
    private final ImBoolean cannyf = new ImBoolean(false);
    private final ImBoolean kmeanf = new ImBoolean(false);

    private final ImFloat blurd = new ImFloat(1.0f);
    private final ImInt blurk = new ImInt(5);
    private final ImInt canny1 = new ImInt(25);
    private final ImInt canny2 = new ImInt(35);

    private final ImInt klusters = new ImInt(3);

    private final KMeansPreprocessor kmean;

    private Mat mat;


    public ProcessingUiModule(KMeansPreprocessor kmean, Executor executor, Bufferable<Frame> buffer) {
        this.kmean = kmean;

        this.buffer = buffer;
        this.executor = executor;
        this.converter = new OpenCVFrameConverter.ToMat();
        this.threadCount = new int[]{2};
        this.thCounter = new AtomicInteger(0);
    }

    @Override
    public String getName() {
        return LocalizationManager.tr("menu.processor.name");
    }

    @Override
    public void render() {
        if (!isOp.get()) return;
        ImGui.begin(getName(), isOp);
        ImGui.pushItemWidth(150);
        ImGui.sliderInt(LocalizationManager.tr("processor.thread.count"), threadCount, 1, 10);

        ImGui.pushItemWidth(150);

        ImGui.newLine();
        ImGui.sameLine();
        ImGui.checkbox(LocalizationManager.tr("processor.kmean.name"), kmeanf);
        ImGui.inputInt(LocalizationManager.tr("processor.kmean.kluster"), klusters);

        ImGui.popItemWidth();
        ImGui.end();
    }

    @Override
    public Frame execute(Frame o) {
        mat = converter.convert(o);
//        for (int i = thCounter.get(); i < threadCount[0]; i++) {
//            executor.execute(createTask(mat));
        mat = task(mat);
//        }
//        BufferElement<Frame> f = buffer.get();
//        if (f == null) return null;
//        return f.getData();
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

    private Runnable createTask(Mat o) {
        return () -> {
            thCounter.incrementAndGet();
            Mat m = o;
            if (kmeanf.get()) {
                kmean.setK(klusters.get());
                m = kmean.process(m);
            }


            buffer.put(new BufferElement<>(converter.convert(m)));
            thCounter.decrementAndGet();
        };
    }

    private Mat task(Mat o) {
        if (kmeanf.get()) {
            kmean.setK(klusters.get());
            o = kmean.process(o);
        }

        return o;
    }

}
