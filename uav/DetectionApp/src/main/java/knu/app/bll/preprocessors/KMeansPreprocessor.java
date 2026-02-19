package knu.app.bll.preprocessors;

import org.bytedeco.javacpp.indexer.UByteIndexer;
import org.bytedeco.opencv.opencv_core.*;
import static org.bytedeco.opencv.global.opencv_core.*;
import static org.bytedeco.opencv.global.opencv_imgproc.resize;

import org.bytedeco.javacpp.indexer.IntIndexer;

public class KMeansPreprocessor implements FramePreprocessor {
    private int k;

    private TermCriteria criteria;


    private final double scale = 0.75;

    public KMeansPreprocessor() {
        this.k = 2;
        this.criteria = new TermCriteria(TermCriteria.EPS, 10, 0.1);
    }

    @Override
    public Mat process(Mat input) {
         final Mat small = new Mat();
         final Mat samples32f = new Mat();
         final Mat labels = new Mat();
         final Mat centers = new Mat();

        Mat result = null;

        if (input.empty()) return input;

        resize(input, small, new Size((int) (input.cols()* scale), (int) (input.rows() * scale)));

        int totalPixels = small.rows() * small.cols();
        small.reshape(1, totalPixels).convertTo(samples32f, CV_32FC1);

        kmeans(samples32f, k, labels, criteria, 1, KMEANS_PP_CENTERS, centers);



        result = new Mat(small.size(), small.type());

        IntIndexer labelsIdx = labels.createIndexer();
        centers.convertTo(centers, CV_8U);
        UByteIndexer centersIdx = centers.createIndexer();
        UByteIndexer resIdx = result.createIndexer();

        for (int i = 0; i < totalPixels; i++) {
            int label = labelsIdx.get(i);

            byte b = (byte) centersIdx.get(label, 0);
            byte g = (byte) centersIdx.get(label, 1);
            byte r = (byte) centersIdx.get(label, 2);

            int row = i / small.cols();
            int col = i % small.cols();

            resIdx.put(row, col, 0, b);
            resIdx.put(row, col, 1, g);
            resIdx.put(row, col, 2, r);
        }

        labelsIdx.release();
        centersIdx.release();
        resIdx.release();

        resize(result, result, input.size());
        return result;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getK() {
        return k;
    }

    public void setCriteria(int maxCount, double epsilon) {
        this.criteria = new TermCriteria(TermCriteria.COUNT | TermCriteria.EPS, maxCount, epsilon);
    }

}

