package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.User;

public class UserDao {
	/** ��Ա����1��ͨ���û������û����룬��ȡһ���û����� */
	public static User getUser(String name, String pass) {
		User user = null;
		if (name != null || pass != null) {
			// ���찴���û����������ѯ�û���sql��ѯ���
			String sql = "select * from user where name=" + "'" + name + "' and pass="
					+ "'" + pass + "'";
			ResultSet rs = BaseDao.executeQuery(sql);// ִ�в�ѯ��䣬�����ؽ����
			try {
				// �����ѯ���
				if (rs.next()) {// ���ƽ����ָ�룬�жϼ�¼�Ƿ���ڣ�
					user = new User();// �����û����󣬲������û���Ϣ
					user.setId(rs.getInt("id"));
					user.setName(ChangeString.ISOToGBK(rs.getString("name")));
					user.setPass(ChangeString.ISOToGBK(rs.getString("pass")));
					user.setIs_admin((rs.getByte("is_admin")));

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;// �����û�����
	}

	/** ��Ա����2��ͨ���û���ţ���ȡһ���û����� */
	public static User getUser(int id) {
		User user = null;
		if (id > 0) {
			// ���찴���û���Ų�ѯ�û���sql��ѯ���
			String sql = "select * from user where id=" + id;
			ResultSet rs = BaseDao.executeQuery(sql);// ִ�в�ѯ��䣬�����ؽ����
			try {
				if (rs.next()) {// ���ƽ����ָ�룬�жϼ�¼�Ƿ���ڣ�
					user = new User();// �����û����󣬲������û���Ϣ
					user.setId(rs.getInt("id"));
					user.setName(ChangeString.ISOToGBK(rs.getString("name")));
					user.setPass(ChangeString.ISOToGBK(rs.getString("pass")));
					user.setIs_admin((rs.getByte("is_admin")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;// �����û�����
	}

	/** ��Ա����3��ͨ���û�������ȡһ���û����� */
	public static User getUser(String name) {
		User user = null;
		if (name != null) {
			// ���찴���û�����ѯ�û���sql��ѯ���
			String sql = "select * from user where name='" + name + "'";
			ResultSet rs = BaseDao.executeQuery(sql);// ִ�в�ѯ��䣬�����ؽ����
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

	/** ��Ա����4����ѯ�����û����󣬲������û������б� */
	public static List<User> selectUserList() {
		User user = null;
		List<User> list = new ArrayList<User>();// ����һ�������û�������б�
		// �����ѯ�����û���sql��ѯ���
		String sql = "select * from user";
		ResultSet rs = BaseDao.executeQuery(sql);// ִ�в�ѯ�����������ؽ����
		try {
			while (rs.next()) {
				// �����û����󣬲������û���Ϣ
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(ChangeString.ISOToGBK(rs.getString("name")));
				user.setPass(ChangeString.ISOToGBK(rs.getString("pass")));
				user.setIs_admin((rs.getByte("is_admin")));
				list.add(user);// ������û�������ӵ��û��б���
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;// �����û������б�
	}

	/** ��Ա����5��������û� */
	public static int insertUser(User user) {
		if (user != null) {
			// ��ȡ�û���Ϣ
			int id = user.getId();
			String name = user.getName();
			String pass = user.getPass();
			byte is_admin = user.getIs_admin();
			// ��������û���sql�������
			String sql = "insert into user values(" + id + ",'" + name + "','" + pass
					+ "'," + is_admin + ")";

			return BaseDao.executeUpdate(sql); // ִ�и�����䣬����������
		} else {
			return -1;
		}
	}

	/** ��Ա����6���޸�ָ���û���Ϣ */
	public static int updateUser(User user) {
		if (user != null) {
			// ��ȡ�û���Ϣ
			int id = user.getId();
			String name = user.getName();
			String pass = user.getPass();
			byte is_admin = user.getIs_admin();
			// �����޸��û���sql�������
			String sql = "update user set name='" + name + "',pass='" + pass
					+ "',is_admin=" + is_admin + " where id=" + id;
			return BaseDao.executeUpdate(sql); // ִ�и�����䣬����������
		} else {
			return -1;
		}
	}

	/** ��Ա����7���޸�ָ���û������� */
	public static int updatePass(String name, String pass) {
		if (name != null && pass != null) {
			// ���찴���û����޸��û������sql�������
			String sql = "update user set pass='" + pass + "' where name='" + name + "'";
			return BaseDao.executeUpdate(sql); // ִ�и�����䣬����������

		} else {
			return -1;
		}
	}

	/** ��Ա����8��ɾ��ָ��id���û� */
	public static int deleteUser(int id) {
		// ����ɾ���û���sql�������
		String sql = "delete from user  where id=" + id ;
		return BaseDao.executeUpdate(sql); // ִ�и�����䣬����������
	}

	/** ��Ա����9����������û���Ϣ */
	public static void emptyUser() {
		// ����ɾ�������û���sql�������
		String sql = "delete from user";
		BaseDao.executeUpdate(sql); // ִ�и������
	}
}
