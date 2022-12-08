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
	<script src="https://kit.fontawesome.com/51772bd9bd.js"></script>
    <title>배송 목록</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <div class="container my-3" style="width:90vw;">
        <h2>
        	배송 목록
        </h2><hr>
        <table style="width:100%;">
        	<tr>
        		<td class="border-end border-dark p-1 ps-3" style="width:1rem">#</td>
        		<td class="border-end border-dark p-1 ps-3" style="width:auto">상품명</td>
        		<td class="border-end border-dark p-1 ps-3" style="width:6rem">개수</td>
        		<td class="border-end border-dark p-1 ps-3" style="width:20rem">배송지</td>
        		<td class="border-end border-dark p-1 ps-3" style="width:10rem">주문일자</td>
        		<td style="width:10rem" class="ps-3">총 개수 : ${cnt}개</td>
        	</tr>
        	<c:forEach var="buy" items="${buyList}">
	        	<tr class="border-top border-dark">
	        		<td class="border-end border-dark p-3">${buy.id}</td>
	        		<td class="border-end border-dark p-3" style="white-space: nowrap; overflow:hidden; text-overflow: ellipsis;">${buy.item.name}</td>
	        		<td class="border-end border-dark p-3">${buy.count}</td>
	        		<td class="border-end border-dark p-3">${buy.user.address1} ${buy.user.address2} ${buy.user.address3}</td>
	        		<td class="border-end border-dark p-3">${buy.regDateTime}</td>
	        		<td class="px-3">
	        			<a href="delivery?id=${buy.id}" class="btn btn-success">배송완료</a>
	        		</td>
	        	</tr>
        	</c:forEach>
        </table>
        
    </div>
    <!-- pagination -->
    <div class="container mt-1 mb-5">
        <ul class="pagination justify-content-center">
            <%-- << 표시 여부 --%>   
            <c:if test="${page > 1 }">
            <li class="page-item"><a class="page-link" href="${url }" title="처음"><i class='fas fa-angle-double-left'></i></a></li>
            </c:if>     
        
            <%-- < 표시 여부 --%>
            <c:if test="${startPage > 1 }">
            <li class="page-item"><a class="page-link" href="${url }?page=${startPage - 1 }"><i class='fas fa-angle-left'></i></a></li>
            </c:if>
            
            <%-- 페이징 안의 '숫자' 표시 --%> 
            <c:if test="${totalPage > 1 }">
                <c:forEach var="k" begin="${startPage }" end="${endPage }">
                <c:choose>
                    <c:when test="${k != page }">
                        <li class="page-item"><a class="page-link" href="${url }?page=${k }&search=${search}">${k }</a></li>        			
                    </c:when>
                    <c:otherwise>
                        <li class="page-item active"><a class="page-link" href="javascript:void(0);">${k }</a></li>
                    </c:otherwise>
                </c:choose>
                </c:forEach>    
            </c:if>
                        
            <%-- > 표시 여부 --%>
            <c:if test="${totalPage > endPage }">
            <li class="page-item"><a class="page-link" href="${url }?page=${endPage + 1 }&search=${search}"><i class='fas fa-angle-right'></i></a></li>
            </c:if>
            
            <%-- >> 표시 여부 --%>
            <c:if test="${page < totalPage }">
            <li class="page-item"><a class="page-link" href="${url }?page=${totalPage }&search=${search}"><i class='fas fa-angle-double-right'></i></a></li>
            </c:if>
            
        </ul>
    </div>
    <!-- pagination --> 
</body>
</html>