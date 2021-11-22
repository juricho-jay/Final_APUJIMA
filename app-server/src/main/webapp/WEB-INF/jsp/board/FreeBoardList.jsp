<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

  <title>자유게시판 목록</title>
   <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core//dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
     
  <style>

    .container2 {
    xborder: 1px solid red;
    width: 800px;
    }
  </style>
  
<div class="container2">
<h1>자유게시판 목록</h1>
<a href='form' class="btn btn-outline-primary btn-sm">글 작성</a><br>
<table class="table table-hover">
<thead>
  <tr>
    <th scope ="col" class ="text-center">번호</th>
    <th scope ="col" class ="text-center">게시판 종류</th>
    <th scope ="col" class ="text-center">제목</th>
   <th scope ="col" class ="text-center">내용</th>
   <th scope ="col" class ="text-center">작성자</th>
   <th scope ="col" class ="text-center">작성일</th>
   <th scope ="col" class ="text-center">조회</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${boardList}" var="board">
<tr>
    <td style= "width: 5%" class="text-center">${board.no}</td>
     <td style= "width: 10%" class="text-center">자유게시판</td> 
     <td style= "width: 10%" class="text-center"><a href='detail?no=${board.no}'>
		     <c:choose>
		          <c:when test="${fn:length(board.title) > 7}">
		             ${fn:substring(board.title, 0, 5)}...
		          </c:when>
		          <c:otherwise>
		             ${board.title}
		          </c:otherwise>
		     </c:choose>
		     </a></td> 
		     <td style= "width: 10%" class="text-center">
		     <c:choose>
		          <c:when test="${fn:length(board.content) > 19}">
		             ${fn:substring(board.content, 0, 8)}...
		          </c:when>
		          <c:otherwise>
		             ${board.content}
		          </c:otherwise>
		     </c:choose>
     </td> 
     <td style= "width: 6%" class="text-center">${board.writer.nickname}</td> 
     <td style= "width: 10%" class="text-center">${board.registeredDate}</td> 
     <td style= "width: 3%" class="text-center">${board.viewCount}</td> 
   
</tr>
</c:forEach>
</tbody>
</table>


</div><!-- .container -->







