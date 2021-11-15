<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>게시글 상세</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">

  <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  
  <style>
    
   
    .no-italics {
    font-style: normal;   
  }
  
    .u-d-rBtn {
    float: right;
    }
    
    .3Btn {
    width: 100%;
    }
    
    #likeContainer {
    margin-top: 3%;
    }
    
    /* 모달창 디자인 */
    #modal.modal-overlay {
      width: 100%;
      height: 100%;
      position: fixed;
      left: 0;
      top: 0;
      display: none;
      overflow: hidden;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      background: rgba(255, 255, 255, 0.25);
      box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.37);
      backdrop-filter: blur(1.5px);
      -webkit-backdrop-filter: blur(1.5px);
      border-radius: 3px;
      border: 1px solid rgba(255, 255, 255, 0.18);
    }
    
    #modal .modal-window {
      background: rgba( 167, 201, 210, 0.70 );
      box-shadow: 0 8px 32px 0 rgba( 31, 38, 135, 0.37 );
      backdrop-filter: blur( 13.5px );
      -webkit-backdrop-filter: blur( 13.5px );
      border-radius: 3px;
      border: 1px solid rgba( 255, 255, 255, 0.18 );
      width: 750px;
      position: absolute;
      overflow-y: auto;
      overflow: hidden;
      max-height: 700px;
      top: 100px;
      xpadding: 20px;
    }
    
    #modal .title {
      xborder: 1px solid gray;
      padding-left: 20px;
      padding-top: 20px;
      height: 50px;
      xdisplay: inline;
      xtext-shadow: 1px 1px 2px gray;
      color: black;
      font-size:20px;
      float:left;
    }
    
    /* #modal .title h2 {
      display: inline;
      
      
    } */
    
    #modal .close-area {
      display: inline;
      float: right;
      padding-right: 30px;
      padding-top: 30px;
      cursor: pointer;
      xtext-shadow: 1px 1px 2px gray;
      color: black;
    }
        
     #modal .content {
     
      margin-top: 40px;
      padding: 0px 10px;
      text-shadow: 1px 1px 2px gray;
      color: white;
     }
        
      /* 댓글수정 모달안 디자인 */
      .content-in {
      xborder: 1px solid gray;
      width: 700px;
      padding:0 20 20 20;
      margin: 0 auto;
      margin-top:50px;
    }
    .sendbtn {
      xborder: 1px solid red;
      xwidth: 640px;
      margin-top: 35px;
      text-align: center;
      
    }
    
  </style>
</head>
<body>
   <script>
   // 자기글만 수정/삭제가 떠서 쓸 일 없긴 한데...
  function checkVaild(){
    if (${board.writer.id} != ${loginUser.id}){
      alert("본인 게시글만 변경/삭제할 수 있습니다.")
      return false;
    }
  }
   
  function boardReport(){
	    
	    window.open("../board/report?no=${board.no}", "report", "width=600 height = 450");
	  }
  function cmUpdateOpen(comment_no){
	 window.name = "parentForm"
	 window.open ("CommentUpdateFormController2.co?no" + comment_no,
			 "UpdateForm","width=570,height=350,resizable=no,scrollbars=no");
  }
  
  </script>
<div class="container">
<form name ="boardDetailInfo" action = 'report' onsubmit="return checkValue()">
   <input type ="hidden" name = "no" value ="${board.no}">
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
         </tr>
  
  <tr>
      <td style = "width: 20%"> 글 제목</td>
      <td colspan ="2">${board.title}</td>
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
  </tbody>
  </table>
  </form>
  
  
  </div><!-- .container -->
   <div class="container" id = UDRContainer>
   <div class="u-d-rBtn">
    <form>
   <c:if test = "${board.writer.id == loginUser.id}">
   <input type="hidden" name= "no" value="${board.no}">
  <input type ="submit" value ="수정" class ="btn btn-primary" onclick ="javascript: form.action = 'updateForm';"/>   
   </c:if>
   </form>
   
    <form>
   <c:if test = "${board.writer.id == loginUser.id}">
      <input type="hidden" name= "no" value="${board.no}">
  <input type ="submit" value ="삭제" class ="btn btn-primary" onclick ="javascript: form.action = 'delete';"/>   
   </c:if>
   </form>
   
      <input type="hidden" name= "no" value="${board.no}">
      <input type="button" value="신고" onclick= "boardReport()"class="btn btn-primary">
     <a href= "list"> <input type="button" value="목록" class="btn btn-primary"></a>
      
   </div> 
   </div> 
  
  <!-- likeContainer -->
  <div class="container" id="likeContainer">
      <form id="likeAddCancel" action="../like/update">
      <input type="hidden" name="no" value="${board.no}">
      <button id="heartBtn" style="border: 0; background-color: white; outline:0;" type="submit" value="${likeOrNot}">
      <i class="bi bi-heart" id="heartIcon"style="color: red" data-like="${likeOrNot}"></i>
      &nbsp;좋아요&nbsp;${likeNo}&nbsp;&nbsp;</button><i class="bi bi-chat-square-dots"></i>&nbsp;댓글&nbsp;${commentNo}</form>
      <hr size="5px" color="black"> <!-- hr 템플릿 설정되어있음 black > opacity -->
  </div>
  <!-- .likeContainer -->
  
  

  
  
  
  
  
  <!-- commentContainer ver2 -->
 <div class="col-lg-12"> 
 <form action= "../comment/add">
  <div class="card">
   <div class="card-header with-border"> 
    <h3 class="card-title">댓글 작성</h3> 
    </div>
     <div class="card-body">
      <div class="row"> 
      <div class="form-group col-sm-8"> 
      <input class="form-control input-sm" id="newReplyText" name = "content" type="text" placeholder="댓글 입력...">
       </div> <div class="form-group col-sm-2">
        <input class="form-control input-sm" id="newReplyWriter" type="text" name ="commenter" value="${loginUser.nickname}" readonly> 
        <input id = "f-content" style = "margin-left: 320px;" type = "submit" class="btn btn-primary btn-sm"  value = "등록">
         <input id="f-board" type ="hidden" name = "no" placeholder = "${board.no}" value = "${board.no}">
         </div> 
    </div>
   
    
    
     <div class="card-footer"> 
     <ul id="replies">
         <c:forEach items ="${commentList}" var= "comment">
   <p> 작성자: ${comment.commenter.nickname}</p>
   <p> 내용 : ${comment.content} </p>
   <p> 댓글 작성날짜 : ${comment.registeredDate}</p>
   
   <div id = "UDbutton" style ="margin-left : 960px"> 
   <button type='button' class='btn btn-xs btn-success' data-toggle="modal" data-target='#modifyModal' data-commentNo = "${comment.no}">댓글 수정</button>
  <!--  data toggle = id  -->
   <a href= 'comment/delete?no=${comment.no}' class="btn btn-primary right"  >댓글 삭제</a>
  </div>
  
<hr>
     
     
     </c:forEach>
     
     </ul> 
     </div>
     
    
    </div> 
    </div>
    </form>
     </div>
    

  
  
  <div class="modal fade" id="modifyModal" role="dialog"> 
  <form action = "../comment/update">
  <c:forEach items ="${commentList}" var ="comment">
  
  
  <div class="modal-dialog" id ="${comment.no}"> 
    <div class="modal-content"> 
      <div class="modal-header"> 
          <h4 class="modal-title">댓글 수정창</h4> 
            <button type="button" class="close" data-dismiss="modal">&times;</button> 
     </div> 
     <div class="modal-body"> 

       <div class="form-group"> 
        <label for="reply_writer">댓글 작성자</label> 
          <input class="form-control" id="commenter" name="commenter" value="${comment.commenter.nickname}" readonly>
      </div>
      <div class="form-group"> 
      <label for="reply_text">댓글 내용</label> 
      <input class="form-control" id="reply_text" name="content" value="${comment.content}" placeholder="${comment.content }"> 
     </div>
     
     </div>
     
      <div class="modal-footer"> 
       <input type ="hidden" name ="content" value ="${comment.content}">
      <input type ="hidden"  id ="commentNo" name = "no" value ="${comment.no}">
      <button type="submit" class="btn btn-success modalModBtn">수정</button> 
     
      <button type="button" class="btn btn-default pull-left" data-dismiss="modal">닫기</button> 
      
      </div>
     </div>
    </div>
    </c:forEach>
   </form>
  </div>

  
  
  <!-- test -->
  <!-- 
 <div class="container2">
   <div id="modal" class="modal-overlay">
     <div class="modal-window">
        <div class="box">
          <div class="title">
              댓글 수정
          </div>
          <div class="close-area">X</div>
        </div>
        <div class="content-in">
            <form name = "updateComment" action='/apus/comment/update2' >
             <input type ="hidden" value ="${comment.no}" name = "no">
             <div class="content4">
                <label for="f-content">내용</label>
                <br>
                <textarea id="f-content" name="content" rows=5 cols=73 class="form-control"></textarea>
               <br><hr>
              </div>
              <div class="col-12 sendbtn">
              <button type="submit" class="btn btn-outline-secondary">수정하기</button>
                </div>
              </form>
            </div>
        </div>
    </div>
    </div>
    <button id="btn-modal" class="btn btn-outline-primary btn-sm">수정하기</button>

  
   -->


<!--  댓글 수정 모달  -->
<!--  
<div class="container2">
  <!-- <a href='MailBoxForm.jsp' class="btn btn-outline-primary btn-sm">보내기</a><br> 
<div id="modal" class="modal-overlay">
    <div class="modal-window">
        <div class="box">
          <div class="title">
              댓글 수정
          </div>
          <div class="close-area">X</div>
        </div>
        <div class="content-in">
            <form name = "updateComment" action='/apus/comment/update2' >
             <input type ="hidden" value ="${comment.no}" name = "no">
             <div class="content4">
                <label for="f-content">내용</label>
                <br>
                <textarea id="f-content" name="content" rows=5 cols=73 class="form-control"></textarea>
               <br><hr>
              </div>
              <div class="col-12 sendbtn">
              <button type="submit" class="btn btn-outline-secondary">수정하기</button>
                </div>
              </form>
            </div>
        </div>
    </div>
    </div>
    <button id="btn-modal" class="btn btn-outline-primary btn-sm">수정하기</button>
-->

   
     
    



<script>

// 좋아요 여부에 따라 하트 
/*
function modalOn(){
	/*  	console.log(event.target);
	  console.log(event.target.dataset.commentno);
	  console.log();
	  
	  document.getElementById(event.target.dataset.commentno).style.display = "block;
	  
}
*/


document.querySelectorAll("#heartBtn").forEach((tag) => {
if (tag.getAttribute("value") == 1) {
  $("#heartIcon").attr('class', 'bi bi-heart-fill');
  
} else {
	$("#heartIcon").attr('class', 'bi bi-heart');

	  }
});


// 좋아요 버튼 ajax

/* 
 $("#heartBtn").click(function(){
    $.ajax({
        type:"POST",   >> or get
        url:"../like/update",
        data : {
        	no: ${board.no}
        	},
       
        success: function(data){
            $(#heartBtn).attr("value", '1');
            좋아요 여부에 따른 하트 > function으로 만들어서 실행
        }
    })
});  */



  
  function boardReport(){
	  
	  window.open("BoardReport.jsp", "report", "width=600 height = 450")
	  
	}
  
  </script>
</body>
</html>
