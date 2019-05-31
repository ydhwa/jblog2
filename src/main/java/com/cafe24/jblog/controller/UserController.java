package com.cafe24.jblog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.jblog.service.UserService;
import com.cafe24.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String joinForm(
			@ModelAttribute UserVo userVo) {
		return "user/join";
	}
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(
			@ModelAttribute @Valid UserVo userVo,
			BindingResult result,
			Model model) {
		
		// Validation check
		if(result.hasErrors()) {
			List<ObjectError> errorList = result.getAllErrors();
			for(ObjectError error: errorList) {
				System.out.println(error);				
			}
			model.addAllAttributes(errorList);
			return "user/join";
		}
		
		userService.join(userVo);
		return "user/joinsuccess";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm(
							@RequestParam(value = "result", defaultValue = "") String result,
							@RequestParam(value = "action", defaultValue = "") String action,
							@RequestParam(value = "category", defaultValue = "") String category,
							@RequestParam(value = "post", defaultValue = "") String post,
							Model model) {
		model.addAttribute("result", result);
		model.addAttribute("action", action);
		model.addAttribute("category", category);
		model.addAttribute("post", post);
		
		return "user/login";
	}
}
