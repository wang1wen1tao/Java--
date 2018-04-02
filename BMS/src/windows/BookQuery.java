package windows;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.util.List;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import data.BookDao;
import entity.Book;

public class BookQuery extends PublicJFrame {
	/** 成员变量： */
	JLabel lb_query;// 声明查询标签
	JTextField tf_query;// 声明查询条件文本框
	JComboBox<String> cb_query;// 声明查询栏目组合框
	JButton bt_query;// 声明查询按钮
	private JTable table;// 声明表格
	// 调用方法，获取所有图书列表，并保存
	private List<Book> list = BookDao.selectBookList();
	// 创建图书表栏目名称字符串
	private String[] tb_heads = { "图书编号 ", "书名 ", "图书类型", "作者", "译者",
			"出版社", "出版时间", "库存", "价格" };
	// 图书表栏目名称对应的表字段
	private String[] fields = { "id", "name", "type", "author",
			"translator", "publisher", "publish_time", "stock",
			"price" };
	// 创建图书表格模型
	private DefaultTableModel model = new DefaultTableModel(
			new Object[][] {}, tb_heads);
	private String field = "id";// 设置默认的当前的查询字段
	String valueStr = " ";// 设置字符串类型查询字段的初始值
	int valueInt = 0;// 设置整型类型查询字段的初始值
	double valueDouble = 0.0;// 设置实型数据查询字段的初始值

	/** 构造方法：初始化界面 */
	BookQuery() {
		this.setTitle("--图书查询-- ");
		this.setVisible(true);
		this.setSize(1200, 500);
		JPanel queryPanel = createQueryPanel();
		this.add(queryPanel, BorderLayout.NORTH);
		JPanel tablePanel = createTablePanel();
		this.add(tablePanel, BorderLayout.CENTER);
		this.setLocationRelativeTo(null);
	}

	/** 成员方法1：创建查询面板的方法 */
	private JPanel createQueryPanel() {
		JPanel queryPanel = new JPanel();
		queryPanel.setOpaque(false);
		queryPanel.setLayout(new GridBagLayout());
		((GridBagLayout) queryPanel
				.getLayout()).columnWidths = new int[] { 0, 100, 200,
						120, 80, 80, 0 };
		((GridBagLayout) queryPanel
				.getLayout()).columnWeights = new double[] { 0.5, 0.0,
						0.0, 0.0, 0.0, 0.0, 0.5 };

		lb_query = new JLabel("请输入图书编号");
		queryPanel.add(lb_query,
				new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.EAST,
						GridBagConstraints.BOTH,
						new Insets(10, 10, 10, 10), 0, 0));

		tf_query = new JTextField();
		tf_query.requestFocus();
		// 为查询条件文本框，添加回车键事件监听器
		tf_query.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {
				if (arg0.getKeyChar() == '\n') {
					// 调用“查询”按钮事件响应方法
					bn_query_actionPerformed();
				}
			}
		});
		queryPanel.add(tf_query,
				new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER,
						GridBagConstraints.BOTH,
						new Insets(10, 0, 10, 10), 0, 0));

		cb_query = new JComboBox<String>(tb_heads);
		// 为查询栏目组合框，添加条目事件监听器
		cb_query.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// 调用“查询栏目”组合框栏目状态被改变事件响应方法
				cb_query_itemStateChanged(arg0);
			}
		});
		queryPanel.add(cb_query,
				new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER,
						GridBagConstraints.BOTH,
						new Insets(10, 0, 10, 10), 0, 0));

		bt_query = new JButton("查询 ");
		// 为“查询”按钮，添加单击事件监听器
		bt_query.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// 调用“查询”按钮事件响应方法
				bn_query_actionPerformed();
			}
		});
		queryPanel.add(bt_query,
				new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER,
						GridBagConstraints.BOTH,
						new Insets(10, 0, 10, 10), 0, 0));

		JButton bn_close = new JButton("关闭");
		// 为“关闭”按钮，添加单击事件监听器
		bn_close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();// 关闭界面
			}
		});
		queryPanel.add(bn_close,
				new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER,
						GridBagConstraints.BOTH,
						new Insets(10, 0, 10, 10), 0, 0));
		return queryPanel;// 返回“查询面板”
	}

	/** 成员方法2：创建表格面板的方法 */
	private JPanel createTablePanel() {
		// 创建表格面板，采用边界布局
		JPanel tablePanel = new JPanel(new BorderLayout(5, 5));
		JScrollPane scrollPane = new JScrollPane();// 创建滚动面板
		tablePanel.add(scrollPane);// 将表格面板添加到滚动面板上
		table = new JTable(model);// 创建表格，并采用指定的表格模型
		addRowToModel(list);
		scrollPane.setViewportView(table);
		return tablePanel;
	}

	/** 成员方法3：向表格模型，添加图书列表 */
	private void addRowToModel(List<Book> list) {
		model.setRowCount(0);// 清除表格模型中的数据
		for (int i = 0; i < list.size(); i++) {
			Book book = (Book) list.get(i);
			model.addRow(new Object[] { book.getId(), book.getName(),
					book.getType(), book.getAuthor(),
					book.getTranslator(), book.getPublisher(),
					book.getPublish_time(), book.getStock(),
					book.getPrice() });
		}
	}

	/** 成员方法4：查询栏目组合框状态被改变的事件响应方法 */
	private void cb_query_itemStateChanged(ItemEvent arg0) {
		if (arg0.getStateChange() == ItemEvent.SELECTED) {
			// 根据选择改变标签显示内容
			lb_query.setText("请输入" + (String) arg0.getItem() + ":");
			tf_query.setText("");
			// 获取组合框中选定的栏目名称
			for (int i = 0; i < tb_heads.length; i++) {
				if (arg0.getItem().equals(tb_heads[i])) {
					field = fields[i];// 保存对应的字段，作为当前被选定的字段
				}
			}
			list = BookDao.selectBookList();// 调用方法，获取所有图书列表
			addRowToModel(list);// 调用方法：将图书列表，添加到表格模型中
			tf_query.requestFocus();// 设置查询条件文本框，获得焦点
		}
	}

	/** 成员方法5：“查询”按钮单击事件响应方法 */
	private void bn_query_actionPerformed() {

		switch (field) {
		case "id":
		case "name":
		case "type":
		case "author":
		case "translator":
		case "publisher":
		case "publish_time":
			// 获取字符串类型的查询条件
			valueStr = tf_query.getText();
			// 调用按照（字段名、字符串字段值）查询图书列表的方法，查询图书信息
			list = BookDao.selectBookList(field, valueStr);
			break;
		case "stock":
			// 获取整型类型的查询条件
			if (tf_query.getText().equals("")) {
				tf_query.setText("0");
			}
			valueInt = new Integer(tf_query.getText()).intValue();
			// 调用按照（字段名、整型字段值）查询图书列表的方法，查询图书信息
			list = BookDao.selectBookList(field, valueInt);
			break;
		case "price":
			// 获取实型类型的查询条件
			if (tf_query.getText().equals("")) {
				tf_query.setText("0");
			}
			valueDouble = new Double(tf_query.getText())
					.doubleValue();
			// 调用按照（字段名、实型字段值）查询图书列表的方法，查询图书信息
			list = BookDao.selectBookList(field, valueDouble);
			break;
		}
		addRowToModel(list);// 将查询获取的图书列表，添加到表格模型中

	}

	/** 测试方法： */
	public static void main(String[] args) {
		new BookQuery();
	}
}
