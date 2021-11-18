<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!DOCTYPE html>
<html>
<head>
  <title>버킷리스트 등록</title>
<!--  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">

  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>-->
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>  
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
  <link rel="stylesheet" href="/apus/css/BucketList.css">
  
  <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    
    
  
</head>
<body>

<div class="row d-flex justify-content-center container">
    <div class="col-md-10">
        <div class="card-hover-shadow-2x mb-3 card">
            <div class="card-header-tab card-header">
                <div class="card-header-title font-size-lg text-capitalize font-weight-normal">
                <i class="bi bi-card-checklist" style="font-size: 2rem; color: cornflowerblue; margin-right: 10%;"></i><b style="font-size: 1.5em">버킷리스트</b></div>
            </div>
            <div class="scroll-area-sm">
                <perfect-scrollbar class="ps-show-limits">
                    <div style="position: static;" class="ps ps--active-y">
                        <div class="ps-content">
                            <ul class="list-group list-group-flush">
                                <c:forEach items="${bucketList}" var="bucket">
                                  <c:if test='${loginUser.id eq bucket.writer.id}'>
                                <li class="list-group-item">
                                    <div class="todo-indicator" id="indicator-bar"></div>
                                    <div class="widget-content p-0">
                                        <div class="widget-content-wrapper">
                                            <div class="widget-content-left mr-2">
                                                <div class="form-check" style="margin-left: 15px; margin-right: -8px; margin-bottom: 11px"> 
                                                <input name="sendNo" class="form-check-input" type="checkbox" 
                                                style="zoom: 1.5; margin-left: " id="send-b-no" value="${bucket.no}"><label class="form-check-label"
                                                 for="send-b-no">&nbsp;</label></div>
                                            </div>
                                            <div class="widget-content-left">
                                                <div class="widget-heading"><b>${bucket.title}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
                                                <!-- <div class="badge badge-danger ml-2">Rejected</div> -->
                                                </div>
                                            </div>
                                            <div class="widget-content-left">
                                            <button type="button" name="updateBtn" style="border: 0; outline=0; background-color: white;" 
                                            value="${bucket.no}" data-title="${bucket.title}" data-content="${bucket.content}"><span>${bucket.content}</span></button>
                                            </div>
                                              <div class="widget-content-right">
                                                <form id="complete" action="update">
                                                <sub>${bucket.registeredDate}&nbsp;&nbsp;</sub> 
                                                    <input type="hidden" name="no" value="${bucket.no}"></input>
                                                    <button type="button" class="border-0 btn-transition btn btn-outline-success2"> 
                                                    <i>${bucket.completedDate}</i></button> 
                                                    <button class="btn-outline-success" id="completeBtn" name="complete" value="${bucket.complete}" type="submit">
                                                    <i class="bi bi-check" style="font-size: 1.5em"></i></button> 
                                                    <button class="border-0 btn-transition btn btn-outline-danger" 
                                                    name="b-trashBtn" onclick="trashDelete(${bucket.no})" type="button">
                                                    <i class="bi bi-trash"></i> </button> 
                                                </form>
                                              </div>
                                        </div>
                                    </div>
                                </li>
                                </c:if>
                              </c:forEach>
                            </ul>
                        </div>
                    </div>
                </perfect-scrollbar>
            </div>
          <div class="d-block card-footer">
            <button class="mr-2 btn btn-link btn-sm" id="checkAll" style="color: deepskyblue; float: left" onclick="selectAll()"><i class="bi bi-check-all" style="zoom: 1.5"></i></button>
            <div class="two">
            <button class="btn btn-primary" id="openModalBtn" style="margin-top: 2.5px; float: right">버킷리스트 추가</button>
            <button class="mr-2 btn btn-link btn-sm" id="deleteBtn" style="float: right; margin-top: 5px;">삭제</button>
            <!-- <button class="mr-2 btn btn-link btn-sm" id="homeBtn" style="float: right; margin-top: 5px;" onclick="moveHome()">취소</button> -->
            </div>
            </div>
            </div>
            </div>
            </div>
            
<!-- 추가 모달 영역 -->        
<form action='add'>
    <div class="modal fade" id="addModal" tabindex="-1" role="dialog" 
      aria-labelledby="exampleModalLabel" aria-hidden="true" data-keyboard="false" data-backdrop="static">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">버킷리스트 추가</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
              <div class="form-group">
                <input type="hidden" id="b-id" name="id" value="${loginUser.id}">
                <label for="bucket-title" class="col-form-label">제목</label>
                <input type="text" class="form-control" id="bucket-title" name="title">
              </div>
              <div class="form-group">
                <label for="bucket-content" class="col-form-label">내용</label>
                <textarea class="form-control" id="bucket-content" name="content"></textarea>
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

<!-- 내용 변경 업데이트 모달 영역 -->        
<form action='contentUpdate'>
    <div class="modal fade" id="updateModal" tabindex="-1" role="dialog" 
      aria-labelledby="exampleModalLabel" aria-hidden="true" data-keyboard="false" data-backdrop="static">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">버킷리스트 변경</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
              <div class="form-group">
                <input type="hidden" id="b-id" name="id" value="${loginUser.id}">
                <label for="bucket-title" class="col-form-label">제목</label>
                <input type="text" class="form-control" id="bucket-title" name="title" placeholder="${content.title}">
              </div>
              <div class="form-group">
                <label for="bucket-content" class="col-form-label">내용</label>
                <textarea class="form-control" id="bucket-content" name="content"></textarea>
              </div>
              
              
          </div>
          <div class="modal-footer">
            <button type="button" id="u-cancelBtn" class="btn btn-secondary" data-dismiss="modal">취소</button>
            <button type="submit" id="bucketUpdateBtn" class="btn btn-primary">확인</button>
          </div>
        </div>
      </div>
    </div>
</form> 

           
<!-- 삭제 모달 영역 -->
<form id="deleteForm" action='delete'>
<div id="deleteModal" class="modal fade" aria-hidden="true" data-keyboard="false" data-backdrop="static">
  <div class="modal-dialog modal-confirm">
    <div class="modal-content">
      <div class="modal-header flex-column">
        <div class="icon-box">
          <i class="bi bi-x-lg"></i>
        </div>            
        <h4 class="modal-title w-100">정말 삭제하시겠습니까?</h4>  
      </div>
      <div class="modal-body">
        <p>삭제된 버킷리스트는 복구할 수 없습니다.</p>
      </div>
      <div class="modal-footer justify-content-center">
        <button type="button" id="d-cancelBtn" class="btn btn-secondary" data-dismiss="modal">취소</button>
        <div>
        <button id="confirmDelete" type="submit" class="btn btn-danger">삭제</button>
        </div>
      </div>
    </div>
  </div>
</div>     
</form>



<script>

// 인디케이터 바 랜덤 색상 부여
window.onload = function() {
  document.querySelectorAll(".todo-indicator").forEach((tag) => {
    tag.style["background-color"] = "#" + Math.floor(Math.random() * 16777215).toString(16);
  });
    
// 달성 버튼 색상 부여
  document.querySelectorAll(".btn-outline-success").forEach((tag) => {
  if (tag.getAttribute("value") == 0) {
    tag.style["color"] = "#3ac47d";
    tag.style["background-color"] = "white";
    
    // hover 설정 - mouseover
    tag.addEventListener('mouseover', function() {
    tag.style["color"] = "white";
    tag.style["background-color"] = "#3ac47d";
    });
    
    // hover 설정 - mouseout
    tag.addEventListener('mouseout', function() {
    tag.style["color"] = "#3ac47d";
    tag.style["background-color"] = "white";
    });
    
  } else {
    tag.style["color"] = "white";
    tag.style["background-color"] = "#3ac47d";
    
    // hover 설정 - mouseover
    tag.addEventListener('mouseover', function() {
    tag.style["color"] = "#3ac47d";
    tag.style["background-color"] = "white";
    });
    
    // hover 설정 - mouseout
    tag.addEventListener('mouseout', function() {
    tag.style["color"] = "white";
    tag.style["background-color"] = "#3ac47d";
    });
    
  }
  }); 
    
    
};

// 돌아가기
function moveHome(){
     location.href = "../loginindex.jsp";
};







// 체크 박스 - 모두 선택 -all true
function selectAll() {
      var obj = document.getElementsByName("sendNo");
      if (obj[0].checked) {
          for (i=0;i<obj.length;i++) {
              obj[i].checked = false;
          }
      } else {
          for (i=0;i<obj.length;i++) {
              obj[i].checked = true;
          }
      } 
};

// 체크박스 중복 체크 > 삭제 눌렀을 때 form으로 넘어갈 데이터들 추가
$('#deleteBtn').click(function() {
  var deleteForm = $('#deleteForm');
  var chk_arr = $("input[name='sendNo']");
  var chk_data = []; 
  
  console.log(chk_arr);

  for( var i=0; i<chk_arr.length; i++ ) { 
    if(chk_arr[i].checked == true ) {
     $('<input>').attr('type','hidden').attr('value', chk_arr[i].value).attr('name','no').appendTo(deleteForm); 
    }
  };
});

// 휴지통 버튼 눌렀을 때 form으로 넘어갈 데이터 하나 추가  //누른 애만 해야 해서 onclick 씀
function trashDelete(no) {
  var deleteForm = $('#deleteForm');
  $('<input>').attr('type','hidden').attr('value', no).attr('name','no').appendTo(deleteForm);
  }; 



  

// 추가 모달 버튼에 이벤트를 건다. 
$('#openModalBtn').on('click', function(){
$('#addModal').modal('show');

});
// 추가 모달 안의 취소 버튼에 이벤트를 건다. 
$('#a-cancelBtn').on('click', function(){
// >>>취소할 때 데이터 초기화하는 식 필요 <<<<
$('#addModal').modal('hide');
});

  
  
// 휴지통 삭제 모달 오픈
// id는 절대값이므로 중복될 수 없다 > name을 활용할 것 > id로 쓸 경우 아이콘 하나만 모달 창 실행
$('button[name=b-trashBtn]').on('click', function(){
$('#deleteModal').modal('show');
});



//내용 업데이트 모달 오픈
$('button[name=updateBtn]').on('click', function(){
$('#updateModal').modal('show');
});


//내용 업데이트 모달 안의 취소 버튼
$('#u-cancelBtn').on('click', function(){
$('#updateModal').modal('hide');
});




// 삭제 모달 버튼에 이벤트를 건다.
$('#deleteBtn').on('click', function(){
$('#deleteModal').modal('show');

});
// 모달 안의 취소 버튼에 이벤트를 건다.
$('#d-cancelBtn').on('click', function(){
$('#deleteForm > input').remove();
$('#deleteModal').modal('hide');
});

</script>

</body>
</html>

 
 



