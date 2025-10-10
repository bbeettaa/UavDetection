package knu.app.samples;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import knu.app.bll.processors.detection.HogSvmDetector;
import knu.app.bll.processors.detection.ObjectDetector;
import knu.app.bll.processors.draw.DetectionRenderer;
import knu.app.bll.processors.draw.ROIRenderer;
import knu.app.bll.grabbers.PlaybackControlFFmpegFrameGrabberVideoSource;
import knu.app.bll.utils.processors.DetectionResult;
import knu.app.bll.utils.processors.hog.HogSvmDetectorConfig;
import knu.app.bll.utils.processors.hog.HogSvmUtils;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class DetectionRoiResult {

    public static void main(String[] args) throws Exception {
        String videoPath = "/home/bedu/Документы/Clip_31.mov";
        String outputJsonPath = "/home/bedu/Документы/Clip_31_anotations.json";
//        String videoPath = "/home/bedu/Документы/cam1c.mp4";
//        String outputJsonPath = "/home/bedu/Документы/cam1c_anotations.json";
        String hogDescriptor = "src/main/resources/HOGDescriptorEsp2";

        HogSvmDetectorConfig hogSvmDetectorConfig = HogSvmDetectorConfig.withTestConfig1();
        ObjectDetector detector = new HogSvmDetector(HogSvmUtils.loadDescriptorFromFile(hogDescriptor), hogSvmDetectorConfig);

        DetectionRenderer renderer = new ROIRenderer();

        var grabber = new PlaybackControlFFmpegFrameGrabberVideoSource();
        grabber.setInputSource(videoPath);
        grabber.start();

        CanvasFrame canvas = new CanvasFrame("Object Detection");
        OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat();

        Map<Long, List<SimpleRect>> detectionsByFrame = new LinkedHashMap<>();

        Frame frame;
        while ((frame = grabber.grab()) != null) {
            Mat mat = converter.convert(frame);
            if (mat == null || mat.empty()) continue;

            DetectionResult result = detector.detect(mat);

            List<SimpleRect> rectsForJson = new ArrayList<>();
            Rect bestR = null;
            double bestsc = 0;
            for (int i = 0; i < result.getRects().size(); i++) {
                Rect r = result.getRects().get(i);
                if (result.getScores().get(i) > bestsc) {
                    bestsc = result.getScores().get(i);
                    bestR = r;
                }
            }
            if(bestR != null)
            rectsForJson.add(new SimpleRect(bestR.x(), bestR.y(), bestR.width(), bestR.height()));

            detectionsByFrame.put(grabber.getFrameNumber(), rectsForJson);

            renderer.render(mat, result.getRects(), result.getScores(), true);
            canvas.showImage(converter.convert(mat));
        }

        grabber.stop();
        canvas.dispose();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(outputJsonPath)) {
            gson.toJson(detectionsByFrame, writer);
            System.out.println("Результати детекції збережено у " + outputJsonPath);
        }
    }

    static class SimpleRect {
        int x, y, width, height;

        public SimpleRect(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }

}
