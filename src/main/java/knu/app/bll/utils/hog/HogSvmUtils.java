package knu.app.bll.utils.hog;

import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.opencv_objdetect.HOGDescriptor;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HogSvmUtils {
    private static final Logger logger = Logger.getLogger(HogSvmUtils.class.getName());

    public static final int WIN_SIZE_L = 32;
    public static final Size WIN_SIZE = new Size(WIN_SIZE_L, WIN_SIZE_L);

    public static final int BLOCK_SIZE_L = WIN_SIZE_L / 2;
    public static final Size BLOCK_SIZE = new Size(BLOCK_SIZE_L, BLOCK_SIZE_L);

    public static final int BLOCK_STRIDE_L = 8;
    public static final Size BLOCK_STRIDE = new Size(BLOCK_STRIDE_L, BLOCK_STRIDE_L);

    public static final int CELL_SIZE_L = 8;
    public static final Size CELL_SIZE = new Size(CELL_SIZE_L, CELL_SIZE_L);

    public static final int NBINS = 9;

    public static final Size HOG_PADDING = new Size(0, 0);

    public static final int TERM_CRITERIA = TermCriteria.EPS;// | TermCriteria.MAX_ITER;
    public static final int MAX_ITERATION_COUNT = 100_000;




    public static void saveDescriptorToFile(String file, HOGDescriptor hog) {
        logger.info("Saving descriptor to file");
        hog.save(file);
    }

    public static HOGDescriptor loadDescriptorFromFile(String file) {
        HOGDescriptor hog = createHog();
        logger.log(Level.INFO, "Loading descriptor from file");
        if (!hog.load(file))
            logger.warning("Cannot load descriptor from file ");
        return hog;
    }


    public static HOGDescriptor createHog() {
        return createHog(WIN_SIZE, BLOCK_SIZE, BLOCK_STRIDE, CELL_SIZE, NBINS);
    }

    public static HOGDescriptor createHog(Size winSize, Size blockSize, Size blockStride, Size cellSize, int nbis) {
        logger.log(Level.INFO, "Block Coverage " + ((float) (BLOCK_SIZE_L - BLOCK_STRIDE_L) / BLOCK_SIZE_L * 100) + "%");
        return new HOGDescriptor(winSize, blockSize, blockStride, cellSize, nbis);
    }









}
