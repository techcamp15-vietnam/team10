package com.example.demogame;

import java.util.List;

import com.Group10.framework.Game;
import com.Group10.framework.Graphics;
import com.Group10.framework.Image;
import com.Group10.framework.Screen;
import com.Group10.framework.Graphics.ImageFormat;
import com.Group10.framework.Input.TouchEvent;
import com.example.motion.Animation;

public class ChoosePlayerScreen extends Screen {
	public Image player1_0,player1_1, player1_2, player1_3,player1_4, player2_0,player2_1, player2_2, player2_3, player2_4;
	public Animation player1, player2;
	
	/**
	 * @padam Game
	 * @author 10-c Pham Thanh Thuong
	 * init ChoosePlayer
	 */
	public ChoosePlayerScreen(Game game) {		
		super(game);
		System.gc();
		Graphics g = game.getGraphics();
		Assets.ChoosePlayerBackGround = g.newImage("menuBackground.png", ImageFormat.RGB565);

		player1_0 = g.newImage("target1_intro0.png", ImageFormat.RGB565);
		player1_1 = g.newImage("target1_intro1.png", ImageFormat.RGB565);
		player1_2 = g.newImage("target1_intro2.png", ImageFormat.RGB565);
		player1_3 = g.newImage("target1_intro3.png", ImageFormat.RGB565);
		player1_4 = g.newImage("target1_intro4.png", ImageFormat.RGB565);
		player2_0 = g.newImage("target2_intro0.png", ImageFormat.RGB565);
		player2_1 = g.newImage("target2_intro1.png", ImageFormat.RGB565);
		player2_2 = g.newImage("target2_intro2.png", ImageFormat.RGB565);
		player2_3 = g.newImage("target2_intro3.png", ImageFormat.RGB565);
		player2_4 = g.newImage("target2_intro4.png", ImageFormat.RGB565);
		
		player1 = new Animation();
		player1.addFrame(player1_0, 300);
		player1.addFrame(player1_1, 50);
		player1.addFrame(player1_2, 50);
		player1.addFrame(player1_3, 50);
		player1.addFrame(player1_4, 300);
		player1.addFrame(player1_3, 50);
		player1.addFrame(player1_2, 50);
		player1.addFrame(player1_1, 50);
		player2 = new Animation();
		player2.addFrame(player2_0, 300);
		player2.addFrame(player2_1, 50);
		player2.addFrame(player2_2, 50);
		player2.addFrame(player2_3, 50);
		player2.addFrame(player2_4, 300);
		player2.addFrame(player2_3, 50);
		player2.addFrame(player2_2, 50);
		player2.addFrame(player2_1, 50);
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
				if((event.x>240)&&(event.x<560)&&(event.y>180)&&(event.y<420)) //player1
				{
					game.setScreen(new SinglePlayerScreen(game, 0));
					break;
				}
				if((event.x>240)&&(event.x<560)&&(event.y>860)&&(event.y<1100)) //player2
				{
					game.setScreen(new SinglePlayerScreen(game, 1));
					break;
				}
			}	
		}

		player1.update(10);
		player2.update(10);
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
		g.drawImage(Assets.ChoosePlayerBackGround, 0, 0);
		g.drawImage(player1.getImage(), 240, 180); //560>x>240, 420>y>180
		g.drawImage(player2.getImage(), 240, 860); //560>x>240, 1100>y>860
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
