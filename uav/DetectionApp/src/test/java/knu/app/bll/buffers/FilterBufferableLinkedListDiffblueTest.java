package knu.app.bll.buffers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import java.util.function.Predicate;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class FilterBufferableLinkedListDiffblueTest {

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link FilterBufferableLinkedList#FilterBufferableLinkedList(int, Predicate)}
   *   <li>{@link FilterBufferableLinkedList#setNewCapacity(int)}
   *   <li>{@link FilterBufferableLinkedList#get()}
   *   <li>{@link FilterBufferableLinkedList#getCapacity()}
   *   <li>{@link FilterBufferableLinkedList#isEmpty()}
   *   <li>{@link FilterBufferableLinkedList#isFull()}
   * </ul>
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
      "void FilterBufferableLinkedList.<init>(int, Predicate)",
      "List FilterBufferableLinkedList.get()",
      "int FilterBufferableLinkedList.getCapacity()",
      "boolean FilterBufferableLinkedList.isEmpty()",
      "boolean FilterBufferableLinkedList.isFull()",
      "void FilterBufferableLinkedList.setNewCapacity(int)"
  })
  void testGettersAndSetters() {
    // Arrange and Act
    FilterBufferableLinkedList<Object> actualFilterBufferableLinkedList =
        new FilterBufferableLinkedList<>(3, mock(Predicate.class));
    actualFilterBufferableLinkedList.setNewCapacity(1);
    List<Object> actualGetResult = actualFilterBufferableLinkedList.get();
    int actualCapacity = actualFilterBufferableLinkedList.getCapacity();
    boolean actualIsEmptyResult = actualFilterBufferableLinkedList.isEmpty();

    // Assert
    assertEquals(1, actualCapacity);
    assertFalse(actualIsEmptyResult);
    assertFalse(actualFilterBufferableLinkedList.isFull());
    assertTrue(actualGetResult.isEmpty());
  }

  /**
   * Test {@link FilterBufferableLinkedList#put(Object)}.
   *
   * <p>Method under test: {@link FilterBufferableLinkedList#put(Object)}
   */
  @Test
  @DisplayName("Test put(Object)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FilterBufferableLinkedList.put(Object)"})
  void testPut() {
    // Arrange
    FilterBufferableLinkedList<Object> filterBufferableLinkedList =
        new FilterBufferableLinkedList<>(3, mock(Predicate.class));

    // Act
    filterBufferableLinkedList.put("Element");

    // Assert
    List<Object> getResult = filterBufferableLinkedList.get();
    assertEquals(1, getResult.size());
    assertEquals("Element", getResult.get(0));
    assertEquals(1, filterBufferableLinkedList.getSize());
  }

  /**
   * Test {@link FilterBufferableLinkedList#put(Object)}.
   *
   * <p>Method under test: {@link FilterBufferableLinkedList#put(Object)}
   */
  @Test
  @DisplayName("Test put(Object)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FilterBufferableLinkedList.put(Object)"})
  void testPut2() {
    // Arrange
    FilterBufferableLinkedList<Object> filterBufferableLinkedList =
        new FilterBufferableLinkedList<>(1, mock(Predicate.class));

    // Act
    filterBufferableLinkedList.put("Element");

    // Assert that nothing has changed
    assertEquals(0, filterBufferableLinkedList.getSize());
    assertTrue(filterBufferableLinkedList.get().isEmpty());
  }

  /**
   * Test {@link FilterBufferableLinkedList#getSize()}.
   *
   * <p>Method under test: {@link FilterBufferableLinkedList#getSize()}
   */
  @Test
  @DisplayName("Test getSize()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"int FilterBufferableLinkedList.getSize()"})
  void testGetSize() {
    // Arrange
    FilterBufferableLinkedList<Object> filterBufferableLinkedList =
        new FilterBufferableLinkedList<>(3, mock(Predicate.class));

    // Act and Assert
    assertEquals(0, filterBufferableLinkedList.getSize());
  }

  /**
   * Test {@link FilterBufferableLinkedList#clear()}.
   *
   * <p>Method under test: {@link FilterBufferableLinkedList#clear()}
   */
  @Test
  @DisplayName("Test clear()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FilterBufferableLinkedList.clear()"})
  void testClear() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    FilterBufferableLinkedList<Object> filterBufferableLinkedList =
        new FilterBufferableLinkedList<>(3, mock(Predicate.class));

    // Act
    filterBufferableLinkedList.clear();

    // Assert
  }

  /**
   * Test {@link FilterBufferableLinkedList#clear()}.
   *
   * <ul>
   *   <li>Given {@link FilterBufferableLinkedList#FilterBufferableLinkedList(int, Predicate)} with
   *       capacity is three and condition is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link FilterBufferableLinkedList#clear()}
   */
  @Test
  @DisplayName(
      "Test clear(); given FilterBufferableLinkedList(int, Predicate) with capacity is three and condition is 'null'")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FilterBufferableLinkedList.clear()"})
  void testClear_givenFilterBufferableLinkedListWithCapacityIsThreeAndConditionIsNull() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.lang.NullPointerException
    //       at java.base/java.util.Objects.requireNonNull(Objects.java:233)
    //       at java.base/java.util.Collection.removeIf(Collection.java:579)
    //       at
    // knu.app.bll.buffers.FilterBufferableLinkedList.clear(FilterBufferableLinkedList.java:55)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange
    FilterBufferableLinkedList<Object> filterBufferableLinkedList =
        new FilterBufferableLinkedList<>(3, null);

    // Act
    filterBufferableLinkedList.clear();

    // Assert
  }

  /**
   * Test {@link FilterBufferableLinkedList#clearAll()}.
   *
   * <p>Method under test: {@link FilterBufferableLinkedList#clearAll()}
   */
  @Test
  @DisplayName("Test clearAll()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FilterBufferableLinkedList.clearAll()"})
  void testClearAll() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange
    FilterBufferableLinkedList<Object> filterBufferableLinkedList =
        new FilterBufferableLinkedList<>(3, mock(Predicate.class));

    // Act
    filterBufferableLinkedList.clearAll();

    // Assert
  }
}
