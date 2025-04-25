package knu.app.pipes;

import knu.app.preprocessors.FramePreprocessor;
import org.bytedeco.opencv.opencv_core.Mat;

public class MatPipeProcessor implements PipeProcessor<Mat> {
    private boolean isR;
    private final FramePreprocessor preprocessor;
    private final String name;

    public MatPipeProcessor(FramePreprocessor preprocessor, String name){
        this.preprocessor = preprocessor;
        this.isR=false;
        this.name = name;
    }


    @Override
    public Mat process(Mat o) {
        if(!isR)
            return o;
        return preprocessor.process(o);
    }

    @Override
    public void setRunning(boolean isR) {
        this.isR = isR;
    }

    @Override
    public boolean isRunning() {
        return isR;
    }

    @Override
    public String getName() {
        return name;
    }
}
