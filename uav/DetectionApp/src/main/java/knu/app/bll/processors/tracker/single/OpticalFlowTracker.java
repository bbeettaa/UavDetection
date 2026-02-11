package knu.app.bll.processors.tracker.single;

import knu.app.bll.utils.Utils;
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
    private boolean isInitialized = false;

    @Override
    public boolean init(Mat frameGray, List<Point2f> initialPoints) {
        try {
            if (frameGray.empty() || initialPoints == null || initialPoints.isEmpty())
                return false;
            close();
            frameGray.copyTo(prevGray);
            convertPointsToMat(initialPoints).copyTo(prevPtsMat);
            isInitialized = true;
            return true;
        } catch (NullPointerException e) {
        }
        return false;
    }

    @Override
    public boolean init(Mat frameGray, Rect roi) {
        try {
            if (frameGray.empty() || roi == null || roi.area() == 0)
                return false;
            close();
            frameGray.copyTo(prevGray);
            convertPointsToMat(Utils.rectToPoints(roi)).copyTo(prevPtsMat);
            isInitialized = true;
            return true;
        } catch (NullPointerException e) {
        }
        return false;
    }

    @Override
    public List<Point2f> update(Mat frameGray, List<Point2f> newPoints) {
        LinkedList<Point2f> trackedPoints = new LinkedList<>();
        if (!isInitialized || frameGray.empty()) {
            return trackedPoints;
        }

        Mat nextPts = convertPointsToMat(newPoints);
        Mat status = new Mat();
        Mat err = new Mat();

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

        updateTrackerState(frameGray, nextPts);

        return convertResult(newPoints, nextPts, status);
    }

    @Override
    public void close() {
        prevGray.close();
        prevPtsMat.close();
        isInitialized = false;
    }

    private Mat convertPointsToMat(List<Point2f> points) {
        Mat mat = new Mat(1, points.size(), CV_32FC2);
        FloatBuffer buf = mat.createBuffer();
        for (Point2f p : points) {
            buf.put(p.x()).put(p.y());
        }
        return mat;
    }

    private void updateTrackerState(Mat newFrame, Mat newPoints) {
        newFrame.copyTo(prevGray);
        newPoints.copyTo(prevPtsMat);
    }

    private LinkedList<Point2f> convertResult(List<Point2f> original, Mat resultMat, Mat status) {
        LinkedList<Point2f> points = new LinkedList<>();
        FloatBuffer resultBuf = resultMat.createBuffer();
        ByteBuffer statusBuf = status.createBuffer();

        for (int i = 0; i < original.size(); i++) {
            if (statusBuf.get(i) == 1) {
                float x = resultBuf.get(i * 2);
                float y = resultBuf.get(i * 2 + 1);
                points.add(new Point2f(x, y));
            } else {
                points.add(original.get(i));
            }
        }
        return points;
    }
}

