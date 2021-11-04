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
    <p>잘못된 접근 방식입니다. 아래 버튼을 눌러 이동해주세요.</p>
<form>  
  <a href='member/list' class="btn btn-primary">회원 목록</a>
  <a href='board/list' class="btn btn-primary">게시글 목록</a>
  <a href='index.jsp' class="btn btn-primary">메인 페이지</a>
</form>
</div> <!-- container -->

</body>
</html>