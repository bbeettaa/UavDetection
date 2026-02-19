package knu.app.bll.utils;


import org.bytedeco.opencv.opencv_core.Mat;

public class MatWrapper implements Comparable<MatWrapper> {
    public final long frameIndex;
    public final Mat mat;

    public MatWrapper(long frameIndex, Mat mat) {
        this.frameIndex = frameIndex;
        this.mat = mat;
    }

    @Override
    public int compareTo(MatWrapper matWrapper) {
        return Long.compare(this.frameIndex, matWrapper.frameIndex);
    }
}
