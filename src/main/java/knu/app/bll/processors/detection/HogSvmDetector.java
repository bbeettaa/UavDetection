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
    private final double hitThreshold = 1.0;
    private final double scale = 1.02;
//    private final double scale = 1.015;
    private final double finalThreshold = 0.0;

    private final int WIN_STRINDE_L = 16;
    private final Size winStride = new Size(WIN_STRINDE_L, WIN_STRINDE_L);

    private Size padding = new Size(0, 0);
    private boolean useMeanShiftGrouping = false;

    private final double weightThreshold = 0.00;

    private float scoreThreshold = 1.0f;
    private float nmsThreshold = 0.4f;

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

//        indices.close();
//        boxes.close();
//        weightPointer.close();
//        detections.close();
//        weights.close();

//        gray.release();
        return new DetectionResult(filtered, scores);
    }

    public void release() {
        hog.close();
    }


}