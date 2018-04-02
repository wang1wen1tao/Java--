package data;

/**
 * �๦�ܣ������ַ�������໥ת����
 * 
 * @author Administrator
 *
 */
public class ChangeString {

	/**
	 * ��̬��Ա����1�����ڽ��ַ���Ϊ��ISO8859_1�����ַ�����ת��Ϊ�ַ���Ϊ"GBK"���ַ����� ת��:
	 * ���ݱ�ISO8859_1��Latin1��������GBK��
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
	 * ��̬��Ա����2�����ڽ��ַ���Ϊ��GBK�����ַ�����ת��Ϊ�ַ���Ϊ"ISO8859_1"���ַ�����
	 * ת��:����GBK�������ݱ�ISO8859_1��Latin1��
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
