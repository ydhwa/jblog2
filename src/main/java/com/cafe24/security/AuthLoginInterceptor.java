package com.cafe24.security;

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
			response.sendRedirect(request.getContextPath() + "/user/login?result=fail");
			return false;
		}
		
		// session 처리
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		
		// 로그인에 성공하면 원래 있었던 곳으로 보낸다.
		// 메인 페이지: action == null / 블로그는 action이 블로그 아이디
		String path = request.getContextPath();
		String action = request.getParameter("action");
		if(action != null && !("".equals(action))) {
			String category = request.getParameter("category");
			String post = request.getParameter("post");
			
			if(category == null) {
				path += action + "/1/1";
			} else if(post == null) {
				path += "/" + action + "/" + category + "/1";
			} else {
				path += "/" + action + "/" + category + "/" + post;
			}
		}
		
		response.sendRedirect(path);
		
		return false;
	}

}
