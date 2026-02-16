//package knu.app.bll.service;
//
//import io.grpc.ManagedChannel;
//import io.grpc.ManagedChannelBuilder;
//import io.grpc.stub.StreamObserver;
//import knu.app.grpc.detector.*;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.List;
//
//public class DetectionClientService {
//
//    private final ManagedChannel channel;
//    private final DetectionServiceGrpc.DetectionServiceBlockingStub blockingStub;
//    private final DetectionServiceGrpc.DetectionServiceStub asyncStub;
//
//    public DetectionClientService(String host, int port) {
//        channel = ManagedChannelBuilder.forAddress(host, port)
//                .usePlaintext()
//                .build();
//        blockingStub = DetectionServiceGrpc.newBlockingStub(channel);
//        asyncStub = DetectionServiceGrpc.newStub(channel);
//    }
//
//    public void shutdown() {
//        channel.shutdown();
//    }
//
//    public  List<DetectorInfo> listDetectors() {
//        return blockingStub.getDetectors(com.google.protobuf.Empty.getDefaultInstance()).getDetectorsList();
//    }
//
//    public DetectorConfig chooseDetector(String detectorId) {
//        return blockingStub.chooseModel(
//                ChooseModelRequest.newBuilder().setDetectorId(detectorId).build()
//        ).getConfig();
//    }
//
//    // Отправляем один кадр на стрим детекции
//    public void detectImage(byte[] imageData) throws InterruptedException {
//        final Object lock = new Object();
//
//        StreamObserver<ImageRequest> requestObserver = asyncStub.streamDetect(new StreamObserver<ImageResponse>() {
//            @Override
//            public void onNext(ImageResponse value) {
//                System.out.println("Detections:");
//                for (Detection d : value.getDetectionsList()) {
//                    System.out.printf("Class: %s, Conf: %.2f, x: %.1f, y: %.1f, w: %.1f, h: %.1f%n",
//                            d.getClassName(), d.getConfidence(), d.getX(), d.getY(), d.getWidth(), d.getHeight());
//                }
//                synchronized (lock) {
//                    lock.notify();
//                }
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                t.printStackTrace();
//                synchronized (lock) {
//                    lock.notify();
//                }
//            }
//
//            @Override
//            public void onCompleted() {
//                System.out.println("Stream completed");
//                synchronized (lock) {
//                    lock.notify();
//                }
//            }
//        });
//
//        requestObserver.onNext(
//                ImageRequest.newBuilder()
//                        .setImage(com.google.protobuf.ByteString.copyFrom(imageData))
//                        .build()
//        );
//
//        requestObserver.onCompleted();
//
//        synchronized (lock) {
//            lock.wait(); // ждём ответа
//        }
//    }
//
//    public void streamDetect(byte[] image, StreamObserver<ImageResponse> responseObserver) {
//        StreamObserver<ImageRequest> requestObserver = asyncStub.streamDetect(responseObserver);
//        requestObserver.onNext(ImageRequest.newBuilder().setImage(com.google.protobuf.ByteString.copyFrom(image)).build());
//        requestObserver.onCompleted();
//    }
//
//    public static void main(String[] args) throws IOException, InterruptedException {
//        DetectionClientService client = new DetectionClientService("localhost", 50051);
//
//        // 1. Получаем детекторы
//        System.out.println("Available detectors:");
//        for (DetectorInfo d : client.listDetectors()) {
//            System.out.printf("ID: %s, Name: %s%n", d.getId(), d.getName());
//        }
//
//        // 2. Выбираем первый детектор
//        String detectorId = client.listDetectors().get(0).getId();
//        client.chooseDetector(detectorId);
//
//        // 3. Отправляем тестовое изображение
//        File file = new File("/home/cachy/Изображения/img1.png");
//        byte[] imageData = new byte[(int) file.length()];
//        try (FileInputStream fis = new FileInputStream(file)) {
//            fis.read(imageData);
//        }
//
//        client.detectImage(imageData);
//
//        client.shutdown();
//    }
//}
