package knu.app.bll.buffers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class OverwritingQueueFrameBufferDiffblueTest {

  /**
   * Test {@link OverwritingQueueFrameBuffer#OverwritingQueueFrameBuffer(int)}.
   *
   * <ul>
   *   <li>When three.
   *   <li>Then return {@link OverwritingQueueFrameBuffer#get()} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link OverwritingQueueFrameBuffer#OverwritingQueueFrameBuffer(int)}
   */
  @Test
  @DisplayName("Test new OverwritingQueueFrameBuffer(int); when three; then return get() is 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void OverwritingQueueFrameBuffer.<init>(int)"})
  void testNewOverwritingQueueFrameBuffer_whenThree_thenReturnGetIsNull() {
    // Arrange and Act
    OverwritingQueueFrameBuffer<Object> actualOverwritingQueueFrameBuffer =
        new OverwritingQueueFrameBuffer<>(3);

    // Assert
    assertNull(actualOverwritingQueueFrameBuffer.get());
    assertEquals(0, actualOverwritingQueueFrameBuffer.getSize());
    assertFalse(actualOverwritingQueueFrameBuffer.isFull());
    assertTrue(actualOverwritingQueueFrameBuffer.isEmpty());
  }

  /**
   * Test {@link OverwritingQueueFrameBuffer#OverwritingQueueFrameBuffer(int)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link OverwritingQueueFrameBuffer#OverwritingQueueFrameBuffer(int)}
   */
  @Test
  @DisplayName(
      "Test new OverwritingQueueFrameBuffer(int); when zero; then throw IllegalArgumentException")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void OverwritingQueueFrameBuffer.<init>(int)"})
  void testNewOverwritingQueueFrameBuffer_whenZero_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new OverwritingQueueFrameBuffer<>(0));
  }

  /**
   * Test {@link OverwritingQueueFrameBuffer#get()}.
   *
   * <ul>
   *   <li>Given {@link OverwritingQueueFrameBuffer#OverwritingQueueFrameBuffer(int)} with capacity
   *       is three.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link OverwritingQueueFrameBuffer#get()}
   */
  @Test
  @DisplayName(
      "Test get(); given OverwritingQueueFrameBuffer(int) with capacity is three; then return 'null'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BufferElement OverwritingQueueFrameBuffer.get()"})
  void testGet_givenOverwritingQueueFrameBufferWithCapacityIsThree_thenReturnNull() {
    // Arrange
    OverwritingQueueFrameBuffer<Object> overwritingQueueFrameBuffer =
        new OverwritingQueueFrameBuffer<>(3);

    // Act and Assert
    assertNull(overwritingQueueFrameBuffer.get());
  }

  /**
   * Test {@link OverwritingQueueFrameBuffer#get()}.
   *
   * <ul>
   *   <li>Then return {@code Data}.
   * </ul>
   *
   * <p>Method under test: {@link OverwritingQueueFrameBuffer#get()}
   */
  @Test
  @DisplayName("Test get(); then return 'Data'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"BufferElement OverwritingQueueFrameBuffer.get()"})
  void testGet_thenReturnData() {
    // Arrange
    OverwritingQueueFrameBuffer<Object> overwritingQueueFrameBuffer =
        new OverwritingQueueFrameBuffer<>(3);
    BufferElement<Object> element = new BufferElement<>("Data");
    overwritingQueueFrameBuffer.put(element);

    // Act
    BufferElement<Object> actualGetResult = overwritingQueueFrameBuffer.get();

    // Assert
    assertEquals("Data", actualGetResult.getData());
    assertSame(element, actualGetResult);
  }

  /**
   * Test {@link OverwritingQueueFrameBuffer#put(BufferElement)}.
   *
   * <ul>
   *   <li>Given {@link OverwritingQueueFrameBuffer#OverwritingQueueFrameBuffer(int)} with capacity
   *       is one {@link BufferElement#BufferElement(Object)} with {@code Data}.
   * </ul>
   *
   * <p>Method under test: {@link OverwritingQueueFrameBuffer#put(BufferElement)}
   */
  @Test
  @DisplayName(
      "Test put(BufferElement); given OverwritingQueueFrameBuffer(int) with capacity is one BufferElement(Object) with 'Data'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void OverwritingQueueFrameBuffer.put(BufferElement)"})
  void testPut_givenOverwritingQueueFrameBufferWithCapacityIsOneBufferElementWithData() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    OverwritingQueueFrameBuffer<Object> overwritingQueueFrameBuffer =
        new OverwritingQueueFrameBuffer<>(1);
    overwritingQueueFrameBuffer.put(new BufferElement<>("Data"));

    // Act
    overwritingQueueFrameBuffer.put(new BufferElement<>("Data"));

    // Assert
  }

  /**
   * Test {@link OverwritingQueueFrameBuffer#isEmpty()}.
   *
   * <ul>
   *   <li>Given {@link OverwritingQueueFrameBuffer#OverwritingQueueFrameBuffer(int)} with capacity
   *       is three.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link OverwritingQueueFrameBuffer#isEmpty()}
   */
  @Test
  @DisplayName(
      "Test isEmpty(); given OverwritingQueueFrameBuffer(int) with capacity is three; then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OverwritingQueueFrameBuffer.isEmpty()"})
  void testIsEmpty_givenOverwritingQueueFrameBufferWithCapacityIsThree_thenReturnTrue() {
    // Arrange
    OverwritingQueueFrameBuffer<Object> overwritingQueueFrameBuffer =
        new OverwritingQueueFrameBuffer<>(3);

    // Act and Assert
    assertTrue(overwritingQueueFrameBuffer.isEmpty());
  }

  /**
   * Test {@link OverwritingQueueFrameBuffer#isEmpty()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link OverwritingQueueFrameBuffer#isEmpty()}
   */
  @Test
  @DisplayName("Test isEmpty(); then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OverwritingQueueFrameBuffer.isEmpty()"})
  void testIsEmpty_thenReturnFalse() {
    // Arrange
    OverwritingQueueFrameBuffer<Object> overwritingQueueFrameBuffer =
        new OverwritingQueueFrameBuffer<>(3);
    overwritingQueueFrameBuffer.put(new BufferElement<>("Data"));

    // Act and Assert
    assertFalse(overwritingQueueFrameBuffer.isEmpty());
  }

  /**
   * Test {@link OverwritingQueueFrameBuffer#isFull()}.
   *
   * <ul>
   *   <li>Given {@link OverwritingQueueFrameBuffer#OverwritingQueueFrameBuffer(int)} with capacity
   *       is three.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link OverwritingQueueFrameBuffer#isFull()}
   */
  @Test
  @DisplayName(
      "Test isFull(); given OverwritingQueueFrameBuffer(int) with capacity is three; then return 'false'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OverwritingQueueFrameBuffer.isFull()"})
  void testIsFull_givenOverwritingQueueFrameBufferWithCapacityIsThree_thenReturnFalse() {
    // Arrange
    OverwritingQueueFrameBuffer<Object> overwritingQueueFrameBuffer =
        new OverwritingQueueFrameBuffer<>(3);

    // Act and Assert
    assertFalse(overwritingQueueFrameBuffer.isFull());
  }

  /**
   * Test {@link OverwritingQueueFrameBuffer#isFull()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link OverwritingQueueFrameBuffer#isFull()}
   */
  @Test
  @DisplayName("Test isFull(); then return 'true'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OverwritingQueueFrameBuffer.isFull()"})
  void testIsFull_thenReturnTrue() {
    // Arrange
    OverwritingQueueFrameBuffer<Object> overwritingQueueFrameBuffer =
        new OverwritingQueueFrameBuffer<>(1);
    overwritingQueueFrameBuffer.put(new BufferElement<>("Data"));

    // Act and Assert
    assertTrue(overwritingQueueFrameBuffer.isFull());
  }

  /**
   * Test {@link OverwritingQueueFrameBuffer#getSize()}.
   *
   * <p>Method under test: {@link OverwritingQueueFrameBuffer#getSize()}
   */
  @Test
  @DisplayName("Test getSize()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"int OverwritingQueueFrameBuffer.getSize()"})
  void testGetSize() {
    // Arrange
    OverwritingQueueFrameBuffer<Object> overwritingQueueFrameBuffer =
        new OverwritingQueueFrameBuffer<>(3);

    // Act and Assert
    assertEquals(0, overwritingQueueFrameBuffer.getSize());
  }

  /**
   * Test {@link OverwritingQueueFrameBuffer#clear()}.
   *
   * <p>Method under test: {@link OverwritingQueueFrameBuffer#clear()}
   */
  @Test
  @DisplayName("Test clear()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void OverwritingQueueFrameBuffer.clear()"})
  void testClear() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    OverwritingQueueFrameBuffer<Object> overwritingQueueFrameBuffer =
        new OverwritingQueueFrameBuffer<>(3);

    // Act
    overwritingQueueFrameBuffer.clear();

    // Assert
  }
}
