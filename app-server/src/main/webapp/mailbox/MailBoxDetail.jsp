<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
  <title>쪽지함</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  
  <style>
    .container {
    xborder: 1px solid red;
    width: 640px;
    height: 500px;
    }
    .listbtn {
    xborder: 1px solid red;
    text-align: center;
    margin-top: 100px;
    }
    .receiver {
    xborder: 1px solid red;
    }
    .time {
    font-size: 12px;
    color: gray;
    }
    .mail {
    xborder: 1px solid red;
    border-bottom: solid 3px rgba(54, 181, 206, 0.67);
    }
    .p {
    font-weight: 600;
    }
    .pp {
    font-weight: 200;
    margin-top: 5px;
    }
  </style>
</head>

<body>
<div class="container">
<h1>쪽지함 상세보기</h1>
<a href='MailBoxForm.jsp' class="btn btn-outline-primary btn-sm">보내기</a><br>


 <form action='list'>

<div class="mb-3 row mail">
<label for='f-sender' class="col-sm-2 col-form-label p">보낸이</label>
<div class="col-sm-6 pp">${mailBox.sender.id}</div>
</div>
<div class="mb-3 row mail">
<label for='f-receiver' class="col-sm-2 col-form-label p">받는이</label>
<div class="col-sm-6 pp">${mailBox.receiver.id}</div>
</div>
<div class="mb-3 row mail">
<label for='f-sentTime' class="col-sm-2 col-form-label p">보낸 날짜</label>
<div class="col-sm-6 pp">${mailBox.sentTime}</div>
</div>
<div class="mb-3 row mail">
<label for='f-sentTime' class="col-sm-2 col-form-label p">확인 날짜</label>
<div class="col-sm-6 pp">${mailBox.receivedTime}</div>
</div>
<div class="mb-3 row mail">
<label for='f-title' class="col-sm-2 col-form-label p">제목</label>
<div class="col-sm-6 pp">${mailBox.title}</div>
</div>
<div class="mb-3 row mail">
<label for='f-title' class="col-sm-2 col-form-label p">내용</label>
<div class="col-sm-6 pp">${mailBox.content}</div>
</div>

<button class="btn btn-primary btn-sm">목록</button>
<a href='delete?no=${mailBox.no}' class="btn btn-outline-primary btn-sm">삭제</a> 
</form> 
</div><!-- .container -->
</body>
</html>








