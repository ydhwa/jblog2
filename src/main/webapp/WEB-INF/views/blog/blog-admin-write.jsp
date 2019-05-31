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

<!-- Smart Editor -->
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/assets/editor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script src="${ pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript">
</script>

</head>
<body>
	<div id="container">
	
		<c:import url='/WEB-INF/views/includes/blog-header.jsp' />

		<div id="wrapper">
			<div id="content" class="full-screen">
			
				<c:import url='/WEB-INF/views/includes/admin-menu.jsp'>
					<c:param name='menu' value='write'/>
				</c:import>

				<form action="${pageContext.request.contextPath}/${ blogVo.blogId }/admin/write" method="post" id="post-write">
			      	<table class="admin-cat-write">
			      		<tr>
			      			<td class="t">제목</td>
			      			<td>
			      				<input type="text" size="60" name="title">
				      			<select name="categoryNo">
				      			
				      				<c:forEach items="${ categoryList }" var="categoryVo">
				      					<option value="${ categoryVo.no }">${ categoryVo.name } (${ categoryVo.posts })</option>
				      				</c:forEach>
				      				
				      			</select>
				      		</td>
			      		</tr>
			      		<tr>
			      			<td class="t">내용</td>
			      				<td><textarea name="contents" id="contents" style="min-width: 490px; width: 90%; height: 300px;"></textarea>
							</td>
			      		</tr>
			      		<tr>
			      			<td>&nbsp;</td>
			      			<td class="s"><input type="button" value="포스트하기" onclick="submitContents(this);"></td>
			      		</tr>
			      	</table>
				</form>
			</div>
		</div>
		
		<c:import url='/WEB-INF/views/includes/blog-footer.jsp' />

	</div>
	
</body>
<!-- Smart Editor -->	
<script type="text/javascript">
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
		oAppRef: oEditors,
		elPlaceHolder: "contents",
		sSkinURI: "${ pageContext.servletContext.contextPath }/assets/editor/SmartEditor2Skin.html",
		fCreator: "createSEditor2"
	});
	
	/* 편집 내용 서버로 전송 */
	// 저장을 위한 액션 시 submitContents 호출된다고 하자.
	function submitContents(elClickedObj) {
		// 에디터 내용이 textarea에 적용됨
		oEditors.getById["contents"].exec("UPDATE_CONTENTS_FIELD", []);
		
		// 에디터의 내용에 대한 값 검증은 document.getElementById("contents").value를 이용하여 처리한다.
		
		try {
			elClickedObj.form.submit();
			alert('포스트 작성이 완료되었습니다.');
		} catch(e) {}		
	}
</script>
</html>