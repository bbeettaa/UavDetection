package knu.app.bll.preprocessors;

import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;

import static org.bytedeco.opencv.global.opencv_imgproc.*;

public class BlurPreprocessor implements FramePreprocessor {

    private final Mat blurred;
    private double d;
    private int kernel;

    public BlurPreprocessor(double d) {
        this.blurred = new Mat();
        this.d = d;
        this.kernel = 5;
    }

    public BlurPreprocessor() {
        this.blurred = new Mat();
        this.d = 1;
        this.kernel = 5;
    }

    @Override
    public Mat process(Mat input) {
        GaussianBlur(input, blurred, new Size(kernel, kernel), d);
        return blurred;
    }

    public void setD(double d) {
        this.d = d;
    }

    public void setKernel(int kernel) {
        this.kernel = kernel;
    }
}
