package com.example.motion;

import com.Group10.framework.Graphics;
import com.Group10.framework.Image;
import com.Group10.framework.Graphics.ImageFormat;

public class NumberDraw {
	Image img0,img1,img2,img3,img4,img5,img6,img7,img8,img9;
	int size;
	public NumberDraw(Graphics g) {
		img0 = g.newImage("number0.png", ImageFormat.RGB565);
		img1 = g.newImage("number1.png", ImageFormat.RGB565);
		img2 = g.newImage("number2.png", ImageFormat.RGB565);
		img3 = g.newImage("number3.png", ImageFormat.RGB565);
		img4 = g.newImage("number4.png", ImageFormat.RGB565);
		img5 = g.newImage("number5.png", ImageFormat.RGB565);
		img6 = g.newImage("number6.png", ImageFormat.RGB565);
		img7 = g.newImage("number7.png", ImageFormat.RGB565);
		img8 = g.newImage("number8.png", ImageFormat.RGB565);
		img9 = g.newImage("number9.png", ImageFormat.RGB565);
		size = 0;
	}
	public NumberDraw(Graphics g, int i) {
		img0 = g.newImage("point0.png", ImageFormat.RGB565);
		img1 = g.newImage("point1.png", ImageFormat.RGB565);
		img2 = g.newImage("point2.png", ImageFormat.RGB565);
		img3 = g.newImage("point3.png", ImageFormat.RGB565);
		img4 = g.newImage("point4.png", ImageFormat.RGB565);
		img5 = g.newImage("point5.png", ImageFormat.RGB565);
		img6 = g.newImage("point6.png", ImageFormat.RGB565);
		img7 = g.newImage("point7.png", ImageFormat.RGB565);
		img8 = g.newImage("point8.png", ImageFormat.RGB565);
		img9 = g.newImage("point9.png", ImageFormat.RGB565);
		size = 1;
	}
	public void drawNumber(Graphics g, int num, int x, int y)
	{
		if(num > 99)
		{
			int i = num/100;
			drawImage(g, i, x, y);
			int k = num%100;
			i = k/10;
			drawImage(g, i, x + 30 + size*30, y);
			i = k%10;
			drawImage(g, i, x + 60+ size*30, y);
			
		}
		else
		{
			int i = num/10;
			drawImage(g, i, x, y);
			i = num%10;
			drawImage(g, i, x + 30+ size*30, y);
		}
	}
	
	

	public void drawNumberFlip(Graphics g, int num, int x, int y)
	{
		if(num > 99)
		{
			int i = num/100;
			drawImageFlip(g, i, x, y);
			int k = num%100;
			i = k/10;
			drawImageFlip(g, i, x - 30 - size*30, y);
			i = k%10;
			drawImageFlip(g, i, x - 60 - size*30, y);
			
		}
		else
		{
			int i = num/10;
			drawImageFlip(g, i, x, y);
			i = num%10;
			drawImageFlip(g, i, x - 30 - size*30, y);
		}
	}
	
	
	
	private void drawImage(Graphics g, int i, int x, int y)
	{
		switch (i)
		{
		case 0: 
			g.drawImage(img0, x, y);
			break;
		case 1: 
			g.drawImage(img1, x, y);
			break;
		case 2: 
			g.drawImage(img2, x, y);
			break;
		case 3: 
			g.drawImage(img3, x, y);
			break;
		case 4: 
			g.drawImage(img4, x, y);
			break;
		case 5: 
			g.drawImage(img5, x, y);
			break;
		case 6: 
			g.drawImage(img6, x, y);
			break;
		case 7: 
			g.drawImage(img7, x, y);
			break;
		case 8: 
			g.drawImage(img8, x, y);
			break;
		case 9: 
			g.drawImage(img9, x, y);
			break;
		}
	}
	
	
	private void drawImageFlip(Graphics g, int i, int x, int y)
	{
		switch (i)
		{
		case 0: 
			g.drawFlipImage(img0, x, y);
			break;
		case 1: 
			g.drawFlipImage(img1, x, y);
			break;
		case 2: 
			g.drawFlipImage(img2, x, y);
			break;
		case 3: 
			g.drawFlipImage(img3, x, y);
			break;
		case 4: 
			g.drawFlipImage(img4, x, y);
			break;
		case 5: 
			g.drawFlipImage(img5, x, y);
			break;
		case 6: 
			g.drawFlipImage(img6, x, y);
			break;
		case 7: 
			g.drawFlipImage(img7, x, y);
			break;
		case 8: 
			g.drawFlipImage(img8, x, y);
			break;
		case 9: 
			g.drawFlipImage(img9, x, y);
			break;
		}
	}

}
