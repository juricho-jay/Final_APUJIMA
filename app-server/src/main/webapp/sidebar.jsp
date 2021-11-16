<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<style>


 .ul {
  position: fixed;
  min-width: 50px;
  min-height: 100vh;
  background-color: yellow;



</style>
<c:if test='${not empty sessionScope.loginUser}'>
    <%-- <c:if test="${empty sessionScope.loginUser.photo}">
    <img src="https://via.placeholder.com/100">
    </c:if>
    <c:if test="${not empty sessionScope.loginUser.photo}">
    <img src="${contextPath}/img${sessionScope.loginUser.photo}_100x100,jpg">
    </c:if>
    ${sessionScope.loginUser.photo}<br> --%>
    ${sessionScope.loginUser.nickname}님<br>
    환영합니다.
</c:if>



