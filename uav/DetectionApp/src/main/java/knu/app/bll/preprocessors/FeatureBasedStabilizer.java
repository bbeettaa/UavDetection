package knu.app.bll.preprocessors;


import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Point2fVector;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.global.opencv_video;

import static org.bytedeco.opencv.global.opencv_calib3d.estimateAffine2D;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.goodFeaturesToTrack;
import static org.bytedeco.opencv.global.opencv_video.calcOpticalFlowPyrLK;


public class FeatureBasedStabilizer implements FramePreprocessor {
        private Mat prevGray = new Mat();
        private Mat prevPts = new Mat();
        private boolean isFirstFrame = true;

        @Override
        public Mat process(Mat input) {
            // Переводим в серый
            Mat gray = new Mat();
            cvtColor(input, gray, opencv_imgproc.COLOR_BGR2GRAY);

            if (!isFirstFrame) {
                // Детектируем хорошие точки для отслеживания на предыдущем кадре
                goodFeaturesToTrack(
                        prevGray,
                        prevPts,
                        200,
                        0.01,
                        30
                );

                if (prevPts.size().area() > 0) {
                    // Рассчитываем оптический поток Лукаса-Канаде
                    Mat nextPts = new Mat(prevPts.size());
                    Mat status = new Mat();
                    Mat err = new Mat();
                    calcOpticalFlowPyrLK(
                            prevGray,
                            gray,
                            prevPts,
                            nextPts,
                            status,
                            err,
                            new Size(21, 21),
                            3,
                            null,
                            0,
                            1e-4
                    );

                    // Оцениваем аффинное преобразование
                    Mat affine = estimateAffine2D(prevPts, nextPts);

                    if (!affine.empty()) {
                        // Применяем преобразование к входному кадру
                        Mat output = new Mat();
                        opencv_imgproc.warpAffine(
                                input,
                                output,
                                affine,
                                input.size()
                        );
                        input = output;
                    }
                }
            } else {
                // Первый кадр, инициализируем
                isFirstFrame = false;
            }

            // Сохраняем текущее состояние
            gray.copyTo(prevGray);
            prevPts.release();

            return input;
        }
    }

