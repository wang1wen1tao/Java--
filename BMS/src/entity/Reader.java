package entity;
/**
 * �๦�ܣ��������á���ȡ���߶����������Ϣ
 * @author zzl
 *
 */

public class Reader {
	
	/** ��Ա����*/
	private String id;    // ��ʾ���߱���
	private String name;// ��ʾ��������
	private String type;// ��ʾ��������
	private String sex;// ��ʾ�����Ա�
	private int max_num;// ��ʾ��������������
	private int days_num;// ��ʾ��������������

	/**
	 * ��Ա�����Ĺ���:��ȡ���߱��
	 * @return
	 */
	public String getId() {// ��ȡ���߱���
		return id;
	}
	
	/**
	 * ��Ա�����Ĺ���:���ö��߱��
	 * @return
	 */
	public void setId(String id) {// ���ö��߱���
		this.id = id;
	}

	/**
	 * ��Ա�����Ĺ���:��ȡ��������
	 * @return
	 */
	public String getName() {// ��ȡ��������
		return name;
	}

	/**
	 * ��Ա�����Ĺ���:���ö�������
	 * @param name
	 */
	public void setName(String name) {// ���ö�������
		this.name = name;

	}

	/**
	 * ��Ա�����Ĺ���:��ȡ��������
	 * @return
	 */
	public String getType() {// ��ȡ��������
		return type;
	}

	/**
	 * ��Ա�����Ĺ���:����������
	 * @param type
	 */
	public void setType(String type) {// ���ö�������
		this.type = type;
	}

	/**
	 * ��Ա�����Ĺ���:��ȡ�����Ա�
	 * @return
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * ��Ա�����Ĺ���:���ö����Ա�
	 * @param sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * ��Ա�����Ĺ���:��ȡ��������������
	 * @return
	 */
	public int getMax_num() {// ��ȡ��������������
		return max_num;
	}

	/**
	 * ��Ա�����Ĺ���:���ö�������������
	 * @param max_num
	 */
	public void setMax_num(int max_num) {// ���ö�������������
		this.max_num = max_num;
	}

	/**
	 * ��Ա�����Ĺ���:��ȡ��������������
	 * @return
	 */
	public int getDays_num() {// ��ȡ��������������
		return days_num;
	}

	/**
	 * ��Ա�����Ĺ���:���ö�������������
	 * @param days_num
	 */
	public void setDays_num(int days_num) {// ���ö�������������
		this.days_num = days_num;
	}
	
}
