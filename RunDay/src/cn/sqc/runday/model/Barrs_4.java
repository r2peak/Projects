package cn.sqc.runday.model;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

import cn.sqc.runday.view.GameFrame;

public class Barrs_4 {//		鱼叉障碍物！
	private Image image;
	private Image images[];
	public static final int WIDTH =150;
	public static final int HEIGHT =350;
	private int x,y;
	
	public Barrs_4() {//构造方法
		Random random = new Random();
		images = new Image[4] ;
	try {
		images[0] = ImageIO.read(new File("image/11.png"));
		images[1]= ImageIO.read(new File("image/12.png"));
		images[2]= ImageIO.read(new File("image/13.png"));
		images[3]= ImageIO.read(new File("image/14.png"));
	} catch (Exception e) {
		// TODO: handle exception
	}
		image= images[random.nextInt(4)];
		x=GameFrame.WIDTH+1500;
		y=0;
	}
	public void step(){
		x-=20;
	}
	public void paintBarrs(Graphics g){
		g.drawImage(image, x, y, WIDTH, HEIGHT, null);
	}
	public boolean outofBounds(){
		return this.x<=-WIDTH;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public Image[] getImages() {
		return images;
	}
	public void setImages(Image[] images) {
		this.images = images;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public static int getWidth() {
		return WIDTH;
	}
	public static int getHeight() {
		return HEIGHT;
	}
	
}
	