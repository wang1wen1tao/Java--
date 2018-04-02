package windows;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import util.GlobalVar;
import data.UserDao;

public class UpdatePass extends PublicJFrame {
	/** 成员变量 */
	private JPasswordField pf_pass1;// 表示新密码密码框
	private JPasswordField pf_pass2;// 表示确认密码密码框
	private JButton bt_ok;// 表示修改密码按钮
	private JButton bt_close;// 表示关闭按钮

	/** 构造方法：用于“修改密码”界面的初始化 */
	UpdatePass() {
		// 第1步：设置“修改密码”界面
		setTitle("--修改密码--");
		setSize(380, 260);
		setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setLayout(null);
		// 第2步：在框架的内容面板上，创建、 设置各个组件
		JLabel lb_pass1 = new JLabel("输入新密码:");
		lb_pass1.setBounds(new Rectangle(100, 50, 70, 25));
		add(lb_pass1);

		pf_pass1 = new JPasswordField();
		pf_pass1.setBounds(new Rectangle(180, 50, 110, 25));
		pf_pass1.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {
				if (arg0.getKeyChar() == '\n') {
					ok_actionPerformed();// 调用“查询”按钮事件响应方法
				}
			}
		});
		add(pf_pass1);

		JLabel lb_pass2 = new JLabel("确认密码：");
		lb_pass2.setBounds(new Rectangle(100, 90, 70, 25));
		add(lb_pass2);

		pf_pass2 = new JPasswordField();
		pf_pass2.setBounds(new Rectangle(180, 90, 110, 25));
		add(pf_pass2);

		bt_ok = new JButton("修改");
		bt_ok.setBounds(new Rectangle(100, 160, 80, 25));
		// 第3步：为按钮组件添加单击事件监听器
		bt_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ok_actionPerformed();// 调用“修改”按钮事件响应方法
			}
		});
		add(bt_ok);

		bt_close = new JButton("关闭");
		bt_close.setBounds(new Rectangle(200, 160, 80, 25));
		//第4步： 为“关闭”按钮添加单击事件监听器
		bt_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();// 关闭“修改密码”界面
			}
		});
		add(bt_close);

	}

	/** 成员方法1： "修改"按钮的事件响应方法 */
	void ok_actionPerformed() {
		// 第1步：获取两次输入的密码
		String pass1 = new String(pf_pass1.getPassword()); // 获取第一次输入密码
		String pass2 = new String(pf_pass2.getPassword()); // 获取第二次输入密码
		// 第2步：判断两次输入的密码是否为空？为空，显示不允许为空消息，并让第1密码框获得焦点，然后返回
		if (pass1.equals("") || pass2.equals("")) {
			JOptionPane.showMessageDialog(this, "用户密码不允许为空！");
			pf_pass1.requestFocus();
			return;
		}
		// 第3步：判断两次输入的密码是否不一致？不一致，显示不一致消息，清空两个密码框，第1密码框获得焦点，然后返回
		if (!pass1.equals(pass2)) {
			JOptionPane.showMessageDialog(this, "输入的密码不一致，请重新输入！");
			pf_pass1.setText("");
			pf_pass2.setText("");
			pf_pass1.requestFocus();
			return;
		}
		// 第4步：判断当前登录的用户对象是否存在？
		if (GlobalVar.login_user != null) {
			//第5步：存在，获取当前登录的用户名，并调用修改密码方法，执行修改密码操作
			String name = GlobalVar.login_user.getName();
			int i = UserDao.updatePass(name, pass1);
			// 第6步：判断修改操作是否成功？成功：显示成功消息，并关闭界面
			if (i == 1) {
				JOptionPane.showMessageDialog(null,
						"密码修改成功! 尝试用新密码登录。");
				dispose();//关闭界面
			}
		} else {
			//第7步：不存在，显示修改密码失败消息
			JOptionPane.showMessageDialog(null, "不是当前用户，密码修改失败!");
		}
	}

	/** 主方法：用于"修改密码"界面测试 */
	public static void main(String args[]) {
		new UpdatePass();
	}
}
