package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.GlyphVector;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.Timestamp;
import java.sql.Time;
import java.util.Random;
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
	private BGMPlayer bgmPlayer=new BGMPlayer();
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
		bgmPlayer.StartBGM(0);
	}

	public void loop(){
		Random random=new Random();
		hotdogs.loop();
		if(wxz.loop(hotdogs)){
			//打算随机触发彩蛋音效，暂时没想到用什么
//			if(random.nextDouble()<Configure.SO_DELICIOUS_SHOW_UP_RATE){
//				bgmPlayer.PlayEffect(1);
//			}
//			else{
//				bgmPlayer.PlayEffect(0);
//			}
			bgmPlayer.PlayEffect(0);
		}
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
//		bufferGraphics.clearRect(0, 0, Configure.SCREEN_WIDTH, Configure.SCREEN_HEIGHT);
		bufferGraphics.drawImage(Resourse.Img.bgImage, 0, 0, Configure.SCREEN_WIDTH, Configure.SCREEN_HEIGHT,null);
		hotdogs.draw(bufferGraphics);
		wxz.draw(bufferGraphics);
		int timeLeft=Configure.ROUND_TIME-(int)((System.currentTimeMillis()-beginTime)/1000);
		
		bufferGraphics.setColor(Color.black);
		bufferGraphics.setFont(Resourse.timeFont);
		String tStr=String.format("Time:%d",timeLeft);
//		Graphics2D graphics2d=(Graphics2D)bufferGraphics;
//		GlyphVector v = Resourse.timeFont.createGlyphVector(getFontMetrics(Resourse.timeFont).getFontRenderContext(), tStr);
//		Shape shape = v.getOutline();
//		
//		//graphics2d.translate(10,27);
//		graphics2d.setColor(Color.RED);
//		graphics2d.fill(shape);
//		graphics2d.setColor(Color.BLUE.darker().darker());
//		graphics2d.setStroke(new BasicStroke(3));
//		graphics2d.draw(shape);
		drawTime(tStr);
		drawScore(String.format("Socre:%3d",wxz.score()));
//		bufferGraphics.drawString(tStr, 10, 27);
//		bufferGraphics.setColor(Color.red);
//		bufferGraphics.setFont(Resourse.scoreFont);
//		bufferGraphics.drawString(String.format("%3d",wxz.score()), Configure.SCREEN_WIDTH-50, 27);
	}
	
	private void drawTime(String tStr) {
		GlyphVector v = Resourse.timeFont.createGlyphVector(getFontMetrics(Resourse.timeFont).getFontRenderContext(), tStr);
		Shape shape = v.getOutline(10,27);
		Graphics2D gg = (Graphics2D) bufferGraphics;
		gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gg.setColor(Color.WHITE);
		gg.fill(shape);
		gg.setColor(Color.red);
		gg.setStroke(new BasicStroke(1));
		gg.draw(shape);
	}
	private void drawScore(String sStr) {
		GlyphVector v = Resourse.timeFont.createGlyphVector(getFontMetrics(Resourse.timeFont).getFontRenderContext(), sStr);
		Shape shape = v.getOutline(Configure.SCREEN_WIDTH-100,27);
		Graphics2D gg = (Graphics2D) bufferGraphics;
		gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gg.setColor(Color.WHITE);
		gg.fill(shape);
		gg.setColor(Color.blue);
		gg.setStroke(new BasicStroke(1));
		gg.draw(shape);
	}
  
	TimerTask loopTask=new TimerTask() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub

			if(System.currentTimeMillis()-beginTime>Configure.ROUND_TIME*1000){ //结束
//				System.out.println("YOU DIE");
//				System.exit(0);
				bgmPlayer.StartBGM(1);
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
