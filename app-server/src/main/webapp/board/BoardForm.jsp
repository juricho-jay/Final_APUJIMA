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
  
   <script type="text/javascript">
   
    
        // 필수 입력정보인 아이디, 비밀번호가 입력되었는지 확인하는 함수
        function checkValue()
        {
          
        	 if(!document.boardInfo.whichBoard.value){
                alert("게시판 타입을 선택하세요.");
                return false;
            }
           if(!document.boardInfo.title.value){
                alert("제목을 입력하세요.");
                return false;
            }
          
            if(!document.userInfo.content.value){
                alert("내용을 입력하세요.");
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
  textarea{
    height:350px;
    width: 200%;
  }
  </style>
  
</head>
<body>
<div class="container">
<div class=""></div>
<h1>게시글 작성</h1>
<form name = "boardInfo" action='add' onsubmit="return checkValue()">

<div class="col-md-6">
  <label for="validationServer04" class="form-label">게시판 글 작성 페이지</label>
</div>
  
  <div class="mb-3 row">
    <label for='f-whichBoard' class="col-sm-3 col-form-label">구분</label>
    <div class="col-sm-6">
      <input id='f-whichBoard' type='radio' name='whichBoard' value = "1">
      <label>자유게시판</label>
      <input id='f-whichBoard' type='radio' name='whichBoard' value = "2">
      <label>Healer 지식in </label>
      <input id='f-whichBoard' type='radio' name='whichBoard' value = "3">
      <label>공지사항</label>
    </div>
</div>

<div class="mb-3 row">
    <label for='f-content' class="col-sm-3 col-form-label">작성자</label>
    <div class="col-sm-6">
     <input id='f-id' type='text' name='writer' class="form-control" placeholder='${loginUser.id}' readonly>
    </div>
</div>

  <div class="mb-3 row">
    <label for='f-title' class="col-sm-3 col-form-label">제목</label>
    <div class="col-sm-6">
      <input id='f-title' type='text' name='title' class="form-control">
    </div>
</div>

<div class="mb-3 row">
    <label for='f-content' class="col-sm-3 col-form-label">내용</label>
    <div class="col-sm-6">
      <textarea id='f-content'  name='content' maxlength ="2048" ></textarea>
    </div>
</div>




  <div class="col-12">
  <input type = "submit" class="btn btn-primary btn-sm"  value = "완료"></a>
  </div>
</form>
</div>

</body>
</html>









