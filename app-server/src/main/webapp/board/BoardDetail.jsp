<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko" >
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>board</title>
<head>
  <title>게시글 상세</title>

  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">

   <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  
  <style>
    
    .UDRContainer{
    width: 90%;
    }
    
    .Ubutton {
    float: right;
}

.Dbutton{
    float: right;
}


.RLbutton{
    float: right;
}
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
    
    
    
  </style>
</head>
<body>
   <script>
   
  function boardReport(){
	    
	    window.open("../board/report?no=${board.no}", "report", "width=600 height = 450");
	  }
  function cmUpdateOpen(comment_no){
	 window.name = "parentForm"
	 window.open ("CommentUpdateFormController2.co?no" + comment_no,
			 "UpdateForm","width=570,height=350,resizable=no,scrollbars=no");
  }
  
  </script>

  <!-- detail new  -->
  
  <div class ="container1212">
<form name ="boardDetailInfo" action = 'report' onsubmit="return checkValue()">
   <input type ="hidden" name = "no" value ="${board.no}">
   <input id = "writer"  type ="hidden" name = "writer" value ="${board.writer.nickname}">
    <h2>${board.title}</h2>
    <table id="datatable-scroller"
  class="table table-bordered tbl_Form">
  <colgroup>
    <col width="250px" />
    <col />
  </colgroup>
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
    
    </tr>
    <tr>
      <th class="active">작성자</th>
      <td>${board.writer.nickname}</td>
      
    </tr>
    
       <tr>
      <td>조회수</td>
      <td>${board.viewCount}</td>
  </tr>
    <tr>
      <th class="active" >내용</th>
      <td width = 800px height = 500px;>
        ${board.content }
      </td>
    </tr>
  </tbody>
  </table>
     </form>
     <div class="container" id = UDRContainer >
      <div class = "Ubutton">
    <form>
   <c:if test = "${board.writer.id == loginUser.id}">
   <input type="hidden" name= "no" value="${board.no}">
  <input type ="submit" value ="수정" class ="btn btn-primary" onclick ="javascript: form.action = 'updateForm';"/>   
   </c:if>
   </form>
   </div>
   
   <div class = "Dbutton">
    <form>
   <c:if test = "${board.writer.id == loginUser.id}">
      <input type="hidden" name= "no" value="${board.no}">
  <input type ="submit" value ="삭제" class ="btn btn-primary" onclick ="javascript: form.action = 'delete';"/>   
   </c:if>
   </form>
   </div>
   
    <div class = "RLbutton">
      <input type="hidden" name= "no" value="${board.no}">
      <input type="button" value="신고" onclick= "boardReport()"class="btn btn-primary">
     <a href= "list"> <input type="button" value="목록" class="btn btn-primary"></a>
     </div>
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
   
   
	   <p style="float:left;"> 작성자: ${comment.commenter.nickname}&nbsp;</p>
	   <div class="dropdown" >
		  <button class="btn btn-secondary dropdown-toggle btn-sm" type="button" id="dropdownMenu2" data-bs-toggle="dropdown" aria-expanded="false" style="dropdown-border-color: rgba($black, .15);">
		    더보기
		  </button>
		  <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
		    <li><a class="dropdown-item" href="#">쪽지보내기</a></li>
		    <li><button class="dropdown-item" type="button">쪽지보내기</button></li>
		    <!-- <li><button class="dropdown-item" type="button">Another action</button></li>
		    <li><button class="dropdown-item" type="button">Something else here</button></li> -->
		  </ul>
		</div>
<br>
   
   <p> 내용 : ${comment.content} </p>
   <p> 댓글 작성날짜 : ${comment.registeredDate}</p>
   
   <div id = "UDbutton" style ="margin-left : 960px"> 
   <c:if test = "${loginUser.id == comment.commenter.id }">
   <button class="btn btn-xs btn-success" name="updateButton" onclick="updateComment('${comment.no}', '${comment.content}')" type="button">댓글 수정</button>
      
      <!--  data toggle = id  -->
   <a href= 'comment/delete?no=${comment.no}' class="btn btn-primary right"  >댓글 삭제</a>
   </c:if>
  </div>
<hr>
     </c:forEach>
     
     </ul> 
     </div>
     
    
    </div> 
    </div>
    </form>
     </div>
    

  <!--  댓글 수정 모달. -->
 <form  id = "updateForm" action='/apus/comment/update'>
 <div class="modal fade" id="updateModal" tabindex="-1" role="dialog" 
      aria-labelledby="exampleModalLabel" aria-hidden="true" data-keyboard="false" data-backdrop="static">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">댓글 수정</h5>
          </div>
          <div class="modal-body">
              <div class="form-group">
                <label for="bucket-title" class="col-form-label">작성자</label>
                <input type="text" class="form-control" id="commenter" name="commenter" value ="${loginUser.nickname}"readonly>
              </div>
              <div class="form-group">
                <label for="comment-content" class="col-form-label">내용</label>
         <textarea class="form-control" id="Commentcontent" name="content" >${comment.content}</textarea>
              </div>
              
              
          </div>
          <div class="modal-footer">
            <button type="button" id="a-cancelBtn" class="btn btn-secondary" data-dismiss="modal">취소</button>
            <button type="submit" id="bucketAddBtn" class="btn btn-primary">확인</button>
          </div>
        </div>
      </div>
    </div>
   </form>

  
    


<script>
document.querySelectorAll("#heartBtn").forEach((tag) => {
if (tag.getAttribute("value") == 1) {
  $("#heartIcon").attr('class', 'bi bi-heart-fill');
  
} else {
	$("#heartIcon").attr('class', 'bi bi-heart');

	  }
});
var m
console.log()
/*
 
 var modifyModal = document.querySelector("#modifyModal");
console.log(modifyModal)
modifyModal.addEventListener("show.bs.modal", () => {
	console.log("===============>222")
});
*/
//휴지통 삭제 모달 오픈
//id는 절대값이므로 중복될 수 없다 > name을 활용할 것 > id로 쓸 경우 아이콘 하나만 모달 창 실행
$('button[name=updateButton]').on('click', function(){
$('#updateModal').modal('show');


});

/*
$('#updateButton').on('click', function(){
	$('#updateModal').modal('show');

	});
*/
	// 모달 안의 취소 버튼에 이벤트를 건다.
	  $('#a-cancelBtn').on('click', function(){
	  $('#updateForm > input').remove();
	  $('#updateModal').modal('hide');
	  });



//데이터 모달에 넘기기 
function updateComment(no,content) {
	  var updateForm= $('#updateForm');
	  $('<input>').attr('type','hidden').attr('value', no).attr('name','no').appendTo(updateForm);
	  $('<input>').attr('type','hidden').attr('value', content).attr('name','content').appendTo(updateForm);

	 //   $('<input>').attr('type','text').attr('value', content).attr('name','content').attr('placeholder','content').appendTo(.child1);

	 console.log(no,content)
	  }; 

  </script>
</body>
</html>
