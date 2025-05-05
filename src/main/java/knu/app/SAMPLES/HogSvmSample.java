package knu.app.SAMPLES;

import knu.app.bll.processors.detection.DetectionResult;
import knu.app.bll.processors.detection.HogSvmDetector;
import knu.app.bll.processors.draw.DetectionRenderer;
import knu.app.bll.processors.draw.ROIRenderer;
import knu.app.bll.utils.HogSvmUtils;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_objdetect.HOGDescriptor;

import static org.bytedeco.opencv.global.opencv_core.cvSetNumThreads;
import static org.bytedeco.opencv.global.opencv_highgui.imshow;
import static org.bytedeco.opencv.global.opencv_highgui.waitKey;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;

public class HogSvmSample {

    public static void main(String[] args) {
        HOGDescriptor hog;
//        boolean isModelTrained = true;
        boolean isModelTrained = false;

        cvSetNumThreads(Runtime.getRuntime().availableProcessors());
        String POSITIVE_PATH = "/mnt/lindisk/datasets/drone.vis/hogSamples/";
        String NEGATIVE_PATH = "/mnt/lindisk/datasets/drone.vis/hogSamples/negative/";
        String[] testPath = {
                "/mnt/lindisk/datasets/drone.vis/hogSamples/samples/DRONE_006041.png",
                "/mnt/lindisk/datasets/drone.vis/hogSamples/samples/DRONE_008021.png",
                "/mnt/lindisk/datasets/drone.vis/hogSamples/samples/Clip_50_001648_crop_9.png",
                "/mnt/lindisk/datasets/drone.vis/hogSamples/samples/Clip_50_001408_crop_3.png",
                "/mnt/lindisk/datasets/drone.vis/hogSamples/samples/Clip_50_001248_crop_9.png",
                "/mnt/lindisk/datasets/drone.vis/hogSamples/samples/drone.jpg",
                "/mnt/lindisk/datasets/drone.vis/hogSamples/negative/HELICOPTER_004485.png",
                "/mnt/lindisk/datasets/drone.vis/hogSamples/negative/0000352_02353_d_0000551.jpg",
        };

        String file = "src/main/resources/HOGDescriptorEsp1";
        if (isModelTrained) {
            HogSvmUtils trainer = new HogSvmUtils();
            hog = trainer.loadDescriptorFromFile(file);
        } else {
            HogSvmUtils trainer = new HogSvmUtils();
            trainer.train(POSITIVE_PATH, NEGATIVE_PATH);
            trainer.saveDescriptorToFile(file);
            hog = trainer.getHog();
        }

        HogSvmDetector detector = new HogSvmDetector(hog);
        DetectionRenderer renderer = new ROIRenderer();

        for (String s : testPath)
            testDetection(s, detector, renderer);

        detector.release();
    }

    private static void testDetection(String imagePath, HogSvmDetector detector, DetectionRenderer renderer) {
        System.out.println("\nProcessing image: " + imagePath);
        Mat image = imread(imagePath);
        if (image.empty()) {
            System.err.println("Failed to load image: " + imagePath);
            return;
        }

        try {
            long msStart = System.nanoTime();
            DetectionResult result = detector.detect(image);
            System.out.println("Detected objects: " + result.getRects().size());

            renderer.render(image, result.getRects(), result.getScores(), true);
            imshow("Result", image);
            System.out.printf("Detected for %s ms", (System.nanoTime() - msStart)/1000/1000 );
            waitKey(0);
        } finally {
            image.release();
        }
    }
}