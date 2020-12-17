package cn.sqc.runday.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import cn.sqc.runday.model.Barrs_1;
import cn.sqc.runday.model.Barrs_2;
import cn.sqc.runday.model.Barrs_3;
import cn.sqc.runday.model.Barrs_4;
import cn.sqc.runday.model.Barrs_5;
import cn.sqc.runday.model.Person;
import cn.sqc.runday.view.EndFrame;
import cn.sqc.runday.view.GameFrame;

/**
 * @author Huey
 *2020-11-27  下午12:28:44
 * 游戏主面板类，核心逻辑类
 *           1、背景图片滚动效果
 *           2、玩家动态效果
 *           3、五种障碍物的出现
 *           4、玩家和障碍物的碰撞逻辑
 *           5、暂停、继续逻辑
 *           6、结束逻辑
 */

public class GamePanel extends JPanel implements KeyListener{
	/**2、生成动态的背景图片***/
	//2.1声明背景图片对象
	Image background;
	Image score;
	Image pause;//暂停
	Image  proceed;//继续.
	
	
	/***3.实现玩家的动态效果和移动功能***/
	//3.1创建玩家对象（类的实例化）
	Person person;
	Barrs_2 barrs_2;//宠物
	Barrs_4 barrs_4;//鱼钩等障碍物
	Barrs_5 barrs_5;//金币
	/**4.实现螃蟹障碍物*/
	//4.1
	Barrs_1[]barrs1 = {};//存储螃蟹数组（没有元素，可以扩容）
	Barrs_3[]barrs3 ={};//导弹
	Barrs_4[]barrs4={};//鱼钩
	Barrs_5[]barrs5 = {};//金币
	
	public GamePanel() {
		//3.2
		person = new Person();//调用Person类的构造方法，创建对象并赋值
		barrs_2 = new Barrs_2();
		//2.2读取图片文件
		try{
			background =ImageIO.read(new File("Image/cc.png"));//跑酷背景
			score =ImageIO.read(new File("Image/a12.png"));//得分背景
			pause = ImageIO.read(new File("Image/b2.png"));
			proceed = ImageIO.read(new File("Image/b1.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	//2.5
	int x=0;//背景图片初始位置
@Override
public void paint(Graphics g) { 
	super.paint(g);
	//2.7
	if(flag){
		x-=20;//图片滚动的速度
	}
		//2.3绘制背景图片(动态切换很流畅)
		g.drawImage(background, x, 0, GameFrame.WIDTH, GameFrame.HEIGHT, null);
		g.drawImage(background, x+GameFrame.WIDTH, 0, GameFrame.WIDTH, GameFrame.HEIGHT,null);
		if(x<=-GameFrame.WIDTH){//实现两张图片之间的切换
			x = 0;
		}
	
	//3.3绘制 玩家
	person.paintPerson(g);
	//绘制螃蟹
	for(int i =0;i<barrs1.length;i++){
		barrs1[i].paintBarrs(g);
	}
	//绘制宠物
	barrs_2.paintBarrs(g);
	//绘制导弹
	for(int i =0;i<barrs3.length;i++){
		barrs3[i].paintBarrs(g);
	}
	//随机绘制鱼钩障碍物
	for(int i =0;i<barrs4.length;i++){
		barrs4[i].paintBarrs(g);
	}
	//随机绘制金币
	for(int i = 0;i<barrs5.length;i++){
		barrs5[i].paintBarrs(g);
	}
	
	
//位置越往下，图层越往上
	//绘制玩家分数
	g.drawImage(score, 120, 50,null);
	g.setColor(Color.ORANGE);
	g.setFont(new Font("宋体",Font.BOLD,30 ));
	g.drawString("玩家得分："+person.getScore()+"分", 133, 95);
	
	//绘制暂停、继续标识图片
	if(flag){
					g.drawImage(proceed, 200, 800, 90,90,null);
	}else{
				g.drawImage(pause, 200, 800, 90, 90, null);
	}
	
}

//生		成	障	碍	物	的	方	法
int index =0;
public void enteredAction(){//实现源源	不	断	生成障碍物的效果
	index++;
	//生成螃蟹障碍物
	if(index%100==0){
		//生成一个螃蟹
		Barrs_1 b1 = new Barrs_1();
		Barrs_3 b3 = new Barrs_3();
		Barrs_4 b4 = new Barrs_4();
		
		barrs1 =Arrays.copyOf(barrs1,barrs1.length+1);//数组扩容
		barrs1[barrs1.length-1]= b1;//放到数组最后一个元素的位置
		//System.out.println("测试"+barrs1.length);		
		barrs3 =Arrays.copyOf(barrs3,barrs3.length+1);
		barrs3[barrs3.length-1]= b3;
		barrs4 =Arrays.copyOf(barrs4,barrs4.length+1);
		barrs4[barrs4.length-1]= b4;
	}
	if(index%15==0){
		Barrs_5 b5 = new Barrs_5();
		barrs5 = Arrays.copyOf(barrs5, barrs5.length +1);
		barrs5[barrs5.length-1] = b5;
	}
}


//移		动	方	法
public void stepAction(){
	//3..4
		person.step();//切换玩家的图片—>动起来
		person.drop();//不断下坠
		barrs_2.drop();
		//螃蟹障碍物移动
	for(int i =0;i<barrs1.length;i++){
		barrs1[i].step();
		//判断当前障碍物是否 越界，并做越界处理
		if(barrs1[i].outofBounds()){
			//删除越界的螃蟹障碍物
			barrs1[i] = barrs1[barrs1.length - 1];//将螃蟹数组最后一个元素，赋给越界的螃蟹，覆盖了，相当于间接删除了。
			barrs1= Arrays.copyOf(barrs1, barrs1.length - 1);//数组缩容
		}
	}
	
	barrs_2.step();
	
	for(int i =0;i<barrs3.length;i++){
			barrs3[i].step();
		//删除越界的导弹障碍物
		if(barrs3[i].outofBounds()){
			barrs3[i] = barrs3[barrs3.length - 1];
			barrs3 = Arrays.copyOf(barrs3, barrs3.length - 1);
		}
	}
	
	for(int i =0;i<barrs4.length;i++){
		barrs4[i].step();
		//删除越界的鱼叉障碍物
		if(barrs4[i].outofBounds()){
		barrs4[i] = barrs4[barrs4.length - 1	];
		barrs4 = Arrays.copyOf(barrs4, barrs4.length - 1);
		}
	}
	for(int i = 0;i<barrs5.length;i++){
		barrs5[i].step();
		if(barrs5[i].outofBounds()){
			//删除越界的金币
			barrs5[i] = barrs5[barrs5.length - 1];
			barrs5 = Arrays.copyOf(barrs5, barrs5.length - 1);
		}
	}
}

//玩家和障碍物碰撞的处理方法
public void pengAction(){
	//判断玩家是否和螃蟹障碍物进行碰撞
	for(int i = 0;i<barrs1.length;i++){//上下左右都写了，下是用不到的
		if(person.getX() + Person.WIDTH >= barrs1[i].getX() &&
		person.getX() <= barrs1[i].getX()	 + Barrs_1.WIDTH  &&
		person .getY() +Person.getHeight() >= barrs1[i].getY() &&
		person.getY()	<= barrs1[i].getY () + Barrs_1.HEIGHT){
			//碰撞后的处理（遮挡类障碍物）
			if(person.getX() + Person.WIDTH <= barrs1[i].getX() + Barrs_1.WIDTH){//防止人在右边，碰撞后可以穿过障碍物
				//左碰撞
				person.setX(barrs1[i].getX()  - Barrs_1.WIDTH);
			}else{
				//右碰撞
				person.setX(barrs1[i].getX()+ Barrs_1.WIDTH	);
			}						
		}
	}
	//判断玩家是否和导弹障碍物进行碰撞
	for(int i = 0;i<barrs3.length;i++){
		if(person.getX() + Person.WIDTH >= barrs3[i].getX() &&
		person.getX() <= barrs3[i].getX()	 + Barrs_3.WIDTH  &&
		person .getY() +Person.getHeight() >= barrs3[i].getY() &&
		person.getY()	<= barrs3[i].getY () + Barrs_3.HEIGHT){
			if(person.getX() + Person.WIDTH <= barrs3[i].getX() + Barrs_3.WIDTH){//玩家的宽度（120px）是比障碍物小的
				//左碰撞
				person.setX(barrs3[i].getX()  - Barrs_3.WIDTH);
			}else{
				//右碰撞
				person.setX(barrs3[i].getX()+ Barrs_3.WIDTH	);
			}	
		}
	}
	//判断玩家是否和鱼叉障碍物进行碰撞
	for(int i = 0;i<=barrs4.length -1;i++){//小心数组越界！
		if(person.getX() + Person.WIDTH >= barrs4[i].getX() &&
		person.getX() <= barrs4[i].getX() + Barrs_4.WIDTH &&
		person.getY() + Person.HEIGHT >= barrs4[i].getY() &&
		person.getY() <= barrs4[i].getY() + Barrs_4.HEIGHT	){
			if(person.getX() + Person.WIDTH <= barrs4[i].getX() + Barrs_4.WIDTH	){
				//左碰撞
				person.setX(barrs4[i].getX() - Barrs_4.WIDTH);
			}else{
				//右碰撞
				person.setX(barrs4[i].getX()+ Barrs_4.WIDTH	);
			}	
		}
	}
	//玩家和金币的碰撞
	for(int i = 0;i<barrs5.length;i++){
		if(person.getX() + Person.WIDTH >= barrs5[i].getX() &&
		person.getX() <= barrs5[i].getX()	 + Barrs_5.WIDTH  &&
		person .getY() +Person.getHeight() >= barrs5[i].getY() &&
		person.getY()	<= barrs5[i].getY () + Barrs_5.HEIGHT){//判断玩家与金币的碰撞
			if(person.getX() + Person.WIDTH <= barrs5[i].getX() + Barrs_5.WIDTH){
				//删除当前金币
				barrs5[i]	= barrs5[barrs5.length - 1];
				barrs5 = Arrays.copyOf(barrs5, barrs5.length - 1);	
				//玩家加分
				int score = person.getScore();
				person.setScore(score + 10);
		   }
		}
	}
		
}
//结束逻辑
	public  void gameOverAction(){
		if(person.outOfBounds()){
			//程序结束
			isGameOver = true;
			//传递数据（创建结束界面）
			new EndFrame(person);//面向对象思想
			//数据清空
			person = new Person();
			barrs1 = new Barrs_1[]{};
			barrs3 = new Barrs_3[]{};
		}	
	}
	
	public boolean isGameOver = false;
	boolean flag = true; 
//2.8	创	建	一	个	程	序	启	动	的 	方	法
public void action(){
	new Thread(){//匿名内部类
		//重写run方法
		public void run() {
			while(!isGameOver){
				//3.4
				if(flag){
						enteredAction();//细节：只有先生成了障碍物后，下面才能调用移动障碍物的方法
						stepAction();
						pengAction();//玩家和障碍物碰撞
						gameOverAction();
					
				}
				//重绘方法
				repaint();
				//线程休眠
				try {
					Thread.sleep(60);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			
		};
	}.start();//创建一个线程并启动

	}

@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyPressed(KeyEvent e) {
	//获取玩家当前位置坐标
	 int x = person.getX();
	 int y = person.getY();
	 int x1 = barrs_2.getX();
	 int y1 = barrs_2.getY();

	//上
		if(e.getKeyCode() == KeyEvent.VK_UP	&&		y > 10	&&		y1 > 10){
			person.setY(y-25);
			barrs_2.setY(y-25);
		}
			//下
		if(e.getKeyCode()== KeyEvent.VK_DOWN	&&		y<=560		&&		y1<560){
			person.setY(y+30);
			barrs_2.setY(y-30);
		}
		//左
		if(e.getKeyCode()==KeyEvent.VK_LEFT		&& 	x>=0	){
			person.setX(x-30);
			barrs_2.setX(x1-30);
			
		}
		//右
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			person.setX(x+22);
			barrs_2.setX(x1+22);
			if(x>=GameFrame.WIDTH-Person.WIDTH){//如果人物到了右边界
				person.setX(GameFrame.WIDTH-Person.WIDTH);
			}
			if(x1>=GameFrame.WIDTH-barrs_2.WIDTH){//如果宠物到了右边界
				barrs_2.setX(GameFrame.WIDTH - barrs_2.WIDTH);
			}
		}
		//暂停 继续功能
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
				flag = !flag;
		}
		
	}

@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
}
}