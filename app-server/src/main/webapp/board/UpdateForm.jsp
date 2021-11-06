<%@page import="apus.domain.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    <%@ page import="apus.domain.Board" %>
    
<!DOCTYPE html>
<html>
<head>
  <title>게시글 수정</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  
  <style>

 
    .container {
        xborder: 1px solid red;
        xwidth: 640px;
    }
  </style>
</head>
<body>
<div class="container">
<form action = "update">
  <table class = "table table-striped" style ="text-align : center, border 1px solid #dddddd">
    <thead>
      <tr>
         <th colspan ="3" style = "background-color: #eeeeee; text-align: center;">게시판 글 수정</th> 
      </tr>
    </thead>
    <tbody>
          
        <tr>
            <td>게시판 분류</td>
            <td>${board.whichBoard}</td>
        </tr>    
         
         <tr>
           <td>글 번호</td>
           <td>${board.no}</td>
         </tr>
         
     <tr>
      <td>작성자</td>
      <td>${board.writer.id}</td>
     </tr>     
  
  <tr>
    <td>글 제목</td>
    <td><input  id = 'f-title' type ='text' class = "form-control" placeholder ="글 제목" name='title' maxlength="50" value ='${board.title }' ></td>
 </tr>
  
   <tr>
      <td>내용</td>
      <td><textarea id = 'f-content' class = "form-control" placeholder="글 내용" name='content' maxlength ="2048" style="height: 350px;" ></textarea></td>
     </tr>
        
  </tbody>
 
</table>
  
<a href = 'update?no=${board.no}' ><input class ="btn btn-primary pull-right" value="변경"></a>
<!--  <input type ="submit" class ="btn btn-primary pull-right" value="변경"> -->
<a href ='list'>목록</a>
</form>


</div><!-- .container -->

</body>
</html>
