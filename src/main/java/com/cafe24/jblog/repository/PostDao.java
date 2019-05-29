package com.cafe24.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.PostVo;

@Repository
public class PostDao {
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private DataSource dataSource;
	
	public Boolean insert(PostVo vo) {
		return 1 == sqlSession.insert("post.insert", vo);
	}
	
	public Boolean update(PostVo vo) {
		return 1 == sqlSession.update("post.update", vo);
	}
	
	public List<PostVo> getList(Long categoryIndex) {
		return sqlSession.selectList("post.getList", categoryIndex);
	}
	
	public PostVo getByIndex(Long categoryNo, Long postIndex) {
		Map<String, Object> map = new HashMap<>();
		map.put("categoryNo", categoryNo);
		map.put("postIndex", postIndex);
		return sqlSession.selectOne("post.getByIndex", map);
	} 
}
