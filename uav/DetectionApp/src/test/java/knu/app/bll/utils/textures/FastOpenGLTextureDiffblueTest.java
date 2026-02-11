package knu.app.bll.utils.textures;

import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.awt.image.BufferedImage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class FastOpenGLTextureDiffblueTest {

  /**
   * Test {@link FastOpenGLTexture#upload(BufferedImage)}.
   *
   * <p>Method under test: {@link FastOpenGLTexture#upload(BufferedImage)}
   */
  @Test
  @DisplayName("Test upload(BufferedImage)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FastOpenGLTexture.upload(BufferedImage)"})
  void testUpload() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: Missing observers.
    //   Diffblue Cover was unable to create an assertion.
    //   Add getters for the following fields or make them package-private:
    //   FastOpenGLTexture.height
    //   FastOpenGLTexture.lock
    //   FastOpenGLTexture.textureId
    //   FastOpenGLTexture.width

    // Arrange and Act
    new FastOpenGLTexture().upload(null);

    // Assert
  }

  /**
   * Test {@link FastOpenGLTexture#renderImGui(float, float)}.
   *
   * <p>Method under test: {@link FastOpenGLTexture#renderImGui(float, float)}
   */
  @Test
  @DisplayName("Test renderImGui(float, float)")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FastOpenGLTexture.renderImGui(float, float)"})
  void testRenderImGui() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: Missing observers.
    //   Diffblue Cover was unable to create an assertion.
    //   Add getters for the following fields or make them package-private:
    //   FastOpenGLTexture.height
    //   FastOpenGLTexture.lock
    //   FastOpenGLTexture.textureId
    //   FastOpenGLTexture.width

    // Arrange and Act
    new FastOpenGLTexture().renderImGui(10.0f, 10.0f);

    // Assert
  }

  /**
   * Test {@link FastOpenGLTexture#dispose()}.
   *
   * <p>Method under test: {@link FastOpenGLTexture#dispose()}
   */
  @Test
  @DisplayName("Test dispose()")
  @Tag("ContributionFromDiffblue")
  @ManagedByDiffblue
  @MethodsUnderTest({"void FastOpenGLTexture.dispose()"})
  void testDispose() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: Missing observers.
    //   Diffblue Cover was unable to create an assertion.
    //   Add getters for the following fields or make them package-private:
    //   FastOpenGLTexture.height
    //   FastOpenGLTexture.lock
    //   FastOpenGLTexture.textureId
    //   FastOpenGLTexture.width

    // Arrange and Act
    new FastOpenGLTexture().dispose();

    // Assert
  }
}
