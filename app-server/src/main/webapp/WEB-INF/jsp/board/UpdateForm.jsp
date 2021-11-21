<%@page import="apus.domain.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    <%@ page import="apus.domain.Board" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
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
<form action = "update" method = 'post'>
  <table id="datatable-scroller"
  class="table table-bordered tbl_Form">
    <thead>
      <tr>
         <th colspan ="3" style = "background-color: #eeeeee; text-align: center;">게시판 글 수정</th> 
      </tr>
    </thead>
    <tbody>
          
        <tr>
            <td>게시판 분류</td>
            <c:if test='${board.whichBoard == 1}'>
            <td>자유게시판</td> 
             </c:if>
              <c:if test='${board.whichBoard == 2}'>
               <td>Healer지식in</td> 
               </c:if>
                <c:if test='${board.whichBoard == 3}'>
                 <td>공지사항</td> 
             </c:if>
        </tr>    
         
         <tr>
           <td>글 번호</td>
           <td>${board.no}</td>
       <td><input id = 'f-no' type ='hidden' class = "form-control"  name='no' maxlength="50" value ='${board.no}' ></td>

         </tr>
         
     <tr>
      <td>작성자</td>
      <td>${board.writer.id}</td>
     </tr>     
  
  <tr>
    <td>글 제목</td>
    <td><input  id = 'f-title' type ='text' class = "form-control" value ="${board.title}" name='title' maxlength="50"  ></td>
 </tr>
    
  
   <tr>
      <td>내용</td>
      <td><input type = 'text' id = 'f-content' class = "form-control" value= "${board.content} " name='content' maxlength ="2048" style="height: 350px;" ></td>
     </tr>
        
  </tbody>
</table>
  
 <input type ="submit" class ="btn btn-primary pull-right" value="변경"> 
<!--  <input type ="submit" class ="btn btn-primary pull-right" value="변경"> -->
</form>


</div><!-- .container -->

</body>
</html>
