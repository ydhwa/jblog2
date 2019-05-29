package com.cafe24.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.CategoryVo;

@Repository
public class CategoryDao {
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private DataSource dataSource;
	
	public Boolean insert(CategoryVo vo) {
		return 1 == sqlSession.insert("category.insert", vo);
	}
	
	public Boolean delete(Long no) {
		return 1 == sqlSession.delete("category.delete", no);
	}
	
	// 리스트를 얻는 로직에서 동적 쿼리를 만들 경우 파라미터를 별도로 추가해야 하기 때문에 별도로 생성함
	public List<CategoryVo> getListForAdmin(String userId) {
		return sqlSession.selectList("category.getListForAdmin", userId);
	}
	public List<CategoryVo> getList(String userId) {
		return sqlSession.selectList("category.getList", userId); 
	}
	
	public Long getNoByIndex(String id, Long categoryIndex) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("categoryIndex", categoryIndex);
		
		return sqlSession.selectOne("category.getNoByIndex", map);
	}
}
