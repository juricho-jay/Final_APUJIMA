<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>게시글 작성</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">

  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../bootstrap/dist/bootstrap.css"></script>
  
  

  <style>
  .container {
    xborder: 1px solid red;
    width: 640px;
  }
  .gender{
    xborder: 1px solid red;
    width: 100px;
    margin-top: 5px;
  }
  textarea{
    height:350px;
    width: 200%;
  }
  </style>
  
</head>
<body>
<div class="container">
<div class=""></div>
<h1>화분 심기</h1>
<form name = "plnatInfo" action='add' onsubmit="return checkValue()">

<div class="col-md-6">
  <label for="validationServer04" class="form-label">게시판 글 작성 페이지</label>
</div>

<div class="mb-3 row">
    <label for='f-content' class="col-sm-3 col-form-label">화분 주인</label>
    <div class="col-sm-6">
     <input id='f-onwer' type='text' name='owner' class="form-control" placeholder='${loginUser.nickname}' readonly>
    </div>
</div>

  <div class="mb-3 row">
    <label for='f-title' class="col-sm-3 col-form-label">화분 이름</label>
    <div class="col-sm-6">
      <input id='f-plantName' type='text' name='name' class="form-control">
    </div>
</div>

  <div class="col-12">
  <input type = "submit" class="btn btn-primary btn-sm"  value = "완료">
  </div>
</form>
</div>

</body>
</html>









