<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>상담신청</title>
<link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core//dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  
</head>
<body>
<div class="container">
<h1>${loginUser.id}님의 상담신청</h1>
<table class="table table-hover">
<thead>
  <tr>
    <!-- <th>번호</th> -->
    <th>상담신청자</th>
    <th>질병여부</th>
    <th>상담내용</th>
    <th>날짜</th>
  </tr>
</thead>

<tbody>
<c:forEach items="${counselingList}" var="counseling">
<c:if test='${loginUser.id eq counseling.counselor.id}'>
<%-- <c:if test ="${counseling.client.id eq loginUser.id} ">  --%>
		<tr>
		  <%-- <td>${counseling.no}</td> --%>
		  <td style= "width: 10%;"><a href='doctordetail?no=${counseling.no}'>${counseling.client.name}</a></td> 
		  <td style= "width: 10%;">
		    <c:choose>
		       <c:when test="${fn:length(counseling.disease) > 7}">
		          ${fn:substring(counseling.disease, 0, 5)}...
		       </c:when>
		       <c:otherwise>
		          ${counseling.disease}
		       </c:otherwise>
		    </c:choose>
		  </td> 
		  <td style= "width: 10%;">
		    <c:choose>
		         <c:when test="${fn:length(counseling.content) > 7}">
		            ${fn:substring(counseling.content, 0, 5)}...
		         </c:when>
		         <c:otherwise>
		            ${counseling.content}
		         </c:otherwise>
		      </c:choose>
		  </td>
		  <td style= "width: 10%;">${counseling.registeredDate}</td>
		</tr>
</c:if>
<%-- </c:if>   --%>
</c:forEach>
</tbody>
</table>
</div><!-- .container -->
</body>
</html>