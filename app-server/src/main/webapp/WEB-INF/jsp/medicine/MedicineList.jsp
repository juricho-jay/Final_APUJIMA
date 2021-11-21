<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="/apus/css/MedicineList.css">

<title>약품 목록</title>
<div>
<h1>약품 목록</h1>
<c:if test = "${loginUser.doctorOrNot eq 2}">

<a href='requestForm' class="btn btn-outline-primary btn-sm">약품 등록 요청</a>
</c:if>

<c:if test = "${loginUser.doctorOrNot eq 3}">

<a href='form' class="btn btn-outline-primary btn-sm">약품 등록</a>
</c:if>

<!--  <a href='form' class="btn btn-outline-primary btn-sm">분류</a><br>-->
<table class="table table-hover">
<thead>
  <tr>
    <th scope ="col">번호</th>
    <th scope ="col">약품명</th>
    <th scope ="col">권장 연령</th>
    <th scope ="col">약품 모양</th>
    <th scope ="col">약품 색상</th>
    <th scope ="col">약품 효능</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${medicineList}" var="medicine">
<tr>
    <td style= "width: 5%;">${medicine.no}</td>
    <td style= "width: 10%;"><a href='detail?no=${medicine.no}'>${medicine.name}</a></td>
    <td style= "width: 5%;">${medicine.ageLimit}</td> 
    <td style= "width: 5%;">${medicine.shape}</td> 
    <td style= "width: 5%;">${medicine.color}</td> 
    <td style= "width: 5%;">${medicine.effect}</td> 
</tr>
</c:forEach>
</tbody>
</table>
</div>







