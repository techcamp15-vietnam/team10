package com.example.demogame;

import android.R.bool;
import android.app.Activity;

import com.Group10.framework.Image;
import com.Group10.framework.Music;
import com.Group10.framework.Sound;
import com.core.SettingManager;

/**
 * 
 * @author 10-c Pham Thanh Thuong
 * save data mutilmedia and img on game
 */

public class Assets {

	public static Image menugame, SinglePlayerBackground, MultiPlayerBackground, SettingBackground, HelpBackGround,ChoosePlayerBackGround;
	public static Image menuSinglePlayerButton, menuMultiplayerButton, menuSettingButton, menuHelpButton;
	public static Sound click, cat, mouse;
	public static Music theme;
	public static Activity acc;
	public static Boolean isMuteSound;
	public static Boolean isMuteMusic;
	public static SettingManager setting;  

/**
 * @padam MainActivity 
 * @author 10-c Pham Thanh Thuong
 * load data
 */
	
	public static void load(MainActivity MainActivity) {
		// TODO Auto-generated method stub
		acc = MainActivity;
		setting = new SettingManager(acc);
		isMuteMusic = setting.getmusic();
		isMuteSound = setting.getsound();
		theme = MainActivity.getAudio().createMusic("menutheme.mp3");
		cat = MainActivity.getAudio().createSound("cat.mp3");
		mouse = MainActivity.getAudio().createSound("mouse.mp3");
	}

	
	public static void playMusic()
	{
		if(isMuteMusic)
		{
			theme.setLooping(true);
			theme.setVolume(0.85f);
			theme.play();
		}
	}
	
	public static void playSoundCat()
	{
			cat.play(1.85f);
	}
	public static void playSoundMouse()
	{
			mouse.play(1.85f);
		
	}
}
