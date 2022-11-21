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
    </style>
</head>
 
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>

    <div class="container mt-3">
        <h2>상품 등록</h2><hr>
        
        <form name="frm" action="registerOk" method="post" enctype="Multipart/form-data">
            <div class="mb-3">
                <label for="name">상품명</label>
                <input type="text" class="form-control" id="name" placeholder="상품명을 입력하세요" name="name" required>
            </div>
            
            <div class="mb-3 mt-3" style="width:100%;">카테고리 
            	<select style="width:15rem; height:2rem;" name="category" class="mb-3" required>
            		<option value="">선택</option>
            		<c:forEach var="cat" items="${categoryList}">
            			<option value="${cat.id}">${cat.name}</option>
            		</c:forEach>
            	</select>
           		<div id="tagradio" style="width:100%; display:flex; flex-wrap:wrap; justify-content:around;">
           		
	            </div>
            </div>
            
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
			<script>
			<%-- 주의! jsp 파일 안에서 Template Literal 사용하면 ${} 는 EL 구문으로 인식되어 서버단에서 먼저 처리된다
			     응답에 출력해야 하는 경우 \${ }  처럼 escaping 해야 한다 --%>
			let i = 0;
			$("#btnAdd").click(function(){
				$("#files").append(`
					<div class="input-group mb-2">
					<input class="form-control col-xs-3" type="file" name="ifile"/>
					<button type="button" class="btn btn-outline-danger" onclick="$(this).parent().remove()">삭제</button>
					</div>
				`);
				i++;
			});
			</script>
			
			<div class="container mt-3 mb-3 border rounded">
				<div class="mb-3 mt-3">
					<label>상품설명 이미지</label>
					<div id="files2">
					
					</div>
					<button type="button" id="btnAdd2" class="btn btn-secondary">추가</button>
				</div>
			</div>
			<script>
			<%-- 주의! jsp 파일 안에서 Template Literal 사용하면 ${} 는 EL 구문으로 인식되어 서버단에서 먼저 처리된다
			     응답에 출력해야 하는 경우 \${ }  처럼 escaping 해야 한다 --%>
			let j = 0;
			$("#btnAdd2").click(function(){
				$("#files2").append(`
					<div class="input-group mb-2">
					<input class="form-control col-xs-3" type="file" name="cfile"/>
					<button type="button" class="btn btn-outline-danger" onclick="$(this).parent().remove()">삭제</button>
					</div>
				`);
				j++;
			});
			</script>
			<!-- 이미지 -->

            <button type="submit" class="btn btn-outline-dark">작성완료</button>
            <a class="btn btn-outline-dark" href="${pageContext.request.contextPath }/home">취소</a>
        </form>
    </div>
</body>
<script src="${pageContext.request.contextPath}/js/getTagsAjax.js"></script>
</html>