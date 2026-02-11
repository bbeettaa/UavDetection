package knu.app.bll.preprocessors;


import org.bytedeco.opencv.opencv_core.Mat;

import static org.bytedeco.opencv.global.opencv_imgproc.*;


public class CannyPreprocessor implements FramePreprocessor {
    private final Mat edges;
    private final Mat result;
    private int v;
    private int v1;

    public CannyPreprocessor(int v, int v1) {
        this.edges = new Mat();
        this.result = new Mat();
        this.v = v;
        this.v1 = v1;
    }

    public CannyPreprocessor() {
        this(25,35);
    }

    @Override
    public Mat process(Mat input) {
        Canny(input, edges, v, v1);
        cvtColor(edges, result, COLOR_GRAY2BGR);
        return result;
    }

    public void setV(int v) {
        this.v = v;
    }

    public void setV1(int v1) {
        this.v1 = v1;
    }
}
