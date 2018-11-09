package main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public class Hotdogs extends LinkedList<Hotdog>{
	Random random=new Random();
	public Hotdogs(){
		super();
	}
	public void loop(){
		if(random.nextDouble()<Configure.HOTDOG_SHOW_UP_RATE){
			autoGenerateHotdog();
		}
		java.util.Iterator<Hotdog> iterator=this.iterator();
		while (iterator.hasNext()){
			Hotdog hotdog=iterator.next();
			hotdog.loop();
			if(hotdog.y>Configure.SCREEN_HEIGHT){
				iterator.remove();
			}
		}
	}
	public void draw(Graphics g){
		for(Hotdog hotdog:this){
			hotdog.draw(g);
		}
	}
	public void autoGenerateHotdog(){
		Hotdog hotdog=Hotdog.randomHotDog();
		this.add(hotdog);
	}
	
}
