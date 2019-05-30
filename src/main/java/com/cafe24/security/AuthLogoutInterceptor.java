package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthLogoutInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		// 로그인에 성공하면 원래 있었던 곳으로 보낸다.
		// 관리자 페이지는 블로그로 이동시킨다.
		// 메인 페이지: action == null / 블로그는 action이 블로그 아이디
		// (관리자 페이지 역시 action이 블로그 아이디이나, 카테고리와 포스트 정보가 없으므로 의도한 대로 리다이렉트 될 것이다.)
		String path = request.getContextPath();
		String action = request.getParameter("action");
		if(action != null) {
			String category = request.getParameter("category");
			String post = request.getParameter("post");
			
			if("".equals(category)) {
				path += "/" + action + "/1/1";
			} else if("".equals(post)) {
				path += "/" + action + "/" + category + "/1";
			} else {
				path += "/" + action + "/" + category + "/" + post;
			}
		}
		
		response.sendRedirect(path);
		return false;
	}

}
