<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%-- 로그인한 사용자 정보 Authentication 객체의 필요한 property 들을 변수에 담아 사용 가능  --%>
<sec:authentication property="name" var="username"/>  
<sec:authentication property="authorities" var="authorities"/> 
<sec:authentication property="principal" var="userdetails"/>    
        
<!DOCTYPE html>
<html lang="ko">
 
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
 
 	<script src="https://kit.fontawesome.com/51772bd9bd.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <link href="/css/header.css" rel="stylesheet">
</head>
<body>
	<header class="fs-3 px-3 fw-bold" style="font-family: 'Nanum Gothic', sans-serif;">
		<span class="fs-3 float-start ps-3" style="padding-right:10%;">
			<a href="${pageContext.request.contextPath}/home">TEST</a>
		</span>
		<div class="px-4 float-start" style="margin-top:1px; border-radius:25px; border:2px solid black;">
			<form id="searchForm" action="${pageContext.request.contextPath}/item/search" method="GET">
				<input class="fs-4" style="border:0px;" type="text" name="search">
				<i style="cursor:pointer" id="listSearch" class="fa-solid fa-magnifying-glass"></i>
			</form>
		</div>
		<a class="ps-3" href="#"><i class="fa-regular fa-heart"></i></a>
		<a class="pe-3" href="${pageContext.request.contextPath}/item/cart"><i class="fa-solid fa-bag-shopping"></i></a>
		<a class="pe-3" href="${pageContext.request.contextPath}/item/buy"><i class="fa-solid fa-ticket"></i></a>
		<!-- 로그인 안했을때는 로그인 form 보여주기 -->
		<sec:authorize access="isAnonymous()">
		<a class="btn btn-outline-dark float-end mt-1 fw-bold mx-1" type="submit" href="${pageContext.request.contextPath}/user/register">SignUp</a>
		<a class="btn btn-outline-dark float-end mt-1 fw-bold mx-1" type="submit" href="${pageContext.request.contextPath}/user/login">Login</a>
		</sec:authorize>
		<!-- 로그인 했을때는 username 과 로그아웃 form 보여주기 -->
		<sec:authorize access="isAuthenticated()">
		<form class="float-end mt-1" action="${pageContext.request.contextPath}/user/logout" method="POST">
			<!--TODO : 로그아웃후 다시 돌아오기 -->
			<span class="d-flex">
				<span class="text-dark fs-6 p-2">
					<span style="margin-top:-5px;">${userdetails.user.name}(${username})</span>
					<span><a style="font-size:1.3rem; color:black;" href="${pageContext.request.contextPath}/user/userinfo"><i class="fa-solid fa-gear"></i></a></span>
				</span> 
				<span><button class="btn btn-outline-dark mb-1 fw-bold" type="submit">Logout</button></span>
			</span>
		</form>
		</sec:authorize>
		<sec:authorize access="hasRole('ADMIN')">
			<a class="nav-link active float-end fs-5" href="${pageContext.request.contextPath }/admin/item/manage">대시보드</a>
			<a class="nav-link active float-end fs-5" href="${pageContext.request.contextPath }/admin/item/list">관리</a>
        </sec:authorize>
	</header>
	<script>
		let listSearch = document.getElementById("listSearch");
		listSearch.onclick = function() {
			let searchForm = document.getElementById("searchForm");
			searchForm.submit();
		}
	</script>
	<div style="height:100px;"></div>
</body>
</html>


