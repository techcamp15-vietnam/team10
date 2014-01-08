package com.example.demogame;

import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;

import com.Group10.framework.Game;
import com.Group10.framework.Graphics;
import com.Group10.framework.Image;
import com.Group10.framework.Screen;
import com.Group10.framework.Graphics.ImageFormat;
import com.Group10.framework.Input.TouchEvent;
import com.core.ManagerHoles;
import com.core.ManagerMap;
import com.core.ManagerTarget;
import com.example.motion.Animation;

public class SinglePlayerScreen extends Screen {
	enum GameState {
		READY, RUNNING, PAUSED, GAMEOVER
	}

	final int INT_TIME_UP = 100;
	final int INT_TIME_UP_READY = 3;
	final int NUMBER_HOLE = 70;
	final int NUMBER_TARGET = 10;
	
	
	int typePlayer;
	public GameState gameState = GameState.READY;
	public Image hole;
	public Image target1;
	public Image target2;
	public Image target1_1, target1_2, target1_3, target2_1, target2_2, target2_3;
	public Animation[] targetAnimation;
	public float time;
	int intTime;
	
	ManagerHoles[] holelist;
	ManagerTarget[] targetlist;
	Paint paint;
	public ManagerMap managerMap;
	/**
	 * @padam Game
	 * @author 10-c Pham Thanh Thuong
	 * init SingglePlayerGame
	 */
	public SinglePlayerScreen(Game game, int typePlayer) {
		
		super(game);
		this.typePlayer = typePlayer;
		Graphics g = game.getGraphics();
		
		//paint to write string in screen
		paint = new Paint();
		paint.setTextSize(300);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.RED);
		time = 0;
		intTime = 100;
		
		Assets.SinglePlayerBackground = g.newImage("menuBackground.png", ImageFormat.RGB565);
		hole= g.newImage("hole.png", ImageFormat.RGB565);
		target1= g.newImage("target1_1.png", ImageFormat.RGB565);
		target2= g.newImage("target2_1.png", ImageFormat.RGB565);
		
		target1_1 = g.newImage("target1_1.png", ImageFormat.RGB565);
		target1_2 = g.newImage("target1_2.png", ImageFormat.RGB565);
		target1_3 = g.newImage("target1_3.png", ImageFormat.RGB565);
		target2_1 = g.newImage("target2_1.png", ImageFormat.RGB565);
		target2_2 = g.newImage("target2_2.png", ImageFormat.RGB565);
		target2_3 = g.newImage("target2_3.png", ImageFormat.RGB565);
		
		managerMap = new ManagerMap();
		managerMap.initwithholes(NUMBER_HOLE);
		holelist = managerMap.getHolelist();
		managerMap.initwithtarget(NUMBER_TARGET);
		targetlist = managerMap.gettargetlist();
		targetAnimation = initTarget(targetlist);
	}
	
	
	public Animation[] initTarget(ManagerTarget[] list)
	{
		Animation[] targetAnimation = new Animation[NUMBER_TARGET];
		for(int i= 0;  i < NUMBER_TARGET; i ++)
		{
			targetAnimation[i] = new Animation();
			if(list[i].getTarget().getType()==0){
				targetAnimation[i].addFrame(target1_1, 300);
				targetAnimation[i].addFrame(target1_2, 50);
				targetAnimation[i].addFrame(target1_3, 300);
				targetAnimation[i].addFrame(target1_2, 50);
			}
			else
			{
				targetAnimation[i].addFrame(target2_1, 300);
				targetAnimation[i].addFrame(target2_2, 50);
				targetAnimation[i].addFrame(target2_3, 300);
				targetAnimation[i].addFrame(target2_2, 50);
			}
		}
		return targetAnimation;
	}
	
	/**
	 * 
	 * @author 10-c Pham Thanh Thuong
	 * process when game is changed
	 */
	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		if (gameState == GameState.READY)
			updateReady(touchEvents, deltaTime);
		if (gameState == GameState.RUNNING)
			updateRunning(touchEvents, deltaTime);
		if (gameState == GameState.PAUSED)
			updatePaused(touchEvents, deltaTime);
		if (gameState == GameState.GAMEOVER)
			updateGameOver(touchEvents, deltaTime);
	}


	/**
	 * 
	 * @author 10-c Pham Thanh Thuong
	 * process when game is ready
	 */
	public void updateReady(List<TouchEvent> TouchEvents, float deltaTime)
	{
		time = time + deltaTime;
		intTime = INT_TIME_UP_READY - (int)(time/100);	
		if(intTime == 0)
		{
			paint.setTextSize(50);
			gameState = GameState.RUNNING;
		}
		
	}
	

	/**
	 * 
	 * @author 10-c Pham Thanh Thuong
	 * process when game is running
	 */
	public void updateRunning(List<TouchEvent> TouchEvents, float deltaTime)
	{
		time = time + deltaTime;
		intTime = INT_TIME_UP - (int)(time/100);	
		int len = TouchEvents.size();
		// TODO Auto-generated method stub

		//change position target
		for(int i = 0; i< NUMBER_TARGET; i++){
			targetAnimation[i].update(10);
			if(targetAnimation[i].getNumberAction()>9){
				//change target
				managerMap.changePos(i); //chage position
				targetAnimation[i].resetNumberAction(); //reset action
			}
		}
		
		//when touch
		for (int i = 0; i < len; i++) {
			TouchEvent event = TouchEvents.get(i);
			//When TOUCH_DOWN
			if (event.type == TouchEvent.TOUCH_DOWN) {
				int x = event.x;
				int y = event.y;
				System.out.print(";  x= " + x+"; y= " + y);
				int j = managerMap.checkTarget(x, y);
				if(j > -1){ //hit target
					System.out.print("j= " + j +";  x= " + x+"; y= " + y);
					if(targetlist[i].getTarget().getType()==this.typePlayer){
						// plus score
					}
					else
					{
						//minus score
					}
					managerMap.changePos(j); //chage position
					targetAnimation[j].resetNumberAction(); //reset action
				}
			}
		}
	}
	
	

	/**
	 * 
	 * @author 10-c Pham Thanh Thuong
	 * process when game is paused
	 */
	public void updatePaused(List<TouchEvent> TouchEvents, float deltaTime)
	{
		
	}
	
	

	/**
	 * 
	 * @author 10-c Pham Thanh Thuong
	 * process when game over
	 */
	public void updateGameOver(List<TouchEvent> TouchEvents, float deltaTime)
	{
		
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


		if (gameState == GameState.READY)
			paintReady(g, deltaTime);
		if (gameState == GameState.RUNNING)
			paintRunning(g, deltaTime);
		if (gameState == GameState.PAUSED)
			paintPaused(g, deltaTime);
		if (gameState == GameState.GAMEOVER)
			paintGameOver(g, deltaTime);
		
	}


	/**
	 * 
	 * @author 10-c Pham Thanh Thuong
	 * repaint game
	 */
	public void paintReady(Graphics g,float deltaTime)
	{
		int i;
		for(i=0; i<NUMBER_HOLE; i++)
		{
			int x = holelist[i].getHole().getX()*80;
			int y = holelist[i].getHole().getY()*120;
			g.drawImage(hole, x, y);
		}
	
		g.drawString("" + intTime, 400, 750, paint);
	}
	public void paintRunning(Graphics g,float deltaTime)
	{
		int i;
		for(i=0; i<NUMBER_HOLE; i++)
		{
			int x = holelist[i].getHole().getX()*80;
			int y = holelist[i].getHole().getY()*120;
			g.drawImage(hole, x, y);
		}
		
		for(i=0;i<NUMBER_TARGET; i++)
		{
			
			int x = targetlist[i].getTarget().getX()*80;
			int y = targetlist[i].getTarget().getY()*120;
			g.drawImage(targetAnimation[i].getImage(), x, y);
		}
		
	
		g.drawString("" + intTime, 750, 50, paint);
	}
	

	/**
	 * 
	 * @author 10-c Pham Thanh Thuong
	 * repaint game
	 */
	public void paintPaused(Graphics g,float deltaTime)
	{
		
	}
	

	/**
	 * 
	 * @author 10-c Pham Thanh Thuong
	 * repaint game
	 */
	public void paintGameOver(Graphics g,float deltaTime)
	{
		
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
