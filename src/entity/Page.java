package entity;

import java.util.List;

public class Page<T> {
	private int size;
	
	private int sumNum;
	
	private int pageNum;
	
	private int current_page;
	
	private List<T> users;

	public Page(int size, int sumNum, int current_page) {
		super();
		//每页行数
		this.size = size;
		//总用户数
		this.sumNum = sumNum;
		//设置页数
		if(size > 0) {
			this.pageNum = sumNum%size==0? sumNum/size : sumNum/size+1;
		}else {
			this.pageNum=0;
		}
		//当前页
		if(current_page < 1){
			
			this.current_page= 1;  //首页
			
		}else if(current_page > this.pageNum) {			
				this.current_page = pageNum;  //尾页,,,如果数据为0，页面数为0，当前页面也为0
		
		}else {
			
			this.current_page = current_page;
		}
		
		
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getSumNum() {
		return sumNum;
	}

	public void setSumNum(int sumNum) {
		this.sumNum = sumNum;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getCurrent_page() {
		return current_page;
	}

	public void setCurrent_page(int current_page) {
		this.current_page = current_page;
	}

	public List<T> getUsers() {
		return users;
	}

	public void setUsers(List<T> list) {
		this.users = list;
	}

	@Override
	public String toString() {
		return "Page [size=" + size + ", sumNum=" + sumNum + ", pageNum=" + pageNum + ", current_page=" + current_page
				+ ", users=" + users + "]";
	}

	
	
	

}
