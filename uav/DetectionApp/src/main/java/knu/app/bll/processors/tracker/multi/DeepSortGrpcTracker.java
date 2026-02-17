package knu.app.bll.processors.tracker.multi;

import java.util.List;

import com.google.protobuf.Empty;
import knu.app.bll.utils.Utils;
import knu.app.bll.utils.processors.DetectionResult;
import knu.app.bll.utils.processors.TrackedObject;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import knu.app.grpc.deepsort.*;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;

public class DeepSortGrpcTracker implements MultiObjectTracker {

    private final ManagedChannel channel;
    private final DeepSortServiceGrpc.DeepSortServiceBlockingStub stub;
    private TrackerConfig config;

    public DeepSortGrpcTracker(String host, int port) {
        this.channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        this.stub = DeepSortServiceGrpc.newBlockingStub(channel);
    }

    public boolean init(float maxIouDistance, int maxAge, int nInit, float nmsMaxOverlap, float maxCosineDistance,
                        int nnBudget, boolean gatingOnlyPosition, String overrideTrackClass, String embedder, boolean half, boolean bgr,
                        boolean embedderGpu, String embedderModelName, String embedderWts, boolean polygon, String today
    ) {
        this.config = TrackerConfig.newBuilder()
                .setMaxIouDistance(maxIouDistance)
                .setMaxAge(maxAge)
                .setNInit(nInit)
                .setNmsMaxOverlap(nmsMaxOverlap)
                .setMaxCosineDistance(maxCosineDistance)
                .setNnBudget(nnBudget)
                .setGatingOnlyPosition(gatingOnlyPosition)
                .setOverrideTrackClass(overrideTrackClass != null ? overrideTrackClass : "")
                .setEmbedder(embedder != null ? embedder : "mobilenet")
                .setHalf(half)
                .setBgr(bgr)
                .setEmbedderGpu(embedderGpu)
                .setEmbedderModelName(embedderModelName != null ? embedderModelName : "")
                .setEmbedderWts(embedderWts != null ? embedderWts : "")
                .setPolygon(polygon)
                .setToday(today != null ? today : "")
                .build();

        InitResponse resp = stub.init(config);
        return resp.getSuccess();
    }

    @Override
    public boolean init() {
        return init(0.7f, 30, 3, 1.0f, 0.2f,
                100, false, "", "mobilenet", true,
                true, true, "", "", false, ""
        );
    }

    @Override
    public List<TrackedObject> update(Mat frame, List<DetectionResult> detResult) {
        // Конвертируем Mat в JPEG bytes
        byte[] frameBytes = Utils.matToJpegBytes(frame);

        // Конвертируем DetectionResult в proto
        FrameRequest.Builder reqBuilder = FrameRequest.newBuilder().setSessionId("default").setTimestamp(System.currentTimeMillis()).setImage(com.google.protobuf.ByteString.copyFrom(frameBytes));

        for (DetectionResult det : detResult)
            for (int i = 0; i < det.getRects().size(); i++) {
                Rect r = det.getRects().get(i);
                double score = det.getScores().get(i);
                String name = (det.getNames().size() > i) ? det.getNames().get(i) : "";

                Detection protoDet = Detection.newBuilder().setClassName(name)
//                        .setClassId(i)
                        .setConfidence((float) score)
                        .setX(r.x())
                        .setY(r.y())
                        .setWidth(r.width())
                        .setHeight(r.height())
                        .build();

                reqBuilder.addDetections(protoDet);
            }


        UpdateResponse resp = stub.update(reqBuilder.build());

        // Конвертируем ответ в список TrackedObject (доменный класс)
        return resp.getTracksList().stream().map(t -> {
            TrackedObject obj = new TrackedObject(new org.bytedeco.opencv.opencv_core.Rect((int) t.getX(), (int) t.getY(), (int) t.getWidth(), (int) t.getHeight()), t.getConfidence(), null // tracker можно опционально положить
            );
            obj.setId(Integer.parseInt(t.getId()));
            return obj;
        }).toList();
    }

    @Override
    public void close() {
        try {
            stub.close(SessionRequest.newBuilder().setSessionId("default").build());
            channel.shutdownNow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getBufferCapacity() {
        BufferCapacityResponse resp = stub.getBufferCapacity(SessionRequest.newBuilder().setSessionId("default").build());
        if (resp == null) return -1;
        return resp.getCapacity();
    }

    @Override
    public void clear() {
        DeepSortServiceGrpc.DeepSortServiceBlockingStub stub = DeepSortServiceGrpc.newBlockingStub(channel);
        SessionRequest request = SessionRequest.newBuilder().build();
        stub.clear(request);
        System.out.println("DeepSort buffer cleared!");
    }
}
