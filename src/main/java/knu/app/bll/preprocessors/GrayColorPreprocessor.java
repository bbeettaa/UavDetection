package knu.app.bll.preprocessors;

import org.bytedeco.opencv.opencv_core.Mat;

import static org.bytedeco.opencv.global.opencv_imgproc.*;

public class GrayColorPreprocessor implements FramePreprocessor {
    final Mat gray;
    final Mat result;
    public GrayColorPreprocessor() {
        this.gray = new Mat();
        this.result = new Mat();
    }

    @Override
    public Mat process(Mat input) {
        cvtColor(input, gray, COLOR_BGR2GRAY);
        // Back to BGR
        cvtColor(gray, result, COLOR_GRAY2BGR);
        return result;
    }
}
