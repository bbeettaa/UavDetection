package knu.app.utils.displayer;

import org.bytedeco.javacv.Frame;

import java.awt.*;

public interface FrameDisplayer {
    void show(Frame frame);
    void show(Image image);
    void close();
}