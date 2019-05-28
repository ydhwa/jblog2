package com.cafe24.jblog.repository;

import java.util.HashMap;
import java.util.Map;

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
	
	public UserVo getByIdAndPassword(String id, String password) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("password", password);
		return sqlSession.selectOne("user.getByIdAndPassword", map);
	}
	
	public UserVo getById(String id) {
		return sqlSession.selectOne("user.getById", id);
	}
}
