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
	.d-none {
		display:none;
	}
	.d-block {
		display:block;
	}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<jsp:include page="/WEB-INF/views/common/header2.jsp" />	
	<div style="width:1300px;"class="container mb-5 d-flex flex-wrap">
		<table class="table table-hover" style="width: 800px; margin: 0 auto; border : 1px solid red;">
		  <thead>
		    <tr>
		      <th scope="col"></th>
		      <th scope="col">주문상품정보</th>
		      <th scope="col">수량</th>
		      <th scope="col">가격</th>
		    </tr>
		  </thead>
		  <tbody>
		  <c:forEach var="cart" items="${cartList }">
		    <tr>
		      <td>
		      	<img style="width:160px; height:160px;" src="${pageContext.request.contextPath }/upload/${cart.item.itemfiles[0].file}">
			  </td>
		      <td>
		      	<div>${cart.item.name}</div>
		      	<div>${cart.color.name}/${cart.size.name}</div>
		      	<div>
		      		<form action="optionchange" method="post">
		      			<input type="hidden" name="id" value="${cart.id}">
			      		<input id="optionChange" type="button" name="changeOption" value="옵션변경" onclick="change(${cart.id})">
			      		<div id="appends${cart.id}" class="d-none">
			      			<select name="color">
				      			<option value="">-선택-</option>
				      			<c:forEach var="color" items="${cart.item.colors}">
				      				<option value="${color.id}">${color.name}</option>
				      			</c:forEach>
			      			</select>
			      			<select name="size">
				      			<option value="">-선택-</option>
				      			<c:forEach var="size" items="${cart.item.sizes}">
				      				<option value="${size.id}">${size.name}</option>
				      			</c:forEach>
			      			</select>
			      			<button class="btn btn-outline-dark btn-sm">변경</button>
			      			<button type="button" class="btn btn-outline-dark btn-sm" onclick="change(${cart.id})">취소</button>
			      		</div>
		      		</form>
		      	</div>
		      </td>
		      <td>
		      	<button type="button" onclick="minus(${cart.id});">-</button>
		      	<input style="width:2rem;" id="countinput${cart.id}" name="count" value="${cart.count}" readonly>
		      	<button type="button" onclick="plus(${cart.id});">+</button>
		      </td>
		      <td><div>
			      <span style="text-decoration: line-through;">${cart.item.price}원</span><br>
				  <span class="prices">${Math.round(cart.item.price*(100-cart.item.discount)/100)}</span><span>원</span>
			  </div></td>
			  <td>
				<form action="delCart" method="post">
					<input type="hidden" name="id" value="${cart.id}">
					<button>X</button>
				</form>
			  </td>
		    </tr>
		  </c:forEach>
		  </tbody>
		</table>
		<div>
			결제금액<span id="orderprice"></span><span>원</span><br>
			총 상품금액<span id="totalprice"></span><span>원</span><br>
			배송비<span id="delivery"></span><span>원</span><br>
			<button class="btn btn-outline-dark">주문하기</button>
		</div>
	</div>
</body>
<script>
setOrder();
function setOrder() {
	const data = {};
	$.ajax({
		url: "getprice",
		type: "GET",
		data: data,
		cache: false,
		success: function(data, status, xhr) {
			if (status == "success") {
				if (data.status != "OK") {
					alert(data.status);
					return;
				}
				let price = data.data;
				let delivery = 3000;
				let orderprice = price + delivery;
				
				document.getElementById("orderprice").innerHTML = orderprice;
				document.getElementById("totalprice").innerHTML = price;
				document.getElementById("delivery").innerHTML = delivery;
			}
		},
	});
}
function minus(id) {
	let cnt = Number($("#countinput" + id).val());
	if (cnt > 1) {
		cnt--;
		setCount(cnt, id);
	} else return;
}
function plus(id) {
	let cnt = Number($("#countinput" + id).val());
	cnt++;
	setCount(cnt, id);
}
function change(id) {
	if($("#appends" + id).hasClass("d-none") === true) {
		$("#appends" + id).addClass("d-block");
		$("#appends" + id).removeClass("d-none");
	}
	else {
		$("#appends" + id).addClass("d-none");
		$("#appends" + id).removeClass("d-block");
	}
};
function setCount(cnt, id) {
	const data = {
		"id":id,
		"count":cnt,
	};
	$.ajax({
		url: "setcount",
		type: "GET",
		data: data,
		cache: false,
		success: function(data, status, xhr) {
			if (status == "success") {
				if (data.status != "OK") {
					alert(data.status);
					return;
				}
				$("#countinput" + id).val(data.data);
				setOrder();
			}
		},
	});
}
</script>
</html>