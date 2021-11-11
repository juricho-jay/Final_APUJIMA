<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    
<!DOCTYPE html>
<html>
<head>
<title>ErrorPage</title>
 
   <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
 
   <style>
    .container {
        xborder: 1px solid red;
        width: 640px;
    }
   </style>
</head>
<body>

<div class = "container">
    <h1>로그인 실패!</h1>
    <p>아이디나 암호가 일치하지 않습니다!</p>
<form>  
  <a href='/apus/member/MemberSignUp.jsp' class="btn btn-primary">로그인 페이지</a>
  <a href='/apus/index.jsp' class="btn btn-primary">메인 페이지</a>
</form>
</div> <!-- container -->

</body>
</html>