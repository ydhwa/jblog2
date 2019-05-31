package com.cafe24.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cafe24.jblog.service.UserService;
import com.cafe24.jblog.vo.UserVo;
import com.cafe24.security.AuthUser;

@Controller
public class MainController {
	
	@Autowired
	private UserService userService;

	@RequestMapping({"", "/main"})
	public String main(
			@AuthUser UserVo authUser,
			Model model) {
		
		// 환영 메시지 출력
		if(authUser != null) {
			model.addAttribute("userName", userService.getUser(authUser).getName());
		}
		
		return "main/index";
	}
}
