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
		Graphics g = game.getGraphics();
		Assets.menugame = g.newImage("menuBackground.png", ImageFormat.RGB565);
		Assets.menuHitButton = g.newImage("menuHitButton.png", ImageFormat.RGB565);
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
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if((event.x>100)&&(event.x <700)&&(event.y>500)&&(event.y<700))
					Assets.menuHitButton = g.newImage("menuHitButton2.png", ImageFormat.RGB565);
			}
			if (event.type == TouchEvent.TOUCH_UP) {
				//if((event.x>100)&&(event.x <700)&&(event.y>500)&&(event.y<700))
					Assets.menuHitButton = g.newImage("menuHitButton1.png", ImageFormat.RGB565);
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
		g.drawImage(Assets.menuHitButton, 100, 500);  //w=600 h=200  700x>100  700>y>500
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
