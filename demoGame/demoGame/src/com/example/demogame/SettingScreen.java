package com.example.demogame;

import java.util.List;

import com.Group10.framework.Game;
import com.Group10.framework.Graphics;
import com.Group10.framework.Image;
import com.Group10.framework.Screen;
import com.Group10.framework.Graphics.ImageFormat;
import com.Group10.framework.Input.TouchEvent;

public class SettingScreen extends Screen {
	Image backMenuButton;
	Image musicLabel, soundLabel;
	Image tick, untick;
	
	/**
	 * @padam Game
	 * @author 10-c Pham Thanh Thuong
	 * init Hitgame
	 */
	public SettingScreen(Game game) {		
		super(game);
		System.gc();
		Graphics g = game.getGraphics();
		Assets.SettingBackground = g.newImage("menuBackground.png", ImageFormat.RGB565);
		
		backMenuButton = g.newImage("backMenuButton.png", ImageFormat.RGB565);
		musicLabel = g.newImage("musicLabel.png", ImageFormat.RGB565);
		soundLabel = g.newImage("soundLabel.png", ImageFormat.RGB565);
		tick = g.newImage("tick.png", ImageFormat.RGB565);
		untick = g.newImage("unTick.png", ImageFormat.RGB565);
	}
	
	

	/**
	 * 
	 * @author 10-c Pham Thanh Thuong
	 * process when game is changed
	 */
	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();	
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			//When TOUCH_DOWN
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if((event.x>250)&&(event.x <700)&&(event.y>500)&&(event.y<580))
				{
					//change mute music
					if(Assets.isMuteMusic)
					{
						Assets.muteMusic();
					}
					else
					{
						Assets.unmuteMusic();
					}
				}
				if((event.x>250)&&(event.x <700)&&(event.y>700)&&(event.y<780))
				{
					//change mute sound
					if(Assets.isMuteSound)
					{
						Assets.muteSound();
					}
					else
					{
						Assets.unmuteSound();
					}
				}
				if((event.x>10)&&(event.x<210)&&(event.y>1050)&&(event.y<1250)) //
				{
					game.setScreen(new loadgamescreen(game));
					break;
				}
			}
		}
		
	}

	
	/**
	 * 
	 * @author 10-c Pham Thanh Thuong
	 * repaint game
	 */
	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.SettingBackground, 0, 0);

		g.drawImage(backMenuButton, 10, 1050);   //210>x>10, 1250>y>1050
		g.drawImage(musicLabel, 250, 500);  //650 > x>250   580>y>500
		g.drawImage(soundLabel, 250, 700);  //650 > x>250   780>y>700
		if(Assets.isMuteMusic)
		{
			g.drawImage(tick, 600, 500);
		}
		else
		{
			g.drawImage(untick, 600, 500);
		}
		if(Assets.isMuteSound)
		{
			g.drawImage(tick, 600, 700);
		}
		else
		{
			g.drawImage(untick, 600, 700);
		}
	}

	
	
	/**
	 * 
	 * @author 10-c Pham Thanh Thuong
	 * process when game pased
	 */
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * 
	 * @author 10-c Pham Thanh Thuong
	 * process when game resume
	 */
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * 
	 * @author 10-c Pham Thanh Thuong
	 * process when game deispose
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * 
	 * @author 10-c Pham Thanh Thuong
	 * process when back
	 */
	@Override
	public void backButton() {
		// TODO Auto-generated method stub
		game.setScreen(new loadgamescreen(game));
		
	}

}
