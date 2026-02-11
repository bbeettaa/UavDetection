package knu.app.bll.postprocessors;

import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Scalar;

import static org.bytedeco.opencv.global.opencv_imgproc.*;

public class FPSOverlayPostprocessorValue implements FramePostprocessorValue<Long> {
    private double fps = 0;
    long frameCount = 0;

    private long lastTime = System.nanoTime();
    long currentTime;

    final double sizeScalar = 0.3;

    @Override
    public void setValue(Long val) {
        currentTime = val;
    }
    @Override
    public Mat process(Mat input) {
        frameCount++;
        double elapsedTime = (currentTime - lastTime) / 1_000_000_000.0;
        if (elapsedTime >= 1.0) {
            fps = frameCount / elapsedTime;
            lastTime = currentTime;
            frameCount = 0;
        }

        String text = String.format("%.0f", fps);
        putText(input, text, new Point(5, 20), FONT_HERSHEY_SIMPLEX, 1.0 - sizeScalar, Scalar.BLACK, 2, LINE_AA, false);
        putText(input, text, new Point(5, 20), FONT_HERSHEY_SIMPLEX, 1.0 - sizeScalar, Scalar.WHITE, 1, LINE_AA, false);

        return input;
    }

}
