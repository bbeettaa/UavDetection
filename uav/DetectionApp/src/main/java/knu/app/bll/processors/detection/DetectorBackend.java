//package knu.app.bll.processors.detection;
//
//import knu.app.bll.processors.detection.ObjectDetector;
//import knu.app.bll.utils.processors.DetectionResult;
//import knu.app.ui.processings.detectors.DetectorUI;
//import org.bytedeco.opencv.opencv_core.Mat;
//import org.bytedeco.opencv.opencv_core.Rect;
//
//import java.util.LinkedList;
//import java.util.List;
//
//// Обёртка для gRPC детектора
//public class GrpcDetectorWrapper implements DetectorUI {
//
//    private final DetectorGrpcClient client;
//    private final String detectorId;
//
//    public GrpcDetectorWrapper(DetectorGrpcClient client, String detectorId) {
//        this.client = client;
//        this.detectorId = detectorId;
//
//        // Выбираем детектор на сервере по ID
//        client.chooseModel(detectorId);
//    }
//
//    @Override
//    public String getName() {
//        return "gRPC Detector: " + detectorId;
//    }
//
//    @Override
//    public void renderSettings() {
//        // Здесь можно показать настройки детектора, если нужно
//        System.out.println("Settings for detector " + detectorId + ":");
//        System.out.println(client.getDetectors().stream()
//                .filter(d -> d.getId().equals(detectorId))
//                .findFirst()
//                .map(d -> d.getConfig())
//                .orElse("No config available"));
//    }
//
//    @Override
//    public DetectionResult detect(Mat mat) {
//        // Конвертация Mat в JPEG
//        byte[] jpegBytes = matToJpeg(mat);
//
//        ImageRequest request = ImageRequest.newBuilder()
//                .setImage(ByteString.copyFrom(jpegBytes))
//                .build();
//
//        // Вызов gRPC стрима
//        ImageResponse response = stub.getDetectors(request); // <-- вызов стрима или RPC GetDetectors/Detect
//
//        List<Rect> rects = new LinkedList<>();
//        List<Double> scores = new LinkedList<>();
//        List<String> names = new LinkedList<>();
//
//        response.getDetectionsList().forEach(d -> {
//            Rect r = new Rect(
//                    (int) d.getX(),
//                    (int) d.getY(),
//                    (int) d.getWidth(),
//                    (int) d.getHeight()
//            );
//            rects.add(r);
//            scores.add((double) d.getConfidence());
//            names.add(d.getClassName());
//        });
//
//        return new DetectionResult(rects, scores, names);
//    }
//
//
//    @Override
//    public ObjectDetector getDetector() {
//        // Можно вернуть null или специальный ObjectDetector для совместимости
//        return null;
//    }
//}
