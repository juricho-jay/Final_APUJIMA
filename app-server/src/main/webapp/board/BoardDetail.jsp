<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>게시글 상세</title>
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
  <table class = "table table-striped" style ="text-align : center, border 1px solid #dddddd">
    <thead>
      <tr>
         <th colspan ="3" style = "background-color: #eeeeee; text-align: center;">게시판 글 보기</th> 
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
      <td style = "width: 20%"> 글 제목</td>
      <td colspan ="2">${board.title}</td>
 </tr>
  <tr>
      <td>작성자</td>
      <td>${board.writer.id}</td>
  </tr>
  <tr>
  
      <td>작성일자</td>
      <td>${board.registeredDate}</td>
      
  </tr>
  
   <tr>
  
      <td>조회수</td>
      <td>${board.viewCount}</td>
  </tr>
  
   <tr>
      <td>내용</td>
      <td colspan="2" style ="min-height: 200px; text-align: left;" >${board.content}</td>
   </tr>
      
  </tbody>
 
</table>




<a href= 'updateForm?no=${board.no}' class="btn btn-primary">수정</a>
<a href= 'delete?no=${board.no}' class="btn btn-primary">삭제</a>
<a href= 'report' class= "btn btn-primary">신고</a>

</div><!-- .container -->

</body>
</html>
