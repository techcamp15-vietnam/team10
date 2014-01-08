package com.core;

import java.util.Random;



public class ManagerMap {

	private ManagerTarget[]  targetlist;
	private ManagerHoles[] holeslist;
	private int numTarget;
	private int numHoles;
	
	
	/**
	 init hole
	@param initwithholes
	@author 10-B Phan Ha
	*/
	public void initwithholes(int numholes)
	{
		numHoles = numholes;
		holeslist = new ManagerHoles[numHoles];
		for (int i=0; i<numHoles; i++)
		{
			holeslist[i] = new ManagerHoles();
			holeslist[i].setHole(customRandom(10,0), customRandom(10,1));
		}
	}
	/**
	 init target
	@param initwithtarget 
	@author 10-B Phan Ha
	*/
	public void initwithtarget(int numtarget)
	{
		numTarget = numtarget;
		targetlist = new ManagerTarget[numTarget];
		
		for (int i=0; i<numTarget/2; i++)
		{
			int pos = customRandom(numHoles, 3 );
			Hole h = holeslist[pos].getHole();
			targetlist[i] = new ManagerTarget();
			targetlist[i].setTarget(h.getX(), h.getY(), 0);
			//System.out.print(i +"   " + targetlist[i].getTarget().getType() + "\n");
		}
		for (int j = numTarget/2; j < numTarget; j++) {
			int pos = customRandom(numHoles, 3 );
			Hole h = holeslist[pos].getHole();			
			targetlist[j] = new ManagerTarget();
			targetlist[j].setTarget(h.getX(), h.getY(), 1);
			//System.out.print(j +"   " + targetlist[j].getTarget().getType() + "\n");
		}
	}
	/**
	 get target list
	@param gettargetlist
	@author 10-B Phan Ha
	*/
	public ManagerTarget[] gettargetlist()
	{
		/*
		for(int i = 0; i<10; i++)
			System.out.print(i +"   " + targetlist[i].getTarget().getType() + "   "+ targetlist[i].getTarget().getX() + "   " + targetlist[i].getTarget().getY()+ "\n");
		*/
		return targetlist;
	}
	/**
	 get hole list
	@param getHolelist
	@author 10-B Phan Ha
	*/
	public ManagerHoles[] getHolelist()
	{
		return holeslist;
	}
	/**
	 check target
	@param checkTarget
	@author 10-B Phan Ha
	*/
	/*
	public Boolean checkTarget(int x, int y, int type)
	{
		for (int i = 0; i < numTarget; i++) {
			Target temptg = targetlist[i].getTarget();
			if (temptg.getX() == x && temptg.getY() == y) {
				 if (temptg.getType() == type)
					 return true;
			}
		}
		return false;
	}
	*/
	public  int checkTarget(int x, int y)
	{
		for (int i = 0; i < numTarget; i++) {
			Target temptg = targetlist[i].getTarget();
			if (temptg.getX() == x && temptg.getY() == y) {
				return i;
			}
		}
		return -1;
	}
	/**
	 change pos of target
	@param changePos
	@author 10-B Phan Ha
	*/
	public void changePos(int i)
	{
		int pos = customRandom(numHoles, 3);
		Hole h = holeslist[pos].getHole();
		Target temptg = targetlist[i].getTarget();
		targetlist[i].setTarget(h.getX(), h.getY(), temptg.getType());
	}
	
	private int customRandom(int range, int t)
	{
		Random rand = new Random();
		int ran = rand.nextInt(range);
		int[]  a = new int[range]; 
		if (t == 3) {
			for (int i = 0; i < numHoles; i++) {
				Hole temptg = holeslist[i].getHole();
					a[i] = temptg.getX();
			}
			for (int i = 0; i < numHoles; i++) {
				if(ran == a[i])
				{
					ran = rand.nextInt(range);
				}
			}
		}
		else
		{
			for (int i = 0; i < numTarget; i++) {
				Target temptg = targetlist[i].getTarget();
				if(t == 0)
				{
					a[i] = temptg.getX();
				}
				else 
				{
					a[i] = temptg.getY();
				}
			}
			for (int i = 0; i < numTarget; i++) {
				if(ran == a[i])
				{
					ran = rand.nextInt(range);
				}
			}
		}
		return ran;
	}
	/**
	 add target
	@param addTarget
	@author 10-B Phan Ha
	*/
	private void addTargetatPos(int x, int y, int type, int pos) 
	{
		targetlist[pos].setTarget(x, y, type);
	}
	/**
	 remove target
	@param removeTaget
	@author 10-B Phan Ha
	*/
	public void removeTagetatPos(int x, int y, int pos)
	{
		
	}
	/**
	 check map clean
	@param checkClean
	@author 10-B Phan Ha
	*/
	public Boolean checkClean()
	{
		return true;
	}
}