package com.cafe24.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.jblog.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String joinForm() {
		return "user/join";
	}
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join() {
		// 별도의 회원가입 서비스
		return "user/join";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm() {
		return "user/login";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login() {
		// 별도의 로그인 서비스
		return "redirect:/";
	}
}
