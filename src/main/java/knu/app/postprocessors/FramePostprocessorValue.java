package knu.app.postprocessors;

import org.bytedeco.opencv.opencv_core.Mat;

public interface FramePostprocessorValue<T> extends FramePostprocessor {
    void setValue(T val);
}
