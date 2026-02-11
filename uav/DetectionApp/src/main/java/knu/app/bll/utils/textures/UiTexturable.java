package knu.app.bll.utils.textures;

import java.awt.image.BufferedImage;

public interface UiTexturable {
    void upload(BufferedImage image);

    void renderImGui(float width, float height);
}
