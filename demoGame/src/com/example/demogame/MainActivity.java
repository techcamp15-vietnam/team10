package com.example.demogame;

import com.Group10.framework.Screen;
import com.Group10.framework.implementation.AndroidGame;

/**
 * 
 * @author 10-c Pham Thanh Thuong
 * main activity
 */


public class MainActivity extends AndroidGame {

	boolean firstTimeCreate = true;


/**
 * 
 * @author 10-c Pham Thanh Thuong
 * initScreen
 */
	@Override
	public Screen getInitScreen() {

		if (firstTimeCreate) {
			Assets.load(this);
			firstTimeCreate = false;
		}

		return new loadgamescreen(this);

	}


/**
 * 
 * @author 10-c Pham Thanh Thuong
 * when back
 */
	@Override
	public void onBackPressed() {
		getCurrentScreen().backButton();
	}


/**
 * 
 * @author 10-c Pham Thanh Thuong
 * when game resume
 */
	@Override
	public void onResume() {
		super.onResume();
		Assets.theme.play();

	}


/**
 * 
 * @author 10-c Pham Thanh Thuong
 * when game Pause
 */
	@Override
	public void onPause() {
		super.onPause();
		Assets.theme.pause();

	}
	
	
}
