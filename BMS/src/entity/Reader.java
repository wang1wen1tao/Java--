package entity;
/**
 * 类功能：用于设置、获取读者对象的属性信息
 * @author zzl
 *
 */

public class Reader {
	
	/** 成员变量*/
	private String id;    // 表示读者编码
	private String name;// 表示读者姓名
	private String type;// 表示读者类型
	private String sex;// 表示读者性别
	private int max_num;// 表示读者最大借书数量
	private int days_num;// 表示读者最多借书天数

	/**
	 * 成员方法的功能:获取读者编号
	 * @return
	 */
	public String getId() {// 获取读者编码
		return id;
	}
	
	/**
	 * 成员方法的功能:设置读者编号
	 * @return
	 */
	public void setId(String id) {// 设置读者编码
		this.id = id;
	}

	/**
	 * 成员方法的功能:获取读者姓名
	 * @return
	 */
	public String getName() {// 获取读者名称
		return name;
	}

	/**
	 * 成员方法的功能:设置读者姓名
	 * @param name
	 */
	public void setName(String name) {// 设置读者名称
		this.name = name;

	}

	/**
	 * 成员方法的功能:获取读者类型
	 * @return
	 */
	public String getType() {// 获取读者类型
		return type;
	}

	/**
	 * 成员方法的功能:设置者类型
	 * @param type
	 */
	public void setType(String type) {// 设置读者类型
		this.type = type;
	}

	/**
	 * 成员方法的功能:获取读者性别
	 * @return
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * 成员方法的功能:设置读者性别
	 * @param sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 成员方法的功能:获取读者最大借书数量
	 * @return
	 */
	public int getMax_num() {// 获取读者最大借书数量
		return max_num;
	}

	/**
	 * 成员方法的功能:设置读者最大借书数量
	 * @param max_num
	 */
	public void setMax_num(int max_num) {// 设置读者最大借书数量
		this.max_num = max_num;
	}

	/**
	 * 成员方法的功能:获取读者最多借书天数
	 * @return
	 */
	public int getDays_num() {// 获取读者最多借书天数
		return days_num;
	}

	/**
	 * 成员方法的功能:设置读者最多借书天数
	 * @param days_num
	 */
	public void setDays_num(int days_num) {// 设置读者最多借书天数
		this.days_num = days_num;
	}
	
}
