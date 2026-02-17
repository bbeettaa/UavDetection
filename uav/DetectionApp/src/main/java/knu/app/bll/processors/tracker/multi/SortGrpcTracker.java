package knu.app.bll.processors.tracker.multi;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import knu.app.bll.utils.Utils;
import knu.app.bll.utils.processors.DetectionResult;
import knu.app.bll.utils.processors.TrackedObject;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import tracker.sort.SortServiceGrpc;

import java.util.ArrayList;
import java.util.List;

public class SortGrpcTracker implements MultiObjectTracker {

    private final SortServiceGrpc.SortServiceBlockingStub stub;
    private final ManagedChannel channel;

    public SortGrpcTracker(String host, int port) {
        this.channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        this.stub = SortServiceGrpc.newBlockingStub(channel);
    }

    public boolean init(int lostTrackBuffer, float trackActivationThreshold,
                        int minimumConsecutiveFrames, float minimumIouThreshold) {
        tracker.sort.SortTracker.TrackerConfig config = tracker.sort.SortTracker.TrackerConfig.newBuilder()
                .setLostTrackBuffer(lostTrackBuffer)
                .setTrackActivationThreshold(trackActivationThreshold)
                .setMinimumConsecutiveFrames(minimumConsecutiveFrames)
                .setMinimumIouThreshold(minimumIouThreshold)
                .build();
        tracker.sort.SortTracker.InitResponse response = stub.init(config);
        return response.getSuccess();
    }

    @Override
    public boolean init() {
        return init(30, 0.25f, 3, 0.3f);
    }

    @Override
    public List<TrackedObject> update(Mat frame, List<DetectionResult> detResults) {
        byte[] imageBytes = Utils.matToJpegBytes(frame);
        tracker.sort.SortTracker.FrameRequest.Builder requestBuilder =
                tracker.sort.SortTracker.FrameRequest.newBuilder()
                        .setImage(com.google.protobuf.ByteString.copyFrom(imageBytes));

        for (DetectionResult detResult : detResults) {
            List<Rect> rects = detResult.getRects();
            List<Double> scores = detResult.getScores();
            List<String> names = detResult.getNames();

            for (int i = 0; i < rects.size(); i++) {
                Rect rect = rects.get(i);
                requestBuilder.addDetectionsBuilder()
                        .setX(rect.x())
                        .setY(rect.y())
                        .setWidth(rect.width())
                        .setHeight(rect.height())
                        .setConfidence(scores.get(i).floatValue())
                        .setClassName(names.get(i));
            }

        }

        tracker.sort.SortTracker.UpdateResponse response = stub.update(requestBuilder.build());
        List<TrackedObject> tracks = new ArrayList<>();
        for (tracker.sort.SortTracker.TrackedObject protoTrack : response.getTracksList()) {
            Rect rect = new Rect(
                    (int) protoTrack.getX(),
                    (int) protoTrack.getY(),
                    (int) protoTrack.getWidth(),
                    (int) protoTrack.getHeight()
            );
            TrackedObject track = new TrackedObject(rect);
            track.setId(Integer.parseInt(protoTrack.getId()));  // если id строка
            track.setScore((float) protoTrack.getConfidence());
            track.setState(TrackedObject.TrackState.Confirmed); // или по protoTrack
            tracks.add(track);
        }
        return tracks;

    }


    @Override
    public void close() {
        stub.close(tracker.sort.SortTracker.SessionRequest.newBuilder().build());
        channel.shutdown();
    }

    @Override
    public int getBufferCapacity() {
        tracker.sort.SortTracker.BufferCapacityResponse response = stub.getBufferCapacity(tracker.sort.SortTracker.SessionRequest.newBuilder().build());
        return response.getCapacity();
    }

    @Override
    public void clear() {
        stub.clear(tracker.sort.SortTracker.SessionRequest.newBuilder().build());
    }
}
