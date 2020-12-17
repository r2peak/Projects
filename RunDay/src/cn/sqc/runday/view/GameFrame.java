package cn.sqc.runday.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import cn.sqc.runday.controller.GamePanel;

/**
 * @author Huey
 *2020-11-27  下午12:40:22
 * 游戏主界面：显示窗体，承载游戏的主面板类
 */

public class GameFrame extends JFrame {
	//设置窗体宽高属性
	public static final int WIDTH=1500;
	public static final int HEIGHT=900;
	public GameFrame() {
		//2.4创建游戏面板对象，并添加到窗体上去
		GamePanel panel = new GamePanel();
		panel.action();//程序启动的方法
		this.addKeyListener(panel);//谁实现就监听谁
		this.add(panel);
		
		/**1.设置窗体基本属性*/
		this.setSize(WIDTH,HEIGHT);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(new ImageIcon("Image/115.png").getImage());
		this.setUndecorated(true);
		this.setVisible(true);	
		
		while(true){
			if(panel.isGameOver){//因为isGameOver之前用static修饰,所以报错
				dispose();//关闭窗口
				}
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			}
	}
	public static void main(String[] args) {
		new GameFrame();
	}
}
