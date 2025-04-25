package knu.app.utils.textures;

import java.awt.image.BufferedImage;

public interface UiTexturable {
    void upload(BufferedImage image);

    void renderImGui(float width, float height);

    void dispose();
}
