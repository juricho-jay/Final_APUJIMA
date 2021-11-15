<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>쪽지보내기</title>
   <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../bootstrap/dist/bootstrap.css"></script>
  
  <script type="text/javascript">
    
        // 필수 입력정보인 아이디, 비밀번호가 입력되었는지 확인하는 함수
        function checkValue()
        {
          
           if(!document.usersend.sender.id.value){
                alert("아이디를 입력하세요.");
                return false;
            }
          
            if(!document.usersend.title.value){
                alert("제목을 입력하세요.");
                return false;
            } 
            
            if(!document.usersend.content.value){
                alert("내용을 입력하세요.");
                return false;
            }
            
        }  
    </script>
 
  <style>
  
    .container {
    xborder: 1px solid red;
    width: 640px;
    text-align: left;
    }
    .content {
    xborder: 1px solid gray;
    width: 640px;
    }
    .sendbtn {
    border: 1px solid red;
    width: 640px;
    text-align: center;
    margin-top: 20px;
    }
    
  </style>
</head>

<body>
<div class="container">

<h1>쪽지보내기</h1>
<form name = "usersend" action='send' method="post" onsubmit="return checkValue()">
<div class="content1">
  <br><br>
  <label for="box1">보낸이</label>
  <br>
  <div class="col-sm-4">
      <input id='f-sender' type='text' name='sender.id' class="form-control" value="${loginUser.nickname}" readOnly>
  </div>
  <br>
</div>
<div class="content2">
  <label for="f-receiver">받는이</label>
  <br>
  <div class="col-sm-4">
      <input id='f-receiver' type='text' name='receiver.id' class="form-control">
      <input type="button" name='findId' value="아이디확인">
      
  </div>
  <!-- <textarea id="f-receiver" name="receiver" rows=1 cols=45></textarea>
  <input type="button" name = 'idCheck' value="존재하는 회원인지 체크">  -->
  <br><br>
</div>
<div class="content3">
  <label for="f-title">제목</label>
  <br>
  <textarea id="f-title"  name="title" rows=1 cols=73 class="form-control">
  </textarea>
  <br><br>
</div>
<div class="content4">
  <label for="f-content">내용</label>
  <br>
  <textarea id="f-content" name="content" rows=10 cols=73 class="form-control">
  </textarea>
</div>
<div class="col-12">
<button type="submit"  class="btn btn-primary btn-sm">보내기</button>
  </div>
</form>
</div><!-- .container -->
</body>
</html>








