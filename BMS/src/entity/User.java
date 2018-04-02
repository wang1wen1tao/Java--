package entity;

/**
 * 类功能：用于设置、获取用户对象的属性信息
 * @author zzl
 *
 */
public class User {
	/** 成员变量 */
	private int id;// 表示用户编号
	private String name;// 表示用户名称
	private String pass;// 表示用户密码
	private byte is_admin;// 表示用户编权限

	/**
	 * 成员方法的功能:获取用户编号
	 * 
	 * @return
	 */
	public int getId() {// 获取用户编号
		return id;
	}

	/**
	 * 成员方法的功能:设置用户编号
	 * 
	 * @param id
	 */
	public void setId(int id) {// 设置用户编号
		this.id = id;
	}

	/**
	 * 成员方法的功能:获取用户名称
	 * 
	 * @return
	 */
	public String getName() {// 获取用户名称
		return name;
	}

	/**
	 * 成员方法的功能:设置用户名称
	 * 
	 * @param id
	 */
	public void setName(String name) {// 设置用户名称
		this.name = name;
	}

	/**
	 * 成员方法的功能:获取用户密码
	 * 
	 * @return
	 */
	public String getPass() {// 获取用户密码
		return pass;
	}

	/**
	 * 成员方法的功能:设置用户密码
	 * 
	 * @param id
	 */
	public void setPass(String pass) {// 设置用户密码
		this.pass = pass;
	}

	/**
	 * 成员方法的功能:获取用户访问权限
	 * 
	 * @return
	 */
	public byte getIs_admin() {// 获取用户权限
		return is_admin;
	}

	/**
	 * 成员方法的功能:设置用户访问权限
	 * 
	 * @param id
	 */
	public void setIs_admin(byte is_admin) {// 设置用户权限
		this.is_admin = is_admin;
	}
}
