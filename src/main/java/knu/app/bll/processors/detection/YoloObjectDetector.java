package knu.app.bll.processors.detection;

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
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;

public class YoloObjectDetector implements ObjectDetector {

  //  private static final Logger log = LogManager.getLogger(YoloObjectDetector.class);
  private static final ManagedChannel channel;
  private static final YoloServiceGrpc.YoloServiceBlockingStub stub;

  static {
    // Инициализация канала и stub один раз (для эффективности, не создавать каждый раз)
    channel = ManagedChannelBuilder.forAddress("localhost", 50051)
        .usePlaintext()
        .build();
    stub = YoloServiceGrpc.newBlockingStub(channel);
  }

  // Закрытие канала при завершении приложения (опционально, если нужно)
  public static void shutdown() {
//    channel.shutdown();
  }

  private float confTh = 0.25F;
  private float iouTh = 0.25F;
  private boolean isAugment = false;
  private boolean isHalf = false;

  public void init(float confTh, float iouTh, boolean isAugment, boolean isHalf){
    this.confTh = confTh;
    this.iouTh = iouTh;
    this.isAugment = isAugment;
    this.isHalf = isHalf;
  }

  @Override
  public DetectionResult detect(Mat frameGray) {
    try {
      // Конвертируем grayscale Mat в байты (JPEG формат; YOLO сервер может ожидать color, но если grayscale - сервер обработает или конвертируйте в RGB если нужно)
      // Если сервер ожидает RGB, добавьте конвертацию: opencv_imgproc.cvtColor(frameGray, frameRGB, opencv_imgproc.COLOR_GRAY2RGB);

      ByteBuffer buffer = ByteBuffer.allocate(
          (int) (frameGray.total() * frameGray.channels() * 3)); // грубая оценка
      boolean ok = opencv_imgcodecs.imencode(".jpg", frameGray, buffer);
      if (!ok) {
        throw new RuntimeException("Failed to encode Mat to JPEG");
      }

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
    Mat matColor = mat;
    if (mat.channels() == 1) {
      matColor = new Mat();
      opencv_imgproc.cvtColor(mat, matColor, opencv_imgproc.COLOR_GRAY2BGR);
    }

    BytePointer buf = new BytePointer();
    boolean ok = opencv_imgcodecs.imencode(".jpg", matColor, buf);
    if (!ok) {
      if (matColor != mat) matColor.release();
      buf.deallocate();
      throw new RuntimeException("Failed to encode Mat to JPEG");
    }

    byte[] bytes = new byte[(int) buf.limit()];
    buf.get(bytes);
    buf.deallocate();
    if (matColor != mat) matColor.release();

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
}
