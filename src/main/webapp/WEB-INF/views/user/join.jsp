<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!-- spring tag library 사용. 위에 있는 3개는 jstl -->
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${ pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
$(document).ready(function() {
// 	$('#id').change(function() {	// 포커스 떠나고 나서 확인
	// 실시간 변경 감지
	$('#id').on("propertychange change keyup paste input", function() {
		$('#check-button').show();
		$('#check-image').hide();
	});
});

$(function() {
	$('#check-button').click(function() {
		var id = $('#id').val();
		// 4자 이상일 경우에만 중복성 검사
		if(id.length < 4) {
			return;
		}
		
		/* ajax 통신 */
		$.ajax({
			url: "${ pageContext.servletContext.contextPath }/user/api/checkid?id=" + id,
			type: "get",
			dataType: "json",
			data: "",
			success: function(response) {
				console.log(response);
				if(response.result != "success") {
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

// 중복 체크 안하면 양식 제출 안하고 경고창 뜨게 하기
function check() {
	if(!$('#check-image').is(':visible')) {
		alert('아이디 중복 체크를 반드시 해주세요!');
		return false;
	} else {
		return true;
	}
}
</script>
</head>
<body>
	<div class="center-content">
		
		<c:import url='/WEB-INF/views/includes/main-menu.jsp' />

		<form:form
			onsubmit="return check();"
			class="join-form" 
			modelAttribute="userVo"
			name="joinForm" 
			id="join-form" method="post" action="${pageContext.request.contextPath}/user/join">
			<label class="block-label" for="name">이름</label>
			<form:input id="name" path="name" type="text" value="" />
			<spring:hasBindErrors name="userVo">
            	<c:if test="${errors.hasFieldErrors('name') }">
               		<br/>
               		<strong style="color: red"> <spring:message code="${errors.getFieldError( 'name' ).codes[0] }" text="${errors.getFieldError( 'name' ).defaultMessage }" /></strong>
            	</c:if>
         	</spring:hasBindErrors>
         
         
			<label class="block-label" for="blog-id">아이디</label>
			<form:input id="id" path="id" type="text"/> 
			<input id="check-button" type="button" value="id 중복체크">
			<img id="check-image" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">
			<spring:hasBindErrors name="userVo">
            	<c:if test="${errors.hasFieldErrors('id') }">
               		<br/>
               		<strong style="color: red"> <spring:message code="${errors.getFieldError( 'id' ).codes[0] }" text="${errors.getFieldError( 'id' ).defaultMessage }" /></strong>
            	</c:if>
         	</spring:hasBindErrors>
         
         
			<label class="block-label" for="password">패스워드</label>
			<input id="password" name="password" type="password" />
			<spring:hasBindErrors name="userVo">
            	<c:if test="${errors.hasFieldErrors('password') }">
               		<br/>
               		<strong style="color: red"> <spring:message code="${errors.getFieldError( 'password' ).codes[0] }" text="${errors.getFieldError( 'password' ).defaultMessage }" /></strong>
            	</c:if>
         	</spring:hasBindErrors>

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" id="submit" value="가입하기">

		</form:form>
	</div>
</body>
</html>
