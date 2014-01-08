package com.Group10.framework;

import com.Group10.framework.Graphics.ImageFormat;


/**
 * 
 * @author 10-a Luu Vinh Loc
 */

public interface Image {
    public int getWidth();
    public int getHeight();
    public ImageFormat getFormat();
    public void dispose();
    public void setid(int id);
    public int getid();
}
