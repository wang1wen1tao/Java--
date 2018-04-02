package windows;

import java.awt.Color;
import java.awt.Image;
import javax.swing.JFrame;

/**
 * 类作用：所有界面的基础框架，用来统一系统图标、背景颜色等
 * @author zzl
 */
public class PublicJFrame extends JFrame {
	/**
	 * 构造方法：用于初始化基础框架，设置统一系统图标、背景颜色、可见性
 * @author zzl

	 */
	PublicJFrame() {
		// 创建、设置界面框架的图标
		Image icon = new JFrame().getToolkit().getImage("images/icon.png");
		this.setIconImage(icon);
		// 设置框架内容面板的背景颜色
		this.getContentPane().setBackground(new Color(110, 212, 103));
		this.setVisible(true);// 设置框架可见
	}
}
