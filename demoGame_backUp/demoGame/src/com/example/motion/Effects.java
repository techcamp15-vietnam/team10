package com.example.motion;


/**
 * @author 10-c Pham Thanh Thuong
 *  Effect
 */
public class Effects {
	private int x;
	private int y;
	private int numberTime;
	
	/**
	 * @author 10-c Pham Thanh Thuong
	 * @padam: Khoi tao Effect with x, y
	 *  Effect
	 */
	public Effects(int x, int y) {
		this.x = x;
		this.y = y;
		numberTime = 10;
	}
	public void update()
	{
		numberTime--;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public boolean isLife()
	{
		if(numberTime > 0)
			return true;
		else
			return false;
	}
}
