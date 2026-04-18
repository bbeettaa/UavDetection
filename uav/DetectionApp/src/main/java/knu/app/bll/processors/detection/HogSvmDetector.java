package knu.app.bll.processors.detection;


import static org.bytedeco.opencv.global.opencv_dnn.NMSBoxes;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGR2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;

import knu.app.bll.utils.MatWrapper;
import knu.app.bll.utils.processors.DetectionResult;
import knu.app.bll.utils.processors.hog.HogSvmDetectorConfig;
import knu.app.bll.utils.processors.hog.HogSvmUtils;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_objdetect.HOGDescriptor;
import org.jspecify.annotations.NonNull;


public class HogSvmDetector implements ObjectDetector {

    private final HogSvmDetectorConfig c;
    private final ThreadLocal<HOGDescriptor> hogThreadLocal;
    int cc = 1;
    private final ExecutorService hogPool = Executors.newFixedThreadPool(cc);
    private final Semaphore hogSemaphore;
    private DetectionResult lastResult= new DetectionResult();

    public HogSvmDetector(String hogfile, HogSvmDetectorConfig c) {
        this.c = c;
        this.hogThreadLocal = ThreadLocal.withInitial(() -> HogSvmUtils.loadDescriptorFromFile(hogfile));
        this.hogSemaphore = new Semaphore(cc);
    }


    @Override
    public DetectionResult detect(MatWrapper matWrapper) {
        if (!hogSemaphore.tryAcquire()) {
            return lastResult;
        }

        DetectionResult res = detect(matWrapper.mat);
        lastResult = res;

        hogSemaphore.release();
        return res;
    }

    private @NonNull DetectionResult detect(Mat frameCopy) {
        Mat gray = new Mat();
        cvtColor(frameCopy, gray, COLOR_BGR2GRAY);

        RectVector detections = new RectVector();
        DoublePointer weights = new DoublePointer();

        hogThreadLocal.get().detectMultiScale(gray, detections, weights, c.getHitThreshold(),
                c.getWinStride(), c.getPadding(), c.getScale(),
                c.getFinalThreshold(), c.isUseMeanShiftGrouping());

        hogThreadLocal.get().groupRectangles(detections, weights, c.getGroupThreshold(), c.getEps());

        RectVector groupedBoxes = new RectVector(detections.size());
        FloatPointer groupedWeights = new FloatPointer(weights.limit());
        for (int i = 0; i < detections.size(); i++) {
            groupedBoxes.put(i, detections.get(i));
            groupedWeights.put(i, (float) weights.get(i));
        }

        IntPointer indices = new IntPointer(weights.limit());
        NMSBoxes(groupedBoxes, groupedWeights, c.getScoreThreshold(), c.getNmsThreshold(), indices);

        List<Rect> filtered = new ArrayList<>();
        List<Double> scores = new ArrayList<>();
        List<String> names = new ArrayList<>();
        int count = (int) indices.limit();
        for (int i = 0; i < count; i++) {
            int idx = indices.get(i);
            double score = groupedWeights.get(idx);
            if (score >= c.getWeightThreshold()) {
                filtered.add(groupedBoxes.get(idx));
                scores.add(score / 10);
                names.add("-");
            }
        }

        indices.close();
        detections.close();
        weights.close();
        gray.release();

        return new DetectionResult(filtered, scores, names);
    }

    public HogSvmDetectorConfig getConfig() {
        return c;
    }


}