package knu.app.utils.video;

import knu.app.buffers.BufferElement;
import org.bytedeco.javacv.*;
import knu.app.buffers.OverwritingQueueFrameBuffer;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.io.*;
import java.util.logging.Logger;

public class NativePlaybackFFmpegVideoSource implements PlaybackControlVideoSource, VideoSource {
    private static final Logger logger = Logger.getLogger(NativePlaybackFFmpegVideoSource.class.getName());
    private String inputSource;
    private static final int FRAME_BUFFER_SIZE = 50;
    private final OverwritingQueueFrameBuffer<Frame> frameBuffer =
            new OverwritingQueueFrameBuffer<>("playbackBuffer", FRAME_BUFFER_SIZE);

    private final AtomicBoolean isRunning = new AtomicBoolean(false);
    private final AtomicBoolean isPaused = new AtomicBoolean(false);
    private final AtomicBoolean stepMode = new AtomicBoolean(false);
    private final Object pauseLock = new Object();
    private float playbackSpeed = 1.0f;

    private int width;
    private int height;
    private int frameSize;
    private int framerate;
    private byte[] buffer;

    private Process ffmpeg;
    private ProcessBuilder pb;
    private final int ffmpegThreads;
    private InputStream videoStream;
    private DataInputStream dataInputStream;

    private final OverwritingQueueFrameBuffer<Frame> playbackBuffer =
            new OverwritingQueueFrameBuffer<>("playbackBuffer", 50);
    private long framesGrabbed = 0;
    private long currentPosition = 0;

    private final Java2DFrameConverter converter = new Java2DFrameConverter();

    public NativePlaybackFFmpegVideoSource(int width, int height, int framerate, int ffmpegThreads) {
        this.width = width;
        this.height = height;
        this.framerate = framerate;
        checkFFmpegAvailability();
        this.frameSize = this.width * this.height * 3;
        this.buffer = new byte[frameSize];
        this.ffmpegThreads = ffmpegThreads;
    }

    public NativePlaybackFFmpegVideoSource(int width, int height, String inputSource, int framerate, int ffmpegThreads) {
        this(width, height, framerate, ffmpegThreads);
        this.inputSource = inputSource;
    }

    public NativePlaybackFFmpegVideoSource(String inputSource, int framerate) {
        this(640, 480, inputSource, framerate, 3);
    }

    public NativePlaybackFFmpegVideoSource(int framerate) {
        this(640, 480, framerate, 3);
    }

    public NativePlaybackFFmpegVideoSource() {
        this(640, 480, 30, 3);
    }

    private void checkFFmpegAvailability() {
        try {
            Process process = new ProcessBuilder("ffmpeg", "-version").start();
            if (process.waitFor() != 0) {
                throw new ClassNotFoundException("FFmpeg not found");
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                logger.info("FFmpeg version: " + reader.readLine());
            }
        } catch (InterruptedException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Frame grab() throws IOException {
        if (!isRunning.get()) return null;

        handlePauseState();

        Frame frame = readFrame();
        if (frame != null) {
            framesGrabbed++;
            frameBuffer.put(new BufferElement<>(frame));
        }

        if (stepMode.get()) {
            stepMode.set(false);
            pause();
        }

        return frame;
    }

    private Frame readFrame() throws IOException {
        int offset = 0;
        while (offset < frameSize) {
            int bytesRead = dataInputStream.read(buffer, offset, frameSize - offset);
            if (bytesRead == -1) return null;
            offset += bytesRead;
        }

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        WritableRaster raster = img.getRaster();
        raster.setDataElements(0, 0, width, height, buffer);
        return converter.convert(img);
    }

    private void handlePauseState() {
        synchronized (pauseLock) {
            if (isPaused.get() && !stepMode.get() && isRunning.get()) {
                try {
                    System.out.println("PAUSEDd");
                    pauseLock.wait();
                    System.out.println("UN  PAUSEDd");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();

                }
            }
        }
    }


    @Override
    public double getFrameRate() {
        return getFramerate();
    }

    @Override
    public void start() {
        if (isRunning.get()) return;
        try {
            pb = new ProcessBuilder(
                    "ffmpeg", "-re",
                    "-i", inputSource, "-f", "image2pipe",
                    "-pix_fmt", "rgb24", "-threads", "" + ffmpegThreads,
                    "-vcodec", "rawvideo", "-s", width + "x" + height,
                    "-flush_packets", "1",
                    "-filter:v", "setpts=" + (1.0 / playbackSpeed) + "*PTS",
                    "-");

            ffmpeg = pb.start();
            videoStream = ffmpeg.getInputStream();
            dataInputStream = new DataInputStream(new BufferedInputStream(videoStream));
            isRunning.set(true);
        } catch (IOException e) {
            throw new RuntimeException("Failed to start FFmpeg", e);
        }
    }

    @Override
    public void play() {
        resume();
    }

    @Override
    public void pause() {
        if (!isRunning.get()) {
            return;
        }
        isPaused.set(true);
    }

    @Override
    public void resume() {
        if (!isRunning.get()) {
            return;
        }
        synchronized (pauseLock) {
            isPaused.set(false);
            pauseLock.notifyAll();
        }
    }

    @Override
    public void stop() throws IOException {
        synchronized (pauseLock) {
            isRunning.set(false);
            isPaused.set(false);
            pauseLock.notifyAll();
        }

        if (ffmpeg != null) {
            ffmpeg.destroy();
            try {
                ffmpeg.waitFor(2, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        closeStreams();
        frameBuffer.clear();
        playbackBuffer.clear();
        framesGrabbed = 0;
        currentPosition = 0;
    }

    @Override
    public void setInputSource(String inputSource) {
        this.inputSource = inputSource;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
        updateBufferSize();
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
        updateBufferSize();
    }

    private void updateBufferSize() {
        this.frameSize = this.width * this.height * 3;
        this.buffer = new byte[frameSize];
    }

    @Override
    public void setFramerate(int framerate) {
        this.framerate = framerate;
    }

    @Override
    public String getInputSource() {
        return inputSource;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getFramerate() {
        return framerate;
    }

    @Override
    public void seek(double timeInSeconds) throws IOException {
//        long targetFrame = (long) (timeInSeconds * framerate);
//        stop();
//        startWithSeek(timeInSeconds);
//        framesGrabbed = targetFrame;
//        currentPosition = targetFrame;
        stop();
        startWithSeek(timeInSeconds);
        framesGrabbed = (long)(timeInSeconds * getFrameRate());
    }

    @Override
    public void stepForward() {
        if (!isRunning.get()) {
            return;
        }
        synchronized (pauseLock) {
            stepMode.set(true);
            pauseLock.notifyAll();
        }
    }

    @Override
    public void stepBackward() {
        try {
            seek(getCurrentPosition() - (1.0 / getFrameRate()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setPlaybackSpeed(float speed) {
        this.playbackSpeed = speed;
        try {
            seek(getCurrentPosition());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public double getCurrentPosition() {
        return framesGrabbed / getFrameRate();
    }

    @Override
    public double getDuration() {
        try {
            Process process = new ProcessBuilder(
                    "ffprobe", "-v", "error",
                    "-show_entries", "format=duration",
                    "-of", "default=noprint_wrappers=1:nokey=1",
                    inputSource).start();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {
                String output = reader.readLine();
                return Double.parseDouble(output);
            }
        } catch (IOException | NumberFormatException e) {
            return 0;
        }
    }

    private void startWithSeek(double timeInSeconds) {
        try {
            String seekPosition = String.format("%.2f", timeInSeconds);
            pb = new ProcessBuilder(
                    "ffmpeg", "-ss", seekPosition,
                    "-i", inputSource, "-f", "image2pipe",
                    "-pix_fmt", "rgb24", "-threads", "" + ffmpegThreads,
                    "-vcodec", "rawvideo", "-s", width + "x" + height,
                    "-flush_packets", "1",
                    "-filter:v", "setpts=" + (1.0 / playbackSpeed) + "*PTS",
                    "-");

            ffmpeg = pb.start();
            videoStream = ffmpeg.getInputStream();
            dataInputStream = new DataInputStream(new BufferedInputStream(videoStream));
            isRunning.set(true);
            isPaused.set(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void closeStreams() throws IOException {
        if (dataInputStream != null) {
            dataInputStream.close();
        }
        if (videoStream != null) {
            videoStream.close();
        }
    }
}
