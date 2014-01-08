package com.core;

public class ManagerHoles {
	private Hole hole;
	public Hole getHole()
	{
		return hole;
	}
	/**
	 set target
	@param setTarget
	@author 10-B Phan Ha
	*/
	public void setHole(int x, int y)
	{
		hole = new Hole();
		hole.setX(x);
		hole.setY(y);
	}
}
