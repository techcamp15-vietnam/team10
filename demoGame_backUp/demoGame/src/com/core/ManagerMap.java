package com.core;

import java.util.ArrayList;
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
		int[] holex = new int[numholes];
		//System.out.println("so holes la : " + numholes);
		holex = randomm(100, null, numholes);
	//	System.out.println("end holex: ");
		//holey = randomm(8, null, numholes);
		for (int i=0; i<numHoles; i++)
		{
			//System.out.println("toa do la : "+ 100/10 +" "+ 100%10 + holex[i]);
			holeslist[i] = new ManagerHoles();
		//	System.out.println("toa do la : "+ holex[i]/10);
			holeslist[i].setHole(holex[i]/10, holex[i]%10);
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
		int[] targetx = new int[numtarget];
		targetx = randomm(numHoles, null, numtarget);
		for (int i=0; i<numTarget/2; i++)
		{
			//int pos = customRandom(numHoles, 3 );
			Hole h = holeslist[targetx[i]].getHole();
			targetlist[i] = new ManagerTarget();
			targetlist[i].setTarget(h.getX(), h.getY(), 0);
			//System.out.print(i +"   " + targetlist[i].getTarget().getType() + "\n");
		}
		for (int j = numTarget/2; j < numTarget; j++) {
			//int pos = customRandom(numHoles, 3 );
			Hole h = holeslist[targetx[j]].getHole();			
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
		ArrayList<Integer> list = new ArrayList<Integer>(numTarget);
		//khoi tao random postion. khong bi trung voi cac vi tri target khac.
		// lay duoc mang vi tri cac target
		for (int j = 0; j < numTarget; j++) {
			int holepos = targetlist[j].getTarget().getX() *10 + targetlist[j].getTarget().getY();
			//System.out.println("---- " + targetlist[j].getTarget().getX() +targetlist[j].getTarget().getY());
			list.add(holepos);
			//System.out.println("++++" + list.get(j));
		}
		
		int[] pos = randomm(numHoles, list, 1);
		//System.out.println(pos[0]);
		//Hole h = holeslist[pos[0]].getHole();
		Target temptg = targetlist[i].getTarget();
		//targetlist[i].setTarget(h.getX()pos, h.getY(), temptg.getType());
		targetlist[i].setTarget(pos[0]/10, pos[0]%10, temptg.getType());
	}
	/**
	 random without ducplicate
	@param randomm
	@author 10-B Phan Ha
	*/
	public int[] randomm(int max,ArrayList<Integer> intArray, int num){
		Random ran = new Random();
		int[] result = new int[num];
		ArrayList<Integer> list = new ArrayList<Integer>(numHoles);
	    //System.out.println("*" + max);

		 
	    if(intArray != null)
	    {
	    	for(int i = 0; i < numHoles; i++) {
				 int holepos = holeslist[i].getHole().getX() *10 + holeslist[i].getHole().getY();
			        list.add(holepos);
			    }
	    //	System.out.println("size "+intArray.size()+list.size());
	    	 for (Integer number : intArray) {
	    		// int l =0;
	    	     // System.out.println("Number = " + number);
	        	for(int j = 0; j < max; j++) {
	                if(number == list.get(j))
	                {	                	
	                	System.out.println("sss" + j+"--"+list.remove(j) +"-----"+ number);
	                	max = max -1;
	                	//l++;
	                	break;
	                }
	            }
	        	//if(l==0)System.out.println("ccc" + number);
	    	}
	    //	System.out.println("max--" +max);
	    //	System.out.println("size "+list.size());
	    for(int i = 0; i < num; i++) {
	    	int x = ran.nextInt(max);
	    	result[i] = list.remove(x);
	    	// System.out.println(result[i]+"------------------------------");
	    	max = max -1;
	    	
	    }
	    
		   // System.out.println("***" + max);
	    }    
	    else 
	    {
	    	for(int i = 0; i < max; i++) {
	    		list.add(i);
	    	}
	    //	System.out.println( "log doan nay");
	    	for (int i = 0; i < num; i++) {
	    		 int x = ran.nextInt(max);
	    		result[i] = list.remove(x);
	    		max = max -1;
			}
	    }
	    System.out.println("end random");
	    return result;
	}
	public ManagerTarget gettaget(int  i)
	{
		return targetlist[i];
	}
}

