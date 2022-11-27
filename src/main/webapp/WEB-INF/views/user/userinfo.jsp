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
    <title>회원정보</title>
</head>
 
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<div class="container" style="width:50%;">
   <div class="row">
      <div class="col-md-12">
         <div id="content" class="content content-full-width">
            <!-- begin profile -->
            <div class="profile">
               <div class="profile-header">
                  <!-- BEGIN profile-header-cover -->
                  <div class="profile-header-cover"></div>
                  <!-- END profile-header-cover -->
                  <!-- BEGIN profile-header-content -->
                  <div class="profile-header-content">
                     <!-- BEGIN profile-header-info -->
                     <div class="profile-header-info mb-5">
                        <h4 class="m-t-10 m-b-5">회원정보 - ${userdetails.user.name}
                        	<a href="update" class="btn btn-xs btn-warning">수정</a>
                        	<a href="delete?id=${userdetails.user.id}" onclick="return confirm('정말로 탈퇴하시겠습니까?')" class="btn btn-xs btn-danger">탈퇴</a>
                     	</h4>
                     </div>
                     <!-- END profile-header-info -->
                  </div>
                  <!-- END profile-header-content -->
               </div>
            </div>
            <!-- end profile -->
            <!-- begin profile-content -->
            <div class="profile-content">
               <!-- begin tab-content -->
               <div class="tab-content p-0">

                  <!-- begin #profile-about tab -->
                  <div class="tab-pane fade in active show" id="profile-about">
                     <!-- begin table -->
                     <div class="table-responsive">
                        <table class="table table-profile">
                           <thead></thead>
                           <tbody>
                              <tr class="highlight">
                                 <td class="field">Name</td>
                                 <td>${userdetails.user.name}</td>
                              </tr>
                              <tr class="divider">
                                 <td colspan="2"></td>
                              </tr>
                              <tr>
                                 <td class="field">Phone</td>
                                 <td><i class="fa fa-mobile fa-lg m-r-5"></i> +82) ${userdetails.user.phonenum}</td>
                              </tr>
                              <tr>
                                 <td class="field">Email</td>
                                 <td>${userdetails.user.email}</td>
                              </tr>
                              <tr class="highlight">
                                 <td class="field">Address</td>
                                 <td>${userdetails.user.address}</td>
                              </tr>
                              <tr class="divider">
                                 <td colspan="2"></td>
                              </tr>
                              <tr>
                                 <td class="field">Point</td>
                                 <td>${userdetails.user.point} p</td>
                              </tr>
                              <tr>
                                 <td class="field">regDate</td>
                                 <td>${userdetails.user.regDateTime}</td>
                              </tr>
                              <tr class="divider">
                                 <td colspan="2"></td>
                              </tr>
                           </tbody>
                        </table>
                        <a class="btn btn-outline-dark" href="updatePassword">비밀번호 변경</a>
                     </div>
                     <!-- end table -->
                  </div>
                  <!-- end #profile-about tab -->
               </div>
               <!-- end tab-content -->
            </div>
            <!-- end profile-content -->
         </div>
      </div>
   </div>
</div>
</body>
</html>