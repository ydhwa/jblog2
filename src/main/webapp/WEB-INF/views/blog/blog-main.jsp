<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">

		<c:import url='/WEB-INF/views/includes/blog-header.jsp' />
		
		<!-- path variable -->
		<input type="hidden" name="categoryIndex" value="${ categoryIndex }">

		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${ postVo.title }</h4>
					<p>
						${ postVo.content }
					<p>
				</div>
				<ul class="blog-list">
				
					<c:forEach items="${ postList }" var="postItem" varStatus="status">
						<li><a href="${ pageContext.request.contextPath }/${ blogVo.blogId }/${ categoryIndex }/${ status.index + 1 }">${ postItem.title }</a> <span>${ postItem.regDate }</span></li>
					</c:forEach>
					
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${ blogVo.logo }" onerror="this.src='${pageContext.request.contextPath}/assets/images/spring-logo.jpg';">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
			
				<c:forEach items="${ categoryList }" var="categoryVo" varStatus="status">
					<li><a href="${ pageContext.request.contextPath }/${ blogVo.blogId }/${ status.index + 1 }">${ categoryVo.name } (${ categoryVo.posts })</a></li>
				</c:forEach>
				
			</ul>
		</div>

		<c:import url='/WEB-INF/views/includes/blog-footer.jsp' />		

	</div>
</body>
</html>