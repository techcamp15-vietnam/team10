package com.example.motion;

import java.util.ArrayList;

import com.Group10.framework.Image;

/**
 * @author 10-c Pham Thanh Thuong
 * init Animation
 */
public class Animation {

	private ArrayList frames;
	private int currentFrame;
	private long animTime;
	private long totalDuration;
	private int numberAction;

	public Animation() {
		frames = new ArrayList();
		totalDuration = 0;
		numberAction = 0;
		synchronized (this) {
			animTime = 0;
			currentFrame = 0;
		}
	}

	/**
	 * @padam image: add image to sprite
	 * @padam  duration: time to view image 
	 * @author 10-c Pham Thanh Thuong
	 */
	public synchronized void addFrame(Image image, long duration) {
		totalDuration += duration;
		frames.add(new AnimFrame(image, totalDuration));
	}

	/**
	 * @padam: elapsedTime: time to update
	 * @author 10-c Pham Thanh Thuong
	 */
	public synchronized void update(long elapsedTime) {
		if (frames.size() > 1) {
			animTime += elapsedTime;
			if (animTime >= totalDuration) {
				animTime = animTime % totalDuration;
				currentFrame = 0;

			}

			while (animTime > getFrame(currentFrame).endTime) {
				currentFrame++;
				numberAction++;
			}
		}
	}

	public synchronized Image getImage() {
		if (frames.size() == 0) {
			return null;
		} else {
			return getFrame(currentFrame).image;
		}
	}

	private AnimFrame getFrame(int i) {
		return (AnimFrame) frames.get(i);
	}

	public int getNumberAction(){
		return numberAction;
	}
	public void resetNumberAction(){
		numberAction = 0;
		resetFrame();
	}
	private void resetFrame(){
		currentFrame = 0;
		animTime = 0;
	}
}
