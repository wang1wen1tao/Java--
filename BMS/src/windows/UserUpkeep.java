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
	/** ��Ա���� */
	private JTextField tf_id;// �����û�����ı���
	private JTextField tf_name;// �����û����ı���
	private JPasswordField pf_pass;// �������������
	private JComboBox<String> jc_isAdmin;// ��������Ȩ����Ͽ�

	// ������ӡ��޸ġ�ȡ�����رա�ɾ������հ�ť
	private JButton jb_insert, jb_update, jb_cancel, jb_close, jb_delete, jb_empty;
	private JTable table;// �������
	// �����û����ģ��
	private DefaultTableModel model = new DefaultTableModel(new Object[][] {},
			new String[] { "���", "����", "����", "����ԱȨ��" });
	// �����û��б������������û�����
	private List<User> list = UserDao.selectUserList();

	/** ���췽�������ڳ�ʼ������ */
	UserUpkeep() {
		// ��1���� ���ý���
		this.setTitle("--�û���Ϣά��--");
		setBounds(220, 100, 800, 400);
		this.setResizable(false);

		// ��2���� ����������һ�����Ի���塱������������Ϊ��ܵ�������塣���Ͻ�����һ���ⲿ�ָ����
		JPanel dialogPane = new JPanel();// �������Ի���塱
		dialogPane.setBorder(new EmptyBorder(5, 5, 5, 5));// ���ñ�Ե�հ״�С
		dialogPane.setBackground(new Color(110, 212, 103));// ���ý��汳��
		dialogPane.setLayout(new BorderLayout());// ���ý��沼��Ϊ�߽粼��
		setContentPane(dialogPane);// �����Ի���塱����Ϊ��ܵ�����ģ��

		// ��3��������������һ�����ⲿ�ָ���塱��ˮƽ���ҷ�����������ϣ���⽫����һ���ڲ��ָ���壬�Ҳ⽫������һ��������
		JSplitPane outerPane = new JSplitPane();// �������ⲿ�ָ���塱
		outerPane.setOpaque(false);// ���á��ⲿ�ָ���塱͸��
		outerPane.setResizeWeight(0.1);// ���á��ⲿ�ָ���塱��ȷ���Ȩ��
		outerPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);// ���á��ⲿ�ָ���塱Ϊˮƽ����
		outerPane.setOneTouchExpandable(true);// ���á��ⲿ�ָ���塱������һ��������չ
		dialogPane.add(outerPane, BorderLayout.CENTER);// �����ⲿ�ָ���塱��ӵ����Ի���塱������

		// ��4��������������һ���ڲ��ָ���壬��ֱ���·�����������Ϸ�������һ��������壬�·�������һ����ť���
		JSplitPane innerPane = new JSplitPane();// �������ڲ��ָ���塱
		innerPane.setOpaque(false);// ���á��ڲ��ָ���塱͸��
		// Ϊ���ڲ��ָ���塱���ñ߿�˵��
		innerPane.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
						"�û���Ϣά��", TitledBorder.LEADING, TitledBorder.TOP,
						new Font("΢���ź�", Font.PLAIN, 14), new Color(59, 59, 59)));
		innerPane.setResizeWeight(0.8); // ���á��ڲ��ָ���塱�߶ȷ���Ȩ��
		innerPane.setOrientation(JSplitPane.VERTICAL_SPLIT);// ���á��ڲ��ָ���塱Ϊ��ֱ����
		outerPane.setLeftComponent(innerPane);// �����ڲ��ָ���塱�����ڡ��ⲿ�ָ���塱�����

		// ��5�������������á�������塱
			// ���ô�����������塱������������������塱��������������塱�������ڡ��ڲ��ָ����"���Ϸ�
		innerPane.setLeftComponent(createDataPanel());
		// ��6�������������á���ť��塱
			// ���ô���"��ť���"����������"��ť���"���� ��"��ť���"������"�ڲ��ָ����"���·�
		innerPane.setRightComponent(createButtonPanel());
		// ��7�������������á������塱
			// ���ô���"�����塱�����������������塱�������������塱�������ڡ��ⲿ�ָ���塱���Ҳ�
		outerPane.setRightComponent(createTablePanel());
	}

	/** ��Ա����1�����ڴ�����������塱�ķ��� */
	private JPanel createDataPanel() {
		//��1�������������á�������塱
		JPanel dataPanel = new JPanel(null);//������������塱

		dataPanel.setBorder(new EmptyBorder(5, 5, 5, 10));//���á�������塱�ı�Ե�հ״�С
		dataPanel.setOpaque(false);//���á�������塱͸��
		//��2�������������á�������塱�ϵĸ������������ӵ���������塱��
		JLabel jl_id = new JLabel("���:");//�������û���š���ǩ
		jl_id.setBounds(50, 20, 100, 25);//���á��û���š���ǩ��λ�ü���С
		dataPanel.add(jl_id);//�����û���š���ǩ��ӵ���������塱��

			// �������û���š��ı��򣬲�����user����û���������ʾ��ʼ����
		if (list.size() == 0) {
			tf_id = new JTextField(String.valueOf(1));
		} else {
			tf_id = new JTextField(String.valueOf(list.size() + 1));
		}

		tf_id.addFocusListener(new FocusAdapter() {// Ϊ���û���š��ı������ʧȥ���������
			public void focusLost(FocusEvent e) {
				tf_id_focusLost();// ���á��û���š��ı����ʧȥ�����¼���Ӧ����
			}
		});
		tf_id.setBounds(130, 20, 150, 25);
		tf_id.setToolTipText("���������û����");// ���á��û���š��ı�����ʾ��Ϣ
		dataPanel.add(tf_id);

		JLabel jl_name = new JLabel("�û���:");//�������û�������ǩ
		jl_name.setBounds(50, 60, 100, 25);
		dataPanel.add(jl_name);

		tf_name = new JTextField(10);//�������û������ı���
		tf_name.setBounds(130, 60, 150, 25);
		tf_name.setToolTipText("���������û���");
		dataPanel.add(tf_name);
		tf_name.addFocusListener(new FocusAdapter() {// Ϊ���û������ı������ʧȥ���������
			public void focusLost(FocusEvent e) {
				tf_name_focusLost();// ���á��û������ı����ʧȥ�����¼���Ӧ����
			}
		});

		JLabel jl_pass1 = new JLabel("����:");//���������롱��ǩ
		jl_pass1.setBounds(50, 100, 100, 25);
		dataPanel.add(jl_pass1);

		pf_pass = new JPasswordField(10);//���������롱�����
		pf_pass.setBounds(130, 100, 150, 25);
		pf_pass.setToolTipText("���������û�����");
		dataPanel.add(pf_pass);

		JLabel jl_isAdmin = new JLabel("����Ȩ�ޣ�");//�������û�Ȩ�ޡ���ǩ
		jl_isAdmin.setBounds(50, 140, 100, 25);
		dataPanel.add(jl_isAdmin);

		String[] admin = new String[] { "����Ա", "����Ա", "һ���û�" };
		jc_isAdmin = new JComboBox<String>(admin);//�������û�Ȩ�ޡ���Ͽ򣬲��ṩѡ����
		jc_isAdmin.setBounds(130, 140, 150, 25);
		dataPanel.add(jc_isAdmin);
		//��3�������ء�������塱
		return dataPanel;
	}

	/** ��Ա����2�����ڴ�������ť��塱�ķ��� */
	private JPanel createButtonPanel() {
		//��1�������������á���ť��塱
		JPanel buttonPanel = new JPanel(new GridBagLayout());//��������ť��塱�����������������
		buttonPanel.setOpaque(false);
		((GridBagLayout) buttonPanel.getLayout()).columnWidths = new int[] { 0, 60, 60,
				60, 60, 60, 0 };// ������������ָ��п��
		((GridBagLayout) buttonPanel.getLayout()).columnWeights = new double[] { 0.5, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.5 };//������������ָ��еĿ��Ȩ��
		//��2�������������á���ť��塱�ϵĸ�����ť���������ӵ����¼�������
		jb_insert = new JButton("���");//��������ӡ���ť
		jb_insert.addActionListener(new ActionListener() {//Ϊ����ӡ���ť��ӵ����¼�������
			public void actionPerformed(ActionEvent e) {
				jb_insert_actionPerformed();//���á���ӡ���ť�¼���Ӧ������������û�
			}
		});
		//������ӡ���ť��ӵ�����ť��塱
		buttonPanel.add(jb_insert,
				new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));

		jb_update = new JButton("�޸�");//�������޸ġ���ť
		jb_update.addActionListener(new ActionListener() {//Ϊ���޸ġ���ť��ӵ����¼�������
			public void actionPerformed(ActionEvent e) {
				jb_update_actionPerformed();// ���á��޸�"��ť�¼���Ӧ�������޸��û���Ϣ
			}
		});
		//�����޸ġ���ť��ӵ�����ť��塱
		buttonPanel.add(jb_update,
				new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));

		jb_delete = new JButton("ɾ��");//������ɾ������ť
		jb_delete.addActionListener(new ActionListener() {// Ϊɾ����ť��ӵ����¼�������
			public void actionPerformed(ActionEvent e) {
				jb_delete_actionPerformed();// ����ɾ����ť�¼���Ӧ������ɾ���û�
			}
		});
		buttonPanel.add(jb_delete,
				new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));

		jb_cancel = new JButton("ȡ��");//������ȡ������ť
		jb_cancel.addActionListener(new ActionListener() {// Ϊȡ����ť��ӵ����¼�������
			public void actionPerformed(ActionEvent e) {
				del_content();// �������"������塱���ݷ����������������е�����
			}
		});
		buttonPanel.add(jb_cancel,
				new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));

		jb_close = new JButton("�ر�");//�������رա���ť
		jb_close.addActionListener(new ActionListener() {// Ϊ�رհ�ť��ӵ����¼�������
			public void actionPerformed(ActionEvent e) {
				dispose();// �رա��û�ά�����桱
			}
		});
		buttonPanel.add(jb_close,
				new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));

		jb_empty = new JButton("��������û�");//����������������ݡ���ť
		jb_empty.addActionListener(new ActionListener() {// Ϊ��հ�ť��ӵ����¼�������
			public void actionPerformed(ActionEvent e) {
				jb_empty_actionPerformed();// ������հ�ť�¼���Ӧ��������������û���Ϣ
			}
		});
		buttonPanel.add(jb_empty,
				new GridBagConstraints(2, 1, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(25, 0, 0, 0), 0, 0));
		//��3�������ء���ť��塱
		return buttonPanel;
	}

	/** ��Ա����3�����ڴ���������ķ��� */
	private JPanel createTablePanel() {
		//��1�������������á������塱
		JPanel tablePanel = new JPanel(new BorderLayout(5, 5));// ���������壬���ñ߽粼��
		JScrollPane scrollPane = new JScrollPane();//������������塱
		tablePanel.add(scrollPane);// ��'�������"��ӵ���"������"��
		//��2�������������á���񡱣���Ϊ��������굥���¼�������
		table = new JTable(model);//����ָ���ı��ģ�����������
		for (int i = 0; i < list.size(); i++) {// ��user���е��û���ӵ����ģ����
			User user = (User) list.get(i);
			model.addRow(new Object[] { user.getId(), user.getName(), user.getPass(),
					user.getIs_admin() });
		}
		table.addMouseListener(new MouseAdapter() {//Ϊ����������¼�������
			public void mouseClicked(MouseEvent e) {
				table_mouseClicked();//���ñ����굥���¼���Ӧ����������ࡰ������塱��ʾѡ�е��û���Ϣ
			}
		});
		scrollPane.setViewportView(table);// �������������'�������"���Ӵ�����ʾ
		//��3�������ء������塱
		return tablePanel;
	}

	/** ��Ա����4��������ա�������塱�е����� */
	public void del_content() {
		//���������û�����ı����е����ݣ�����user�����û�������������
		tf_id.setText(String.valueOf(list.size() + 1));
		// ����������塱�������ı������������������Ϊ��
		tf_name.setText("");
		pf_pass.setText("");
		jc_isAdmin.setSelectedItem("����Ա");// ����Ͽ����������Ϊ������Ա��
		tf_id.setEditable(true);// �����û���š��ı������ÿ���
	}

	/** ��Ա����5���û�����ı���ʧȥ�����¼���Ӧ���� */
	public void tf_id_focusLost() {
		User user = UserDao.getUser(Integer.parseInt(tf_id.getText().trim()));// ͨ���û���ţ���ȡһ���û�����
		if (user != null) {// �ж��û������Ƿ���ڣ������ڣ�������ݣ����������û����
			JOptionPane.showMessageDialog(null, "�û�����Ѿ����ڣ������������û���ţ�");
			del_content(); // ������ա�������塱��������ա�������塱�е�����
		}
	}

	/** ��Ա����6���û����ı���ʧȥ�����¼���Ӧ���� */
	public void tf_name_focusLost() {
		User user = UserDao.getUser(tf_name.getText().trim());// ͨ���û�������ȡ�û�����
		if (user != null) {// �ж��û������Ƿ���ڣ������ڣ�����û������ݣ����������û���
			JOptionPane.showMessageDialog(null, "�û����Ѿ����ڣ������������û�����");
			tf_name.setText("");
		}
	}

	/** ��Ա����7������ˢ�±������ */
	public void refresh() {
		model.setRowCount(0);// ������ģ���е�����
		list = UserDao.selectUserList();// ��ȡ�û������б�
		// ��������ģ�����������
		for (int i = 0; i < list.size(); i++) {
			User user = (User) list.get(i);
			model.addRow(new Object[] { user.getId(), user.getName(), user.getPass(),
					user.getIs_admin() });
		}
		del_content();// ��ա�������塱�е�����

	}

	/** ��Ա����8�������굥���¼���Ӧ���� */
	public void table_mouseClicked() {
		// ��ȡ�б���ѡ���û�����
		User user = (User) list.get(table.getSelectedRow());
		// �ڡ�������塱�����ö�Ӧ������
		tf_id.setText(String.valueOf(user.getId()));//�����û����
		tf_name.setText(user.getName());//�����û���
		pf_pass.setText(user.getPass());//�����û�����
		//�����û�Ȩ��
		if (user.getIs_admin() == 1) {
			jc_isAdmin.setSelectedIndex(0);
		} else {
			if (user.getIs_admin() == 0) {
				jc_isAdmin.setSelectedIndex(1);
			} else {
				jc_isAdmin.setSelectedIndex(2);
			}

		}
		tf_id.setEditable(false);// ���á��û����"�ı��򲻿ɱ༭
	}

	/** ��Ա����9��"���"��ť�¼���Ӧ����������������û� */
	public void jb_insert_actionPerformed() {
		// ��1��������һ���û�����
		User user = new User();
		// ��2���������û����������ֵ
		user.setId(Integer.parseInt(tf_id.getText().trim()));// �����û�����ı��
		user.setName(tf_name.getText().trim());// �����û�������û���
		user.setPass(new String(pf_pass.getPassword()).trim());// �����û����������
		int is_admin = 2;// ��ȡ���û�Ȩ�ޡ�
		if (jc_isAdmin.getSelectedItem().toString().equals("����Ա")) {
			is_admin = 1;
		} else {
			if (jc_isAdmin.getSelectedItem().toString().equals("����Ա")) {
				is_admin = 0;
			}
		}
		user.setIs_admin((byte) is_admin);// �����û�����Ĺ���Ȩ��
		// ��3�����ж��û����������Ƿ�Ϊ�գ�����ִ����Ӳ������ж���Ӳ����Ƿ�ɹ����ɹ�������ģ������������ݲ�ˢ��
		if (tf_name.getText().trim().equals("")
				|| new String(pf_pass.getPassword()).trim().equals("")) {
			//���գ��������ʾ��������
			JOptionPane.showMessageDialog(null, "�û���Ϣ����Ϊ�գ�");
			return;
		} else {// �����գ���ִ�С���ӡ��û��������
			int i = UserDao.insertUser(user);// ִ����Ӳ���
			if (i == 1) {// �ж���Ӳ����Ƿ�ɹ������ɹ���������ģ�������������
				model.addRow(new Object[] { user.getId(), user.getName(), user.getPass(),
						user.getIs_admin() });
				refresh();// ˢ�¡������塱����
			}
			del_content();// ��ա�������塱�е�����
		}
	}

	/** ��Ա����10�����޸ġ���ť�¼���Ӧ�����������޸��û���Ϣ */
	public void jb_update_actionPerformed() {
		User user = new User();
		user.setId(Integer.parseInt(tf_id.getText().trim()));
		user.setName(tf_name.getText().trim());
		user.setPass(new String(pf_pass.getPassword()).trim());
		int is_admin = 2;// ��ȡ���û�Ȩ�ޡ�
		if (jc_isAdmin.getSelectedItem().toString().equals("����Ա")) {
			is_admin = 1;
		} else {
			if (jc_isAdmin.getSelectedItem().toString().equals("����Ա")) {
				is_admin = 0;
			}
		}
		user.setIs_admin((byte) is_admin);
		if (tf_name.getText().trim().equals("")
				|| new String(pf_pass.getPassword()).trim().equals("")) {
			JOptionPane.showMessageDialog(null, "�û���Ϣ����Ϊ�գ�");
			return;
		} else {
			int i = UserDao.updateUser(user);// ִ�и��²���
			if (i == 1) {
				JOptionPane.showMessageDialog(null, "�û���Ϣ�޸ĳɹ���");
			}
			refresh();// ˢ�¡������塱����
			del_content();// ��ա�������塱�е�����
		}
	}

	/** ��Ա����11����ɾ������ť�¼���Ӧ����������ɾ���û� */
	public void jb_delete_actionPerformed() {
		int id = Integer.parseInt(tf_id.getText()); // ��ȡ�û����
		int m = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫɾ�������û���Ϣ��?", "ɾ���û���Ϣ",
				JOptionPane.YES_NO_OPTION); // ����ȷ�϶Ի���ѯ���Ƿ�ɾ���û���
		if (m == JOptionPane.YES_OPTION) {// ���ȷ������ִ��ɾ������
			if (UserDao.deleteUser(id) == 1) {// ִ���û�ɾ������
				refresh();// ˢ�¡������塱����
				JOptionPane.showMessageDialog(null, "�û���Ϣɾ���ɹ���");
			}
			del_content();//  ��ա�������塱�е�����
		}
	}

	/** ��Ա����12����ɾ�������û�����ť�¼���Ӧ����������ɾ��ȫ���û���Ϣ */
	public void jb_empty_actionPerformed() {
		int m = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫɾ��ȫ���û���?", "����û�",
				JOptionPane.YES_NO_OPTION); // ����ȷ�϶Ի���ѯ���Ƿ�ɾ�������û���
		if (m == JOptionPane.YES_OPTION) {// ���ȷ�������ٴε���ȷ�϶Ի���ѯ���Ƿ�ɾ�������û���
			int n = JOptionPane.showConfirmDialog(null, "ȷ��Ҫ���ȫ���û���?", "����û��ٴ�ȷ��",
					JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {// ���ȷ������ִ�����user�����
				UserDao.emptyUser();// ִ�����user���ݱ����
				refresh();// ˢ�¡������塱����
				del_content();//  ��ա�������塱�е�����
			}
		}
	}

	// ���Է����������û�ά���Ĳ���
	public static void main(String[] args) {
		new UserUpkeep();// ����UserUpkeep���һ����������
	}
}
