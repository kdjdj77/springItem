Kakao.init('4e8ac6138dbdeaa7b4b27d42b6dc81e6'); //발급받은 키 중 javascript키를 사용해준다.
kakaoLogout();
//카카오로그인
function kakaoLogin() {
    Kakao.Auth.login({
      success: function (response) {
        Kakao.API.request({
          url: '/v2/user/me',
          success: function (response) {
        	//const kakaoForm = document.getElementById("kakaoForm");
        	let kakaoForm = document.createElement("form");
			kakaoForm.setAttribute("charset", "UTF-8");
			kakaoForm.setAttribute("method", "post");
			kakaoForm.setAttribute("action", "apiLogin");
			document.body.appendChild(kakaoForm);
        	
        	console.log(response);
        	
        	const userN = response.properties.nickname
        	const userId = "kakao_" + response.id;
        	const email = response.kakao_account.email;
        	
        	console.log(userN);
        	console.log(userId);
        	console.log(email);
        	
			const input1 = document.createElement('input');
       		input1.type = "hidden"; input1.name = "id"; input1.value = userId;
       		kakaoForm.append(input1);
       		const input2 = document.createElement('input');
       		input2.type = "hidden"; input2.name = "name"; input2.value = userN;
       		kakaoForm.append(input2);
       		const input3 = document.createElement('input');
       		input3.type = "hidden"; input3.name = "email"; input3.value = email;
       		kakaoForm.append(input3);
       		
       		kakaoForm.submit();
          },
          fail: function (error) {
            console.log(error)
          },
        })
      },
      fail: function (error) {
        console.log(error)
      },
    })
  }
//카카오로그아웃  
function kakaoLogout() {
    if (Kakao.Auth.getAccessToken()) {
      Kakao.API.request({
        url: '/v1/user/unlink',
        success: function (response) {
        	//console.log(response)
        },
        fail: function (error) {
          console.log(error)
        },
      })
      Kakao.Auth.setAccessToken(undefined)
   }
}  