package knu.app.bll.utils.processors.hog;

import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.indexer.DoubleIndexer;
import org.bytedeco.javacpp.indexer.FloatIndexer;
import org.bytedeco.javacpp.indexer.IntIndexer;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.TermCriteria;
import org.bytedeco.opencv.opencv_ml.SVM;
import org.bytedeco.opencv.opencv_objdetect.HOGDescriptor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import static knu.app.bll.utils.processors.hog.HogSvmUtils.*;
import static org.bytedeco.opencv.global.opencv_core.CV_32S;
import static org.bytedeco.opencv.global.opencv_imgcodecs.IMREAD_GRAYSCALE;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.bytedeco.opencv.global.opencv_imgproc.equalizeHist;
import static org.bytedeco.opencv.global.opencv_imgproc.resize;
import static org.bytedeco.opencv.global.opencv_ml.ROW_SAMPLE;

public abstract class HogTrainer {
    /**
     * Training Hog on positives and negatives samples.
     *
     * @param posDir path to positives samples dir
     * @param negDir path to negative samples dir
     */
    public abstract HOGDescriptor train(String posDir, String negDir);



    protected List<Mat> loadAndPreparePositives(String dirPath) {
        List<Mat> samples = new ArrayList<>();
        File dir = new File(dirPath);
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            Mat img = imread(file.getAbsolutePath(), IMREAD_GRAYSCALE);
            if (img.empty()) continue;

            Mat processed = preprocessImage(img);

            samples.add(processed);
            img.release();
        }
        return samples;
    }


    protected List<Mat> generateNegativePatches(String dirPath, int negStep) {
        List<Mat> patches = new ArrayList<>();
        File dir = new File(dirPath);

        Mat img;
        Rect roi;
        Mat patch;

        for (File file : Objects.requireNonNull(dir.listFiles())) {
            img = imread(file.getAbsolutePath(), IMREAD_GRAYSCALE);
            if (img.empty()) continue;

            if (img.cols() < WIN_SIZE.width() || img.rows() < WIN_SIZE.height()) {
                img.release();
                continue;
            }

            for (int y = 0; y <= img.rows() - WIN_SIZE.height(); y += negStep) {
                for (int x = 0; x <= img.cols() - WIN_SIZE.width(); x += negStep) {
                    roi = new Rect(x, y, WIN_SIZE.width(), WIN_SIZE.height());
                    patch = new Mat(img, roi);
                    patches.add(preprocessImage(patch));
                    patch.release();
                }
            }
            img.release();
        }
        return patches;
    }


    protected Mat preprocessImage(Mat img) {
        Mat resized = new Mat();
        resize(img, resized, WIN_SIZE);

        Mat equalized = new Mat();
        equalizeHist(resized, equalized);

        resized.release();
        return equalized;
    }

    protected Mat prepareLabels(int posCount, int negCount) {
        Mat labels = new Mat(posCount + negCount, 1, CV_32S);
        try (IntIndexer indexer = labels.createIndexer()) {
            IntStream.range(0, posCount).forEach(i -> indexer.put(i, 0, 1));
            IntStream.range(posCount, posCount + negCount).forEach(i -> indexer.put(i, 0, -1));
        }
        return labels;
    }



    protected Mat trainSVM(Mat samples, Mat labels) {
        SVM svm = SVM.create();
        svm.setType(SVM.C_SVC);
        svm.setKernel(SVM.LINEAR);
        svm.setTermCriteria(new TermCriteria(TERM_CRITERIA, MAX_ITERATION_COUNT, 1e-6));

        svm.trainAuto(samples, ROW_SAMPLE, labels);

        Mat sv = svm.getSupportVectors();
        Mat alpha = new Mat();
        Mat svidx = new Mat();
        double rho = svm.getDecisionFunction(0, alpha, svidx);

        FloatPointer detector = new FloatPointer(sv.cols() + 1);
        try (FloatIndexer svIndexer = sv.createIndexer();
             DoubleIndexer alphaIndexer = alpha.createIndexer()) {

            for (int i = 0; i < sv.cols(); i++) {
                double sum = 0.0;
                for (int j = 0; j < alpha.rows(); j++) {
                    sum += alphaIndexer.get(j, 0) * svIndexer.get(0, i);
                }
                detector.put(i, (float) -sum);
            }
            detector.put(sv.cols(), (float) rho);
        }

        sv.release();
        alpha.release();
        svidx.release();
        svm.close();

        return new Mat(detector);
    }



    protected void releaseMats(List<Mat> mats) {
        mats.forEach(Mat::release);
    }


}
