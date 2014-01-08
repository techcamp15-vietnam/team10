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
import com.example.demogame.SinglePlayerScreen.GameState;
import com.example.motion.Animation;

public class MultiPlayerScreen extends Screen {

	enum GameState {
		READY, RUNNING, PAUSED, GAMEOVER
	}

	final int INT_TIME_UP = 30;
	final int INT_TIME_UP_READY = 3;
	final int NUMBER_HOLE = 30;
	final int NUMBER_TARGET = 10;
	

	public float timepause;
	public float timeplay;
	int intTime;
	int score_1;
	int score_2;
	
	public GameState gameState = GameState.RUNNING;
	
	public Image target1_1,target1_11, target1_2,target1_21, target1_3, target2_1, target2_2,target2_11, target2_21, target2_3, target1_die, target2_die;
	public Animation[] targetAnimation;
	
	public Image hole;
	ManagerHoles[] holelist;
	ManagerTarget[] targetlist;
	Paint paint;
	public ManagerMap managerMap;
	
	
	/**
	 * @padam Game
	 * @author 10-c Pham Thanh Thuong
	 * init Hitgame
	 */
	public MultiPlayerScreen(Game game)
	{
		super(game);
		System.gc();
		Graphics g = game.getGraphics();
		Assets.MultiPlayerBackground = g.newImage("multiplayBackground2.png", ImageFormat.RGB565);
	

		paint = new Paint();
		paint.setTextSize(40);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.RED);

		timepause = 0;
		timeplay = 0;
		intTime = 100;
		score_1 = 0;
		score_2 = 0;
		
		hole= g.newImage("hole.png", ImageFormat.RGB565);
		

		target1_1 = g.newImage("target1_1.png", ImageFormat.RGB565);
		target1_11 = g.newImage("target1_11.png", ImageFormat.RGB565);
		target1_2 = g.newImage("target1_2.png", ImageFormat.RGB565);
		target1_21 = g.newImage("target1_2.png", ImageFormat.RGB565);
		target1_3 = g.newImage("target1_3.png", ImageFormat.RGB565);
		target2_1 = g.newImage("target2_1.png", ImageFormat.RGB565);
		target2_11 = g.newImage("target2_1.png", ImageFormat.RGB565);
		target2_2 = g.newImage("target2_2.png", ImageFormat.RGB565);
		target2_21 = g.newImage("target2_2.png", ImageFormat.RGB565);
		target2_3 = g.newImage("target2_3.png", ImageFormat.RGB565);
		target1_die = g.newImage("target1_success.png", ImageFormat.RGB565);
		target2_die = g.newImage("target2_success.png", ImageFormat.RGB565);
		
		managerMap = new ManagerMap();
		managerMap.initwithholes(NUMBER_HOLE);//init hole
		holelist = managerMap.getHolelist();
		managerMap.initwithtarget(NUMBER_TARGET);
		targetlist = managerMap.gettargetlist();
		targetAnimation = initTarget(targetlist);
	}
	

	/**
	 * 
	 * @author 10-c Pham Thanh Thuong
	 * init Animation
	 */
	public Animation[] initTarget(ManagerTarget[] list)
	{
		Animation[] targetAnimation = new Animation[NUMBER_TARGET];
		for(int i= 0;  i < NUMBER_TARGET; i ++)
		{
			targetAnimation[i] = new Animation();
			if(list[i].getTarget().getType()==0){
				targetAnimation[i].addFrame(target1_1, 300);
				targetAnimation[i].addFrame(target1_11, 30);
				targetAnimation[i].addFrame(target1_2, 30);
				targetAnimation[i].addFrame(target1_21, 30);
				targetAnimation[i].addFrame(target1_3, 500);
				targetAnimation[i].addFrame(target1_21, 30);
				targetAnimation[i].addFrame(target1_2, 30);
				targetAnimation[i].addFrame(target1_11, 30);
			}
			else
			{
				targetAnimation[i].addFrame(target2_1, 300);
				targetAnimation[i].addFrame(target2_11, 30);
				targetAnimation[i].addFrame(target2_2, 30);
				targetAnimation[i].addFrame(target2_21, 30);
				targetAnimation[i].addFrame(target2_3, 500);
				targetAnimation[i].addFrame(target2_21, 30);
				targetAnimation[i].addFrame(target2_2, 30);
				targetAnimation[i].addFrame(target2_11, 30);
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
		timepause = timepause + deltaTime;
		intTime = INT_TIME_UP_READY - (int)(timepause/100);	
		if(intTime == 0)
		{
			paint.setTextSize(40);
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
		//process time
		timeplay = timeplay + deltaTime;
		intTime = INT_TIME_UP - (int)(timeplay/100);	
		
		if(intTime <= 0 )
		{
			gameState = GameState.GAMEOVER;
			return;
		}
		
		//change position target
		for(int i = 0; i< NUMBER_TARGET; i++){
			targetAnimation[i].update(45);
			if(targetAnimation[i].getNumberAction()>21){
				//change target
				managerMap.changePos(i); //chage position
				targetAnimation[i].resetNumberAction(); //reset action
			}
		}
		

		int len = TouchEvents.size();
		//when touch
		for (int i = 0; i < len; i++) {
			TouchEvent event = TouchEvents.get(i);
			//When TOUCH_DOWN
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if(event.y < 680)
				{
					int x = event.x/80;
					int y = event.y/60;
					int j = managerMap.checkTarget(x, y);
					if(j > -1){ //hit target
						if(targetlist[j].getTarget().getType()==1){
							// plus score
							score_2++;
						}
						else
						{
							if(score_2 > 0)
								score_2--;
							//minus score
						}
						
						managerMap.changePos(j); //chage position
						targetAnimation[j].resetNumberAction(); //reset action1.
					}
				}
				else
				{
					int x = event.x/80;
					int y = (event.y - 680)/60;
					int j = managerMap.checkTarget(x, y);
					if(j > -1){ //hit target
						if(targetlist[j].getTarget().getType()==0){
							// plus score
							score_1++;
						}
						else
						{
							if(score_1 > 0)
								score_1--;
							//minus score
						}
						
						managerMap.changePos(j); //chage position
						targetAnimation[j].resetNumberAction(); //reset action1.
					}
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
		int len = TouchEvents.size();
		// TODO Auto-generated method stub
		timepause = 0;	
		for (int i = 0; i < len; i++) {
			TouchEvent event = TouchEvents.get(i);
			//When TOUCH_DOWN
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if((event.x>300)&&(event.x<500)&&(event.y>1050)&&(event.y<1150)) //player1
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
		g.drawImage(Assets.MultiPlayerBackground, 0, 0);

		if (gameState == GameState.READY)
			paintReady(g, deltaTime);
		if (gameState == GameState.RUNNING)
			paintRunning(g, deltaTime);
		if (gameState == GameState.PAUSED)
			paintPaused(g, deltaTime);
		if (gameState == GameState.GAMEOVER)
			paintGameOver(g, deltaTime);
		
		// TODO Auto-generated method stub
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
			int y = holelist[i].getHole().getY()*60;
			g.drawImage(hole, x, 680+y);
			g.drawFlipImage(hole, x, y);
		}
		g.drawString("" + intTime, 400, 750, paint);
	}
	

	/**
	 * 
	 * @author 10-c Pham Thanh Thuong
	 * repaint game
	 */
	public void paintRunning(Graphics g,float deltaTime)
	{
		int i;
		for(i=0; i<NUMBER_HOLE; i++)
		{
			int x = holelist[i].getHole().getX()*80;
			int y = holelist[i].getHole().getY()*60;
			g.drawImage(hole, x, 680+y);
			g.drawFlipImage(hole, x, y);
		}

		for(i=0;i<NUMBER_TARGET; i++)
		{			
			int x = targetlist[i].getTarget().getX()*80;
			int y = targetlist[i].getTarget().getY()*60;
			g.drawImage(targetAnimation[i].getImage(), x, 680 + y);
			g.drawFlipImage(targetAnimation[i].getImage(), x, y);
		}
		

		g.drawString("" + intTime, 25, 660, paint);
		g.drawString("Score: " + score_1, 150, 660, paint);
		//g.drawFlipString("Score: " + score_1, 150, 660);

	}
	
	/**
	 * 
	 * @author 10-c Pham Thanh Thuong
	 * repaint game
	 */
	public void paintPaused(Graphics g,float deltaTime)
	{
		int i;
		for(i=0; i<NUMBER_HOLE; i++)
		{
			int x = holelist[i].getHole().getX()*80;
			int y = holelist[i].getHole().getY()*60;
			g.drawImage(hole, x, 680+y);
			g.drawFlipImage(hole, x, y);
		}

		for(i=0;i<NUMBER_TARGET; i++)
		{			
			int x = targetlist[i].getTarget().getX()*80;
			int y = targetlist[i].getTarget().getY()*60;
			g.drawImage(targetAnimation[i].getImage(), x, 680 + y);
			g.drawFlipImage(targetAnimation[i].getImage(), x, y);
		}
		

		g.drawString("" + intTime, 25, 660, paint);
		g.drawString("Score: " + score_1, 150, 660, paint);
		
		paint.setTextSize(50);
		g.drawString("Pause", 400, 640, paint);
		paint.setTextSize(100);
		g.drawString("Back Menu", 400, 1100, paint);
		paint.setTextSize(40);
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
		gameState = GameState.PAUSED;
	}


	
	/**
	 * 
	 * @author 10-c Pham Thanh Thuong
	 * process when game resume
	 */
	@Override
	public void resume() {
		gameState = GameState.RUNNING;
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
		if(gameState == GameState.PAUSED)
		{
			gameState = GameState.READY;
		}
		if(gameState == GameState.RUNNING){
			pause();
		}
		if(gameState == GameState.GAMEOVER)
		{
			game.setScreen(new loadgamescreen(game));
		}
	}

}
