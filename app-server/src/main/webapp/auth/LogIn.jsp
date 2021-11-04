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
  background: #0064ab;
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
  background: #0079ce;
  border: none;
  border-radius: 0px 3px 3px 0px;
  color: #f4f4f4;
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
  background: #1bb2e9;
  border: none;
  border-radius: 0px 3px 3px 0px;
  color: #f4f4f4;
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

  <form action="#" method="get">

    <fieldset>

      <p><input type="text" required value="Username" placeholder="Username"></p>

      <p><input type="password" required value="Password" placeholder="Password"></p>

      <p><a href="#">아이디/패스워드 찾기</a></p>

      <p><input type="submit" value="Login"></p>
     
    </fieldset>

  </form>

  <p><span class="btn-round">or</span></p>

  <p>

    <a class="facebook-before"><span class="fontawesome-facebook"></span></a>
    <button class="facebook">Login Using Facbook</button>

  </p>

  <p>

    <a class="twitter-before"><span class="fontawesome-twitter"></span></a>
    <button class="twitter">Login Using Twitter</button>

  </p>

</div>
</body>
</html>