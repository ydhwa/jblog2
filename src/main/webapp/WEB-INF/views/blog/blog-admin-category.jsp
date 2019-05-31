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
<script src="${ pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
function deleteCategory(no, posts) {
	if(posts > 0) {
		alert('삭제할 수 없습니다!\n카테고리를 전부 비워주세요.');
	} else {
		$.when(window.location.href = '${pageContext.request.contextPath}/${ authUser.id }/admin/category/delete/' + no).done(function() {
			alert('카테고리가 삭제되었습니다.');	
		});
	}
}

$(document).ready(function() {
	// 카테고리 등록 시 성공 alert
	$('#category').on('submit', function() {
		alert('카테고리 등록이 완료되었습니다.');
    });
});
</script>
</head>
<body>
	<div id="container">
	
		<c:import url='/WEB-INF/views/includes/blog-header.jsp' />
		
		<div id="wrapper">
			<div id="content" class="full-screen">

				<c:import url='/WEB-INF/views/includes/admin-menu.jsp'>
					<c:param name='menu' value='category'/>
				</c:import>
				
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      		
		      		<c:forEach items="${ categoryList }" var="categoryVo" varStatus="status">
		      			<tr>
							<td>${ status.index + 1 }</td>
							<td>${ categoryVo.name }</td>
							<td>${ categoryVo.posts }</td>
							<td>${ categoryVo.description }</td>
							
							<!-- 미분류 카테고리는 삭제하지 못하도록 만들었다. -->
							<td>
								<c:if test="${ status.index > 0 }">
									<img style="cursor: pointer;" onclick="deleteCategory(${ categoryVo.no }, ${ categoryVo.posts })" src="${pageContext.request.contextPath}/assets/images/delete.jpg">
								</c:if>
							</td>
						</tr>
					</c:forEach>  
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
      			<form action="${pageContext.request.contextPath}/${ blogVo.blogId }/admin/category/insert" method="post" id="category">
		      		<table id="admin-cat-add">
		      			<tr>
		      				<td class="t">카테고리명</td>
		      				<td><input type="text" name="name"></td>
		      			</tr>
		      			<tr>
		      				<td class="t">설명</td>
		      				<td><input type="text" name="description"></td>
		      			</tr>
		      			
		      			<input type="hidden" name="blogId" value="${ authUser.id }">
		      			<tr>
		      				<td class="s">&nbsp;</td>
		      				<td><input type="submit" value="카테고리 추가"></td>
		      			</tr>      		      		
		      		</table>
		      	</form>
			</div>
		</div>

		<c:import url='/WEB-INF/views/includes/blog-footer.jsp' />

	</div>
</body>
</html>