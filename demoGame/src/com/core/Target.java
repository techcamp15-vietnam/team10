package com.core;

/**
	target object
	@param target
	@author 10-B Phan Ha
	*/
public class Target {
	private int posX;
	private int posY;
	private int type;
	public void setX (int X)
	{
		posX = X;
	}
	public void setY (int Y)
	{
		posY = Y;
	}
	public void setType (int Type)
	{
		type = Type;
	}
	public int getX ()
	{
		return posX;
	}
	public int getY ()
	{
		return posY;
	}
	public int getType ()
	{
		return type;
	}
}
