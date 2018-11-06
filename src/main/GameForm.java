package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.Timestamp;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GameForm extends JFrame{
	Timer timer=new Timer();
	Image imageBuffer=null;
	Graphics bufferGraphics,formGraphics;
	WXZ wxz=new WXZ();
	Hotdogs hotdogs=new Hotdogs();
	private long beginTime=0;
	private int keyState=0;
	public GameForm() {
		// TODO Auto-generated constructor stub
		this.setTitle("WXZ EAT HOTDOG");
		this.setSize(Configure.SCREEN_WIDTH, Configure.SCREEN_HEIGHT+27);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			Resourse.init();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("fail to init resourses");
			System.exit(1);
		}
//		this.addMouseListener(mouseListener);
		this.addKeyListener(keyListener);
	}
	
	KeyListener keyListener=new KeyListener() {
		
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			keyState=0;
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyCode()==e.VK_LEFT){
				keyState=-1;
			}
			else if(e.getKeyCode()==e.VK_RIGHT){
				keyState=1;
			}
		}
	};
	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		imageBuffer = createImage(Configure.SCREEN_WIDTH, Configure.SCREEN_HEIGHT);//创建图形缓冲区  
		bufferGraphics = imageBuffer.getGraphics();//获取图形缓冲区的图形上下文  
		
		bufferGraphics.setFont(new Font("微软雅黑",Font.BOLD , 20));
		formGraphics=this.getGraphics();
		timer.schedule(loopTask,50,(int)(Configure.LOOP_PERIOD*1000));
		beginTime=System.currentTimeMillis();
	}

	public void loop(){
		hotdogs.loop();
		wxz.loop(hotdogs);
		switch (keyState) {
		case -1:
			wxz.move(-1);
			break;
		case 1:
			wxz.move(1);
			break;
		default:
			break;
		}
	}
	public void draw(){
		bufferGraphics.clearRect(0, 0, Configure.SCREEN_WIDTH, Configure.SCREEN_HEIGHT);
		hotdogs.draw(bufferGraphics);
		wxz.draw(bufferGraphics);
		int timtLeft=Configure.ROUND_TIME-(int)((System.currentTimeMillis()-beginTime)/1000);
		bufferGraphics.setColor(Color.black);
		bufferGraphics.drawString(String.valueOf(timtLeft), 10, 27);
		bufferGraphics.setColor(Color.red);
		bufferGraphics.drawString(String.format("%3d",wxz.score()), Configure.SCREEN_WIDTH-50, 27);
	}
	
	TimerTask loopTask=new TimerTask() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub

			if(System.currentTimeMillis()-beginTime>Configure.ROUND_TIME*1000){ //结束
//				System.out.println("YOU DIE");
//				System.exit(0);
				SwingUtilities.invokeLater(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						GameForm.this.exit();
						AlertWindow alertWindow=new AlertWindow("Game Over", "YOUR SCORE IS:"+wxz.score(), new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								GameForm gameForm=new GameForm();
								gameForm.show();
							}
						});
						alertWindow.show();
					}

				});
			}
			else{
				loop();
				draw();

				SwingUtilities.invokeLater(new Runnable(){
	
					@Override
					public void run() {
						// TODO Auto-generated method stub
						formGraphics.drawImage(imageBuffer, 0, 26, null);
					}
				
				});
			}
		}
	};
	
	public void exit(){
		timer.cancel();
		this.hide();
	}
}
