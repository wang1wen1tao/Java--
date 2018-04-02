package windows;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import util.GlobalVar;
import data.UserDao;
import entity.User;

public class Login extends PublicJFrame {
	/** 成员变量 */
	private JTextField tf_user;// 定义用户名文本框
	private JPasswordField pf_pass;// 定义密码框
	private JButton bt_login;// 定义登录按钮
	private JButton bt_close;// 定义关闭按钮

	/** 构造方法：用于 初始化“登录界面” */
	Login() {
		// 第1步：获取框架内容面板，并设置内容面板的布局为绝对布局
		this.getContentPane().setLayout(null);
		// 第2步：在框架的内容面板上，创建、 设置各个组件
		JLabel lb_user = new JLabel("用户名:");// 创建用户名标签
		// 设置用户名标签的放置位置和放置大小
		lb_user.setBounds(new Rectangle(100, 50, 70, 25));
		add(lb_user);// 将用户名标签添加到内容面板上

		tf_user = new JTextField();// 创建用户名文本框
		tf_user.setBounds(new Rectangle(170, 50, 110, 25));
		add(tf_user);

		JLabel lb_pass = new JLabel("密码：");// 创建密码标签
		lb_pass.setBounds(new Rectangle(100, 90, 50, 25));
		add(lb_pass);

		pf_pass = new JPasswordField();// 创建密码框
		pf_pass.setBounds(new Rectangle(170, 90, 110, 25));
		pf_pass.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {
				if (arg0.getKeyChar() == '\n') {
					bt_login_actionPerformed();// 调用“查询”按钮事件响应方法
				}
			}
		});
		add(pf_pass);

		bt_login = new JButton("登录");// 创建登录按钮
		bt_login.setBounds(new Rectangle(100, 160, 80, 25));
		// 第3步：为按钮添加单击事件监听器
		// 为“登录”按钮添加单击事件监听器
		bt_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 调用“登录”按钮事件响应方法
				bt_login_actionPerformed();
			}
		});
		add(bt_login);

		bt_close = new JButton("关闭");// 创建关闭按钮
		bt_close.setBounds(new Rectangle(200, 160, 80, 25));
		// 为“关闭”按钮添加单击事件监听器
		bt_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 释放“登录界面”占用的屏幕资源，即关闭“登录界面”
				dispose();
			}
		});
		add(bt_close);
		
		// 第4步：设置“登录界面的标题、大小、位置等属性
		setTitle("图书管理系统--登录界面");// 设置“登录界面”标题
		setSize(380, 260);// 设置“登录界面”大小
		setResizable(false);// 设置“登录界面”大小不可改变
		setLocationRelativeTo(null);// 设置“登录界面”放置在屏幕中央
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置“登录界面”默认的关闭操作
	}

	/** 成员方法1： "登录"按钮的事件响应方法 */
	void bt_login_actionPerformed() {
		// 第1步:获得用户名和密码
		String name = tf_user.getText().trim();
		String pass = new String(pf_pass.getPassword()).trim();
		// 第2步：判断用户名或密码是否为空？
		if (name.equals("") || pass.equals("")) {
			// 为空，则显示对话框，给出提示信息，并返回
			JOptionPane.showMessageDialog(this, "用户信息不允许为空！");
			return;
		}
		// 第3步：调用方法，获取登录用户对象
		User user = UserDao.getUser(name, pass);
		// 第4步：判断登录用户对象是否存在？
		if (user != null) {
			// 第5步：存在，则保存当前登录用户对象，创建系统主界面，根据用户权限设置主界面，关闭登录界面
			GlobalVar.login_user = user;// 保存当前登录用户对象
//			System.out.println("进入系统主界面");
			MainFrame main = new MainFrame();// 创建系统主界面
			// 根据用户权限，设置需要显示的系统主界面内容
			main.setPurView((byte) user.getIs_admin());//此方法在系统主界面中定义
			this.dispose();// 释放“登录界面”占用的屏幕资源
		} else {
			// 第6步：不存在， 显示提示对话框，清空用户名和密码
			JOptionPane.showMessageDialog(this, "用户名或密码错误!");
			tf_user.setText("");// 清空用户名文本框
			pf_pass.setText("");// 清空密码框
			return;
		}
	}

	/** 主方法：“图书管理系统”入口 */
	public static void main(String args[]) {
		new Login();// 创建登录界面
	}
}
