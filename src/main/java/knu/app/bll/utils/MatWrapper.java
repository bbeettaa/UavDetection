package knu.app.bll.utils;


import org.bytedeco.opencv.opencv_core.Mat;

public record MatWrapper(long frameIndex, Mat mat) {
}
