package knu.app.bll.buffers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class OverwritingQueueBlockedFrameBufferDiffblueTest {

  /**
   * Test {@link OverwritingQueueBlockedFrameBuffer#OverwritingQueueBlockedFrameBuffer(int)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * OverwritingQueueBlockedFrameBuffer#OverwritingQueueBlockedFrameBuffer(int)}
   */
  @Test
  @DisplayName(
      "Test new OverwritingQueueBlockedFrameBuffer(int); then throw IllegalArgumentException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void OverwritingQueueBlockedFrameBuffer.<init>(int)"})
  void testNewOverwritingQueueBlockedFrameBuffer_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new OverwritingQueueBlockedFrameBuffer<>(0));
  }

  /**
   * Test {@link OverwritingQueueBlockedFrameBuffer#OverwritingQueueBlockedFrameBuffer(int)}.
   *
   * <ul>
   *   <li>When three.
   *   <li>Then return Size is zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * OverwritingQueueBlockedFrameBuffer#OverwritingQueueBlockedFrameBuffer(int)}
   */
  @Test
  @DisplayName(
      "Test new OverwritingQueueBlockedFrameBuffer(int); when three; then return Size is zero")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void OverwritingQueueBlockedFrameBuffer.<init>(int)"})
  void testNewOverwritingQueueBlockedFrameBuffer_whenThree_thenReturnSizeIsZero() {
    // Arrange and Act
    OverwritingQueueBlockedFrameBuffer<Object> actualOverwritingQueueBlockedFrameBuffer =
        new OverwritingQueueBlockedFrameBuffer<>(3);

    // Assert
    assertEquals(0, actualOverwritingQueueBlockedFrameBuffer.getSize());
    assertFalse(actualOverwritingQueueBlockedFrameBuffer.isFull());
    assertTrue(actualOverwritingQueueBlockedFrameBuffer.isEmpty());
  }

  /**
   * Test {@link OverwritingQueueBlockedFrameBuffer#put(BufferElement)}.
   *
   * <ul>
   *   <li>Then {@link OverwritingQueueBlockedFrameBuffer#OverwritingQueueBlockedFrameBuffer(int)}
   *       with capacity is one Size is one.
   * </ul>
   *
   * <p>Method under test: {@link OverwritingQueueBlockedFrameBuffer#put(BufferElement)}
   */
  @Test
  @DisplayName(
      "Test put(BufferElement); then OverwritingQueueBlockedFrameBuffer(int) with capacity is one Size is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void OverwritingQueueBlockedFrameBuffer.put(BufferElement)"})
  void testPut_thenOverwritingQueueBlockedFrameBufferWithCapacityIsOneSizeIsOne() {
    // Arrange
    OverwritingQueueBlockedFrameBuffer<Object> overwritingQueueBlockedFrameBuffer =
        new OverwritingQueueBlockedFrameBuffer<>(1);
    overwritingQueueBlockedFrameBuffer.put(new BufferElement<>("Data"));

    // Act
    overwritingQueueBlockedFrameBuffer.put(new BufferElement<>("Data"));

    // Assert that nothing has changed
    assertEquals(1, overwritingQueueBlockedFrameBuffer.getSize());
    assertFalse(overwritingQueueBlockedFrameBuffer.isEmpty());
  }

  /**
   * Test {@link OverwritingQueueBlockedFrameBuffer#put(BufferElement)}.
   *
   * <ul>
   *   <li>Then {@link OverwritingQueueBlockedFrameBuffer#OverwritingQueueBlockedFrameBuffer(int)}
   *       with capacity is three Size is one.
   * </ul>
   *
   * <p>Method under test: {@link OverwritingQueueBlockedFrameBuffer#put(BufferElement)}
   */
  @Test
  @DisplayName(
      "Test put(BufferElement); then OverwritingQueueBlockedFrameBuffer(int) with capacity is three Size is one")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void OverwritingQueueBlockedFrameBuffer.put(BufferElement)"})
  void testPut_thenOverwritingQueueBlockedFrameBufferWithCapacityIsThreeSizeIsOne() {
    // Arrange
    OverwritingQueueBlockedFrameBuffer<Object> overwritingQueueBlockedFrameBuffer =
        new OverwritingQueueBlockedFrameBuffer<>(3);

    // Act
    overwritingQueueBlockedFrameBuffer.put(new BufferElement<>("Data"));

    // Assert
    assertEquals(1, overwritingQueueBlockedFrameBuffer.getSize());
    assertFalse(overwritingQueueBlockedFrameBuffer.isEmpty());
  }

  /**
   * Test {@link OverwritingQueueBlockedFrameBuffer#get()}.
   *
   * <p>Method under test: {@link OverwritingQueueBlockedFrameBuffer#get()}
   */
  @Test
  @DisplayName("Test get()")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BufferElement OverwritingQueueBlockedFrameBuffer.get()"})
  void testGet() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    // TODO: Populate arranged inputs
    OverwritingQueueBlockedFrameBuffer<Object> overwritingQueueBlockedFrameBuffer = null;

    // Act
    BufferElement<Object> actualGetResult = overwritingQueueBlockedFrameBuffer.get();

    // Assert
    // TODO: Add assertions on result
  }

  /**
   * Test {@link OverwritingQueueBlockedFrameBuffer#isEmpty()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link OverwritingQueueBlockedFrameBuffer#isEmpty()}
   */
  @Test
  @DisplayName("Test isEmpty(); then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OverwritingQueueBlockedFrameBuffer.isEmpty()"})
  void testIsEmpty_thenReturnFalse() {
    // Arrange
    OverwritingQueueBlockedFrameBuffer<Object> overwritingQueueBlockedFrameBuffer =
        new OverwritingQueueBlockedFrameBuffer<>(3);
    overwritingQueueBlockedFrameBuffer.put(new BufferElement<>("Data"));

    // Act and Assert
    assertFalse(overwritingQueueBlockedFrameBuffer.isEmpty());
  }

  /**
   * Test {@link OverwritingQueueBlockedFrameBuffer#isEmpty()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link OverwritingQueueBlockedFrameBuffer#isEmpty()}
   */
  @Test
  @DisplayName("Test isEmpty(); then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OverwritingQueueBlockedFrameBuffer.isEmpty()"})
  void testIsEmpty_thenReturnTrue() {
    // Arrange
    OverwritingQueueBlockedFrameBuffer<Object> overwritingQueueBlockedFrameBuffer =
        new OverwritingQueueBlockedFrameBuffer<>(3);

    // Act and Assert
    assertTrue(overwritingQueueBlockedFrameBuffer.isEmpty());
  }

  /**
   * Test {@link OverwritingQueueBlockedFrameBuffer#isFull()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link OverwritingQueueBlockedFrameBuffer#isFull()}
   */
  @Test
  @DisplayName("Test isFull(); then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OverwritingQueueBlockedFrameBuffer.isFull()"})
  void testIsFull_thenReturnFalse() {
    // Arrange
    OverwritingQueueBlockedFrameBuffer<Object> overwritingQueueBlockedFrameBuffer =
        new OverwritingQueueBlockedFrameBuffer<>(3);

    // Act and Assert
    assertFalse(overwritingQueueBlockedFrameBuffer.isFull());
  }

  /**
   * Test {@link OverwritingQueueBlockedFrameBuffer#isFull()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link OverwritingQueueBlockedFrameBuffer#isFull()}
   */
  @Test
  @DisplayName("Test isFull(); then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OverwritingQueueBlockedFrameBuffer.isFull()"})
  void testIsFull_thenReturnTrue() {
    // Arrange
    OverwritingQueueBlockedFrameBuffer<Object> overwritingQueueBlockedFrameBuffer =
        new OverwritingQueueBlockedFrameBuffer<>(1);
    overwritingQueueBlockedFrameBuffer.put(new BufferElement<>("Data"));

    // Act and Assert
    assertTrue(overwritingQueueBlockedFrameBuffer.isFull());
  }

  /**
   * Test {@link OverwritingQueueBlockedFrameBuffer#getSize()}.
   *
   * <p>Method under test: {@link OverwritingQueueBlockedFrameBuffer#getSize()}
   */
  @Test
  @DisplayName("Test getSize()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"int OverwritingQueueBlockedFrameBuffer.getSize()"})
  void testGetSize() {
    // Arrange
    OverwritingQueueBlockedFrameBuffer<Object> overwritingQueueBlockedFrameBuffer =
        new OverwritingQueueBlockedFrameBuffer<>(3);

    // Act and Assert
    assertEquals(0, overwritingQueueBlockedFrameBuffer.getSize());
  }

  /**
   * Test {@link OverwritingQueueBlockedFrameBuffer#clear()}.
   *
   * <p>Method under test: {@link OverwritingQueueBlockedFrameBuffer#clear()}
   */
  @Test
  @DisplayName("Test clear()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void OverwritingQueueBlockedFrameBuffer.clear()"})
  void testClear() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    OverwritingQueueBlockedFrameBuffer<Object> overwritingQueueBlockedFrameBuffer =
        new OverwritingQueueBlockedFrameBuffer<>(3);

    // Act
    overwritingQueueBlockedFrameBuffer.clear();

    // Assert
  }
}
