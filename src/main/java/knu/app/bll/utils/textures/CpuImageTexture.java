package knu.app.bll.utils.textures;

import imgui.ImGui;
import org.lwjgl.opengl.GL33;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.nio.ByteBuffer;

public class CpuImageTexture implements UiTexturable {
    private int textureId = -1;
    private int width = 0;
    private int height = 0;
    private ByteBuffer cachedBuffer = null;

    @Override
    public void upload(BufferedImage image) {
        if (image == null) return;
        long start = System.nanoTime();

        int imgWidth = image.getWidth();
        int imgHeight = image.getHeight();

        boolean sizeChanged = imgWidth != this.width || imgHeight != this.height;
        this.width = imgWidth;
        this.height = imgHeight;

        if (cachedBuffer == null || sizeChanged) {
            cachedBuffer = ByteBuffer.allocateDirect(width * height * 4);
        } else {
            cachedBuffer.clear();
        }

        DataBuffer dataBuffer = image.getRaster().getDataBuffer();

        if (dataBuffer instanceof DataBufferInt) {
            // Обработка INT-буфера (обычно TYPE_INT_ARGB или TYPE_INT_RGB)
            int[] pixels = ((DataBufferInt) dataBuffer).getData();
            for (int pixel : pixels) {
                cachedBuffer.put((byte) ((pixel >> 16) & 0xFF)); // R
                cachedBuffer.put((byte) ((pixel >> 8) & 0xFF));   // G
                cachedBuffer.put((byte) (pixel & 0xFF));          // B
                cachedBuffer.put((byte) ((pixel >> 24) & 0xFF));  // A
            }
        } else if (dataBuffer instanceof DataBufferByte) {
            // Обработка BYTE-буфера (обычно TYPE_3BYTE_BGR или TYPE_4BYTE_ABGR)
            byte[] pixels = ((DataBufferByte) dataBuffer).getData();
            int pixelLength = image.getColorModel().getPixelSize() / 8;

            for (int i = 0; i < pixels.length; i += pixelLength) {
                // Для TYPE_3BYTE_BGR порядок: BGR -> нужно конвертировать в RGB
                if (pixelLength >= 3) {
                    cachedBuffer.put(pixels[i + 2]); // R
                    cachedBuffer.put(pixels[i + 1]); // G
                    cachedBuffer.put(pixels[i]);     // B
                    cachedBuffer.put((byte) 0xFF);   // A (если нет альфа-канала)
                }
                // Для TYPE_4BYTE_ABGR можно добавить соответствующую обработку
            }
        } else {
            throw new UnsupportedOperationException("Unsupported image type: " + image.getType());
        }

        cachedBuffer.flip();

        if (textureId == -1 || sizeChanged) {
            if (textureId != -1) GL33.glDeleteTextures(textureId);
            textureId = GL33.glGenTextures();
            GL33.glBindTexture(GL33.GL_TEXTURE_2D, textureId);
            GL33.glTexImage2D(GL33.GL_TEXTURE_2D, 0, GL33.GL_RGBA, width, height, 0,
                    GL33.GL_RGBA, GL33.GL_UNSIGNED_BYTE, cachedBuffer);
            GL33.glTexParameteri(GL33.GL_TEXTURE_2D, GL33.GL_TEXTURE_MIN_FILTER, GL33.GL_LINEAR);
            GL33.glTexParameteri(GL33.GL_TEXTURE_2D, GL33.GL_TEXTURE_MAG_FILTER, GL33.GL_LINEAR);
        } else {
            GL33.glBindTexture(GL33.GL_TEXTURE_2D, textureId);
            GL33.glTexSubImage2D(GL33.GL_TEXTURE_2D, 0, 0, 0, width, height,
                    GL33.GL_RGBA, GL33.GL_UNSIGNED_BYTE, cachedBuffer);
        }
        System.out.println("upload took: " + (System.nanoTime() - start) / 1_000_000f + " ms");
    }

    @Override
    public void renderImGui(float width, float height) {
        if (textureId != -1) {
            ImGui.image(textureId, width, height);
        }
    }

    @Override
    public void dispose() {
        if (textureId != -1) {
            GL33.glDeleteTextures(textureId);
            textureId = -1;
        }
    }
}