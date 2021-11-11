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
  
  <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    
    
  <style>
body {
    margin: 0;
    font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, "Noto Sans", sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
    font-size: 0.88rem;
    font-weight: 400;
    line-height: 1.5;
    color: #495057;
    text-align: left;
    background-color: skyblue;
    display: flex;
    align-items: center; /* 수직 정렬 */
    flex-direction: row; /* default: row */
    justify-content: center; /* flex direction에 대해서 정렬방식 선택 */
}

i {
    font-style: italic
}

.container {
    margin-top: 100px
}

.card {
    box-shadow: 0 0.46875rem 2.1875rem rgba(4, 9, 20, 0.03), 0 0.9375rem 1.40625rem rgba(4, 9, 20, 0.03), 0 0.25rem 0.53125rem rgba(4, 9, 20, 0.05), 0 0.125rem 0.1875rem rgba(4, 9, 20, 0.03);
    border-width: 0;
    transition: all .2s
}

.card-header:first-child {
    border-radius: calc(0.25rem - 1px) calc(0.25rem - 1px) 0 0
}

.card-header {
    display: flex; 
    align-items: center;
    border-bottom-width: 1px;
    padding-top: 0;
    padding-bottom: 0;
    padding-right: 0.625rem;
    height: 5.0rem;
    background-color: #fff
}

.widget-subheading {
    color: #858a8e;
    font-size: 10px
}

.card-header.card-header-tab .card-header-title {
    display: flex;
    align-items: center;
    white-space: nowrap
}

.card-header .header-icon {
    font-size: 1.65rem;
    margin-right: 0.625rem
}

.card-header.card-header-tab .card-header-title {
    display: flex;
    align-items: center;
    white-space: nowrap
}

.btn-actions-pane-right {
    margin-left: auto;
    white-space: nowrap
}

.text-capitalize {
    text-transform: capitalize !important
}

.scroll-area-sm {
    height: 400px;
    overflow-x: hidden
}

.list-group-item {
    position: relative;
    display: block;
    padding: 0.75rem 1.25rem;
    margin-bottom: -1px;
    background-color: #fff;
    border: 1px solid rgba(0, 0, 0, 0.125)
}

.list-group {
    display: flex;
    flex-direction: column;
    padding-left: 0;
    margin-bottom: 0
}

.todo-indicator {
    position: absolute;
    width: 6px;
    height: 60%;
    border-radius: 0.3rem;
    left: 0.625rem;
    top: 20%;
    opacity: .6;
    transition: opacity .2s;
    border: 0.5px solid lightgrey;
}

.bg-warning {
    background-color: #f7b924 !important
}

.widget-content {
    padding: 1rem;
    flex-direction: row;
    align-items: center
}

.widget-content .widget-content-wrapper {
    display: flex;
    flex: 1;
    position: relative;
    align-items: center
}

.widget-content .widget-content-right.widget-content-actions {
    visibility: hidden;
    opacity: 0;
    transition: opacity .2s
}

.widget-content .widget-content-right {
    margin-left: auto
}

.btn:not(:disabled):not(.disabled) {
    cursor: pointer
}

.btn {
    position: relative;
    transition: color 0.15s, background-color 0.15s, border-color 0.15s, box-shadow 0.15s
}

 .btn-outline-success {
    color: #3ac47d;
    user-select: none;
    background-color: transparent;
    border: 1px solid transparent;
    padding: 0.375rem 0.75rem;
    font-size: 1rem;
    line-height: 1.5;
    border-radius: 0.25rem;
} 
 
 .btn-outline-success:hover {
    color: #fff;
    background-color: #3ac47d;
    border-color: black;
    user-select: none;
    background-color: #3ac47d;
    border: 1px solid transparent;
    padding: 0.375rem 0.75rem;
    font-size: 1rem;
    line-height: 1.5;
    border-radius: 0.25rem;
}  





.btn-outline-success2 {
    color: #82B3ED;
    border-color: #82B3ED;
}

.btn-outline-success2:hover {
    color: #fff;
    background-color: #82B3ED;
    border-color: #82B3ED
}

.btn-outline-success2:hover {
    color: #fff;
    background-color: #82B3ED;
    border-color: #82B3ED
}

.btn-primary {
    color: #fff;
    background-color: #3f6ad8;
    border-color: #3f6ad8;
    margin-left: 2%;
}

.btn {
    position: relative;
    transition: color 0.15s, background-color 0.15s, border-color 0.15s, box-shadow 0.15s;
    outline: none !important
}

.card-footer {
    background-color: #fff
}

input {
  border:none;
  background-color: white;
  /*border-radius:4px 가능 */
}

/* 삭제 모달 스타일 */
.modal-confirm {    
  color: #636363;
  width: 400px;
}
.modal-confirm .modal-content {
  padding: 20px;
  border-radius: 5px;
  border: none;
  text-align: center;
  font-size: 14px;
}
.modal-confirm .modal-header {
  border-bottom: none;   
  position: relative;
}
.modal-confirm h4 {
  text-align: center;
  font-size: 26px;
  margin: 30px 0 -10px;
}
.modal-confirm .close {
  position: absolute;
  top: -5px;
  right: -2px;
}
.modal-confirm .modal-body {
  color: #999;
}
.modal-confirm .modal-footer {
  border: none;
  text-align: center;   
  border-radius: 5px;
  font-size: 13px;
  padding: 10px 15px 25px;
}
.modal-confirm .modal-footer a {
  color: #999;
}   
.modal-confirm .icon-box {
  width: 80px;
  height: 80px;
  margin: 0 auto;
  border-radius: 50%;
  z-index: 9;
  text-align: center;
  border: 3px solid #f15e5e;
}
.modal-confirm .icon-box i {
  color: #f15e5e;
  font-size: 46px;
  display: inline-block;
}
.modal-confirm .btn, .modal-confirm .btn:active {
  color: #fff;
  border-radius: 4px;
  background: #60c7c1;
  text-decoration: none;
  transition: all 0.4s;
  line-height: normal;
  min-width: 120px;
  border: none;
  min-height: 40px;
  border-radius: 3px;
  margin: 0 5px;
}
.modal-confirm .btn-secondary {
  background: #c1c1c1;
}
.modal-confirm .btn-secondary:hover, .modal-confirm .btn-secondary:focus {
  background: #a8a8a8;
}
.modal-confirm .btn-danger {
  background: #f15e5e;
}
.modal-confirm .btn-danger:hover, .modal-confirm .btn-danger:focus {
  background: #ee3535;
}
.trigger-btn {
  display: inline-block;
  margin: 100px auto;
}

.d-block {
  padding-right: 20px !important;
  padding-left: 13px !important;
}

.two {
  /* margin-left: 70%; */
}

  </style>
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
                                                <div class="widget-heading"><b>${bucket.title}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>${bucket.content}
                                                <!-- <div class="badge badge-danger ml-2">Rejected</div> -->
                                                </div>
                                            </div>
	                                            <div class="widget-content-right">
	                                              <form id="complete" action="update">
	                                              <sub>${bucket.registeredDate}&nbsp;&nbsp;</sub> 
	                                                  <input type="hidden" name="no" value="${bucket.no}"></input>
				                                            <button class="border-0 btn-transition btn btn-outline-success2"> 
				                                            <i>${bucket.completedDate}</i></button> 
				                                            <button class="btn-outline-success" id="completeBtn" name="complete" value="${bucket.complete}" type="submit">
				                                            <i class="bi bi-check" style="font-size: 1.5em"></i></button> 
				                                            <button class="border-0 btn-transition btn btn-outline-danger" name="b-trashBtn" onclick="trashDelete(${bucket.no})" type="button">
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
            <button class="mr-2 btn btn-link btn-sm" id="homeBtn" style="float: right; margin-top: 5px;" onclick="moveHome()">취소</button>
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

 
 



