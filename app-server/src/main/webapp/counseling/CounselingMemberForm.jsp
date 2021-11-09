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
  
  <style>
   .container {
    xborder: 1px solid red;
    width: 640px;
    text-align: left;
    }
	 .all{
	  xborder: 1px solid red;
	  width: 640px;
	  margin-top: 100px;
	  }
	  .gender{
	  xborder: 1px solid red;
	  margin-top: 5px;
	  }
	 
	  textarea {
	  width: 460px;
	  height: 200px;
	  padding: 10px;
}
  </style>
</head>
<body>
<div class="container">
<h1>상담신청하기</h1>
<form name = "counselingmember" action='add' class='all'>
  <div class="mb-3 row">
    <label for='f-client' class="col-sm-3 col-form-label">이름</label>
    <div class="col-sm-6">
      <input id='f-client' type='text' name='client.id' class="form-control" value="${loginUser.id}" readOnly>
    </div>
  </div>
  <div class="mb-3 row">
    <label for='f-tel' class="col-sm-3 col-form-label">휴대번호</label>
    <div class="col-sm-6">
      <input id='f-tel' type='text' name='tel' class="form-control" value="${loginUser.phoneNum}" readOnly>
    </div>
  </div>
  <div class="mb-3 row">
    <label for='f-disease' class="col-sm-3 col-form-label">질병여부</label>
    <div class="col-sm-6">
      <input id='f-disease' type='text' name='disease' class="form-control">
    </div>
  </div>
  <div class="mb-3 row">
    <label for='f-content' class="col-sm-3 col-form-label">상담내용</label>
    <br>
  <textarea id="f-content" name="content" placeholder ="자유롭게 기입해주세요. 상담시 도움이 됩니다." class="form-control"  >
  </textarea>
  </div>
  <div class="mb-3 row">
    <label for='f-grade' class="col-sm-3 col-form-label">상담사 성별</label>
    <div class="col-sm-6 gender">
      <input id='f-sex' type='radio' name='sex' value = "여">
      <label>여자</label>
      <input id='f-sex' type='radio' name='sex' value = "남">
      <label>남자</label>
      <input id='f-sex' type='radio' name='sex' value = "상관없음">
      <label>상관없음</label>
    </div>
</div>
  <div class="col-12">
    <button class="btn btn-primary btn-sm" >상담 신청하기</button>
  </div>
</form>
</div><!-- .container -->
</body>
</html>