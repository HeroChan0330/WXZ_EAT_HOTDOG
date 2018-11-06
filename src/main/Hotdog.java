package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import javax.annotation.Resource;

public class Hotdog extends Rectangle{
	public Hotdog(int x,int y) {
		// TODO Auto-generated constructor stub
		super(x,y, Configure.HOTDOG_WIDTH, Configure.HOTDOG_HEIGHT);
	}
	public static Hotdog randomHotDog(){
		Random random=new Random();
		int x=random.nextInt(Configure.SCREEN_WIDTH-Configure.WXZ_WIDTH);
		return new Hotdog(x, 0);
	}
	
	public void loop(){
		y+=(int)(Configure.HOTDOG_DROP_SPEED*Configure.LOOP_PERIOD);
	}
	
	public void draw(Graphics g){
		g.drawImage(Resourse.Img.hotdog, x, y, width,height,null);
	}
}
