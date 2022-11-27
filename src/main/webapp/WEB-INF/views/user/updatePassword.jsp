<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
    
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <title>비밀번호 변경</title>
</head>
 
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<div class="container mt-5" style="width:50%; display:flex; justify-content:center">
	<form action="updatePasswordOk" method="POST">
		<h4>비밀번호 변경</h4>
		<input type="hidden" name="id" value="${userdetails.user.id}">
	   	<div class="mt-3">현재 비밀번호 <input type="password" style="width:300px;" name="chkpassword" placeholder="현재 비밀번호를 입력하세요"></div>
	   	<div class="mt-3">비밀번호 입력 <input id="pw" type="password" style="width:300px;" name="password" placeholder="변경할 비밀번호를 입력하세요"></div>
	   	<div class="my-3">비밀번호 확인 <input id="rpw" type="password" style="width:300px;" name="re_password" placeholder="비밀번호를 한번더 입력해주세요"></div>
	   	<button onclick="return func();" class="btn btn-outline-dark">수정</button>
	   	<a href="userinfo" class="btn btn-outline-dark">취소</a>
	</form>
	<div class="form-group mt-3">
        <label for="phonenum">휴대폰번호</label><br>
      	<input type="number" id="phonenum" name="phonenum" class="form-control w-50 d-inline-flex p-2" pattern="^(010|011|016|017|018|019)[0-9]{3,4}[0-9]{4}$" placeholder="예) 01012341234" value="${phonenum }" required/>
      	<span id="phoneChk" class="btn btn-outline-dark doubleChk ">인증번호 전송</span><br><br>
      	<label for="">휴대폰 인증번호 입력</label><br>
      	<input id="phone2" type="text" name="phone2" title="인증번호 입력" disabled required/>
		<span id="phoneChk2" class="btn btn-outline-dark doubleChk">본인인증</span><br>
		<span class="point successPhoneChk"><b>휴대폰 번호 입력후 인증번호 전송을 눌러주세요</b></span>
		<input type="hidden" id="phoneDoubleChk"/>
    </div>
</div>
</body>
<script>
function func() {
	let pw = document.getElementById("pw").value;
	let rpw = document.getElementById("rpw").value;
	if (pw != rpw) {
		alert("비밀번호와 비밀번호확인은 같아야 합니다");
		return false;
	}
	return true;
}
</script>
</html>