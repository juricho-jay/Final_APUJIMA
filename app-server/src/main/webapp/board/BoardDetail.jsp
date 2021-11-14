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
   <script>
  function checkVaild(){
    if (${board.writer.id} != ${loginUser.id}){
      alert("본인 게시글이 아니어 할 수 없습니다.")
      return false;
    }
  }
   
  function boardReport(){
	    
	    window.open("../board/report?no=${board.no}", "report", "width=600 height = 450");
	  }
  
  </script>
<div class="container">
<form name ="boardDetailInfo" action = 'report' onsubmit="return checkValue()">
  <table class = "table table-striped" style ="text-align : center, border 1px solid #dddddd">
    <thead>
      <tr>
         <th colspan ="3" style = "background-color: #eeeeee; text-align: center;">게시판 글 보기</th> 
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
         <input type ="hidden" name = "no" value ="${board.no}">
         </tr>
  
  <tr>
      <td style = "width: 20%"> 글 제목</td>
      <td colspan ="2">${board.title}</td>
      <input type = "hidden" id = 'okok'>
 </tr>
  <tr>
      <td>작성자</td>
      <td>${board.writer.nickname}</td>
      <td><input id = "writer"  type ="hidden" name = "writer" value ="${board.writer.nickname}"></td>
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
   <td>
   <c:if test = "${board.writer.id == loginUser.id}">
   <a href= 'updateForm?no=${board.no}' class="btn btn-primary" onclick = "return checkVaild()">수정</a>
   <a href= 'delete?no=${board.no}' class="btn btn-primary" >삭제</a>
   </c:if>
    <input type="button" value="신고" onclick= "boardReport()" class="btn btn-primary">  
 <!--  <input type="submit" value="신고" class="btn btn-primary" onclick = "boardReport()">  -->
   </td>
   </tr>
    
          
  </tbody>

  </table>
  </form>
  </div><!-- .container -->
  
  <div class = "commentContainer">
    <div class ="commentList">
    <form id = "commentListForm">
 <table class="table table-hover">
      <thead>
      <tr>
       <td>댓글</td>
      </tr>
      <tr>
        <th>작성자</th>
        <th>내용</th>
        <th>작성일자</th>
      </tr> 
       </thead>
    
    <tbody>
      <c:forEach items ="${commentList}" var= "comment">
    <tr>
       <td> ${comment.commenter.nickname}</td>
      <td> ${comment.content}</td>
      <td> ${comment.registeredDate}</td>
    </tr>
     
        <tr>
      <c:if test ="${comment.commenter.id == loginUser.id}">
     
   <td>
   <div align="right">
   <a href= 'CommentUpdateForm?no=${comment.no}' class="btn btn-primary right" onclick = "return checkVaild()">댓글 수정</a>
   <a href= 'comment/delete?no=${comment.no}' class="btn btn-primary right"  >댓글 삭제</a>
      </div>
      </td>
      </c:if>
      </tr>
   </c:forEach>
      <tr>
      <c:choose>
      <c:when test = "${like.liker.no == null && like.likeBoard.no == null}">
        <td style ="width : 80%"><a href ="like/add">좋아요</a></td>
        <td><input type = "button" id = "buttonChange1" value ="❤"  onclick ="colorChange()"></td>
      </c:when>
      
      <c:otherwise>
       <td style ="width : 80%"><a href = "like/delete" >좋아요</a></td>
        <td><input type = "button" id = "buttonChange2" value ="♡"  onclick ="colorChange()"></td>
      </c:otherwise>  
     </c:choose>
      </tr> 

      </tbody>
      </table>
   </form>
</div>   
     <form id = "commentForm" action= "../comment/add">
    <!--   action ='detail?no=${board.no}'> -->
    <table class="table table-hover" >
      <tbody>
      <tr>
      <td><input id="f-board" type ="hidden" name = "no" placeholder = "${board.no}" value = "${board.no}"></td>
      </tr>
      <tr>
         <td style = "width: 20%">작성자</td>
        <td><input id ="f-writer" type='text' name = "writer"  placeholder ="${loginUser.id}"  value = "${loginUser.id}" readonly></td>
      </tr>
      <tr>
      <td style = "width: 20%">내용</td>
       <td>
        <div>
          <textarea  rows = "4" cols="70" name = "content"></textarea>
        <input id = "f-content" style = "margin-left: 50px;" type = "submit" class="btn btn-primary btn-sm"  value = "등록">
        </div>
       </td>
       
     </tr>
        </tbody>
    </table>
     </form>
    

</div> <!-- commentcontainer -->

<script>
 function changeColor(){

	 var a = document.getElementById("buttonChange1")
	 var b = document.getElementById("buttonChange2")
	 
	 a.style.backgroundColor = "red";
	 b.style.backgroundColor = "white";
 }
  
  </script>
</body>
</html>
