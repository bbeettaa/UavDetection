package knu.app.bll.preprocessors;

import org.bytedeco.javacpp.indexer.Indexer;
import org.bytedeco.javacpp.indexer.UByteIndexer;

import org.bytedeco.opencv.opencv_core.*;

import static org.bytedeco.opencv.global.opencv_core.*;


    import org.bytedeco.javacpp.indexer.IntIndexer; // НОВЫЙ ИНДЕКСАТОР
import org.bytedeco.javacpp.indexer.UByteIndexer;

// ... (Остальные импорты)

    public class KMeansPreprocessor implements FramePreprocessor {
        private int k;
        final Mat samples32f = new Mat();
        final Mat labels = new Mat();
        final Mat centers = new Mat();

        // Меняем Indexer на более конкретные типы
        IntIndexer labelsIndexer;
        UByteIndexer centersIndexer;
        UByteIndexer resultIndexer;

        Mat result = null;

        final TermCriteria criteria = new TermCriteria(TermCriteria.COUNT | TermCriteria.EPS, 100, 0.1);

        public KMeansPreprocessor(int k) {
            this.k = k;
        }

        @Override
        public Mat process(Mat input) {
            // ... (KMeans-часть остается прежней)
            input.reshape(1, input.rows() * input.cols()).convertTo(samples32f, CV_32F);

            // Убедимся, что result имеет правильный размер и тип
            if (result == null || result.size(0) != input.rows() || result.size(1) != input.cols()) {
                result = new Mat(input.size(), input.type());
            }

            kmeans(samples32f, k, labels, criteria, 1, KMEANS_PP_CENTERS, centers);

            // centers преобразуется в CV_8UC3, то есть 8-битные беззнаковые (0-255)
            centers.convertTo(centers, CV_8UC3);

            // 1. Используем IntIndexer, так как labels - CV_32S (32-битное целое)
            // reshape(1, 1) для labels, чтобы получить одномерный доступ
            labelsIndexer = labels.reshape(1, 1).createIndexer();
            // 2. Используем UByteIndexer для centers (CV_8UC3)
            centersIndexer = centers.createIndexer();
            // 3. Используем UByteIndexer для result (CV_8UC3)
            resultIndexer = result.createIndexer();

            int totalPixels = input.rows() * input.cols();

            // ОПТИМИЗИРОВАННЫЙ ЦИКЛ (Используем один цикл и прямой доступ)
            for (int i = 0; i < totalPixels; i++) {
                // Чтение метки: используем get(i) вместо getDouble(...)
                // Это намного быстрее!
                int label = labelsIndexer.get(i);

                // Чтение центроида (цвета) из centers
                // В centersIndexer (UByteIndexer) данные хранятся как 0-255.
                int b = centersIndexer.get(label, 0);
                int g = centersIndexer.get(label, 1);
                int r = centersIndexer.get(label, 2);

                // Запись цвета в result
                // Мы используем i для индексации одномерной формы labels
                // Но resultIndexer создан для двумерной (y, x, c), поэтому нужно преобразование i в (y, x)
                int y = i / input.cols();
                int x = i % input.cols();

                // ЗАМЕНА: Использовать put(y, x, c, (byte) value), но БЕЗ * 255.
                // b, g, r уже 8-битные (0-255), так как centers был CV_8UC3.
                resultIndexer.put(y, x, 0, (byte) b);
                resultIndexer.put(y, x, 1, (byte) g);
                resultIndexer.put(y, x, 2, (byte) r);
            }

            labelsIndexer.release();
            centersIndexer.release();
            resultIndexer.release();

            return result;
        }

        // ... (Getter/Setter)

    public void setK(int k) {
        this.k = k;
    }

    public int getK() {
        return k;
    }
}

