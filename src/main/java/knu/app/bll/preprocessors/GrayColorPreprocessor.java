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
        if (input.empty()) {
            return new Mat();  // Handle empty input
        }
        cvtColor(input, this.gray, COLOR_BGR2GRAY);
        // Back to BGR (3-канальный grayscale для совместимости)
        cvtColor(this.gray, this.result, COLOR_GRAY2BGR);
        return this.result.clone();  // Return a copy to avoid caller modifying instance var
    }
    }
