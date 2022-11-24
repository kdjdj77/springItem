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
</head>
 
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
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
      <div class="swiper-button-next"></div>
      <div class="swiper-button-prev"></div>
      <div class="swiper-scrollbar"></div>
      <div class="swiper-pagination"></div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.js"></script>
	<script src="/js/mainItemList.js"></script>

	<div style="width:1500px; height:auto; border:1px solid black; display:flex; margin: 0 auto;">
		<c:forEach var="i" items="${itemList}">
			<div class="container" style="width: 280px; height: 353px;">
				<form action="reserv" method="POST">
					<div class="row">
						<div class="card" style="border: 1px solid blue;">
							<img
								src="${pageContext.request.contextPath }/upload/${i.itemfiles[0].file}"
								class="card-img-top"
								style="width: 253px; height: 253px; object-fit: fill; border: 1px solid red;">
							<div class="card-body">
								<h5 class="card-title">${i.name }</h5>
							</div>
							<ul class="list-group list-group-flush">
								<li class="list-group-item">${i.discount }%</li>
								<li class="list-group-item">${i.price*(100-i.discount)/100}원</li>
							</ul>
							<input type="hidden" name="carId" value="${i.id }">
						</div>
					</div>
				</form>
			</div>
		</c:forEach>
	</div>

</body>
</html>
 

