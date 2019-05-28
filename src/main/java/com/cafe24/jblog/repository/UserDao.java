package com.cafe24.jblog.repository;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private DataSource dataSource;
	
	public Boolean insert(UserVo vo) {
		return 1 == sqlSession.insert("user.insert", vo);
	}
	
	public UserVo get(UserVo vo) {
		return sqlSession.selectOne("user.get", vo);
	}
}
