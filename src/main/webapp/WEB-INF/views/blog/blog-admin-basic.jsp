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
<!-- Image upload 시 Preview -->
<script type="text/javascript">
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                $('#logoImage').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
	$(document).ready(function() {
    	$('#logoFile').change(function() {
    		readURL(this);
    	});
    	
    	// 블로그 업데이트 시 성공 alert
    	$('#admin-basic').on('submit', function() {
    		alert('블로그 설정 수정이 완료되었습니다.');
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
					<c:param name='menu' value='basic'/>
				</c:import>

				<form action="${pageContext.request.contextPath}/${ blogVo.blogId }/admin/basic/update" method="post" enctype="multipart/form-data" id="admin-basic">
	 		      	<table class="admin-config">
			      		<tr>
			      			<td class="t">블로그 제목</td>
			      			<td><input type="text" size="40" name="title" value="${ blogVo.title }"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">로고이미지</td>
			      			<td>  				
								<img id="logoImage" src="${ pageContext.request.contextPath }${ blogVo.logo }" onerror="this.src='${pageContext.request.contextPath}/assets/images/spring-logo.jpg';">
			      			</td>      			
			      		</tr>      		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td><input id="logoFile" type="file" name="logo-file"></td>      			
			      		</tr>           		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td class="s"><input type="submit" value="기본설정 변경"></td>      			
			      		</tr>           		
			      	</table>
				</form>
			</div>
		</div>

		<c:import url='/WEB-INF/views/includes/blog-footer.jsp' />

	</div>
</body>
</html>