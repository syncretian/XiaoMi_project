package entity;

import java.util.Date;

public class User {

	private int id;
	private String name;
	private Integer sex;
	private String phone_number;
	private String area;
	private Integer manager;
	private String username;
	private String password;
	private String photo;
	private Date create_time;
	
	
	public User() {
		super();
	}

	

	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Integer getSex() {
		return sex;
	}



	public void setSex(Integer sex) {
		this.sex = sex;
	}



	public String getPhone_number() {
		return phone_number;
	}



	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}



	public String getArea() {
		return area;
	}



	public void setArea(String area) {
		this.area = area;
	}



	public Integer getManager() {
		return manager;
	}



	public void setManager(Integer manager) {
		this.manager = manager;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getPhoto() {
		return photo;
	}



	public void setPhoto(String photo) {
		this.photo = photo;
	}



	public Date getCreate_time() {
		return create_time;
	}



	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", sex=" + sex + ", phone_number=" + phone_number + ", area="
				+ area + ", manager=" + manager + ", username=" + username + ", password=" + password + ", photo="
				+ photo + ", create_time=" + create_time + "]";
	}


	

	
}
