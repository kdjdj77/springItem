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
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1"
    />
    <!-- Link Swiper's CSS -->
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.css"
    />

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
 
 	<script src="https://kit.fontawesome.com/51772bd9bd.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link href="/css/itemList.css" rel="stylesheet">

    <title>목록</title>
    <style>
    	.like{
			font-size:2.3rem;
			text-shadow: 2px 2px 6px gray;
			position:absolute; 
			bottom:3px; 
			right:8px; 
			z-index:5;
			transition:0.2s;
		}
		.like:hover{font-size:2.5rem; cursor:pointer;}
		.like-ok{color:hotpink;}
		.like-no{color:white;}
    </style>
</head>
 
<body oncontextmenu='return false' onselectstart='return false' ondragstart='return false'>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<jsp:include page="/WEB-INF/views/common/header2.jsp"/>

	<div class="swiper mySwiper">
      <div class="swiper-wrapper">
        <div class="swiper-slide">
          <img src="${pageContext.request.contextPath }/upload/1.jpg"
          />
        </div>
        <div class="swiper-slide">
          <img
            src="${pageContext.request.contextPath }/upload/2.jpg"
          />
        </div>
        <div class="swiper-slide">
          <img
            src="${pageContext.request.contextPath }/upload/3.jpg"
          />
        </div>
        <div class="swiper-slide">
          <img
            src="${pageContext.request.contextPath }/upload/1.jpg"
          />
        </div>
        <div class="swiper-slide">
          <img
            src="${pageContext.request.contextPath }/upload/2.jpg"
          />
        </div>
        <div class="swiper-slide">
          <img
            src="${pageContext.request.contextPath }/upload/3.jpg"
          />
        </div>
        <div class="swiper-slide">
          <img
            src="${pageContext.request.contextPath }/upload/1.jpg"
          />
        </div>
        <div class="swiper-slide">
          <img
            src="${pageContext.request.contextPath }/upload/2.jpg"
          />
        </div>
        <div class="swiper-slide">
          <img
            src="${pageContext.request.contextPath }/upload/3.jpg"
          />
        </div>
      </div>
      <div class="swiper-button-next me-5"></div>
      <div class="swiper-button-prev ms-5"></div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.js"></script>
	<script src="/js/mainItemList.js"></script>
	<br>
	<div class="sub_wrap">
		<c:forEach var="i" items="${itemList}">
			<div class="container" id="container_wrap">
				<div class="row">
					<div id="card_box">
						<div style="position:relative;">
							<a href="${pageContext.request.contextPath}/item/detail?id=${i.id}">
								<c:if test="${i.itemfiles[0].file == null}">
									<div class="card-img-top" style="color:black; text-decoration:none; text-align:center; border:1px solid black;background-color:ivory; width:253px; height:253px;">
										NO IMAGE
									</div>
								</c:if>
								<c:if test="${i.itemfiles[0].file != null}">
									<img src="${pageContext.request.contextPath }/upload/${i.itemfiles[0].file}" class="card-img-top">
								</c:if>
							</a>
							<c:choose>
								<c:when test="${likeList.contains(i)}">
									<div class="like like-ok" id="${i.id}" onclick="clickLike(this);">
										<i class="fa-solid fa-heart"></i>
									</div>
								</c:when>
								<c:otherwise>
									<div class="like like-no" id="${i.id}" onclick="clickLike(this);">
										<i class="fa-solid fa-heart"></i>
									</div>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="card-body">
							<p class="card-title"><a href="item/detail?id=${i.id }" style="text-decoration : none; color: black;">${i.name }</a></p>
						</div>
						<ul class="list-group list-group-flush" id="discolor">
							<li class="list-group-item"><span style="font-size:0.9rem; margin-right:1rem;">${i.discount }%</span>${i.discountPrice}원</li>
							<li class="list-group-item"></li>
						</ul>
						<input type="hidden" name="itemId" value="${i.id }">
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</body>
<sec:authorize access="isAuthenticated()">
<script>
	function clickLike(e) {
		const id = $(e).attr('id');
		const data = { "itemId":id, };
		$.ajax({
			url: "item/data/likecontrol",
			type: "GET",
			data: data,
			cache: false,
			success: function(data, status, xhr) {
				if (status == "success") {
					if (data.status != "OK") return;
					$(e).toggleClass("like-no");
					$(e).toggleClass("like-ok");
				}
			},
		});	
	}
</script>
</sec:authorize>
<sec:authorize access="isAnonymous()">
<script>
	function clickLike(e) {
		alert("로그인 후 이용가능합니다");
		return;
	}
</script>
</sec:authorize>
</html>
 

