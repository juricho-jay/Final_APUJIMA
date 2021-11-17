<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
  <title>게시글 작성</title>
   <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
  
  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
  <script src="https://unpkg.com/@popperjs/core@2/dist/umd/popper.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
   
  
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
      <c:if test = "${loginUser.doctorOrNot == 3}" >
      <input id='f-whichBoard' type='radio' name='whichBoard' value = "3">
      <label>공지사항</label>
      </c:if>
    </div>
</div>

<div class="mb-3 row">
    <label for='f-content' class="col-sm-3 col-form-label">작성자</label>
    <div class="col-sm-6">
     <input id='f-id' type='text' name='writer' class="form-control" placeholder='${loginUser.nickname}' readonly>
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
  <input type = "submit" class="btn btn-primary btn-sm"  value = "완료">
  </div>
</form>
</div>

</body>
</html>









