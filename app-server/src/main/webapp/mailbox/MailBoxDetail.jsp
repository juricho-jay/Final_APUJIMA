<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<link rel="stylesheet" href="/apus/css/MailBoxDetail.css">


<div class="container">
<h1>쪽지함 상세보기</h1>
<!-- <button type="submit" class="btn btn-outline-secondary">보내기</button> -->
<!-- <a href='MailBoxForm.jsp' class="btn btn-outline-primary btn-sm">보내기</a><br> -->

<div class="big-box">
  <div class="box2">
      <span class="box-sender">보낸이 : ${mailBox.sender.nickname}</span><br>
      <span class="box-sendtime">보낸시간 : ${mailBox.sentTime}</span><br>
      <span class="box-receivedtime">확인시간 : ${mailBox.receivedTime}</span><br>
  </div>
  <hr>
  <div class="box3">
      <span class="box-title"><b>제목</b> : ${mailBox.title}</span><br>
      <b>내용</b><br>
      <span class="box-content">${mailBox.content}</span>
  </div>
</div>
<button class="btn btn-outline-secondary btn-sm" onclick="location.href='list'">목록</button>
  <a href='delete?no=${mailBox.no}' class="btn btn-outline-secondary btn-sm">삭제</a> 

 <%-- <form action='list'>
	
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
	
	<button class="btn btn-outline-secondary btn-sm">목록</button>
	<a href='delete?no=${mailBox.no}' class="btn btn-outline-secondary btn-sm">삭제</a> 
</form>  --%>
</div><!-- .container -->






