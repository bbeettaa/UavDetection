//package knu.app.bll.service;
//
//import com.google.protobuf.Empty;
//import knu.app.bll.processors.detection.DetectorBackend;
//import knu.app.grpc.detector.DetectorConfig;
//import knu.app.grpc.detector.DetectorInfo;
//import knu.app.grpc.detector.GetDetectorsResponse;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ConcurrentMap;
//import java.util.concurrent.atomic.AtomicReference;
//
///**
// * Простой менеджер backend'ов.
// * Хранит карту id -> DetectorBackend, текущий активный id.
// */
//public class DetectorManager {
//
//    private final ConcurrentMap<String, DetectorBackend> backends = new ConcurrentHashMap<>();
//    private final AtomicReference<String> activeId = new AtomicReference<>(null);
//
//    public void register(DetectorBackend backend) {
//        backends.put(backend.getId(), backend);
//        // если активен не выбран — выбрать первый
//        activeId.compareAndSet(null, backend.getId());
//    }
//
//    public GetDetectorsResponse getDetectorsResponse() {
//        GetDetectorsResponse.Builder b = GetDetectorsResponse.newBuilder();
//        for (DetectorBackend be : backends.values()) {
//            DetectorInfo info = DetectorInfo.newBuilder()
//                    .setId(be.getId())
//                    .setName(be.getName())
//                    .setImplementation(be.getImplementation())
//                    .setConfig(be.getConfig()) // backend предоставляет свой текущий config (может быть default)
//                    .build();
//            b.addDetectors(info);
//        }
//        return b.build();
//    }
//
//    /**
//     * Возвращает выбранный конфиг после установки active.
//     */
//    public DetectorConfig chooseModel(String id) {
//        DetectorBackend be = backends.get(id);
//        if (be == null) {
//            throw new IllegalArgumentException("Detector not found: " + id);
//        }
//        activeId.set(id);
//        return be.getConfig();
//    }
//
//    public void setConfig(String id, DetectorConfig cfg) {
//        DetectorBackend be = backends.get(id);
//        if (be == null) {
//            throw new IllegalArgumentException("Detector not found: " + id);
//        }
//        be.setConfig(cfg);
//    }
//
//    public DetectorBackend getActiveBackend() {
//        String id = activeId.get();
//        return id == null ? null : backends.get(id);
//    }
//
//    public List<DetectorInfo> listDetectorInfos() {
//        return new ArrayList<>(getDetectorsResponse().getDetectorsList());
//    }
//}
