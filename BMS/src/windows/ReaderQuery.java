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

import data.ReaderDao;
import entity.Reader;

public class ReaderQuery extends PublicJFrame {
	/** ��Ա���� */
	JLabel lb_query;// ������ѯ��ǩ
	JTextField tf_query;// ������ѯ�����ı���
	JComboBox<String> cb_query;// ������ѯ��Ŀ��Ͽ�
	JButton bt_query;// ������ѯ��ť

	private JTable table;// �������
	// ���÷�������ȡ���ж����б�������
	private List<Reader> list = ReaderDao.selectReaderList();
	// ���߱���Ŀ����
	private String[] tb_heads = { "���߱�� ", "�������� ", "��������", "�Ա�",
			"�ɽ�����", "�ɽ�����" };
	// ���߱���Ŀ���ƶ�Ӧ�ı��ֶ�
	private String[] fields = { "id", "name", "type", "sex",
			"max_num", "days_num" };
	// �������߱��ģ��
	private DefaultTableModel model = new DefaultTableModel(
			new Object[][] {}, tb_heads);
	private String field = "id";// ����Ĭ�ϵ�ǰ��ѯ�ֶ�
	String valueStr = "";// �����ַ������Ͳ�ѯ�ֶε��ֶ�ֵ
	int valueInt = 0;// �������Ͳ�ѯ�ֶε��ֶ�ֵ

	/** ���췽���������߲�ѯ�������ʼ������ */
	ReaderQuery() {
		// ���ý���
		this.setTitle("--���߲�ѯ-- ");
		this.setVisible(true);
		this.setSize(1000, 500);
		this.setLocationRelativeTo(null);// ���ý������
		// ���������á���ѯ��塱
		JPanel queryPanel = createQueryPanel();
		this.add(queryPanel, BorderLayout.NORTH);
		// ���������á������塱
		JPanel tablePanel = createTablePanel();
		this.add(tablePanel, BorderLayout.CENTER);

	}

	/** ��Ա����1����������ѯ��塱���� */
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

		lb_query = new JLabel("��������߱��");
		queryPanel.add(lb_query,
				new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.EAST,
						GridBagConstraints.BOTH,
						new Insets(10, 10, 10, 10), 0, 0));

		tf_query = new JTextField();
		tf_query.requestFocus();
		// Ϊ��ѯ�����ı�����ӻس��¼�������
		tf_query.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {
				if (arg0.getKeyChar() == '\n') {
					query_actionPerformed();// ���á���ѯ����ť�¼���Ӧ����
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
				query_actionPerformed();
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

	/** ��Ա����2�������������塱���� */
	private JPanel createTablePanel() {
		// ���������壬���ñ߽粼��
		JPanel tablePanel = new JPanel(new BorderLayout(5, 5));
		JScrollPane scrollPane = new JScrollPane();// �����������
		tablePanel.add(scrollPane);// ����������ӵ����������
		table = new JTable(model);// ������񣬲�����ָ���ı��ģ��
		addRowToModel(list);// �������б���ӵ����ģ����
		scrollPane.setViewportView(table);
		return tablePanel;// ���ء������塱
	}

	/** ��Ա����3������ģ�ͣ���Ӷ����б� */
	private void addRowToModel(List<Reader> list) {
		model.setRowCount(0);// ������ģ���е�����
		for (int i = 0; i < list.size(); i++) {
			Reader reader = (Reader) list.get(i);// �ӱ���б��л�ȡһ�����߶���
			// ��������߶�������ԣ���ӵ����߱��ģ���б�������
			model.addRow(new Object[] { reader.getId(),
					reader.getName(), reader.getType(),
					reader.getSex(), reader.getMax_num(),
					reader.getDays_num() });
		}
	}

	/** ��Ա����4������ѯ��Ͽ�״̬���ı���¼���Ӧ���� */
	private void cb_query_itemStateChanged(ItemEvent arg0) {
		if (arg0.getStateChange() == ItemEvent.SELECTED) {
			// ����ѡ���ֶΣ��ı��ǩ��ʾ����
			lb_query.setText("������" + (String) arg0.getItem() + ":");
			tf_query.setText("");
			// ��ȡ��Ͽ���ѡ������Ŀ����
			for (int i = 0; i < tb_heads.length; i++) {
				if (arg0.getItem().equals(tb_heads[i])) {
					field = fields[i];// �����Ӧ���ֶΣ���Ϊ��ǰ��ѡ�����ֶ�
				}
			}
			list = ReaderDao.selectReaderList();// ��ȡ���ж����б�
			addRowToModel(list);// �������б���ӵ����ģ����
			tf_query.requestFocus();// ���ò�ѯ�����ı��򣬻�ý���
		}
	}

	/** ��Ա����5������ѯ����ť�����¼���Ӧ���� */
	private void query_actionPerformed() {
		// ��ѯ�ֶ��������Ͳ�ͬ��ִ�еĲ�ѯ��䲻ͬ
		switch (field) {
		case "id":
		case "name":
		case "type":
		case "sex":
			valueStr = tf_query.getText();// ��ȡ�ַ������͵Ĳ�ѯ����
			// ���ð��գ��ֶ������ַ����ֶ�ֵ����ѯ�����б�ķ�������ѯ������Ϣ
			list = ReaderDao.selectReaderList(field, valueStr);
			break;
		case "max_num":
		case "days_num":
			if (tf_query.getText().equals("")) {
				valueInt = 0;
			} else {
				valueInt = new Double(tf_query.getText()).intValue();
			}
			// ���ð��գ��ֶ����������ֶ�ֵ����ѯ�����б�ķ�������ѯ������Ϣ
			list = ReaderDao.selectReaderList(field, valueInt);
			break;
		}
		addRowToModel(list);// ����ѯ��ȡ�Ķ����б���ӵ����ģ����
	}

	/** ���Է����� ���ڡ����߲�ѯ��ģ��Ĳ��� */
	public static void main(String[] args) {
		new ReaderQuery();// ���������߲�ѯ������
	}
}
