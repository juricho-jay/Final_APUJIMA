<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>회원가입</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">

  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../bootstrap/dist/bootstrap.css"></script>
  
  <style>
  .head1 {
  margin-bottom:50px;
  }
  .container {
    xborder: 1px solid red;
    width: 640px;
    xmargin-top: 230px;
  }
  </style>
</head>
<body>

<div class="container">
<h1 class ="head1" >의사 회원가입</h1>
<!-- <form action='add'> -->
<form action='add'>

  <div class="mb-3 row">
    <label for='f-name' class="col-sm-2 col-form-label">이름</label>
    <div class="col-sm-6">
      <input id='f-name' type='text' name='name' class="form-control">
    </div>
</div>

<div class="mb-3 row">
    <label for='f-id' class="col-sm-2 col-form-label">아이디</label>
    <div class="col-sm-10">
      <input id='f-id' type='text' name='id' class="form-control">
    </div>
</div>

<div class="mb-3 row">
    <label for='f-email' class="col-sm-2 col-form-label">이메일</label>
    <div class="col-sm-10">
      <input id='f-email' type='email' name='email' class="form-control">
    </div>
</div>

<div class="mb-3 row">
  <label for='f-password' class="col-sm-2 col-form-label">암호</label>
  <div class="col-sm-6">
    <input id='f-password' type='password' name='password' class="form-control">
  </div>
</div>


<div class="mb-3 row">
  <label for='f-password' class="col-sm-2 col-form-label">전공</label>
  <div class="col-sm-6">
    <input id='f-major' type='text' name='major' class="form-control">
  </div>
</div>

<div class="mb-3 row">
  <label for='f-password' class="col-sm-2 col-form-label">의사면허</label>
  <div class="col-sm-6">
    <input id='f-license' type='text' name='license' class="form-control">
  </div>
</div>
 
  <div class="col-12">
    <button class="btn btn-primary" type="submit">회원 가입</button>
  </div>
  
</form>
</div><!-- .container -->

<!-- <label for='f-name'>이름</label> <input id='f-name' type='text' name='name'><br>
<label for='f-email'>이메일</label> <input id='f-email' type='email' name='email'><br>
<label for='f-password'>암호</label> <input id='f-password' type='password' name='password'><br>
<label for='f-photo'>사진</label> <input id='f-photo' type='text' name='photo'><br>
<label for='f-tel'>전화</label> <input id='f-tel' type='tel' name='tel'><br>
<button>등록</button><br> -->
<!-- </form> -->
</body>
</html>









