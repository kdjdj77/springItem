<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>

    <title>로그인</title>
</head>

<body>
	<form id="frm" action="${pageContext.request.contextPath }/user/register" method="POST">
        <input type="hidden" name="username" value="${username.toUpperCase()}">
        <input type="hidden" name="name" value="${name}">
        <input type="hidden" name="password" value="${username.toUpperCase()}">
        <input type="hidden" name="re_password" value="${username.toUpperCase()}">
        <input type="hidden" name="phonenum" value="${phonenum}">
        <input type="hidden" name="email" value="${email}">
        <input type="hidden" name="address" value="${address}">
        <input type="hidden" name="provider" value="api">
	</form>
</body>
<script>
	const frm = document.getElementById("frm");
	frm.submit();
</script>
</html>