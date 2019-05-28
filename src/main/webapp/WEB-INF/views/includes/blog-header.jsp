<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<div id="header">
	<h1>${ blogVo.title }</h1>

	<ul>
		<c:if test="${ not empty authUser }">
			<li><a href="">로그인</a></li>
			<li><a href="">로그아웃</a></li>
		</c:if>

		<c:if test="${ authUser.id == blogVo.blogId }">
			<li><a href="">블로그 관리</a></li>
		</c:if>
	</ul>
</div>