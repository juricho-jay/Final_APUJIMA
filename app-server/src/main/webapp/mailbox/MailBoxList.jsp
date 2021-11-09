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
    xwidth: 640px;
    }
  </style>
  
</head>


    
<body>
<div class="container">
<h1>${loginUser.id}님의 쪽지함</h1>
<a href='MailBoxForm.jsp' class="btn btn-outline-primary btn-sm">보내기</a><br>
<table class="table table-hover">
<thead>
  <tr>
    <th scope="col"><input type="checkbox" name="select_all" id="select_all" value="select_all"></th>
    <th>번호</th>
    <th>보낸이</th>
    <!-- <th>받는이</th> -->
    <th>제목</th>
    <th>내용</th>
    <th>날짜</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${mailBoxList}" var="mailBox">
<tr>
  <td><input type="checkbox" name="select_tch" id="select_tch" value="${list.userno}"></td>
  <td>${mailBox.no}</td>
  <td>${mailBox.sender.id}</td> 
  <%-- <td>${mailBox.receiver.id}</td> --%>
  <td><a href='detail?no=${mailBox.no}'>${mailBox.title}</a></td>
  <td>${mailBox.content}</td>
  <td>${mailBox.sentTime}</td>
</tr>

</c:forEach>
</tbody>
</table>
</div><!-- .container -->
</body>
</html>








