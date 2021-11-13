<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>idCheckForm.jsp</title>
</head>
<body>
  <div style="text-align: center;">
    <h3>*아이디 중복확인 결과* </h3>
    
    <p>입력 ID : ${inputId} </p>
  
  
  
    <c:if test= "${not empty member}">
    <p style='color:red'> 이미 등록된 아이디 입니다. 다른 아이디로 회원가입 해주세요.</p>
    
    </c:if>
    
     <c:if test= "${empty member}">
   <p> 회원가입이 가능한 아이디 입니다. </p>
   <input type="button" value="[적용]" onclick= "sendCheckValue()">  
    </c:if>
    
  <hr>
  <a href = "javascript:history.back()">[다시검색]</a>
  &nbsp;&nbsp;
  <a href ="javascript:window.close()">[창닫기]</a>
  
  
  
  <script>

  
  function sendCheckValue() {
	   opener.document.userInfo.id.value = "${inputId}";
	   window.close();

	  
  }
  
  </script>

</body>
</html>