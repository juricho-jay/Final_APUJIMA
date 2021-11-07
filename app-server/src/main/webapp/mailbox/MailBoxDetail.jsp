<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<!DOCTYPE html>
<html>
<head>
  <title>쪽지함</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
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
<a href='MailBoxForm.jsp' class="btn btn-outline-primary btn-sm">보내기</a><br>
<table>
<tbody>
<tr>
  <td style = "width: 20%">제목</td>
  <td colspan ="2">${mailBox.title}</td>
</tr>
</tbody>
</table>
<%--  <table class="table table-hover">
<thead>
  <tr>
    <th>번호</th>
    <th>보낸이</th>
    <th>받는이</th>
    <th>제목</th>
    <th>내용</th>
    <th>날짜</th>
    <th>확인날짜</th>
  </tr>
</thead>
<tbody>
<tr>
  <td>${mailBox.no}</td>
  <td>${mailBox.sender.id}</td> 
  <td>${mailBox.receiver.id}</td>
  <td><a href='detail?no=${mailBox.no}'>${mailBox.title}</a></td>
  <td>${mailBox.content}</td>
  <td>${mailBox.sentTime}</td>
  <td>${mailBox.receivedTime}</td>
</tr>
</tbody>
</table> --%>

<%-- <form action='list'>

<div class="mb-3 row">
<label for='f-sender' class="col-sm-2 col-form-label">보낸이</label>
<div class="col-sm-6">${mailBox.sender.id}</div>
</div>
<div class="mb-3 row">
<label for='f-receiver' class="col-sm-2 col-form-label">받는이</label>
<div class="col-sm-6">${mailBox.receiver.id}</div>
</div>
<div class="mb-3 row">
<label for='f-sentTime' class="col-sm-2 col-form-label">보낸 날짜</label>
<div class="col-sm-6">${mailBox.sentTime}</div>
</div>
<div class="mb-3 row">
<label for='f-sentTime' class="col-sm-2 col-form-label">확인 날짜</label>
<div class="col-sm-6">${mailBox.receivedTime}</div>
</div>
<div class="mb-3 row">
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
</form> --%>
</div><!-- .container -->
</body>
</html>








