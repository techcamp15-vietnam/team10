package com.Group10.framework.implementation;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.Base64;

import com.Group10.framework.Graphics;
import com.Group10.framework.Image;


/**
 * 
 * @author 10-a Luu Vinh Loc
 */
public class AndroidGraphics implements Graphics {
    AssetManager assets;
    Bitmap frameBuffer;
    Canvas canvas;
    Paint paint;
    Rect srcRect = new Rect();
    Rect dstRect = new Rect();
    
    public AndroidGraphics(AssetManager assets, Bitmap frameBuffer) {
        this.assets = assets;
        this.frameBuffer = frameBuffer;
        this.canvas = new Canvas(frameBuffer);
        this.paint = new Paint();
    }
	// load image from assets
    @Override
    public Image newImage(String fileName, ImageFormat format) {
        Config config = null;
        if (format == ImageFormat.RGB565)
            config = Config.RGB_565;
        else if (format == ImageFormat.ARGB4444)
            config = Config.ARGB_4444;
        else
            config = Config.ARGB_8888;

        Options options = new Options();
        options.inPreferredConfig = config;
        InputStream in = null;
        Bitmap bitmap = null;
        try {
            in = assets.open(fileName);
            bitmap = BitmapFactory.decodeStream(in, null, options);
            if (bitmap == null)
                throw new RuntimeException("Couldn't load bitmap from asset '"
                        + fileName + "'");
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load bitmap from asset '"
                    + fileName + "'");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }

        if (bitmap.getConfig() == Config.RGB_565)
            format = ImageFormat.RGB565;
        else if (bitmap.getConfig() == Config.ARGB_4444)
            format = ImageFormat.ARGB4444;
        else
            format = ImageFormat.ARGB8888;

        return new AndroidImage(bitmap, format);
    }
	//reset color to white-color
    @Override
    public void clearScreen(int color) {
        canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8,
                (color & 0xff));
    }
	//draw line from x,y to x2,y2
    @Override
    public void drawLine(int x, int y, int x2, int y2, int color) {
        paint.setColor(color);
        canvas.drawLine(x, y, x2, y2, paint);
    }
	// draw rectangular 
    @Override
    public void drawRect(int x, int y, int width, int height, int color) {
        paint.setColor(color);
        paint.setStyle(Style.FILL);
        canvas.drawRect(x, y, x + width - 1, y + height - 1, paint);
    }
    //draw color
    @Override
    public void drawARGB(int a, int r, int g, int b) {
        paint.setStyle(Style.FILL);
       canvas.drawARGB(a, r, g, b);
    }
    //draw text
    @Override
    public void drawString(String text, int x, int y, Paint paint){
    	canvas.drawText(text, x, y, paint);
    }
    //draw image from source to destination
    public void drawImage(Image Image, int x, int y, int srcX, int srcY,
            int srcWidth, int srcHeight) {
        srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX + srcWidth;
        srcRect.bottom = srcY + srcHeight;
        dstRect.left = x;
        dstRect.top = y;
        dstRect.right = x + srcWidth;
        dstRect.bottom = y + srcHeight;

        canvas.drawBitmap(((AndroidImage) Image).bitmap, srcRect, dstRect,
                null);
    }
    //draw image
    @Override
    public void drawImage(Image Image, int x, int y) {
        canvas.drawBitmap(((AndroidImage)Image).bitmap, x, y, null);
    }
    //draw scaled image from source to destination
    public void drawScaledImage(Image Image, int x, int y, int width, int height, int srcX, int srcY, int srcWidth, int srcHeight){    	
   	 	srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX + srcWidth;
        srcRect.bottom = srcY + srcHeight;     
        dstRect.left = x;
        dstRect.top = y;
        dstRect.right = x + width;
        dstRect.bottom = y + height;
        canvas.drawBitmap(((AndroidImage) Image).bitmap, srcRect, dstRect, null);
    }
    //get width from screen
    @Override
    public int getWidth() {
        return frameBuffer.getWidth();
    }
    //get height from screen
    @Override
    public int getHeight() {
        return frameBuffer.getHeight();
    }
    
  //flip image for multiplayer
    @Override
  	public void drawFlipImage(Image Image, int x, int y) {
	    Bitmap bmp= ((AndroidImage)Image).bitmap;//bitmap luu tru ho
		//Canvas canvas = new Canvas(bmp);
		Matrix matrix = new Matrix ();
		matrix.postRotate (180);
		Bitmap rotatedBitmap = Bitmap.createBitmap (bmp, 0, 0,bmp.getWidth (), bmp.getHeight (), matrix, true);
        canvas.drawBitmap(rotatedBitmap, x, y, null);
    }
    

    @Override
  	public void drawFlipString(String str, int x, int y) {
	    Bitmap bmp= StringToBitMap(str);//bitmap luu tru ho
		//Canvas canvas = new Canvas(bmp);
		Matrix matrix = new Matrix ();
		matrix.postRotate (180);
		Bitmap rotatedBitmap = Bitmap.createBitmap (bmp, 0, 0,bmp.getWidth (), bmp.getHeight (), matrix, true);
        canvas.drawBitmap(rotatedBitmap, x, y, null);
    }
    
    
    public Bitmap StringToBitMap(String encodedString){
        try{
          byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
          Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
          return bitmap;
        }catch(Exception e){
          e.getMessage();
          return null;
        }
         }
}
