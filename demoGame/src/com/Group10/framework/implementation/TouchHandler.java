package com.Group10.framework.implementation;

import java.util.List;

import android.view.View.OnTouchListener;

import com.Group10.framework.Input.TouchEvent;

/**
 * 
 * @author 10-b Pham Thanh Thuong
 */

public interface TouchHandler extends OnTouchListener {
    public boolean isTouchDown(int pointer);
    
    public int getTouchX(int pointer);
    
    public int getTouchY(int pointer);
    
    public List<TouchEvent> getTouchEvents();
}
