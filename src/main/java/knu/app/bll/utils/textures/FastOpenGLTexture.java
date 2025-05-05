package knu.app.bll.utils.textures;

import imgui.ImGui;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12; // Для GL_BGR

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.nio.ByteBuffer;

public class FastOpenGLTexture implements UiTexturable {
    private int textureId = -1;
    private int width = 0;
    private int height = 0;
    private final Object lock = new Object();

    @Override
    public void upload(BufferedImage image) {
        synchronized (lock) {
            dispose();

            if (image == null) {
                System.err.println("Null image provided");
                return;
            }

            width = image.getWidth();
            height = image.getHeight();

            try {
                byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
                ByteBuffer buffer = ByteBuffer.allocateDirect(pixels.length).put(pixels).flip();

                textureId = GL11.glGenTextures();
                GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId);

                if (image.getType() == BufferedImage.TYPE_BYTE_GRAY) {
                    GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
                    GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RED,
                            width, height, 0,
                            GL11.GL_RED, GL11.GL_UNSIGNED_BYTE, buffer);
                }
                else {
                    int format = (image.getType() == BufferedImage.TYPE_3BYTE_BGR)
                            ? GL12.GL_BGR
                            : GL11.GL_RGB;
                    GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB,
                            width, height, 0,
                            format, GL11.GL_UNSIGNED_BYTE, buffer);
                }

                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
                GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
            } catch (Exception e) {
                dispose();
                throw new RuntimeException("Texture upload failed", e);
            }
        }
    }

    @Override
    public void renderImGui(float width, float height) {
        synchronized (lock) {
            if (textureId == -1) return;

            if (!GL11.glIsTexture(textureId)) {
                System.err.println("Attempt to render invalid texture");
                textureId = -1;
                return;
            }

            float aspectRatio = (float)this.width / this.height;
            float displayHeight = width / aspectRatio;

            ImGui.image(textureId, width, Math.min(height, displayHeight));
        }
    }



    public void dispose() {
        synchronized (lock) {
            if (textureId != -1 && GL11.glIsTexture(textureId)) {
                GL11.glDeleteTextures(textureId);
            }
            textureId = -1;
            width = 0;
            height = 0;
        }
    }

}





