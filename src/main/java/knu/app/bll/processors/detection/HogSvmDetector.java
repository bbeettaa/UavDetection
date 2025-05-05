package knu.app.bll.processors.detection;


import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_objdetect.HOGDescriptor;

import java.util.ArrayList;
import java.util.List;

import static org.bytedeco.opencv.global.opencv_dnn.NMSBoxes;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGR2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;


public class HogSvmDetector implements ObjectDetector {
    private final HOGDescriptor hog;
    private  double hitThreshold = 1.0;
    private  double scale = 1.02;
    private  double finalThreshold = 0.0;

    private  Size winStride = new Size(16, 16);

    private  Size padding = new Size(0, 0);
    private  boolean useMeanShiftGrouping = false;

    private  double weightThreshold = 0.00;

    private  float scoreThreshold = 1.0f;
    private  float nmsThreshold = 0.4f;

    public HogSvmDetector(HOGDescriptor hog) {
        this.hog = hog;
    }


    @Override
    public DetectionResult detect(Mat frame) {
        Mat gray = new Mat();
        cvtColor(frame, gray, COLOR_BGR2GRAY);

        RectVector detections = new RectVector();
        DoublePointer weights = new DoublePointer();

        hog.detectMultiScale(gray, detections, weights, hitThreshold, winStride, padding, scale, finalThreshold, useMeanShiftGrouping);

        var boxes = new RectVector(detections);
        var weightPointer = new FloatPointer(weights);
        var indices = new IntPointer();

        NMSBoxes(boxes, weightPointer, scoreThreshold, nmsThreshold, indices);

        List<Rect> filtered = new ArrayList<>();
        List<Double> scores = new ArrayList<>();
        if (!detections.empty() && indices.capacity() > 0) {
            for (int idx : indices.getStringCodePoints()) {
                if (weights.get(idx) >= weightThreshold) {
                    filtered.add(boxes.get(idx));
                    scores.add(weights.get(idx));
                }
            }
        }

        indices.close();
        boxes.close();
        weightPointer.close();
        detections.close();
        weights.close();

        gray.release();
        return new DetectionResult(filtered, scores);
    }

    public HOGDescriptor getHog() {
        return hog;
    }

    public double getHitThreshold() {
        return hitThreshold;
    }

    public void setHitThreshold(double hitThreshold) {
        this.hitThreshold = hitThreshold;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public double getFinalThreshold() {
        return finalThreshold;
    }

    public void setFinalThreshold(double finalThreshold) {
        this.finalThreshold = finalThreshold;
    }

    public Size getWinStride() {
        return winStride;
    }

    public void setWinStride(int winStride) {
        this.winStride = new Size(winStride, winStride);
    }

    public Size getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = new Size(padding, padding);
    }

    public boolean isUseMeanShiftGrouping() {
        return useMeanShiftGrouping;
    }

    public void setUseMeanShiftGrouping(boolean useMeanShiftGrouping) {
        this.useMeanShiftGrouping = useMeanShiftGrouping;
    }

    public double getWeightThreshold() {
        return weightThreshold;
    }

    public void setWeightThreshold(double weightThreshold) {
        this.weightThreshold = weightThreshold;
    }

    public float getScoreThreshold() {
        return scoreThreshold;
    }

    public void setScoreThreshold(float scoreThreshold) {
        this.scoreThreshold = scoreThreshold;
    }

    public float getNmsThreshold() {
        return nmsThreshold;
    }

    public void setNmsThreshold(float nmsThreshold) {
        this.nmsThreshold = nmsThreshold;
    }
}