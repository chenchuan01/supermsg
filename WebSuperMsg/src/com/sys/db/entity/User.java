package com.sys.db.entity;

import com.sys.base.BaseEntity;
/**
 * 系统用户
 */
public class User extends BaseEntity{
	private String name;
	private String userName;
	private String password;
	private Integer roles = null;
	private String friends_id;
	private Integer online;
	
    public User() {
    }
    public User(String userName) {
		super();
		this.userName = userName;
	}
    
	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	

	public User(Integer id) {
		super.setId(id);
	}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getRoles() {
		return roles;
	}
	public void setRoles(Integer roles) {
		this.roles = roles;
	}
	public String getFriends_id() {
		return friends_id;
	}
	public void setFriends_id(String friends_id) {
		this.friends_id = friends_id;
	}
	public Integer getOnline() {
		return online;
	}
	public void setOnline(Integer online) {
		this.online = online;
	}
	
}
