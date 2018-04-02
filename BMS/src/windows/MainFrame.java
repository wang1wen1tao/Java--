package windows;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import data.BaseDao;

public class MainFrame extends PublicJFrame {
	/** 成员变量 */
	private JMenuItem mi_user_upkeep;// 声明用户维护菜单项
	private JMenuItem mi_reader_upkeep;// 声明读者维护菜单项
	private JMenuItem mi_book_upkeep;// 声明图书维护菜单项
	private JMenuItem mi_borrow;// 声明借书菜单项
	private JMenuItem mi_back;// 声明还书菜单项
	private JMenuItem mi_reader_query;// 声明读者查询菜单项
	private JMenuItem mi_book_query;// 声明图书查询菜单项
	private JMenuItem mi_update_pass;// 声明修改密码菜单项
	private JMenuItem mi_book_statistics;// 声明图书统计菜单项
	private JMenuItem mi_exit;// 声明退出系统菜单项

	private JButton bt_reader_upkeep;// 声明读者维护按钮
	private JButton bt_book_upkeep;// 声明图书维护按钮
	private JButton bt_borrow;// 声明借书按钮
	private JButton bt_back;// 声明还书按钮
	private JButton bt_reader_query;// 声明读者查询按钮
	private JButton bt_book_query;// 声明图书查询按钮
	private JButton bt_book_statistics;// 声明图书统计按钮
	private JButton bt_exit;// 声明退出系统按钮

	/** 构造方法：系统主界面初始化方法 */
	public MainFrame() {
		//第1步：系统主界面设置
		this.setTitle("图书管理系统");// 设置主界面标题
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);// 设置主界面窗口最大化
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//第2步：创建、设置菜单栏。先调用创建系统菜单栏方法，创建系统菜单栏,再在主界面设置菜单栏
		this.setJMenuBar(createMenuBar());
		//第3步： 创建、设置工具栏。先调用创建系统工具栏方法，创建系统工具栏，再将工具栏添加到界面的上方
		this.add(createToolBar(), BorderLayout.NORTH); 
	}

	/** 成员方法1：创建系统菜单栏方法 */
	private JMenuBar createMenuBar() {
		// 第1步：创建菜单栏对象
		JMenuBar menuBar = new JMenuBar();
		//第2步：创建菜单。如基础维护菜单，又包含用户维护、读者维护、图书维护菜单项
		JMenu menu_upkeep = new JMenu("基础维护"); // 创建"基础维护"菜单
		//第3步：创建菜单项，并为菜单项设置单击事件监听器
		mi_user_upkeep = new JMenuItem("用户维护"); // 创建“用户维护”菜单项
		mi_user_upkeep.addActionListener(new ActionListener() {//添加单击事件监听器
			public void actionPerformed(ActionEvent e) {
				user_upkeep_actionPerformed();// 调用“用户维护”响应方法
			}
		});
		//第4步：将菜单项添加到菜单上
		menu_upkeep.add(mi_user_upkeep);// 将“用户维护”菜单项添加到“基础维护”菜单
		
		mi_reader_upkeep = new JMenuItem("读者维护"); // 创建“读者维护”菜单项
		mi_reader_upkeep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reader_upkeep_actionPerformed();// 调用“读者维护”响应方法
			}
		});
		menu_upkeep.add(mi_reader_upkeep);// 将“读者维护”菜单项添加到“基础维护”菜单

		mi_book_upkeep = new JMenuItem("图书维护"); // 创建"图书维护"菜单项
		mi_book_upkeep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				book_upkeep_actionPerformed();// 调用"图书维护"响应方法
			}
		});
		menu_upkeep.add(mi_book_upkeep);
		//第5步：将菜单添加到菜单栏上
		menuBar.add(menu_upkeep);// 将"基础维护"菜单添加到系统菜单栏

		JMenu menu_borrow = new JMenu("借阅管理"); // 创建"借阅管理"菜单
		mi_borrow = new JMenuItem("借书"); // 创建"借书"菜单项
		mi_borrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrow_actionPerformed();// 调用"借书"响应方法
			}
		});
		menu_borrow.add(mi_borrow);

		mi_back = new JMenuItem("还书"); // 创建"还书"菜单项
		mi_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back_actionPerformed();// 调用"还书"响应方法
			}
		});
		menu_borrow.add(mi_back);
		menuBar.add(menu_borrow);// 将"借阅管理"菜单添加到系统菜单栏

		JMenu menu_query = new JMenu("查询统计"); // 创建"查询统计"菜单
		mi_reader_query = new JMenuItem("读者查询"); // 创建"读者查询"菜单项
		mi_reader_query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reader_query_actionPerformed();// 调用"读者查询"响应方法
			}
		});
		menu_query.add(mi_reader_query);

		mi_book_query = new JMenuItem("图书查询"); // 创建"图书查询"菜单项
		mi_book_query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				book_query_actionPerformed();// 调用"图书查询"响应方法
			}
		});
		menu_query.add(mi_book_query);

		mi_book_statistics = new JMenuItem("图书统计"); // 创建" 图书统计"菜单项
		mi_book_statistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				book_statistics_actionPerformed();// 调用"图书统计"响应方法
			}
		});
		menu_query.add(mi_book_statistics);
		menuBar.add(menu_query);

		JMenu menu_management = new JMenu("系统管理");// 创建"系统管理"菜单
		mi_update_pass = new JMenuItem("修改密码"); // 创建"修改密码"菜单项
		mi_update_pass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update_pass_actionPerformed();// 调用"修改密码"响应方法
			}
		});
		menu_management.add(mi_update_pass);

		mi_exit = new JMenuItem("退出系统"); // 创建"退出系统"菜单项
		mi_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit_actionPerformed();// 调用"退出系统"响应方法
			}
		});
		menu_management.add(mi_exit);
		menuBar.add(menu_management);
		//第6步：返回菜单栏。
		return menuBar;
	}

	/** 成员方法2： 创建工具栏方法 */
	private JToolBar createToolBar() {
		//第1步：创建、设置工具栏
		JToolBar toolBar = new JToolBar("图书管理系统工具栏");// 创建工具栏对象
		toolBar.setFloatable(false); // 设置工具栏不可浮动
			// 设置工具栏边框导角方式
		toolBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
		//第2步： 创建、设置工具栏上的快捷按钮
		bt_reader_upkeep = new JButton("读者维护");// 创建"读者维护"按钮
			// 创建"读者维护"图标	
		ImageIcon icon_reader_upkeep = new ImageIcon("images/reader_upkeep.png");
		bt_reader_upkeep.setIcon(icon_reader_upkeep);// 为"读者维护"按钮设置图标
		bt_reader_upkeep.setToolTipText("读者维护");// 为"读者维护"按钮设置提示
			// 将"读者维护"按钮注册单击事件监听器
		bt_reader_upkeep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reader_upkeep_actionPerformed();// 调用"读者维护’响应方法
			}
		});
		//第3步：将按钮添加到工具栏上
		toolBar.add(bt_reader_upkeep);// 将“读者维护”按钮添加到工具栏
		
		bt_book_upkeep = new JButton("图书维护");
		ImageIcon icon_book_upkeep = new ImageIcon("images/book_upkeep.png");
		bt_book_upkeep.setIcon(icon_book_upkeep);
		toolBar.add(bt_book_upkeep);
		bt_book_upkeep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				book_upkeep_actionPerformed();// 调用“图书维护”响应方法
			}
		});

		bt_borrow = new JButton("借书");
		ImageIcon icon_borrow = new ImageIcon("images/borrow.png");
		bt_borrow.setIcon(icon_borrow);
		toolBar.add(bt_borrow);
		bt_borrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrow_actionPerformed();// 调用“借书”响应方法
			}
		});

		bt_back = new JButton("还书");
		ImageIcon icon_back = new ImageIcon("images/back.png");
		bt_back.setIcon(icon_back);
		toolBar.add(bt_back);
		bt_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back_actionPerformed();// 调用“还书”响应方法
			}
		});

		bt_reader_query = new JButton("读者查询");
		ImageIcon icon_reader_query = new ImageIcon("images/reader_query.png");
		bt_reader_query.setIcon(icon_reader_query);
		toolBar.add(bt_reader_query);
		bt_reader_query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reader_query_actionPerformed();// 调用“读者查询”响应方法
			}
		});

		bt_book_query = new JButton("图书查询");
		ImageIcon icon_book_query = new ImageIcon("images/book_query.png");
		bt_book_query.setIcon(icon_book_query);
		toolBar.add(bt_book_query);
		bt_book_query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				book_query_actionPerformed();// 调用“图书查询”响应方法
			}
		});

		bt_book_statistics = new JButton("图书统计");
		ImageIcon icon_book_statistics = new ImageIcon("images/book_statistics.png");
		bt_book_statistics.setIcon(icon_book_statistics);
		toolBar.add(bt_book_statistics);
		bt_book_statistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				book_statistics_actionPerformed();// 调用“图书统计”响应方法
			}
		});

		bt_exit = new JButton("退出系统");
		ImageIcon icon_exit = new ImageIcon("images/exit.png");
		bt_exit.setIcon(icon_exit);
		toolBar.add(bt_exit);
		bt_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit_actionPerformed();// 调用“退出系统"响应方法
			}
		});
		//第4步：返回工具栏
		return toolBar;
	}

	/** 成员方法3：设置系统功能使用权限的方法 */
	void setPurView(byte purView) {
		switch (purView) {
		case 0://用户权限为0，为操作员
			mi_user_upkeep.setEnabled(false);//设置“用户维护”菜单项不可用
			mi_book_statistics.setEnabled(false);
			bt_book_statistics.setEnabled(false);//设置“图书统计”按钮不可用
			break;
		case 1://用户权限为1，为管理员
			break;
		default://用户权限为其他，为一般访客
			mi_user_upkeep.setEnabled(false);
			mi_reader_upkeep.setEnabled(false);
			mi_book_upkeep.setEnabled(false);
			mi_borrow.setEnabled(false);
			mi_back.setEnabled(false);
			mi_book_statistics.setEnabled(false);
			mi_update_pass.setEnabled(false);

			bt_book_statistics.setEnabled(false);
			bt_reader_upkeep.setEnabled(false);
			bt_book_upkeep.setEnabled(false);
			bt_borrow.setEnabled(false);
			bt_back.setEnabled(false);
		}
	}

	/** 成员方法4：用户维护响应方法 */
	private void user_upkeep_actionPerformed() {
		new UserUpkeep();// 创建“用户维护”界面
	}

	/** 成员方法5：读者维护响应方法 */
	private void reader_upkeep_actionPerformed() {
		new ReaderUpkeep();// 创建“读者维护”界面
	}

	/** 成员方法6：图书维护响应方法 */
	private void book_upkeep_actionPerformed() {
		new BookUpkeep();// 创建“图书维护”界面
	}

	/** 成员方法7：借书响应方法 */
	private void borrow_actionPerformed() {
		// new Borrow();// 创建“借书”界面
	}

	/** 成员方法8：还书响应方法 */
	private void back_actionPerformed() {
		// new Back();// 创建“还书”界面
	}

	/** 成员方法9：读者查询响应方法 */
	private void reader_query_actionPerformed() {
		new ReaderQuery();// 创建“读者查询”界面
	}

	/** 成员方法10：图书查询响应方法 */
	private void book_query_actionPerformed() {
		new BookQuery();// 创建“图书查询”界面
	}

	/** 成员方法12：图书统计响应方法 */
	private void book_statistics_actionPerformed() {
		// new BookStatistics();// 创建“图书统计”界面
	}

	/** 成员方法13：修改密码响应方法 */
	private void update_pass_actionPerformed() {
		new UpdatePass();//// 创建“修改用户密码”界面
	}

	/** 成员方法14：退出系统响应方法 */
	private void exit_actionPerformed() {
		BaseDao.close();// 关闭与数据库的连接
		System.exit(0);// 退出系统
	}

	// 测试方法：用于主界面的测试
	public static void main(String args[]) {
		new MainFrame();// 创建系统主界面匿名对象
	}
}
