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
    <h1> 에러 페이지입니다.</h1>
    <p>여긴 로그인 에러지롱~~.</p>
    <p>아이디 비밀번호가 맞는게 읎다!! 돌아가라!!</p>
<form>  
  <a href='/apus/member/MemberSignUp.jsp' class="btn btn-primary">로그인 페이지</a>
  <a href='/apus/index.jsp' class="btn btn-primary">메인 페이지</a>
</form>
</div> <!-- container -->

</body>
</html>