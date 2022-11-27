<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    

<c:choose>
	<c:when test="${result == 0 }">
		<script>
			alert("현재 비밀번호를 다시 입력해주세요");
			history.back();
		</script>
	</c:when>
	<c:otherwise>
		<script>
			alert("비밀번호 변경 성공");
			location.href = "/user/userinfo";
		</script>
	</c:otherwise>
</c:choose>
    


