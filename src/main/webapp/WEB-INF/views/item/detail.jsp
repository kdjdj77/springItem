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
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<jsp:include page="/WEB-INF/views/common/header2.jsp" />
	<div id="wrap">
		<div id="wrap_left">
			<div id="demo" class="carousel slide w-30" data-bs-ride="carousel">
				<div class="carousel-inner w-30">
					<c:forEach var="itemfiles" items="${item.itemfiles }">
						<div class="carousel-item active">
							<img id="big_imgSize" src="${pageContext.request.contextPath }/upload/${itemfiles.file}" alt=".." class="d-block">
						</div>
					</c:forEach>
				</div>
				<button class="carousel-control-prev" type="button" data-bs-target="#demo" data-bs-slide="prev">
					<span class="carousel-control-prev-icon"></span>
				</button>
				<button class="carousel-control-next" type="button" data-bs-target="#demo" data-bs-slide="next">
					<span class="carousel-control-next-icon"></span>
				</button>
			</div>

			<div>
				<c:forEach var="itemfiles" items="${item.itemfiles }">
					<div>
						<img id="small_imgSize" src="${pageContext.request.contextPath }/upload/${itemfiles.file}" alt="Chicago" class="d-block">
					</div>
				</c:forEach>
			</div>
		</div>

		<!-- 정보페이지 -->
		<div class="container" style="width: 800px; height: hidden; margin-right: 0;">
			<div class="row align-items-md-stretch">
				<div class="col-lg-12">
					<div class="h-100 p-5 rounded-3">
						<div class="box-2">
							<div class="box-inner-2">
								<div>
									<h2 class="fw-bold">${item.name }</h2>
								</div>
								<br>
								<form name="cart" action="cartOk" method="POST">
									<span style="text-decoration: line-through;">${item.price }원</span><br>
									<span style="font-size: 1.5rem; margin-right: 1rem; color: hotpink;">${item.discount }<span style="font-size: 1rem;">%</span></span> 
									<span style="font-size: 1.5rem; font-weight: bold; font-weight: bold;">${item.price*(100-item.discount)/100}<span style="font-size: 1rem; font-weight: bold;">원</span></span>
									<hr>
									<fieldset class="form-group">
										<div class="row">
											<p class="col-form-label col-sm-2 pt-0">색상</p>
											<div class="col-sm-10">
												<c:forEach var="color" items="${item.colors }">
													<div class="form-check">
														<input class="form-check-input" type="radio" name="gridRadios" id="gridRadios1" value="${color.id }"> 
														<label class="form-check-label" for="gridRadios1"> ${color.color } </label>
													</div>
												</c:forEach>
											</div>
										</div>
									</fieldset>
									<hr>
									<fieldset class="form-group">
										<div class="row">
											<p class="col-form-label col-sm-2 pt-0">사이즈</p>
											<div class="col-sm-10">
												<c:forEach var="size" items="${item.sizes }">
													<div class="form-check">
														<input class="form-check-input" type="radio" name="gridRadios2" id="gridRadios2" value="${size.id }"> 
														<label class="form-check-label" for="gridRadios2"> ${size.name } </label>
													</div>
												</c:forEach>
											</div>
										</div>
									</fieldset>
									<hr><br>
									<p style="border: 1px solid black;"></p>
									<div style="display: flex; justify-content: space-between;">
										<h4>총 결제금액</h4>
										<p>0원</p>
									</div>
									<div style="width: 100%; height: 80px; display: flex; justify-content: space-between; margin: 30px 0;">
										 <input type="hidden" name="id" value="${item.id }">
										<button type="submit" style="width: 320px; height: 80px; border-radius: 10px; line-height: 80px; text-align: center; color: white; background-color: rgb(55, 55, 55); outline : 0; border : 0; font-size: 1.5rem;">장바구니</button>
										<button type="submit" style="width: 320px; height: 80px; border-radius: 10px; line-height: 80px; text-align: center; color: white; background-color: rgb(255, 111, 177); outline : 0; border : 0; font-size: 1.5rem;">구매하기</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<br><br><br><br><br><hr>
	<div style="width: 1000px; height: hidden; margin: 0 auto;">
		<img src="${pageContext.request.contextPath }/upload/test1.jpg" alt="..">
		<img style="margin: 30px 0;" src="${pageContext.request.contextPath }/upload/test2.jpg" alt="..">
		<img style="margin: 30px 0;" src="${pageContext.request.contextPath }/upload/test3.jpg" alt="..">
		<img style="margin: 30px 0;" src="${pageContext.request.contextPath }/upload/test4.jpg" alt="..">
		<img style="margin: 30px 0;" src="${pageContext.request.contextPath }/upload/test5.jpg" alt="..">
		<img style="margin: 30px 0;" src="${pageContext.request.contextPath }/upload/test6.jpg" alt="..">
	</div>
	
	<br><br><br><br><br>	
</body>
</html>