package com.cafe24.security;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.jblog.vo.UserVo;

public class AuthBlogInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 1. Handler 종류 확인(HandlerMethod or DefaultServletHandler)
		if(handler instanceof HandlerMethod == false) {
			return true;
		}
		
		// 2. Casting
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		
		// 3. Method의 @AuthBlog 받아오기
		AuthBlog authBlog = handlerMethod.getMethodAnnotation(AuthBlog.class);
		
		// 5. @Auth가 안 붙어있는 경우
		if(authBlog == null) {
			return true;
		}
		
		// 이전 활동에 대한 파라미터 받아오기
		// PathVariable 얻기
		Map<?, ?> pathVariables = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		String blogId = (String) pathVariables.get("id");
		String category = null;
		String post = null;
		if(blogId != null) {
			category = request.getParameter("category");
			post = request.getParameter("post");
			
			if(category == null) {
				category = "1";
				post = "1";
			} else if(post == null) {
				post = "1";
			}
		}
		
		// 6. @Auth가 (class 또는 method에) 붙어 있기 때문에 인증 여부를 체크해야 한다.
		// 비회원이 관리자 페이지에 접근하려고 할 때
		HttpSession session = request.getSession();
		if(session == null) {	// 인증이 되어있지 않음
			response.sendRedirect(request.getContextPath());
			return false;
		}
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if(authUser == null) {	// 인증이 되어있지 않음
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		// 회원이 관리자 페이지에 접근하려고 할 때
		// 회원 != 블로그 주인
		if(!blogId.equals(authUser.getId())) {
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		// 회원 == 블로그 주인
		return true;
	}

}
