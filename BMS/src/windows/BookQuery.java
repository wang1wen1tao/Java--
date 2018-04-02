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
	/** ��Ա������ */
	JLabel lb_query;// ������ѯ��ǩ
	JTextField tf_query;// ������ѯ�����ı���
	JComboBox<String> cb_query;// ������ѯ��Ŀ��Ͽ�
	JButton bt_query;// ������ѯ��ť
	private JTable table;// �������
	// ���÷�������ȡ����ͼ���б�������
	private List<Book> list = BookDao.selectBookList();
	// ����ͼ�����Ŀ�����ַ���
	private String[] tb_heads = { "ͼ���� ", "���� ", "ͼ������", "����", "����",
			"������", "����ʱ��", "���", "�۸�" };
	// ͼ�����Ŀ���ƶ�Ӧ�ı��ֶ�
	private String[] fields = { "id", "name", "type", "author",
			"translator", "publisher", "publish_time", "stock",
			"price" };
	// ����ͼ����ģ��
	private DefaultTableModel model = new DefaultTableModel(
			new Object[][] {}, tb_heads);
	private String field = "id";// ����Ĭ�ϵĵ�ǰ�Ĳ�ѯ�ֶ�
	String valueStr = " ";// �����ַ������Ͳ�ѯ�ֶεĳ�ʼֵ
	int valueInt = 0;// �����������Ͳ�ѯ�ֶεĳ�ʼֵ
	double valueDouble = 0.0;// ����ʵ�����ݲ�ѯ�ֶεĳ�ʼֵ

	/** ���췽������ʼ������ */
	BookQuery() {
		this.setTitle("--ͼ���ѯ-- ");
		this.setVisible(true);
		this.setSize(1200, 500);
		JPanel queryPanel = createQueryPanel();
		this.add(queryPanel, BorderLayout.NORTH);
		JPanel tablePanel = createTablePanel();
		this.add(tablePanel, BorderLayout.CENTER);
		this.setLocationRelativeTo(null);
	}

	/** ��Ա����1��������ѯ���ķ��� */
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

		lb_query = new JLabel("������ͼ����");
		queryPanel.add(lb_query,
				new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.EAST,
						GridBagConstraints.BOTH,
						new Insets(10, 10, 10, 10), 0, 0));

		tf_query = new JTextField();
		tf_query.requestFocus();
		// Ϊ��ѯ�����ı�����ӻس����¼�������
		tf_query.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {
				if (arg0.getKeyChar() == '\n') {
					// ���á���ѯ����ť�¼���Ӧ����
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
		// Ϊ��ѯ��Ŀ��Ͽ������Ŀ�¼�������
		cb_query.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// ���á���ѯ��Ŀ����Ͽ���Ŀ״̬���ı��¼���Ӧ����
				cb_query_itemStateChanged(arg0);
			}
		});
		queryPanel.add(cb_query,
				new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER,
						GridBagConstraints.BOTH,
						new Insets(10, 0, 10, 10), 0, 0));

		bt_query = new JButton("��ѯ ");
		// Ϊ����ѯ����ť����ӵ����¼�������
		bt_query.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// ���á���ѯ����ť�¼���Ӧ����
				bn_query_actionPerformed();
			}
		});
		queryPanel.add(bt_query,
				new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER,
						GridBagConstraints.BOTH,
						new Insets(10, 0, 10, 10), 0, 0));

		JButton bn_close = new JButton("�ر�");
		// Ϊ���رա���ť����ӵ����¼�������
		bn_close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();// �رս���
			}
		});
		queryPanel.add(bn_close,
				new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER,
						GridBagConstraints.BOTH,
						new Insets(10, 0, 10, 10), 0, 0));
		return queryPanel;// ���ء���ѯ��塱
	}

	/** ��Ա����2������������ķ��� */
	private JPanel createTablePanel() {
		// ���������壬���ñ߽粼��
		JPanel tablePanel = new JPanel(new BorderLayout(5, 5));
		JScrollPane scrollPane = new JScrollPane();// �����������
		tablePanel.add(scrollPane);// ����������ӵ����������
		table = new JTable(model);// ������񣬲�����ָ���ı��ģ��
		addRowToModel(list);
		scrollPane.setViewportView(table);
		return tablePanel;
	}

	/** ��Ա����3������ģ�ͣ����ͼ���б� */
	private void addRowToModel(List<Book> list) {
		model.setRowCount(0);// ������ģ���е�����
		for (int i = 0; i < list.size(); i++) {
			Book book = (Book) list.get(i);
			model.addRow(new Object[] { book.getId(), book.getName(),
					book.getType(), book.getAuthor(),
					book.getTranslator(), book.getPublisher(),
					book.getPublish_time(), book.getStock(),
					book.getPrice() });
		}
	}

	/** ��Ա����4����ѯ��Ŀ��Ͽ�״̬���ı���¼���Ӧ���� */
	private void cb_query_itemStateChanged(ItemEvent arg0) {
		if (arg0.getStateChange() == ItemEvent.SELECTED) {
			// ����ѡ��ı��ǩ��ʾ����
			lb_query.setText("������" + (String) arg0.getItem() + ":");
			tf_query.setText("");
			// ��ȡ��Ͽ���ѡ������Ŀ����
			for (int i = 0; i < tb_heads.length; i++) {
				if (arg0.getItem().equals(tb_heads[i])) {
					field = fields[i];// �����Ӧ���ֶΣ���Ϊ��ǰ��ѡ�����ֶ�
				}
			}
			list = BookDao.selectBookList();// ���÷�������ȡ����ͼ���б�
			addRowToModel(list);// ���÷�������ͼ���б���ӵ����ģ����
			tf_query.requestFocus();// ���ò�ѯ�����ı��򣬻�ý���
		}
	}

	/** ��Ա����5������ѯ����ť�����¼���Ӧ���� */
	private void bn_query_actionPerformed() {

		switch (field) {
		case "id":
		case "name":
		case "type":
		case "author":
		case "translator":
		case "publisher":
		case "publish_time":
			// ��ȡ�ַ������͵Ĳ�ѯ����
			valueStr = tf_query.getText();
			// ���ð��գ��ֶ������ַ����ֶ�ֵ����ѯͼ���б�ķ�������ѯͼ����Ϣ
			list = BookDao.selectBookList(field, valueStr);
			break;
		case "stock":
			// ��ȡ�������͵Ĳ�ѯ����
			if (tf_query.getText().equals("")) {
				tf_query.setText("0");
			}
			valueInt = new Integer(tf_query.getText()).intValue();
			// ���ð��գ��ֶ����������ֶ�ֵ����ѯͼ���б�ķ�������ѯͼ����Ϣ
			list = BookDao.selectBookList(field, valueInt);
			break;
		case "price":
			// ��ȡʵ�����͵Ĳ�ѯ����
			if (tf_query.getText().equals("")) {
				tf_query.setText("0");
			}
			valueDouble = new Double(tf_query.getText())
					.doubleValue();
			// ���ð��գ��ֶ�����ʵ���ֶ�ֵ����ѯͼ���б�ķ�������ѯͼ����Ϣ
			list = BookDao.selectBookList(field, valueDouble);
			break;
		}
		addRowToModel(list);// ����ѯ��ȡ��ͼ���б���ӵ����ģ����

	}

	/** ���Է����� */
	public static void main(String[] args) {
		new BookQuery();
	}
}
