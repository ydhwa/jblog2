package com.cafe24.jblog.vo;

public class CategoryVo {
	private Long no;
	private String name;
	private String description;
	private String regDate;
	private String blogId;
	
	private Integer posts;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	public Integer getPosts() {
		return posts;
	}
	public void setPosts(Integer posts) {
		this.posts = posts;
	}
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + ", description=" + description + ", regDate=" + regDate
				+ ", blogId=" + blogId + ", posts=" + posts + "]";
	}
}
