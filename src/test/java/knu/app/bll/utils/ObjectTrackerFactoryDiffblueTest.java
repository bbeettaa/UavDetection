package knu.app.bll.utils;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.function.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ObjectTrackerFactoryDiffblueTest {

  /**
   * Test getters and setters.
   *
   * <p>Method under test: {@link ObjectTrackerFactory#getInstance()}
   */
  @Test
  @DisplayName("Test getters and setters")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"ObjectTrackerFactory ObjectTrackerFactory.getInstance()"})
  void testGettersAndSetters() {
    // Arrange and Act
    ObjectTrackerFactory actualInstance = ObjectTrackerFactory.getInstance();
    ObjectTrackerFactory actualInstance2 = actualInstance.getInstance();

    // Assert
    assertSame(actualInstance, actualInstance2);
  }

  /**
   * Test {@link ObjectTrackerFactory#registry(String, Supplier)}.
   *
   * <p>Method under test: {@link ObjectTrackerFactory#registry(String, Supplier)}
   */
  @Test
  @DisplayName("Test registry(String, Supplier)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void ObjectTrackerFactory.registry(String, Supplier)"})
  void testRegistry() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: Missing observers.
    //   Diffblue Cover was unable to create an assertion.
    //   Add getters for the following fields or make them package-private:
    //   ObjectTrackerFactory.registry

    // Arrange and Act
    ObjectTrackerFactory.getInstance().registry("Key", mock(Supplier.class));

    // Assert
  }

  /**
   * Test {@link ObjectTrackerFactory#create(String)}.
   *
   * <p>Method under test: {@link ObjectTrackerFactory#create(String)}
   */
  @Test
  @DisplayName("Test create(String)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({
      "knu.app.bll.processors.tracker.ObjectTracker ObjectTrackerFactory.create(String)"
  })
  void testCreate() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> ObjectTrackerFactory.getInstance().create("Key"));
  }
}
