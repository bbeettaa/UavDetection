package knu.app.bll.utils.grabbers;

import knu.app.bll.events.EventModelListener;
import org.bytedeco.javacv.Frame;

import java.io.IOException;

public interface PlaybackControlVideoSource {
    void play();

    void pause();

    void stop() throws IOException;

    void seek(long timestamp) throws IOException;

    void stepForward();

    void stepBackward();

    void setPlaybackSpeed(float speed);

    long getCurrentPosition();

    long getDuration();

    long getFrameNumber();

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

    void addListener(EventModelListener listener);

}

