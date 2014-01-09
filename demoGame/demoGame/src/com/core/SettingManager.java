package com.core;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
/**
setting manager
@param SettingManager
@author 10-B Phan Ha
*/

public class SettingManager {
	Activity _context;	

	public SettingManager(Activity acc)
	{
		super();
		this._context = acc;
	}
	
	
	public void setSetting(Boolean sound, int time, Boolean music)
	{
		SharedPreferences preferences = this._context.getSharedPreferences("hitdown", Context.MODE_PRIVATE);  
		SharedPreferences.Editor editor = preferences.edit();
		editor.putBoolean("sound", sound);
		editor.putBoolean("music", music);
		editor.putInt("time", time);
		editor.commit();
	}
	public Boolean getsound()
	{
		SharedPreferences preferences = this._context.getSharedPreferences("hitdown", Context.MODE_PRIVATE);  
		return preferences.getBoolean("sound", true);
	}
	public Boolean getmusic()
	{
		SharedPreferences preferences = this._context.getSharedPreferences("hitdown", Context.MODE_PRIVATE);  
		return preferences.getBoolean("music", true);
	}
	public int gettimeplay()
	{
		SharedPreferences preferences = this._context.getSharedPreferences("hitdown", Context.MODE_PRIVATE);  
		return preferences.getInt("time", 30);
	}

}
