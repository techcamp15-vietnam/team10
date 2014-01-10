package com.example.motion;

import com.Group10.framework.Image;

/**
 * @author 10-c Pham Thanh Thuong
 * init AnimFrame
 */
public class AnimFrame {

	Image image;
	long endTime;

	public AnimFrame(Image image, long endTime) {
		this.image = image;
		this.endTime = endTime;
	}
}