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
$(function() {
	$('#id').change(function() {	// 포커스 떠나고 나서 확인
		$('#check-button').show();
		$('#check-image').hide();
	});
	
	$('#check-button').click(function() {
		var email = $('#email').val();
		if(email == '') {
			return;
		}
		
		/* ajax 통신 */
		$.ajax({
			url: "${ pageContext.servletContext.contextPath }/user/api/checkid?id=" + email,
			type: "get",
			dataType: "json",
			data: "",
			success: function(response) {
				if(response.result != "success") {
// 					console.error(response.message);
					console.log(response);
					return;
				}
				
				if(response.data == true) {
					alert('이미 존재하는 아이디입니다.\n다른 아이디를 사용해주세요.');
					$('#id').focus();
					$('#id').val("");
					return ;
				}
				
				$('#check-button').hide();
				$('#check-image').show();
			},
			error: function(xhr, error) {
				console.error("[ERROR] " + error);
			}
		});
	});
});
</script>
</head>
<body>
	<div class="center-content">
		
		<c:import url='/WEB-INF/views/includes/main-menu.jsp' />

		<form:form
			class="join-form" 
			modelAttribute="userVo"
			name="joinForm" 
			id="join-form" method="post" action="${pageContext.request.contextPath}/user/join">
			<label class="block-label" for="name">이름</label>
			<form:input id="name" path="name" type="text" value="" />
			<form:errors path="name" style="display: block; font-weight: bold; color: red; text-align: left; padding: 0;"/>
			
			<label class="block-label" for="blog-id">아이디</label>
			<form:input id="blog-id" path="id" type="text"/> 
			<input id="check-button" type="button" value="id 중복체크">
			<img id="check-image" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">
			<form:errors path="id" style="display: block; font-weight: bold; color: red; text-align: left; padding: 0;"/>

			<label class="block-label" for="password">패스워드</label>
			<input id="password" name="password" type="password" />

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form:form>
	</div>
</body>
</html>
