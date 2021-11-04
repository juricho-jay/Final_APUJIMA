<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>쪽지보내기</title>
   <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core//dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  
  <style>
    .container {
    xborder: 1px solid red;
    width: 640px;
    }
    .content {
    border: 1px solid gray;
    width: 640px;
    height: 400px;
    vertical-align: top;
    text-align: left;
    }
    .sendbtn {
    border: 1px solid red;
    width: 640px;
    text-align: center;
    }
  </style>
  
</head>
<body>
<div class="container">
<h1>쪽지보내기</h1>
<div class="content">
  <label for="box">제목</label>
  <br>
  <textarea id="box" name="box" rows=1 cols=73></textarea>
  <br><br>
  <label for="box">내용</label>
  <br>
  <textarea id="box" name="box" rows=10 cols=73></textarea>
</div>
<div class="sendbtn">
<a href='MailBoxAdd.jsp' class="btn btn-outline-primary btn-sm">보내기</a><br>
</div>
</div><!-- .container -->
</body>
</html>








