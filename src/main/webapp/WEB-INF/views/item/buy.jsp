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
table {
  border-collapse: separate;
  border-spacing: 0 10px;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<jsp:include page="/WEB-INF/views/common/header2.jsp" />
	<div>
		<div style="width: 200px; margin: 0 auto; color: hotpink; font-size : 2.5rem; font-weight: bold;">구매내역</div>
	</div>
	<table style="width: 1000px; margin : 30px auto;">
	<c:forEach var="buy" items="${buyList }">
	<tr style="border: 2px solid pink;">
		<td><img src="${pageContext.request.contextPath }/upload/${buy.item.itemfiles[0].file }" class="img-thumbnail" style="width: 150px; height: 150px; margin-right : 30px;"></td>
		<td style="width: 300px;">
			<div>
				<p><b>${buy.item.name }</b></p>
				<p>개수 : ${buy.count }EA</p>
				<p>색상 : ${buy.color.name }</p>
				<p>사이즈 : ${buy.size.name }</p>
				<p>가격 : ${buy.item.discountPrice * buy.count }원</p>
			</div>
		</td>
		<td>
			<div class="card border-light" style="width: 450px; height: 150px;">
			  <div class="card-header">${buy.isOrder ? "상품배송중" : "배송완료" }</div>
			  <div class="card-body">
			    <p class="card-text">구매가 완료되었습니다. 이용해주셔서 감사합니다. 상품의 이용방법, 반품 등에 대한 문의는 판매자에게 문의해주세요.</p>
			  </div>
			</div>
		</td>
	</tr>
	<tr style="height: 30px;"></tr>
	</c:forEach>
	</table>
</body>
</html>
