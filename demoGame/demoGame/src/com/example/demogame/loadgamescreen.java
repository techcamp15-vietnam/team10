package com.example.demogame;

import java.util.List;

import com.Group10.framework.Game;
import com.Group10.framework.Graphics;
import com.Group10.framework.Screen;
import com.Group10.framework.Graphics.ImageFormat;
import com.Group10.framework.Input.TouchEvent;

/**
 * 
 * @author 10-c Pham Thanh Thuong
 * load menu game
 */

public class loadgamescreen extends Screen{
	
	/**
	 * @padam Game
	 * @author 10-c Pham Thanh Thuong
	 * init menu game
	 */
	public loadgamescreen(Game game) {	
		super(game);
		System.gc();
		Graphics g = game.getGraphics();
		Assets.menugame = g.newImage("menuBackground.png", ImageFormat.RGB565);
		Assets.menuSinglePlayerButton = g.newImage("menuSinglePlayerButton.png", ImageFormat.RGB565);
		Assets.menuSinglePlayerButton.setid(0);
		Assets.menuMultiplayerButton = g.newImage("menuMultiplayerButton.png", ImageFormat.RGB565);
		Assets.menuMultiplayerButton.setid(0);
		Assets.menuSettingButton = g.newImage("menuSettingButton.png", ImageFormat.RGB565);
		Assets.menuSettingButton.setid(0);
		Assets.menuHelpButton = g.newImage("menuHelpButton.png", ImageFormat.RGB565);
		Assets.menuHelpButton.setid(0);
	}
	
	
	
	/**
	 * 
	 * @author 10-c Pham Thanh Thuong
	 * process when game is changed
	 */
	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		// TODO Auto-generated method stub
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();	int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			//When TOUCH_DOWN
			if (event.type == TouchEvent.TOUCH_DOWN) {
				//event hitbutton
				if((event.x>100)&&(event.x <700)&&(event.y>300)&&(event.y<500))
				{
					Assets.menuSinglePlayerButton = g.newImage("menuSinglePlayerButton2.png", ImageFormat.RGB565);
					Assets.menuSinglePlayerButton.setid(1);
				}
				//event MultipalyerButton
				if((event.x>100)&&(event.x <700)&&(event.y>550)&&(event.y<750))
				{
					Assets.menuMultiplayerButton = g.newImage("menuMultiplayerButton2.png", ImageFormat.RGB565);
					Assets.menuMultiplayerButton.setid(1);
				}
				//event SettingButton
				if((event.x>100)&&(event.x <700)&&(event.y>800)&&(event.y<1000))
				{
					Assets.menuSettingButton = g.newImage("menuSettingButton2.png", ImageFormat.RGB565);
					Assets.menuSettingButton.setid(1);
				}
				//even HelpButton
				if((event.x>100)&&(event.x <700)&&(event.y>1050)&&(event.y<1250))
				{
					Assets.menuHelpButton = g.newImage("menuHelpButton2.png", ImageFormat.RGB565);
					Assets.menuHelpButton.setid(1);
				}
			}
			
			
			//when TOUCH_UP
			if (event.type == TouchEvent.TOUCH_UP) {
				//event hitbutton
				if((event.x>100)&&(event.x <700)&&(event.y>300)&&(event.y<500))
				{
					if(Assets.menuSinglePlayerButton.getid()==1) {
						Assets.menuSinglePlayerButton = g.newImage("menuSinglePlayerButton.png", ImageFormat.RGB565);
						//game.setScreen(new SinglePlayerScreen(game));
						game.setScreen(new ChoosePlayerScreen(game));
					}
				}
				else
				{
					Assets.menuSinglePlayerButton = g.newImage("menuSinglePlayerButton.png", ImageFormat.RGB565);
				}

				//event MultipalyerButton
				if((event.x>100)&&(event.x <700)&&(event.y>550)&&(event.y<750))
				{
					if(Assets.menuMultiplayerButton.getid()==1) {
						Assets.menuMultiplayerButton = g.newImage("menuMultiplayerButton.png", ImageFormat.RGB565);
						game.setScreen(new ReadyMultiPlayerScreen(game));
					}
				}
				else
				{
					Assets.menuMultiplayerButton = g.newImage("menuMultiplayerButton.png", ImageFormat.RGB565);
				}
				
				//event Settingbutton
				if((event.x>100)&&(event.x <700)&&(event.y>800)&&(event.y<1000))
				{
					if(Assets.menuSettingButton.getid()==1) {
						Assets.menuSettingButton = g.newImage("menuSettingButton.png", ImageFormat.RGB565);
						game.setScreen(new SettingScreen(game));
					}
				}
				else
				{
					Assets.menuSettingButton = g.newImage("menuSettingButton.png", ImageFormat.RGB565);
				}
				
				//event HelpButton
				if((event.x>100)&&(event.x <700)&&(event.y>1050)&&(event.y<1250))
				{
					if(Assets.menuHelpButton.getid()==1) {
						Assets.menuHelpButton = g.newImage("menuHelpButton.png", ImageFormat.RGB565);
						game.setScreen(new SettingScreen(game));
					}
				}
				else
				{
					Assets.menuHelpButton = g.newImage("menuHelpButton.png", ImageFormat.RGB565);
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
		g.drawImage(Assets.menugame, 0, 0);
		g.drawImage(Assets.menuSinglePlayerButton, 100, 300);  //w=600 h=200  700>x>100  500>y>300
		g.drawImage(Assets.menuMultiplayerButton, 100, 550);  //w=600 h=200  700>x>100  750>y>550
		g.drawImage(Assets.menuSettingButton, 100, 800);  //w=600 h=200  700>x>100  1000>y>800
		g.drawImage(Assets.menuHelpButton, 100, 1050);  //w=600 h=200  700>x>100  1250>y>1050
		// TODO Auto-generated method stub
		
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
        android.os.Process.killProcess(android.os.Process.myPid());
	}

}
