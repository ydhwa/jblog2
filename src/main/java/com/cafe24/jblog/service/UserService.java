package com.cafe24.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.jblog.repository.BlogDao;
import com.cafe24.jblog.repository.CategoryDao;
import com.cafe24.jblog.repository.UserDao;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao categoryDao;
	
	public UserVo getUser(UserVo userVo) {
		return userDao.get(userVo);
	}

	/*
	 * 1. 유저 테이블에 유저 등록
	 * 2. 블로그 생성
	 * 3. 블로그의 Default 카테고리 생성
	 */
	@Transactional
	public void join(UserVo userVo) {
		userDao.insert(userVo);
		blogDao.insert(userVo.getId());
		categoryDao.insert(new CategoryVo(userVo.getId()));
	}
	
	public Boolean existId(String id) {
		return userDao.get(new UserVo(id)) != null;
	}
	
	
}
