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
<title>검색 - ${search}</title>
<style>
	.a-none {
		font-size:0.8rem;
		padding:2px 10px;
		color:black;
		text-decoration:none;
		border:1px solid dimgray;
		border-radius:1rem;
	}
	.a-none:hover {
		color:black;
		text-decoration:none;
	}
	.selected {
		background-color:hotpink;
		color:white;
		border:1px solid hotpink;
	}
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
	<p class="container fs-1 bold my-5" style="text-align:center;">
		<span style="color:hotpink">'${search}'</span>
		<span>에 대한 통합 검색결과입니다.</span>
	</p>
	<div class="mb-3 me-5 float-end">
		<a class="a-none" id="sort0" href="${pageContext.request.contextPath}/item/search?search=${search}&sort=0">
		신상품</a>
		<a class="a-none" id="sort1" href="${pageContext.request.contextPath}/item/search?search=${search}&sort=1">
		좋아요</a>
		<a class="a-none" id="sort2" href="${pageContext.request.contextPath}/item/search?search=${search}&sort=2">
		판매량</a>
		<a class="a-none" id="sort3" href="${pageContext.request.contextPath}/item/search?search=${search}&sort=3">
		낮은가격</a>
	</div>
	<script> 
		$("#sort" + ${sort}).addClass("selected"); 
	</script>
	<div class="sub_wrap">
		<c:forEach var="item" items="${itemList}">
			<div class="container" id="container_wrap">
				<div class="row">
					<div id="card_box">
						<div style="position:relative;">
							<a href="${pageContext.request.contextPath}/item/detail?id=${i.id}">
								<c:if test="${item.itemfiles[0].file == null}">
									<div class="card-img-top" style="color:black; text-decoration:none; text-align:center; border:1px solid black;background-color:ivory; width:300px; height:293px;">
										NO IMAGE
									</div>
								</c:if>
								<c:if test="${item.itemfiles[0].file != null}">
									<img src="${pageContext.request.contextPath }/upload/${item.itemfiles[0].file}" class="card-img-top">
								</c:if>
							</a>
							<c:choose>
								<c:when test="${likeList.contains(item)}">
									<div class="like like-ok" id="${item.id}" onclick="clickLike(this);">
										<i class="fa-solid fa-heart"></i>
									</div>
								</c:when>
								<c:otherwise>
									<div class="like like-no" id="${item.id}" onclick="clickLike(this);">
										<i class="fa-solid fa-heart"></i>
									</div>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="card-body">
							<p class="card-title"><a href="${pageContext.request.contextPath}/item/detail?id=${item.id }" style="text-decoration : none; color: black;">${item.name}</a></p>
						</div>
						<ul class="list-group list-group-flush" id="discolor">
							<li class="list-group-item"><span style="font-size:0.9rem; margin-right:1rem;">${item.discount}%</span> ${item.discountPrice}원</li>
							<li class="list-group-item"></li>
						</ul>
						<input type="hidden" name="itemId" value="${item.id}">
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	<!-- pagination -->
	<div class="container mt-1">
		<ul class="pagination justify-content-center">
			<c:if test="${page > 1 }">
				<li class="page-item"><a class="page-link" href="${url }?search=${search}&sort=${sort}" title="처음"><i class='fas fa-angle-double-left'></i></a></li>
			</c:if>
			<c:if test="${startPage > 1 }">
				<li class="page-item"><a class="page-link" href="${url }?page=${startPage - 1 }&search=${search}&sort=${sort}"><i class='fas fa-angle-left'></i></a></li>
			</c:if>
			<c:if test="${totalPage > 1 }">
				<c:forEach var="k" begin="${startPage }" end="${endPage }">
					<c:choose>
						<c:when test="${k != page }">
							<li class="page-item"><a class="page-link" href="${url }?page=${k }&search=${search}&sort=${sort}">${k }</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item active"><a class="page-link" href="javascript:void(0);">${k }</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:if>
			<c:if test="${totalPage > endPage }">
				<li class="page-item"><a class="page-link" href="${url }?page=${endPage + 1 }&search=${search}&sort=${sort}"><i class='fas fa-angle-right'></i></a></li>
			</c:if>
			<c:if test="${page < totalPage }">
				<li class="page-item"><a class="page-link" href="${url }?page=${totalPage }&search=${search}&sort=${sort}"><i class='fas fa-angle-double-right'></i></a></li>
			</c:if>
		</ul>
	</div>
	<!-- pagination -->
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