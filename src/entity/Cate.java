package entity;

public class Cate {

	private int gid;
	private String name;
	
	
	public Cate() {
		super();
	}


	public Cate(int gid, String name) {
		super();
		this.gid = gid;
		this.name = name;
	}


	public int getGid() {
		return gid;
	}


	public void setGid(int gid) {
		this.gid = gid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
}
