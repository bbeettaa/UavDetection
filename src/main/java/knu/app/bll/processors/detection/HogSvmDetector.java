package knu.app.bll.processors.detection;


import knu.app.bll.utils.hog.HogSvmDetectorConfig;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_objdetect.HOGDescriptor;

import java.util.ArrayList;
import java.util.List;

import static org.bytedeco.opencv.global.opencv_dnn.NMSBoxes;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGR2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;


public class HogSvmDetector implements ObjectDetector {
    private final HOGDescriptor hog;
    private final HogSvmDetectorConfig c;

    public HogSvmDetector(HOGDescriptor hog, HogSvmDetectorConfig c) {
        this.hog = hog;
        this.c = c;
    }


    @Override
    public DetectionResult detect(Mat frame) {
        Mat gray = new Mat();
        cvtColor(frame, gray, COLOR_BGR2GRAY);

        RectVector detections = new RectVector();
        DoublePointer weights = new DoublePointer();

        // 1 detect rois
        hog.detectMultiScale(gray, detections, weights, c.getHitThreshold(), c.getWinStride(), c.getPadding(),
                c.getScale(), c.getFinalThreshold(), c.isUseMeanShiftGrouping());

        // 2 group
        hog.groupRectangles(detections, weights, c.getGroupThreshold(), c.getEps());

        // 3 Конвертируем назад в структуры для NMS
        RectVector groupedBoxes = new RectVector(detections.size());
        FloatPointer groupedWeights = new FloatPointer(weights.limit());
        for (int i = 0; i < detections.size(); i++) {
            groupedBoxes.put(i, detections.get(i));
            groupedWeights.put(i, (float) weights.get(i));
        }

        IntPointer indices = new IntPointer(weights.limit());
        NMSBoxes(groupedBoxes, groupedWeights, c.getScoreThreshold(), c.getNmsThreshold(), indices);


        // 6. Считываем результаты NMS
        List<Rect> filtered = new ArrayList<>();
        List<Double> scores = new ArrayList<>();
        int count = (int) indices.limit();
        for (int i = 0; i < count; i++) {
            int idx = indices.get(i);
            double score = groupedWeights.get(idx);
            if (score >= c.getWeightThreshold()) {
                filtered.add(groupedBoxes.get(idx));
                scores.add(score);
            }
        }


        indices.close();
        detections.close();
        weights.close();

        gray.release();
        return new

                DetectionResult(filtered, scores);
    }

    public HOGDescriptor getHog() {
        return hog;
    }

    public HogSvmDetectorConfig getConfig() {
        return c;
    }


}