package data;

/**
 * 类功能：用于字符编码的相互转换。
 * 
 * @author Administrator
 *
 */
public class ChangeString {

	/**
	 * 静态成员方法1：用于将字符集为“ISO8859_1”的字符串，转换为字符集为"GBK"的字符串。 转出:
	 * 数据表（ISO8859_1或Latin1）→程序（GBK）
	 */
	public static String ISOToGBK(String str) {
		if (str != null) {
			try {
				str = new String(str.getBytes("Latin1"), "GBK");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return str;

	}

	/**
	 * 静态成员方法2：用于将字符集为“GBK”的字符串，转换为字符集为"ISO8859_1"的字符串。
	 * 转入:程序（GBK）→数据表（ISO8859_1或Latin1）
	 */
	public static String GBKToISO(String str) {
		if (str != null) {
			try {
				str = new String(str.getBytes("GBK"), "Latin1");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return str;

	}
}
