package knu.app.utils.video;

import org.bytedeco.javacv.Frame;

import java.io.IOException;

public interface PlaybackControlVideoSource {
    void play();

    void pause()  throws IOException;

    void stop() throws IOException;

    void seek(double timeInSeconds) throws IOException;

    void stepForward();

    void stepBackward();

    void setPlaybackSpeed(float speed);

    double getCurrentPosition();

    double getDuration();

    Frame grab() throws Exception;

    double getFrameRate();

    void start() ;

    void resume();

    void setInputSource(String inputSource);

    void setWidth(int width) ;

    void setHeight(int height) ;

    void setFramerate(int framerate) ;

    String getInputSource() ;

    int getWidth();

    int getHeight();

    int getFramerate();
}
