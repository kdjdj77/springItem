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
		<table class="table table-hover" style="width: 800px; margin: 0 auto;">
		  <thead>
		    <tr>
		      <th scope="col"></th>
		      <th scope="col">주문상품정보</th>
		      <th scope="col" style="text-align: center;">수량</th>
		      <th scope="col" style="text-align: center;">가격</th>
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
		      	<div>${cart.color.name} / ${cart.size.name}</div><br>
		      	<div>
		      		<form action="optionchange" method="post">
		      			<input type="hidden" name="id" id="id" value="${cart.id}">
			      		<input class="btn btn-outline-dark" id="optionChange" type="button" name="changeOption" value="옵션변경" onclick="change(${cart.id})">
			      		<div id="appends${cart.id}" class="d-none" style="margin-top : 15px;">
			      			<select name="color" style="width: 70px; height: 30px;">
				      			<option value="">-선택-</option>
				      			<c:forEach var="color" items="${cart.item.colors}">
				      				<option value="${color.id}">${color.name}</option>
				      			</c:forEach>
			      			</select>
			      			<select name="size" style="width: 70px; height: 30px;">
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
		     	<div style="width: 100px; height: 40px; display : flex; margin-right: 30px; margin-top : 50px;">
		      	<button type="button" class="btn btn-outline-primary" onclick="minus(${cart.id});">-</button>
		      	<input style="width:3rem; height: 38px; text-align: center;" id="countinput${cart.id}" name="count" value="${cart.count}" readonly>
		      	<button type="button" class="btn btn-outline-success" onclick="plus(${cart.id});">+</button>
		      	</div>
		      	
		      </td>
		      <td>
		      	<div style="width: 90px; height: 50px; margin-top : 50px; margin-right:20px; text-align:right;">
			      <span style="text-decoration: line-through;">${cart.item.price}원</span><br>
				  <span class="prices" style="font-weight: bold; color: hotpink;">${cart.item.discountPrice }</span><span>원</span>
			  	</div>
			  </td>
			  <td>
				<form action="delCart" method="post">
					<input type="hidden" name="id" value="${cart.id}">
					<button style="margin-top : 50px;" class="btn btn-dark">X</button>
				</form>
			  </td>
		    </tr>
		  </c:forEach>
		  </tbody>
		</table>
		<div>
			<div style="width: 300px; height: 200px; margin-top : 50px; background-color : #e0e0e0; padding: 10px;">
			<p style="font-weight: bold; font-size: 1.2rem;">결제금액</p>
			<span id="orderprice" name="orderprice" style="font-weight: bold; color : hotpink; font-size: 1.2rem;"></span><span>원</span><br><br>

			<table style="width: 300px; height: 100px;">
				<tr style="width: 200px; height: 50px;">
					<td>총 상품금액</td>
					<td><span id="totalprice"></span><span>원</span></td>
				</tr>
				<tr style="width: 200px; height: 50px;">
					<td>배송비</td>
					<td><span id="delivery"></span><span>원</span></td>
				</tr>
			</table>
			</div>
			<input type="hidden" name="address" id="address" value="${userdetails.user.address2}">
			
			<form action="buy" method="post">
				<button class="btn btn-outline-dark" style="width: 300px; height: 40px; margin-top: 30px; line-height:30px;" onclick="return payment();">주문하기</button>
			</form>
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

function payment() {
	if ($("input[name='id']").val() == null) {
		alert("구매할 상품이 없습니다");
		return false;
	}
	
	if ($("input[name='address']").val() == "-") {
		alert("주소를 등록 후 구매해주세요");
		return false;
	}
	
	return true;
}

</script>

</html>