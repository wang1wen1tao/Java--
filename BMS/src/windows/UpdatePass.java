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
	/** ��Ա���� */
	private JPasswordField pf_pass1;// ��ʾ�����������
	private JPasswordField pf_pass2;// ��ʾȷ�����������
	private JButton bt_ok;// ��ʾ�޸����밴ť
	private JButton bt_close;// ��ʾ�رհ�ť

	/** ���췽�������ڡ��޸����롱����ĳ�ʼ�� */
	UpdatePass() {
		// ��1�������á��޸����롱����
		setTitle("--�޸�����--");
		setSize(380, 260);
		setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setLayout(null);
		// ��2�����ڿ�ܵ���������ϣ������� ���ø������
		JLabel lb_pass1 = new JLabel("����������:");
		lb_pass1.setBounds(new Rectangle(100, 50, 70, 25));
		add(lb_pass1);

		pf_pass1 = new JPasswordField();
		pf_pass1.setBounds(new Rectangle(180, 50, 110, 25));
		pf_pass1.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {
				if (arg0.getKeyChar() == '\n') {
					ok_actionPerformed();// ���á���ѯ����ť�¼���Ӧ����
				}
			}
		});
		add(pf_pass1);

		JLabel lb_pass2 = new JLabel("ȷ�����룺");
		lb_pass2.setBounds(new Rectangle(100, 90, 70, 25));
		add(lb_pass2);

		pf_pass2 = new JPasswordField();
		pf_pass2.setBounds(new Rectangle(180, 90, 110, 25));
		add(pf_pass2);

		bt_ok = new JButton("�޸�");
		bt_ok.setBounds(new Rectangle(100, 160, 80, 25));
		// ��3����Ϊ��ť�����ӵ����¼�������
		bt_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ok_actionPerformed();// ���á��޸ġ���ť�¼���Ӧ����
			}
		});
		add(bt_ok);

		bt_close = new JButton("�ر�");
		bt_close.setBounds(new Rectangle(200, 160, 80, 25));
		//��4���� Ϊ���رա���ť��ӵ����¼�������
		bt_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();// �رա��޸����롱����
			}
		});
		add(bt_close);

	}

	/** ��Ա����1�� "�޸�"��ť���¼���Ӧ���� */
	void ok_actionPerformed() {
		// ��1������ȡ�������������
		String pass1 = new String(pf_pass1.getPassword()); // ��ȡ��һ����������
		String pass2 = new String(pf_pass2.getPassword()); // ��ȡ�ڶ�����������
		// ��2�����ж���������������Ƿ�Ϊ�գ�Ϊ�գ���ʾ������Ϊ����Ϣ�����õ�1������ý��㣬Ȼ�󷵻�
		if (pass1.equals("") || pass2.equals("")) {
			JOptionPane.showMessageDialog(this, "�û����벻����Ϊ�գ�");
			pf_pass1.requestFocus();
			return;
		}
		// ��3�����ж���������������Ƿ�һ�£���һ�£���ʾ��һ����Ϣ�������������򣬵�1������ý��㣬Ȼ�󷵻�
		if (!pass1.equals(pass2)) {
			JOptionPane.showMessageDialog(this, "��������벻һ�£����������룡");
			pf_pass1.setText("");
			pf_pass2.setText("");
			pf_pass1.requestFocus();
			return;
		}
		// ��4�����жϵ�ǰ��¼���û������Ƿ���ڣ�
		if (GlobalVar.login_user != null) {
			//��5�������ڣ���ȡ��ǰ��¼���û������������޸����뷽����ִ���޸��������
			String name = GlobalVar.login_user.getName();
			int i = UserDao.updatePass(name, pass1);
			// ��6�����ж��޸Ĳ����Ƿ�ɹ����ɹ�����ʾ�ɹ���Ϣ�����رս���
			if (i == 1) {
				JOptionPane.showMessageDialog(null,
						"�����޸ĳɹ�! �������������¼��");
				dispose();//�رս���
			}
		} else {
			//��7���������ڣ���ʾ�޸�����ʧ����Ϣ
			JOptionPane.showMessageDialog(null, "���ǵ�ǰ�û��������޸�ʧ��!");
		}
	}

	/** ������������"�޸�����"������� */
	public static void main(String args[]) {
		new UpdatePass();
	}
}
