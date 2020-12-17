package cn.sqc.runday.view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * 
 * @author Huey
 * @date 2020-11-16
 * 登录界面：用户名输入框  密码输入框  登录取消按钮 功能
 *
 */
public class LoginFrame extends JFrame{
	//用户名变量（文本）
	JLabel userLabel;
	//用户名输入框（文本输入框）
	JTextField userField;
	//密码变量（文本）
	JLabel userLabel2;
	//密码输入框（文本输入框）
	JPasswordField userField2;
	//登录按钮、取消按钮（按钮）
	JButton Login,Cancel;

	public LoginFrame() {//直接 alt / （无参构造）	
		userLabel = new JLabel("用户名");	
		//设置字体
		userLabel.setFont(new Font("微软雅黑",Font.BOLD,18));				
		userLabel2 = new JLabel("密  码");
		userLabel2.setFont(new Font("微软雅黑",Font.BOLD,18));
			
		//布局方式：绝对布局
		userLabel.setBounds(20, 220, 100, 30);//x位置，y位置，所占显示空间的大小
		this.add(userLabel);//将用户名这三个字添加到登录界面上，以下同理
		userLabel2.setBounds(20, 280, 100, 30);
		this.add(userLabel2);

		//用户名输入框
		userField = new JTextField();
		userField.setBounds(80, 220, 100, 30);
		//设置输入框凹陷效果
		userField.setBorder(BorderFactory.createLoweredBevelBorder());
		//设置输入框背景透明
		userField.setOpaque(false);
		this.add(userField);
		
		userField2 = new JPasswordField();
		userField2.setBounds(80, 280, 100, 30);
		userField2.setBorder(BorderFactory.createLoweredBevelBorder());
		userField2.setOpaque(false);
		this.add(userField2);
		
		
		
//登录按钮
		Login = new JButton("登录");
		Login.setBounds(45,350,60,36);
		//Login.setBackground(new Color(44,22,44));//背景色
		//Login.setForeground(Color.BLUE);//前景色		
		//绑定登录按钮的事件监听
		Login.addActionListener(new ActionListener() {//ActionListener alt /
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("点击登录按钮");
				//获取用户名输入框的内容
				String userName = userField.getText();
				String passWord = userField2.getText();//横杠原因：方法太老了，不推荐用
				if("Huey".equals(userName) && "123".equals(passWord)){
					//登录成功
					JOptionPane.showMessageDialog(null, "欢迎"+userName+"来到天天酷跑游戏");
					//跳转到下一界面
					new MainFrame();
					//关闭当前界面
					dispose();
				}else if("".equals(userName) || "".equals(passWord)){
					//不能为空
					JOptionPane.showMessageDialog(null, "用户名 / 密码不能为空，请重新输入！");
				}else{
					JOptionPane.showMessageDialog(null, "用户名 / 密码输入错误，请重新输入！");
				}
				
			}
		});
		this.add(Login);
		
//取消按钮
		Cancel = new JButton("取消"); 
		Cancel.setBounds(135,350,60,36);
		this.add(Cancel);
		Cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		

		
		//创建背景面板，并添加到窗体上去
		LoginPanel panel = new LoginPanel();
		this.add(panel);	
		
		//设置登录界面的基本属性
		this.setSize(900,530);
		this.setLocationRelativeTo(null);//位置居中
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		
		//设置窗体的Logo图标
		this.setIconImage(new ImageIcon("Image/115.png").getImage());//存储图片
		this.setVisible(true);
	}
	
	
	
	//测试用的main方法       main + Alt /
	public static void main(String[] args) {
		new LoginFrame();
	}
	
	class LoginPanel extends JPanel{//画板
		//背景图片变量
		Image background;//------ctr shift + o 导包
		public LoginPanel() {//-----alt / 回车 构造方法		在{后双击,显示作用域
			//读取图片文件，赋值给background变量
			try {//-----虽然不大可能，但也做好吃饭噎死的准备
				background = ImageIO.read(new File("Image/login.jpg"));//----read参数为File类型
			} catch (IOException e) {//-------捕获异常信息
				// 打印异常日志信息
				e.printStackTrace();
			}
		}
		//绘制方法
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			//绘制背景图片
			g.drawImage(background, 0, 0,900,530, null);//900,530为宽高
		}
	}
}
//throws ......抛异常，将下面的异常向上抛,交给上级：不建议   