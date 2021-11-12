<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>dateCheckPro.jsp</title>
</head>
<body>
  <div style="text-align: center;">
    <h3>*출석체크* </h3>
    <c:choose>
    <c:when test= "${not empty dateCheck}">
    <p style='color:red'> 이미 출석체크를 하셨습니다. <br>내일 다시 출석해주세요.</p>
    </c:when>
     <c:otherwise>
     <p> 출석체크로 30포인트가 추가되었습니다. </p>
    </c:otherwise>
    </c:choose>
  </div> 
  <hr>
  <!-- &nbsp;&nbsp;
  <a href ="javascript:window.close()">[창닫기]</a> -->
  
  <script>
  </script>
</body>
</html>