package com.cafe24.jblog.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserVo {
	@Length(min = 4, max = 20)
	@NotEmpty
	private String id;
	
	@Length(min = 2, max = 20)
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String password;
	private String regDate;
	
	public UserVo() {
	}
	public UserVo(String id) {
		this.id = id;
	}
	public UserVo(String id, String password) {
		this.id = id;
		this.password = password;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "UserVo [id=" + id + ", name=" + name + ", password=" + password + ", regDate=" + regDate + "]";
	}
}
