package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.Book;

public class BookDao {
	/** ��Ա����1��ͨ��ͼ���ţ���ȡһ��ͼ����� */
	public static Book getBookById(String id) {
		Book book = null;
		if (id != null) {
			// ���찴��ͼ���Ų�ѯͼ���sql��ѯ���
			String sql = "select * from book where id='" + id + "'";
			ResultSet rs = BaseDao.executeQuery(sql);// ִ�в�ѯ��䣬�����ؽ����
			// �����ѯ���
			try {
				if (rs.next()) {
					// ����ͼ����󣬱���ͼ����Ϣ
					book = new Book();
					book.setId(ChangeString.ISOToGBK(rs.getString("id")));
					book.setName(ChangeString.ISOToGBK(rs.getString("name")));
					book.setType(ChangeString.ISOToGBK(rs.getString("type")));
					book.setAuthor(ChangeString.ISOToGBK(rs.getString("author")));
					book.setTranslator(ChangeString.ISOToGBK(rs.getString("translator")));
					book.setPublisher(ChangeString.ISOToGBK(rs.getString("publisher")));
					book.setPublish_time(
							ChangeString.ISOToGBK(rs.getString("publish_time")));
					book.setStock(rs.getInt("stock"));
					book.setPrice(rs.getDouble("price"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return book;// ����ͼ�����
	}

	/** ��Ա����2��ͨ��ͼ��������ȡһ��ͼ����� */
	public static Book getBookByName(String name) {
		Book book = null;
		if (name != null) {
			// ���찴��ͼ�����Ʋ�ѯͼ���sql��ѯ���
			String sql = "select * from book where name='" + name + "'";
			ResultSet rs = BaseDao.executeQuery(sql);// ִ�в�ѯ��䣬�����ؽ����
			// �����ѯ���
			try {
				if (rs.next()) {
					// ����ͼ����󣬱���ͼ����Ϣ
					book = new Book();
					book.setId(ChangeString.ISOToGBK(rs.getString("id")));
					book.setName(ChangeString.ISOToGBK(rs.getString("name")));
					book.setType(ChangeString.ISOToGBK(rs.getString("type")));
					book.setAuthor(ChangeString.ISOToGBK(rs.getString("author")));
					book.setTranslator(ChangeString.ISOToGBK(rs.getString("translator")));
					book.setPublisher(ChangeString.ISOToGBK(rs.getString("publisher")));
					book.setPublish_time(
							ChangeString.ISOToGBK(rs.getString("publish_time")));
					book.setStock(rs.getInt("stock"));
					book.setPrice(rs.getDouble("price"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return book;// ����ͼ�����
	}

	/** ��Ա����3��ͨ��sql��ѯ��䣬��ȡͼ������б� */
	public static List<Book> selectBookList(String sql) {
		List<Book> list = new ArrayList<Book>();// �����б�
		if (sql != null) {
			Book book = null;
			ResultSet rs = BaseDao.executeQuery(sql);// ִ�в�ѯ��䣬�����ؽ����
			try {
				while (rs.next()) {
					// ����ͼ����󣬱���ͼ����Ϣ
					book = new Book();
					book.setId(ChangeString.ISOToGBK(rs.getString("id")));
					book.setName(ChangeString.ISOToGBK(rs.getString("name")));
					book.setType(ChangeString.ISOToGBK(rs.getString("type")));
					book.setAuthor(ChangeString.ISOToGBK(rs.getString("author")));
					book.setTranslator(ChangeString.ISOToGBK(rs.getString("translator")));
					book.setPublisher(ChangeString.ISOToGBK(rs.getString("publisher")));
					book.setPublish_time(
							ChangeString.ISOToGBK(rs.getString("publish_time")));
					book.setStock(rs.getInt("stock"));
					book.setPrice(rs.getDouble("price"));
					list.add(book);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;// ����ͼ���б�
	}

	/** ��Ա����4����ȡ����ͼ������б� */
	public static List<Book> selectBookList() {
		// �����ѯ����ͼ���sql��ѯ���
		String sql = "select * from book";
		return selectBookList(sql);// ����ͼ���б�
	}

	/** ��Ա����5�������ַ����Բ�ѯ��������ȡͼ������б����ط��� */
	public static List<Book> selectBookList(String field, String value) {
		// ����Ĭ�ϵ�sql��ѯ���
		String sql = "select * from book";
		if ((value != null & value.length() > 0) && field != null) {
			// ���찴���ַ����Բ�ѯͼ���sql��ѯ��䣬����ģ����ѯ
			sql = "select * from book where " + field + " like '%" + value + "%'";
		}
		return selectBookList(sql);// ����ͼ���б�
	}

	/** ��Ա����6������double���Ͳ�ѯ��������ȡͼ������б����ط��� */
	public static List<Book> selectBookList(String field, double value) {
		// ����Ĭ�ϵ�sql��ѯ���
		String sql = "select * from book";
		if (field != null && value != 0) {
			// ���찴��double���Բ�ѯͼ���sql��ѯ��䣬���������ѯ
			sql = "select * from book where " + field + " between "
					+ ((int) (value / 10) * 10) + " and "
					+ ((int) (value / 10) * 10 + 10);
		}
		return selectBookList(sql);// ���ض����б�
	}

	/** ��Ա����7���������Ͳ�ѯ��������ȡͼ������б����ط��� */
	public static List<Book> selectBookList(String field, int value) {
		// ����Ĭ�ϵ�sql��ѯ���
		String sql = "select * from book";
		if (field != null && value != 0) {
			// ���찴���������Բ�ѯͼ���sql��ѯ��䣬���������ѯ
			sql = "select * from book where " + field + " between "
					+ ((int) (value / 10) * 10) + " and "
					+ ((int) (value / 10) * 10 + 10);
		}
		return selectBookList(sql);// ����ͼ���б�
	}

	/** ��Ա����8�������ͼ�� */
	public static int insertBook(Book book) {
		if (book != null) {
			// ��ȡͼ����Ϣ
			String id = book.getId();
			String name = book.getName();
			String type = book.getType();
			String author = book.getAuthor();
			String translator = book.getTranslator();
			String publisher = book.getPublisher();
			String publish_time = book.getPublish_time();
			int stock = book.getStock();
			double price = book.getPrice();
			// �������ͼ���sql�������
			String sql = "insert into book values('" + id + "','" + name + "','" + type
					+ "','" + author + "','" + translator + "','" + publisher + "','"
					+ publish_time + "'," + stock + "," + price + ")";
			return BaseDao.executeUpdate(sql);// ִ�и������
		} else {
			return -1;
		}
	}

	/** ��Ա����9���޸�ָ��ͼ����Ϣ */
	public static int updateBook(Book book) {
		if (book != null) {
			// ��ȡͼ����Ϣ
			String id = book.getId();
			String name = book.getName();
			String type = book.getType();
			String author = book.getAuthor();
			String translator = book.getTranslator();
			String publisher = book.getPublisher();
			String publish_time = book.getPublish_time();
			int stock = book.getStock();
			double price = book.getPrice();
			// �����޸�ͼ���sql�������
			String sql = "update book set name='" + name + "',type='" + type
					+ "',author='" + author + "', translator='" + translator
					+ "',publisher='" + publisher + "',publish_time='" + publish_time
					+ "', stock=" + stock + ",price=" + price + " where id='" + id + "'";
			return BaseDao.executeUpdate(sql);// ִ�и������
		} else {
			return -1;
		}
	}

	/** ��Ա����10��ɾ��ָ��ͼ����Ϣ */
	public static int deleteBook(String id) {
		// ����ɾ��ͼ���sql�������
		String sql = "delete from book  where id='" + id + "'";
		return BaseDao.executeUpdate(sql);// ִ�и������
	}

	/** ��Ա����11���������ͼ����Ϣ */
	public static void emptyBook() {
		// �������ͼ���sql�������
		String sql = "delete from book";
		BaseDao.executeUpdate(sql);// ִ�и������
	}
}
