package knu.app.bll.utils.displayer;

import org.bytedeco.javacv.Frame;

import java.awt.*;
import org.bytedeco.javacv.*;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class SwingFrameDisplayer implements FrameDisplayer {
    private final JFrame frame;
    private final JLabel label;
    private final Java2DFrameConverter converterImg = new Java2DFrameConverter();

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
        ImageIcon imageIcon = new ImageIcon(bufferedImage);
        label.setIcon(imageIcon);
        label.repaint();
    }

    private BufferedImage frameToBufferedImage(Frame frame) {
        return converterImg.convert(frame);
    }

    @Override
    public void close() {
        frame.dispose();
    }
}
