package knu.app.bll.preprocessors;

import org.bytedeco.opencv.opencv_core.Mat;

public interface FramePreprocessor {
    Mat process(Mat input);
}