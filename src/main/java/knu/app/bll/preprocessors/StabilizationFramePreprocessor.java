package knu.app.bll.preprocessors;

import org.bytedeco.opencv.opencv_core.Mat;

import static org.bytedeco.opencv.global.opencv_calib3d.estimateAffine2D;
import static org.bytedeco.opencv.global.opencv_imgproc.*;
import static org.bytedeco.opencv.global.opencv_imgproc.warpAffine;
import static org.bytedeco.opencv.global.opencv_video.calcOpticalFlowPyrLK;

public class StabilizationFramePreprocessor implements FramePreprocessor {
    private Mat prevGray = null;
    private Mat prevPts = new Mat();

    @Override
    public Mat process(Mat input) {
                Mat gray = new Mat();
                cvtColor(input, gray, COLOR_BGR2GRAY);

                if (prevGray != null) {
                    goodFeaturesToTrack(prevGray, prevPts, 200, 0.01, 30);

                    Mat nextPts = new Mat();
                    calcOpticalFlowPyrLK(prevGray, gray, prevPts, nextPts, new Mat(), new Mat());

                    Mat affine = estimateAffine2D(prevPts, nextPts);

                    if (!affine.empty()) {
                        Mat output = new Mat();
                        warpAffine(input, output, affine, input.size());
                        input = output;
                    }
                }

                prevGray = gray.clone();
                return input;

    }
}
