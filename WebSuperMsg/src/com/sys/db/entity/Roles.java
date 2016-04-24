package com.sys.db.entity;


/**
 *@author chenchuan
 *@date 2016Äê3ÔÂ5ÈÕ
 *Roles.java
 */
public class Roles {
	
	private Integer index;
	
	private String name;
	
	
	public Roles() {
		super();
	}
	public Roles(Integer index, String name) {
		super();
		this.index = index;
		this.name = name;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
