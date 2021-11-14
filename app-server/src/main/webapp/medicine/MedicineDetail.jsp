<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>약품 상세</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">

  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../bootstrap/dist/bootstrap.css"></script>
  
<script>
 
function nameCheck(frm){
  
  frm.action = "check";
  frm.submit();
  return false;
  
}
</script>

<script>
function addMedicine(frm){
  
  frm.action = "add";
  frm.submit();
  return false;
  
}
</script>


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
  </style>
  
</head>
<body>
<div class="container">
<div class=""></div>
<h1>약품 정보 변경</h1>
<form  action='update'>
<!--  폼으로 왜 안넘어 가고 계속 nameCheck에 걸리는건지 알아보기 -->

<div class="col-md-6">
  <label for="validationServer04" class="form-label">-APUJIMA에 오신 것을 환영합니다-</label>
</div>

  <div class="mb-3 row">
  <label for='f-no' class="col-sm-3 col-form-label">번호</label>
  <div class="col-sm-6">
    <input id='f-no' type='text' name='no' class="form-control" value='${medicine.no}' readonly>
  </div>
</div>

  <div class="mb-3 row">
    <label for='f-name' class="col-sm-3 col-form-label">* 이름</label>
    <div class="col-sm-6">
      <input id='f-name' type='text' name='name' class="form-control" value="${medicine.name}">
    </div>
</div>

<div class="mb-3 row">
    <label for='m-age' class="col-sm-3 col-form-label">* 권장 연령</label>
    <div class="col-sm-6">
      <input id='m-age' type='text' name='age' class="form-control" value="${medicine.ageLimit}" onKeyup="this.value=this.value.replace(/[^-0-9]/g,'');"/>
    </div>
</div>

<div class="mb-3 row">
  <label for='m-shape' class="col-sm-3 col-form-label">* 약품 모양</label>
  <div class="col-sm-6">
    <input id='m-shape' type='text' name='shape' class="form-control" value="${medicine.shape}">
  </div>
</div>
<div class="mb-3 row">
  <label for='m-color' class="col-sm-3 col-form-label">* 약품 색상</label>
  <div class="col-sm-6">
    <input id='m-color' type='text' name='color' class="form-control"value="${medicine.color}">
  </div>
</div>
<div class="mb-3 row">
    <label for='m-effect' class="col-sm-3 col-form-label">* 약품 효능</label>
    <div class="col-sm-6">
      <input id='m-effect' type='text' name='effect' class="form-control"value="${medicine.effect}">
    </div>
</div>
  <div class="col-12">
    <button class="btn btn-primary">약품정보 변경</button>
    <a href='delete?name=${medicine.name}' class="btn btn-primary">삭제</a> 
<a href='list' class="btn btn-primary">목록</a><br>
  </div>

</form>
</div>


<!-- <label for='f-name'>이름</label> <input id='f-name' type='text' name='name'><br>
<label for='f-email'>이메일</label> <input id='f-email' type='email' name='email'><br>
<label for='f-password'>암호</label> <input id='f-password' type='password' name='password'><br>
<label for='f-photo'>사진</label> <input id='f-photo' type='text' name='photo'><br>
<label for='f-tel'>전화</label> <input id='f-tel' type='tel' name='tel'><br>
<button>등록</button><br>
</form>  -->

 




</body>
</html>