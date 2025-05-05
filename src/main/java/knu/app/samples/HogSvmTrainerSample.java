package knu.app.samples;

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

public class HogSvmTrainerSample {

    public static void main(String[] args) {
        cvSetNumThreads(Runtime.getRuntime().availableProcessors());
        String positivePath = "/mnt/lindisk/datasets/drone.vis/hogSamples/";
        String negativePath = "/mnt/lindisk/datasets/drone.vis/hogSamples/negative/";
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
        String modelFile = "src/main/resources/HOGDescriptorEsp1";

//        HOGDescriptor hog = trainNewModel(modelFile);
        HOGDescriptor hog = trainDescriptor(modelFile, positivePath, negativePath);
        detect(hog, testPath);
    }

    private static HOGDescriptor loadDescriptor(String modelFile) {
        HogSvmUtils trainer = new HogSvmUtils();
        return trainer.loadDescriptorFromFile(modelFile);
    }

    private static HOGDescriptor trainDescriptor(String modelFile, String posPath, String negPath) {
        HogSvmUtils trainer = new HogSvmUtils();
        trainer.train(posPath, negPath);
        trainer.saveDescriptorToFile(modelFile);
        return trainer.getHog();
    }


    private static void detect(HOGDescriptor hog, String[] testPath) {
        HogSvmDetector detector = new HogSvmDetector(hog);
        DetectionRenderer renderer = new ROIRenderer();

        for (String s : testPath)
            detectAndView(s, detector, renderer);
    }

    private static void detectAndView(String imagePath, HogSvmDetector detector, DetectionRenderer renderer) {
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
            System.out.printf("Detected for %s ms", (System.nanoTime() - msStart) / 1000 / 1000);
            waitKey(0);
        } finally {
            image.release();
        }
    }
}