package knu.app.pipes;

import knu.app.buffers.Bufferable;
import knu.app.preprocessors.FramePreprocessor;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.opencv_core.Mat;

import java.util.concurrent.Executor;

public class PreprocessorPipe implements Pipeable<Frame> {
    private final Executor executor;
    private final Bufferable<Frame> buffer;
    private final MatPipeProcessor[] pipeProcessors;
    private final OpenCVFrameConverter.ToMat converter;
    private Mat mat;

    public PreprocessorPipe(Executor executor, Bufferable<Frame> buffer, MatPipeProcessor... pipeProcessors) {
        this.executor = executor;
        this.buffer = buffer;
        this.pipeProcessors = pipeProcessors;
        this.converter = new OpenCVFrameConverter.ToMat();
        this.mat = null;
    }

    @Override
    public Frame execute(Frame o) {
        if(o == null) return null;

        try {
            mat = converter.convert(o);
            for (PipeProcessor<Mat> p : pipeProcessors)
                mat = p.process(mat);

            return converter.convert(mat);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public MatPipeProcessor[] getProcessors() {
        return pipeProcessors;
    }
}
