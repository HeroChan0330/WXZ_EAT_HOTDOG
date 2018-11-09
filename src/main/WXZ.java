package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.text.html.HTMLDocument.Iterator;

public class WXZ extends Rectangle{
	private int mScore=0;
	public WXZ() {
		// TODO Auto-generated constructor stub
		super((Configure.SCREEN_WIDTH-Configure.WXZ_WIDTH)/2,Configure.SCREEN_HEIGHT-Configure.WXZ_HEIGHT,Configure.WXZ_WIDTH,Configure.WXZ_HEIGHT);
//		this.y=Configure.SCREEN_HEIGHT-Configure.WXZ_HEIGHT;
//		this.x=(Configure.SCREEN_WIDTH-Configure.WXZ_WIDTH)/2;
	}
	
	public void draw(Graphics g){
		g.drawImage(Resourse.Img.wxz, x, y, width, height, null);
	}
	
	public void move(double dir){
		x+=(int)(dir*(double)Configure.HORIZONTAL_SPEED*Configure.LOOP_PERIOD);
		if(x<0) x=0;
		if(x+width>Configure.SCREEN_WIDTH) x=Configure.SCREEN_WIDTH-width;
	}
	public int score(){
		return mScore;
	}
	
	public boolean loop(Hotdogs hotdogs){
		boolean ret=false;
		java.util.Iterator<Hotdog> iterator=hotdogs.iterator();
		while (iterator.hasNext()) {
			Hotdog hotdog=iterator.next();
			if(hotdog.intersects(this)&&hotdog.y+hotdog.height<y+Configure.TORRANT_Y_DEL){
				mScore++;
				iterator.remove();
				ret=true;
			}
		}
		return ret;
	}
}
