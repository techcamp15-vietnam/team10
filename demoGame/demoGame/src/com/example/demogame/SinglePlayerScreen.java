package com.example.demogame;

import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.Group10.framework.Game;
import com.Group10.framework.Graphics;
import com.Group10.framework.Image;
import com.Group10.framework.Screen;
import com.Group10.framework.Graphics.ImageFormat;
import com.Group10.framework.Input.TouchEvent;
import com.core.HighScoreManager;
import com.core.ManagerHoles;
import com.core.ManagerMap;
import com.core.ManagerTarget;
import com.example.motion.Animation;
import com.example.motion.EffectsTion;
import com.example.motion.NumberDraw;

public class SinglePlayerScreen extends Screen {
	enum GameState {
		READY, RUNNING, PAUSED, GAMEOVER
	}

	final int INT_TIME_UP = 25;
	final int INT_TIME_UP_READY = 3;
	final int NUMBER_HOLE = 70;
	final int NUMBER_TARGET = 30;
	
	
	int typePlayer;
	public GameState gameState = GameState.READY;
	public Image hole;
	public Image timeUp;
	public Image target1_1,target1_11, target1_2,target1_21, target1_3, target2_1, target2_2,target2_11, target2_21, target2_3, target1_die, target2_die;
	public Animation[] targetAnimation;
	public EffectsTion effectTarget_1;
	public EffectsTion effectTarget_2;
	public float timepause;
	public float timeplay;
	int intTime;
	int score;
	NumberDraw numberDraw;
	NumberDraw numberDrawBig;
	
	ManagerHoles[] holelist;
	ManagerTarget[] targetlist;
	Paint paint;
	public ManagerMap managerMap;
	
	HighScoreManager highScoreManger;
	
	/**
	 * @padam Game
	 * @author 10-c Pham Thanh Thuong
	 * init SingglePlayerGame
	 */
	public SinglePlayerScreen(Game game, int typeplayer) {
		
		super(game);
		this.typePlayer = typeplayer;
		gameState = GameState.READY;
		Log.i("TypePlayer", " " + typePlayer);
		
		Graphics g = game.getGraphics();
		
		//paint to write string in screen
		paint = new Paint();
		paint.setTextSize(50);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.RED);
		timepause = 0;
		timeplay = 0;
		intTime = 100;
		score = 0;
		numberDraw = new NumberDraw(g);
		numberDrawBig = new NumberDraw(g, 1);
		
		Assets.SinglePlayerBackground = g.newImage("menuBackground.png", ImageFormat.RGB565);
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
		timeUp = g.newImage("timeUpText.png", ImageFormat.RGB565);

		effectTarget_1 = new EffectsTion(g.newImage("target1_success.png", ImageFormat.RGB565));
		effectTarget_2 = new EffectsTion(g.newImage("target2_success.png", ImageFormat.RGB565));
		
		managerMap = new ManagerMap();
		managerMap.initwithholes(NUMBER_HOLE);
		holelist = managerMap.getHolelist();
		managerMap.initwithtarget(NUMBER_TARGET);
		targetlist = managerMap.gettargetlist();
		targetAnimation = initTarget(targetlist);
		
		highScoreManger = new HighScoreManager();
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
		{
			updateReady(touchEvents, deltaTime);
		}
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
		Log.i("Thanh Thuong", "Thanh thuong");
		timepause = timepause + deltaTime;
		intTime = INT_TIME_UP_READY - (int)(timepause/100);	
		if(intTime <= 0)
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
		timeplay = timeplay + deltaTime;
		intTime = INT_TIME_UP - (int)(timeplay/100);	
		
		if(intTime <= 0 )
		{
			gameState = GameState.GAMEOVER;
			return;
		}
		

		//change position target
		for(int i = 0; i< NUMBER_TARGET; i++){
			targetAnimation[i].update(20);
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
				
				int x = event.x/80;
				int y = event.y/120;
				int j = managerMap.checkTarget(x, y);
				if(j > -1){ //hit target
					
					
					if(targetlist[j].getTarget().getType()==0){
						effectTarget_1.addEffect(x*80, y*120);
					}
					else{
						effectTarget_2.addEffect(x*80, y*120);
					}
					
					
					
					if(targetlist[j].getTarget().getType()==this.typePlayer){
						// plus score
						score++;
					}
					else
					{
						if(score > 0)
							score--;
						//minus score
					}
					
					managerMap.changePos(j); //chage position
					targetAnimation[j].resetNumberAction(); //reset action1.
				}
			
			}
		}

		effectTarget_1.update();
		effectTarget_2.update();
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
	
		//g.drawString("" + intTime, 400, 750, paint);
		numberDrawBig.drawNumber(g, intTime, 340, 580);
		
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
			int y = holelist[i].getHole().getY()*120;
			g.drawImage(hole, x, y);
		}
		
		for(i=0;i<NUMBER_TARGET; i++)
		{			
			int x = targetlist[i].getTarget().getX()*80;
			int y = targetlist[i].getTarget().getY()*120;
			g.drawImage(targetAnimation[i].getImage(), x, y);
		}

		effectTarget_1.paint(g);
		effectTarget_2.paint(g);
		
		numberDraw.drawNumber(g, intTime, 710, 10);
		numberDraw.drawNumber(g, score, 600, 10);
		
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
			int y = holelist[i].getHole().getY()*120;
			g.drawImage(hole, x, y);
		}
		
		for(i=0;i<NUMBER_TARGET; i++)
		{			
			int x = targetlist[i].getTarget().getX()*80;
			int y = targetlist[i].getTarget().getY()*120;
			g.drawImage(targetAnimation[i].getImage(), x, y);
		}


		numberDraw.drawNumber(g, intTime, 710, 10);
		numberDraw.drawNumber(g, score, 600, 10);
		
		//g.drawString("" + intTime, 750, 50, paint);
		//g.drawString("Score: " + score, 500, 50, paint);
		
		g.drawString("Pause", 400, 640, paint);
		paint.setTextSize(100);
		g.drawString("Back Menu", 400, 1100, paint);
		paint.setTextSize(50);
	}
	

	/**
	 * 
	 * @author 10-c Pham Thanh Thuong
	 * repaint game
	 */
	public void paintGameOver(Graphics g,float deltaTime)
	{
		//paint.setTextSize(350);
		//g.drawString("Time UP", 400, 400, paint);
		g.drawImage(timeUp, 100, 540);
		/*
		if(highScoreManger.checkHighscore(score)){
			g.drawString("Your Hight Score:  " + score, 400, 800, paint);
		}
		else
		{
			g.drawString("Your Score:  " + score, 400, 800, paint);
		}
		*/
	}
	
	
	
	/**
	 * 
	 * @author 10-c Pham Thanh Thuong
	 * process when game pased
	 */
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		gameState = GameState.PAUSED;
	}

	
	/**
	 * 
	 * @author 10-c Pham Thanh Thuong
	 * process when game resume
	 */
	@Override
	public void resume() {
		// TODO Auto-generated method stub
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
		//game.setScreen(new loadgamescreen(game));
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
