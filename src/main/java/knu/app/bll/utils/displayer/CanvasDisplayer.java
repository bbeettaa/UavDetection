package knu.app.bll.utils.displayer;

import org.bytedeco.javacv.*;
import org.bytedeco.javacv.Frame;
import org.opencv.core.Mat;

import java.awt.*;

public class CanvasDisplayer implements FrameDisplayer {
    private final CanvasFrame canvas;
    private final OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat();

    public CanvasDisplayer(String title, double gamma) {
        this.canvas = new CanvasFrame(title, CanvasFrame.getDefaultGamma() );
        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void show(Frame frame) {
        canvas.showImage(frame);
    }

    @Override
    public void show(Image image) {
        canvas.showImage(image);
    }

    @Override
    public void close() {
        canvas.dispose();
    }

    public Frame convert(Mat mat) {
        return converter.convert(mat);
    }

    public org.bytedeco.opencv.opencv_core.Mat convert(Frame frame) {
        return converter.convert(frame);
    }
}

