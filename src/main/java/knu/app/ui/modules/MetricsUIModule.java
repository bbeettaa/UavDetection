package knu.app.ui.modules;

import imgui.ImGui;
import imgui.type.ImBoolean;
import java.util.concurrent.CompletableFuture;
import knu.app.bll.utils.EvaluationMetrics;
import knu.app.bll.utils.MetricsEvaluator;
import org.bytedeco.opencv.opencv_core.Rect;

import javax.swing.*;
import java.util.List;

public class MetricsUIModule implements UIModule<MetricsUIModule.FrameRecord> {
    private final ImBoolean isOp = new ImBoolean(false);
    private final MetricsEvaluator evaluator;
    String loadedFile;

    public static class FrameRecord {
        long timeStamp;
        List<Rect> roi;
    }

    public MetricsUIModule(){
        evaluator = new MetricsEvaluator(loadedFile, 0);
    }

    @Override
    public String getName() {
        return "Metrics Evaluator";
    }

    @Override
    public void render() {
        if (ImGui.begin("Metrics Evaluator")) {
            if (ImGui.button("Load GT File")) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Load GT File");
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    loadedFile = fileChooser.getSelectedFile().getAbsolutePath();
                    evaluator.loadGroundTruth(loadedFile);
                }
            }

            ImGui.text("Loaded File: " + (loadedFile != null ? loadedFile : "None"));
            ImGui.separator();
            if (evaluator != null) {
                EvaluationMetrics metrics = evaluator.getMetrics();

                ImGui.text("Precision:  " + String.format("%.2f", metrics.getPrecision()));
                ImGui.text("Recall:     " + String.format("%.2f", metrics.getRecall()));

//                ImGui.text("MOTA:       " + String.format("%.2f", metrics.get));
//                ImGui.text("ID Switches:" + metrics.idSwitches);
//                ImGui.text("Frames:     " + metrics.totalFrames);

                ImGui.text("TP:         " + metrics.getTP());
                ImGui.text("FP:         " + metrics.getFP());
                ImGui.text("FN:         " + metrics.getFN());

                if (ImGui.button("Reset")) {
                    evaluator.reset();
                }
            }

        }
        ImGui.end();
    }

    @Override
    public MetricsUIModule.FrameRecord execute(MetricsUIModule.FrameRecord o) {
        evaluator.evaluate(o.timeStamp, o.roi);
        return null;
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

    public MetricsEvaluator getEvaluator() {
        return evaluator;
    }
}
