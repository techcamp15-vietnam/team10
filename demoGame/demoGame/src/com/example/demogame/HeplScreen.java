package com.example.demogame;

import java.util.List;

import com.Group10.framework.Game;
import com.Group10.framework.Graphics;
import com.Group10.framework.Screen;
import com.Group10.framework.Graphics.ImageFormat;
import com.Group10.framework.Input.TouchEvent;

public class HeplScreen extends Screen {
	/**
	 * @padam Game
	 * @author 10-c Pham Thanh Thuong
	 * init Hitgame
	 */
	public HeplScreen(Game game) {		
		super(game);
		System.gc();
		Graphics g = game.getGraphics();
		Assets.HelpBackGround = g.newImage("menuBackground.png", ImageFormat.RGB565);
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
		
	}

	
	/**
	 * 
	 * @author 10-c Pham Thanh Thuong
	 * repaint game
	 */
	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.HelpBackGround, 0, 0);
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
		game.setScreen(new loadgamescreen(game));
		
	}

}
