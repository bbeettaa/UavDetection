package knu.app.pipes;

import knu.app.buffers.Bufferable;
import knu.app.preprocessors.FramePreprocessor;
import org.bytedeco.javacv.Frame;
import org.bytedeco.opencv.opencv_core.Mat;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public interface Pipeable<T> {
    T execute(T o);

    MatPipeProcessor[] getProcessors();

}
