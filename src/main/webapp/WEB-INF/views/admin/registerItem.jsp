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

    <title>상품 등록</title>
    <style>
	    input[type="number"]::-webkit-outer-spin-button,
		input[type="number"]::-webkit-inner-spin-button {
		    -webkit-appearance: none;
		    margin: 0;
		}
		th, td {
		  vertical-align : top;
		}
    </style>
</head>
 
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>

    <div class="container mt-3" style="width:60vw;">
        <h2>상품 등록</h2><hr>
        
        <form name="frm" onsubmit="return submitForm();" action="registerOk" method="post" enctype="Multipart/form-data">
            <div class="mb-3">
                <label for="name">상품명</label>
                <input type="text" class="form-control" id="name" placeholder="상품명을 입력하세요" name="name" required>
            </div>
            
            <div class="mb-3 mt-3">카테고리/태그
            	<select style="width:15rem; height:2rem;" name="category" class="mb-3" required>
            		<option value="">선택</option>
            		<c:forEach var="cat" items="${categoryList}">
            			<option value="${cat.id}">${cat.name}</option>
            		</c:forEach>
            	</select>
           		<select id="tag" style="width:15rem; height:2rem;" name="tag" class="mb-3" required>
           		
           		</select>
            </div>
            
            <table style="width:100%;">
            	<tr>
            		<td style="width:40%;">
            			색상
            			<button class="btn btn-sm btn-outline-dark ms-3" type="button" id="colorAdd">추가</button>
            		</td>
            		<td style="width:40%;">
            			사이즈
            			<button class="btn btn-sm btn-outline-dark ms-3" type="button" id="sizeAdd">추가</button>
            		</td>
            	</tr>
            	<tr style="padding-right:10rem;">
            		<td id="colors"></td>
            		<td id="sizes"></td>
            	</tr>
            </table>
            
            <div class="mb-3 mt-3" style="width:100%">
                <label for="discount">할인율</label>
                <input type="number" class="form-control" id="discount" placeholder="할인율(%)을 입력하세요" name="discount" step="0.1" required>
            </div>
            
            <div class="mb-3 mt-3">
                <label for="price">가격</label>
                <input type="number" class="form-control" id="price" placeholder="가격을 입력하세요" name="price" required></input>
            </div> 
            
            <div class="mb-3 mt-3">
                <label for="stock">재고</label>
                <input type="number" class="form-control" id="stock" placeholder="재고를 입력하세요" name="stock" required></input>
            </div> 
            
            <div class="mb-3 mt-3">
                <label for="content">상품설명</label>
                <textarea class="form-control" rows="5" id="content" placeholder="상품설명을 입력하세요" name="content"></textarea>
            </div>

			<!-- 이미지 -->
			<div class="container mt-3 mb-3 border rounded">
				<div class="mb-3 mt-3">
					<label>썸네일 이미지</label>
					<div id="files">
					
					</div>
					<button type="button" id="btnAdd" class="btn btn-secondary">추가</button>
				</div>
			</div>
			
			<div class="container mt-3 mb-3 border rounded">
				<div class="mb-3 mt-3">
					<label>상품설명 이미지</label>
					<div id="files2">
					
					</div>
					<button type="button" id="btnAdd2" class="btn btn-secondary">추가</button>
				</div>
			</div>
			<!-- 이미지 -->

            <button class="btn btn-success">등록</button>
            <a class="btn btn-danger" href="${pageContext.request.contextPath }/home">취소</a>
        </form>
    </div>
    <div style="height:100px;">
    </div>
</body>
<script src="${pageContext.request.contextPath}/js/registerItem.js"></script>
</html>