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
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<link href="/css/itemDetail.css" rel="stylesheet">
<title>Insert title here</title>
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
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<jsp:include page="/WEB-INF/views/common/header2.jsp" />

	<c:choose>
		<c:when test="${category == null}">
			<div
				style="width: 1000px; height: 80px; margin: 0 auto; margin-top: 100px; text-align: center;">
				<p style="font-weight: 900; font-size: 3rem; color: hotpink;">${tag.category.name}</p>
			</div>
			<br>
			<ul
				style="display: flex; width: 1000px; height: 80px; text-align: center; margin: 0 auto;">
				<c:forEach var="c" items="${tag.category.tags }">
					<li style="list-style: none; width: 20%;">
						<a href="${pageContext.request.contextPath}/item/list?tag=${c.id}" style="font-size: 1.2rem; font-weight: bold;" class="redColor">${c.name}</a>
					</li>
				</c:forEach>
			</ul>
			<hr>
			<div class="sub_wrap">
				<c:forEach var="tagItem" items="${tag.items}">
					<div class="container" id="container_wrap">
						<div class="row">
							<div id="card_box">
								<div style="position:relative;">
									<a href="${pageContext.request.contextPath}/item/detail?id=${tagItem.id}">
										<img src="${pageContext.request.contextPath }/upload/${tagItem.itemfiles[0].file}" class="card-img-top">
									</a>
									<c:choose>
										<c:when test="${likeList.contains(tagItem)}">
											<div class="like like-ok" id="${tagItem.id}" onclick="clickLike(this);">
												<i class="fa-solid fa-heart"></i>
											</div>
										</c:when>
										<c:otherwise>
											<div class="like like-no" id="${tagItem.id}" onclick="clickLike(this);">
												<i class="fa-solid fa-heart"></i>
											</div>
										</c:otherwise>
									</c:choose>
								</div>
								<div class="card-body">
									<p class="card-title redText">${tagItem.name }</p>	
								</div>																													
								<ul class="list-group list-group-flush" id="discolor">
									<li class="list-group-item"><span style="font-size:0.9rem; margin-right:1rem;">${tagItem.discount }%</span> ${tagItem.price*(100-tagItem.discount)/100} 원</li>
									<li class="list-group-item"></li>
								</ul>
								<input type="hidden" name="itemId" value="${tagItem.id }">
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			
						
		</c:when>
		<c:otherwise>

			<div style="width: 1000px; height: 80px; margin: 0 auto; margin-top: 100px; text-align: center;">
				<p style="font-weight: 900; font-size: 3rem; color: hotpink;">${category.name }</p>
			</div>
			<br>
			<ul style="display: flex; width: 1000px; height: 80px; text-align: center; margin: 0 auto;">
				<c:forEach var="c" items="${category.tags }">
					<li style="list-style: none; width: 20%;">
						<a href="${pageContext.request.contextPath}/item/list?tag=${c.id}" style="font-size: 1.2rem; font-weight: bold;">${c.name}</a>
					</li>
				</c:forEach>
			</ul>
			<hr><br>
			<div class="sub_wrap">
				<c:forEach var="cateItem" items="${category.items}">
					<div class="container" id="container_wrap">
						<div class="row">
							<div id="card_box">
								<div style="position:relative;">
									<a href="${pageContext.request.contextPath}/item/detail?id=${cateItem.id}">
										<img src="${pageContext.request.contextPath }/upload/${cateItem.itemfiles[0].file}" class="card-img-top">
									</a>
									<c:choose>
										<c:when test="${likeList.contains(cateItem)}">
											<div class="like like-ok" id="${cateItem.id}" onclick="clickLike(this);">
												<i class="fa-solid fa-heart"></i>
											</div>
										</c:when>
										<c:otherwise>
											<div class="like like-no" id="${cateItem.id}" onclick="clickLike(this);">
												<i class="fa-solid fa-heart"></i>
											</div>
										</c:otherwise>
									</c:choose>
								</div>
								<div class="card-body">
									<p class="card-title">${cateItem.name }</p>
								</div>
								<ul class="list-group list-group-flush" id="discolor">
									<li class="list-group-item"><span style="font-size:0.9rem; margin-right:1rem;">${cateItem.discount }%</span> ${cateItem.price*(100-cateItem.discount)/100}원</li>
									<li class="list-group-item"></li>
								</ul>
								<input type="hidden" name="itemId" value="${cateItem.id }">
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:otherwise>
	</c:choose>
</body>
<sec:authorize access="isAuthenticated()">
<script>
	function clickLike(e) {
		const id = $(e).attr('id');
		const data = { "itemId":id, };
		$.ajax({
			url: "data/likecontrol",
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