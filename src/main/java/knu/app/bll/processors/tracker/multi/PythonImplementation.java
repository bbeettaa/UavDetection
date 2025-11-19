package knu.app.bll.processors.tracker.multi;

import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.ArrayList;
import java.util.List;
import knu.app.bll.utils.processors.DetectionResult;
import knu.app.bll.utils.processors.TrackedObject;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import tracker.BytetrackObjecttracker.Detection;
import tracker.BytetrackObjecttracker.FrameDetections;
import tracker.BytetrackObjecttracker.InitResponse;
import tracker.BytetrackObjecttracker.TrackerParams;
import tracker.BytetrackObjecttracker.TracksResponse;
import tracker.TrackerServiceGrpc;

public class PythonImplementation implements MultiObjectTracker {

  private final ManagedChannel channel;
  private final TrackerServiceGrpc.TrackerServiceBlockingStub stub;

  private int tackBuffer = 100;

  public PythonImplementation(String host, int port) {
    channel = ManagedChannelBuilder.forAddress(host, port)
        .maxInboundMessageSize(10 * 1024 * 1024).usePlaintext().build();

    stub = TrackerServiceGrpc.newBlockingStub(channel);
  }


  public boolean init(float trackThresh, float highThresh, float matchThresh, int trackBuffer) {
    TrackerParams req = TrackerParams.newBuilder().setTrackThresh(trackThresh)
        .setHighThresh(highThresh).setMatchThresh(matchThresh).setTrackBuffer(trackBuffer).build();

    InitResponse resp = stub.initTracker(req);

    if (resp.getOk()) {
      this.tackBuffer = trackBuffer;
    }
    return resp.getOk();
  }

  @Override
  public List<TrackedObject> update(Mat frame, List<DetectionResult> detResults) {
    List<Detection> dets = new ArrayList<>();
    int imgWidth = frame.cols();
    int imgHeight = frame.rows();

    byte[] imgBytes = matToJpegBytes(frame);

    // Конвертируем DetectionResult -> gRPC Detection
    for (DetectionResult dr : detResults) {
      List<Rect> rects = dr.getRects();
      List<Double> scores = dr.getScores();

      for (int i = 0; i < rects.size(); i++) {
        Rect r = rects.get(i);
        int x = r.x();
        int y = r.y();
        int width = r.width();
        int height = r.height();
        double score = i < scores.size() ? scores.get(i) : 1.0;

        dets.add(Detection.newBuilder()
            .setX1((float) x)
            .setY1((float) y)
            .setX2((float) (x + width))
            .setY2((float) (y + height))
            .setScore((float) score)
            .build());
      }
    }

    FrameDetections fd = FrameDetections
        .newBuilder()
        .setImage(ByteString.copyFrom(imgBytes))
        .addAllDets(dets)
        .setImgWidth(imgWidth).
        setImgHeight(imgHeight)
        .build();

    // Отправляем на сервер
    TracksResponse response = stub.updateTracks(fd);

    // Конвертируем ответ в список TrackedObject
    List<TrackedObject> trackedObjects = new ArrayList<>();
    response.getTracksList().forEach(track -> {
      Rect bbox = new Rect((int) track.getX1(), (int) track.getY1(),
          (int) (track.getX2() - track.getX1()), (int) (track.getY2() - track.getY1()));
      TrackedObject obj = new TrackedObject(bbox);
      obj.setId(track.getId());
      obj.setScore(1.0f); // можно передавать средний score, если нужно
      trackedObjects.add(obj);
    });

    return trackedObjects;
  }


  private byte[] matToJpegBytes(Mat mat) {
    Mat resize = new Mat();

    opencv_imgproc.resize(mat, resize,
        new org.bytedeco.opencv.opencv_core.Size(640, 640));

    IntPointer jpegParams = new IntPointer(opencv_imgcodecs.IMWRITE_JPEG_QUALITY, 100);

    BytePointer buf = new BytePointer();
    boolean ok = opencv_imgcodecs.imencode(".jpg", resize, buf, jpegParams);
    if (!ok) {
      buf.deallocate();
      throw new RuntimeException("Failed to encode Mat to JPEG");
    }

    byte[] bytes = new byte[(int) buf.limit()];
    buf.get(bytes);
    buf.deallocate();

    return bytes;
  }

  @Override
  public void close() {
    if (channel != null) {
      channel.shutdown();
    }
  }

  @Override
  public int getBufferCapacity() {
    return this.tackBuffer;
  }
}
