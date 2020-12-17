package cn.sqc.runday.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class MainFrame extends JFrame implements MouseListener {
	//设置窗体的基本属性	大小
	/**
	 *  1.1、设置窗体基本属性大小 居中 边框隐藏 默认关闭按钮 logo图标
		1.2、创建背景面板MainPanel，实现背景图片功能
		
		2.图片按钮功能
	 */
	//2.1创建开始按钮 帮助按钮 离开按钮 组件
	JLabel start,help,exit;
	
	JPanel MainPanel;
	
	public MainFrame() {//无参构造，创建对象。并在main函数中调用
		//2.2
		start = new JLabel(new ImageIcon("Image/hh1.png"));//ImageIcon:图标
		start.setBounds(350,320,150,40);
		start.setEnabled(false);//false按钮为灰色		
		start.addMouseListener(this);
		this.add(start);
		
		help = new JLabel(new ImageIcon("Image/hh2.png"));
		help.setBounds(350,420,150,40);
		help.setEnabled(false);
		help.addMouseListener(this);
		this.add(help);
		
		exit = new JLabel(new ImageIcon("Image/hh3.png"));
		exit.setBounds(350, 520, 150, 40);
		exit.setEnabled(false);
		exit.addMouseListener(this);
		this.add(exit);
			
		
		/**1.实现背景图片及窗体属性*/
		MainPanel panel = new MainPanel();
		this.add(panel);
		
		//设置窗体基本属性大小 居中 边框隐藏 默认关闭按钮 logo图标
		this.setSize(1200,730);//大小
		this.setLocationRelativeTo(null);//居中
		this.setUndecorated(true);//边框隐藏
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//默认关闭
		this.setIconImage(new ImageIcon("Image/115.png").getImage());//logo
		this.setVisible(true);			
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
	
	//2、创建背景面板MainPanel，实现背景图片功能
	class MainPanel extends JPanel{//创建的MainPanel类，在MainFrame中调用
	Image background;		
	public MainPanel() {
		try {
			background = ImageIO.read(new File("Image/main.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(background, 0, 0,1200,730, null);
		}
	}
	
	

//以下五个方法均为添加 implements MouseListener 后，快捷出来的
	@Override
	public void mouseClicked(MouseEvent e) {
		//鼠标点击
		if(e.getSource().equals(start)){
			//跳转到下一界面
			new WindowFrame().Start();
			//关闭当前界面
				//dispose();
		}else if(e.getSource().equals(exit)){
			dispose();
		}else if(e.getSource().equals(help)){
			JOptionPane.showMessageDialog(null, "有疑问请联系开发者：Huey");
		}
		
	}




	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mouseEntered(MouseEvent e) {
		// 鼠标移入
		if(e.getSource().equals(start)){//e指一个事件。e.getSource()获取事件
			//如果鼠标移入到（start）组件（图片按钮）
			start.setEnabled(true);
		}else if(e.getSource().equals(help)){
			help.setEnabled(true);
		}else if(e.getSource().equals(exit)){
			exit.setEnabled(true);
		}
	}




	@Override
	public void mouseExited(MouseEvent e) {
		//鼠标移出
			if(e.getSource().equals(start)){
				start.setEnabled(false);
		}else if(e.getSource().equals(help)){
			help.setEnabled(false);
		}else if(e.getSource().equals(exit)){
			exit.setEnabled(false);
		}
	}
}


