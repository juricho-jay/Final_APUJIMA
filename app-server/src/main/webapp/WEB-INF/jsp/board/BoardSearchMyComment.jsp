<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


  <title>내가 쓴 댓글</title>
   <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core//dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
     
  <style>

    .container {
    xborder: 1px solid red;
    xwidth: 640px;
    }
  </style>
  

<div>

<div class="container" >
<h1>나의 댓글</h1>
<br>
<table class="table table-hover">
<thead>
  <tr>
    <th scope ="col" style= "width: 10%" class ="text-center">번호</th>
   <th scope ="col" style= "width: 30%" class ="text-center">내용</th>
   <th scope ="col" style= "width: 10%" class ="text-center">작성자</th>
   <th scope ="col" style= "width: 10%" class ="text-center">작성일</th>
  </tr>
</thead>
<tbody>
<c:forEach items="${commentList}" var="comment">
<tr>
       <td style= "width: 10%" class="text-center">${comment.no}</td>
       <td style= "width: 10%" class="text-center"><a href='../board/detail?no=${comment.commentBoard.no}'>${comment.content}</a></td> 
       <td style= "width: 10%" class="text-center">${loginUser.nickname}</td> 
       <td style= "width: 30%" class="text-center">${comment.registeredDate}</td> 
</tr>
</c:forEach>
</tbody>
</table>

<br>
<br>
<a href='form' class="btn btn-outline-primary btn-sm">글 작성</a><br>

</div><!-- .container -->
</div>









