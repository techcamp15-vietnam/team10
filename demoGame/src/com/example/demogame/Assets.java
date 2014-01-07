package com.example.demogame;

import com.Group10.framework.Image;
import com.Group10.framework.Music;
import com.Group10.framework.Sound;

/**
 * 
 * @author 10-c Pham Thanh Thuong
 * save data mutilmedia and img on game
 */

public class Assets {
	
	public static Image menugame;
	public static Sound click;
	public static Music theme;

/**
 * 
 * @author 10-c Pham Thanh Thuong
 * load data
 */
	
	public static void load(MainActivity MainActivity) {
		// TODO Auto-generated method stub
		theme = MainActivity.getAudio().createMusic("menutheme.mp3");
		theme.setLooping(true);
		theme.setVolume(0.85f);
		theme.play();
	}
	
}
