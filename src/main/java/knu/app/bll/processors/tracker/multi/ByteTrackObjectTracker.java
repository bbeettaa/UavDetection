//package knu.app.bll.processors.tracker.multi;
//
//import io.grpc.ManagedChannel;
//import io.grpc.ManagedChannelBuilder;
//import java.util.ArrayList;
//import java.util.List;
//import knu.app.bll.processors.tracker.single.ObjectTracker;
//import knu.app.bll.utils.processors.DetectionResult;
//import knu.app.bll.utils.processors.TrackedObject;
//import org.bytedeco.opencv.opencv_core.Mat;
//import org.bytedeco.opencv.opencv_core.Point2f;
//import org.bytedeco.opencv.opencv_core.Rect;
//import tracker.BytetrackObjecttracker.Detection;
//import tracker.BytetrackObjecttracker.FrameDetections;
//import tracker.BytetrackObjecttracker.InitResponse;
//import tracker.BytetrackObjecttracker.TrackerParams;
//import tracker.BytetrackObjecttracker.TracksResponse;
//import tracker.TrackerServiceGrpc;
//
//
//public class ByteTrackObjectTracker implements MultiObjectTracker {
//
//  private final ManagedChannel channel;
//  private final TrackerServiceGrpc.TrackerServiceBlockingStub stub;
//
//  public ByteTrackObjectTracker(String host, int port) {
//    channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
//    stub = TrackerServiceGrpc.newBlockingStub(channel);
//  }
//
//  public boolean init(float trackThresh, float highThresh, float matchThresh, int trackBuffer) {
//    TrackerParams req = TrackerParams.newBuilder().setTrackThresh(trackThresh)
//        .setHighThresh(highThresh).setMatchThresh(matchThresh).setTrackBuffer(trackBuffer).build();
//
//    InitResponse resp = stub.initTracker(req);
//    return resp.getOk();
//  }
//
//
//  @Override
//  public List<TrackedObject> update(Mat frame, List<DetectionResult> detResults) {
//    List<Detection> dets = new ArrayList<>();
//    int imgWidth = frame.cols();
//    int imgHeight = frame.rows();
//
//    // Конвертируем DetectionResult -> gRPC Detection
//    for (DetectionResult dr : detResults) {
//      List<Rect> rects = dr.getRects();
//      List<Double> scores = dr.getScores();
//
//      for (int i = 0; i < rects.size(); i++) {
//        Rect r = rects.get(i);
//        double score = i < scores.size() ? scores.get(i) : 1.0;
//        dets.add(Detection.newBuilder()
//            .setX1(r.x())
//            .setY1(r.y())
//            .setX2(r.x() + r.width())
//            .setY2(r.y() + r.height())
//            .setScore((float) score)
//            .build());
//      }
//    }
//
//    // Формируем FrameDetections для gRPC
//    FrameDetections request = FrameDetections.newBuilder()
//        .addAllDets(dets)
//        .setImgWidth(imgWidth)
//        .setImgHeight(imgHeight)
//        .build();
//
//    // Отправляем на сервер
//    TracksResponse response = stub.updateTracks(request);
//
//    // Конвертируем ответ в список TrackedObject
//    List<TrackedObject> trackedObjects = new ArrayList<>();
//    response.getTracksList().forEach(track -> {
//      Rect bbox = new Rect(
//          (int) track.getX1(),
//          (int) track.getY1(),
//          (int) (track.getX2() - track.getX1()),
//          (int) (track.getY2() - track.getY1())
//      );
//      TrackedObject obj = new TrackedObject(bbox);
//      obj.setId(track.getId());
//      obj.setScore(1.0f); // можно передавать средний score, если нужно
//      trackedObjects.add(obj);
//    });
//
//    return trackedObjects;
//  }
//
//
//  @Override
//  public void close() {
//    if (channel != null) {
//      channel.shutdown();
//    }
//  }
//}
