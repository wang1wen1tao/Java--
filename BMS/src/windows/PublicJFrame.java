package windows;

import java.awt.Color;
import java.awt.Image;
import javax.swing.JFrame;

/**
 * �����ã����н���Ļ�����ܣ�����ͳһϵͳͼ�ꡢ������ɫ��
 * @author zzl
 */
public class PublicJFrame extends JFrame {
	/**
	 * ���췽�������ڳ�ʼ��������ܣ�����ͳһϵͳͼ�ꡢ������ɫ���ɼ���
 * @author zzl

	 */
	PublicJFrame() {
		// ���������ý����ܵ�ͼ��
		Image icon = new JFrame().getToolkit().getImage("images/icon.png");
		this.setIconImage(icon);
		// ���ÿ���������ı�����ɫ
		this.getContentPane().setBackground(new Color(110, 212, 103));
		this.setVisible(true);// ���ÿ�ܿɼ�
	}
}
