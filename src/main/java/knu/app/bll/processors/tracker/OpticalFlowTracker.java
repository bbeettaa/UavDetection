package knu.app.bll.processors.tracker;

import org.bytedeco.opencv.opencv_core.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.LinkedList;
import java.util.List;

import static org.bytedeco.opencv.global.opencv_core.*;
import static org.bytedeco.opencv.global.opencv_video.calcOpticalFlowPyrLK;

public class OpticalFlowTracker implements ObjectTracker {
    private final Mat prevGray = new Mat();
    private final Mat prevPtsMat = new Mat();

    @Override
    public List<Point2f> track(Mat frameGray, List<Point2f> roiPoints) {
        if (roiPoints == null || roiPoints.isEmpty()) {
            return new LinkedList<>();
        }

        // Конвертируем LinkedList<Point2f> в Mat
        Mat nextPts = new Mat(1, roiPoints.size(), CV_32FC2);
        FloatBuffer buf = nextPts.createBuffer();
        for (Point2f p : roiPoints) {
            buf.put(p.x()).put(p.y());
        }

        Mat status = new Mat();
        Mat err = new Mat();

        if (prevGray.empty()) {
            // Первый кадр - просто сохраняем точки и изображение
            frameGray.copyTo(prevGray);
            nextPts.copyTo(prevPtsMat);
            return new LinkedList<>(roiPoints);
        }

        // Вычисляем optical flow
        calcOpticalFlowPyrLK(
                prevGray,
                frameGray,
                prevPtsMat,
                nextPts,
                status,
                err,
                new Size(11, 11),
                3,
                new TermCriteria(TermCriteria.COUNT | TermCriteria.EPS, 30, 0.01),
                0,
                0.001
        );

        // Обновляем предыдущее изображение и точки
        frameGray.copyTo(prevGray);
        nextPts.copyTo(prevPtsMat);

        // Конвертируем результат обратно в LinkedList<Point2f>
        LinkedList<Point2f> trackedPoints = new LinkedList<>();
        FloatBuffer resultBuf = nextPts.createBuffer();
        ByteBuffer statusBuf = status.createBuffer();

        for (int i = 0; i < roiPoints.size(); i++) {
            if (i<statusBuf.limit() && statusBuf.get(i) == 1) { // Точка успешно отслежена
                float x = resultBuf.get(i * 2);
                float y = resultBuf.get(i * 2 + 1);
                trackedPoints.add(new Point2f(x, y));
            } else {
                // Если точка потеряна, используем предыдущее положение
                trackedPoints.add(roiPoints.get(i));
            }
        }

        return trackedPoints;
    }
}