<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>신고 게시판 목록</title>
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
<h1>승인 대기 게시판 목록</h1>

<!--  <a href='form' class="btn btn-outline-primary btn-sm">분류</a><br>-->
<table class="table table-hover">
<thead>
  <tr>
    <th>게시판 종류</th>
    <th>제목</th>
    <th>작성자</th>
    <th>신고자</th>
    <th>신고 사유</th>
    <th  style = 'text-align: right'>승인관리</th>
  </tr>
</thead>
<tbody>
<c:forEach items="${medicineApprovalList}" var="medicine">
<form action = 'confirm'>
<tr>
    <td>${medicine.no}</td>
    <td>${medicine.name}</td>
    <input type = "hidden" name = "name" value = "${medicine.name}">
    <td>${medicine.ageLimit}</td> 
    <td>${medicine.shape}</td> 
    <td>${medicine.color}</td> 
    <td><input type = "submit" value ='승인'></td>
    <td><input type = "button" value = "거절"  onclick = "reject(this.form);"></td>
    
</tr>
</form>
</c:forEach>
</tbody>
</table>
</div><!-- .container -->

<script>

function reject(frm) {
	frm.action = "reject";
	frm.submit();
	return true;
}

</script>

</body>
</html>








