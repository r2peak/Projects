package cn.sqc.runday.model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import cn.sqc.runday.view.GameFrame;

public class Barrs_2{  //  ³èÎï£¡
	private Image image;
	private Image images [] ;
	public static final int WIDTH= 70;
	public static final int HEIGHT = 60;
	private int x,y;
	int index;
	public Barrs_2() {
		init();
		image = images[0];
		x=300;
		y=460;
	}
	public void drop() {
		y ++;
		if(y>=460){
			y = 460;
		}
	}
	public void step(){
		image = images[index++/2%images.length];
	}
	 public void paintBarrs(Graphics g) {
			g.drawImage(image, x,y,WIDTH,HEIGHT, null);
		}
	public boolean outofBounds() {
		return this.x<=-WIDTH;
	}
public void init(){
	images = new Image[6];
	for( int i=0;i<6;i++){
		try {
			images[i]=ImageIO.read(new File ("Image/"+"d"+(i+1)+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
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
public int getIndex() {
	return index;
}
public void setIndex(int index) {
	this.index = index;
}
public static int getWidht() {
	return WIDTH;
}
public static int getHeight() {
	return HEIGHT;
}

}


