package knu.app.utils.video;

import org.bytedeco.javacv.Frame;

import java.io.IOException;

public interface VideoSource {

    Frame grab() throws Exception;

    double getFrameRate();

    void start();

    void pause() throws IOException;

    void resume();

    void stop() throws IOException;

    void setInputSource(String inputSource);

    void setWidth(int width);

    void setHeight(int height);

    void setFramerate(int framerate);

    String getInputSource();

    int getWidth();

    int getHeight();

    int getFramerate();
}