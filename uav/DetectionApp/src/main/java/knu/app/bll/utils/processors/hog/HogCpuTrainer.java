package knu.app.bll.utils.processors.hog;

import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.indexer.FloatIndexer;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_objdetect.HOGDescriptor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import static knu.app.bll.utils.processors.hog.HogSvmUtils.*;
import static org.bytedeco.opencv.global.opencv_core.CV_32F;
import static org.bytedeco.opencv.global.opencv_imgcodecs.IMREAD_GRAYSCALE;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;

public class HogCpuTrainer extends HogTrainer {
    private static final Logger logger = Logger.getLogger(HogCpuTrainer.class.getName());


    private final HOGDescriptor hog;

    public HogCpuTrainer() {
        this(HogSvmUtils.createHog());
        opencv_core.setUseOpenCL(true);
    }

    public HogCpuTrainer(HOGDescriptor hog) {
        this.hog = hog;
        opencv_core.setUseOpenCL(true);
    }


    /**
     * Training Hog on positives and negatives samples.
     *
     * @param posDir path to positives samples dir
     * @param negDir path to negative samples dir
     */
    @Override
    public HOGDescriptor train(String posDir, String negDir) {
        logger.info("Training Process started");

        logger.info("\tLoading positive samples...");
        List<Mat> positives = loadAndPreparePositives(posDir);
        logger.info("\tLoading done");

        logger.info("\tLoading negative patches...");
        List<Mat> negatives = loadNegativePatches(negDir);
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
        return hog;
    }

    private List<Mat> loadNegativePatches(String dirPath) {
        List<Mat> patches = new ArrayList<>();
        File dir = new File(dirPath);
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            Mat img = imread(file.getAbsolutePath(), IMREAD_GRAYSCALE);
            if (img.empty()) continue;

            if (img.cols() != WIN_SIZE.width() || img.rows() != WIN_SIZE.height()) {
                logger.warning("Skipping image with incorrect size: " + file.getName());
                img.release();
                continue;
            }

            patches.add(preprocessImage(img));
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
        hog.compute(img, descriptor, BLOCK_STRIDE, HOG_PADDING, new org.bytedeco.opencv.opencv_core.PointVector());

        for (int col = 0; col < descriptor.limit(); col++) {
            indexer.put(row, col, descriptor.get(col));
        }
        descriptor.close();
    }
}



