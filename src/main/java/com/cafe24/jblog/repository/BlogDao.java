package com.cafe24.jblog.repository;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.BlogVo;

@Repository
public class BlogDao {
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private DataSource dataSource;
	
	public Boolean insert(String userId) {
		return 1 == sqlSession.insert("blog.insert", userId);
	}
	
	public BlogVo getByBlogId(String blogId) {
		return sqlSession.selectOne("blog.getByBlogId", blogId);
	}
	
	public BlogVo getByBlogIdForAdmin(String blogId) {
		return sqlSession.selectOne("blog.getByBlogIdForAdmin", blogId);
	}
	
	public Boolean update(BlogVo vo) {
		return 1 == sqlSession.update("blog.update", vo);
	}
}
