package com.example.demogame;

import com.Group10.framework.Game;
import com.Group10.framework.Graphics;
import com.Group10.framework.Screen;
import com.Group10.framework.Graphics.ImageFormat;

/**
 * 
 * @author 10-c Pham Thanh Thuong
 * load menu game
 */

public class loadgamescreen extends Screen{
	
	/**
	 * 
	 * @author 10-c Pham Thanh Thuong
	 * init menu game
	 */
	public loadgamescreen(Game game) {		
		super(game);
		Graphics g = game.getGraphics();
		Assets.menugame = g.newImage("menuBackground.png", ImageFormat.RGB565);
	}
	
	
	
	/**
	 * 
	 * @author 10-c Pham Thanh Thuong
	 * process when game is changed
	 */
	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
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
		
	}

}
