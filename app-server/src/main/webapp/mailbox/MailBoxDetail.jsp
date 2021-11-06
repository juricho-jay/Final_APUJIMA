<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>쪽지함</title>
   <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core//dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  
  <style>
    .container {
    xborder: 1px solid red;
    width: 640px;
    height: 500px;
    }
    .listbtn {
    xborder: 1px solid red;
    text-align: center;
    margin-top: 100px;
    }
    .receiver {
    xborder: 1px solid red;
    }
    .time {
    font-size: 12px;
    color: gray;
    }
  </style>
</head>
<body>
<div class="container">
<h1>쪽지함 상세보기</h1>
<form action='list'>
<div class="mb-3 row sender">
<label for='f-sender' class="col-sm-2 col-form-label">보낸이</label>
<div class="col-sm-6">${mailBox.sender.id}</div>
</div>
<div class="mb-3 row receiver">
<label for='f-receiver' class="col-sm-2 col-form-label">받는이</label>
<div class="col-sm-6">${mailBox.receiver.id}</div>
</div>
<div class="mb-3 row time">
<label for='f-sentTime' class="col-sm-2 col-form-label">보낸 시간</label>
<div class="col-sm-6">${mailBox.sentTime}</div>
</div>
<div class="mb-3 row receiver">
<label for='f-title' class="col-sm-2 col-form-label">제목</label>
<div class="col-sm-6">${mailBox.title}</div>
</div>
<table class = "mailBox">
<tbody>
<tr>
	<td>내용</td>
	<td>${mailBox.content}</td>
</tr>
</tbody>
</table>
<button class="btn btn-primary btn-sm">목록</button>
<a href='delete?no=${mailBox.no}' class="btn btn-outline-primary btn-sm">삭제</a> 
</form>
</div><!-- .container -->
</body>
</html>








