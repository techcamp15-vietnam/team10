package com.example.demogame;

import java.util.List;

import com.Group10.framework.Game;
import com.Group10.framework.Graphics;
import com.Group10.framework.Image;
import com.Group10.framework.Screen;
import com.Group10.framework.Graphics.ImageFormat;
import com.Group10.framework.Input.TouchEvent;

public class HeplScreen extends Screen {
	Image backMenuButton;

	/**
	 * @padam Game
	 * @author 10-c Pham Thanh Thuong 
	 * init Hitgame
	 */
	public HeplScreen(Game game) {
		super(game);
		System.gc();
		Graphics g = game.getGraphics();
		Assets.HelpBackGround = g.newImage("help_screen.png",
				ImageFormat.RGB565);

		backMenuButton = g.newImage("backMenuButton.png", ImageFormat.RGB565);
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
			// When TOUCH_DOWN
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if ((event.x > 10) && (event.x < 210) && (event.y > 1050)
						&& (event.y < 1250)) //
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
		g.drawImage(Assets.HelpBackGround, 0, 0);
		g.drawImage(backMenuButton, 10, 1050); // 210>x>10, 1250>y>1050

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
