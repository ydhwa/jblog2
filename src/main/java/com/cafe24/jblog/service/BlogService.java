package com.cafe24.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog.repository.BlogDao;
import com.cafe24.jblog.repository.CategoryDao;
import com.cafe24.jblog.repository.PostDao;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;

@Service
public class BlogService {
	private static final String SAVE_LOGO_PATH = "/jblog-uploads/logo";
	private static final String URL = "/images";

	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private PostDao postDao;

	public BlogVo getBlog(String id) {
		return blogDao.getByBlogId(id);
	}

	public Map<String, Object> showBlogMainPage(String id, Long categoryIndex, Long postIndex) {
		Map<String, Object> map = new HashMap<String, Object>();

		Long categoryNo = categoryDao.getNoByIndex(id, categoryIndex);

		map.put("categoryIndex", categoryIndex);
		map.put("blogVo", blogDao.getByBlogId(id));
		map.put("categoryList", categoryDao.getList(id));
		map.put("postList", postDao.getList(categoryNo));
		map.put("postVo", postDao.getByIndex(categoryNo, postIndex));

		return map;
	}

	// ===================================== 관리자 서비스

	// 블로그 설정 관리부
	public void updateBlogConfig(String blogId, String blogTitle, String blogLogoUrl) {
		BlogVo blogVo = new BlogVo();
		blogVo.setBlogId(blogId);
		blogVo.setTitle(blogTitle);
		blogVo.setLogo(blogLogoUrl);
		blogDao.update(blogVo);
	}
	// 이미지 파일 업로드
	public String fileUpload(MultipartFile multipartFile) {
		String url = "";
		try {
			if(multipartFile.isEmpty()) {
				return url;
			}
		
			String originalFileName = multipartFile.getOriginalFilename();
			String extName = originalFileName.substring(originalFileName.lastIndexOf('.') + 1);
			String saveFileName = generateSaveFileName(extName);
		
			byte[] fileData = multipartFile.getBytes();
			OutputStream os = new FileOutputStream(SAVE_LOGO_PATH + "/" + saveFileName);
			os.write(fileData);
			os.close();
			
			url = URL + "/" + saveFileName;
		
		} catch (IOException e) {
			throw new RuntimeException("Fileupload ERROR: " + e);
		}
		return url;
	}
	private String generateSaveFileName(String extName) {
		Calendar calendar = Calendar.getInstance();
		String filename = String.format("%s%s%s%s%s%s%s%s.%s", 
				calendar.get(Calendar.YEAR), 
				calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DATE),
				calendar.get(Calendar.HOUR),
				calendar.get(Calendar.MINUTE),
				calendar.get(Calendar.SECOND),
				calendar.get(Calendar.MILLISECOND),
				UUID.randomUUID().toString(),	// 동시에 다른 사용자가 파일 업로드를 진행할 수 있으므로
				extName);

		return filename;
	}
	

	// 카테고리 관리부
	public List<CategoryVo> showCategoryPage(String id) {
		return categoryDao.getListForAdmin(id);
	}

	public Boolean insertCategory(CategoryVo categoryVo) {
		return categoryDao.insert(categoryVo);
	}

	public Boolean deleteCategory(Long categoryNo) {
		return categoryDao.delete(categoryNo);
	}

	// 게시글 작성부
	public List<CategoryVo> showPostWritePage(String id) {
		return categoryDao.getListForAdmin(id);
	}
	public Boolean writePost(PostVo postVo) {
		return postDao.insert(postVo);
	}


}
