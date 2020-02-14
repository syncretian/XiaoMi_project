package entity;

import java.util.Date;

public class Commodity {
	private Integer cid;
	private Integer gid;
	private String category_name;
	private String name;
	private String color;
	private String size;
	private double price;
	private String description;
	private String full_description;
	private String pic;
	private Integer state;
	private String version;
	private Date product_date;
	
	private Integer number;  //购物车里商品数量
	
	private String picc;   //修改前的图片名

	
	public Commodity() {
		super();
	}



	public Commodity(Integer cid, String category_name, String name, String color, String size, double price,
			String description, String full_description, String pic, Integer state, String version, Date product_date) {
		super();
		this.cid = cid;
		this.category_name = category_name;
		this.name = name;
		this.color = color;
		this.size = size;
		this.price = price;
		this.description = description;
		this.full_description = full_description;
		this.pic = pic;
		this.state = state;
		this.version = version;
		this.product_date = product_date;
	}



	public Integer getCid() {
		return cid;
	}



	public void setCid(Integer cid) {
		this.cid = cid;
	}



	public Integer getGid() {
		return gid;
	}



	public void setGid(Integer gid) {
		this.gid = gid;
	}



	public String getCategory_name() {
		return category_name;
	}



	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getColor() {
		return color;
	}



	public void setColor(String color) {
		this.color = color;
	}



	public String getSize() {
		return size;
	}



	public void setSize(String size) {
		this.size = size;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getFull_description() {
		return full_description;
	}



	public void setFull_description(String full_description) {
		this.full_description = full_description;
	}



	public String getPic() {
		return pic;
	}



	public void setPic(String pic) {
		this.pic = pic;
	}



	public Integer getState() {
		return state;
	}



	public void setState(Integer state) {
		this.state = state;
	}



	public String getVersion() {
		return version;
	}



	public void setVersion(String version) {
		this.version = version;
	}



	public Date getProduct_date() {
		return product_date;
	}



	public void setProduct_date(Date product_date) {
		this.product_date = product_date;
	}



	public String getPicc() {
		return picc;
	}



	public void setPicc(String picc) {
		this.picc = picc;
	}







	@Override
	public String toString() {
		return "Commodity [cid=" + cid + ", gid=" + gid + ", category_name=" + category_name + ", name=" + name
				+ ", color=" + color + ", size=" + size + ", price=" + price + ", description=" + description
				+ ", full_description=" + full_description + ", pic=" + pic + ", state=" + state + ", version="
				+ version + ", product_date=" + product_date + ", number=" + number + ", picc=" + picc + "]";
	}



	public Integer getNumber() {
		return number;
	}



	public void setNumber(Integer number) {
		this.number = number;
	}




	
}
