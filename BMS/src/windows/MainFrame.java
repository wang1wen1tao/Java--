package windows;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import data.BaseDao;

public class MainFrame extends PublicJFrame {
	/** ��Ա���� */
	private JMenuItem mi_user_upkeep;// �����û�ά���˵���
	private JMenuItem mi_reader_upkeep;// ��������ά���˵���
	private JMenuItem mi_book_upkeep;// ����ͼ��ά���˵���
	private JMenuItem mi_borrow;// ��������˵���
	private JMenuItem mi_back;// ��������˵���
	private JMenuItem mi_reader_query;// �������߲�ѯ�˵���
	private JMenuItem mi_book_query;// ����ͼ���ѯ�˵���
	private JMenuItem mi_update_pass;// �����޸�����˵���
	private JMenuItem mi_book_statistics;// ����ͼ��ͳ�Ʋ˵���
	private JMenuItem mi_exit;// �����˳�ϵͳ�˵���

	private JButton bt_reader_upkeep;// ��������ά����ť
	private JButton bt_book_upkeep;// ����ͼ��ά����ť
	private JButton bt_borrow;// �������鰴ť
	private JButton bt_back;// �������鰴ť
	private JButton bt_reader_query;// �������߲�ѯ��ť
	private JButton bt_book_query;// ����ͼ���ѯ��ť
	private JButton bt_book_statistics;// ����ͼ��ͳ�ư�ť
	private JButton bt_exit;// �����˳�ϵͳ��ť

	/** ���췽����ϵͳ�������ʼ������ */
	public MainFrame() {
		//��1����ϵͳ����������
		this.setTitle("ͼ�����ϵͳ");// �������������
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);// ���������洰�����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//��2�������������ò˵������ȵ��ô���ϵͳ�˵�������������ϵͳ�˵���,�������������ò˵���
		this.setJMenuBar(createMenuBar());
		//��3���� ���������ù��������ȵ��ô���ϵͳ����������������ϵͳ���������ٽ���������ӵ�������Ϸ�
		this.add(createToolBar(), BorderLayout.NORTH); 
	}

	/** ��Ա����1������ϵͳ�˵������� */
	private JMenuBar createMenuBar() {
		// ��1���������˵�������
		JMenuBar menuBar = new JMenuBar();
		//��2���������˵��������ά���˵����ְ����û�ά��������ά����ͼ��ά���˵���
		JMenu menu_upkeep = new JMenu("����ά��"); // ����"����ά��"�˵�
		//��3���������˵����Ϊ�˵������õ����¼�������
		mi_user_upkeep = new JMenuItem("�û�ά��"); // �������û�ά�����˵���
		mi_user_upkeep.addActionListener(new ActionListener() {//��ӵ����¼�������
			public void actionPerformed(ActionEvent e) {
				user_upkeep_actionPerformed();// ���á��û�ά������Ӧ����
			}
		});
		//��4�������˵�����ӵ��˵���
		menu_upkeep.add(mi_user_upkeep);// �����û�ά�����˵�����ӵ�������ά�����˵�
		
		mi_reader_upkeep = new JMenuItem("����ά��"); // ����������ά�����˵���
		mi_reader_upkeep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reader_upkeep_actionPerformed();// ���á�����ά������Ӧ����
			}
		});
		menu_upkeep.add(mi_reader_upkeep);// ��������ά�����˵�����ӵ�������ά�����˵�

		mi_book_upkeep = new JMenuItem("ͼ��ά��"); // ����"ͼ��ά��"�˵���
		mi_book_upkeep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				book_upkeep_actionPerformed();// ����"ͼ��ά��"��Ӧ����
			}
		});
		menu_upkeep.add(mi_book_upkeep);
		//��5�������˵���ӵ��˵�����
		menuBar.add(menu_upkeep);// ��"����ά��"�˵���ӵ�ϵͳ�˵���

		JMenu menu_borrow = new JMenu("���Ĺ���"); // ����"���Ĺ���"�˵�
		mi_borrow = new JMenuItem("����"); // ����"����"�˵���
		mi_borrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrow_actionPerformed();// ����"����"��Ӧ����
			}
		});
		menu_borrow.add(mi_borrow);

		mi_back = new JMenuItem("����"); // ����"����"�˵���
		mi_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back_actionPerformed();// ����"����"��Ӧ����
			}
		});
		menu_borrow.add(mi_back);
		menuBar.add(menu_borrow);// ��"���Ĺ���"�˵���ӵ�ϵͳ�˵���

		JMenu menu_query = new JMenu("��ѯͳ��"); // ����"��ѯͳ��"�˵�
		mi_reader_query = new JMenuItem("���߲�ѯ"); // ����"���߲�ѯ"�˵���
		mi_reader_query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reader_query_actionPerformed();// ����"���߲�ѯ"��Ӧ����
			}
		});
		menu_query.add(mi_reader_query);

		mi_book_query = new JMenuItem("ͼ���ѯ"); // ����"ͼ���ѯ"�˵���
		mi_book_query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				book_query_actionPerformed();// ����"ͼ���ѯ"��Ӧ����
			}
		});
		menu_query.add(mi_book_query);

		mi_book_statistics = new JMenuItem("ͼ��ͳ��"); // ����" ͼ��ͳ��"�˵���
		mi_book_statistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				book_statistics_actionPerformed();// ����"ͼ��ͳ��"��Ӧ����
			}
		});
		menu_query.add(mi_book_statistics);
		menuBar.add(menu_query);

		JMenu menu_management = new JMenu("ϵͳ����");// ����"ϵͳ����"�˵�
		mi_update_pass = new JMenuItem("�޸�����"); // ����"�޸�����"�˵���
		mi_update_pass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update_pass_actionPerformed();// ����"�޸�����"��Ӧ����
			}
		});
		menu_management.add(mi_update_pass);

		mi_exit = new JMenuItem("�˳�ϵͳ"); // ����"�˳�ϵͳ"�˵���
		mi_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit_actionPerformed();// ����"�˳�ϵͳ"��Ӧ����
			}
		});
		menu_management.add(mi_exit);
		menuBar.add(menu_management);
		//��6�������ز˵�����
		return menuBar;
	}

	/** ��Ա����2�� �������������� */
	private JToolBar createToolBar() {
		//��1�������������ù�����
		JToolBar toolBar = new JToolBar("ͼ�����ϵͳ������");// ��������������
		toolBar.setFloatable(false); // ���ù��������ɸ���
			// ���ù������߿򵼽Ƿ�ʽ
		toolBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
		//��2���� ���������ù������ϵĿ�ݰ�ť
		bt_reader_upkeep = new JButton("����ά��");// ����"����ά��"��ť
			// ����"����ά��"ͼ��	
		ImageIcon icon_reader_upkeep = new ImageIcon("images/reader_upkeep.png");
		bt_reader_upkeep.setIcon(icon_reader_upkeep);// Ϊ"����ά��"��ť����ͼ��
		bt_reader_upkeep.setToolTipText("����ά��");// Ϊ"����ά��"��ť������ʾ
			// ��"����ά��"��ťע�ᵥ���¼�������
		bt_reader_upkeep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reader_upkeep_actionPerformed();// ����"����ά������Ӧ����
			}
		});
		//��3��������ť��ӵ���������
		toolBar.add(bt_reader_upkeep);// ��������ά������ť��ӵ�������
		
		bt_book_upkeep = new JButton("ͼ��ά��");
		ImageIcon icon_book_upkeep = new ImageIcon("images/book_upkeep.png");
		bt_book_upkeep.setIcon(icon_book_upkeep);
		toolBar.add(bt_book_upkeep);
		bt_book_upkeep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				book_upkeep_actionPerformed();// ���á�ͼ��ά������Ӧ����
			}
		});

		bt_borrow = new JButton("����");
		ImageIcon icon_borrow = new ImageIcon("images/borrow.png");
		bt_borrow.setIcon(icon_borrow);
		toolBar.add(bt_borrow);
		bt_borrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrow_actionPerformed();// ���á����顱��Ӧ����
			}
		});

		bt_back = new JButton("����");
		ImageIcon icon_back = new ImageIcon("images/back.png");
		bt_back.setIcon(icon_back);
		toolBar.add(bt_back);
		bt_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back_actionPerformed();// ���á����顱��Ӧ����
			}
		});

		bt_reader_query = new JButton("���߲�ѯ");
		ImageIcon icon_reader_query = new ImageIcon("images/reader_query.png");
		bt_reader_query.setIcon(icon_reader_query);
		toolBar.add(bt_reader_query);
		bt_reader_query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reader_query_actionPerformed();// ���á����߲�ѯ����Ӧ����
			}
		});

		bt_book_query = new JButton("ͼ���ѯ");
		ImageIcon icon_book_query = new ImageIcon("images/book_query.png");
		bt_book_query.setIcon(icon_book_query);
		toolBar.add(bt_book_query);
		bt_book_query.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				book_query_actionPerformed();// ���á�ͼ���ѯ����Ӧ����
			}
		});

		bt_book_statistics = new JButton("ͼ��ͳ��");
		ImageIcon icon_book_statistics = new ImageIcon("images/book_statistics.png");
		bt_book_statistics.setIcon(icon_book_statistics);
		toolBar.add(bt_book_statistics);
		bt_book_statistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				book_statistics_actionPerformed();// ���á�ͼ��ͳ�ơ���Ӧ����
			}
		});

		bt_exit = new JButton("�˳�ϵͳ");
		ImageIcon icon_exit = new ImageIcon("images/exit.png");
		bt_exit.setIcon(icon_exit);
		toolBar.add(bt_exit);
		bt_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit_actionPerformed();// ���á��˳�ϵͳ"��Ӧ����
			}
		});
		//��4�������ع�����
		return toolBar;
	}

	/** ��Ա����3������ϵͳ����ʹ��Ȩ�޵ķ��� */
	void setPurView(byte purView) {
		switch (purView) {
		case 0://�û�Ȩ��Ϊ0��Ϊ����Ա
			mi_user_upkeep.setEnabled(false);//���á��û�ά�����˵������
			mi_book_statistics.setEnabled(false);
			bt_book_statistics.setEnabled(false);//���á�ͼ��ͳ�ơ���ť������
			break;
		case 1://�û�Ȩ��Ϊ1��Ϊ����Ա
			break;
		default://�û�Ȩ��Ϊ������Ϊһ��ÿ�
			mi_user_upkeep.setEnabled(false);
			mi_reader_upkeep.setEnabled(false);
			mi_book_upkeep.setEnabled(false);
			mi_borrow.setEnabled(false);
			mi_back.setEnabled(false);
			mi_book_statistics.setEnabled(false);
			mi_update_pass.setEnabled(false);

			bt_book_statistics.setEnabled(false);
			bt_reader_upkeep.setEnabled(false);
			bt_book_upkeep.setEnabled(false);
			bt_borrow.setEnabled(false);
			bt_back.setEnabled(false);
		}
	}

	/** ��Ա����4���û�ά����Ӧ���� */
	private void user_upkeep_actionPerformed() {
		new UserUpkeep();// �������û�ά��������
	}

	/** ��Ա����5������ά����Ӧ���� */
	private void reader_upkeep_actionPerformed() {
		new ReaderUpkeep();// ����������ά��������
	}

	/** ��Ա����6��ͼ��ά����Ӧ���� */
	private void book_upkeep_actionPerformed() {
		new BookUpkeep();// ������ͼ��ά��������
	}

	/** ��Ա����7��������Ӧ���� */
	private void borrow_actionPerformed() {
		// new Borrow();// ���������顱����
	}

	/** ��Ա����8��������Ӧ���� */
	private void back_actionPerformed() {
		// new Back();// ���������顱����
	}

	/** ��Ա����9�����߲�ѯ��Ӧ���� */
	private void reader_query_actionPerformed() {
		new ReaderQuery();// ���������߲�ѯ������
	}

	/** ��Ա����10��ͼ���ѯ��Ӧ���� */
	private void book_query_actionPerformed() {
		new BookQuery();// ������ͼ���ѯ������
	}

	/** ��Ա����12��ͼ��ͳ����Ӧ���� */
	private void book_statistics_actionPerformed() {
		// new BookStatistics();// ������ͼ��ͳ�ơ�����
	}

	/** ��Ա����13���޸�������Ӧ���� */
	private void update_pass_actionPerformed() {
		new UpdatePass();//// �������޸��û����롱����
	}

	/** ��Ա����14���˳�ϵͳ��Ӧ���� */
	private void exit_actionPerformed() {
		BaseDao.close();// �ر������ݿ������
		System.exit(0);// �˳�ϵͳ
	}

	// ���Է���������������Ĳ���
	public static void main(String args[]) {
		new MainFrame();// ����ϵͳ��������������
	}
}
