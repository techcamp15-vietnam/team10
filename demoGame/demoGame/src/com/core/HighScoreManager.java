package com.core;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class HighScoreManager{
	private int highScore;
	Activity _context;
	/**
	 check high score
	@param checkHighscore
	@author 10-B Phan Ha
	*/
	public HighScoreManager(Activity acc)
	{
		super();
		this._context = acc;
	}
	
	public Boolean checkHighscore(int score)
	{
		 int oldhighscore = getHighscore();
		 System.out.println("qua lan nay");
		 if(oldhighscore > score){
			 return false;
		 }
		 else {
			setHighscore(score);
			return true;
		}
	}
	/**
	 get high score
	@param getHighscore
	@author 10-B Phan Ha
	*/
	public int getHighscore()
	{
		
		SharedPreferences preferences = this._context.getSharedPreferences("hitdown", Context.MODE_PRIVATE);  
		if(preferences.getInt("score", -1) == -1)
		{
			//Neu la lan dau tien thi tra ve 0. Khong hien thi highscore hoac hien
			System.out.println("lan dau");
			return 0;
		}
		else
		{
			highScore = preferences.getInt("score", -1);
			return highScore;
		}
		
	}
	/**
	 set high score
	@param setHighscore
	@author 10-B Phan Ha
	*/
	private void setHighscore(int score)
	{
		SharedPreferences preferences = this._context.getSharedPreferences("hitdown", Context.MODE_PRIVATE);  
		SharedPreferences.Editor editor = preferences.edit();
		editor.putInt("score", score);
		editor.commit();
	}

}
