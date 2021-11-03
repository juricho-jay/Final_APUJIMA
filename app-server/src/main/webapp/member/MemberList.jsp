<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>회원목록</title>
</head>
<body>
<h1>회원 목록</h1>
<a href='form'>새회원</a><br>
<table border='1'>
<thead>
  <tr>
    <th>번호</th>
    <th>이름</th>
    <th>아이디</th>
    <th>생년월일</th>
    <th>연락처</th>
    <th>이메일</th>
    <th>성별</th>
    <th>포인트</th>
    <th>회원등급</th>
    <th>등록일</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${memberList}" var="member">
<tr>
    <td>${member.no}</td>
    <td><a href='detail?no=${member.no}'>${member.name}</a></td> 
    <td>${member.id}</td> 
    <td>${member.birthDay}</td> 
    <td>${member.phoneNum}</td> 
    <td>${member.email}</td> 
    <td>${member.sex}</td> 
    <td>${member.point}</td> 
    <td>${member.doctorOrNot}</td> 
    <td>${member.registeredDate}</td>
</tr>
</c:forEach>

</tbody>
</table>
</body>
</html>








