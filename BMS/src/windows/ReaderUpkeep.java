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
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import util.Constant;

import data.ReaderDao;
import entity.Reader;

public class ReaderUpkeep extends PublicJFrame {
	/** 成员变量 */
	private JTextField tf_id;// 声明读者编号文本框
	private JTextField tf_name;// 声明读者名文本框
	private JComboBox<String> cb_type;// 声明读者类型组合框
	private JComboBox<String> cb_sex;// 声明读者性别组合框
	private JTextField tf_max_num;// 声明读者最大借阅数量文本框
	private JTextField tf_days_num;// 声明读者最大借阅天数文本框
	// 声明添加、修改、取消、关闭、删除、清空按钮
	private JButton jb_insert, jb_update, jb_cancel, jb_close,
			jb_delete, jb_empty;
	private JTable table;// 声明表格
	private DefaultTableModel model = new DefaultTableModel(
			new Object[][] {}, new String[] { "编号", "姓名", "读者类型",
					"性别", "最大借书数量", "最多借书天数" });// 创建读者表格模型
	private List<Reader> list = ReaderDao.selectReaderList();// 创建所有读者列表，并保存读者对象

	/** 构造方法：用于初始化界面 */
	ReaderUpkeep() {
		// 界面设置
		this.setTitle("--读者信息维护--");
		setBounds(220, 100, 1000, 500);
		this.setResizable(false);

		// 创建一个“对话面板”,其上放置一个“外部分割面板”，并将它设置为框架的内容面板
		JPanel dialogPane = new JPanel();
		dialogPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialogPane.setBackground(new Color(110, 212, 103));
		dialogPane.setLayout(new BorderLayout());
		setContentPane(dialogPane);

		/**
		 * 创建一个“外部分割面板”，水平左右放置组件。 其中，左测放置一个“内部分割面板”，右测放置一个“表格面板”
		 */
		JSplitPane outerPane = new JSplitPane();// 创建“外部分割面板”
		outerPane.setOpaque(false);// 设置“外部分割面板”透明
		outerPane.setResizeWeight(0.1);// 设置“外部分割面”板宽度分配权限
		outerPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);// 设置"外部分割面板"为水平分配
		outerPane.setOneTouchExpandable(true);// 设置"外部分割面板"可以有一个触发扩展
		dialogPane.add(outerPane, BorderLayout.CENTER);
		// 创建一个"内部分割面板"，垂直上下放置组件。其中，上方放置一个"数据面板"，下方放置一个"按钮面板"
		JSplitPane innerPane = new JSplitPane();// 创建"内部分割面板"
		innerPane.setOpaque(false);
		// 为一个“内部分割面板”设置边框说明
		innerPane.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"读者信息维护", TitledBorder.LEADING, TitledBorder.TOP,
				new Font("微软雅黑", Font.PLAIN, 14),
				new Color(59, 59, 59)));
		innerPane.setResizeWeight(0.8);
		innerPane.setOrientation(JSplitPane.VERTICAL_SPLIT);// 设置“内部分割面板”为垂直分配
		outerPane.setLeftComponent(innerPane);// 将“内部分割面板”设置在“外部分割面板”的左侧
		JPanel dataPanel = createDataPanel();// 调用创建“数据面板”方法，创建“数据面板”
		innerPane.setLeftComponent(dataPanel);// 将“数据面板”，设置在“内部分割面板”的左侧
		JPanel buttonPanel = createButtonPanel();// 调用创建“按钮面板”方法，创建“按钮面板”
		innerPane.setRightComponent(buttonPanel);// 将“按钮面板”设置在“内部分割面板”的下方
		JPanel tablePanel = createTablePanel();// 调用创建“表格面板”方法，创建“表格面板”
		outerPane.setRightComponent(tablePanel);// 将“表格面板”，设置在“外部分割面板”的右侧
	}

	/** 成员方法1：用于创建“数据面板”的方法 */
	private JPanel createDataPanel() {
		JPanel dataPanel = new JPanel(null);
		dataPanel.setBorder(new EmptyBorder(5, 5, 5, 10));
		dataPanel.setOpaque(false);

		JLabel jl_id = new JLabel("编号:");
		jl_id.setBounds(50, 20, 100, 25);
		dataPanel.add(jl_id);

		tf_id = new JTextField();
		tf_id.setBounds(160, 20, 150, 25);

		tf_id.setToolTipText("必须输入读者编号");
		tf_id.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				tf_id_focusLost();// 调用读者编号文本框加失去焦点事件响应方法
			}
		});
		dataPanel.add(tf_id);

		JLabel jl_name = new JLabel("读者名称:");
		jl_name.setBounds(50, 60, 100, 25);
		dataPanel.add(jl_name);

		tf_name = new JTextField(10);
		tf_name.setBounds(160, 60, 150, 25);
		tf_name.setToolTipText("必须输入读者名");
		dataPanel.add(tf_name);

		JLabel jl_type = new JLabel("读者类型:");
		jl_type.setBounds(50, 100, 150, 25);
		dataPanel.add(jl_type);

		cb_type = new JComboBox<String>(Constant.READER_TYPES);
		cb_type.setBounds(160, 100, 150, 25);
		dataPanel.add(cb_type);

		JLabel jl_sex = new JLabel("性别：");
		jl_sex.setBounds(50, 140, 150, 25);
		dataPanel.add(jl_sex);

		cb_sex = new JComboBox<String>(Constant.SEX);
		cb_sex.setBounds(160, 140, 150, 25);
		dataPanel.add(cb_sex);

		JLabel jl_max_num = new JLabel("最大借书数量:");
		jl_max_num.setBounds(50, 180, 150, 25);
		dataPanel.add(jl_max_num);

		tf_max_num = new JTextField(10);
		tf_max_num.setBounds(160, 180, 150, 25);
		dataPanel.add(tf_max_num);

		JLabel jl_days_num = new JLabel("最多借书天数:");
		jl_days_num.setBounds(50, 220, 150, 25);
		dataPanel.add(jl_days_num);

		tf_days_num = new JTextField(10);
		tf_days_num.setBounds(160, 220, 150, 25);
		dataPanel.add(tf_days_num);

		return dataPanel;
	}

	/** 成员方法2：用于创建“按钮面板”的方法 */
	private JPanel createButtonPanel() {
		// 创建“按钮面板”，并采用网格包布局
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		buttonPanel.setOpaque(false);
		((GridBagLayout) buttonPanel
				.getLayout()).columnWidths = new int[] { 0, 60, 60,
						60, 60, 60, 0 };// 设置网格包布局各列宽度
		((GridBagLayout) buttonPanel
				.getLayout()).columnWeights = new double[] { 0.5, 0.0,
						0.0, 0.0, 0.0, 0.0, 0.5 };// 设置网格包布局各列的宽度权限

		jb_insert = new JButton("添加");
		jb_insert.addActionListener(new ActionListener() {// 为添加按钮添加单击事件监听器
			public void actionPerformed(ActionEvent e) {
				jb_insert_actionPerformed();// 调用“添加”按钮事件响应方法，添加新用户
			}
		});
		buttonPanel.add(jb_insert,
				new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER,
						GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 5), 0, 0));

		jb_update = new JButton("修改");
		jb_update.addActionListener(new ActionListener() {// 为修改按钮添加单击事件监听器
			public void actionPerformed(ActionEvent e) {
				jb_update_actionPerformed();// 调用“修改”按钮事件响应方法，修改用户信息
			}
		});
		buttonPanel.add(jb_update,
				new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER,
						GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 5), 0, 0));

		jb_delete = new JButton("删除");
		jb_delete.addActionListener(new ActionListener() {// 为删除按钮添加单击事件监听器
			public void actionPerformed(ActionEvent e) {
				jb_delete_actionPerformed();// 调用“删除”按钮事件响应方法，删除用户对象
			}
		});
		buttonPanel.add(jb_delete,
				new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER,
						GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 5), 0, 0));

		jb_cancel = new JButton("取消");
		jb_cancel.addActionListener(new ActionListener() {// 为取消按钮添加单击事件监听器
			public void actionPerformed(ActionEvent e) {
				del_content();// 调用清空“数据面板”方法，清除数据面板中的数据
			}
		});
		buttonPanel.add(jb_cancel,
				new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER,
						GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 5), 0, 0));

		jb_close = new JButton("关闭");
		jb_close.addActionListener(new ActionListener() {// 为关闭按钮添加单击事件监听器
			public void actionPerformed(ActionEvent e) {
				dispose();// 关闭用户维护界面
			}
		});
		buttonPanel.add(jb_close,
				new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER,
						GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 5), 0, 0));

		jb_empty = new JButton("清空所有读者");
		jb_empty.addActionListener(new ActionListener() {// 为清空按钮添加单击事件监听器
			public void actionPerformed(ActionEvent e) {
				jb_empty_actionPerformed();// 调用“清空所有读者”按钮事件响应方法，清空所有读者信息
			}
		});
		buttonPanel.add(jb_empty,
				new GridBagConstraints(2, 1, 3, 1, 0.0, 0.0,
						GridBagConstraints.CENTER,
						GridBagConstraints.BOTH,
						new Insets(25, 0, 0, 0), 0, 0));
		return buttonPanel;
	}

	/** 成员方法3：用于创建“表格面板”的方法 */
	private JPanel createTablePanel() {
		JPanel tablePanel = new JPanel(new BorderLayout(5, 5));// 创建表格面板，采用边界布局
		JScrollPane scrollPane = new JScrollPane();// 创建滚动面板
		tablePanel.add(scrollPane);// 将表格面板添加到滚动面板上
		table = new JTable(model);// 创建表格，并采用指定的表格模型

		for (int i = 0; i < list.size(); i++) {
			Reader reader = (Reader) list.get(i);
			model.addRow(new Object[] { reader.getId(),
					reader.getName(), reader.getType(),
					reader.getSex(), reader.getMax_num(),
					reader.getDays_num() });
		}
		table.addMouseListener(new MouseAdapter() {// 为表格添加鼠标事件监听器
			public void mouseClicked(MouseEvent e) {
				table_mouseClicked();// 调用表格鼠标单击事件响应方法，向左侧数据面板显示选中读者的信息
			}
		});
		scrollPane.setViewportView(table);
		return tablePanel;
	}

	/** 成员方法4：用于清空“数据面板”中的数据 */
	public void del_content() {
		tf_id.setText("");
		tf_id.setEditable(true);
		tf_name.setText("");
		cb_type.setSelectedIndex(0);
		cb_sex.setSelectedItem("管理员");
		tf_max_num.setText("");
		tf_days_num.setText("");

	}

	/** 成员方法5：读者编号文本框失去焦点事件响应方法 */
	public void tf_id_focusLost() {
		Reader reader = ReaderDao
				.getReaderById(tf_id.getText().trim());// 通过读者编号，获取读者对象
		if (reader != null) {// 判断读者对象是否存在，若存在，清除数据，重新输入读者编号
			JOptionPane.showMessageDialog(null,
					"读者编号已经存在，请重新输入读者编号！");
			del_content(); // 清除数据面板中的数据
		}
	}

	/** 成员方法6：用于更新表格数据 */
	public void refresh() {
		model.setRowCount(0);// 清除表格模型中的数据
		list = ReaderDao.selectReaderList();// 获取读者对象列表
		for (int i = 0; i < list.size(); i++) {// 重新项表格模型中添加数据
			Reader reader = (Reader) list.get(i);
			model.addRow(new Object[] { reader.getId(),
					reader.getName(), reader.getType(),
					reader.getSex(), reader.getMax_num(),
					reader.getDays_num() });
		}
		del_content();// 清除数据面板中的数据

	}

	/** 成员方法7：表格鼠标单击事件响应方法 */
	public void table_mouseClicked() {
		// 获取选中读者的信息
		Reader reader_old = (Reader) list.get(table.getSelectedRow());
		// 在数据面板中设置对应的数据
		tf_id.setText(reader_old.getId());
		tf_name.setText(reader_old.getName());
		cb_type.setSelectedItem(reader_old.getType());
		cb_sex.setSelectedItem(reader_old.getSex());
		tf_max_num.setText(String.valueOf(reader_old.getMax_num()));
		tf_days_num.setText(String.valueOf(reader_old.getDays_num()));

		tf_id.setEditable(false);// 设置读者编号文本框不可编辑
	}

	/** 成员方法8：“添加”新读者方法 */
	public void jb_insert_actionPerformed() {
		Reader reader = new Reader();// 创建一个读者对象
		// 设置读者对象的属性值
		reader.setId(tf_id.getText().trim());
		reader.setName(tf_name.getText().trim());
		reader.setType(cb_type.getSelectedItem().toString());
		reader.setSex(cb_sex.getSelectedItem().toString());
		reader.setMax_num(new Integer(tf_max_num.getText().trim()));
		reader.setDays_num(new Integer(tf_days_num.getText().trim()));

		// 判断读者名或读者编号不允许为空？若空，则给出提示，并返回
		if (tf_name.getText().trim().equals("")
				|| tf_id.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "读者信息不能为空！");
			return;
		} else {// 若不空，则执行添加读者对象操作
			int i = ReaderDao.insertReader(reader);// 执行添加操作
			if (i == 1) {// 判断添加操作是否成功？若成功，则向表格模型添加这条数据
				model.addRow(new Object[] { reader.getId(),
						reader.getName(), reader.getType(),
						reader.getSex(), reader.getMax_num(),
						reader.getDays_num() });
				refresh();
			}
			del_content();
		}
	}

	/** 成员方法9：“修改”读者信息按钮事件响应方法 */
	public void jb_update_actionPerformed() {
		Reader reader = new Reader();
		reader.setId(tf_id.getText().trim());
		reader.setName(tf_name.getText().trim());
		reader.setType(cb_type.getSelectedItem().toString());
		reader.setSex(cb_sex.getSelectedItem().toString());
		reader.setMax_num(new Integer(tf_max_num.getText().trim()));
		reader.setDays_num(new Integer(tf_days_num.getText().trim()));
		if (tf_name.getText().trim().equals("")
				|| tf_id.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "读者信息不能为空！");
			return;
		} else {// 若不空，则执行添加读者对象操作
			int i = ReaderDao.updateReader(reader);
			if (i == 1) {
				JOptionPane.showMessageDialog(null, "读者信息修改成功！");
			}
			refresh();// 更新表格内容
			del_content();
		}
	}

	/** 成员方法10：“删除”读者信息 */
	public void jb_delete_actionPerformed() {
		String id = tf_id.getText().trim(); // 获取读者编号
		int m = JOptionPane.showConfirmDialog(null, "你确认要删除这条读者信息吗?",
				"删除读者信息", JOptionPane.YES_NO_OPTION); // 调用确认对话框，询问是否删除读者？
		if (m == JOptionPane.YES_OPTION) {// 如果确定，则执行删除操作
			if (ReaderDao.deleteReader(id) == 1) {// 执行读者删除操作
				refresh();// 更新表格内容
				JOptionPane.showMessageDialog(null, "读者信息删除成功！");
			}
			del_content();// 清除数据面板内容
		}
	}

	/** 成员方法11：删除所有读者信息 */
	public void jb_empty_actionPerformed() {
		int m = JOptionPane.showConfirmDialog(null, "你确认要删除所有读者信息吗?",
				"清空读者信息", JOptionPane.YES_NO_OPTION);
		if (m == JOptionPane.YES_OPTION) {
			int n = JOptionPane.showConfirmDialog(null,
					"真的确认清空所有读者数据吗?", "清空读者信息再次确认",
					JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				ReaderDao.emptyReader();
				refresh();// 更新表格内容
				del_content();// 清除数据面板内容
			}
		}
	}

	// 测试方法：用于读者维护的测试
	public static void main(String[] args) {
		new ReaderUpkeep();
	}
}
