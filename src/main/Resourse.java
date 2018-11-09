package main;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Resourse.Img;

public class Resourse {
	public static class Img{
		public static Image bgImage;
		public static Image wxz;
		public static Image hotdog;
	}
	public static Font scoreFont=null;
	public static Font timeFont=new Font("微软雅黑",Font.BOLD , 20);
//    public static Font getFont(String path,int style) {
//    	Font font=null;
//        InputStream is = null;
//        BufferedInputStream bis = null;
//        try {
//            is = new FileInputStream(new File(path));
//            bis = new BufferedInputStream(is);
//            
//            // createFont返回一个使用指定字体类型和输入数据的新 Font。<br>
//            // 新 Font磅值为 1，样式为 PLAIN,注意 此方法不会关闭 InputStream
//            font = Font.createFont(Font.TRUETYPE_FONT, bis);
//            // 复制此 Font对象并应用新样式，创建一个指定磅值的新 Font对象。
//            font = font.deriveFont(style);
//        } catch (FontFormatException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (null != bis) {
//                    bis.close();
//                }
//                if (null != is) {
//                    is.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        
//        return font;
//    }
	public static Font loadFont(String fontFileName, float fontSize){  //第一个参数是外部字体名，第二个是字体大小
		try{
			File file = new File(fontFileName);
			FileInputStream aixing = new FileInputStream(file);
			Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT, aixing);
			Font dynamicFontPt = dynamicFont.deriveFont(fontSize);
			aixing.close();
			return dynamicFontPt;
		}
		catch(Exception e) {//异常处理
		
			e.printStackTrace();
			return new java.awt.Font("宋体", Font.PLAIN, 14);
		}
	}
    
    
	public static void init() throws IOException{
		Img.wxz=ImageIO.read(new File("imgs/WXZ.png"));
		Img.hotdog=ImageIO.read(new File("imgs/HotDog.png"));
		Img.bgImage=ImageIO.read(new File("imgs/bg.jpg"));
		scoreFont=loadFont("font\\Kreepshow 'Frigid'.ttf",20);
		System.out.println(scoreFont==null);
	}
}
