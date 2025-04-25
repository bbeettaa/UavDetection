package knu.app.postprocessors;

import org.bytedeco.opencv.opencv_core.Mat;

public interface FramePostprocessor {
    Mat process(Mat input) throws InterruptedException;
}