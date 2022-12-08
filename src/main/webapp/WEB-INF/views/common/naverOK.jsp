<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name ="google-signin-client_id" content="815201325834-i9cdubuv18imuu4srr19f6cbj5m82n0f.apps.googleusercontent.com">
<title></title>
</head>
<body>
	<form id="frm" action="${pageContext.request.contextPath}/user/apiLogin" method="post"></form>
</body>
<script src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js" charset="utf-8"></script>
<script>
	let naverLogin = new naver.LoginWithNaverId(
		{
			clientId: "dzvV5hLnOrqi5DnS2Izf", //내 애플리케이션 정보에 cliendId를 입력해줍니다.
			callbackUrl: "http://localhost:8083/user/naverOK", // 내 애플리케이션 API설정의 Callback URL 을 입력해줍니다.
			isPopup: false,
			callbackHandle: true
		}
	);

	naverLogin.init();
	window.addEventListener('load', function () {
		naverLogin.getLoginStatus(function (status) {
			if (status) {
				const naverForm = document.getElementById("frm");
				const userN = naverLogin.user.name;
				const userId = "naver_" + naverLogin.user.id;
				const email = naverLogin.user.email;
				const phonenum = naverLogin.user.mobile;
				
				const input1 = document.createElement('input');
				input1.type = "hidden"; input1.name = "id"; 	input1.value = userId;
				const input2 = document.createElement('input');
				input2.type = "hidden"; input2.name = "name";	input2.value = userN;
				const input3 = document.createElement('input');
				input3.type = "hidden"; input3.name = "phonenum"; input3.value = phonenum;
				const input4 = document.createElement('input');
				input4.type = "hidden"; input4.name = "email";	input4.value = email;
				
				naverForm.append(input1);
				naverForm.append(input2);
				naverForm.append(input3);
				naverForm.append(input4);
				naverForm.submit();
			}
		});
	});
</script>
</html>