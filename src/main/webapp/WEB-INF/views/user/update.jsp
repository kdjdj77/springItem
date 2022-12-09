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
    <title>회원정보 수정</title>
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

<div class="container" style="width:50%;">
	<form id="frm" method="POST">
	<input type="hidden" name="id" value="${userdetails.user.id}">
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
                        <h4 class="m-t-10 m-b-5">
                        	수정 - ${userdetails.user.name}
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
                                 <td class="field">이름</td>
                                 <td><input type="text" name="name" value="${userdetails.user.name}"></td>
                              </tr>
                              <tr class="divider">
                                 <td colspan="2"></td>
                              </tr>
                              <tr>
                                 <td class="field">연락처</td>
                                 <td>
                                 	<i class="fa fa-mobile fa-lg m-r-5"></i> +82) <input type="number" id="phonenum" name="phonenum" value="${userdetails.user.phonenum}">
                                 	<span id="phoneChk" class="btn btn-outline-dark doubleChk btn-sm mb-1">인증번호 전송</span>
                                 </td>
                              </tr>
                              <tr>
                                 <td class="field">휴대폰 인증</td>
                                 <td>
				                	 <input id="phone2" type="text" name="phone2" title="인증번호 입력" required/>
									 <span id="phoneChk2" class="btn btn-outline-dark btn-sm doubleChk mb-1">본인인증</span><br>
									 <span class="point successPhoneChk"><b>휴대폰 번호 입력후 인증번호 전송을 눌러주세요</b></span>
									 <input type="hidden" id="phoneDoubleChk"/>
								 </td>
                              </tr>
                              <tr>
                                 <td class="field">이메일</td>
                                 <td><input type="text" name="email" value="${userdetails.user.email}"></td>
                              </tr>
                              <tr class="highlight">
                                 <td class="field">우편번호</td>
                                 <td>
				                	<input type="text" class="" id="sample4_postcode" placeholder="우편번호" value="${userdetails.user.address1}" disabled required>
									<input type="button" class="btn btn-outline-dark btn-sm mb-1" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
                    			</td>          
                              </tr>
                              <tr class="highlight">
                                 <td class="field">주소</td>
                                 <td>
                                 	<input type="text" id="sample4_roadAddress" placeholder="도로명주소" size="60" value="${userdetails.user.address2}" disabled required><br>
									<input type="hidden" id="sample4_jibunAddress" placeholder="지번주소"  size="60">
                                 	<span id="guide" style="color:#999;display:none"></span>
                                 </td>
                              </tr>
                              <tr class="highlight">
                                 <td class="field">상세주소</td>
                                 <td>
                                 	<input type="text" id="sample4_detailAddress" name="address3" placeholder="상세주소" value="${userdetails.user.address3}" size="60" required><br>
                                 </td>
                              </tr>
                              <tr class="divider">
                                 <td colspan="2"></td>
                              </tr>
                              <tr>
                                 <td class="field">포인트</td>
                                 <td>${userdetails.user.point} p</td>
                              </tr>
                              <tr>
                                 <td class="field">가입일</td>
                                 <td>${userdetails.user.regDateTime}</td>
                              </tr>
                              <tr class="divider">
                                 <td colspan="2"></td>
                              </tr>
                              <tr class="highlight">
                                 <td class="field">&nbsp;</td>
                                 <td class="p-t-10 p-b-10">
                                    <button type="button" onclick="frmsubmit()" class="btn btn-primary width-150">Update</button>
                                    <a href="${pageContext.request.contextPath}/user/userinfo" class="btn btn-outline-dark width-150 m-l-5">Cancel</a>
                                 </td>
                              </tr>
                           </tbody>
                        </table>
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
   </form>
</div>
</body>
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
<script>
	let frm = document.getElementById("frm");
	frm.action+="updateOk?address1=" + address1 + "&address2=" + address2;
	function frmsubmit() {
		if (checkNum == true) frm.submit();
		else alert("인증번호를 확인해 주세요");
	}
</script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
let address1 = "";
let address2 = "";
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("sample4_roadAddress").value = roadAddr + extraRoadAddr;
                address1 = data.zonecode;
                address2 = roadAddr + extraRoadAddr;
                var guideTextBox = document.getElementById("guide");
            }
        }).open();
    }
</script>
</html>