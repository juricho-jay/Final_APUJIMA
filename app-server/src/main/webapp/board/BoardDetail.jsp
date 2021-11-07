<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<form name ="boardDetailInfo">
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
      
      <tr>
      <!-- 아이디, 작성날짜 -->
      <td width = "150">
      <div>
        ${comment.commenter}<br>
        <font size = "2" color ="lightgray">${comment.registeredDate}</font>
      </div>
      </td>
      
      <!-- 본문내용 -->
      <td width ="550">
        <div class="text_wrapper">
          ${comment.content}
        </div>
       </td>
       
       <!-- 버튼 -->
       <td width ="100">
        <div id ="btn" style ="text-align:center;">
          <a href ="#">[답변]</a><br>
        <c:if test ="${comment.commenter == loginUser.id} ">   
          <a href ="#">[수정]</a><br>
          <a href= "#">[삭제]]</a>
          </c:if> 
          </div>
  </tbody>
  
  <!-- 로그인 했을 경우만 댓글 작성 가능 -->
  <c:if test= "${loginUser.id !=null}"> </c:if>
  <form id ="writeCommentForm">
    <input type ="hidden" name = "commentBoard" value = "${board.no }">
    <input type ="hidden" name = "commenter" value = "${loginUser.id}">
  
  <!--  로그인한 경우 아이디 자동출력 -->
  <td width = "150">
    <div>
    ${loginUser.id}
    </div>
  </td>
  
  <!-- 본문 작성 -->
  <td width ="550">
    <div>
    <!--  내일 질문 -->
        <textarea name ="content" rows= "4" cols  ="70" ></textarea>
      </div>
    </td>
    
    <!--  댓글 등록 버튼 -->
    <td width = "100">
      <div id ="btn" style ="text-align:center;">
        <p><a href = "#">[댓글등록]</a></p>
        </div>
        </td>
  </form>
  
  
</table>
</form>

<script>
  function checkVaild(){
    if(!document.boardDetailInfo.id.value){
      alert("본인 게시글이 아니어 할 수 없습니다.")
      return false;
    }
    
  }
  
  </script>

<a href= 'updateForm?no=${board.no}' class="btn btn-primary" onclick = "return checkVaild()">수정</a>
<a href= 'delete?no=${board.no}' class="btn btn-primary" onclick = "return checkVaild()" >삭제</a>
<a href= 'report' class= "btn btn-primary">신고</a>

</div><!-- .container -->

</body>
</html>
