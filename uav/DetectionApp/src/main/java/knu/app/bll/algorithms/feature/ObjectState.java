package knu.app.bll.algorithms.feature;

import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Rect;

import java.util.HashMap;
import java.util.Map;

public class ObjectState {
    public Point center;
    public Rect boundingBox;

    // 1. Геометричні ознаки та розмір
    public double area;
    public double aspectRatio;
    public double circularity; // міра "круглості" форми

    // 2. Кінематичні ознаки
    public double speed;
    public double angleDirection;
    public double acceleration; // похідна

    public double brightnessMean;
    public double textureStdDev;

    public boolean isAnomalous = false;
    public String anomalyDescription = "";

    public Map<String, Boolean> classificationResults = new HashMap<>();

    public ObjectState(Point center, Rect boundingBox) {
        this.center = center;
        this.boundingBox = boundingBox;
        this.area = boundingBox.width() * boundingBox.height();
        this.aspectRatio = (double) boundingBox.width() / Math.max(1, boundingBox.height());
    }
}