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
    }
    .listbtn {
    xborder: 1px solid red;
    width: 640px;
    text-align: center;
    margin-top: 20px;
    }
  </style>
  
</head>
<body>
<div class="container">
<h1>쪽지함 상세보기</h1>
<table class = "table">
<tbody>
<tr>
	<td style = "width: 20%">제목</td>
	<td colspan ="2">${mailbox.title}</td>
</tr>
<tr>
	<td>작성자</td>
	<td>${mailbox.receiver.id}</td>
</tr>
<tr>
	<td>내용</td>
	<td>${mailbox.content}</td>
</tr>
</tbody>
</table>
<div class="listbtn">
<a href='MailBoxList.jsp' class="btn btn-outline-primary btn-sm">목록</a><br>
</div>
</div><!-- .container -->
</body>
</html>








