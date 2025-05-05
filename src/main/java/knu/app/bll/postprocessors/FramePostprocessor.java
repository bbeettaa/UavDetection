package knu.app.bll.postprocessors;

import org.bytedeco.opencv.opencv_core.Mat;

public interface FramePostprocessor {
    Mat process(Mat input);
}