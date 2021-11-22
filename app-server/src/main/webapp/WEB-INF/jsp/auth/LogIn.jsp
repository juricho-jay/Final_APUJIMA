<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core//dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
  
<style>
@use postcss-preset-env;

/* ---------- FONTAWESOME ---------- */
/* ---------- https://fortawesome.github.com/Font-Awesome/ ---------- */
/* ---------- http://weloveiconfonts.com/ ---------- */

@import url(https://weloveiconfonts.com/api/?family=fontawesome);

/* ---------- ERIC MEYER'S RESET CSS ---------- */
/* ---------- https://meyerweb.com/eric/tools/css/reset/ ---------- */

@import url(https://meyerweb.com/eric/tools/css/reset/reset.css);

/* ---------- FONTAWESOME ---------- */

[class*="fontawesome-"]:before {
  font-family: 'FontAwesome', sans-serif;
}

/* ---------- GENERAL ---------- */

body {
  background: #ffffff;
  color: #5a5656;
  display: grid;
  font-family: 'Open Sans', sans-serif;
  line-height: 1.5;
  margin: 0;
  min-height: 100vh;
  place-items: center;
}

a { text-decoration: none; }

h1 { font-size: 1em;
    margin-left: 25%; }

h1, p {
  margin-bottom: 10px;
}


strong {
  font-weight: bold;
}

.uppercase { text-transform: uppercase; }

/* ---------- LOGIN ---------- */

#login {
  margin: 50px auto;
  width: 300px;
}

form fieldset input[type="text"],
input[type="password"] {
  appearance: none;
  background: #e5e5e5;
  border: none;
  border-radius: 3px;
  color: #5a5656;
  font-family: inherit;
  font-size: 14px;
  height: 50px;
  outline: none;
  padding: 0px 10px;
  width: 280px;
}

form fieldset input[type="submit"] {
  appearance: none;
  background-color: #008dde;
  border: none;
  border-radius: 3px;
  color: #f4f4f4;
  cursor: pointer;
  font-family: inherit;
  height: 50px;
  text-transform: uppercase;
  width: 300px;
}

form fieldset a {
  color: #5a5656;
  font-size: 10px;
}

form fieldset a:hover { text-decoration: underline; }

.btn-round {
  background: #5a5656;
  border-radius: 50%;
  color: #f4f4f4;
  display: block;
  font-size: 12px;
  height: 50px;
  line-height: 50px;
  margin: 30px 125px;
  text-align: center;
  text-transform: uppercase;
  width: 50px;
}

.facebook-before {
  background: #ffe835;
  border-radius: 3px 0px 0px 3px;
  color: #f4f4f4;
  display: block;
  float: left;
  height: 50px;
  line-height: 50px;
  text-align: center;
  width: 50px;
}

.facebook {
  background: rgb(255, 253, 228);
  border: none;
  border-radius: 0px 3px 3px 0px;
  color: #281f10;
  cursor: pointer;
  height: 50px;
  text-transform: uppercase;
  width: 250px;
}




.twitter-before {
  background: #189bcb;
  border-radius: 3px 0px 0px 3px;
  color: #f4f4f4;
  display: block;
  float: left;
  height: 50px;
  line-height: 50px;
  text-align: center;
  width: 50px;
}


.twitter {
  background: rgb(255, 253, 228);
  border: none;
  border-radius: 0px 3px 3px 0px;
  color: #281f10;
  cursor: pointer;
  height: 50px;
  text-transform: uppercase;
  width: 250px;
}

</style>
</head>
<body>
<div id="login">

  <h1><strong>APUJIMA</strong> 로그인</h1>

  <form action="login" method=post>

    <fieldset>

      <p><input type="text"  name = 'id' placeholder="UserId" value = "${cookie.email.value}"></p>

      <p><input type="password" name = 'password' placeholder="Password"></p>


 <div class="mb-3">
  <div class="form-check">
    <input id="dropdownCheck" class="form-check-input" 
    type="checkbox" name="saveEmail" ${not empty cookie.email ? "checked" : ""}>
    <label class="form-check-label" for="flexCheckDefault"> Remember me</label>
  </div>
  </div>


		<!--     <div class="mb-3">
		      <div class="form-check">
		        <input type="checkbox" class="form-check-input" id="dropdownCheck">
		        <label class="form-check-label" for="dropdownCheck">
		          Remember me
		        </label>
		      </div>
		    </div> -->


      <p><a href="#">아이디/패스워드 찾기</a></p>

      <p><input type="submit" value="Login" style="background-color: #3d6356;"></p>
      
    
     
    </fieldset>

  </form>

  <p><span class="btn-round">or</span></p>

<<<<<<< HEAD
  <p>

    <a class="facebook-before"><span class="fontawesome-facebook"></span></a>
    <button class="facebook">Login Using Facebook</button>

  </p>

  <p>

    <a class="twitter-before"><span class="fontawesome-twitter"></span></a>
    <button class="twitter">Login Using Twitter</button>

  </p>
  
  
=======
 
    <ul>
    <li onclick="kakaoLogin();">
    <a class="facebook-before"><img class="white" src="/apus/img/kakaotalk.svg" alt="카카오톡" style="width: 30px; height: 30px; margin-top: 10px;"></a>
    <button class="facebook" onclick="location.href='javascript:void(0)'">카카오 로그인</button>
    </li>
    </ul>
    <ul style="margin-top: 5px;">
    <li onclick="kakaoLogout();">
    <a class="facebook-before"><img class="white" src="/apus/img/kakaotalk.svg" alt="카카오톡" style="width: 30px; height: 30px; margin-top: 10px;"></a>
    <button class="twitter" onclick="location.href='javascript:void(0)'">카카오 로그아웃</button>
    <li>
    </ul>
      
    <!--   <a id="kakao-login-btn"></a>
    <a href="http://developers.kakao.com/logout">Logout</a> -->
>>>>>>> 4ee0c33b2c69cc385d45afb4b084dcc4e827465c
  
  <ul>
  <!-- <li onclick="kakaoLogin();">
      <a href="javascript:void(0)">
          <span><button>카카오 로그인</button></span>
      </a>
  </li> -->
  <!-- <li onclick="kakaoLogout();">
     <a href="javascript:void(0)">
          <span>카카오 로그아웃</span>
      </a>
  </li> -->
</ul>

</div>

<script>
Kakao.init('20a420d97d3e04fba7060b53450a6265'); //발급받은 키 중 javascript키를 사용해준다.
console.log(Kakao.isInitialized()); // sdk초기화여부판단
//카카오로그인
function kakaoLogin() {
    Kakao.Auth.login({
      success: function (response) {
        Kakao.API.request({
          url: '/v2/user/me',
          success: function (response) {
            console.log(response)
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
          console.log(response)
        },
        fail: function (error) {
          console.log(error)
        },
      })
      Kakao.Auth.setAccessToken(undefined)
    }
  }  
</script>

<script type='text/javascript'>
        //<![CDATA[
        // 사용할 앱의 JavaScript 키를 설정해 주세요.
        Kakao.init('20a420d97d3e04fba7060b53450a6265');
        // 카카오 로그인 버튼을 생성합니다.
        Kakao.Auth.createLoginButton({
            container: '#kakao-login-btn',
            success: function (authObj) {
                alert(JSON.stringify(authObj));
            },
            fail: function (err) {
                alert(JSON.stringify(err));
            }
        });
      //]]>
    </script>


</body>
</html>