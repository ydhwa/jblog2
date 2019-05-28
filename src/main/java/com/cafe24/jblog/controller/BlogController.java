package com.cafe24.jblog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.jblog.service.BlogService;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	private BlogService blogService;

	@RequestMapping({"", "/{pathNo1}", "/{pathNo1}/{pathNo2}"})
	public String index(
						@PathVariable String id,
						@PathVariable Optional<Long> pathNo1,
						@PathVariable Optional<Long> pathNo2,
						ModelMap model) {
		
		
		return "blog/blog-main";
	}
	
	// ---------- 여기부터는 관리자 페이지 ----------
	@RequestMapping("/admin/basic")
	public String adminBasic() {
		return "blog/blog-admin-basic";
	}
	@RequestMapping(value = "/admin/basic/update", method = RequestMethod.POST)
	public String updateBasic() {
		// 별도의 블로그 기본 관리 업데이트 서비스
		return "redirect:/admin/basic";
	}
	
	@RequestMapping("/admin/category")
	public String adminCategory() {
		return "blog/blog-admin-category";
	}
	@RequestMapping(value = "/admin/category/insert", method = RequestMethod.POST)
	public String insertCategory() {
		// 별도의 카테고리 추가 서비스
		return "redirect:/admin/category";
	}
	@RequestMapping("/admin/category/delete/{categoryNo}")
	public String deleteCategory(@PathVariable Optional<Long> categoryNo) {
		// 별도의 카테고리 삭제 서비스
		return "redirect:/admin/category";
	}
	
	@RequestMapping("/admin/write")
	public String adminWrite() {
		return "blog/blog-admin-write";
	}
	@RequestMapping(value = "/admin/write", method = RequestMethod.POST)
	public String writePost() {
		// 별도의 블로그 글쓰기 서비스
		return "redirect:/admin/write";
	}

}
