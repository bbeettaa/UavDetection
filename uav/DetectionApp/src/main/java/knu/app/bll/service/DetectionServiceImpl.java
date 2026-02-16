//package knu.app.bll.service;
//
//import com.google.protobuf.Empty;
//import io.grpc.stub.StreamObserver;
//import knu.app.bll.registry.DetectorRegistry;
//import knu.app.grpc.detector.*;
//import knu.app.ui.processings.detectors.DetectorUI;
//import knu.app.bll.utils.processors.DetectionResult;
//import org.bytedeco.opencv.opencv_core.Mat;
//
//public class DetectionServiceImpl extends DetectionServiceGrpc.DetectionServiceImplBase {
//
//    // ---------------- GET DETECTORS ----------------
//    @Override
//    public void getDetectors(Empty request, StreamObserver<GetDetectorsResponse> responseObserver) {
//        GetDetectorsResponse.Builder builder = GetDetectorsResponse.newBuilder();
//
//        for (DetectorUI detector : DetectorRegistry.getDetectors()) {
//            builder.addDetectors(
//                    DetectorInfo.newBuilder()
//                            .setId(detector.getId())          // уникальный ID
//                            .setName(detector.getName())
//                            .setImplementation(detector.getClass().getSimpleName())
//                            .setConfig(detector.getConfig())  // метод getConfig должен быть в DetectorUI
//                            .build()
//            );
//        }
//
//        responseObserver.onNext(builder.build());
//        responseObserver.onCompleted();
//    }
//
//    // ---------------- CHOOSE MODEL ----------------
//    @Override
//    public void chooseModel(ChooseModelRequest request,
//                            StreamObserver<ChooseModelResponse> responseObserver) {
//
//        DetectorUI detector = DetectorRegistry.getDetectors().stream()
//                .filter(d -> d.getId().equals(request.getDetectorId()))
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("Detector not found"));
//
//        DetectorRegistry.setActiveDetector(detector);
//
//        // возвращаем конфиг выбранного детектора
//        responseObserver.onNext(
//                ChooseModelResponse.newBuilder()
//                        .setConfig(detector.getConfig())
//                        .build()
//        );
//        responseObserver.onCompleted();
//    }
//
//    // ---------------- STREAM DETECT ----------------
//    @Override
//    public StreamObserver<ImageRequest> streamDetect(StreamObserver<ImageResponse> responseObserver) {
//
//        DetectorUI detector = DetectorRegistry.getActiveDetector();
//
//        return new StreamObserver<>() {
//            @Override
//            public void onNext(ImageRequest request) {
//                Mat mat = ImageUtils.fromProto(request.getImage());
//                DetectionResult result = detector.detect(mat);
//
//                ImageResponse response = ImageResponse.newBuilder()
//                        .addAllDetections(ImageUtils.toProto(result))
//                        .build();
//
//                responseObserver.onNext(response);
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                t.printStackTrace();
//            }
//
//            @Override
//            public void onCompleted() {
//                responseObserver.onCompleted();
//            }
//        };
//    }
//
//    // ---------------- SET CONFIG ----------------
//    @Override
//    public void setConfig(SetConfigRequest request, StreamObserver<Empty> responseObserver) {
//
//        DetectorUI detector = DetectorRegistry.getDetectors().stream()
//                .filter(d -> d.getId().equals(request.getDet
