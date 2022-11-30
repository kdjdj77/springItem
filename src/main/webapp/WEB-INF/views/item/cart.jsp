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
	
	<c:forEach var="cart" items="${cartList }">
		<p>${cart.regDateTime }</p>
		<p>${cart.count }</p>
		<p>${username }</p>
		<p>${name }</p>
		<p>${cart.item.name}</p>
		<p>${cart.color.name}</p>
		<p>${cart.size.name}</p>
	</c:forEach>
	
	<table class="table table-hover" style="width: 1000px; margin: 0 auto; border : 1px solid red;">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col"></th>
      <th scope="col">주문상품정보</th>
      <th scope="col">수량</th>
      <th scope="col">가격</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td></td>
      <td>
      	<div>${cartList[0].item.name}</div>
      	<div>${cartList[0].color.name } / ${cartList[0].size.name }</div>
      	<div><input type="submit" name="changeOption" value="옵션변경" onclick="change()"><div id="appends"></div></div>
      </td>
      <td><div>${cartList[0].count }</div></td>
      <td><div></div></td>
    </tr>
    <tr>
      <th scope="row">2</th>
      <td>@</td>
      <td>Jacob</td>
      <td>Thornton</td>
      <td>@fat</td>
    </tr>
  </tbody>
</table>
</body>
<script>
function change() {
		$("#appends").append(`
			<div>동작확인</div>
		`);
};
</script>
</html>