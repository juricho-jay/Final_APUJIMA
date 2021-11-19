<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
  <title>상담 상세보기</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  
  <style>
    
  </style>
</head>

<body>
<div class="container">
<h1>상담 상세보기</h1>

<div class="mb-3 row counsel">
<label for='f-name' class="col-sm-2 col-form-label p">이름</label>
<div class="col-sm-6 pp">${counseling.client.name}</div>
</div>
<div class="mb-3 row counsel">
<label for='f-tel' class="col-sm-2 col-form-label p">휴대번호</label>
<div class="col-sm-6 pp">${counseling.client.tel}</div>
</div>
<div class="mb-3 row counsel">
<label for='f-disease' class="col-sm-2 col-form-label p">질병여부</label>
<div class="col-sm-6 pp">${counseling.disease}</div>
</div>
<div class="mb-3 row counsel">
<label for='f-content' class="col-sm-2 col-form-label p">상담내용</label>
<div class="col-sm-6 pp">${counseling.content}</div>
</div>
<div class="mb-3 row counsel">
<label for='f-createddt' class="col-sm-2 col-form-label p">날짜</label>
<div class="col-sm-6 pp">${counseling.registeredDate}</div>
</div>

<a href='${contextPath}/app/counseling/doctorlist' class="btn btn-primary">목록</a><br>

</div><!-- .container -->
</body>
</html>








