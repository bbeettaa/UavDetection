package knu.app.bll.utils;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import knu.app.bll.algorithms.IouComputer;
import org.bytedeco.opencv.opencv_core.Rect;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.stream.IntStream;


public class MetricsEvaluator {
    private Map<Long, List<Rect>> groundTruthByFrame;
    private final double iouThreshold;
    private EvaluationMetrics currentMetrics;

    public MetricsEvaluator(String jsonPath, double iouThreshold) {
        this.iouThreshold = iouThreshold;
        this.currentMetrics = new EvaluationMetrics();
    }

    public void loadGroundTruth(String jsonPath) {
        Map<Long, List<Rect>> gtMap = new HashMap<>();
        try (Reader reader = new FileReader(jsonPath)) {
            Gson gson = new Gson();
            JsonObject json = gson.fromJson(reader, JsonObject.class);
            for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
                long frameId = Integer.parseInt(entry.getKey());
                JsonArray arr = entry.getValue().getAsJsonArray();
                List<Rect> boxes = new ArrayList<>();
                for (JsonElement el : arr) {
                    JsonObject obj = el.getAsJsonObject();
                    int x = obj.get("x").getAsInt();
                    int y = obj.get("y").getAsInt();
                    int w = obj.get("width").getAsInt();
                    int h = obj.get("height").getAsInt();
                    boxes.add(new Rect(x, y, w, h));
                }
                gtMap.put(frameId, boxes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        groundTruthByFrame = gtMap;
    }

    public void evaluate(long frameIndex, List<Rect> predictions) {
        if (groundTruthByFrame == null) {
            return;
        }

        List<Rect> gtBoxes = groundTruthByFrame.getOrDefault(frameIndex, Collections.emptyList());

        boolean[] matchedGT = new boolean[gtBoxes.size()];
        boolean[] matchedPred = new boolean[predictions.size()];

        int truePositives = 0;

        for (int i = 0; i < predictions.size(); i++) {
            Rect pred = predictions.get(i);
            int bestMatchIndex = findBestMatch(pred, gtBoxes, matchedGT);

            if (bestMatchIndex != -1) {
                matchedGT[bestMatchIndex] = true;
                matchedPred[i] = true;
                truePositives++;
            }
        }

        int falsePositives = (int) IntStream.range(0, matchedPred.length)
                .filter(i -> !matchedPred[i])
                .count();

        int falseNegatives = (int) IntStream.range(0, matchedGT.length)
                .filter(i -> !matchedGT[i])
                .count();

        currentMetrics.update(truePositives, falsePositives, falseNegatives);
    }

    private int findBestMatch(Rect pred, List<Rect> gtBoxes, boolean[] matchedGT) {
        double bestIoU = 0.0;
        int bestIndex = -1;

        for (int j = 0; j < gtBoxes.size(); j++) {
            if (matchedGT[j]) continue;

            Rect gt = gtBoxes.get(j);
            double iou = IouComputer.computeIoU(pred, gt);

            if (iou >= iouThreshold && iou > bestIoU) {
                bestIoU = iou;
                bestIndex = j;
            }
        }

        return bestIndex;
    }


    public void reset(){
        currentMetrics = new EvaluationMetrics();
    }

    public EvaluationMetrics getMetrics() {
        return currentMetrics;
    }
}

