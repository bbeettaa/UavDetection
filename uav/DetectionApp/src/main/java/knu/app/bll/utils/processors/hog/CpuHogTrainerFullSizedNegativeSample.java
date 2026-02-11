package knu.app.bll.utils.processors.hog;

import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.opencv_objdetect.HOGDescriptor;

import java.util.List;
import java.util.logging.Logger;

import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.indexer.FloatIndexer;

import static knu.app.bll.utils.processors.hog.HogSvmUtils.*;

import static org.bytedeco.opencv.global.opencv_core.CV_32F;

public class CpuHogTrainerFullSizedNegativeSample extends HogTrainer {
    private static final Logger logger = Logger.getLogger(CpuHogTrainerFullSizedNegativeSample.class.getName());


    private final HOGDescriptor hog;
    private final int negStep;

    public CpuHogTrainerFullSizedNegativeSample() {
        this(HogSvmUtils.createHog(), 1);
        opencv_core.setUseOpenCL(true);

        System.out.println("OpenCL available: " + opencv_core.haveOpenCL());
        System.out.println("OpenCL enable: " + opencv_core.useOpenCL());
    }

    public CpuHogTrainerFullSizedNegativeSample(HOGDescriptor hog, int negativePatchesStep) {
        this.hog = hog;
        this.negStep = negativePatchesStep;
        opencv_core.setUseOpenCL(true);
    }

    @Override
    public HOGDescriptor train(String posDir, String negDir) {
        logger.info("Training Process started");
        logger.info("\tLoading positive samples...");
        List<Mat> positives = loadAndPreparePositives(posDir);
        logger.info("\tLoading done");
        List<Mat> negatives;

        logger.info("\tLoading negative samples...");
        negatives = generateNegativePatches(negDir, negStep);
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
        hog.compute(img, descriptor, BLOCK_STRIDE, HOG_PADDING, new PointVector());

        for (int col = 0; col < descriptor.limit(); col++) {
            indexer.put(row, col, descriptor.get(col));
        }
        descriptor.close();
    }

}
