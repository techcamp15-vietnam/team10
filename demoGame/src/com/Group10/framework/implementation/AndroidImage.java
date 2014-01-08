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
    int id;
    //format to bitmap
    public AndroidImage(Bitmap bitmap, ImageFormat format) {
        this.bitmap = bitmap;
        this.format = format;
    }
	//set and get index of bitmap
	@Override
	public void setid(int id) {
		// TODO Auto-generated method stub
		this.id = id;
	}  
    
	@Override
	public int getid() {
		// TODO Auto-generated method stub
		return this.id;
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
	//free memory before decoding the second bitmap
    @Override
    public void dispose() {
        bitmap.recycle();
    }
}
