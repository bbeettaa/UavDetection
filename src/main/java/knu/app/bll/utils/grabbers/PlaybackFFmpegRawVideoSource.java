package knu.app.bll.utils.grabbers;

import knu.app.bll.utils.Utils;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

import java.util.ArrayList;
import java.util.List;

public class PlaybackFFmpegRawVideoSource implements PlaybackControlVideoSource, VideoSource {
    private static final Logger logger = Logger.getLogger(PlaybackFFmpegRawVideoSource.class.getName());
    private String inputSource;
    private final AtomicBoolean isRunning = new AtomicBoolean(false);
    private final AtomicBoolean isPaused = new AtomicBoolean(false);
    private final Object pauseLock = new Object();
    private int width;
    private int height;
    private int frameSize;
    private int framerate;
    private byte[] buffer;
    private final Java2DFrameConverter converter;
    private InputStream videoStream;
    private BufferedReader errorReader;
    private DataInputStream dataInputStream;
    private Process ffmpeg;
    private final int ffmpegThreads;
    private long seekTime = 0;
    private float playbackSpeed = 1.0f;
    private long duration = 0;
    private int timestampIndex=0;

    private float fps = 0;
    private long out_time_ms = 0;
    private long out_time;
    private String bitrate = "0kbits/s";
    private int dropFrames = 0;

    public PlaybackFFmpegRawVideoSource(int width, int height, int framerate, int ffmpegThreads) {
        this.width = width;
        this.height = height;
        this.framerate = framerate;
        checkFFmpegAvailability();
        this.frameSize = this.width * this.height * 3;
        this.buffer = new byte[frameSize];
        this.ffmpegThreads = ffmpegThreads;
        converter = new Java2DFrameConverter();
    }

    public PlaybackFFmpegRawVideoSource(int width, int height, String inputSource, int framerate, int ffmpegThreads) {
        this(width, height, framerate, ffmpegThreads);
        this.inputSource = inputSource;
    }

    public PlaybackFFmpegRawVideoSource(String inputSource, int framerate, int ffmpegThreads) {
        this(640, 480, inputSource, framerate, ffmpegThreads);
    }

    public PlaybackFFmpegRawVideoSource(String inputSource, int framerate) {
        this(inputSource, framerate, 3);
    }

    public PlaybackFFmpegRawVideoSource(int framerate) {
        this(640, 480, framerate, 3);
    }

    public PlaybackFFmpegRawVideoSource() {
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
    public Frame grab() throws Exception {
        return grabRowVideo();
    }

    public Frame grabRowVideo() throws Exception {
        int offset = 0;
        while (offset < frameSize) {
            int bytesRead = dataInputStream.read(buffer, offset, frameSize - offset);
            if (bytesRead == -1) return null;
            offset += bytesRead;
        }
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        WritableRaster raster = img.getRaster();
        raster.setDataElements(0, 0, width, height, buffer);

        timestampIndex++;
        return converter.convert(img);
    }

    @Override
    public double getFrameRate() {
        return framerate;
    }

    @Override
    public void start() {
        try {
            startRawVideoProcess();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startRawVideoProcess() throws IOException {
        List<String> command = new ArrayList<>();
        command.add("ffmpeg");
        command.add("-re");
        if (seekTime > 0) {
            command.add("-ss");
            command.add(Utils.formatTimestamp(seekTime));
        }
        command.add("-i");
        command.add(inputSource);
        command.add("-pix_fmt");
        command.add("rgb24");
        command.add("-threads");
        command.add(String.valueOf(ffmpegThreads));
        command.add("-r");
        command.add(String.valueOf(framerate));
        command.add("-vcodec");
        command.add("rawvideo");
        command.add("-f");
        command.add("image2pipe");
        command.add("-s");
        command.add(width + "x" + height);
        command.add("-flush_packets");
        command.add("1");
        command.add("-avioflags");
        command.add("direct");
        command.add("-bufsize");
        command.add("0k");
        if (playbackSpeed != 1.0f) {
            command.add("-vf");
            command.add("setpts=" + (1.0f / playbackSpeed) + "*PTS");
        }
        command.add("-progress");
        command.add("pipe:2"); // write progress in stderr
        command.add("-nostats");
        command.add("-");

        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectError(ProcessBuilder.Redirect.PIPE);
        ffmpeg = pb.start();
        videoStream = ffmpeg.getInputStream();
        dataInputStream = new DataInputStream(new BufferedInputStream(videoStream));
        errorReader = new BufferedReader(new InputStreamReader(ffmpeg.getErrorStream()));
        isRunning.set(true);
        logger.info("FFmpeg process started");
//        ptsTimestamps = extractFrameTimestamps(inputSource);
        startProgressParserThread();
    }

    private void startProgressParserThread() {
        new Thread(() -> {
            try {
                String line;
                while ((line = errorReader.readLine()) != null) {
                    if (line.startsWith("fps=")) fps = Float.parseFloat(parseString(line));

                    if (line.startsWith("bitrate=")) bitrate = parseString(line);

                    if (line.startsWith("out_time_ms=")) out_time_ms = Long.parseLong(parseString(line));

                    if (line.startsWith("drop_frames=")) dropFrames = Integer.parseInt(parseString(line));

                    if (duration == 0 && line.startsWith("total_size")) duration = Integer.parseInt(parseString(line));

                }
            } catch (IOException e) {
//                e.printStackTrace();
            }
        }).start();
    }

    private String parseString(String line) {
        if (line.contains("=N/A")) return "0";
        return line.split("=")[1];
    }

    @Override
    public void play() {
        if (!isRunning.get()) {
            start();
        } else if (isPaused.get()) {
            resume();
        }
    }

    @Override
    public void pause() {
        if (!isRunning.get() || isPaused.get()) return;
        synchronized (pauseLock) {
            isPaused.set(true);
            logger.info("FFmpeg process paused");
        }
    }

    @Override
    public void resume() {
        if (!isRunning.get() || !isPaused.get()) return;
        synchronized (pauseLock) {
            isPaused.set(false);
            pauseLock.notifyAll();
            logger.info("FFmpeg process resumed");
        }
    }

    @Override
    public void stop() throws IOException {
        isRunning.set(false);
        isPaused.set(false);
        if (ffmpeg != null) {
            ffmpeg.destroy();
            try {
                ffmpeg.waitFor(2, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        if (dataInputStream != null) {
            dataInputStream.close();
        }
        if (videoStream != null) {
            videoStream.close();
        }
        seekTime = 0;
        duration = 0;
        logger.info("FFmpeg process stopped");
    }

    @Override
    public void seek(long timestamp) throws IOException {
        if (isRunning.get()) {
            stop();
            this.seekTime = timestamp;
            this.timestampIndex=Integer.parseInt(timestamp+"");
            start();
        }
    }

    @Override
    public void stepForward() {
        try {
            long current = getCurrentPosition();
            seek(current + (long) (1000.0 / framerate));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void stepBackward() {
        try {
            long current = getCurrentPosition();
            long step = (long) (1000.0 / framerate);
            seek(Math.max(0, current - step));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setPlaybackSpeed(float speed) {
        if (speed <= 0) throw new IllegalArgumentException("Speed must be positive");
        this.playbackSpeed = speed;
        if (isRunning.get()) {
            try {
                stop();
                start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public long getCurrentPosition() {
        return out_time_ms;
    }

    @Override
    public long getDuration() {
        if (!isRunning.get()) return 0;
        if (duration == 0) {
            fetchDuration();
        }
        return duration;
    }

    private void fetchDuration() {
        try {
            Process process = new ProcessBuilder("ffprobe", "-v", "error", "-show_entries", "format=duration", "-of", "default=noprint_wrappers=1:nokey=1", inputSource).start();
            process.waitFor();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line = reader.readLine();
                if (line != null) {
                    duration = (long) (Double.parseDouble(line) * 1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setInputSource(String inputSource) {
        this.inputSource = inputSource;
    }

    @Override
    public String getInputSource() {
        return inputSource;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
        this.frameSize = this.width * this.height * 3;
        this.buffer = new byte[frameSize];
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
        this.frameSize = this.width * this.height * 3;
        this.buffer = new byte[frameSize];
    }

    @Override
    public void setFramerate(int framerate) {
        this.framerate = framerate;
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
    public boolean isRunning() {
        return isRunning.get();
    }

    @Override
    public boolean isPaused() {
        return isPaused.get();
    }


}