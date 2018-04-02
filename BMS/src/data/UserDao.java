package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.User;

public class UserDao {
	/** 成员方法1：通过用户名和用户密码，获取一个用户对象 */
	public static User getUser(String name, String pass) {
		User user = null;
		if (name != null || pass != null) {
			// 构造按照用户名和密码查询用户的sql查询语句
			String sql = "select * from user where name=" + "'" + name + "' and pass="
					+ "'" + pass + "'";
			ResultSet rs = BaseDao.executeQuery(sql);// 执行查询语句，并返回结果集
			try {
				// 处理查询结果
				if (rs.next()) {// 下移结果集指针，判断记录是否存在？
					user = new User();// 创建用户对象，并保存用户信息
					user.setId(rs.getInt("id"));
					user.setName(ChangeString.ISOToGBK(rs.getString("name")));
					user.setPass(ChangeString.ISOToGBK(rs.getString("pass")));
					user.setIs_admin((rs.getByte("is_admin")));

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;// 返回用户对象
	}

	/** 成员方法2：通过用户编号，获取一个用户对象 */
	public static User getUser(int id) {
		User user = null;
		if (id > 0) {
			// 构造按照用户编号查询用户的sql查询语句
			String sql = "select * from user where id=" + id;
			ResultSet rs = BaseDao.executeQuery(sql);// 执行查询语句，并返回结果集
			try {
				if (rs.next()) {// 下移结果集指针，判断记录是否存在？
					user = new User();// 创建用户对象，并保存用户信息
					user.setId(rs.getInt("id"));
					user.setName(ChangeString.ISOToGBK(rs.getString("name")));
					user.setPass(ChangeString.ISOToGBK(rs.getString("pass")));
					user.setIs_admin((rs.getByte("is_admin")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;// 返回用户对象
	}

	/** 成员方法3：通过用户名，获取一个用户对象 */
	public static User getUser(String name) {
		User user = null;
		if (name != null) {
			// 构造按照用户名查询用户的sql查询语句
			String sql = "select * from user where name='" + name + "'";
			ResultSet rs = BaseDao.executeQuery(sql);// 执行查询语句，并返回结果集
			try {
				if (rs.next()) {
					user = new User();
					user.setId(rs.getInt("id"));
					user.setName(ChangeString.ISOToGBK(rs.getString("name")));
					user.setPass(ChangeString.ISOToGBK(rs.getString("pass")));
					user.setIs_admin((rs.getByte("is_admin")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	/** 成员方法4：查询所有用户对象，并返回用户对象列表 */
	public static List<User> selectUserList() {
		User user = null;
		List<User> list = new ArrayList<User>();// 创建一个放置用户对象的列表
		// 构造查询所有用户的sql查询语句
		String sql = "select * from user";
		ResultSet rs = BaseDao.executeQuery(sql);// 执行查询操作，并返回结果集
		try {
			while (rs.next()) {
				// 创建用户对象，并保存用户信息
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(ChangeString.ISOToGBK(rs.getString("name")));
				user.setPass(ChangeString.ISOToGBK(rs.getString("pass")));
				user.setIs_admin((rs.getByte("is_admin")));
				list.add(user);// 将这个用户对象，添加到用户列表中
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;// 返回用户对象列表
	}

	/** 成员方法5：添加新用户 */
	public static int insertUser(User user) {
		if (user != null) {
			// 获取用户信息
			int id = user.getId();
			String name = user.getName();
			String pass = user.getPass();
			byte is_admin = user.getIs_admin();
			// 构造添加用户的sql更新语句
			String sql = "insert into user values(" + id + ",'" + name + "','" + pass
					+ "'," + is_admin + ")";

			return BaseDao.executeUpdate(sql); // 执行更新语句，并返回整数
		} else {
			return -1;
		}
	}

	/** 成员方法6：修改指定用户信息 */
	public static int updateUser(User user) {
		if (user != null) {
			// 获取用户信息
			int id = user.getId();
			String name = user.getName();
			String pass = user.getPass();
			byte is_admin = user.getIs_admin();
			// 构造修改用户的sql更新语句
			String sql = "update user set name='" + name + "',pass='" + pass
					+ "',is_admin=" + is_admin + " where id=" + id;
			return BaseDao.executeUpdate(sql); // 执行更新语句，并返回整数
		} else {
			return -1;
		}
	}

	/** 成员方法7：修改指定用户的密码 */
	public static int updatePass(String name, String pass) {
		if (name != null && pass != null) {
			// 构造按照用户名修改用户密码的sql更新语句
			String sql = "update user set pass='" + pass + "' where name='" + name + "'";
			return BaseDao.executeUpdate(sql); // 执行更新语句，并返回整数

		} else {
			return -1;
		}
	}

	/** 成员方法8：删除指定id的用户 */
	public static int deleteUser(int id) {
		// 构造删除用户的sql更新语句
		String sql = "delete from user  where id=" + id ;
		return BaseDao.executeUpdate(sql); // 执行更新语句，并返回整数
	}

	/** 成员方法9：清空所有用户信息 */
	public static void emptyUser() {
		// 构造删除所有用户的sql更新语句
		String sql = "delete from user";
		BaseDao.executeUpdate(sql); // 执行更新语句
	}
}
