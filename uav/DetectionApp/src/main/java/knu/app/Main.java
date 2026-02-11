package knu.app;

import knu.app.bll.utils.PipelineInitializer;
import knu.app.bll.utils.VideoProcessingUI;
import knu.app.bll.utils.processors.hog.HogSvmDetectorConfig;
import org.bytedeco.opencv.opencv_core.Mat;

import static org.bytedeco.opencv.global.opencv_imgcodecs.IMREAD_GRAYSCALE;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;

public class Main {

    public static final String YOLO_HOST = "localhost";
    public static final int YOLO_PORT = 50051;

    public static final String BYTETRACK_HOST = "localhost";
    public static final int BYTETRACK_PORT = 50061;

    public static final String SORT_HOST = "localhost";
    public static final int SORT_PORT = 50062;



    public static void main(String[] args) {
//        String singleDescriptorFile = "src/main/resources/drone.jpg";
        String singleDescriptorFile = "src/main/resources/bss-holland-vector.jpg";
        Mat singleDescriptor = imread(singleDescriptorFile, IMREAD_GRAYSCALE);

        HogSvmDetectorConfig hogSvmDetectorConfig = HogSvmDetectorConfig.withDefaultConfig();
        String hogDescriptor = "src/main/resources/HOGDescriptorEsp.Big";

        PipelineInitializer initializer = new PipelineInitializer(1, singleDescriptor, hogDescriptor, hogSvmDetectorConfig);
        VideoProcessingUI ui = new VideoProcessingUI(initializer.getUiModules());
        ui.run();
    }
}

