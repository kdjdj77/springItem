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
<html lang="ko">
 
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <title>상품 수정</title>
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

    <div class="container mt-3" style="width:60%;">
        <h2>상품 수정</h2><hr>
        
        <form name="frm" onsubmit="return submitForm();" action="updateOk" method="post" enctype="Multipart/form-data">
            <input type="hidden" name="id" value="${item.id}">
            <div class="mb-3">
                <label for="name">상품명</label>
                <input type="text" class="form-control" id="name" value="${item.name}" placeholder="상품명을 입력하세요" name="name" required>
            </div>
            
            <div class="mb-3 mt-3" style="width:100%;">카테고리/태그<br>
            	<select style="width:15rem; height:2rem;" name="category" class="mb-3" required>
            		<option value="">선택</option>
            		<c:forEach var="cat" items="${categoryList}">
            			<c:choose>
            				<c:when test="${cat.id eq item.category.id}">
            					<option value="${cat.id}" selected>${cat.name}</option>
            				</c:when>
            				<c:otherwise>
            					<option value="${cat.id}">${cat.name}</option>
            				</c:otherwise>
            			</c:choose>
            		</c:forEach>
            	</select>
            	<select id="tag" style="width:15rem; height:2rem;" name="tag" class="mb-3" required>
            		<c:forEach var="tag" items="${item.category.tags}">
            			<c:choose>
            				<c:when test="${tag.id eq item.tag.id}">
            					<option value="${tag.id}" selected>${tag.name}</option>
            				</c:when>
            				<c:otherwise>
            					<option value="${tag.id}">${tag.name}</option>
            				</c:otherwise>
            			</c:choose>
            		</c:forEach>
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
            		<td id="colors">
            			<c:if test="${not empty item.colors && fn:length(item.colors) > 0 }">	
							<div id="delColors">
							
							</div>
							<div class="mb-3 mt-3" style="padding-right:10%;">
								<c:forEach var="fileDto" items="${item.colors}">
									<c:if test="${fileDto.isvalid}">
										<div class="input-group mb-2">
											<input class="itemcolor form-control col-xs-3" type="text" readonly value="${fileDto.color}">
											<button type="button"  class="btn btn-outline-danger" onclick="deleteColors(${fileDto.id}); $(this).parent().remove(); ">삭제</button> 
										</div>
									</c:if>
								</c:forEach>
							</div>		
						</c:if>			
						<script>function deleteColors(fileId){$("#delColors").append(`<input type='hidden' name='delcolors' value='\${fileId}'>`);}</script>
            		</td>
            		<td id="sizes">
            			<c:if test="${not empty item.sizes && fn:length(item.sizes) > 0 }">		
							<div id="delSizes">
							
							</div>
							<div class="mb-3 mt-3" style="padding-right:10%;">
								<c:forEach var="fileDto" items="${item.sizes}">
									<c:if test="${fileDto.isvalid}">
										<div class="input-group mb-2">
											<input class="itemsize form-control col-xs-3" type="text" readonly value="${fileDto.name}">
											<button type="button"  class="btn btn-outline-danger" onclick="deleteSizes(${fileDto.id}); $(this).parent().remove(); ">삭제</button> 
										</div>
									</c:if>
								</c:forEach>
							</div>			
						</c:if>			
						<script>function deleteSizes(fileId){$("#delSizes").append(`<input type='hidden' name='delsizes' value='\${fileId}'>`);}</script>
            		</td>
            	</tr>
            </table>
            
            <div class="mb-3 mt-3" style="width:100%">
                <label for="discount">할인율</label>
                <input type="number" value="${item.discount}" class="form-control" id="discount" placeholder="할인율(%)을 입력하세요" name="discount" step="0.1" required>
            </div>
            
            <div class="mb-3 mt-3">
                <label for="price">가격</label>
                <input type="number" value="${item.price}" class="form-control" id="price" placeholder="가격을 입력하세요" name="price" required></input>
            </div> 
            
            <div class="mb-3 mt-3">
                <label for="stock">재고</label>
                <input type="number" value="${item.stock}" class="form-control" id="stock" placeholder="재고를 입력하세요" name="stock" required></input>
            </div> 
            
            <div class="mb-3 mt-3">
                <label for="content">상품설명</label>
                <textarea class="form-control" rows="5" id="content" placeholder="상품설명을 입력하세요" name="content">${item.content}</textarea>
            </div>

			<!-- 이미지 -->
			<div class="container mt-3 mb-3 border rounded">
				<!-- 기존 첨부파일  목록 (삭제 가능) -->
				<c:if test="${not empty item.itemfiles && fn:length(item.itemfiles) > 0 }">
					<div class="container mt-3 mb-3 border rounded">					
						<div id="delItemFiles">
						
						</div>
						<div class="mb-3 mt-3">
							<label>썸네일 이미지</label>
							<c:forEach var="fileDto" items="${item.itemfiles}">
								<div class="input-group mb-2">
									<input class="form-control col-xs-3" type="text" readonly value="${fileDto.source }">
									<button type="button"  class="btn btn-outline-danger" onclick="deleteIFiles(${fileDto.id }); $(this).parent().remove(); ">삭제</button> 
								</div>
							</c:forEach>
						</div>
					</div>				
				</c:if>			
				<script>
				function deleteIFiles(fileId){
					$("#delItemFiles").append(`<input type='hidden' name='delifile' value='\${fileId}'>`);
				}
				</script>
				<div class="mb-3 mt-3">
					<label>썸네일 이미지</label>
					<div id="files">
						
					</div>
					<button type="button" id="btnAdd" class="btn btn-secondary">추가</button>
				</div>
			</div>
			
			<div class="container mt-3 mb-3 border rounded">
				<!-- 기존 첨부파일  목록 (삭제 가능) -->
				<c:if test="${not empty item.contentfiles && fn:length(item.contentfiles) > 0 }">
					<div class="container mt-3 mb-3 border rounded">					
						<div id="delContentFiles">
						
						</div>
						<div class="mb-3 mt-3">
							<label>상품설명 이미지</label>
							<c:forEach var="fileDto" items="${item.contentfiles}">
								<div class="input-group mb-2">
									<input class="form-control col-xs-3" type="text" readonly value="${fileDto.source }">
									<button type="button"  class="btn btn-outline-danger" onclick="deleteCFiles(${fileDto.id }); $(this).parent().remove(); ">삭제</button> 
								</div>
							</c:forEach>
						</div>
					</div>				
				</c:if>			
				<script>
				function deleteCFiles(fileId){
					$("#delContentFiles").append(`<input type='hidden' name='delcfile' value='\${fileId}'>`);
				}
				</script>
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
<script src="${pageContext.request.contextPath}/js/updateItem.js"></script>
</html>