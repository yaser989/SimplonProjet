package org.openup.dao;

public class Names {
	
	private long id;
	private String name;
	public Names() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Names(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Names [id=" + id + ", name=" + name + "]";
	}


	
	

}
