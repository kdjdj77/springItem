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
    <link href="/css/mainItemList.css" rel="stylesheet">

    <title>목록</title>
</head>
 
<body>
	<ul class="menu">
      <li>
        <a href="#">베스트</a>
      </li>
      <li>
        <a href="#">신상</a>
      </li>
      <li>
        <a href="#">아우터</a>
        <ul class="submenu">
          <li>
            <a href="#">가디건/조끼</a>
          </li>
          <li>
            <a href="#">야상/점퍼</a>
          </li>
          <li>
            <a href="#">자켓/코드</a>
          </li>
          <li>
            <a href="#">패딩</a>
          </li>
          <li>
            <a href="#">플리스</a>
          </li>
        </ul>
      </li>
      <li>
        <a href="#">상의</a>
        <ul class="submenu">
          <li>
            <a href="#">긴팔티셔츠</a>
          </li>
          <li>
            <a href="#">맨투맨</a>
          </li>
          <li>
            <a href="#">후드</a>
          </li>
          <li>
            <a href="#">반팔/민소매티셔츠</a>
          </li>
          <li>
            <a href="#">니트</a>
          </li>
        </ul>
      </li>
      <li>
        <a href="#">셔츠</a>
      </li>
      <li>
        <a href="#">트레이닝</a>
      </li>
      <li>
        <a href="#">베이직</a>
      </li>
      <li>
        <a href="#">원피스</a>
      </li>
      <li>
        <a href="#">스커트</a>
      </li>
      <li>
        <a href="#">팬츠</a>
        <ul class="submenu">
          <li>
            <a href="#">청바지</a>
          </li>
          <li>
            <a href="#">롱팬츠</a>
          </li>
          <li>
            <a href="#">면바지</a>
          </li>
          <li>
            <a href="#">슬랙스</a>
          </li>
          <li>
            <a href="#">레깅스</a>
          </li>
        </ul>
      </li>
      <li>
        <a href="#">가방</a>
        <ul class="submenu">
          <li>
            <a href="#">백팩/스쿨백</a>
          </li>
          <li>
            <a href="#">크로스/토트백</a>
          </li>
        </ul>
      </li>
      <li>
        <a href="#">신발</a>
        <ul class="submenu">
          <li>
            <a href="#">운동화/단화</a>
          </li>
          <li>
            <a href="#">구두/워커</a>
          </li>
          <li>
            <a href="#">샌들/슬리퍼/장화</a>
          </li>
        </ul>
      </li>
      <li>
        <a href="#">악세사리</a>
        <ul class="submenu">
          <li>
            <a href="#">주얼리</a>
          </li>
          <li>
            <a href="#">모자/벨트</a>
          </li>
          <li>
            <a href="#">양말/스타킹</a>
          </li>
        </ul>
      </li>
    </ul>
</body>
</html>
 

