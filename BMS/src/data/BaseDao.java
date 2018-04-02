package data;

import java.sql.Connection;//�������ӽӿ�
import java.sql.DriverManager;//����������������
import java.sql.ResultSet;//�����ѯ������ӿ�
import java.sql.SQLException;//����SQL�쳣�ӿ�

/**
 * �๦�ܣ��������ݿ�Ĳ�ѯ�����º͹ر����Ӳ���
 * @author Administrator
 */
public class BaseDao {
	/* ��Ա����*/
	protected static String driver = "com.mysql.jdbc.Driver";// ������Ҫ���ص�����
	protected static String url = "jdbc:mysql://localhost:3306/tsgl";// ����Ҫʹ�õ����ݿ���Դ
	protected static String dbUser = "root";// �����������ݿ���û���
	protected static String dbPwd = "";// �����������ݿ������
	public static Connection conn = null;// ���������ݿ�����Ӷ���

	/**
	 * ���췽��:���ڼ��������������������ݿ������
	 */
	private BaseDao() {
		try {
			if (conn == null) {
				Class.forName(driver);// �������ݿ�����
				// ����ָ�����������������ݿ������
				conn = DriverManager.getConnection(url, dbUser, dbPwd);
			} else {
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��Ա����1:����ִ�ж���,ִ�в�ѯ����
	 * @param sql
	 * @return
	 */
	public static ResultSet executeQuery(String sql) {
		if (sql != null) {
			if (conn == null) {
				new BaseDao();// ���������������������ݿ�����Ӷ���
			}
			try {
				return conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE).executeQuery(
						ChangeString.GBKToISO(sql));// ����ִ�ж���ִ�в�ѯ�����������ؽ����
			} catch (SQLException e) {
				System.out.println("��ѯʧ�ܣ�");//��ʽ
				e.printStackTrace();
				return null;
			}
		} else {
			System.out.println("��ѯ���Ϊ�գ�");//��ʽ
			return null;
		}
	}

	/**
	 * ��Ա����2������ִ�ж���ִ�и��²���
	 * @param sql
	 * @return
	 */
	public static int executeUpdate(String sql) {
		if (sql != null) {
			if (conn == null) {
				new BaseDao();// ���������������������ݿ�����Ӷ���
			}
			try {
				return conn.createStatement().executeUpdate(
						ChangeString.GBKToISO(sql));// ����ִ�ж���ִ�и��²�����������1
			} catch (SQLException e) {
				System.out.println("����ʧ�ܣ�");//��ʽ
				e.printStackTrace();
				return -1;
			}
		} else {
			System.out.println("�������Ϊ�գ�");//��ʽ
			return -1;
		}
	}

	/**
	 * ��Ա����3���ر������ݿ������
	 */
	public static void close() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return;
			}
		} else {
			return;
		}
	}
}
