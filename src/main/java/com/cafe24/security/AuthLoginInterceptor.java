package com.cafe24.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.jblog.service.UserService;
import com.cafe24.jblog.vo.UserVo;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		UserVo userVo = new UserVo();
		userVo.setId(id);
		userVo.setPassword(password);
		UserVo authUser = userService.getUser(userVo);
		if(authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		// session 처리
		HttpSession session = request.getSession(true);
		List<Integer> viewList = new ArrayList<>();	// 들렀던 페이지 목록
		session.setAttribute("authUser", authUser);
		session.setAttribute("viewList", viewList);
		
		response.sendRedirect(request.getContextPath());
		
		return false;
	}

}
