package knu.app.utils.video;

import knu.app.ui.tools.StatisticDisplayUI;
import org.bytedeco.javacv.*;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.bytedeco.javacv.Frame;

import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;


import java.io.*;


public class NativeFFmpegVideoSource implements VideoSource {
    private static final Logger logger = Logger.getLogger(NativeFFmpegVideoSource.class.getName());
    private String inputSource;

    private final AtomicBoolean isRunning = new AtomicBoolean(false);
    private final AtomicBoolean isPaused = new AtomicBoolean(false);
    private final Object pauseLock = new Object();

    private int width;
    private int height;
    int frameSize;
    private int framerate;
    private byte[] buffer;

    private final Java2DFrameConverter converter;
    private InputStream videoStream;
    private DataInputStream dataInputStream;

    private Process ffmpeg;

    private final int ffmpegThreads;

    public NativeFFmpegVideoSource(int width, int height, int framerate, int ffmpegThreads) {
        this.width = width;
        this.height = height;
        this.framerate = framerate;
        checkFFmpegAvailability();

        this.frameSize = this.width * this.height * 3;
        this.buffer = new byte[frameSize];

        this.ffmpegThreads = ffmpegThreads;
        converter = new Java2DFrameConverter();
    }

    public NativeFFmpegVideoSource(int width, int height, String inputSource, int framerate, int ffmpegThreads) {
        this(width, height, framerate, ffmpegThreads);
        this.inputSource = inputSource;
    }

    public NativeFFmpegVideoSource(String inputSource, int framerate) {
        this(640, 480, inputSource, framerate, 2);
    }

    public NativeFFmpegVideoSource(int framerate) {
        this(640, 480, framerate, 2);
    }

    public NativeFFmpegVideoSource( ) {
        this(640, 480, 30, 2);
    }


    private void checkFFmpegAvailability() {
        try {
            Process process = new ProcessBuilder("ffmpeg", "-version").start();
            if (process.waitFor() != 0) {
                throw new ClassNotFoundException("FFmpeg not found");
            }
            // Read version from stderr (FFmpeg outputs version info to stderr)
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                logger.info("FFmpeg version: " + reader.readLine());
            }
        } catch (InterruptedException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Frame grab() throws IOException {
        int offset = 0;

        while (offset < frameSize) {
            int bytesRead = dataInputStream.read(buffer, offset, frameSize - offset);
            if (bytesRead == -1) return null; // EOF
            offset += bytesRead;
        }

//        dis.readFully(buffer);
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        WritableRaster raster = img.getRaster();
        raster.setDataElements(0, 0, width, height, buffer);

//        Mat mat = new Mat(height, width, CvType.makeType(1,3));
//        mat.data().put(buffer);

//        Mat bgr = new Mat();
//        cvtColor(mat, bgr, opencv_imgproc.COLOR_RGB2BGR);
        return converter.convert(img);
//        return converter.convert();
    }


    @Override
    public double getFrameRate() {
        return framerate; // Can be parsed from FFmpeg output
    }

    @Override
    public void start() {
        try {
            // Важно! Принудительный сброс буфера
            // Минимизация буферизации
            // Контроль размера буфера
            ProcessBuilder pb = new ProcessBuilder(
                    "ffmpeg", "-re",
                    "-i", inputSource, "-f", "image2pipe",
                    "-pix_fmt", "rgb24", "-threads", "" + ffmpegThreads,
                    "-c:v", "libx264", "-r", framerate + "",
                    "-vcodec", "rawvideo", "-s", width + "x" + height,
                    "-flush_packets", "1",  // Важно! Принудительный сброс буфера
                    "-avioflags", "direct", // Минимизация буферизации
                    "-bufsize", 0 + "k", // Контроль размера буфера
                    "-");
//        pb.redirectError(ProcessBuilder.Redirect.INHERIT);
            ffmpeg = pb.start();
            videoStream = ffmpeg.getInputStream();
            dataInputStream = new DataInputStream(new BufferedInputStream(videoStream));

            logger.info("FFmpeg process started");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pause() throws IOException {
        if (!isRunning.get() || isPaused.get()) return;

        synchronized (pauseLock) {
            isPaused.set(true);
//            try {
                // Ожидаем завершения процесса (но он продолжает работать в фоне)
//                ffmpeg.waitFor(100, TimeUnit.MILLISECONDS);
                logger.info("FFmpeg process paused");
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
        }
    }

    @Override
    public void resume()   {
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

        logger.info("FFmpeg process stopped");
    }


    public void setInputSource(String inputSource) {
        this.inputSource = inputSource;
    }

    public void setWidth(int width) {
        this.width = width;
        this.frameSize = this.width * this.height * 3;
        this.buffer = new byte[frameSize];
    }

    public void setHeight(int height) {
        this.height = height;
        this.frameSize = this.width * this.height * 3;
        this.buffer = new byte[frameSize];
    }

    public void setFramerate(int framerate) {
        this.framerate = framerate;
    }


    public String getInputSource() {
        return inputSource;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getFramerate() {
        return framerate;
    }
}