package knu.app.bll.utils;

import knu.app.bll.utils.grabbers.PlaybackFFmpegRawVideoSource;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.indexer.DoubleIndexer;
import org.bytedeco.javacpp.indexer.FloatIndexer;
import org.bytedeco.javacpp.indexer.IntIndexer;
import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.opencv_ml.SVM;
import org.bytedeco.opencv.opencv_objdetect.HOGDescriptor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

import static org.bytedeco.opencv.global.opencv_core.CV_32F;
import static org.bytedeco.opencv.global.opencv_core.CV_32S;
import static org.bytedeco.opencv.global.opencv_imgcodecs.IMREAD_GRAYSCALE;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.bytedeco.opencv.global.opencv_imgproc.equalizeHist;
import static org.bytedeco.opencv.global.opencv_imgproc.resize;
import static org.bytedeco.opencv.global.opencv_ml.ROW_SAMPLE;

public class HogSvmUtils {
    private static final Logger logger = Logger.getLogger(HogSvmUtils.class.getName());
    private final HOGDescriptor hog;

    private static final int WIN_SIZE_L = 32;
    private static final Size WIN_SIZE = new Size(WIN_SIZE_L, WIN_SIZE_L);

    private static final int BLOCK_SIZE_L = WIN_SIZE_L / 2;
    private static final Size BLOCK_SIZE = new Size(BLOCK_SIZE_L, BLOCK_SIZE_L);

    private static final int BLOCK_STRIDE_L = 8;
    private static final Size BLOCK_STRIDE = new Size(BLOCK_STRIDE_L, BLOCK_STRIDE_L);

    private static final int CELL_SIZE_L = 4;
    private static final Size CELL_SIZE = new Size(CELL_SIZE_L, CELL_SIZE_L);

    private static final int NBINS = 9;

    private static final int TERM_CRITERIA = TermCriteria.MAX_ITER | TermCriteria.EPS;
    private static final int MAX_ITERATION_COUNT = 20_000;

    private final int negativePatchesStep = BLOCK_STRIDE_L;

    public HogSvmUtils() {
        this(createHog());
    }

    public HogSvmUtils(HOGDescriptor hog) {
        this.hog = hog;
        logger.log(Level.INFO,"Block Coverage " + ((float) (BLOCK_SIZE_L - BLOCK_STRIDE_L) / BLOCK_SIZE_L * 100) + "%");
    }

    public void saveDescriptorToFile(String file) {
        logger.info("Saving descriptor to file");
        hog.save(file);
    }

    public HOGDescriptor loadDescriptorFromFile(String file) {
        logger.log(Level.INFO,"Loading descriptor from file");
        if (!hog.load(file))
            logger.warning("Cannot load descriptor from file ");
        return hog;
    }

    public void train(String posDir, String negDir) {
        logger.info("Training Process started");
        logger.info("\tLoading positive samples...");
        List<Mat> positives = loadAndPreparePositives(posDir);
        logger.info("\tLoading done");
        List<Mat> negatives;

        logger.info("\tLoading negative samples...");
        negatives = generateNegativePatches(negDir);
        logger.info("\tLoading done");

        logger.info("\tPreparing samples...");
        Mat samples = prepareSamples(positives, negatives);
        Mat labels = prepareLabels(positives.size(), negatives.size());
        logger.info("\tPreparing done");

        logger.info("\tTraining on data...");
        Mat svm = trainSVM(samples, labels);
        logger.info("\tTraining done");

        hog.setSVMDetector(svm);

        releaseMats(positives);
        releaseMats(negatives);
        samples.release();
        labels.release();

        logger.info("Training process done");
    }

    private List<Mat> loadAndPreparePositives(String dirPath) {
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

    private Mat preprocessImage(Mat img) {
        Mat resized = new Mat();
        resize(img, resized, WIN_SIZE);

        Mat equalized = new Mat();
        equalizeHist(resized, equalized);

        resized.release();
        return equalized;
    }

    private List<Mat> generateNegativePatches(String dirPath) {
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

            for (int y = 0; y <= img.rows() - WIN_SIZE.height(); y += negativePatchesStep) {
                for (int x = 0; x <= img.cols() - WIN_SIZE.width(); x += negativePatchesStep) {
                    roi = new Rect(x, y, WIN_SIZE.width(), WIN_SIZE.height());
                    patch = new Mat(img, roi);
//                    imshow("patch", patch);
//                    waitKey(0);
                    patches.add(preprocessImage(patch));
                    patch.release();
                }
            }
            img.release();
        }
        return patches;
    }

    private Mat prepareSamples(List<Mat> positives, List<Mat> negatives) {
        int descriptorSize = (int) hog.getDescriptorSize();
        Mat samples = new Mat(positives.size() + negatives.size(), descriptorSize, CV_32F);

        try (FloatIndexer indexer = samples.createIndexer()) {
            int row = 0;
            for (Mat img : positives) {
                computeHogDescriptor(img, indexer, row++);
            }
            for (Mat img : negatives) {
                computeHogDescriptor(img, indexer, row++);
            }
        }
        return samples;
    }

    private void computeHogDescriptor(Mat img, FloatIndexer indexer, int row) {
        FloatPointer descriptor = new FloatPointer(hog.getDescriptorSize());
        hog.compute(img, descriptor, BLOCK_STRIDE, new Size(0, 0), new PointVector());

        for (int col = 0; col < descriptor.limit(); col++) {
            indexer.put(row, col, descriptor.get(col));
        }
        descriptor.close();
    }

    private Mat prepareLabels(int posCount, int negCount) {
        Mat labels = new Mat(posCount + negCount, 1, CV_32S);
        try (IntIndexer indexer = labels.createIndexer()) {
            IntStream.range(0, posCount).forEach(i -> indexer.put(i, 0, 1));
            IntStream.range(posCount, posCount + negCount).forEach(i -> indexer.put(i, 0, -1));
        }
        return labels;
    }

    private Mat trainSVM(Mat samples, Mat labels) {
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

    private void releaseMats(List<Mat> mats) {
        mats.forEach(Mat::release);
    }


    public static HOGDescriptor createHog() {
        return createHog(WIN_SIZE, BLOCK_SIZE, BLOCK_STRIDE, CELL_SIZE, NBINS);
    }

    public static HOGDescriptor createHog(Size winSize, Size blockSize, Size blockStride, Size cellSize, int nbis) {
        return new HOGDescriptor(winSize, blockSize, blockStride, cellSize, nbis);
    }

    public HOGDescriptor getHog() {
        return hog;
    }
}
