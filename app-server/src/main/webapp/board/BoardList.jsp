<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <title>게시판 목록</title>
   <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core//dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
     
  <style>

    .container {
    xborder: 1px solid red;
    xwidth: 640px;
    }
  </style>
  
</head>
<body>
<div>

<div class="container" style = "margin-left : 120px">
<h1>게시판 목록</h1>
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
    <td style= "width: 10%" class="text-center">${board.no}</td>
     <c:if test='${board.whichBoard == 1}'>
    <td style= "width: 10%" class="text-center">자유게시판</td> 
    </c:if>
    <c:if test='${board.whichBoard == 2}'>
     <td style= "width: 10%" class="text-center">Healer지식in</td> 
    </c:if>
    <c:if test='${board.whichBoard == 3}'>
     <td style= "width: 10%" class="text-center">공지사항</td> 
    </c:if>
     <td style= "width: 10%" class="text-center"><a href='detail?no=${board.no}'>${board.title}</a></td> 
     <td style= "width: 10%" class="text-center">${board.content}</td> 
     <td style= "width: 10%" class="text-center">${board.writer.nickname}</td> 
     <td style= "width: 30%" class="text-center">${board.registeredDate}</td> 
     <td style= "width: 10%" class="text-center">${board.viewCount}</td> 
   
</tr>
</c:forEach>
</tbody>
</table>
<a href='form' class="btn btn-outline-primary btn-sm">글 작성</a><br>

</div><!-- .container -->
</div>
</body>
</html>








