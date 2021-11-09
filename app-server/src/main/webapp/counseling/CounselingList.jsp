<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>상담신청</title>
<link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core//dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  
</head>
<body>
<div class="container">
<h1>${loginUser.id}님의 상담신청리스트</h1>
<a href='CounselingForm.jsp' class="btn btn-outline-primary btn-sm">상담신청하기</a><br>
<table class="table table-hover">
<thead>
  <tr>
    <th>번호</th>
    <th>지병여부</th>
    <th>상담내용</th>
    <th>날짜</th>
  </tr>
</thead>

</table>
</div><!-- .container -->
</body>
</html>