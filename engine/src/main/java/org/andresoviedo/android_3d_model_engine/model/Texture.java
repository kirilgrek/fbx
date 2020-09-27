package org.andresoviedo.android_3d_model_engine.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.andresoviedo.util.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;

/**
 * Represents a model texture (jpb, bmp, etc)
 */
public class Texture {

    // texture info
    private String textureFile;
    private final byte[] textureData;
    private Bitmap bitmap;

    // whether the texture bytes were modified
    private boolean changed;

    // Loaded in GLThread
    private int textureId = -1;

    // native buffer to transfer to GPU
    private ByteBuffer textureBuffer;

    public Texture(byte[] data) {
        this.textureData = data;
    }

    public String getTextureFile() {
        return textureFile;
    }

    public void setTextureFile(String textureFile) {
        this.textureFile = textureFile;
    }

    public byte[] getTextureData() {
        return textureData;
    }

    public int getTextureId() {
        return textureId;
    }

    public void setTextureId(int textureId) {
        this.textureId = textureId;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    /**
     * Get the texture bytes buffer to write to GPU
     *
     * @return the buffer to write to GPU
     */
    public ByteBuffer getBuffer() {
        if (textureBuffer == null && getTextureData() != null) {
            textureBuffer = IOUtils.createNativeByteBuffer(getTextureData().length);
            textureBuffer.put(getTextureData());
        }
        return textureBuffer;
    }

    public void setBuffer(ByteBuffer textureBuffer) {
        this.textureBuffer = textureBuffer;
    }

    /**
     *
     * @return true if the texture bytes were modified
     */
    public boolean hasChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }
}
