<%@page import="apus.domain.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>화분 이름 수정</title>
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
<form action = "/apus/comment/update">
  <table class = "table table-striped" style ="text-align : center, border 1px solid #dddddd">
    <thead>
      <tr>
         <th colspan ="3" style = "background-color: #eeeeee; text-align: center;">게시판 글 수정</th> 
      </tr>
    </thead>
    <tbody>
         <tr>
           <td>댓글 번호</td>
           <td>${comment.no}</td>
       <td><input id = 'f-no' type ='hidden' class = "form-control"  name='no' maxlength="50" value ='${comment.no}' ></td>
         </tr>
         
         <tr>
           <td>댓글 내용</td>
        <td><input type ="text" id ="f-content" class ="form-control" name = "content" value ="${comment.content}"> 
         <tr>
  
  
      <tr>
     <td> <input type ="submit" class ="btn btn-primary pull-right" value="변경">
    <a href ='list' class ="btn btn-primary pull-right">목록</a></td>
      </tr>
  </tbody>
</table>
  
 
<!--  <input type ="submit" class ="btn btn-primary pull-right" value="변경"> -->
</form>
</div>

</body>
</html>