<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>약품 등록</title>
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



<script type="text/javascript">
    
        // 필수 입력정보인 아이디, 비밀번호가 입력되었는지 확인하는 함수
        function checkValue()
        {
          
            if(!document.medicineAdd.name.value){
                alert("약품 이름을 작성해 주세요.");
                return false;
            }
           
           if(!document.medicineAdd.age.value){
               alert("권장 연령을 작성해 주세요.");
               return false;
           }
           
           
           if(!document.medicineAdd.shape.value){
               alert("약품 모양을 작성해 주세요.");
               return false;
           }
           
           if(!document.medicineAdd.color.value){
               alert("약품 색상을 작성해 주세요.");
               return false;
           }
           
           if(!document.medicineAdd.effect.value){
               alert("약품 효능을 작성해 주세요.");
               return false;
           }
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
<h1>약품등록</h1>
<form name = "medicineAdd" action='add' onsubmit="return checkValue()">
<!--  폼으로 왜 안넘어 가고 계속 nameCheck에 걸리는건지 알아보기 -->

<div class="col-md-6">
  <label for="validationServer04" class="form-label">-APUJIMA에 오신 것을 환영합니다-</label>
</div>

  <div class="mb-3 row">
    <label for='f-name' class="col-sm-3 col-form-label">* 이름</label>
    <div class="col-sm-6">
      <input id='f-name' type='text' name='name' >
      <input type="button" value="중복체크" onclick= "nameCheck(this.form)">  
    </div>
</div>

<div class="mb-3 row">
    <label for='m-age' class="col-sm-3 col-form-label">* 권장 연령</label>
    <div class="col-sm-6">
      <input id='m-age' type='text' name='age' class="form-control" onKeyup="this.value=this.value.replace(/[^-0-9]/g,'');"/>
    </div>
</div>

<div class="mb-3 row">
  <label for='m-shape' class="col-sm-3 col-form-label">* 약품 모양</label>
  <div class="col-sm-6">
    <input id='m-shape' type='text' name='shape' class="form-control">
  </div>
</div>
<div class="mb-3 row">
  <label for='m-color' class="col-sm-3 col-form-label">* 약품 색상</label>
  <div class="col-sm-6">
    <input id='m-color' type='text' name='color' class="form-control">
  </div>
</div>
<div class="mb-3 row">
    <label for='m-effect' class="col-sm-3 col-form-label">* 약품 효능</label>
    <div class="col-sm-6">
      <input id='m-effect' type='text' name='effect' class="form-control">
    </div>
</div>
  <div class="col-12">
    <button class="btn btn-primary btn-sm" onclick= "addMedicine(this.form)" >약품 등록 신청</button>
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