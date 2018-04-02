package windows;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import util.GlobalVar;
import data.UserDao;
import entity.User;

public class Login extends PublicJFrame {
	/** ��Ա���� */
	private JTextField tf_user;// �����û����ı���
	private JPasswordField pf_pass;// ���������
	private JButton bt_login;// �����¼��ť
	private JButton bt_close;// ����رհ�ť

	/** ���췽�������� ��ʼ������¼���桱 */
	Login() {
		// ��1������ȡ���������壬�������������Ĳ���Ϊ���Բ���
		this.getContentPane().setLayout(null);
		// ��2�����ڿ�ܵ���������ϣ������� ���ø������
		JLabel lb_user = new JLabel("�û���:");// �����û�����ǩ
		// �����û�����ǩ�ķ���λ�úͷ��ô�С
		lb_user.setBounds(new Rectangle(100, 50, 70, 25));
		add(lb_user);// ���û�����ǩ��ӵ����������

		tf_user = new JTextField();// �����û����ı���
		tf_user.setBounds(new Rectangle(170, 50, 110, 25));
		add(tf_user);

		JLabel lb_pass = new JLabel("���룺");// ���������ǩ
		lb_pass.setBounds(new Rectangle(100, 90, 50, 25));
		add(lb_pass);

		pf_pass = new JPasswordField();// ���������
		pf_pass.setBounds(new Rectangle(170, 90, 110, 25));
		pf_pass.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent arg0) {
				if (arg0.getKeyChar() == '\n') {
					bt_login_actionPerformed();// ���á���ѯ����ť�¼���Ӧ����
				}
			}
		});
		add(pf_pass);

		bt_login = new JButton("��¼");// ������¼��ť
		bt_login.setBounds(new Rectangle(100, 160, 80, 25));
		// ��3����Ϊ��ť��ӵ����¼�������
		// Ϊ����¼����ť��ӵ����¼�������
		bt_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ���á���¼����ť�¼���Ӧ����
				bt_login_actionPerformed();
			}
		});
		add(bt_login);

		bt_close = new JButton("�ر�");// �����رհ�ť
		bt_close.setBounds(new Rectangle(200, 160, 80, 25));
		// Ϊ���رա���ť��ӵ����¼�������
		bt_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �ͷš���¼���桱ռ�õ���Ļ��Դ�����رա���¼���桱
				dispose();
			}
		});
		add(bt_close);
		
		// ��4�������á���¼����ı��⡢��С��λ�õ�����
		setTitle("ͼ�����ϵͳ--��¼����");// ���á���¼���桱����
		setSize(380, 260);// ���á���¼���桱��С
		setResizable(false);// ���á���¼���桱��С���ɸı�
		setLocationRelativeTo(null);// ���á���¼���桱��������Ļ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ���á���¼���桱Ĭ�ϵĹرղ���
	}

	/** ��Ա����1�� "��¼"��ť���¼���Ӧ���� */
	void bt_login_actionPerformed() {
		// ��1��:����û���������
		String name = tf_user.getText().trim();
		String pass = new String(pf_pass.getPassword()).trim();
		// ��2�����ж��û����������Ƿ�Ϊ�գ�
		if (name.equals("") || pass.equals("")) {
			// Ϊ�գ�����ʾ�Ի��򣬸�����ʾ��Ϣ��������
			JOptionPane.showMessageDialog(this, "�û���Ϣ������Ϊ�գ�");
			return;
		}
		// ��3�������÷�������ȡ��¼�û�����
		User user = UserDao.getUser(name, pass);
		// ��4�����жϵ�¼�û������Ƿ���ڣ�
		if (user != null) {
			// ��5�������ڣ��򱣴浱ǰ��¼�û����󣬴���ϵͳ�����棬�����û�Ȩ�����������棬�رյ�¼����
			GlobalVar.login_user = user;// ���浱ǰ��¼�û�����
//			System.out.println("����ϵͳ������");
			MainFrame main = new MainFrame();// ����ϵͳ������
			// �����û�Ȩ�ޣ�������Ҫ��ʾ��ϵͳ����������
			main.setPurView((byte) user.getIs_admin());//�˷�����ϵͳ�������ж���
			this.dispose();// �ͷš���¼���桱ռ�õ���Ļ��Դ
		} else {
			// ��6���������ڣ� ��ʾ��ʾ�Ի�������û���������
			JOptionPane.showMessageDialog(this, "�û������������!");
			tf_user.setText("");// ����û����ı���
			pf_pass.setText("");// ��������
			return;
		}
	}

	/** ����������ͼ�����ϵͳ����� */
	public static void main(String args[]) {
		new Login();// ������¼����
	}
}
