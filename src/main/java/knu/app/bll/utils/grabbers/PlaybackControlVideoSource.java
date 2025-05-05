package knu.app.bll.utils.grabbers;

import org.bytedeco.javacv.Frame;

import java.io.IOException;

public interface PlaybackControlVideoSource {
    void play();

    void pause() throws IOException;

    void stop() throws IOException;

    void seek(long timestamp) throws IOException;

    void stepForward();

    void stepBackward();

    void setPlaybackSpeed(float speed);

    long getCurrentPosition();

    long getDuration();

    Frame grab() throws Exception;

    double getFrameRate();

    void start();

    void resume();

    void setInputSource(String inputSource);

    void setWidth(int width);

    void setHeight(int height);

    void setFramerate(int framerate);

    String getInputSource();

    int getWidth();

    int getHeight();

    int getFramerate();


    boolean isRunning();

    boolean isPaused();


}

