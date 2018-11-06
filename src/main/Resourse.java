package main;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Resourse.Img;

public class Resourse {
	public static class Img{
		public static Image wxz;
		public static Image hotdog;
	}
	public static void init() throws IOException{
		Img.wxz=ImageIO.read(new File("imgs/WXZ.png"));
		Img.hotdog=ImageIO.read(new File("imgs/HotDog.png"));
	}
}
