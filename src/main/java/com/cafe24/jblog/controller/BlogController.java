package com.cafe24.jblog.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog.service.BlogService;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;
import com.cafe24.jblog.vo.UserVo;
import com.cafe24.security.AuthBlog;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	// 블로그 페이지
	@RequestMapping({"", "/{pathNo1}", "/{pathNo1}/{pathNo2}"})
	public String index(
						@PathVariable String id,
						@PathVariable Optional<Long> pathNo1,
						@PathVariable Optional<Long> pathNo2,
						Model model) {
		Long categoryIndex = pathNo1.isPresent() ? pathNo1.get() : 1L;
		Long postIndex = pathNo2.isPresent() ? pathNo2.get() : 1L;
		
		model.addAllAttributes(blogService.showBlogMainPage(id, categoryIndex, postIndex));
		
		return "blog/blog-main";
	}
	
	// 관리자 페이지
	// 블로그 설정 관리
	@AuthBlog
	@RequestMapping("/admin/basic")
	public String adminBasic(
							@PathVariable String id,
							Model model) {
		model.addAttribute("blogVo", blogService.getBlog(id));
		return "blog/blog-admin-basic";
	}
	@AuthBlog
	@RequestMapping(value = "/admin/basic/update", method = RequestMethod.POST)
	public String updateBasic(
							@PathVariable String id,
							@RequestParam("title") String blogTitle,
							@RequestParam("logo-file") MultipartFile multipartFile) {
		blogService.updateBlogConfig(id, blogTitle, multipartFile);
		return "redirect:/" + id + "/admin/basic";
	}
	
	// 카테고리 관리
	@AuthBlog
	@RequestMapping("/admin/category")
	public String adminCategory(
								Model model, 
								HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		model.addAllAttributes(blogService.showCategoryPage(authUser.getId()));
		return "blog/blog-admin-category";
	}
	@AuthBlog
	@RequestMapping(value = "/admin/category/insert", method = RequestMethod.POST)
	public String insertCategory(
								@PathVariable String id, 
								@ModelAttribute CategoryVo categoryVo) {
		blogService.insertCategory(categoryVo);
		return "redirect:/" + id + "/admin/category";
	}
	@AuthBlog
	@RequestMapping("/admin/category/delete/{categoryNo}")
	public String deleteCategory(
								@PathVariable String id, 
								@PathVariable Optional<Long> categoryNo,
								Model model) {
		if(categoryNo.isPresent()) {
			blogService.deleteCategory(categoryNo.get());
		}
		return "redirect:/" + id + "/admin/category";
	}
	
	// 게시글 작성
	@AuthBlog
	@RequestMapping("/admin/write")
	public String adminWrite(
								@PathVariable String id,
								Model model) {
		model.addAllAttributes(blogService.showPostWritePage(id));
		return "blog/blog-admin-write";
	}
	@AuthBlog
	@RequestMapping(value = "/admin/write", method = RequestMethod.POST)
	public String writePost(
							@PathVariable String id, 
							@ModelAttribute PostVo postVo) {
		blogService.writePost(postVo);
		return "redirect:/" + id + "/admin/write";
	}
}
