package knu.app.bll.buffers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class BufferElementDiffblueTest {

  /**
   * Test {@link BufferElement#BufferElement(Object)}.
   *
   * <p>Method under test: {@link BufferElement#BufferElement(Object)}
   */
  @Test
  @DisplayName("Test new BufferElement(Object)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void BufferElement.<init>(Object)"})
  void testNewBufferElement() {
    // Arrange and Act
    BufferElement<Object> actualBufferElement = new BufferElement<>("Data");

    // Assert
    assertEquals("Data", actualBufferElement.getData());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link BufferElement#getData()}
   *   <li>{@link BufferElement#getTimestamp()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"Object BufferElement.getData()", "long BufferElement.getTimestamp()"})
  void testGettersAndSetters() {
    // Arrange
    BufferElement<Object> bufferElement = new BufferElement<>("Data");

    // Act
    Object actualData = bufferElement.getData();
    bufferElement.getTimestamp();

    // Assert
    assertEquals("Data", actualData);
  }
}
