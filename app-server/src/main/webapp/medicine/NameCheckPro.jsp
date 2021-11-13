<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NameCheckForm.jsp</title>
</head>
<body>
  <div style="text-align: center;">
    <h3>*약품이름 중복확인 결과* </h3>
    
    <p>입력 ID : ${inputName} </p>
    
    <c:if test= "${not empty medicine}">
    <p style='color:red'> 이미 등록된 약품 이름 입니다. 약품 리스트를 확인해주세요.</p>
    
    </c:if>
    
     <c:if test= "${empty medicine}">
   <p> 등록이 가능한 의약품 이름 입니다. </p>
    </c:if>
    
  <hr>
  <a href = "javascript:history.back()">[돌아가기]</a>
  
  <!--  
  <script>
  function sendCheckValue() {
	   opener.document.medicineAdd.name.value = "${inputName}";
	   window.close();
  }
  </script>
-->
</body>
</html>