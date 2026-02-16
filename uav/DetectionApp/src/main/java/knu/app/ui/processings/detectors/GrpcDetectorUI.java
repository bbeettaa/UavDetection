//package knu.app.ui.processings.detectors;
//
//import imgui.ImGui;
//import io.grpc.stub.ClientCallStreamObserver;
//import io.grpc.stub.ClientResponseObserver;
//import knu.app.bll.processors.detection.ObjectDetector;
//import knu.app.bll.registry.DetectionRegistry;
//import knu.app.bll.utils.processors.DetectionResult;
//import knu.app.grpc.detector.*;
//import knu.app.grpc.detector.DetectorProtos.*;
//import io.grpc.ManagedChannel;
//import io.grpc.ManagedChannelBuilder;
//import org.bytedeco.javacpp.BytePointer;
//import org.bytedeco.javacpp.IntPointer;
//import org.bytedeco.opencv.global.opencv_imgcodecs;
//import org.bytedeco.opencv.opencv_core.Mat;
//import org.bytedeco.opencv.opencv_core.Rect;
//import org.bytedeco.opencv.opencv_core.Size;
//import org.opencv.core.MatOfByte;
//
//import java.nio.ByteBuffer;
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.concurrent.CountDownLatch;
//
//import static org.bytedeco.opencv.global.opencv_imgcodecs.imencode;
//import static org.bytedeco.opencv.global.opencv_imgproc.resize;
//
//public class GrpcDetectorUI implements DetectorUI {
//
//    private final String name;
//    private final String detectorId;
//    private final ManagedChannel channel;
//    private final DetectionServiceGrpc.DetectionServiceStub asyncStub;
//    private int jpegQuality = 85;
//    private ModelConfig modelConfig;
//
//    public GrpcDetectorUI(String host, int port, String detectorId, String name) {
//        this.name = name;
//        this.detectorId = detectorId;
//        this.channel = ManagedChannelBuilder.forAddress(host, port)
//                .usePlaintext()
//                .build();
//        this.asyncStub = DetectionServiceGrpc.newStub(channel);
//    }
//
//    @Override
//    public String getName() {
//        return name;
//    }
//
//    @Override
//    public void renderSettings() {
//        for (Param p : modelConfig.getParams()) {
//            switch (p.getType()) {
//                case FLOAT:
//                    if (ImGui.sliderFloat(p.getName(), p.getFloatValue(), p.getMin(), p.getMax())) {
//                        updateParamOnServer(p);
//                    }
//                    break;
//                case INT:
//                    if (ImGui.sliderInt(p.getName(), p.getIntValue(), (int) p.getMin(), (int) p.getMax())) {
//                        updateParamOnServer(p);
//                    }
//                    break;
//                case BOOL:
//                    if (ImGui.checkbox(p.getName(), p.getBoolValue())) {
//                        updateParamOnServer(p);
//                    }
//                    break;
//                case ENUM:
//                    if (ImGui.combo(p.getName(), p.getSelectedIndex(), p.getOptions())) {
//                        updateParamOnServer(p);
//                    }
//                    break;
//            }
//        }
//
//    }
//
//    @Override
//    public DetectionResult detect(Mat mat) {
//        byte[] jpeg = matToJpegBytes(mat);
//        ImageRequest request = ImageRequest.newBuilder()
//                .setImage(com.google.protobuf.ByteString.copyFrom(jpeg))
//                .build();
//
//        CountDownLatch latch = new CountDownLatch(1);
//        List<Rect> rects = new LinkedList<>();
//        List<Double> scores = new LinkedList<>();
//        List<String> names = new LinkedList<>();
//
//        asyncStub.streamDetect(new ClientResponseObserver<ImageRequest, ImageResponse>() {
//            private ClientCallStreamObserver<ImageRequest> requestStream;
//
//            @Override
//            public void beforeStart(ClientCallStreamObserver<ImageRequest> requestStream) {
//                this.requestStream = requestStream;
//                requestStream.onNext(request);
//                requestStream.onCompleted();
//            }
//
//            @Override
//            public void onNext(ImageResponse response) {
//                response.getDetectionsList().forEach(d -> {
//                    rects.add(new Rect((int) d.getX(), (int) d.getY(),
//                            (int) d.getWidth(), (int) d.getHeight()));
//                    scores.add((double) d.getConfidence());
//                    names.add(d.getClassName());
//                });
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                t.printStackTrace();
//                latch.countDown();
//            }
//
//            @Override
//            public void onCompleted() {
//                latch.countDown();
//            }
//        });
//
//        try {
//            latch.await();
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//
//        return new DetectionResult(rects, scores, names);
//    }
//
//
//    @Override
//    public ObjectDetector getDetector() {
//        // Можно возвращать null или обёртку для интеграции с твоим пайплайном
//        return null;
//    }
//
//    public void shutdown() {
//        channel.shutdown();
//    }
//
//
//    private byte[] matToJpegBytes(Mat mat) {
////    Mat matResized = new Mat();
//
////    resize(mat, matResized, new Size(640, 800));
//
//        IntPointer jpegParams = new IntPointer(opencv_imgcodecs.IMWRITE_JPEG_QUALITY, jpegQuality);
//
//        BytePointer buf = new BytePointer();
//        boolean ok = opencv_imgcodecs.imencode(".jpg", mat, buf, jpegParams);
//        if (!ok) {
////      if (matResized != mat) {
////        matResized.release();
////      }
//            buf.deallocate();
//            throw new RuntimeException("Failed to encode Mat to JPEG");
//        }
//
//        byte[] bytes = new byte[(int) buf.limit()];
//        buf.get(bytes);
//        buf.deallocate();
////    if (matResized != mat) {
////      matResized.release();
////    }
//
//        return bytes;
//    }
//
//    private static class ModelConfig {
//        private final List<ParamWrapper> params = new ArrayList<>();
//
//        static ModelConfig fromProto(DetectorConfig proto) {
//            ModelConfig mc = new ModelConfig();
//            for (Param p : proto.getParamsList()) {
//                mc.params.add(ParamWrapper.fromProto(p));
//            }
//            return mc;
//        }
//
//        List<ParamWrapper> getParams() {
//            return params;
//        }
//    }
//
//    private static class ParamWrapper {
//        final String name;
//        final ParamType type;
//        // local storage for ImGui widgets
//        final float[] floatValue = new float[1];
//        final float min;
//        final float max;
//        final int[] intValue = new int[1];
//        final ImBoolean boolValue = new ImBoolean(false);
//        final String[] stringValue = new String[]{""};
//        final String[] options; // for ENUM
//        final int[] selectedIndex = new int[1];
//
//        ParamWrapper(String name, ParamType type,
//                     float floatVal, float min, float max,
//                     int intVal, boolean boolVal, String strVal,
//                     String[] options, int selectedIndex) {
//            this.name = name;
//            this.type = type;
//            this.floatValue[0] = floatVal;
//            this.min = min;
//            this.max = max;
//            this.intValue[0] = intVal;
//            this.boolValue.set(boolVal);
//            if (strVal == null) strVal = "";
//            this.stringValue[0] = strVal;
//            this.options = options;
//            this.selectedIndex[0] = selectedIndex;
//        }
//
//        static ParamWrapper fromProto(Param p) {
//            String[] options = p.getOptionsList().isEmpty() ?
//                    null :
//                    p.getOptionsList().toArray(new String[0]);
//
//            // default values if not set
//            float fVal = p.getFloatValue();
//            int iVal = p.getIntValue();
//            boolean bVal = p.getBoolValue();
//            String sVal = p.getStringValue();
//            int sel = p.getSelectedIndex();
//
//            return new ParamWrapper(
//                    p.getName(),
//                    p.getType(),
//                    fVal,
//                    p.getMin(),
//                    p.getMax(),
//                    iVal,
//                    bVal,
//                    sVal,
//                    options,
//                    sel
//            );
//        }
//
//        Param toProto() {
//            Param.Builder b = Param.newBuilder()
//                    .setName(name)
//                    .setType(type)
//                    .setMin(min)
//                    .setMax(max);
//
//            switch (type) {
//                case FLOAT:
//                    b.setFloatValue(floatValue[0]);
//                    break;
//                case INT:
//                    b.setIntValue(intValue[0]);
//                    break;
//                case BOOL:
//                    b.setBoolValue(boolValue.get());
//                    break;
//                case STRING:
//                    b.setStringValue(stringValue[0] == null ? "" : stringValue[0]);
//                    break;
//                case ENUM:
//                    if (options != null) {
//                        for (String o : options) b.addOptions(o);
//                    }
//                    b.setSelectedIndex(selectedIndex[0]);
//                    break;
//                default:
//                    break;
//            }
//            return b.build();
//        }
//    }
//}
