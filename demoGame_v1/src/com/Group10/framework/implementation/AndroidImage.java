package com.Group10.framework.implementation;

import android.graphics.Bitmap;

import com.Group10.framework.Image;
import com.Group10.framework.Graphics.ImageFormat;

/**
 * 
 * @author 10-a Luu Vinh Loc
 */

public class AndroidImage implements Image {
    Bitmap bitmap;
    ImageFormat format;
    
    public AndroidImage(Bitmap bitmap, ImageFormat format) {
        this.bitmap = bitmap;
        this.format = format;
    }

    @Override
    public int getWidth() {
        return bitmap.getWidth();
    }

    @Override
    public int getHeight() {
        return bitmap.getHeight();
    }

    @Override
    public ImageFormat getFormat() {
        return format;
    }

    @Override
    public void dispose() {
        bitmap.recycle();
    }      
}
