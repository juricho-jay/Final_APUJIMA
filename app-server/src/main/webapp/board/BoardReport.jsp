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
    
    <p> 제  목 : ${board.title} </p>
    <p> 작성자 : ${board.writer.id} </p>
  
    <p> 사유선택 : 여러 사유에 해당되는 경우, 대표적인 사유 1개를 선택해 주세요.</p>
    </div>
    <div style="text-align : left; margin : 20px">
      <input id='member' type='radio' value = "부적절한 홍보 게시글" >
      <label>부적절한 홍보 게시글</label><br>
      <input id='doctor' type='radio' value = "음란성 또는 청소년에게 부적합한 내용" >
      <label>음란성 또는 청소년에게 부적합한 내용</label><br>
      <input id='doctor' type='radio'  value = "명예훼손/사생활 침해 및 저작권침해등" >
      <label>명예훼손/사생활 침해 및 저작권침해등</label><br>
      <input id='doctor' type='radio'  value = "불법촬영물등 신고" >
      <label>불법촬영물등 신고</label><br>
      <input id='doctor' type='radio'  value = "0" onclick="div_OnOff(this.value,'con');">
      <label>기타</label><br>
      </div>
    <div id="con" style = "display:none">
      <input type = 'text' name = 'reason' class='form-control'>
    </div>
  <hr>
    <button class="btn btn-primary">신고하기</button>
  &nbsp;&nbsp;
   <button class="btn btn-primary" onclick = "back()">취소</button>
  
  
  
  <script>
  function back() {
	  window.close();
  }
  
  function div_OnOff(v,id){
	  // 라디오 버튼 value 값 조건 비교
	  if(v == "0"){
	   document.getElementById(id).style.display = ""; // 보여줌
	  }else{
	   document.getElementById(id).style.display = "none"; // 숨김
	  }
	 }
  
  
  </script>

</body>
</html>