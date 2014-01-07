package com.example.demogame;

import java.util.List;

import com.Group10.framework.Game;
import com.Group10.framework.Graphics;
import com.Group10.framework.Image;
import com.Group10.framework.Screen;
import com.Group10.framework.Graphics.ImageFormat;
import com.Group10.framework.Input.TouchEvent;
import com.core.ManagerHoles;
import com.core.ManagerMap;
import com.core.ManagerTarget;

public class SinglePlayerScreen extends Screen {
	public Image hole;
	public Image target1;
	public Image target2;
	ManagerHoles[] holelist;
	ManagerTarget[] targetlist;
	
	public ManagerMap managerMap;
	/**
	 * @padam Game
	 * @author 10-c Pham Thanh Thuong
	 * init Hitgame
	 */
	public SinglePlayerScreen(Game game) {		
		super(game);
		Graphics g = game.getGraphics();
		Assets.SinglePlayerBackground = g.newImage("menuBackground.png", ImageFormat.RGB565);
		hole= g.newImage("hole.png", ImageFormat.RGB565);
		target1= g.newImage("target1_1.png", ImageFormat.RGB565);
		target2= g.newImage("target2_1.png", ImageFormat.RGB565);
		
		managerMap = new ManagerMap();
		managerMap.initwithholes(10);
		managerMap.initwithtarget(10);
		holelist = managerMap.getHolelist();
		targetlist = managerMap.gettargetlist();
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
		g.drawImage(Assets.SinglePlayerBackground, 0, 0);
		int i;
		/*
		for(i=0; i<10; i++)
		{
			int x = holelist[i].getHole().getX()*80;
			int y = holelist[i].getHole().getY()*60;
			g.drawImage(hole, x, y);
		}
		*/
		for(i=0; i< 10; i++)
		{
			int x = targetlist[i].getTarget().getX()*80;
			int y = targetlist[i].getTarget().getY()*60;
			if(targetlist[i].getTarget().getType()==0)
				g.drawImage(target1, x, y);
			else
				g.drawImage(target2, x, y);
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
