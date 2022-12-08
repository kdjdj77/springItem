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
	   	<div class="mt-3">현재 비밀번호<br> <input type="password" class="form-control" name="chkpassword" placeholder="현재 비밀번호를 입력하세요"></div>
	   	<div class="mt-3">비밀번호 입력<br> <input id="pw" type="password" class="form-control" name="password" placeholder="변경할 비밀번호를 입력하세요"></div>
	   	<div class="my-3">비밀번호 확인<br> <input id="rpw" type="password" class="form-control" name="re_password" placeholder="비밀번호를 한번더 입력해주세요"></div>
	   	<label for="phonenum">휴대폰번호</label><br>
      	<input type="number" id="phonenum" name="phonenum" class="form-control w-50 d-inline-flex p-2" pattern="^(010|011|016|017|018|019)[0-9]{3,4}[0-9]{4}$" placeholder="예) 01012341234" value="${userdetails.user.phonenum }" disabled required/>
      	<span id="phoneChk" class="btn btn-outline-dark doubleChk ">인증번호 전송</span><br><br>
      	<label for="">휴대폰 인증번호 입력</label><br>
      	<input id="phone2" type="text" name="phone2" class="form-control w-50 d-inline-flex" title="인증번호 입력" required/>
		<span id="phoneChk2" class="btn btn-outline-dark doubleChk">본인인증</span><br>
		<span class="point successPhoneChk"><b>휴대폰 번호 입력후 인증번호 전송을 눌러주세요</b></span><br>
		<input type="hidden" id="phoneDoubleChk"/>
		<br> 
	   	<button onclick="return func();" class="btn btn-outline-dark">수정</button>
	   	<a href="userinfo" class="btn btn-outline-dark">취소</a>
	</form>
</div>
</body>
<script>
function func() {
	if (checkNum != true) alert("인증번호를 확인해 주세요") return false;
	let pw = document.getElementById("pw").value;
	let rpw = document.getElementById("rpw").value;
	if (pw != rpw) {
		alert("비밀번호와 비밀번호확인은 같아야 합니다");
		return false;
	}
	return true;
}
<script>
//휴대폰 번호 인증
let code2 = "";
let checkNum = false;
$("#phoneChk").click(function(){
	
	if(($("#phonenum").val() == "")){
		alert("휴대폰 번호가 올바르지 않습니다.")
		$(".successPhoneChk").text("유효한 번호를 입력해주세요.");
		$(".successPhoneChk").css("color","red");
	}else if(!($("#phonenum").val() == "") && $("#phonenum").val().length == 11){
		alert("인증번호 발송이 완료되었습니다.\n휴대폰에서 인증번호 확인을 해주십시오.");
		var phone = $("#phonenum").val();
		$.ajax({
	        type:"GET",
	        url:"phoneCheck?phone=" + phone,
	        cache : false,
	        success:function(data){
	        	if(data == "error"){
	        		alert("휴대폰 번호가 올바르지 않습니다.")
					$(".successPhoneChk").text("유효한 번호를 입력해주세요.");
					$(".successPhoneChk").css("color","red");
					$("#phonenum").attr("autofocus",true);
	        	}else{	        		
	        		$("#phone2").attr("disabled",false);
	        		$("#phoneChk2").css("display","inline-block");
	        		$(".successPhoneChk").text("인증번호를 입력한 뒤 본인인증을 눌러주십시오.");
	        		$(".successPhoneChk").css("color","green");
	        		$("#phonenum").attr("readonly",true);
	        		code2 = data;
	        	}
	        }
	    });
	}else{
		alert("휴대폰 번호가 올바르지 않습니다.")
		$(".successPhoneChk").text("유효한 번호를 입력해주세요");
		$(".successPhoneChk").css("color","red")
	}
	
});

//휴대폰 인증번호 대조
$("#phoneChk2").click(function(){
	console.log(phoneChk2);
	
	if(code2.length <= 0 ){
		$("#phone2").attr("disabled",false);
		$("#phoneChk2").css("display","inline-block");
		$(".successPhoneChk").text("인증번호를 입력한 뒤 본인인증을 눌러주십시오.");
		$(".successPhoneChk").css("color","green");
	}
	else if(
		$("#phone2").val() == code2){
		$(".successPhoneChk").text("인증번호가 일치합니다.");
		$(".successPhoneChk").css("color","green");
		$("#phoneDoubleChk").val("true");
		$("#phone2").attr("disabled",true);
		checkNum = true;
	}else{
		$(".successPhoneChk").text("인증번호가 일치하지 않습니다. 확인해주시기 바랍니다.");
		$(".successPhoneChk").css("color","red");
		$("#phoneDoubleChk").val("false");
		$(this).attr("autofocus",true);
		return;
	}
});

</script>
</html>