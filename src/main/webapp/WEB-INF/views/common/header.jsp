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
		<a class="pe-3" href="#"><i class="fa-solid fa-bag-shopping"></i></a>
		<!-- 로그인 안했을때는 로그인 form 보여주기 -->
		<sec:authorize access="isAnonymous()">
		<a class="btn btn-outline-dark float-end mt-1 fw-bold mx-1" type="submit" href="${pageContext.request.contextPath}/user/register">SignUp</a>
		<a class="btn btn-outline-dark float-end mt-1 fw-bold mx-1" type="submit" href="${pageContext.request.contextPath}/user/login">Login</a>
		</sec:authorize>
		<!-- 로그인 했을때는 username 과 로그아웃 form 보여주기 -->
		<sec:authorize access="isAuthenticated()">
		<form class="float-end mt-1" action="${pageContext.request.contextPath}/user/logout" method="POST">
			<!--TODO : 로그아웃후 다시 돌아오기 -->
			<span class="d-flex"><span class="text-dark fs-6 p-2">
			<span TODO="">${userdetails.user.name}(${username})</span>
			</span> 
			<span><button class="btn btn-outline-dark mb-1 fw-bold" type="submit">Logout</button></span>
			</span>
		</form>
		</sec:authorize>
	</header>
	<script>
		let listSearch = document.getElementById("listSearch");
		listSearch.onclick = function() {
			let searchForm = document.getElementById("searchForm");
			searchForm.submit();
		}
	</script>
	<div class="container" style="font-size:17px; margin-top:100px; padding-top:10px; font-family: 'Nanum Gothic', sans-serif;">
		<div class="bs-example">
		<ul id="nav" style="width:900px; margin:auto;" class="nav nav-pills clearfix right" role="tablist">
	      <li class="dropdown"><a href="#">베스트</a></li>
	      <li class="dropdown"><a href="#">신상</a></li>
	      <li class="dropdown"><a data-toggle="dropdown" href="${pageContext.request.contextPath}/item/list?category=1">아우터</a>
	        <ul id="products-menu" class="dropdown-menu clearfix" role="menu">
	          <li><a href="${pageContext.request.contextPath}/item/list?tag=1">가디건/조끼</a></li>
	          <li><a href="${pageContext.request.contextPath}/item/list?tag=2">야상/점퍼</a></li>
	          <li><a href="${pageContext.request.contextPath}/item/list?tag=3">자켓/코드</a></li>
	          <li><a href="${pageContext.request.contextPath}/item/list?tag=4">패딩</a></li>
	          <li><a href="${pageContext.request.contextPath}/item/list?tag=5">플리스</a></li>
	        </ul>
	      </li>
	      <li class="dropdown"><a data-toggle="dropdown" href="${pageContext.request.contextPath}/item/list?category=2">상의</a>
	        <ul id="products-menu" class="dropdown-menu clearfix" role="menu">
	          <li><a href="${pageContext.request.contextPath}/item/list?tag=6">긴팔티셔츠</a></li>
	          <li><a href="${pageContext.request.contextPath}/item/list?tag=7">맨투맨</a></li>
	          <li><a href="${pageContext.request.contextPath}/item/list?tag=8">후드</a></li>
	          <li><a href="${pageContext.request.contextPath}/item/list?tag=9">반팔/민소매티셔츠</a></li>
	          <li><a href="${pageContext.request.contextPath}/item/list?tag=10">니트</a></li>
	        </ul>
	      </li>
	      <li class="dropdown"><a href="${pageContext.request.contextPath}/item/list?category=3">셔츠/블라우스</a></li>
	      <li class="dropdown"><a href="${pageContext.request.contextPath}/item/list?category=4">트레이닝</a></li>
	      <li class="dropdown"><a href="${pageContext.request.contextPath}/item/list?category=5">베이직</a></li>
	      <li class="dropdown"><a href="${pageContext.request.contextPath}/item/list?category=6">원피스</a></li>
	      <li class="dropdown"><a href="${pageContext.request.contextPath}/item/list?category=7">스커트</a></li>
	      <li class="dropdown"><a data-toggle="dropdown" href="${pageContext.request.contextPath}/item/list?category=8">팬츠</a>
	        <ul id="products-menu" class="dropdown-menu clearfix" role="menu">
	          <li><a href="${pageContext.request.contextPath}/item/list?tag=11">청바지</a></li>
	          <li><a href="${pageContext.request.contextPath}/item/list?tag=12">롱팬츠</a></li>
	          <li><a href="${pageContext.request.contextPath}/item/list?tag=13">면바지</a></li>
	          <li><a href="${pageContext.request.contextPath}/item/list?tag=14">슬랙스</a></li>
	          <li><a href="${pageContext.request.contextPath}/item/list?tag=15">레깅스</a></li>
	          <li><a href="${pageContext.request.contextPath}/item/list?tag=16">숏팬츠</a></li>
	        </ul>
	      </li>
	      <li class="dropdown"><a data-toggle="dropdown" href="${pageContext.request.contextPath}/item/list?category=9">가방</a>
	        <ul id="products-menu" class="dropdown-menu clearfix" role="menu">
	          <li><a href="${pageContext.request.contextPath}/item/list?tag=25">백팩/스쿨백</a></li>
	          <li><a href="${pageContext.request.contextPath}/item/list?tag=26">크로스/토트백</a></li>
	        </ul>
	      </li>
	      <li class="dropdown"><a data-toggle="dropdown" href="${pageContext.request.contextPath}/item/list?category=10">신발</a>
	        <ul id="products-menu" class="dropdown-menu clearfix" role="menu">
	          <li><a href="${pageContext.request.contextPath}/item/list?tag=17">운동화/단화</a></li>
	          <li><a href="${pageContext.request.contextPath}/item/list?tag=18">구두/워커</a></li>
	          <li><a href="${pageContext.request.contextPath}/item/list?tag=19">샌들/슬리퍼/장화</a></li>
	        </ul>
	      </li>
	      <li class="dropdown"><a data-toggle="dropdown" href="${pageContext.request.contextPath}/item/list?category=11">악세사리</a>
	        <ul id="products-menu" class="dropdown-menu clearfix" role="menu">
	          <li><a href="${pageContext.request.contextPath}/item/list?tag=27">주얼리</a></li>
	          <li><a href="${pageContext.request.contextPath}/item/list?tag=28">모자/벨트</a></li>
	          <li><a href="${pageContext.request.contextPath}/item/list?tag=29">양말/스타킹</a></li>
	        </ul>
	      </li>
	    </ul>
	    </div>
    </div>
    <hr>
</body>
</html>


