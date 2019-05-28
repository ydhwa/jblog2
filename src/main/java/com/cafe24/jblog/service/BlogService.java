package com.cafe24.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.BlogDao;
import com.cafe24.jblog.repository.CategoryDao;
import com.cafe24.jblog.repository.PostDao;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private PostDao postDao;
	
	
}
