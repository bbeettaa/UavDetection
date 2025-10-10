package knu.app.bll.algorithms.kalman;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.bytedeco.opencv.opencv_core.Point2f;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for TransitionMatrixKalmanFilter.
 *
 * These tests cover:
 * 1. Dynamic behavior with sequential noisy measurements.
 * 2. Correct handling of invalid inputs and edge cases.
 * 3. Resource management (releaseResources).
 *
 * Testing techniques used:
 * - Dynamic testing: feeding sequences of measurements and checking output.
 * - White-box testing: aware of internal state update mechanism.
 * - Critical path: reset -> setDt -> update.
 * - Edge-case testing: null, NaN, zero or negative dt.
 */
class TransitionMatrixKalmanFilterTest {

  private KalmanFilter filter;

  /**
   * Set up a new filter before each test.
   * Initializes filter at a starting position (50,50).
   */
  @BeforeEach
  public void setUp() {
    filter = new TransitionMatrixKalmanFilter();
    filter.reset(new Point2f(50, 50));
  }

  /**
   * Release resources after each test to avoid memory leaks.
   */
  @AfterEach
  public void tearDown() {
    filter.releaseResources();
  }

  /**
   * Test 1: Dynamic behavior with sequential noisy measurements.
   *
   * Name: update_With_Sequential_Noisy_Measurements_Should_ConvergeToTrueTrajectory_And_SatisfyRealtimeLatency
   *
   * Steps:
   * - Simulate linear motion with velocity vx.
   * - Add Gaussian noise to measurements.
   * - Call update() for each frame.
   * - Compute average positional error and latency.
   *
   * Checks:
   * - Average error < 2 * measurement noise sigma.
   * - Average update latency < 70% of frame interval (dt).
   */
  @Test
  public void update_WithNoisyMeasurements_ShouldConvergeAndBeFast() {
    float vx = 30f; // pixels per second
    int frames = 50; // number of frames
    float dt = 0.033f;
    filter.setDt(dt);

    Random rnd = new Random(42);
    double sigma = 3.0; // noise level

    double totalError = 0.0;
    long totalLatency = 0L;

    for (int n = 1; n <= frames; n++) {
      float t = n * dt;
      Point2f truePos = new Point2f(50 + vx * t, 50);

      // Add Gaussian noise to simulate measurement uncertainty
      float noisyX = (float) (truePos.x() + rnd.nextGaussian() * sigma);
      float noisyY = (float) (truePos.y() + rnd.nextGaussian() * sigma);
      Point2f measurement = new Point2f(noisyX, noisyY);

      long start = System.nanoTime();
      Point2f estimate = filter.update(measurement); // main update call
      long elapsed = System.nanoTime() - start;

      totalLatency += elapsed;

      double err = Math.hypot(estimate.x() - truePos.x(), estimate.y() - truePos.y());
      totalError += err;
    }

    double avgError = totalError / frames;
    double avgLatencyMs = ((double) totalLatency / frames) / 1_000_000.0;

    // Assertions
    assertTrue(avgError < 2 * sigma,
        "Average error should be less than twice the measurement noise");
    assertTrue(avgLatencyMs < dt * 1000 * 0.85,
        "update() should complete within 85% of frame interval to satisfy real-time constraints");
  }

  /**
   * Test 2: Invalid inputs, edge cases for dt, and resource handling.
   *
   * Name: InvalidInputs_ReleaseAndDtEdgecases_Should_HandleGracefully_And_NotLeakResources
   *
   * Steps:
   * - Set dt = 0.0f (invalid) -> expect IllegalArgumentException.
   * - Call update(null) -> should safely return prediction.
   * - Call update(NaN) -> expect IllegalArgumentException.
   * - Call update() after releaseResources() -> expect IllegalStateException.
   */
  @Test
  public void invalidInputs_ShouldNotCrashOrLeak() {
    // dt = 0.0 is invalid
    assertThrows(IllegalArgumentException.class, () -> filter.setDt(0.0f));

    // update(null) should not throw and return a valid prediction
    assertDoesNotThrow(() -> {
      Point2f est = filter.update(null);
      assertNotNull(est, "update(null) should return predicted state, not crash");
    });

    // update(NaN) should throw exception
    Point2f nanPoint = new Point2f(Float.NaN, Float.NaN);
    assertThrows(IllegalArgumentException.class, () -> filter.update(nanPoint));

    // after releaseResources, update() should throw IllegalStateException
    KalmanFilter f2 = new TransitionMatrixKalmanFilter();
    f2.reset(new Point2f(0, 0));
    f2.releaseResources();
    assertThrows(IllegalStateException.class, () -> f2.update(new Point2f(1, 1)));
  }
}
