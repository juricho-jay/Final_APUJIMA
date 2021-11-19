<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!DOCTYPE html>
<html>
<head>
<title>상담신청</title>
<link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core//dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  <script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
  
  <style>
   .container {
    xborder: 1px solid red;
    width: 640px;
    text-align: left;
    }
	 .all{
	  xborder: 1px solid red;
	  width: 640px;
	  margin-top: 100px;
	  }
	  .gender{
	  xborder: 1px solid red;
	  margin-top: 5px;
	  }
	  
	  .total {
	   margin-top: 50px;
	  }
	  
	   .box{
     xborder: 1px solid red;
     width:600px;
     xheight:200px; 
/*   resize:none; */
/*   resize: horizontal; */
      xresize: vertical;
      }
	 
     }
    .box3 {
	    xdisplay:inline-block; 
	    xborder: 1px solid red;
	    width:200px; 
	    height:100px; 
	    margin-left:170px;
	    margin-top: 100px;
     }  
     .box4 {
     xborder: 1px solid red;
     width:200px; 
     height:100px; 
     margin: 0 auto;
     margin-top: 50px;
     }
     .context{
     text-indent: -0.9em;
     }
     .text1{
     font-size:14px;
     }
    
  </style>
  
</head>

<body>




<div class="container">
 <h5>상담신청하기
 <button class="btn btn-outline-secondary btn-sm" onclick="location.href='list'">나의상담리스트</button>
 </h5>
 <br><br>
  



  <div class="mb-3 row">
    <label for='f-client' class="col-sm-3 col-form-label">이름</label>
    <div class="col-sm-6">
      <input id='f-client' type='text' name="client.name"class="form-control" value="${loginUser.name}" readOnly>
    </div>
  </div>
  <div class="mb-3 row">
    <label for='f-tel' class="col-sm-3 col-form-label">휴대번호</label>
    <div class="col-sm-6">
      <input id='f-tel' type='text'  name="client.tel" class="form-control" value="${loginUser.tel}" readOnly>
    </div>
  </div> 
  
  <span class="text1">*상담신청시 이름과 휴대번호는 필수입니다.</span>
  <hr>
  
<form class="total" name="counselingmember" action='add' method="post">
  <div class="mb-3 row">
    <label for='f-name' class="col-sm-3 col-form-label">상담사 선생님</label>
    <div class="col-sm-4">
    
       <input id='f-counselor-no' type='hidden' name="counselorNo" class="form-control" value="${counselor.no}">
       <input id='f-name' type='text'  name="counselor.name" class="form-control" value="${counselor.name}" readOnly>
     </div>
  </div>
  
  <hr style="margin-top:50px;">
  
 
  
  <div class="mb-3 row" style="margin-top:50px;">
    <label for='f-disease' class="col-sm-3 col-form-label">질병여부</label>
    <div class="col-sm-6">
      <input id='f-disease' type='text' name='disease' class="form-control" required>
    </div>
  </div>

  <div class="mb-3">
    <label for="f-content" class="col-sm-3 col-form-label context">상담내용</label>
    <br>
    <textarea class="form-control" id="f-content" name='content' required></textarea>
  </div>
   <span class="text1">*자세하게 기입해주시면 상담시 많은 도움이 됩니다.</span>
  
  
  <div class="col-12 box4">
    <button class="btn btn-outline-secondary btn-sm" onclick="history.back()">뒤로가기</button>
    <button type="submit" class="btn btn-primary btn-sm" >상담 신청하기</button>
  </div>
</form>
</div><!-- .container -->


<script>
$("#men").hide();
$("#inlineRadio1").click(function(){
	$("#women").show();
	$("#men").hide();
});
$("#inlineRadio2").click(function(){
	$("#women").hide();
	$("#men").show();
});




</script>

</body>
</html>


 <%-- <div class="mb-4 row box">
    <label for='f-choice' class="col-sm-2 col-form-label box2">상담사 선택</label>
      <div class="box3">
          <input type="radio" id="inlineRadio1" name="counselor.name" value="1" checked> 여성
          <input type="radio" id="inlineRadio2" name="counselor.name" value="2"> 남성
        
        <div id="women">
          <select class="form-select women">
              <c:forEach items="${counselingList}" var="counseling">
              <option>선택하세요</option>
              <option>${counseling.counselor.name}</option>
              </c:forEach>
          </select>
        </div>
        
        <div id="men">
          <select class="form-select men">
              <option>선택하세요</option>
              <option>김태호 상담사</option>
              <option>김진현 상담사</option>
          </select>
        </div>
      </div>
  </div>  --%>
