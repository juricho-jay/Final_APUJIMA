<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>약품 등록 요청</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">

  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../bootstrap/dist/bootstrap.css"></script>
  
   <script type="text/javascript">
    
        // 필수 입력정보인 아이디, 비밀번호가 입력되었는지 확인하는 함수
        function checkValue()
        {
        	
           if(!document.medicineRequest.name.value){
                alert("약품 이름을 작성해 주세요.");
                return false;
            }
           
           if(!document.medicineRequest.age.value){
               alert("권장 연령을 작성해 주세요.");
               return false;
           }
           
           
           if(!document.medicineRequest.shape.value){
               alert("약품 모양을 작성해 주세요.");
               return false;
           }
           
           if(!document.medicineRequest.color.value){
               alert("약품 색상을 작성해 주세요.");
               return false;
           }
           
           if(!document.medicineRequest.effect.value){
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
<h1>약품 등록 요청</h1>
<form name = "medicineRequest" action='request' onsubmit="return checkValue()">

<div class="col-md-6">
  <label for="validationServer04" class="form-label">-약품 정보에 도움을 주셔서 감사합니다.-</label>
 
</div>


  <div class="mb-3 row">
    <label for='m-name' class="col-sm-3 col-form-label">* 약품명</label>
    <div class="col-sm-6">
      <input id='m-name' type='text' name='name' class="form-control">
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
    <button class="btn btn-primary btn-sm" >약품 등록 신청</button>
  </div>

</div>




<!-- <div class="mb-3 row">
  <label for='f-photo' class="col-sm-2 col-form-label">성별</label>
  <div class="col-sm-6">
  
    <select id='f-sex' name='sex' class="form-control">
    <option value = "" placeholder="성별"></option>
    <option value = "남성">남자</option>
    <option value = "여성">여자</option>
    </select>
    
  </div>
</div> -->

</form>
</div><!-- .container -->

<!--  
<script>
$(document).on("keyup", "input[age]", function() {
    var val= $(this).val();
    if(val.replace(/[0-9]/g, "").length > 0) {
        alert("숫자만 입력해 주십시오.");
        $(this).val('');
    }
    if(val < 1 || val > 100) {
        alert("1~100 범위로 입력해 주십시오.");
        $(this).val('');
    }
});
</script>
-->

</body>
</html>









