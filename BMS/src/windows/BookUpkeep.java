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
import data.BookDao;
import entity.Book;

public class BookUpkeep extends PublicJFrame {
	/** ��Ա���� */
	private JTextField tf_id;// ����ͼ�����ı���
	private JTextField tf_name;// ���������ı���
	private JComboBox<String> cb_type;// ����ͼ��������Ͽ�
	private JTextField tf_author;// ���������ı���
	private JTextField tf_translator;// ���������ı���
	private JTextField tf_publisher;// �����������ı���
	private JTextField tf_publish_time;// ��������ʱ���ı���
	private JTextField tf_stock;// ����ͼ�������ı���
	private JTextField tf_price;// ����ͼ��۸��ı���

	// ������ӡ��޸ġ�ȡ�����رա�ɾ������հ�ť
	private JButton jb_insert, jb_update, jb_cancel, jb_close,
			jb_delete, jb_empty;
	private JTable table;// �������
	// ����ͼ����ģ��
	private DefaultTableModel model = new DefaultTableModel(
			new Object[][] {}, new String[] { "���", "����", "ͼ������",
					"����", "����", "������", "����ʱ��", "ͼ����", "�۸�" });
	private List<Book> list = BookDao.selectBookList();// ��������ͼ���б�������ͼ�����

	/** ���췽�������ڳ�ʼ������ */
	BookUpkeep() {
		// ��������
		this.setTitle("--ͼ����Ϣά��--");
		setBounds(220, 100, 1300, 650);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		// ����һ���Ի����,���Ϸ���һ���ⲿ�ָ���壬����������Ϊ��ܵ��������
		JPanel dialogPane = new JPanel();
		dialogPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialogPane.setBackground(new Color(110, 212, 103));
		dialogPane.setLayout(new BorderLayout());
		setContentPane(dialogPane);

		// ����һ���ⲿ�ָ���壬ˮƽ���ҷ�����������У�������һ���ڲ��ָ���壬�Ҳ����һ��������
		JSplitPane outerPane = new JSplitPane();// �����ⲿ�ָ����
		outerPane.setOpaque(false);// �����ⲿ�ָ����͸��
		outerPane.setResizeWeight(0.1);// �����ⲿ�ָ�����ȷ���Ȩ��
		outerPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);//�����ⲿ�ָ����ˮƽ����
		outerPane.setOneTouchExpandable(true);//�����ⲿ�ָ���������һ��������չ
		dialogPane.add(outerPane, BorderLayout.CENTER);
		// ����һ���ڲ��ָ���壬��ֱ���·�����������У��Ϸ�����һ��������壬�·�����һ����ť���
		JSplitPane innerPane = new JSplitPane();// �����ڲ��ָ����
		innerPane.setOpaque(false);
		// Ϊһ���ڲ��ָ�������ñ߿�˵��
		innerPane.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"ͼ����Ϣά��", TitledBorder.LEADING, TitledBorder.TOP,
				new Font("΢���ź�", Font.PLAIN, 14),
				new Color(59, 59, 59)));
		innerPane.setResizeWeight(0.8);
		innerPane.setOrientation(JSplitPane.VERTICAL_SPLIT);//�����ڲ��ָ����Ϊ��ֱ����
		outerPane.setLeftComponent(innerPane);// ���ڲ��ָ�����������ⲿ�ָ��������
		JPanel dataPanel = createDataPanel();// ���ô���������巽���������������
		innerPane.setLeftComponent(dataPanel);// ��������壬�������ڲ��ָ��������
		JPanel buttonPanel = createButtonPanel();// ���ô�����ť��巽����������ť���
		innerPane.setRightComponent(buttonPanel);// ����ť����������ڲ��ָ������·�
		JPanel tablePanel = createTablePanel();// ���ô��������巽��������������
		outerPane.setRightComponent(tablePanel);// �������壬�������ⲿ�ָ������Ҳ�
	}

	/** ��Ա����1�����ڴ������������"�ķ��� */
	private JPanel createDataPanel() {
		JPanel dataPanel = new JPanel(null);
		dataPanel.setBorder(new EmptyBorder(5, 5, 5, 10));
		dataPanel.setOpaque(false);

		JLabel jl_id = new JLabel("���:");
		jl_id.setBounds(50, 20, 100, 25);
		dataPanel.add(jl_id);

		tf_id = new JTextField();
		tf_id.setBounds(140, 20, 170, 25);

		tf_id.setToolTipText("��������ͼ����");
		tf_id.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				tf_id_focusLost();// ����ͼ�����ı����ʧȥ�����¼���Ӧ����
			}
		});
		dataPanel.add(tf_id);

		JLabel jl_name = new JLabel("ͼ������:");
		jl_name.setBounds(50, 60, 100, 25);
		dataPanel.add(jl_name);

		tf_name = new JTextField(10);
		tf_name.setBounds(140, 60, 170, 25);
		tf_name.setToolTipText("��������ͼ����");
		dataPanel.add(tf_name);
		tf_name.addFocusListener(new FocusAdapter() {//Ϊͼ�����ı������ʧȥ���������
			public void focusLost(FocusEvent e) {
				tf_name_focusLost();// ����ͼ�����ı����ʧȥ�����¼���Ӧ����
			}
		});

		JLabel jl_type = new JLabel("ͼ������:");
		jl_type.setBounds(50, 100, 150, 25);
		dataPanel.add(jl_type);

		cb_type = new JComboBox<String>(Constant.BOOK_TYPES);
		cb_type.setBounds(140, 100, 170, 25);
		dataPanel.add(cb_type);

		JLabel jl_author = new JLabel("���ߣ�");
		jl_author.setBounds(50, 140, 150, 25);
		dataPanel.add(jl_author);

		tf_author = new JTextField(10);
		tf_author.setBounds(140, 140, 170, 25);
		dataPanel.add(tf_author);

		JLabel jl_translator = new JLabel("����:");
		jl_translator.setBounds(50, 180, 150, 25);
		dataPanel.add(jl_translator);

		tf_translator = new JTextField(10);
		tf_translator.setBounds(140, 180, 170, 25);
		dataPanel.add(tf_translator);

		JLabel jl_publisher = new JLabel("������:");
		jl_publisher.setBounds(50, 220, 150, 25);
		dataPanel.add(jl_publisher);

		tf_publisher = new JTextField(10);
		tf_publisher.setBounds(140, 220, 170, 25);
		dataPanel.add(tf_publisher);

		JLabel jl_publish_time = new JLabel("��������:");
		jl_publish_time.setBounds(50, 260, 150, 25);
		dataPanel.add(jl_publish_time);

		tf_publish_time = new JTextField("2015-01");
		tf_publish_time.setBounds(140, 260, 170, 25);
		dataPanel.add(tf_publish_time);

		JLabel jl_stock = new JLabel("�����:");
		jl_stock.setBounds(50, 300, 150, 25);
		dataPanel.add(jl_stock);

		tf_stock = new JTextField(10);
		tf_stock.setBounds(140, 300, 170, 25);
		dataPanel.add(tf_stock);

		JLabel jl_price = new JLabel("�۸�:");
		jl_price.setBounds(50, 340, 150, 25);
		dataPanel.add(jl_price);

		tf_price = new JTextField(10);
		tf_price.setBounds(140, 340, 170, 25);
		dataPanel.add(tf_price);

		return dataPanel;
	}

	/** ��Ա����2�����ڴ���"��ť���"�ķ��� */
	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel(new GridBagLayout());//������ť��壬�����������
		buttonPanel.setOpaque(false);
		((GridBagLayout) buttonPanel
				.getLayout()).columnWidths = new int[] { 0, 60, 60,
						60, 60, 60, 0 };// ������������ָ��п��
		((GridBagLayout) buttonPanel
				.getLayout()).columnWeights = new double[] { 0.5, 0.0,
						0.0, 0.0, 0.0, 0.0, 0.5 };// ������������ָ��еĿ��Ȩ��

		jb_insert = new JButton("���");
		jb_insert.addActionListener(new ActionListener() {//Ϊ��Ӱ�ť��ӵ����¼�������
			public void actionPerformed(ActionEvent e) {
				jb_insert_actionPerformed();// ������Ӱ�ť�¼���Ӧ���������ͼ��
			}
		});
		buttonPanel.add(jb_insert,
				new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER,
						GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 5), 0, 0));

		jb_update = new JButton("�޸�");
		jb_update.addActionListener(new ActionListener() {//Ϊ�޸İ�ť��ӵ����¼�������
			public void actionPerformed(ActionEvent e) {
				jb_update_actionPerformed();// �����޸İ�ť�¼���Ӧ�������޸�ͼ����Ϣ
			}
		});
		buttonPanel.add(jb_update,
				new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER,
						GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 5), 0, 0));

		jb_delete = new JButton("ɾ��");
		jb_delete.addActionListener(new ActionListener() {// Ϊɾ����ť��ӵ����¼�������
			public void actionPerformed(ActionEvent e) {
				jb_delete_actionPerformed();// ����ɾ����ť�¼���Ӧ������ɾ��ͼ�����
			}
		});
		buttonPanel.add(jb_delete,
				new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER,
						GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 5), 0, 0));

		jb_cancel = new JButton("ȡ��");
		jb_cancel.addActionListener(new ActionListener() {//Ϊȡ����ť��ӵ����¼�������
			public void actionPerformed(ActionEvent e) {
				del_content();// ����ɾ�����ݷ����������������е�����
			}
		});
		buttonPanel.add(jb_cancel,
				new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER,
						GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 5), 0, 0));

		jb_close = new JButton("�ر�");
		jb_close.addActionListener(new ActionListener() {//Ϊ�رհ�ť��ӵ����¼�������
			public void actionPerformed(ActionEvent e) {
				dispose();// �ر�ͼ��ά������
			}
		});
		buttonPanel.add(jb_close,
				new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER,
						GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 5), 0, 0));

		jb_empty = new JButton("�������ͼ��");
		jb_empty.addActionListener(new ActionListener() {//Ϊ��հ�ť��ӵ����¼�������
			public void actionPerformed(ActionEvent e) {
				jb_empty_actionPerformed();// ������հ�ť�¼���Ӧ�������������ͼ��
			}
		});
		buttonPanel.add(jb_empty,
				new GridBagConstraints(2, 1, 3, 1, 0.0, 0.0,
						GridBagConstraints.CENTER,
						GridBagConstraints.BOTH,
						new Insets(25, 0, 0, 0), 0, 0));
		return buttonPanel;
	}

	/** ��Ա����3�����ڴ���������ķ��� */
	private JPanel createTablePanel() {
		// ���������壬���ñ߽粼��
		JPanel tablePanel = new JPanel(new BorderLayout(5, 5));
		JScrollPane scrollPane = new JScrollPane();// �����������
		tablePanel.add(scrollPane);// ����������ӵ����������
		table = new JTable(model);// ������񣬲�����ָ���ı��ģ��

		for (int i = 0; i < list.size(); i++) {
			Book book = (Book) list.get(i);
			model.addRow(new Object[] { book.getId(), book.getName(),
					book.getType(), book.getAuthor(),
					book.getTranslator(), book.getPublisher(),
					book.getPublish_time(), book.getStock(),
					book.getPrice() });
		}
		table.addMouseListener(new MouseAdapter() {// Ϊ����������¼�������
			public void mouseClicked(MouseEvent e) {
				// ���ñ����굥���¼���Ӧ��������������������ʾѡ��ͼ��
				table_mouseClicked();
			}
		});
		scrollPane.setViewportView(table);
		return tablePanel;
	}

	/** ��Ա����4�����������������е����� */
	public void del_content() {
		tf_id.setText("");
		tf_name.setText("");
		cb_type.setSelectedIndex(0);
		tf_author.setText("");
		tf_translator.setText("");
		tf_publisher.setText("");
		tf_publish_time.setText("");
		tf_stock.setText("");
		tf_price.setText("");
		tf_id.setEditable(true);
	}

	/** ��Ա����5��ͼ�����ı���ʧȥ�����¼���Ӧ���巽�� */
	public void tf_id_focusLost() {
		// ͨ��ͼ���ţ���ȡͼ�����
		Book book = BookDao.getBookById(tf_id.getText().trim());
		if (book != null) {// �ж�ͼ������Ƿ���ڣ������ڣ�������ݣ�����������߱��
			JOptionPane.showMessageDialog(null,
					"ͼ�����Ѿ����ڣ�����������ͼ���ţ�");
			del_content(); // �����������е�����
		}
	}

	/** ��Ա����6��ͼ�����ı���ʧȥ�����¼���Ӧ���巽�� */
	public void tf_name_focusLost() {
		Book book = BookDao.getBookByName(tf_name.getText().trim());
		if (book != null) {
			JOptionPane.showMessageDialog(null, "ͼ�����Ѿ����ڣ�����������ͼ������");
			tf_name.setText("");
		}
	}

	/** ��Ա����7�����ڸ��±������ */
	public void refresh() {
		model.setRowCount(0);// ������ģ���е�����
		list = BookDao.selectBookList();// ��ȡͼ������б�
		for (int i = 0; i < list.size(); i++) {// ��������ģ�����������
			Book book = (Book) list.get(i);
			model.addRow(new Object[] { book.getId(), book.getName(),
					book.getType(), book.getAuthor(),
					book.getTranslator(), book.getPublisher(),
					book.getPublish_time(), book.getStock(),
					book.getPrice() });
		}
		del_content();// �����������е�����

	}

	/** ��Ա����8�������굥���¼���Ӧ���� */
	public void table_mouseClicked() {
		// ��ȡѡ��ͼ�����Ϣ
		Book book_old = (Book) list.get(table.getSelectedRow());
		// ��������������ö�Ӧ��ͼ������
		tf_id.setText(book_old.getId());
		tf_name.setText(book_old.getName());
		cb_type.setSelectedItem(book_old.getType());
		tf_author.setText(book_old.getAuthor());
		tf_translator.setText(book_old.getTranslator());
		tf_publisher.setText(book_old.getPublisher());
		tf_publish_time.setText(book_old.getPublish_time());
		tf_stock.setText(String.valueOf(book_old.getStock()));
		tf_price.setText(String.valueOf(book_old.getPrice()));
		tf_id.setEditable(false);// ���ö���ͼ���ı��򲻿ɱ༭
	}

	/** ��Ա����9�������ͼ�鷽�� */
	public void jb_insert_actionPerformed() {
		Book book = new Book();// ����һ��ͼ�����
		// ����ͼ����������ֵ
		book.setId(tf_id.getText().trim());
		book.setName(tf_name.getText().trim());
		book.setType(cb_type.getSelectedItem().toString());
		book.setAuthor(tf_author.getText().trim());

		book.setTranslator(tf_translator.getText().trim());
		book.setPublisher(tf_publisher.getText().trim());
		book.setPublish_time(tf_publish_time.getText().trim());
		book.setStock(new Integer(tf_stock.getText().trim()));
		book.setPrice(new Double(tf_price.getText().trim()));

		// �ж�ͼ������ͼ���Ų�����Ϊ�գ����գ��������ʾ��������
		if (tf_name.getText().trim().equals("")
				|| tf_id.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "ͼ����Ϣ����Ϊ�գ�");
			return;
		} else {// �����գ���ִ�����ͼ��������
			int i = BookDao.insertBook(book);// ִ����Ӳ���
			if (i == 1) {// �ж���Ӳ����Ƿ�ɹ������ɹ���������ģ�������������
				model.addRow(new Object[] { book.getId(),
						book.getName(), book.getType(),
						book.getAuthor(), book.getTranslator(),
						book.getPublisher(), book.getPublish_time(),
						book.getStock(), book.getPrice() });
				refresh();
			}
			del_content();
		}
	}

	/** ��Ա����10���޸�ͼ����Ϣ��ť�¼���Ӧ���� */
	public void jb_update_actionPerformed() {
		Book book = new Book();// ����һ��ͼ�����
		book.setId(tf_id.getText().trim());
		book.setName(tf_name.getText().trim());
		book.setType(cb_type.getSelectedItem().toString());
		book.setAuthor(tf_author.getText().trim());
		book.setTranslator(tf_translator.getText().trim());
		book.setPublisher(tf_publisher.getText().trim());
		book.setPublish_time(tf_publish_time.getText().trim());
		book.setStock(new Integer(tf_stock.getText().trim()));
		book.setPrice(new Double(tf_price.getText().trim()));

		if (tf_name.getText().trim().equals("")
				|| tf_id.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "ͼ����Ϣ����Ϊ�գ�");
			return;
		} else {// �����գ���ִ�����ͼ��������
			int i = BookDao.updateBook(book);
			if (i == 1) {
				JOptionPane.showMessageDialog(null, "ͼ����Ϣ�޸ĳɹ���");
			}

			refresh();// ���±������
			del_content();
		}
	}

	/** ��Ա����11��ɾ��ͼ�� ��Ϣ */
	public void jb_delete_actionPerformed() {
		String id = tf_id.getText().trim(); // ��ȡ�û����
		 // ����ȷ�϶Ի���ѯ���Ƿ�ɾ��ͼ�飿
		int m = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫɾ������ͼ����Ϣ��?",
				"ɾ��ͼ����Ϣ", JOptionPane.YES_NO_OPTION);
		if (m == JOptionPane.YES_OPTION) {// ���ȷ������ִ��ɾ������
			if (BookDao.deleteBook(id) == 1) {// ִ���û�ɾ������
				refresh();// ���±������
				JOptionPane.showMessageDialog(null, "ͼ����Ϣɾ���ɹ���");
			}
			del_content();// ��������������
		}
	}

	/** ��Ա����12��ɾ������ͼ����Ϣ */
	public void jb_empty_actionPerformed() {
		int m = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫɾ������ͼ����Ϣ��?",
				"���ͼ����Ϣ", JOptionPane.YES_NO_OPTION);
		if (m == JOptionPane.YES_OPTION) {
			int n = JOptionPane.showConfirmDialog(null,
					"���ȷ���������ͼ��������?", "���ͼ����Ϣ�ٴ�ȷ��",
					JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				BookDao.emptyBook();
				refresh();// ���±������
				del_content();// ��������������
			}
		}
	}

	// ���Է���������ͼ��ά���Ĳ���
	public static void main(String[] args) {
		new BookUpkeep();
	}
}
