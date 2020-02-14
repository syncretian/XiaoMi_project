package entity;

public class Trolley {

	private Integer tid;
	private Integer uid;
	private Integer cid;
	private Integer number;
	
	
	
	
	public Trolley() {
		super();
	}
	public Trolley(Integer uid, Integer cid) {
		super();
		this.uid = uid;
		this.cid = cid;
	}
	public Trolley(Integer tid, Integer uid, Integer cid, Integer number) {
		super();
		this.tid = tid;
		this.uid = uid;
		this.cid = cid;
		this.number = number;
	
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}


	@Override
	public String toString() {
		return "Trolley [tid=" + tid + ", uid=" + uid + ", cid=" + cid + ", number=" + number + "]";
	}
	
	
}
