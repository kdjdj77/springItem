<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name ="google-signin-client_id" content="976906053956-l05prnqd30qlqs0pfvl2vt7ra8lr0ia1.apps.googleusercontent.com">
<title></title>
<style>
	a {text-decoretion:none; color:black;}
	li {list-style:none;}
</style>
</head>
<body>
	<div id="apis">
		<form id="kakaoform" action="apiLogin" method="POST">
		</form>
		<div onclick="kakaoLogin();">
	      <a href="javascript:void(0)" style="background-color: #fef01b" class="border border-2 w-100 btn btn-lg my-1">
	          <img style="height:30px; margin-top:-5px;" src="https://pbs.twimg.com/profile_images/738200195578494976/CuZ9yUAT_400x400.jpg"/>
	          <span>카카오 로그인</span>
	      </a>
		</div>
		<div>
          	<a id="naverIdLogin_loginButton" href="javascript:void(0)" style="background-color: #2db400" class="border border-2 w-100 btn btn-lg my-1">
	            <img style="height:30px; margin-top:-5px;" src="https://play-lh.googleusercontent.com/Kbu0747Cx3rpzHcSbtM1zDriGFG74zVbtkPmVnOKpmLCS59l7IuKD5M3MKbaq_nEaZM"/>
	            <span style="color:white;">네이버 로그인</span>
	        </a>
        </div>
        <div id="GgCustomLogin">
			<a onclick="onSignIn();" href="javascript:void(0)" style="background-color: white" class="border border-2 w-100 btn btn-lg my-1">
				<img style="height:25px; margin-top:-5px;" src="http://img1.daumcdn.net/thumb/R1280x0.fjpg/?fname=http://t1.daumcdn.net/brunch/service/user/5rH/image/aFrEyVpANu07FvoBZQbIB4aF_uc"/>
				<span>구글 로그인</span>
			</a>
		</div>
	    <div class="g_id_signin" data-type="standard"></div>
	    
		<div id="message"></div>
	</div>
</body>
<!-- 카카오 스크립트 -->
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script src="${pageContext.request.contextPath}/js/loginKakao.js"></script>
<!-- 구글 스크립트 -->
<script src="https://accounts.google.com/gsi/client" async defer></script>
<script src="https://unpkg.com/jwt-decode/build/jwt-decode.js"></script>
<script src="${pageContext.request.contextPath}/js/loginGoogle.js"></script>
<!--<script src="https://apis.google.com/js/platform.js?onload=init" async defer></script>-->
<!-- 네이버 스크립트 -->
<script src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/loginNaver.js"></script>
</html>