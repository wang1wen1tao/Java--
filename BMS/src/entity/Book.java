package entity;
/**
 * �๦�ܣ��������á���ȡͼ������������Ϣ
 * @author Administrator
 *
 */
public class Book {
	/* ��Ա����*/
	private String id;    // ��ʾͼ��ı��
	private String name;// ��ʾͼ�������
	private String type;// ��ʾͼ�������
	private String author;// ��ʾͼ�������
	private String translator;// ��ʾͼ�������
	private String publisher;// ��ʾͼ��ĳ�����
	private String publish_time;// ��ʾͼ��ĳ���ʱ��
	private int stock;// ��ʾͼ��Ŀ����
	private double price;// ��ʾͼ��ļ۸�

	/**
	 * ��Ա�����Ĺ���:��ȡͼ����
	 * @return
	 */
	
	public String getId() {// ��ȡͼ��ı��
		return id;
	}

	/**
	 * ��Ա�����Ĺ���:����ͼ����
	 * @param id
	 */
	public void setId(String id) {// ����ͼ��ı��
		this.id = id;
	}

	/**
	 * ��Ա�����Ĺ���:��ȡͼ������
	 * @return
	 */
	public String getName() {// ��ȡͼ�������
		return name;
	}

	/**
	 * ��Ա�����Ĺ���:����ͼ������
	 * @param name
	 */
	public void setName(String name) {// ����ͼ�������
		this.name = name;
	}

	/**
	 * ��Ա�����Ĺ���:��ȡͼ������
	 * @return
	 */
	public String getType() {// ��ȡͼ�������
		return type;
	}

	/**
	 * ��Ա�����Ĺ���:����ͼ������
	 * @param type
	 */
	public void setType(String type) {// ����ͼ�������
		this.type = type;
	}

	/**
	 * ��Ա�����Ĺ���:��ȡͼ������
	 * @return
	 */
	public String getAuthor() {// ��ȡͼ�������
		return author;
	}

	/**
	 * ��Ա�����Ĺ���:����ͼ������
	 * @param author
	 */
	public void setAuthor(String author) {// ����ͼ�������
		this.author = author;
	}

	/**
	 * ��Ա�����Ĺ���:��ȡͼ�������
	 * @return
	 */
	public String getTranslator() {// ��ȡͼ�������
		return translator;
	}

	/**
	 * ��Ա�����Ĺ���:����ͼ�������
	 * @param translator
	 */
	public void setTranslator(String translator) {// ����ͼ�������
		this.translator = translator;
	}

	/**
	 * ��Ա�����Ĺ���:��ȡͼ��ĳ�����
	 * @return
	 */
	public String getPublisher() {// ��ȡͼ��ĳ�����
		return publisher;
	}

	/**
	 * ��Ա�����Ĺ���:����ͼ��ĳ�����
	 * @param publisher
	 */
	public void setPublisher(String publisher) {// ����ͼ��ĳ�����
		this.publisher = publisher;
	}

	/**
	 * ��Ա�����Ĺ���:��ȡͼ��ĳ���ʱ��
	 * @return
	 */
	public String getPublish_time() {// ��ȡͼ��ĳ���ʱ��
		return publish_time;
	}

	/**
	 * ��Ա�����Ĺ���:����ͼ��ĳ���ʱ��
	 * @param publish_time
	 */
	public void setPublish_time(String publish_time) {// ����ͼ��ĳ���ʱ��
		this.publish_time = publish_time;
	}

	/**
	 * ��Ա�����Ĺ���:��ȡͼ��Ŀ����
	 * @return
	 */
	public int getStock() {// ��ȡͼ��Ŀ����
		return stock;
	}

	/**
	 * ��Ա�����Ĺ���:����ͼ��Ŀ����
	 * @param stock
	 */
	public void setStock(int stock) {// ����ͼ��Ŀ����
		this.stock = stock;
	}

	/**
	 * ��Ա�����Ĺ���:��ȡͼ��ļ۸�
	 * @return
	 */
	public double getPrice() {// ��ȡͼ��ļ۸�
		return price;
	}

	/**
	 * ��Ա�����Ĺ���:����ͼ��ļ۸�
	 * @param price
	 */
	public void setPrice(double price) {// ����ͼ��ļ۸�
		this.price = price;
	}
}
