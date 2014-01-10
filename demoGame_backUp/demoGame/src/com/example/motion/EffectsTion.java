package com.example.motion;

import java.util.ArrayList;

import com.Group10.framework.Graphics;
import com.Group10.framework.Image;

/**
 * @author 10-c Pham Thanh Thuong
 * init AnimFrame
 */
public class EffectsTion {
	private ArrayList effectsTion;
	private Image img;
	
	/**
	 * @author 10-c Pham Thanh Thuong
	 * @padam: create Effectstion with image
	 *  Effect
	 */
	public EffectsTion(Image img) {
		effectsTion = new ArrayList();
		this.img = img;
	}
	
	/**
	 * @author 10-c Pham Thanh Thuong
	 * @padam: create Effects with image
	 *  Effect
	 */
	public void addEffect(int x, int y)
	{
		effectsTion.add(new Effects(x, y));
	}
	
	/**
	 * @author 10-c Pham Thanh Thuong
	 * update() Effecttion
	 */
	public void update(){
		if(effectsTion.size()<=0) return;
		else{
			for(int i = 0; i< effectsTion.size(); i++)
			{
				Effects temp = (Effects)effectsTion.get(i);
				temp.update();
				if(temp.isLife()) return;
				else{
					effectsTion.remove(i);
					i--;
				}
			}
		}
	}
	

	/**
	 * @author 10-c Pham Thanh Thuong
	 * paint screen
	 */
	public void paint(Graphics g){
		if(effectsTion.size()<=0) return;
		else{
			for(int i = 0; i< effectsTion.size(); i++)
			{
				Effects temp = (Effects)effectsTion.get(i);
				g.drawImage(img, temp.getX(), temp.getY());
			}
		}
	}
	

	/**
	 * @author 10-c Pham Thanh Thuong
	 * paint screen and flip image
	 */
	public void paintFlip(Graphics g){
		if(effectsTion.size()<=0) return;
		else{
			for(int i = 0; i< effectsTion.size(); i++)
			{
				Effects temp = (Effects)effectsTion.get(i);
				g.drawFlipImage(img, temp.getX(), temp.getY());
			}
		}
	}
}
