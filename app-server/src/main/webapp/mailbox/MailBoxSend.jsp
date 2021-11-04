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
    xwidth: 640px;
    }
  </style>
  
</head>
<body>
<div class="container">
<h1>쪽지보내기</h1>
<table class="table table-hover">
<thead>
  <tr>
    <th>번호</th>
    <th>보낸사람</th>
    <th>제목</th>
    <th>날짜</th>
  </tr>
</thead>
<tbody>
<c:forEach items="${mailBoxList}" var="mailBox">
<tr>
  <td>${mailBox.no}</td>
  <td><a href='detail?no=${mailBox.no}'>${mailBox.receiver}</a></td> 
  <td>${mailBox.title}</td>
  <td>${mailBox.receivedTime}</td>
</tr>
</c:forEach>
</tbody>
</table>
<a href='MailBoxAdd.jsp' class="btn btn-outline-primary btn-sm">보내기</a><br>
</div><!-- .container -->
</body>
</html>








