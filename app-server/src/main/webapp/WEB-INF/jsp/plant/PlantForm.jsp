<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>게시글 작성</title>
 <link rel="stylesheet" href="${contextPath}/node_modules/bootstrap/dist/css/bootstrap.css">
  <script src="${contextPath}/node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="${contextPath}/bootstrap/dist/bootstrap.css"></script>
  
  

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
<div class="container1">
<div class="d"></div>
<h1>화분 심기</h1>
<form name = "plnatInfo" action='add' onsubmit="return checkValue()" method = "post">

<div class="col-md-6">
  <label for="validationServer04" class="form-label">화분 생성 페이지</label>
</div>

<div class="mb-3 row">
    <label for='f-content' class="col-sm-3 col-form-label">화분 주인</label>
    <div class="col-sm-6">
     <input id='f-onwer' type='text' name='owner' class="form-control" placeholder='${loginUser.nickname}' readonly>
    </div>
</div>

  <div class="mb-3 row">
    <label for='f-plantName' class="col-sm-3 col-form-label">화분 이름</label>
    <div class="col-sm-6">
      <input id='f-plantName' type='text' name='plantName' class="form-control">
    <div class ="invalid-feedback">
    이미 존재하는 화분입니다.
   </div>
  </div>
   <div class="col-auto">
    <button id="p-plant-name-btn" type="button" class="btn btn-primary form-control">중복검사</button>
  </div>
</div>

  <div class="col-12">
  <input type ="submit" value ="화분 등록" class ="btn btn-primary">
<!--   <button id = "p-add-check-btn"  class ="btn btn-primary btn-sm">화분 추가</button>-->
  </div>
</form>
</div>

<script>
var addBtn = document.querySelector("#p-add-check-btn");
var nameTag = doucument.querySelector("#f-plantName");
addBtn.setAttribute("disabled","disabled");


	
	
	
	document.querySelector("#p-plant-name-btn").onclick =() =>{
		  var xhr = new XMLHttpRequest();
		  xhr.addEventListener("load",function() {
			  if (this.responseText == "false"){
				  addBtn.removeAttribute("disabled");
				  nameTag.classList.remove("is-invalid");
			  } else {
				  addBtn.sendAttribute("disabled" , "disabled");
				  nameTag.classList.add("is-invalid");
			  }
		  })
		  xhr.open("get" , "checkEmail?email=" + nameTag.value);
		  xhr.send();
};


</script>
</body>
</html>









