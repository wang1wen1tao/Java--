package entity;

/**
 * �๦�ܣ��������á���ȡ�û������������Ϣ
 * @author zzl
 *
 */
public class User {
	/** ��Ա���� */
	private int id;// ��ʾ�û����
	private String name;// ��ʾ�û�����
	private String pass;// ��ʾ�û�����
	private byte is_admin;// ��ʾ�û���Ȩ��

	/**
	 * ��Ա�����Ĺ���:��ȡ�û����
	 * 
	 * @return
	 */
	public int getId() {// ��ȡ�û����
		return id;
	}

	/**
	 * ��Ա�����Ĺ���:�����û����
	 * 
	 * @param id
	 */
	public void setId(int id) {// �����û����
		this.id = id;
	}

	/**
	 * ��Ա�����Ĺ���:��ȡ�û�����
	 * 
	 * @return
	 */
	public String getName() {// ��ȡ�û�����
		return name;
	}

	/**
	 * ��Ա�����Ĺ���:�����û�����
	 * 
	 * @param id
	 */
	public void setName(String name) {// �����û�����
		this.name = name;
	}

	/**
	 * ��Ա�����Ĺ���:��ȡ�û�����
	 * 
	 * @return
	 */
	public String getPass() {// ��ȡ�û�����
		return pass;
	}

	/**
	 * ��Ա�����Ĺ���:�����û�����
	 * 
	 * @param id
	 */
	public void setPass(String pass) {// �����û�����
		this.pass = pass;
	}

	/**
	 * ��Ա�����Ĺ���:��ȡ�û�����Ȩ��
	 * 
	 * @return
	 */
	public byte getIs_admin() {// ��ȡ�û�Ȩ��
		return is_admin;
	}

	/**
	 * ��Ա�����Ĺ���:�����û�����Ȩ��
	 * 
	 * @param id
	 */
	public void setIs_admin(byte is_admin) {// �����û�Ȩ��
		this.is_admin = is_admin;
	}
}
