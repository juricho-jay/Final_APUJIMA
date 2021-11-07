<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>쪽지보내기</title>
   <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../bootstrap/dist/bootstrap.css"></script>
  
  <script type="text/javascript">
    
        // 필수 입력정보인 아이디 입력되었는지 확인하는 함수
        function checkValue()
        {
        	if(!document.userInfo.receiver.id.value){
                alert("아이디를 입력하세요.");
                return false;
            }
        	// 비밀번호와 비밀번호 확인에 입력된 값이 동일한지 확인
            if(document.userInfo.receiver.id.value != document.userInfo.idcheck.value ){
                alert("아이디를 다시 입력하세요.");
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
<!-- <form name = "userInfo" action="send" method="post" onsubmit="return checkValue()">
보낸이 : <input type="text" name="sender.id" placeholder="abc"><br><p><p>
받는이 : <input type="text" name="receiver.id" placeholder="abc"><br><p><p>
제목 : <input type="text" name="title"><br><p><p>
내용 : <textarea rows="5" cols="30" name="content"></textarea>
<p>
<input type="submit" class="sendbtn" value="쪽지 보내기">
<div class="sendbtn">
<button class="btn btn-primary btn-sm">보내기</button>
</div>
</form> -->


<form name = "mail" action='send' onsubmit="return checkValue()">
<div class="content">
  <br><br>
  <!-- <div class="mb-3 row">
    <label for='f-receiver' class="col-sm-3 col-form-label">받는이</label>
    <div class="col-sm-6">
      <input id='f-receiver' type='text' name='receiver.id' class="form-control">
    </div>
</div> -->
  <label for="box1">보낸이</label>
  <%-- <p>${loginUser.id}님 반갑습니다..</p> --%>
  <br>
  <div class="col-sm-4">
      <input id='f-sender' type='text' name='sender.id' class="form-control">
  </div>
  <br>
  <label for="box2">받는이</label>
  <br>
  <div class="col-sm-4">
      <input id='f-receiver' type='text' name='receiver.id' class="form-control">
  </div>
  <!-- <textarea id="f-receiver" name="receiver" rows=1 cols=45></textarea>
  <input type="button" name = 'idCheck' value="존재하는 회원인지 체크">  -->
  <br><br>
  <label for="box3">제목</label>
  <br>
  <textarea id="f-title"  name="title" rows=1 cols=73 class="form-control">
  </textarea>
  <br><br>
  <label for="box4">내용</label>
  <br>
  <textarea id="f-content" name="content" rows=10 cols=73 class="form-control">
  </textarea>
</div>
<button class="btn btn-primary">보내기</button>
  
</form>
</div><!-- .container -->
</body>
</html>








