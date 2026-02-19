package knu.app.bll.processors.detection;

import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import knu.app.bll.utils.MatWrapper;
import knu.app.bll.utils.processors.DetectionResult;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Size;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import ssd.detector.SsdDetectionServiceGrpc;
import ssd.detector.SsdDetector;

public class SsdObjectDetector implements ObjectDetector {

        private final ManagedChannel channel;
        private final SsdDetectionServiceGrpc.SsdDetectionServiceBlockingStub blockingStub;
        private final SsdDetectionServiceGrpc.SsdDetectionServiceStub asyncStub;

        private int inputWidth = 640;
        private int inputHeight = 480;
        private int jpegQuality = 85;
        private SsdDetector.SsdConfig config;
        private final String sessionId = "java_detector";

        public SsdObjectDetector(String host, int port) {
            channel = ManagedChannelBuilder.forAddress(host, port)
                    .usePlaintext()
                    .maxInboundMessageSize(50 * 1024 * 1024)
                    .build();

            blockingStub = SsdDetectionServiceGrpc.newBlockingStub(channel);
            asyncStub = SsdDetectionServiceGrpc.newStub(channel);
            config = SsdDetector.SsdConfig.newBuilder().setInputWidth(inputWidth).setInputHeight(inputHeight).build();
        }

        public void setConfig(SsdDetector.SsdConfig config) {
            this.config = config;
            blockingStub.setConfig(config);
        }

        public SsdDetector.SsdConfig getConfig() {
            return blockingStub.getConfig(com.google.protobuf.Empty.getDefaultInstance()).getConfig();
        }

        public void startStreaming() {
            blockingStub.startStreaming(SsdDetector.SessionRequest.newBuilder().setSessionId(sessionId).build());
        }

        public void stopStreaming() {
            blockingStub.stopStreaming(SsdDetector.SessionRequest.newBuilder().setSessionId(sessionId).build());
        }

        public void restartConnection() {
            blockingStub.restartConnection(SsdDetector.SessionRequest.newBuilder().setSessionId(sessionId).build());
        }



        private byte[] matToJpeg(Mat mat) {
            Mat resized = new Mat();
            opencv_imgproc.resize(mat, resized, new Size(inputWidth, inputHeight));

            IntPointer params = new IntPointer(opencv_imgcodecs.IMWRITE_JPEG_QUALITY, jpegQuality);
            BytePointer buf = new BytePointer();
            boolean ok = opencv_imgcodecs.imencode(".jpg", resized, buf, params);
            if (!ok) throw new RuntimeException("Failed to encode Mat to JPEG");

            byte[] bytes = new byte[(int) buf.limit()];
            buf.get(bytes);
            buf.deallocate();
            resized.release();
            return bytes;
        }

        public void shutdown() {
            channel.shutdown();
        }

        public void setInputWidth(int width) { this.inputWidth = width; }
        public void setInputHeight(int height) { this.inputHeight = height; }
        public void setJpegQuality(int quality) { this.jpegQuality = quality; }

    @Override
    public DetectionResult detect(MatWrapper matWrapper)  {
        byte[] jpeg = matToJpeg(matWrapper.mat);

        CountDownLatch latch = new CountDownLatch(1);
        List<org.bytedeco.opencv.opencv_core.Rect> rects = new LinkedList<>();
        List<Double> scores = new LinkedList<>();
        List<String> names = new LinkedList<>();

        StreamObserver<SsdDetector.FrameRequest> requestObserver = asyncStub.streamDetect(new StreamObserver<SsdDetector.TrackingResult>() {
            @Override
            public void onNext(SsdDetector.TrackingResult result) {
                result.getDetectionsList().forEach(d -> {
                    int x = (int) d.getX();
                    int y = (int) d.getY();
                    int w = (int) d.getWidth();
                    int h = (int) d.getHeight();
                    rects.add(new org.bytedeco.opencv.opencv_core.Rect(x, y, w, h));
                    scores.add((double) d.getConfidence());
                    names.add(d.getClassName());
                });
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                latch.countDown();
            }
        });

        // Отправляем кадр
        requestObserver.onNext(SsdDetector.FrameRequest.newBuilder()
                .setSessionId(sessionId)
                .setImage(ByteString.copyFrom(jpeg))
                .setTimestamp(System.currentTimeMillis())
                .build());
        requestObserver.onCompleted();

        try {
            latch.await(3, TimeUnit.SECONDS); // ждём ответ
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new DetectionResult(rects, scores, names);
    }
}
