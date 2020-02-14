package entity;

public class Order {
	private String oid;
	private Double sum_price;
	private String description ;
	
	

	
	
	public Order() {
		// TODO Auto-generated constructor stub
	
		
	}


	public Order(String orders_number, Double sum, String str) {
		// TODO Auto-generated constructor stub
		oid=orders_number;
		sum_price=sum;
		description=str;
	}


	public Double getSum_price() {
		return sum_price;
	}
	public void setSum_price(Double sum_price) {
		this.sum_price = sum_price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	public String getOid() {
		return oid;
	}


	public void setOid(String oid) {
		this.oid = oid;
	}


	@Override
	public String toString() {
		return "Order [oid=" + oid + ", sum_price=" + sum_price + ", description=" + description + "]";
	}
	

}
