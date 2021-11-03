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
  .container {
    xborder: 1px solid red;
    width: 640px;
  }
  </style>
</head>
<body>
<div class="container">
<h1>회원가입</h1>
<!-- <form action='add'> -->
<form action='add'>
<div class="col-md-3">
  <label for="validationServer04" class="form-label ">일반/의사</label>
  <select class="form-select is-invalid" name="userType" id="validationServer04" aria-describedby="validationServer04Feedback" required>
    <option selected disabled value="0">선택</option>
    <option value="1">일반 회원</option>
    <option value="2">의사</option>
  </select>
  <div id="validationServer04Feedback" class="invalid-feedback">
    Please select a valid state.
  </div>
</div>
  <div class="mb-3 row">
    <label for='f-name' class="col-sm-2 col-form-label">이름</label>
    <div class="col-sm-6">
      <input id='f-name' type='text' name='name' class="form-control">
    </div>
</div>
<div class="mb-3 row">
    <label for='f-id' class="col-sm-2 col-form-label">아이디</label>
    <div class="col-sm-10">
      <input id='f-id' type='id' name='id' class="form-control">
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

  <div class="col-md-4">
    <label for="validationServer02" class="form-label">Last name</label>
    <input type="text" class="form-control is-valid" id="validationServer02" value="Otto" required>
    <div class="valid-feedback">
      
    </div>
  </div>
  <div class="col-md-4">
    <label for="validationServerUsername" class="form-label">Username</label>
    <div class="input-group has-validation">
      <span class="input-group-text" id="inputGroupPrepend3">@</span>
      <input type="text" class="form-control is-invalid" id="validationServerUsername" aria-describedby="inputGroupPrepend3 validationServerUsernameFeedback" required>
      <div id="validationServerUsernameFeedback" class="invalid-feedback">
        이름은 필수 입력입니다.
      </div>
    </div>
  </div>
  <div class="col-md-6">
    <label for="validationServer03" class="form-label">City</label>
    <script>
    
    <input type="text" class="form-control is-invalid" id="validationServer03" aria-describedby="validationServer03Feedback" required>

    </script>
    <input type="text" class="form-control is-invalid" id="validationServer03" aria-describedby="validationServer03Feedback" required>
    <div id="validationServer03Feedback" class="invalid-feedback">
      필수 **입니다.
    </div>
  </div>
 
  <div class="col-md-3">
    <label for="validationServer05" class="form-label">Zip</label>
    <input type="text" class="form-control is-invalid" id="validationServer05" aria-describedby="validationServer05Feedback" required>
    <div id="validationServer05Feedback" class="invalid-feedback">
      Please provide a valid zip.
    </div>
  </div>
  <div class="col-12">
    <div class="form-check">
      <input class="form-check-input is-invalid" type="checkbox" value="" id="invalidCheck3" aria-describedby="invalidCheck3Feedback" required>
      <label class="form-check-label" for="invalidCheck3">
        Agree to terms and conditions
      </label>
      <div id="invalidCheck3Feedback" class="invalid-feedback">
        You must agree before submitting.
      </div>
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









