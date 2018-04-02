package windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import data.UserDao;
import entity.User;

public class UserUpkeep extends PublicJFrame {
	/** 成员变量 */
	private JTextField tf_id;// 声明用户编号文本框
	private JTextField tf_name;// 声明用户名文本框
	private JPasswordField pf_pass;// 声明密码密码框
	private JComboBox<String> jc_isAdmin;// 声明管理权限组合框

	// 声明添加、修改、取消、关闭、删除、清空按钮
	private JButton jb_insert, jb_update, jb_cancel, jb_close, jb_delete, jb_empty;
	private JTable table;// 声明表格
	// 创建用户表格模型
	private DefaultTableModel model = new DefaultTableModel(new Object[][] {},
			new String[] { "编号", "姓名", "密码", "管理员权限" });
	// 创建用户列表，并保存所有用户对象
	private List<User> list = UserDao.selectUserList();

	/** 构造方法：用于初始化界面 */
	UserUpkeep() {
		// 第1步： 设置界面
		this.setTitle("--用户信息维护--");
		setBounds(220, 100, 800, 400);
		this.setResizable(false);

		// 第2步： 创建、设置一个“对话面板”，并将它设置为框架的内容面板。其上将放置一个外部分割面板
		JPanel dialogPane = new JPanel();// 创建“对话面板”
		dialogPane.setBorder(new EmptyBorder(5, 5, 5, 5));// 设置边缘空白大小
		dialogPane.setBackground(new Color(110, 212, 103));// 设置界面背景
		dialogPane.setLayout(new BorderLayout());// 设置界面布局为边界布局
		setContentPane(dialogPane);// 将“对话面板”设置为框架的内容模板

		// 第3步：创建、设置一个“外部分割面板”，水平左右放置组件。其上，左测将放置一个内部分割面板，右测将放置另一个表格面板
		JSplitPane outerPane = new JSplitPane();// 创建“外部分割面板”
		outerPane.setOpaque(false);// 设置“外部分割面板”透明
		outerPane.setResizeWeight(0.1);// 设置“外部分割面板”宽度分配权限
		outerPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);// 设置“外部分割面板”为水平分配
		outerPane.setOneTouchExpandable(true);// 设置“外部分割面板”可以有一个触发扩展
		dialogPane.add(outerPane, BorderLayout.CENTER);// 将“外部分割面板”添加到“对话面板”的中央

		// 第4步：创建、设置一个内部分割面板，垂直上下放置组件。其上方将放置一个数据面板，下方将放置一个按钮面板
		JSplitPane innerPane = new JSplitPane();// 创建“内部分割面板”
		innerPane.setOpaque(false);// 设置“内部分割面板”透明
		// 为“内部分割面板”设置边框说明
		innerPane.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
						"用户信息维护", TitledBorder.LEADING, TitledBorder.TOP,
						new Font("微软雅黑", Font.PLAIN, 14), new Color(59, 59, 59)));
		innerPane.setResizeWeight(0.8); // 设置“内部分割面板”高度分配权限
		innerPane.setOrientation(JSplitPane.VERTICAL_SPLIT);// 设置“内部分割面板”为垂直分配
		outerPane.setLeftComponent(innerPane);// 将“内部分割面板”设置在“外部分割面板”的左侧

		// 第5步：创建、设置“数据面板”
			// 调用创建“数据面板”方法，创建“数据面板”，并将“数据面板”，设置在“内部分割面板"的上方
		innerPane.setLeftComponent(createDataPanel());
		// 第6步：创建、设置“按钮面板”
			// 调用创建"按钮面板"方法，创建"按钮面板"，并 将"按钮面板"设置在"内部分割面板"的下方
		innerPane.setRightComponent(createButtonPanel());
		// 第7步：创建、设置“表格面板”
			// 调用创建"表格面板”方法，创建“表格面板”，并将“表格面板”，设置在“外部分割面板”的右侧
		outerPane.setRightComponent(createTablePanel());
	}

	/** 成员方法1：用于创建“数据面板”的方法 */
	private JPanel createDataPanel() {
		//第1步：创建、设置“数据面板”
		JPanel dataPanel = new JPanel(null);//创建“数据面板”

		dataPanel.setBorder(new EmptyBorder(5, 5, 5, 10));//设置“数据面板”的边缘空白大小
		dataPanel.setOpaque(false);//设置“数据面板”透明
		//第2步：创建、设置“数据面板”上的各个组件，并添加到“数据面板”上
		JLabel jl_id = new JLabel("编号:");//创建“用户编号”标签
		jl_id.setBounds(50, 20, 100, 25);//设置“用户编号”标签的位置及大小
		dataPanel.add(jl_id);//将“用户编号”标签添加到“数据面板”上

			// 创建“用户编号”文本框，并根据user表的用户个数，显示初始数据
		if (list.size() == 0) {
			tf_id = new JTextField(String.valueOf(1));
		} else {
			tf_id = new JTextField(String.valueOf(list.size() + 1));
		}

		tf_id.addFocusListener(new FocusAdapter() {// 为“用户编号”文本框添加失去焦点监听器
			public void focusLost(FocusEvent e) {
				tf_id_focusLost();// 调用“用户编号”文本框加失去焦点事件响应方法
			}
		});
		tf_id.setBounds(130, 20, 150, 25);
		tf_id.setToolTipText("必须输入用户编号");// 设置“用户编号”文本框提示信息
		dataPanel.add(tf_id);

		JLabel jl_name = new JLabel("用户名:");//创建“用户名”标签
		jl_name.setBounds(50, 60, 100, 25);
		dataPanel.add(jl_name);

		tf_name = new JTextField(10);//创建“用户名”文本框
		tf_name.setBounds(130, 60, 150, 25);
		tf_name.setToolTipText("必须输入用户名");
		dataPanel.add(tf_name);
		tf_name.addFocusListener(new FocusAdapter() {// 为“用户名”文本框添加失去焦点监听器
			public void focusLost(FocusEvent e) {
				tf_name_focusLost();// 调用“用户名”文本框加失去焦点事件响应方法
			}
		});

		JLabel jl_pass1 = new JLabel("密码:");//创建“密码”标签
		jl_pass1.setBounds(50, 100, 100, 25);
		dataPanel.add(jl_pass1);

		pf_pass = new JPasswordField(10);//创建“密码”密码框
		pf_pass.setBounds(130, 100, 150, 25);
		pf_pass.setToolTipText("必须输入用户密码");
		dataPanel.add(pf_pass);

		JLabel jl_isAdmin = new JLabel("管理权限：");//创建“用户权限”标签
		jl_isAdmin.setBounds(50, 140, 100, 25);
		dataPanel.add(jl_isAdmin);

		String[] admin = new String[] { "管理员", "操作员", "一般用户" };
		jc_isAdmin = new JComboBox<String>(admin);//创建“用户权限”组合框，并提供选择项
		jc_isAdmin.setBounds(130, 140, 150, 25);
		dataPanel.add(jc_isAdmin);
		//第3步：返回“数据面板”
		return dataPanel;
	}

	/** 成员方法2：用于创建“按钮面板”的方法 */
	private JPanel createButtonPanel() {
		//第1步：创建、设置“按钮面板”
		JPanel buttonPanel = new JPanel(new GridBagLayout());//创建“按钮面板”，并采用网格包布局
		buttonPanel.setOpaque(false);
		((GridBagLayout) buttonPanel.getLayout()).columnWidths = new int[] { 0, 60, 60,
				60, 60, 60, 0 };// 设置网格包布局各列宽度
		((GridBagLayout) buttonPanel.getLayout()).columnWeights = new double[] { 0.5, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.5 };//设置网格包布局各列的宽度权限
		//第2步：创建、设置“按钮面板”上的各个按钮组件，并添加单击事件监听器
		jb_insert = new JButton("添加");//创建“添加”按钮
		jb_insert.addActionListener(new ActionListener() {//为“添加”按钮添加单击事件监听器
			public void actionPerformed(ActionEvent e) {
				jb_insert_actionPerformed();//调用“添加”按钮事件响应方法，添加新用户
			}
		});
		//将“添加”按钮添加到“按钮面板”
		buttonPanel.add(jb_insert,
				new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));

		jb_update = new JButton("修改");//创建“修改”按钮
		jb_update.addActionListener(new ActionListener() {//为“修改”按钮添加单击事件监听器
			public void actionPerformed(ActionEvent e) {
				jb_update_actionPerformed();// 调用“修改"按钮事件响应方法，修改用户信息
			}
		});
		//将“修改”按钮添加到“按钮面板”
		buttonPanel.add(jb_update,
				new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));

		jb_delete = new JButton("删除");//创建“删除”按钮
		jb_delete.addActionListener(new ActionListener() {// 为删除按钮添加单击事件监听器
			public void actionPerformed(ActionEvent e) {
				jb_delete_actionPerformed();// 调用删除按钮事件响应方法，删除用户
			}
		});
		buttonPanel.add(jb_delete,
				new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));

		jb_cancel = new JButton("取消");//创建“取消”按钮
		jb_cancel.addActionListener(new ActionListener() {// 为取消按钮添加单击事件监听器
			public void actionPerformed(ActionEvent e) {
				del_content();// 调用清空"数据面板”内容方法，清除数据面板中的数据
			}
		});
		buttonPanel.add(jb_cancel,
				new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));

		jb_close = new JButton("关闭");//创建“关闭”按钮
		jb_close.addActionListener(new ActionListener() {// 为关闭按钮添加单击事件监听器
			public void actionPerformed(ActionEvent e) {
				dispose();// 关闭“用户维护界面”
			}
		});
		buttonPanel.add(jb_close,
				new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));

		jb_empty = new JButton("清空所有用户");//创建“清空所有数据”按钮
		jb_empty.addActionListener(new ActionListener() {// 为清空按钮添加单击事件监听器
			public void actionPerformed(ActionEvent e) {
				jb_empty_actionPerformed();// 调用清空按钮事件响应方法，清空所有用户信息
			}
		});
		buttonPanel.add(jb_empty,
				new GridBagConstraints(2, 1, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(25, 0, 0, 0), 0, 0));
		//第3步：返回“按钮面板”
		return buttonPanel;
	}

	/** 成员方法3：用于创建表格面板的方法 */
	private JPanel createTablePanel() {
		//第1步：创建、设置“表格面板”
		JPanel tablePanel = new JPanel(new BorderLayout(5, 5));// 创建表格面板，采用边界布局
		JScrollPane scrollPane = new JScrollPane();//创建“滚动面板”
		tablePanel.add(scrollPane);// 将'滚动面板"添加到上"表格面板"上
		//第2步：创建、设置“表格”，并为表格添加鼠标单击事件监听器
		table = new JTable(model);//采用指定的表格模型来创建表格
		for (int i = 0; i < list.size(); i++) {// 将user表中的用户添加到表格模型中
			User user = (User) list.get(i);
			model.addRow(new Object[] { user.getId(), user.getName(), user.getPass(),
					user.getIs_admin() });
		}
		table.addMouseListener(new MouseAdapter() {//为表格添加鼠标事件监听器
			public void mouseClicked(MouseEvent e) {
				table_mouseClicked();//调用表格鼠标单击事件响应方法，在左侧“数据面板”显示选中的用户信息
			}
		});
		scrollPane.setViewportView(table);// 将“表格”设置在'滚动面板"的视窗上显示
		//第3步：返回“表格面板”
		return tablePanel;
	}

	/** 成员方法4：用于清空“数据面板”中的数据 */
	public void del_content() {
		//重新设置用户编号文本框中的数据，按照user表中用户个数重新设置
		tf_id.setText(String.valueOf(list.size() + 1));
		// 将“数据面板”中其它文本框和密码框的内容设置为空
		tf_name.setText("");
		pf_pass.setText("");
		jc_isAdmin.setSelectedItem("管理员");// 将组合框的内容设置为“管理员”
		tf_id.setEditable(true);// 将“用户编号”文本框设置可用
	}

	/** 成员方法5：用户编号文本框失去焦点事件响应方法 */
	public void tf_id_focusLost() {
		User user = UserDao.getUser(Integer.parseInt(tf_id.getText().trim()));// 通过用户编号，获取一个用户对象
		if (user != null) {// 判断用户对象是否存在，若存在，清除数据，重新输入用户编号
			JOptionPane.showMessageDialog(null, "用户编号已经存在，请重新输入用户编号！");
			del_content(); // 调用清空“数据面板”方法，清空“数据面板”中的数据
		}
	}

	/** 成员方法6：用户名文本框失去焦点事件响应方法 */
	public void tf_name_focusLost() {
		User user = UserDao.getUser(tf_name.getText().trim());// 通过用户名，获取用户对象
		if (user != null) {// 判断用户对象是否存在，若存在，清除用户名数据，重新输入用户名
			JOptionPane.showMessageDialog(null, "用户名已经存在，请重新输入用户名！");
			tf_name.setText("");
		}
	}

	/** 成员方法7：用于刷新表格数据 */
	public void refresh() {
		model.setRowCount(0);// 清除表格模型中的数据
		list = UserDao.selectUserList();// 获取用户对象列表
		// 重新向表格模型中添加数据
		for (int i = 0; i < list.size(); i++) {
			User user = (User) list.get(i);
			model.addRow(new Object[] { user.getId(), user.getName(), user.getPass(),
					user.getIs_admin() });
		}
		del_content();// 清空“数据面板”中的数据

	}

	/** 成员方法8：表格鼠标单击事件响应方法 */
	public void table_mouseClicked() {
		// 获取列表中选中用户对象
		User user = (User) list.get(table.getSelectedRow());
		// 在“数据面板”中设置对应的数据
		tf_id.setText(String.valueOf(user.getId()));//设置用户编号
		tf_name.setText(user.getName());//设置用户名
		pf_pass.setText(user.getPass());//设置用户密码
		//设置用户权限
		if (user.getIs_admin() == 1) {
			jc_isAdmin.setSelectedIndex(0);
		} else {
			if (user.getIs_admin() == 0) {
				jc_isAdmin.setSelectedIndex(1);
			} else {
				jc_isAdmin.setSelectedIndex(2);
			}

		}
		tf_id.setEditable(false);// 设置“用户编号"文本框不可编辑
	}

	/** 成员方法9："添加"按钮事件响应方法，用于添加新用户 */
	public void jb_insert_actionPerformed() {
		// 第1步：创建一个用户对象
		User user = new User();
		// 第2步：设置用户对象的属性值
		user.setId(Integer.parseInt(tf_id.getText().trim()));// 设置用户对象的编号
		user.setName(tf_name.getText().trim());// 设置用户对象的用户名
		user.setPass(new String(pf_pass.getPassword()).trim());// 设置用户对象的密码
		int is_admin = 2;// 获取“用户权限”
		if (jc_isAdmin.getSelectedItem().toString().equals("管理员")) {
			is_admin = 1;
		} else {
			if (jc_isAdmin.getSelectedItem().toString().equals("操作员")) {
				is_admin = 0;
			}
		}
		user.setIs_admin((byte) is_admin);// 设置用户对象的管理权限
		// 第3步：判断用户名或密码是否为空？不空执行添加操作。判断添加操作是否成功？成功，向表格模型添加这条数据并刷新
		if (tf_name.getText().trim().equals("")
				|| new String(pf_pass.getPassword()).trim().equals("")) {
			//若空，则给出提示，并返回
			JOptionPane.showMessageDialog(null, "用户信息不能为空！");
			return;
		} else {// 若不空，则执行“添加”用户对象操作
			int i = UserDao.insertUser(user);// 执行添加操作
			if (i == 1) {// 判断添加操作是否成功？若成功，则向表格模型添加这条数据
				model.addRow(new Object[] { user.getId(), user.getName(), user.getPass(),
						user.getIs_admin() });
				refresh();// 刷新“表格面板”内容
			}
			del_content();// 清空“数据面板”中的数据
		}
	}

	/** 成员方法10：“修改”按钮事件响应方法，用于修改用户信息 */
	public void jb_update_actionPerformed() {
		User user = new User();
		user.setId(Integer.parseInt(tf_id.getText().trim()));
		user.setName(tf_name.getText().trim());
		user.setPass(new String(pf_pass.getPassword()).trim());
		int is_admin = 2;// 获取“用户权限”
		if (jc_isAdmin.getSelectedItem().toString().equals("管理员")) {
			is_admin = 1;
		} else {
			if (jc_isAdmin.getSelectedItem().toString().equals("操作员")) {
				is_admin = 0;
			}
		}
		user.setIs_admin((byte) is_admin);
		if (tf_name.getText().trim().equals("")
				|| new String(pf_pass.getPassword()).trim().equals("")) {
			JOptionPane.showMessageDialog(null, "用户信息不能为空！");
			return;
		} else {
			int i = UserDao.updateUser(user);// 执行更新操作
			if (i == 1) {
				JOptionPane.showMessageDialog(null, "用户信息修改成功！");
			}
			refresh();// 刷新“表格面板”内容
			del_content();// 清空“数据面板”中的数据
		}
	}

	/** 成员方法11：“删除”按钮事件响应方法，用于删除用户 */
	public void jb_delete_actionPerformed() {
		int id = Integer.parseInt(tf_id.getText()); // 获取用户编号
		int m = JOptionPane.showConfirmDialog(null, "你确认要删除这条用户信息吗?", "删除用户信息",
				JOptionPane.YES_NO_OPTION); // 调用确认对话框，询问是否删除用户？
		if (m == JOptionPane.YES_OPTION) {// 如果确定，则执行删除操作
			if (UserDao.deleteUser(id) == 1) {// 执行用户删除操作
				refresh();// 刷新“表格面板”内容
				JOptionPane.showMessageDialog(null, "用户信息删除成功！");
			}
			del_content();//  清空“数据面板”中的数据
		}
	}

	/** 成员方法12：“删除所有用户”按钮事件响应方法，用于删除全部用户信息 */
	public void jb_empty_actionPerformed() {
		int m = JOptionPane.showConfirmDialog(null, "你确认要删除全部用户吗?", "清空用户",
				JOptionPane.YES_NO_OPTION); // 调用确认对话框，询问是否删除所有用户？
		if (m == JOptionPane.YES_OPTION) {// 如果确定，则再次调用确认对话框，询问是否删除所有用户？
			int n = JOptionPane.showConfirmDialog(null, "确认要清空全部用户吗?", "清空用户再次确认",
					JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {// 如果确定，则执行清空user表操作
				UserDao.emptyUser();// 执行清空user数据表操作
				refresh();// 刷新“表格面板”内容
				del_content();//  清空“数据面板”中的数据
			}
		}
	}

	// 测试方法：用于用户维护的测试
	public static void main(String[] args) {
		new UserUpkeep();// 创建UserUpkeep类的一个匿名对象
	}
}
