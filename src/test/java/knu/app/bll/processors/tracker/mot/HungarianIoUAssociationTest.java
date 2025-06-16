package knu.app.bll.processors.tracker.mot;

import knu.app.bll.mot.HungarianIoUAssociation;
import knu.app.bll.processors.tracker.ObjectTracker;
import knu.app.bll.utils.processors.DetectionResult;
import knu.app.bll.utils.processors.TrackedObject;
import org.bytedeco.opencv.opencv_core.Rect;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HungarianIoUAssociationTest {
    private final HungarianIoUAssociation associator = new HungarianIoUAssociation(0.0001, 1_000);

    // Mock objects for dependencies
    private final ObjectTracker mockTracker = null;
    private final MotionModel mockMotion = null;

    @Test
    void testNoTracks() {
        DetectionResult detections = new DetectionResult(List.of(new Rect(0, 0, 10, 10)), List.of(0.9));

        Map<TrackedObject, Rect> result = associator.associate(List.of(), detections);
        assertTrue(result.isEmpty());
    }

    @Test
    void testNoDetections() {
        List<TrackedObject> tracks = List.of(new TrackedObject(1, new Rect(0, 0, 10, 10), 0.9f, mockTracker, mockMotion));

        Map<TrackedObject, Rect> result = associator.associate(tracks, new DetectionResult(List.of(), List.of()));
        assertTrue(result.isEmpty());
    }

    @Test
    void testPerfectMatch() {
        Rect rect = new Rect(0, 0, 10, 10);
        TrackedObject track = createTrack(rect);
        DetectionResult detections = createDetection(rect);

        Map<TrackedObject, Rect> result = associator.associate(List.of(track), detections);

        assertEquals(1, result.size());
        assertSame(rect, result.get(track));
    }


    @Test
    void testMultipleObjects() {
        List<TrackedObject> tracks = List.of(createTrack(new Rect(0, 0, 10, 10)), createTrack(new Rect(20, 20, 10, 10)));

        DetectionResult detections = new DetectionResult(List.of(new Rect(0, 0, 10, 10),  // Perfect match
                new Rect(25, 25, 10, 10)  // No match (IoU = 0.16)
        ), List.of(0.9, 0.9));

        Map<TrackedObject, Rect> result = associator.associate(tracks, detections);
        assertEquals(2, result.size());
        assertTrue(result.containsValue(detections.getRects().get(0)));
    }

    @Test
    void testDifferentSizes() {
        // More tracks than detections
        List<TrackedObject> tracks = List.of(createTrack(new Rect(0, 0, 10, 10)), createTrack(new Rect(10, 10, 10, 10)), createTrack(new Rect(20, 20, 10, 10)));

        DetectionResult detections = createDetection(new Rect(0, 0, 10, 10));

        Map<TrackedObject, Rect> result = associator.associate(tracks, detections);
        assertEquals(1, result.size());
    }

    @Test
    void testEqualIoUAssignments() {
        TrackedObject track1 = createTrack(new Rect(0, 0, 10, 10));
        TrackedObject track2 = createTrack(new Rect(0, 0, 10, 10));
        DetectionResult detections = new DetectionResult(List.of(new Rect(0, 0, 10, 10), new Rect(0, 0, 10, 10)), List.of(0.9, 0.9));

        Map<TrackedObject, Rect> result = associator.associate(List.of(track1, track2), detections);
        assertEquals(2, result.size());
        assertTrue(result.containsKey(track1));
        assertTrue(result.containsKey(track2));
    }


    @Test
    void testIdenticalRects() {
        Rect a = new Rect(0, 0, 10, 10);
        Rect b = new Rect(0, 0, 10, 10);

        double iou = associator.computeIoU(a, b);
        assertEquals(1.0, iou, 1e-6);
    }

    @Test
    void testNoOverlap() {
        Rect a = new Rect(0, 0, 10, 10);
        Rect b = new Rect(20, 20, 10, 10);

        double iou = associator.computeIoU(a, b);
        assertEquals(0.0, iou, 1e-6);
    }

    @Test
    void testPartialOverlap() {
        Rect a = new Rect(0, 0, 10, 10);
        Rect b = new Rect(5, 5, 10, 10); // Overlap: 5x5 = 25, union = 100+100-25 = 175

        double expectedIoU = 25.0 / 175.0;
        double iou = associator.computeIoU(a, b);
        assertEquals(expectedIoU, iou, 1e-6);
    }

    @Test
    void testEdgeTouching() {
        Rect a = new Rect(0, 0, 10, 10);
        Rect b = new Rect(10, 0, 10, 10); // Touch at edge, no overlap

        double iou = associator.computeIoU(a, b);
        assertEquals(0.0, iou, 1e-6);
    }

    @Test
    void testContainedRect() {
        Rect a = new Rect(0, 0, 10, 10);
        Rect b = new Rect(2, 2, 5, 5);

        double expectedIoU = 25.0 / 100.0;
        double iou = associator.computeIoU(a, b);
        assertEquals(expectedIoU, iou, 1e-6);
    }


    @Test
    void testSmallObjectPerfectMatch20x20() {
        Rect rect = new Rect(0, 0, 20, 20);
        TrackedObject track = createTrack(rect);
        DetectionResult detections = createDetection(rect);

        Map<TrackedObject, Rect> result = associator.associate(List.of(track), detections);
        assertEquals(1, result.size());
        assertSame(rect, result.get(track));
    }

    @Test
    void testSmallObjectShift3px_IoUAboveThreshold() {
        Rect trackRect = new Rect(0, 0, 20, 20);
        Rect detRect = new Rect(3, 3, 20, 20);
        TrackedObject track = createTrack(trackRect);
        DetectionResult detections = createDetection(detRect);

        Map<TrackedObject, Rect> result = associator.associate(List.of(track), detections);
        assertEquals(1, result.size());
        assertSame(detRect, result.get(track));
    }



    @Test
    void testMultipleSmallObjectsMixed20x20() {
        List<TrackedObject> tracks = List.of(createTrack(new Rect(0, 0, 20, 20)), createTrack(new Rect(50, 50, 20, 20)), createTrack(new Rect(100, 100, 20, 20)));

        List<Rect> dets = List.of(new Rect(0, 0, 20, 20), new Rect(52, 50, 20, 20),
                new Rect(102, 102, 20, 20)
        );
        DetectionResult detections = new DetectionResult(dets, List.of(0.8, 0.8, 0.8));

        Map<TrackedObject, Rect> result = associator.associate(tracks, detections);

        assertEquals(3, result.size());
        assertTrue(result.containsValue(dets.get(0)));
    }

    private TrackedObject createTrack(Rect rect) {
        return new TrackedObject(1, rect, 0.9f, mockTracker, mockMotion);
    }

    @Test
    void testHungarianOptimalSwap() {
        TrackedObject track1 = createTrack(new Rect(0, 0, 20, 20));
        TrackedObject track2 = createTrack(new Rect(0, 40, 20, 20));
        Rect detA = new Rect(0, 40, 20, 20);
        Rect detB = new Rect(0, 0, 20, 20);
        DetectionResult detections = new DetectionResult(List.of(detA, detB), List.of(0.9, 0.9));

        Map<TrackedObject, Rect> result = associator.associate(List.of(track1, track2), detections);

        assertEquals(2, result.size());
        assertSame(detB, result.get(track1));
        assertSame(detA, result.get(track2));
    }



    @Test
    void testZeroIoUThreshold() {
        HungarianIoUAssociation assocZero = new HungarianIoUAssociation(0.0, 100);
        TrackedObject track = createTrack(new Rect(0, 0, 20, 20));

        Rect detNominal = new Rect(20, 20, 20, 20);
        DetectionResult detections = new DetectionResult(List.of(detNominal), List.of(0.8));
        Map<TrackedObject, Rect> res = assocZero.associate(List.of(track), detections);

        assertEquals(1, res.size() );
        assertSame(detNominal, res.get(track));
    }

    @Test
    void testCostCalculationScalesCorrectly() {
        HungarianIoUAssociation assocSmallScale = new HungarianIoUAssociation(0.0, 10);
        HungarianIoUAssociation assocLargeScale = new HungarianIoUAssociation(0.0, 1000);

        Rect a = new Rect(0, 0, 20, 20);
        Rect b = new Rect(5, 5, 20, 20);

        int costSmall = calculateCost(a, b, 10);
        int costLarge = calculateCost(a, b, 1000);

        assertEquals((int) ((1.0 - assocSmallScale.computeIoU(a, b)) * 10), costSmall);
        assertEquals((int) ((1.0 - assocLargeScale.computeIoU(a, b)) * 1000), costLarge);
        assertTrue(costLarge > costSmall);
    }



    @Test
    void testSmallObjectExactMatch() {
        Rect rect = new Rect(0, 0, 20, 20);
        TrackedObject track = createTrack(rect);
        DetectionResult detections = createDetection(rect);

        Map<TrackedObject, Rect> result = associator.associate(List.of(track), detections);

        assertEquals(1, result.size());
        assertSame(rect, result.get(track));
    }

    @Test
    void testSmallObjectSlightShiftAboveThreshold() {
        TrackedObject track = createTrack(new Rect(0, 0, 20, 20));
        // Сдвиг на 4 пикселя - пересечение 16x20
        DetectionResult detections = createDetection(new Rect(4, 0, 20, 20));

        Map<TrackedObject, Rect> result = associator.associate(List.of(track), detections);
        assertFalse(result.isEmpty());
    }


    @Test
    void testSmallObjectsCloseProximity() {
        List<TrackedObject> tracks = List.of(
                createTrack(new Rect(0, 0, 20, 20)),
                createTrack(new Rect(25, 0, 20, 20))
        );

        DetectionResult detections = new DetectionResult(
                List.of(
                        new Rect(3, 2, 20, 20),  // Лучшее соответствие для первого трека
                        new Rect(26, 1, 20, 20)  // Лучшее соответствие для второго
                ),
                List.of(0.9, 0.9)
        );

        Map<TrackedObject, Rect> result = associator.associate(tracks, detections);
        assertEquals(2, result.size());

        // Проверка корректности сопоставления
        assertTrue(result.values().containsAll(detections.getRects()));
    }

    @Test
    void testSmallObjectResizing() {
        TrackedObject track = createTrack(new Rect(0, 0, 20, 20));
        // Детекция 18x18 с центром в (9,9)
        DetectionResult detections = createDetection(new Rect(1, 1, 18, 18));

        double iou = associator.computeIoU(track.getRect(), detections.getRects().get(0));
        assertTrue(iou > 0.5, "IoU should be above threshold");
    }

    @Test
    void testHighDensitySmallObjects() {
        // 5 треков в шахматном порядке
        List<TrackedObject> tracks = List.of(
                createTrack(new Rect(0, 0, 20, 20)),
                createTrack(new Rect(22, 0, 20, 20)),
                createTrack(new Rect(44, 0, 20, 20)),
                createTrack(new Rect(0, 22, 20, 20)),
                createTrack(new Rect(22, 22, 20, 20))
        );

        // 5 детекций со смещениями
        DetectionResult detections = new DetectionResult(
                List.of(
                        new Rect(2, 1, 20, 20),   // Трек 0
                        new Rect(23, 2, 20, 20),  // Трек 1
                        new Rect(46, 0, 20, 20),  // Трек 2
                        new Rect(1, 24, 20, 20),  // Трек 3
                        new Rect(20, 23, 20, 20)  // Трек 4
                ),
                List.of(0.9, 0.9, 0.9, 0.9, 0.9)
        );

        Map<TrackedObject, Rect> result = associator.associate(tracks, detections);
        assertEquals(5, result.size());
    }

    @Test
    void testOverlappingSmallObjectsDifferentSizes() {
        TrackedObject track = createTrack(new Rect(0, 0, 20, 20));
        // Детекция 25x25 с тем же центром
        DetectionResult detections = createDetection(new Rect(-2, -2, 25, 25));

        double iou = associator.computeIoU(track.getRect(), detections.getRects().get(0));
        assertEquals(0.64, iou, 0.01); // (20*20)/(25*25) = 400/625 = 0.64
        assertFalse(associator.associate(List.of(track), detections).isEmpty());
    }




    @Test
    void testModerateShiftStillAssociates20x20() {
        // Сдвиг на 3 пикселя, IoU ~0.56 — ассоциируется
        TrackedObject track = createTrack(new Rect(0, 0, 20, 20));
        Rect shifted = new Rect(3, 3, 20, 20);

        DetectionResult detections = new DetectionResult(
                List.of(shifted),
                List.of(0.9)
        );

        Map<TrackedObject, Rect> result = associator.associate(List.of(track), detections);
        assertEquals(1, result.size() );
        assertSame(shifted, result.get(track));
    }



    @Test
    void testModerateShiftStillAssociates20x20_2() {
        // Сдвиг на 3 пикселя, IoU ~0.56 — ассоциируется
        TrackedObject track = createTrack(new Rect(0, 0, 20, 20));
        Rect shifted = new Rect(3, 3, 20, 20);

        DetectionResult detections = new DetectionResult(
                List.of(shifted),
                List.of(0.9)
        );

        Map<TrackedObject, Rect> result = associator.associate(List.of(track), detections);
        assertEquals(1, result.size() );
        assertSame(shifted, result.get(track));
    }

    @Test
    void testVeryFastObjectSkipped() {
        TrackedObject track = createTrack(new Rect(0, 0, 20, 20));
        Rect fastMoved = new Rect(30, 30, 20, 20);

        DetectionResult detections = new DetectionResult(List.of(fastMoved), List.of(0.95));
        Map<TrackedObject, Rect> result = associator.associate(List.of(track), detections);

        assertTrue(result.isEmpty() );
    }

    @Test
    void testOneFastOneStableObject() {
        TrackedObject slow = createTrack(new Rect(0, 0, 20, 20));
        TrackedObject fast = createTrack(new Rect(100, 100, 20, 20));

        Rect detectionSlow = new Rect(1, 1, 20, 20);       // IoU > 0.5
        Rect detectionFast = new Rect(140, 140, 20, 20);   // IoU ≈ 0 (ускорение)

        DetectionResult detections = new DetectionResult(
                List.of(detectionSlow, detectionFast),
                List.of(0.9, 0.9)
        );

        Map<TrackedObject, Rect> result = associator.associate(List.of(slow, fast), detections);

        assertEquals(1, result.size() );
        assertTrue(result.containsKey(slow));
        assertFalse(result.containsKey(fast));
    }



    @Test
    void testFastDownwardMotionAndGrowth() {
        TrackedObject track = createTrack(new Rect(100, 100, 20, 20));
        Rect movedAndScaled = new Rect(130, 150, 30, 30);

        DetectionResult detections = new DetectionResult(List.of(movedAndScaled), List.of(0.9));
        Map<TrackedObject, Rect> result = associator.associate(List.of(track), detections);

        assertTrue(result.isEmpty() );
    }



    @Test
    void testSingleObjectWithExtremeAcceleration() {
        // Начальная позиция объекта
        Rect initialPosition = new Rect(0, 0, 20, 20);
        TrackedObject trackedObject = createTrack(initialPosition);

        // Объект "ускоряется" и перемещается очень далеко за один кадр
        Rect newPosition = new Rect(200, 200, 20, 20); // IoU = 0.0

        DetectionResult detections = new DetectionResult(
                List.of(newPosition),
                List.of(0.95)
        );

        Map<TrackedObject, Rect> result = associator.associate(List.of(trackedObject), detections);

        assertTrue(result.isEmpty());
    }

    @Test
    void testShiftBy15Pixels() {
        Rect initial = new Rect(0, 0, 20, 20);
        Rect shifted = new Rect(15, 15, 20, 20); // Overlap есть, но маленький

        TrackedObject track = createTrack(initial);
        DetectionResult detections = new DetectionResult(List.of(shifted), List.of(0.95));

        Map<TrackedObject, Rect> result = associator.associate(List.of(track), detections);

        assertFalse(result.isEmpty(), "" + result.size());
    }

    @Test
    void testShiftBy18Pixels() {
        Rect initial = new Rect(0, 0, 20, 20);
        Rect shifted = new Rect(18, 18, 20, 20); // Очень маленькое пересечение (2x2)

        TrackedObject track = createTrack(initial);
        DetectionResult detections = new DetectionResult(List.of(shifted), List.of(0.95));

        Map<TrackedObject, Rect> result = associator.associate(List.of(track), detections);

        assertFalse(result.isEmpty(), "" + result.size());
    }

    @Test
    void testShiftBy19Pixels() {
        Rect initial = new Rect(0, 0, 20, 20);
        Rect shifted = new Rect(19, 19, 20, 20); // Пересечение 1x1 пиксель

        TrackedObject track = createTrack(initial);
        DetectionResult detections = new DetectionResult(List.of(shifted), List.of(0.95));

        Map<TrackedObject, Rect> result = associator.associate(List.of(track), detections);

        assertFalse(result.isEmpty(), "" + result.size());
    }

    @Test
    void testShiftBy20Pixels() {
        Rect initial = new Rect(0, 0, 20, 20);
        Rect shifted = new Rect(20, 20, 20, 20); // Ровно рядом, пересечения нет

        TrackedObject track = createTrack(initial);
        DetectionResult detections = new DetectionResult(List.of(shifted), List.of(0.95));

        Map<TrackedObject, Rect> result = associator.associate(List.of(track), detections);

        assertTrue(result.isEmpty(), "" + result.size());
    }

    @Test
    void testShiftBy21Pixels() {
        Rect initial = new Rect(0, 0, 20, 20);
        Rect shifted = new Rect(21, 21, 20, 20); // Больше, чем габариты — 100% нет пересечения

        TrackedObject track = createTrack(initial);
        DetectionResult detections = new DetectionResult(List.of(shifted), List.of(0.95));

        Map<TrackedObject, Rect> result = associator.associate(List.of(track), detections);

        assertTrue(result.isEmpty(), "" + result.size());
    }




    private DetectionResult createDetection(Rect rect) {
        return new DetectionResult(List.of(rect), List.of(0.9));
    }

    private int calculateCost(Rect a, Rect b, int scale) {
        double iou = new HungarianIoUAssociation(0.0, 0).computeIoU(a, b);
        return (int) ((1.0 - iou) * scale);
    }


}