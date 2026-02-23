package knu.app.bll.algorithms.feature;

import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Rect;

public class ObjectState {
    public Point center;
    public Rect boundingBox;

    public double area;
    public double aspectRatio;

    public double speed;
    public double angleDirection;

    public double brightnessMean;
    public double textureStdDev;

    public boolean isAnomalous = false;
    public String anomalyDescription = "";

    public ObjectState(Point center, Rect boundingBox) {
        this.center = center;
        this.boundingBox = boundingBox;
        this.area = boundingBox.width() * boundingBox.height();
        this.aspectRatio = (double) boundingBox.width() / Math.max(1, boundingBox.height());
    }
}