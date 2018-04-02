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
	/** ��Ա���� */
	private JTextField tf_id;// �������߱���ı���
	private JTextField tf_name;// �����������ı���
	private JComboBox<String> cb_type;// ��������������Ͽ�
	private JComboBox<String> cb_sex;// ���������Ա���Ͽ�
	private JTextField tf_max_num;// �������������������ı���
	private JTextField tf_days_num;// �������������������ı���
	// ������ӡ��޸ġ�ȡ�����رա�ɾ������հ�ť
	private JButton jb_insert, jb_update, jb_cancel, jb_close,
			jb_delete, jb_empty;
	private JTable table;// �������
	private DefaultTableModel model = new DefaultTableModel(
			new Object[][] {}, new String[] { "���", "����", "��������",
					"�Ա�", "����������", "����������" });// �������߱��ģ��
	private List<Reader> list = ReaderDao.selectReaderList();// �������ж����б���������߶���

	/** ���췽�������ڳ�ʼ������ */
	ReaderUpkeep() {
		// ��������
		this.setTitle("--������Ϣά��--");
		setBounds(220, 100, 1000, 500);
		this.setResizable(false);

		// ����һ�����Ի���塱,���Ϸ���һ�����ⲿ�ָ���塱������������Ϊ��ܵ��������
		JPanel dialogPane = new JPanel();
		dialogPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		dialogPane.setBackground(new Color(110, 212, 103));
		dialogPane.setLayout(new BorderLayout());
		setContentPane(dialogPane);

		/**
		 * ����һ�����ⲿ�ָ���塱��ˮƽ���ҷ�������� ���У�������һ�����ڲ��ָ���塱���Ҳ����һ���������塱
		 */
		JSplitPane outerPane = new JSplitPane();// �������ⲿ�ָ���塱
		outerPane.setOpaque(false);// ���á��ⲿ�ָ���塱͸��
		outerPane.setResizeWeight(0.1);// ���á��ⲿ�ָ��桱���ȷ���Ȩ��
		outerPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);// ����"�ⲿ�ָ����"Ϊˮƽ����
		outerPane.setOneTouchExpandable(true);// ����"�ⲿ�ָ����"������һ��������չ
		dialogPane.add(outerPane, BorderLayout.CENTER);
		// ����һ��"�ڲ��ָ����"����ֱ���·�����������У��Ϸ�����һ��"�������"���·�����һ��"��ť���"
		JSplitPane innerPane = new JSplitPane();// ����"�ڲ��ָ����"
		innerPane.setOpaque(false);
		// Ϊһ�����ڲ��ָ���塱���ñ߿�˵��
		innerPane.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"������Ϣά��", TitledBorder.LEADING, TitledBorder.TOP,
				new Font("΢���ź�", Font.PLAIN, 14),
				new Color(59, 59, 59)));
		innerPane.setResizeWeight(0.8);
		innerPane.setOrientation(JSplitPane.VERTICAL_SPLIT);// ���á��ڲ��ָ���塱Ϊ��ֱ����
		outerPane.setLeftComponent(innerPane);// �����ڲ��ָ���塱�����ڡ��ⲿ�ָ���塱�����
		JPanel dataPanel = createDataPanel();// ���ô�����������塱������������������塱
		innerPane.setLeftComponent(dataPanel);// ����������塱�������ڡ��ڲ��ָ���塱�����
		JPanel buttonPanel = createButtonPanel();// ���ô�������ť��塱��������������ť��塱
		innerPane.setRightComponent(buttonPanel);// ������ť��塱�����ڡ��ڲ��ָ���塱���·�
		JPanel tablePanel = createTablePanel();// ���ô����������塱�����������������塱
		outerPane.setRightComponent(tablePanel);// ���������塱�������ڡ��ⲿ�ָ���塱���Ҳ�
	}

	/** ��Ա����1�����ڴ�����������塱�ķ��� */
	private JPanel createDataPanel() {
		JPanel dataPanel = new JPanel(null);
		dataPanel.setBorder(new EmptyBorder(5, 5, 5, 10));
		dataPanel.setOpaque(false);

		JLabel jl_id = new JLabel("���:");
		jl_id.setBounds(50, 20, 100, 25);
		dataPanel.add(jl_id);

		tf_id = new JTextField();
		tf_id.setBounds(160, 20, 150, 25);

		tf_id.setToolTipText("����������߱��");
		tf_id.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				tf_id_focusLost();// ���ö��߱���ı����ʧȥ�����¼���Ӧ����
			}
		});
		dataPanel.add(tf_id);

		JLabel jl_name = new JLabel("��������:");
		jl_name.setBounds(50, 60, 100, 25);
		dataPanel.add(jl_name);

		tf_name = new JTextField(10);
		tf_name.setBounds(160, 60, 150, 25);
		tf_name.setToolTipText("�������������");
		dataPanel.add(tf_name);

		JLabel jl_type = new JLabel("��������:");
		jl_type.setBounds(50, 100, 150, 25);
		dataPanel.add(jl_type);

		cb_type = new JComboBox<String>(Constant.READER_TYPES);
		cb_type.setBounds(160, 100, 150, 25);
		dataPanel.add(cb_type);

		JLabel jl_sex = new JLabel("�Ա�");
		jl_sex.setBounds(50, 140, 150, 25);
		dataPanel.add(jl_sex);

		cb_sex = new JComboBox<String>(Constant.SEX);
		cb_sex.setBounds(160, 140, 150, 25);
		dataPanel.add(cb_sex);

		JLabel jl_max_num = new JLabel("����������:");
		jl_max_num.setBounds(50, 180, 150, 25);
		dataPanel.add(jl_max_num);

		tf_max_num = new JTextField(10);
		tf_max_num.setBounds(160, 180, 150, 25);
		dataPanel.add(tf_max_num);

		JLabel jl_days_num = new JLabel("����������:");
		jl_days_num.setBounds(50, 220, 150, 25);
		dataPanel.add(jl_days_num);

		tf_days_num = new JTextField(10);
		tf_days_num.setBounds(160, 220, 150, 25);
		dataPanel.add(tf_days_num);

		return dataPanel;
	}

	/** ��Ա����2�����ڴ�������ť��塱�ķ��� */
	private JPanel createButtonPanel() {
		// ��������ť��塱�����������������
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		buttonPanel.setOpaque(false);
		((GridBagLayout) buttonPanel
				.getLayout()).columnWidths = new int[] { 0, 60, 60,
						60, 60, 60, 0 };// ������������ָ��п��
		((GridBagLayout) buttonPanel
				.getLayout()).columnWeights = new double[] { 0.5, 0.0,
						0.0, 0.0, 0.0, 0.0, 0.5 };// ������������ָ��еĿ��Ȩ��

		jb_insert = new JButton("���");
		jb_insert.addActionListener(new ActionListener() {// Ϊ��Ӱ�ť��ӵ����¼�������
			public void actionPerformed(ActionEvent e) {
				jb_insert_actionPerformed();// ���á���ӡ���ť�¼���Ӧ������������û�
			}
		});
		buttonPanel.add(jb_insert,
				new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER,
						GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 5), 0, 0));

		jb_update = new JButton("�޸�");
		jb_update.addActionListener(new ActionListener() {// Ϊ�޸İ�ť��ӵ����¼�������
			public void actionPerformed(ActionEvent e) {
				jb_update_actionPerformed();// ���á��޸ġ���ť�¼���Ӧ�������޸��û���Ϣ
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
				jb_delete_actionPerformed();// ���á�ɾ������ť�¼���Ӧ������ɾ���û�����
			}
		});
		buttonPanel.add(jb_delete,
				new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER,
						GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 5), 0, 0));

		jb_cancel = new JButton("ȡ��");
		jb_cancel.addActionListener(new ActionListener() {// Ϊȡ����ť��ӵ����¼�������
			public void actionPerformed(ActionEvent e) {
				del_content();// ������ա�������塱�����������������е�����
			}
		});
		buttonPanel.add(jb_cancel,
				new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER,
						GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 5), 0, 0));

		jb_close = new JButton("�ر�");
		jb_close.addActionListener(new ActionListener() {// Ϊ�رհ�ť��ӵ����¼�������
			public void actionPerformed(ActionEvent e) {
				dispose();// �ر��û�ά������
			}
		});
		buttonPanel.add(jb_close,
				new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER,
						GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 5), 0, 0));

		jb_empty = new JButton("������ж���");
		jb_empty.addActionListener(new ActionListener() {// Ϊ��հ�ť��ӵ����¼�������
			public void actionPerformed(ActionEvent e) {
				jb_empty_actionPerformed();// ���á�������ж��ߡ���ť�¼���Ӧ������������ж�����Ϣ
			}
		});
		buttonPanel.add(jb_empty,
				new GridBagConstraints(2, 1, 3, 1, 0.0, 0.0,
						GridBagConstraints.CENTER,
						GridBagConstraints.BOTH,
						new Insets(25, 0, 0, 0), 0, 0));
		return buttonPanel;
	}

	/** ��Ա����3�����ڴ����������塱�ķ��� */
	private JPanel createTablePanel() {
		JPanel tablePanel = new JPanel(new BorderLayout(5, 5));// ���������壬���ñ߽粼��
		JScrollPane scrollPane = new JScrollPane();// �����������
		tablePanel.add(scrollPane);// ����������ӵ����������
		table = new JTable(model);// ������񣬲�����ָ���ı��ģ��

		for (int i = 0; i < list.size(); i++) {
			Reader reader = (Reader) list.get(i);
			model.addRow(new Object[] { reader.getId(),
					reader.getName(), reader.getType(),
					reader.getSex(), reader.getMax_num(),
					reader.getDays_num() });
		}
		table.addMouseListener(new MouseAdapter() {// Ϊ����������¼�������
			public void mouseClicked(MouseEvent e) {
				table_mouseClicked();// ���ñ����굥���¼���Ӧ��������������������ʾѡ�ж��ߵ���Ϣ
			}
		});
		scrollPane.setViewportView(table);
		return tablePanel;
	}

	/** ��Ա����4��������ա�������塱�е����� */
	public void del_content() {
		tf_id.setText("");
		tf_id.setEditable(true);
		tf_name.setText("");
		cb_type.setSelectedIndex(0);
		cb_sex.setSelectedItem("����Ա");
		tf_max_num.setText("");
		tf_days_num.setText("");

	}

	/** ��Ա����5�����߱���ı���ʧȥ�����¼���Ӧ���� */
	public void tf_id_focusLost() {
		Reader reader = ReaderDao
				.getReaderById(tf_id.getText().trim());// ͨ�����߱�ţ���ȡ���߶���
		if (reader != null) {// �ж϶��߶����Ƿ���ڣ������ڣ�������ݣ�����������߱��
			JOptionPane.showMessageDialog(null,
					"���߱���Ѿ����ڣ�������������߱�ţ�");
			del_content(); // �����������е�����
		}
	}

	/** ��Ա����6�����ڸ��±������ */
	public void refresh() {
		model.setRowCount(0);// ������ģ���е�����
		list = ReaderDao.selectReaderList();// ��ȡ���߶����б�
		for (int i = 0; i < list.size(); i++) {// ��������ģ�����������
			Reader reader = (Reader) list.get(i);
			model.addRow(new Object[] { reader.getId(),
					reader.getName(), reader.getType(),
					reader.getSex(), reader.getMax_num(),
					reader.getDays_num() });
		}
		del_content();// �����������е�����

	}

	/** ��Ա����7�������굥���¼���Ӧ���� */
	public void table_mouseClicked() {
		// ��ȡѡ�ж��ߵ���Ϣ
		Reader reader_old = (Reader) list.get(table.getSelectedRow());
		// ��������������ö�Ӧ������
		tf_id.setText(reader_old.getId());
		tf_name.setText(reader_old.getName());
		cb_type.setSelectedItem(reader_old.getType());
		cb_sex.setSelectedItem(reader_old.getSex());
		tf_max_num.setText(String.valueOf(reader_old.getMax_num()));
		tf_days_num.setText(String.valueOf(reader_old.getDays_num()));

		tf_id.setEditable(false);// ���ö��߱���ı��򲻿ɱ༭
	}

	/** ��Ա����8������ӡ��¶��߷��� */
	public void jb_insert_actionPerformed() {
		Reader reader = new Reader();// ����һ�����߶���
		// ���ö��߶��������ֵ
		reader.setId(tf_id.getText().trim());
		reader.setName(tf_name.getText().trim());
		reader.setType(cb_type.getSelectedItem().toString());
		reader.setSex(cb_sex.getSelectedItem().toString());
		reader.setMax_num(new Integer(tf_max_num.getText().trim()));
		reader.setDays_num(new Integer(tf_days_num.getText().trim()));

		// �ж϶���������߱�Ų�����Ϊ�գ����գ��������ʾ��������
		if (tf_name.getText().trim().equals("")
				|| tf_id.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "������Ϣ����Ϊ�գ�");
			return;
		} else {// �����գ���ִ����Ӷ��߶������
			int i = ReaderDao.insertReader(reader);// ִ����Ӳ���
			if (i == 1) {// �ж���Ӳ����Ƿ�ɹ������ɹ���������ģ�������������
				model.addRow(new Object[] { reader.getId(),
						reader.getName(), reader.getType(),
						reader.getSex(), reader.getMax_num(),
						reader.getDays_num() });
				refresh();
			}
			del_content();
		}
	}

	/** ��Ա����9�����޸ġ�������Ϣ��ť�¼���Ӧ���� */
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
			JOptionPane.showMessageDialog(null, "������Ϣ����Ϊ�գ�");
			return;
		} else {// �����գ���ִ����Ӷ��߶������
			int i = ReaderDao.updateReader(reader);
			if (i == 1) {
				JOptionPane.showMessageDialog(null, "������Ϣ�޸ĳɹ���");
			}
			refresh();// ���±������
			del_content();
		}
	}

	/** ��Ա����10����ɾ����������Ϣ */
	public void jb_delete_actionPerformed() {
		String id = tf_id.getText().trim(); // ��ȡ���߱��
		int m = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫɾ������������Ϣ��?",
				"ɾ��������Ϣ", JOptionPane.YES_NO_OPTION); // ����ȷ�϶Ի���ѯ���Ƿ�ɾ�����ߣ�
		if (m == JOptionPane.YES_OPTION) {// ���ȷ������ִ��ɾ������
			if (ReaderDao.deleteReader(id) == 1) {// ִ�ж���ɾ������
				refresh();// ���±������
				JOptionPane.showMessageDialog(null, "������Ϣɾ���ɹ���");
			}
			del_content();// ��������������
		}
	}

	/** ��Ա����11��ɾ�����ж�����Ϣ */
	public void jb_empty_actionPerformed() {
		int m = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫɾ�����ж�����Ϣ��?",
				"��ն�����Ϣ", JOptionPane.YES_NO_OPTION);
		if (m == JOptionPane.YES_OPTION) {
			int n = JOptionPane.showConfirmDialog(null,
					"���ȷ��������ж���������?", "��ն�����Ϣ�ٴ�ȷ��",
					JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				ReaderDao.emptyReader();
				refresh();// ���±������
				del_content();// ��������������
			}
		}
	}

	// ���Է��������ڶ���ά���Ĳ���
	public static void main(String[] args) {
		new ReaderUpkeep();
	}
}
