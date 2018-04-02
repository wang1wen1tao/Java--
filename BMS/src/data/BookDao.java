package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.Book;

public class BookDao {
	/** 成员方法1：通过图书编号，获取一个图书对象 */
	public static Book getBookById(String id) {
		Book book = null;
		if (id != null) {
			// 构造按照图书编号查询图书的sql查询语句
			String sql = "select * from book where id='" + id + "'";
			ResultSet rs = BaseDao.executeQuery(sql);// 执行查询语句，并返回结果集
			// 处理查询结果
			try {
				if (rs.next()) {
					// 创建图书对象，保存图书信息
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
		return book;// 返回图书对象
	}

	/** 成员方法2：通过图书名，获取一个图书对象 */
	public static Book getBookByName(String name) {
		Book book = null;
		if (name != null) {
			// 构造按照图书名称查询图书的sql查询语句
			String sql = "select * from book where name='" + name + "'";
			ResultSet rs = BaseDao.executeQuery(sql);// 执行查询语句，并返回结果集
			// 处理查询结果
			try {
				if (rs.next()) {
					// 创建图书对象，保存图书信息
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
		return book;// 返回图书对象
	}

	/** 成员方法3：通过sql查询语句，获取图书对象列表 */
	public static List<Book> selectBookList(String sql) {
		List<Book> list = new ArrayList<Book>();// 创建列表
		if (sql != null) {
			Book book = null;
			ResultSet rs = BaseDao.executeQuery(sql);// 执行查询语句，并返回结果集
			try {
				while (rs.next()) {
					// 创建图书对象，保存图书信息
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
		return list;// 返回图书列表
	}

	/** 成员方法4：获取所有图书对象列表 */
	public static List<Book> selectBookList() {
		// 构造查询所有图书的sql查询语句
		String sql = "select * from book";
		return selectBookList(sql);// 返回图书列表
	}

	/** 成员方法5：按照字符属性查询条件，获取图书对象列表，重载方法 */
	public static List<Book> selectBookList(String field, String value) {
		// 构造默认的sql查询语句
		String sql = "select * from book";
		if ((value != null & value.length() > 0) && field != null) {
			// 构造按照字符属性查询图书的sql查询语句，采用模糊查询
			sql = "select * from book where " + field + " like '%" + value + "%'";
		}
		return selectBookList(sql);// 返回图书列表
	}

	/** 成员方法6：按照double类型查询条件，获取图书对象列表，重载方法 */
	public static List<Book> selectBookList(String field, double value) {
		// 构造默认的sql查询语句
		String sql = "select * from book";
		if (field != null && value != 0) {
			// 构造按照double属性查询图书的sql查询语句，采用区间查询
			sql = "select * from book where " + field + " between "
					+ ((int) (value / 10) * 10) + " and "
					+ ((int) (value / 10) * 10 + 10);
		}
		return selectBookList(sql);// 返回读者列表
	}

	/** 成员方法7：按照整型查询条件，获取图书对象列表，重载方法 */
	public static List<Book> selectBookList(String field, int value) {
		// 构造默认的sql查询语句
		String sql = "select * from book";
		if (field != null && value != 0) {
			// 构造按照整型属性查询图书的sql查询语句，采用区间查询
			sql = "select * from book where " + field + " between "
					+ ((int) (value / 10) * 10) + " and "
					+ ((int) (value / 10) * 10 + 10);
		}
		return selectBookList(sql);// 返回图书列表
	}

	/** 成员方法8：添加新图书 */
	public static int insertBook(Book book) {
		if (book != null) {
			// 获取图书信息
			String id = book.getId();
			String name = book.getName();
			String type = book.getType();
			String author = book.getAuthor();
			String translator = book.getTranslator();
			String publisher = book.getPublisher();
			String publish_time = book.getPublish_time();
			int stock = book.getStock();
			double price = book.getPrice();
			// 构造添加图书的sql更新语句
			String sql = "insert into book values('" + id + "','" + name + "','" + type
					+ "','" + author + "','" + translator + "','" + publisher + "','"
					+ publish_time + "'," + stock + "," + price + ")";
			return BaseDao.executeUpdate(sql);// 执行更新语句
		} else {
			return -1;
		}
	}

	/** 成员方法9：修改指定图书信息 */
	public static int updateBook(Book book) {
		if (book != null) {
			// 获取图书信息
			String id = book.getId();
			String name = book.getName();
			String type = book.getType();
			String author = book.getAuthor();
			String translator = book.getTranslator();
			String publisher = book.getPublisher();
			String publish_time = book.getPublish_time();
			int stock = book.getStock();
			double price = book.getPrice();
			// 构造修改图书的sql更新语句
			String sql = "update book set name='" + name + "',type='" + type
					+ "',author='" + author + "', translator='" + translator
					+ "',publisher='" + publisher + "',publish_time='" + publish_time
					+ "', stock=" + stock + ",price=" + price + " where id='" + id + "'";
			return BaseDao.executeUpdate(sql);// 执行更新语句
		} else {
			return -1;
		}
	}

	/** 成员方法10：删除指定图书信息 */
	public static int deleteBook(String id) {
		// 构造删除图书的sql更新语句
		String sql = "delete from book  where id='" + id + "'";
		return BaseDao.executeUpdate(sql);// 执行更新语句
	}

	/** 成员方法11：清空所有图书信息 */
	public static void emptyBook() {
		// 构造清空图书的sql更新语句
		String sql = "delete from book";
		BaseDao.executeUpdate(sql);// 执行更新语句
	}
}
