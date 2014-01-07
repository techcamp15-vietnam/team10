package com.core;

public class ManagerTarget {
	
	/**
	 get target
	@param getTarget
	@author 10-B Phan Ha
	*/
	private Target target;
	public Target getTarget()
	{
		return target;
	}
	/**
	 set target
	@param setTarget
	@author 10-B Phan Ha
	*/
	public void setTarget(int x, int y, int type)
	{
		target = new Target();
		target.setType(x);
		target.setX(x);
		target.setY(y);
	}
}
