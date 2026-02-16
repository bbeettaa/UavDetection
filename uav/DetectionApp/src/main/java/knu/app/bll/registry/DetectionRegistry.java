//package knu.app.bll.registry;
//
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//import knu.app.ui.processings.detectors.DetectorUI;
//
//public class DetectionRegistry {
//
//    private static final DetectionRegistry INSTANCE = new DetectionRegistry();
//
//    private final Map<String, DetectorUI> detectors = Collections.synchronizedMap(new HashMap<>());
//
//    private DetectionRegistry() {}
//
//    public static DetectionRegistry getInstance() {
//        return INSTANCE;
//    }
//
//    public void registerRemoteDetector(String id, DetectorUI client) {
//        detectors.put(id, client);
//    }
//
//    public void registerDetector(String id, DetectorUI detector) {
//        detectors.put(id, detector);
//    }
//
//    public DetectorUI getDetector(String id) {
//        return detectors.get(id);
//    }
//
//    public Map<String, DetectorUI> getAllDetectors() {
//        return Collections.unmodifiableMap(detectors);
//    }
//}
