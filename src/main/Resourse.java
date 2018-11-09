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
	public static Font timeFont=new Font("΢���ź�",Font.BOLD , 20);
//    public static Font getFont(String path,int style) {
//    	Font font=null;
//        InputStream is = null;
//        BufferedInputStream bis = null;
//        try {
//            is = new FileInputStream(new File(path));
//            bis = new BufferedInputStream(is);
//            
//            // createFont����һ��ʹ��ָ���������ͺ��������ݵ��� Font��<br>
//            // �� Font��ֵΪ 1����ʽΪ PLAIN,ע�� �˷�������ر� InputStream
//            font = Font.createFont(Font.TRUETYPE_FONT, bis);
//            // ���ƴ� Font����Ӧ������ʽ������һ��ָ����ֵ���� Font����
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
	public static Font loadFont(String fontFileName, float fontSize){  //��һ���������ⲿ���������ڶ����������С
		try{
			File file = new File(fontFileName);
			FileInputStream aixing = new FileInputStream(file);
			Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT, aixing);
			Font dynamicFontPt = dynamicFont.deriveFont(fontSize);
			aixing.close();
			return dynamicFontPt;
		}
		catch(Exception e) {//�쳣����
		
			e.printStackTrace();
			return new java.awt.Font("����", Font.PLAIN, 14);
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
