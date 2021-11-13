<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>약품 목록</title>
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
<h1>약품 목록</h1>
<c:if test = "${loginUser.doctorOrNot eq 2}">

<a href='MedicineRequestForm.jsp' class="btn btn-outline-primary btn-sm">약품 등룍 요청</a>
</c:if>

<c:if test = "${loginUser.doctorOrNot eq 3}">

<a href='MedicineForm.jsp' class="btn btn-outline-primary btn-sm">약품 등록</a>
</c:if>

<!--  <a href='form' class="btn btn-outline-primary btn-sm">분류</a><br>-->
<table class="table table-hover">
<thead>
  <tr>
    <th>번호</th>
    <th>약품명</th>
    <th>권장 연령</th>
    <th>약품 모양</th>
    <th>약품 색상</th>
    <th>약품 효능</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${medicineList}" var="medicine">
<tr>
    <td>${medicine.no}</td>
    <td><a href='detail?no=${medicine.no}'>${medicine.name}</a></td>
    <td>${medicine.ageLimit}</td> 
    <td>${medicine.shape}</td> 
    <td>${medicine.color}</td> 
    <td>${medicine.effect}</td> 
</tr>
</c:forEach>
</tbody>
</table>

</div><!-- .container -->

</body>
</html>








