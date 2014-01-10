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

	final int INT_TIME_UP = 20;
	final int INT_TIME_UP_READY = 3;
	final int NUMBER_HOLE = 70;
	final int NUMBER_TARGET = 30;
	final int changeY = 80;
	final int SIZECOMBO = 4;
	final int TIME_COMBO_UP = 200;
	
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
	boolean isNewHighScore;
	Image newHightScore;
	Image scoreImage;
	Image yourScore;
	Image highscore;
	Image comboIMG;
	NumberDraw numberDraw;
	NumberDraw numberDrawBig;
	

	int setcombo;
	int combo;
	int time_combo;
	
	Image backMenubutton, pause, replay;
	
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
		isNewHighScore = false;
		newHightScore = g.newImage("newHightScore.png", ImageFormat.RGB565);
		scoreImage = g.newImage("score.png", ImageFormat.RGB565);
		yourScore = g.newImage("yourScore.png", ImageFormat.RGB565);
		highscore = g.newImage("highScore.png", ImageFormat.RGB565);
		numberDraw = new NumberDraw(g);
		numberDrawBig = new NumberDraw(g, 1);
		
		time_combo = 0;
		setcombo = 0;
		comboIMG = g.newImage("combo.png", ImageFormat.RGB565);
		
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

		backMenubutton = g.newImage("backMenuButton.png", ImageFormat.RGB565);
		pause = g.newImage("pause.png", ImageFormat.RGB565);
		replay = g.newImage("replayButton.png", ImageFormat.RGB565);
		
		effectTarget_1 = new EffectsTion(g.newImage("target1_success.png", ImageFormat.RGB565));
		effectTarget_2 = new EffectsTion(g.newImage("target2_success.png", ImageFormat.RGB565));
		
		managerMap = new ManagerMap();
		managerMap.initwithholes(NUMBER_HOLE);
		holelist = managerMap.getHolelist();
		managerMap.initwithtarget(NUMBER_TARGET);
		targetlist = managerMap.gettargetlist();
		targetAnimation = initTarget(targetlist);
		
		highScoreManger = new HighScoreManager(Assets.acc);
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
		if(time_combo > 0) time_combo--;
		else 
			{
				if(setcombo > SIZECOMBO) 
					{
						//score = score + setcombo - SIZECOMBO;
						setcombo = 0;
					}
			}
		
		if(intTime <= 0 )
		{
			gameState = GameState.GAMEOVER;
			isNewHighScore = highScoreManger.checkHighscore(score);
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
				int y = (event.y - changeY)/120;
				int j = managerMap.checkTarget(x, y);
				if(j > -1){ //hit target
					
					
					if(targetlist[j].getTarget().getType()==0){

						Assets.playSoundCat();
						effectTarget_1.addEffect(x*80, y*120 + changeY);
					}
					else{

						Assets.playSoundMouse();
						effectTarget_2.addEffect(x*80, y*120 + changeY);
					}
					
					
					
					if(targetlist[j].getTarget().getType()==this.typePlayer){
						// plus score
						score++;
						setcombo++;
						if(setcombo > SIZECOMBO)
						{
							score++;
						}
						if(setcombo == SIZECOMBO + 1) time_combo = TIME_COMBO_UP;
					}
					else
					{
						if(score > 0)
							score--;
						setcombo = 0;
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
				if((event.x>10)&&(event.x<210)&&(event.y>1050)&&(event.y<1250)) //player1
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
		int len = TouchEvents.size();
		for (int i = 0; i < len; i++) {
		TouchEvent event = TouchEvents.get(i);
		//When TOUCH_DOWN
		if (event.type == TouchEvent.TOUCH_DOWN) {
			if((event.x>10)&&(event.x<210)&&(event.y>1050)&&(event.y<1250)) //player1
			{
				game.setScreen(new loadgamescreen(game));
				break;
			}
			if((event.x>600)&&(event.x<800)&&(event.y>1050)&&(event.y<1250)) //player1
			{
				game.setScreen(new ChoosePlayerScreen(game));
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
			int y = holelist[i].getHole().getY()*120 + changeY;
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
			int y = holelist[i].getHole().getY()*120 + changeY;
			g.drawImage(hole, x, y);
		}
		
		for(i=0;i<NUMBER_TARGET; i++)
		{			
			int x = targetlist[i].getTarget().getX()*80;
			int y = targetlist[i].getTarget().getY()*120 + changeY;
			g.drawImage(targetAnimation[i].getImage(), x, y);
		}

		effectTarget_1.paint(g);
		effectTarget_2.paint(g);
		
		numberDraw.drawNumber(g, intTime, 710, 10);
		g.drawImage(scoreImage, 500, 10);
		numberDraw.drawNumber(g, score, 600, 10);
		
		if((setcombo > SIZECOMBO)&&(time_combo > 0))
		{
			//COMBO
			g.drawImage(comboIMG, 100, 10);
			numberDrawBig.drawNumber(g, setcombo - SIZECOMBO, 500, 10);
		}
		
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
			int y = holelist[i].getHole().getY()*120 + changeY;
			g.drawImage(hole, x, y);
		}
		
		for(i=0;i<NUMBER_TARGET; i++)
		{			
			int x = targetlist[i].getTarget().getX()*80;
			int y = targetlist[i].getTarget().getY()*120 + changeY;
			g.drawImage(targetAnimation[i].getImage(), x, y);
		}


		numberDraw.drawNumber(g, intTime, 710, 10);
		numberDraw.drawNumber(g, score, 600, 10);
		numberDraw.drawNumber(g, score, 600, 10);
		
		
		g.drawImage(pause, 100, 540);
		paint.setTextSize(100);

		g.drawImage(backMenubutton, 10, 1050);   //210>x>10, 1250>y>1050
		paint.setTextSize(50);
	}
	

	/**
	 * 
	 * @author 10-c Pham Thanh Thuong
	 * repaint game
	 */
	public void paintGameOver(Graphics g,float deltaTime)
	{
		g.drawImage(timeUp, 100, 300);
		if(isNewHighScore)
		{
			g.drawImage(newHightScore, 100, 550);
		}
		else
		{
			int hightscore = highScoreManger.getHighscore();
			g.drawImage(highscore, 100, 100);
			numberDrawBig.drawNumber(g, hightscore, 350, 220);
			g.drawImage(yourScore, 100, 550);
		}
		numberDrawBig.drawNumber(g, score, 350, 670);
		g.drawImage(backMenubutton, 10, 1050);   //210>x>10, 1250>y>1050
		g.drawImage(replay, 600, 1050);   //800>x>600, 1250>y>1050
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
