package knu.app.bll.preprocessors;

import org.bytedeco.javacpp.indexer.Indexer;
import org.bytedeco.javacpp.indexer.UByteIndexer;

import org.bytedeco.opencv.opencv_core.*;

import static org.bytedeco.opencv.global.opencv_core.*;

public class KMeansPreprocessor implements FramePreprocessor {
    private int k;
    final Mat samples32f = new Mat();
    final Mat labels = new Mat();
    final Mat centers = new Mat();
    Indexer labelsIndexer;
    Indexer centersIndexer;
    UByteIndexer resultIndexer;
    Mat result = null;

    final TermCriteria criteria = new TermCriteria(TermCriteria.COUNT | TermCriteria.EPS, 100, 0.1);

    public KMeansPreprocessor(int k) {
        this.k = k;
    }

    @Override
    public Mat process(Mat input) {
        input.reshape(1, input.rows() * input.cols()).convertTo(samples32f, CV_32F);

        result = new Mat(input.size(), input.type());

        kmeans(samples32f, k, labels, criteria, 1, KMEANS_PP_CENTERS, centers);

        centers.convertTo(centers, CV_8UC3);

        labelsIndexer = labels.createIndexer();
        centersIndexer = centers.createIndexer();

        resultIndexer = result.createIndexer();

        for (int y = 0; y < input.rows(); y++) {
            for (int x = 0; x < input.cols(); x++) {
                int label = (int) labelsIndexer.getDouble((long) y * input.cols() + x);

                float b = (float) centersIndexer.getDouble(label, 0);
                float g = (float) centersIndexer.getDouble(label, 1);
                float r = (float) centersIndexer.getDouble(label, 2);

                resultIndexer.put(y, x, 0, (byte) (b * 255));
                resultIndexer.put(y, x, 1, (byte) (g * 255));
                resultIndexer.put(y, x, 2, (byte) (r * 255));
            }
        }


        resultIndexer.release();
        return result;
    }

    public void setK(int k) {
        this.k = k;
    }
}
