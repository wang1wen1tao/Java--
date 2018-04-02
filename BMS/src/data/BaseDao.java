package data;

import java.sql.Connection;//导入连接接口
import java.sql.DriverManager;//导入驱动器管理类
import java.sql.ResultSet;//导入查询结果集接口
import java.sql.SQLException;//导入SQL异常接口

/**
 * 类功能：用于数据库的查询、更新和关闭连接操作
 * @author Administrator
 */
public class BaseDao {
	/* 成员变量*/
	protected static String driver = "com.mysql.jdbc.Driver";// 定义需要加载的驱动
	protected static String url = "jdbc:mysql://localhost:3306/tsgl";// 定义要使用的数据库资源
	protected static String dbUser = "root";// 定义连接数据库的用户名
	protected static String dbPwd = "";// 定义连接数据库的密码
	public static Connection conn = null;// 声明与数据库的连接对象

	/**
	 * 构造方法:用于加载驱动器，创建与数据库的连接
	 */
	private BaseDao() {
		try {
			if (conn == null) {
				Class.forName(driver);// 加载数据库驱动
				// 按照指定参数，创建与数据库的连接
				conn = DriverManager.getConnection(url, dbUser, dbPwd);
			} else {
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 成员方法1:创建执行对象,执行查询操作
	 * @param sql
	 * @return
	 */
	public static ResultSet executeQuery(String sql) {
		if (sql != null) {
			if (conn == null) {
				new BaseDao();// 加载驱动，并创建与数据库的连接对象
			}
			try {
				return conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE).executeQuery(
						ChangeString.GBKToISO(sql));// 创建执行对象，执行查询操作，并返回结果集
			} catch (SQLException e) {
				System.out.println("查询失败！");//调式
				e.printStackTrace();
				return null;
			}
		} else {
			System.out.println("查询语句为空！");//调式
			return null;
		}
	}

	/**
	 * 成员方法2：创建执行对象，执行更新操作
	 * @param sql
	 * @return
	 */
	public static int executeUpdate(String sql) {
		if (sql != null) {
			if (conn == null) {
				new BaseDao();// 加载驱动，并创建与数据库的连接对象
			}
			try {
				return conn.createStatement().executeUpdate(
						ChangeString.GBKToISO(sql));// 创建执行对象，执行更新操作，并返回1
			} catch (SQLException e) {
				System.out.println("更新失败！");//调式
				e.printStackTrace();
				return -1;
			}
		} else {
			System.out.println("更新语句为空！");//调式
			return -1;
		}
	}

	/**
	 * 成员方法3：关闭与数据库的连接
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
