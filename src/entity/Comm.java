package entity;

import java.util.Date;

public class Comm {
	private String name;
	private String state;
	private String begin;
	private String end;
	
	public Comm() {
		super();
	}

	
	public Comm(String name, String state, String begin, String end) {
		super();
		this.name = name;
		this.state = state;
		this.begin = begin;
		this.end = end;
	}






	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "Comm [name=" + name + ", state=" + state + ", begin=" + begin + ", end=" + end + "]";
	}
	
	

}
