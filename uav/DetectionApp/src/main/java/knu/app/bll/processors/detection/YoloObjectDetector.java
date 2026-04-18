package knu.app.bll.processors.detection;

import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import knu.app.bll.utils.MatWrapper;
import knu.app.bll.utils.processors.DetectionResult;
import knu.app.grpc.yolo.*;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

public class YoloObjectDetector implements ObjectDetector {

    private final ManagedChannel channel;
    private final YoloDetectionServiceGrpc.YoloDetectionServiceStub asyncStub;
    private final YoloDetectionServiceGrpc.YoloDetectionServiceBlockingStub blockingStub;
    private StreamObserver<ImageFrame> requestObserver;
    private final AtomicReference<DetectionResult> lastResult = new AtomicReference<>(new DetectionResult());
    private String sessionId = "default";
    private int jpegQuality = 85;
    private long waitDuration = 500;

    public YoloObjectDetector(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .maxInboundMessageSize(20 * 1024 * 1024)
                .usePlaintext()
                .build();

        asyncStub = YoloDetectionServiceGrpc.newStub(channel);
        blockingStub = YoloDetectionServiceGrpc.newBlockingStub(channel);
    }

    // =============================
    // CONFIG
    // =============================

    public void sendConfig(YoloConfig config) {
        blockingStub.setConfig(SetConfigRequest.newBuilder()
                .setConfig(config)
                .setSessionId(sessionId)
                .build());
    }

    public YoloConfig getConfig() {
            try {
                return blockingStub
                        .withDeadlineAfter(1, TimeUnit.SECONDS) // Ждем максимум секунду
                        .getConfig(com.google.protobuf.Empty.getDefaultInstance())
                        .getConfig();
            } catch (Exception e) {
                System.err.println("YOLO Server unreachable: " + e.getMessage());
                return YoloConfig.getDefaultInstance();
            }
    }

    // =============================
    // STREAM CONTROL
    // =============================

    public void startStreaming() {
        blockingStub.startStreaming(StartStreamingRequest.newBuilder()
                .setSessionId(sessionId)
                .build());

        requestObserver = asyncStub.streamTrack(new StreamObserver<TrackingResult>() {
            @Override
            public void onNext(TrackingResult result) {
                lastResult.set(convertResult(result));
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("Stream completed");
            }
        });
    }

    public void stopStreaming() {
        if (requestObserver != null) {
            requestObserver.onCompleted();
        }

        blockingStub.stopStreaming(
                StopStreamingRequest.newBuilder()
                        .setSessionId(sessionId)
                        .build()
        );
    }

    public void restartConnection() {
        stopStreaming();
        startStreaming();
    }

    // =============================
    // DETECT (STREAM MODE)
    // =============================

    @Override
    public DetectionResult detect(MatWrapper matWrapper) {
        return detectSyncAsfut(matWrapper.mat);
//        return detectSyncAs(matWrapper.mat());
//        return detectSync(matWrapper.mat());
//        return asyncDetect(matWrapper.mat());
//        return detectBlocking(matWrapper.mat(), 150);
    }

    int cc = 12;

    private final ExecutorService yoloPool = Executors.newFixedThreadPool(cc);
    private final Semaphore inflight = new Semaphore(cc);




    public DetectionResult detectSync(Mat frame) {
        byte[] jpeg = matToJpegBytes(frame);
        ImageFrame frameMsg = ImageFrame.newBuilder()
                .setSessionId("default")
                .setImage(ByteString.copyFrom(jpeg))
                .setTimestamp(System.currentTimeMillis())
                .build();

        yoloPool.submit(() -> {
            try {
                TrackingResult result = blockingStub
                        .withDeadlineAfter(250, TimeUnit.MILLISECONDS)
                        .detectSingle(frameMsg);

                lastResult.set(convertResult(result));
            } catch (Exception ignored) {
            } finally {
                inflight.release();
            }
        });
        return lastResult.get();
    }

    public DetectionResult detectSyncAsfut(Mat frame) {
        if (!inflight.tryAcquire()) {
            return lastResult.get();
        }

        try {
            byte[] jpeg = matToJpegBytes(frame);
            ImageFrame frameMsg = ImageFrame.newBuilder()
                    .setSessionId("default")
                    .setImage(ByteString.copyFrom(jpeg))
                    .setTimestamp(System.currentTimeMillis())
                    .build();

            CompletableFuture<DetectionResult> future = CompletableFuture.supplyAsync(() -> {
                try {
                    TrackingResult result = blockingStub
                            .withDeadlineAfter(waitDuration, TimeUnit.MILLISECONDS)
                            .detectSingle(frameMsg);
                    return convertResult(result);
                } catch (Exception e) {
                    return lastResult.get();
                } finally {
                    inflight.release();
                }
            }, yoloPool);

            DetectionResult detection = future.get();
            lastResult.set(detection);
            return detection;

        } catch (Exception e) {
            inflight.release();
            return lastResult.get();
        }
    }


    public DetectionResult detectSyncAs(Mat frame) {
        if (!inflight.tryAcquire()) {
            return lastResult.get();
        }

            byte[] jpeg = matToJpegBytes(frame);
            ImageFrame frameMsg = ImageFrame.newBuilder()
                    .setSessionId("default")
                    .setImage(ByteString.copyFrom(jpeg))
                    .setTimestamp(System.currentTimeMillis())
                    .build();

            yoloPool.submit(() -> {
                try {
                    TrackingResult result = blockingStub
                            .withDeadlineAfter(150, TimeUnit.MILLISECONDS)
                            .detectSingle(frameMsg);

                    lastResult.set(convertResult(result));
                } catch (Exception ignored) {
                } finally {
                    inflight.release();
                }
            });
            return lastResult.get();

    }


    private DetectionResult asyncDetect(Mat frame) {
        if (requestObserver == null) {
            return new DetectionResult();
        }

        byte[] jpeg = matToJpegBytes(frame);

        ImageFrame frameMsg = ImageFrame.newBuilder()
                .setSessionId(sessionId)
                .setImage(ByteString.copyFrom(jpeg))
                .setTimestamp(System.currentTimeMillis())
                .build();

        requestObserver.onNext(frameMsg);

        return lastResult.get();
    }


    // =============================
    // UTILS
    // =============================

    private DetectionResult convertResult(TrackingResult response) {
        List<Rect> rects = new LinkedList<>();
        List<Double> scores = new LinkedList<>();
        List<String> names = new LinkedList<>();

        for (Detection d : response.getDetectionsList()) {
            rects.add(new Rect(
                    (int) d.getX(),
                    (int) d.getY(),
                    (int) d.getWidth(),
                    (int) d.getHeight()
            ));
            scores.add((double) d.getConfidence());
            names.add(d.getClassName());
        }

        return new DetectionResult(rects, scores, names);
    }

    private byte[] matToJpegBytes(Mat mat) {
        BytePointer buf = new BytePointer();
        IntPointer params = new IntPointer(opencv_imgcodecs.IMWRITE_JPEG_QUALITY, jpegQuality);

        boolean ok = opencv_imgcodecs.imencode(".jpg", mat, buf, params);
        if (!ok) {
            buf.deallocate();
            throw new RuntimeException("JPEG encode failed");
        }

        byte[] bytes = new byte[(int) buf.limit()];
        buf.get(bytes);
        buf.deallocate();

        return bytes;
    }

    public void shutdown() {
        channel.shutdown();
    }

    public void setJpegQuality(int jpegQuality) {
        this.jpegQuality = jpegQuality;
    }

    public void setWaitDuration(long waitDuration) {
        this.waitDuration = waitDuration;
    }
}
