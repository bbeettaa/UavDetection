package knu.app.pipes;

import knu.app.preprocessors.FramePreprocessor;
import org.bytedeco.opencv.opencv_core.Mat;

public interface PipeProcessor<T> {
    T process(T o);

    void setRunning(boolean isR);

    boolean isRunning();

    String getName();
}
