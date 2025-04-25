package knu.app.preprocessors;


import org.bytedeco.opencv.opencv_core.Mat;

public class FPSLimiterPreprocessor implements FramePreprocessor {

    private long frameIntervalMillis;
    private long lastFrameTime;
    private int targetFPS;

    public FPSLimiterPreprocessor(double targetFPS) {
        this.frameIntervalMillis = (long) (1000.0 / targetFPS);
        this.lastFrameTime = System.currentTimeMillis();
    }

    @Override
    public Mat process(Mat input) {
        long currentTime = System.currentTimeMillis();
        long elapsed = currentTime - lastFrameTime;
        long sleepTime = frameIntervalMillis - elapsed;

        if (sleepTime > 0) {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return input; // Возвращаем кадр без изменений при прерывании
            }
        }

        lastFrameTime = System.currentTimeMillis();
        return input;
    }

    public void setTargetFPS(int targetFPS) {
        this.targetFPS = targetFPS;
        this.frameIntervalMillis = 1000 / targetFPS;
    }
}
