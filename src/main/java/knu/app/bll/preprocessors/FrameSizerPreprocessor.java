package knu.app.bll.preprocessors;


import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;

import static org.bytedeco.opencv.global.opencv_imgproc.resize;

public class FrameSizerPreprocessor implements FramePreprocessor {

    private  Size size;

    public FrameSizerPreprocessor(int width, int height) {
        this.size = new Size(width, height);
    }

    @Override
    public Mat process(Mat input) {
        resize(input, input, size);
        return input;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }


}
