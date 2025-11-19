package knu.app.bll.processors.detection;

import static org.bytedeco.opencv.global.opencv_imgproc.resize;

import com.example.yolo.BoundingBox;
import com.example.yolo.ImageRequest;
import com.example.yolo.ImageResponse;
import com.example.yolo.YoloServiceGrpc;
import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import knu.app.bll.utils.processors.DetectionResult;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.Size;
import org.opencv.core.MatOfInt;

public class YoloObjectDetector implements ObjectDetector {

  private float confTh = 0.25F;
  private float iouTh = 0.25F;
  private boolean isAugment = false;
  private boolean isHalf = false;

  private int widthResize = 640;
  private int heightResize = 640;
  private int jpegQuality = 85;
  //  private static final Logger log = LogManager.getLogger(YoloObjectDetector.class);
  private  final ManagedChannel channel;
  private  final YoloServiceGrpc.YoloServiceBlockingStub stub;

  public YoloObjectDetector(String host, int port) {
    channel = ManagedChannelBuilder.forAddress(host, port)
        .maxInboundMessageSize(10 * 1024 * 1024)
        .usePlaintext()
        .build();
    stub = YoloServiceGrpc.newBlockingStub(channel);
  }


  // Закрытие канала при завершении приложения (опционально, если нужно)
  public static void shutdown() {
//    channel.shutdown();
  }

  public void init(float confTh, float iouTh, boolean isAugment, boolean isHalf){
    this.confTh = confTh;
    this.iouTh = iouTh;
    this.isAugment = isAugment;
    this.isHalf = isHalf;
  }

  @Override
  public DetectionResult detect(Mat frameGray) {
    try {
      byte[] imgBytes = matToJpegBytes(frameGray);

      // Создаём запрос
      ImageRequest request = ImageRequest.newBuilder()
          .setImage(ByteString.copyFrom(imgBytes))
          .setConfThreshold(confTh)
          .setIouThreshold(iouTh)
          .addAllClasses(Collections.emptyList())
          .setMaxDet(100)
          .setAugment(isAugment)
          .setHalf(isHalf)
          .build();

      // Отправляем запрос и получаем ответ
      ImageResponse response = stub.detectObjects(request);

      // Конвертируем в DetectionResult (предполагаем, что DetectionResult имеет конструктор или билдер для списка detections)
      // Адаптируйте под реальную структуру DetectionResult (e.g., List<Detection> с className, x, y, w, h, conf)
      DetectionResult detections = new DetectionResult();

      int frameWidth = frameGray.cols();
      int frameHeight = frameGray.rows();


      for (BoundingBox box : response.getBoxesList()) {
        // Преобразуем нормализованные координаты в Rect (можно оставить нормализованными или масштабировать на width/height)
        // Если нужно масштабирование, добавь widthImg и heightImg
        int centerX = (int) (box.getX() * frameWidth);
        int centerY = (int) (box.getY() * frameHeight);
        int w = (int) (box.getWidth() * frameWidth);
        int h = (int) (box.getHeight() * frameHeight);

        int x = centerX - w / 2;
        int y = centerY - h / 2;

        detections.getRects().add(new Rect(x, y, w, h));
        detections.getScores().add((double) box.getConfidence());
        detections.getNames().add(box.getClassName());
      }

      return detections;
    } catch (Exception e) {
//      log.error("Error during YOLO detection: ", e);
      return new DetectionResult();  // Возврат пустого результата при ошибке
    }
  }


  private byte[] matToJpegBytes(Mat mat) {
    Mat matResized = new Mat();

    resize(mat, matResized, new Size(widthResize, heightResize));

    IntPointer jpegParams = new IntPointer(opencv_imgcodecs.IMWRITE_JPEG_QUALITY, jpegQuality);

    BytePointer buf = new BytePointer();
    boolean ok = opencv_imgcodecs.imencode(".jpg", matResized, buf, jpegParams);
    if (!ok) {
      if (matResized != mat) {
        matResized.release();
      }
      buf.deallocate();
      throw new RuntimeException("Failed to encode Mat to JPEG");
    }

    byte[] bytes = new byte[(int) buf.limit()];
    buf.get(bytes);
    buf.deallocate();
    if (matResized != mat) {
      matResized.release();
    }

    return bytes;
  }


  public void setAugment(boolean augment) {
    isAugment = augment;
  }

  public void setConfTh(float confTh) {
    this.confTh = confTh;
  }

  public void setHalf(boolean half) {
    isHalf = half;
  }

  public void setIouTh(float iouTh) {
    this.iouTh = iouTh;
  }


  public void setHeightResize(int heightResize) {
    this.heightResize = heightResize;
  }

  public void setWidthResize(int widthResize) {
    this.widthResize = widthResize;
  }

  public void setJpegQuality(int jpegQuality) {
    this.jpegQuality = jpegQuality;
  }

  public int getJpegQuality() {
    return jpegQuality;
  }

  public int getHeightResize() {
    return heightResize;
  }

  public int getWidthResize() {
    return widthResize;
  }
}
