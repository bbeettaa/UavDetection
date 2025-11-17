package knu.app.bll.utils;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Locale;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class LocalizationManagerDiffblueTest {

  /**
   * Test {@link LocalizationManager#tr(String)}.
   *
   * <p>Method under test: {@link LocalizationManager#tr(String)}
   */
  @Test
  @DisplayName("Test tr(String)")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"String LocalizationManager.tr(String)"})
  void testTr() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.util.MissingResourceException: Can't find resource for bundle
    // java.util.PropertyResourceBundle, key Key
    //       at java.base/java.util.ResourceBundle.getObject(ResourceBundle.java:567)
    //       at java.base/java.util.ResourceBundle.getString(ResourceBundle.java:523)
    //       at knu.app.bll.utils.LocalizationManager.tr(LocalizationManager.java:11)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange and Act
    LocalizationManager.tr("Key");

    // Assert
  }

  /**
   * Test {@link LocalizationManager#setLocale(Locale)}.
   *
   * <ul>
   *   <li>When Default.
   * </ul>
   *
   * <p>Method under test: {@link LocalizationManager#setLocale(Locale)}
   */
  @Test
  @DisplayName("Test setLocale(Locale); when Default")
  @Disabled("TODO: Complete this test")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void LocalizationManager.setLocale(Locale)"})
  void testSetLocale_whenDefault() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.util.MissingResourceException: Can't find bundle for base name messages, locale ru_RU
    //       at
    // java.base/java.util.ResourceBundle.throwMissingResourceException(ResourceBundle.java:2059)
    //       at java.base/java.util.ResourceBundle.getBundleImpl(ResourceBundle.java:1697)
    //       at java.base/java.util.ResourceBundle.getBundleImpl(ResourceBundle.java:1600)
    //       at java.base/java.util.ResourceBundle.getBundleImpl(ResourceBundle.java:1555)
    //       at java.base/java.util.ResourceBundle.getBundle(ResourceBundle.java:935)
    //       at knu.app.bll.utils.LocalizationManager.setLocale(LocalizationManager.java:15)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange and Act
    LocalizationManager.setLocale(Locale.getDefault());

    // Assert
  }

  /**
   * Test {@link LocalizationManager#setLocale(Locale)}.
   *
   * <ul>
   *   <li>When {@link Locale#Locale(String)} with {@code en}.
   * </ul>
   *
   * <p>Method under test: {@link LocalizationManager#setLocale(Locale)}
   */
  @Test
  @DisplayName("Test setLocale(Locale); when Locale(String) with 'en'")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void LocalizationManager.setLocale(Locale)"})
  void testSetLocale_whenLocaleWithEn() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Diffblue AI was unable to find a test

    // Arrange and Act
    LocalizationManager.setLocale(new Locale("en"));

    // Assert
  }
}
