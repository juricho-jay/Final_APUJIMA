<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardReport.jsp</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">

  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../bootstrap/dist/bootstrap.css"></script>
</head>
<body>
  <div style="text-align: left;">
    <h3>신고하기</h3>
    <form action = "reportAdd">
    
    <input type = 'hidden' name = "no" value = "${report.requestBoard.no }">
    <p> 제  목 : ${report.requestBoard.title} </p>
    <input type = 'hidden' name = "title" value = "${report.requestBoard.title}">
    <p> 작성자 : ${report.requester.id} </p>
    <input type = 'hidden' name = "id" value = "${report.requester.id}">
  
    <p> 사유선택 : 여러 사유에 해당되는 경우, 대표적인 사유 1개를 선택해 주세요.</p>

    <div style="text-align : left; margin : 20px">
      <input id='member' type='radio' name='reason' value = "부적절한 홍보 게시글" >
      <label>부적절한 홍보 게시글</label><br>
      <input id='doctor' type='radio' name='reason'value = "음란성 또는 청소년에게 부적합한 내용" >
      <label>음란성 또는 청소년에게 부적합한 내용</label><br>
      <input id='doctor' type='radio'  name='reason'value = "명예훼손/사생활 침해 및 저작권침해등" >
      <label>명예훼손/사생활 침해 및 저작권침해등</label><br>
      <input id='doctor' type='radio'  name='reason'value = "불법촬영물등 신고" >
      <label>불법촬영물등 신고</label><br>
      <input id='doctor' type='radio'  name='reason'value = "0" onclick="div_OnOff(this.value,'con');">
      <label>기타</label><br>
      </div>
    <div id="con" style = "display:none">
      <input type = 'text' name = 'reason2' class='form-control'>
    </div>
  <hr>
   <!--   <input type ="submit" class="btn btn-primary" value = '신고하기' onclick="back2()">-->
    <input type ="submit" class="btn btn-primary" value = '신고하기' >
  &nbsp;&nbsp;
   <button class="btn btn-primary" onclick = "back()">취소</button>
  </form>
      </div>
  
  <script>
  function back() {
	  window.close();
  }
  
  function back2() {
      alert("신고가 완료되었습니다!");
	    self.close();
	  }
  
  function div_OnOff(v,id){
	  // 라디오 버튼 value 값 조건 비교
	  if(v == "0"){
	   document.getElementById(id).style.display = ""; // 보여줌
	  }else{
	   document.getElementById(id).style.display = "none"; // 숨김
	  }
	 }
  
  function getParentText() { 
	  document.getElementById("okok").value = window.opener.document.getElementById("okok").value; }

  
  </script>

</body>
</html>