package entity;

import java.util.Date;
/**
 * �๦�ܣ��������á���ȡ������Ϣ
 * @author Administrator
 *
 */
public class Borrow {
	/*��Ա����*/
	private int id;// ��ʾ���ĺ�
	private String book_id;// ��ʾͼ����
	private String reader_id;// ��ʾ���߱��
	private Date borrow_date;// ��ʾ��������
	private Date back_date;// ��ʾ��������
	private boolean is_back;// ��ʾ�Ƿ���

	/**
	 *  ��Ա�����Ĺ���:��ȡ���ĺ�
	 * @return
	 */
	public int getId() {// ��ȡ���ĺ�
		return id;
	}

	/**
	 *  ��Ա�����Ĺ���:���ý��ĺ�
	 * @param id
	 */
	public void setId(int id) {// ���ý��ĺ�
		this.id = id;
	}

	/**
	 * ��Ա�����Ĺ���:��ȡͼ����
	 * @return
	 */
	public String getBook_id() {// ��ȡͼ����
		return book_id;
	}

	/**
	 *  ��Ա�����Ĺ���:����ͼ����
	 * @param book_id
	 */
	public void setBook_id(String book_id) {// ����ͼ����
		this.book_id = book_id;
	}

	/**
	 * ��Ա�����Ĺ���:��ȡ���߱��
	 * @return
	 */
	public String getReader_id() {// ��ȡ���߱��
		return reader_id;
	}

	/**
	 * ��Ա�����Ĺ���:���ö��߱��
	 * @param reader_id
	 */
	public void setReader_id(String reader_id) {// ���ö��߱��
		this.reader_id = reader_id;
	}

	/**
	 * ��Ա�����Ĺ���:��ȡ��������
	 * @return
	 */
	public Date getBorrow_date() {// ��ȡ��������
		return back_date;
	}

	/**
	 * ��Ա�����Ĺ���:���ý�������
	 * @param borrow_date
	 */
	public void setBorrow_date(Date borrow_date) {// ���ý�������
		this.borrow_date = borrow_date;
	}

	/**
	 * ��Ա�����Ĺ���:��ȡ��������
	 * @return
	 */
	public Date getBack_date() {// ��ȡ��������
		return back_date;
	}

	/**
	 * ��Ա�����Ĺ���:���û�������
	 * @param back_date
	 */
	public void setBack_date(Date back_date) {// ���û�������
		this.back_date = back_date;
	}

	/**
	 * ��Ա�����Ĺ���:��ȡ�Ƿ�����Ϣ
	 * @return
	 */
	public boolean Is_back() {// �ж��Ƿ���
		return is_back;
	}

	/**
	 * ��Ա�����Ĺ���:�����Ƿ�����Ϣ
	 * @param is_back
	 */
	public void setIs_back(boolean is_back) {// �����Ƿ���
		this.is_back = is_back;
	}

	public void setTranslator(String isoToGBK) {
		// TODO Auto-generated method stub
		
	}
}
