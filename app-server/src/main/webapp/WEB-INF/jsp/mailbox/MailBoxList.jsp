<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<link rel="stylesheet" href="/apus/css/MailBoxList.css">

<title>쪽지함</title>

<div class="container2">
	<h1>${loginUser.nickname}님의 쪽지</h1>
	<!-- <a href='MailBoxForm.jsp' class="btn btn-outline-primary btn-sm">보내기</a><br> -->
<div id="modal" class="modal-overlay">
    <div class="modal-window">
        <div class="box">
	        <div class="title">
	            쪽지함
	        </div>
	        <div class="close-area">X</div>
        </div>
        <div class="content-in">
							<div class="content1">
							  <br>
							  <label for="box1">보낸이</label>
							  <br>
							  <div class="col-sm-4">
							      <input id='f-sender' type='text' name='sender.nickname' class="form-control" value="${loginUser.nickname}" readOnly>
							  </div>
							  <br>
							</div>
            <form  name = "usersend" action='send' method="post" onsubmit="return checkValue()">
							<div class="content2">
							  <label for="f-receiver">받는이</label>
							  <br>
							  <div class="col-sm-4">
							      <input id='f-receiver' type='text' name='nickname' class="form-control" required>
							      <!-- <input type="button" name='findId' value="아이디확인"> -->
							      
							  </div>
							  <!-- <textarea id="f-receiver" name="receiver" rows=1 cols=45></textarea>
							  <input type="button" name = 'idCheck' value="존재하는 회원인지 체크">  -->
							  <br>
							</div>
							<div class="content3">
							  <label for="f-title">제목</label>
							  <br>
							  <textarea id="f-title"  name="title" rows=1 cols=73 class="form-control" required></textarea>
							  <br>
							</div>
							<div class="content4">
							  <label for="f-content">내용</label>
							  <br>
							  <textarea id="f-content" name="content" rows=5 cols=73 class="form-control" required></textarea>
							  <br>
							</div>
							<div>
							<button id="btnsub" type="submit" class="btn btn-outline-secondary" onclick="getData()">보내기</button>
							  </div>
							</form>
		        </div>
		    </div>
		</div>
		
		<button id="btn-modal" class="btn btn-outline-primary btn-sm">보내기</button>
		
		
	
		<hr>
		<div>
		  <c:forEach items="${mailBoxList}" var="mailBox">
        <c:if test='${loginUser.nickname eq mailBox.receiver.nickname}'>
		        <div class="mail-box">
		            <div class="box-photo">
		              <%-- <span class="box-img">${mailBox.receiver.photo}</span> <!-- 이게 될까? --> --%>
		                  <img src="${contextPath}/upload/member/${mailBox.sender.photo}_100x100.jpg"
		            class="rounded-circle"
		            height="80"
		            alt=""
		          />
		            </div>
		             <b class="box-id">${mailBox.sender.nickname}</b>
		             <div class="box-title">
		               <a href='detail?no=${mailBox.no}'>
		                <c:choose>
								       <c:when test="${fn:length(mailBox.title) > 7}">
								          ${fn:substring(mailBox.title, 0, 5)}...
								       </c:when>
								       <c:otherwise>
								          ${mailBox.title}
								       </c:otherwise>
								    </c:choose>
		               </a>
		             </div>
		            <span class="box-time">${mailBox.sentTime} 
		            <button style="margin-left: 10px;" class="border-0 btn-transition btn btn-outline-secondary" 
                name="b-trashBtn" type="button" data-no="${mailBox.no}">
                <i class="bi bi-trash"></i>
                </button>
                </span>
	          </div>
        </c:if>
      </c:forEach>
		</div>
		
		</div>
		
		
		<!-- 휴지통 모달 -->
		<form id="deleteForm" action='delete'>
				<div id="deleteModal" class="modal fade" aria-hidden="true" data-bs-keyboard="false" data-bs-backdrop="static">
				  <div class="modal-dialog modal-confirm">
				    <div class="modal-content">
				      <div class="modal-header flex-column">
				        <div class="icon-box">
				        </div>            
				        <h4 class="modal-title w-100">정말 삭제하시겠습니까?</h4>  
				      </div>
				      <div class="modal-body">
				        <p>삭제된 쪽지는 복구할 수 없습니다.</p>
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
				          <div class="transferNo">
				          <input type="hidden" id="sendNo" name="no" value="">
				          </div>   
</form>
		
		
		
	


<script>

    const modal = document.getElementById("modal")
		const btnModal = document.getElementById("btn-modal")
		btnModal.addEventListener("click", e => {
		    modal.style.display = "flex"
		})
		
		const closeBtn = modal.querySelector(".close-area")
		closeBtn.addEventListener("click", e => {
		    modal.style.display = "none"
		})
		
		modal.addEventListener("click", e => {
	    const evTarget = e.target
		    if(evTarget.classList.contains("modal-overlay")) {
		        modal.style.display = "none"
		    }
    })
    
    $(document).on(selector, event, function() {

}); 
</script>

<script>
//휴지통 모달 열기 + mailbox.no 값 전달 
$('button[name=b-trashBtn]').on('click', function(){
  if(${loginUser != null}) {
  var no = $(this).data('no');
  $(".transferNo #sendNo").val(no);
$('#deleteModal').modal('show');
  }
});

//휴지통 모달 안 취소 버튼>닫기
// 모달 안의 취소 버튼에 이벤트를 건다.
$('#d-cancelBtn').on('click', function(){
$('#deleteModal').modal('hide');
});



</script>





