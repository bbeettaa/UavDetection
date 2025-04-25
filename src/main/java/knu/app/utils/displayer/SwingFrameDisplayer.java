package knu.app.utils.displayer;

import org.bytedeco.javacv.Frame;

import java.awt.*;
import org.bytedeco.javacv.*;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class SwingFrameDisplayer implements FrameDisplayer {
    private JFrame frame;
    private JLabel label;
    private ImageIcon imageIcon;

    public SwingFrameDisplayer() {
        frame = new JFrame("Frame Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        label = new JLabel();
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    @Override
    public void show(Frame frame) {
        BufferedImage bufferedImage = frameToBufferedImage(frame);
        showImage(bufferedImage);
    }

    @Override
    public void show(Image image) {
        BufferedImage bufferedImage = (BufferedImage) image;
        showImage(bufferedImage);
    }

    private void showImage(BufferedImage bufferedImage) {
        imageIcon = new ImageIcon(bufferedImage);
        label.setIcon(imageIcon);
        label.repaint();
    }

    Java2DFrameConverter converterImg = new Java2DFrameConverter();
    OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat();


    private BufferedImage frameToBufferedImage(Frame frame) {
        BufferedImage bufferedImage = converterImg.convert(frame);
        return bufferedImage;
    }

    @Override
    public void close() {
        frame.dispose();
    }
}
