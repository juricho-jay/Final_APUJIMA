<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>내 화분 목록</title>
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
<h1>내 화분 목록</h1>
<a href='form' class="btn btn-outline-primary btn-sm">분류</a><br>
<table class="table table-hover">
<thead>
  <tr>
    
    <th>화분 이름</th>
    <th>화분 주인</th>
    <th>화분 레벨</th>
    <th>화분 경험치</th>
    <th>화분 모양</th>
    <th>화분 생성일</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${plantList}" var="plant">
<tr>
    <td>${plant.plantName}</td>
    <td><a href='detail?no=${plant.no}'>${plant.ownerName.nickname}</a></td> 
    <td>${plant.level}</td> 
    <td>${plant.exp}</td> 
    <td>${plant.shape}</td>
    <td>${plant.registeredDate}</td> 
   
</tr>
</c:forEach>
</tbody>
</table>
<a href='PlantForm.jsp' class="btn btn-outline-primary btn-sm">화분 생성</a><br>

</div><!-- .container -->
</body>
</html>








