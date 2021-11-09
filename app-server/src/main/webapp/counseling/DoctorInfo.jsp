<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>APUJIMA 의사</title>
<link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core//dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  <style>
  .container {
    xborder: 1px solid red;
    width: 640px;
    text-align: left;
  }
  .doctor {
    width: 640px;
    height:500px; 
    margin-top: 100px;
    text-align: center;
  }
 .doctorjpg {
    border: 1px solid red;
    float:left;
    width:300px;
    height:400px; 
  }
 .doctorinfo {
	  display:inline-block; 
	  width:200px; 
	  height:100px; 
	  margin-left:100px;
	  margin-top: 100px;
  }  
  .title {
    font-size: 20px;
    font-weight: 200px;
    font-style: italic;
    font-family: 'Nanum Myeongjo', serif;
  }
</style>


</head>
<body>
<div class="container">
<h1>APUJIMA 의사</h1>
<c:forEach items="${memberList}" var="member">
<div class='doctor'>
<div class='doctorjpg'>
<img src="img/doctorinfo.jpeg" width="100" height="200">
</div>
<div class='doctorinfo'>
<p class='title'>"${member.doctor.introduction}"</p>
<p>이름 : ${member.name}</p>
<p>전문분야 : ${member.doctor.major}</p>
<p>메일 : ${member.email}</p>
<p>연락처 : ${member.phoneNum}</p>
</div>
</div>
</c:forEach>
</div><!-- .container -->
</body>
</html>