package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.Reader;

public class ReaderDao {
	/** ��Ա����1��ͨ�����߱�ţ���ȡһ�����߶��� */
	public static Reader getReaderById(String id) {
		Reader reader = null;
		if (id != null) {
			// ���찴�ն��߱�Ų�ѯ���ߵ�sql��ѯ���
			String sql = "select * from reader where id='" + id + "'";
			ResultSet rs = BaseDao.executeQuery(sql);// ִ�в�ѯ��䣬�����ؽ����
			try {
				if (rs.next()) {// ���ƽ����ָ�룬�жϼ�¼�Ƿ���ڣ�
					reader = new Reader();// �������߶��󣬲����������Ϣ
					reader.setId(ChangeString.ISOToGBK(rs.getString("id")));
					reader.setName(ChangeString.ISOToGBK(rs.getString("name")));
					reader.setType(ChangeString.ISOToGBK(rs.getString("type")));
					reader.setSex(ChangeString.ISOToGBK(rs.getString("sex")));
					reader.setMax_num(rs.getInt("max_num"));
					reader.setDays_num(rs.getInt("days_num"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return reader;// ���ض��߶���
	}

	/** ��Ա����2��ͨ��sql��ѯ��䣬��ȡ���߶����б� */
	public static List<Reader> selectReaderList(String sql) {
		List<Reader> list = new ArrayList<Reader>(); // ����һ��������߶�����б�
		if (sql != null) {
			Reader reader = null;
			ResultSet rs = BaseDao.executeQuery(sql);// ִ�в�ѯ��䣬�����ؽ����
			try {
				while (rs.next()) {// ���ƽ����ָ�룬�жϼ�¼�Ƿ���ڣ�
					reader = new Reader(); // �������߶��󣬱��������Ϣ
					reader.setId(ChangeString.ISOToGBK(rs.getString("id")));
					reader.setName(ChangeString.ISOToGBK(rs.getString("name")));
					reader.setType(ChangeString.ISOToGBK(rs.getString("type")));
					reader.setSex(ChangeString.ISOToGBK(rs.getString("sex")));
					reader.setMax_num(rs.getInt("max_num"));
					reader.setDays_num(rs.getInt("days_num"));
					list.add(reader);// ������б���Ӷ��߶���
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;// ���ض����б�
	}

	/** ��Ա����3����ȡ���ж��߶����б� */
	public static List<Reader> selectReaderList() {
		// �����ѯ���ж��ߵ�sql��ѯ���
		String sql = "select * from reader";
		return selectReaderList(sql);// ִ�в�ѯ��䣬���ض����б�
	}

	/** ��Ա����4�������ַ�����������ֵ��ѯ��������ȡ���߶����б� */
	public static List<Reader> selectReaderList(String field, String value) {
		String sql = "select * from reader";// ����Ĭ�ϵ�sql��ѯ���
		if ((value != null & value.length() > 0) && field != null) {
			// ���찴���ַ�����������ֵ����ѯ��sql��ѯ��䣬����ģ����ѯ
			sql = "select * from reader where " + field + " like '%" + value + "%'";
		}
		return selectReaderList(sql);// ִ�в�ѯ��䣬 ���ض����б�
	}

	/** ��Ա����5�������������ݲ�ѯ��������ȡ���߶����б� */
	public static List<Reader> selectReaderList(String field, int value) {
		String sql = "select * from reader";// ����Ĭ�ϵ�sql��ѯ���
		if (field != null && value != 0) {
			// ���찴����������ֵ����ѯ��sql��ѯ���
			sql = "select * from reader where " + field + "=" + value;
		}
		return selectReaderList(sql);// ִ�в�ѯ��䣬���ض����б�
	}

	/** ��Ա����6������¶��� */
	public static int insertReader(Reader reader) {
		if (reader != null) {
			// ��ȡ������Ϣ
			String id = reader.getId();
			String name = reader.getName();
			String type = reader.getType();
			String sex = reader.getSex();
			int max_num = reader.getMax_num();
			int days_num = reader.getDays_num();
			// ������Ӷ��ߵ�SQL�������
			String sql = "insert into reader values('" + id + "','" + name + "','" + type
					+ "','" + sex + "'," + max_num + "," + days_num + ")";
			return BaseDao.executeUpdate(sql); // ִ�и������
		} else {
			return -1;
		}
	}

	/** ��Ա����7���޸�ָ��������Ϣ */
	public static int updateReader(Reader reader) {
		if (reader != null) {
			// ��ȡ������Ϣ
			String id = reader.getId();
			String name = reader.getName();
			String type = reader.getType();
			String sex = reader.getSex();
			int max_num = reader.getMax_num();
			int days_num = reader.getDays_num();
			// �����޸Ķ��ߵ�SQL�������
			String sql = "update reader set name='" + name + "',type='" + type + "',sex='"
					+ sex + "', max_num=" + max_num + ",days_num=" + days_num
					+ " where id='" + id + "'";
			return BaseDao.executeUpdate(sql);// ִ�и������
		} else {
			return -1;
		}
	}

	/** ��Ա����8��ɾ��ָ������ */
	public static int deleteReader(String id) {
		// ���찴���߱��ɾ�����ߵ�SQL�������
		String sql = "delete from reader  where id='" + id + "'";
		return BaseDao.executeUpdate(sql);// ִ�и������
	}

	/** ��Ա����9��������ж�����Ϣ */
	public static void emptyReader() {
		// ����ɾ�����ߵ�SQL�������
		String sql = "delete from reader";
		BaseDao.executeUpdate(sql);// ִ�и������
	}
}
