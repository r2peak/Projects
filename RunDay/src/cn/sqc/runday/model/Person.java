package cn.sqc.runday.model;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import cn.sqc.runday.controller.GamePanel;
import cn.sqc.runday.view.GameFrame;

/**
 * @author Huey
 * @date 2020-11-23
 * 玩家的实体类
 */
public class Person {//1.声明属性
	private Image image;//1.1 玩家当前显示图片
	private Image[] images;//1.2 玩家所有图片
	
	public static final int WIDTH = 120;//1.3玩家宽高
	public static final int HEIGHT = 120;
	
	//1.4玩家初始位置坐标
	private int x,y;
	int index;//下面用作切换图片
	//玩家得分
	private int score;
	//玩家跑酷距离
	private int distance;
	//玩家总分
	private int totalScore;
	
	public Person() {//2.赋值
		//给图片数组images赋值
		init();//2.1	先写，会提示要不要实现！自动生成方法
		//默认当前显示图片位第一张图片 2.6
		image = images[0];
		
		x = 90;//2.7
		y = 580;//脚踩地板
		index = 0;
		score = 0;
		distance = 0;
	}
	//玩家自由下落方法5.1
	public void drop() {
		y += 5;
		if(y>=580){// 下落归下落，也得温柔点，不能让小人儿踩破了地板
			y = 580;
		}
	}
	//玩家移动的方法
	public void step(){
		//玩家图片的切换
		image = images[index ++ /3%images.length];
		//玩家坐标改变（玩家坐标通过键盘控制，此次不做处理）
		//玩家跑酷距离计算
		distance += 2;
	}
	//绘制玩家的方法
	public void paintPerson(Graphics g){
		g.drawImage(image, x, y, WIDTH, HEIGHT, null);
	}

	//判断玩家是否越界的方法
	public boolean outOfBounds(){
		return this.x >= GameFrame.WIDTH || this.x <= -WIDTH;
	}
	private void init() {//2.2
		images = new Image[9];
		for(int i = 0; i<images.length; i++){//2.3
			try {//2.5
				images[i] = ImageIO.read(new File("Image/"+(i+1) + ".png"));//2.4
			} catch (IOException e) {//2.5
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
//2.8  右键，Source，GGAS
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
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getTotalScore() {
		return (int) (score * 10 + distance * 0.6);
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	
	
}
