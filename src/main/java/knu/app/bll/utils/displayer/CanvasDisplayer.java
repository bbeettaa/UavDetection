package knu.app.bll.utils.displayer;

import org.bytedeco.javacv.*;
import org.bytedeco.javacv.Frame;
import org.opencv.core.Mat;

import java.awt.*;

public class CanvasDisplayer implements FrameDisplayer {
    private final CanvasFrame canvas;

    public CanvasDisplayer(String title ) {
        this.canvas = new CanvasFrame(title, CanvasFrame.getDefaultGamma() );
        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void show(Frame frame) {
        canvas.showImage(frame);
    }

}

