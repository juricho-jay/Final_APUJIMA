<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
            <form name = "usersend" action='send' method="post" onsubmit="return checkValue()">
							<div class="content1">
							  <br>
							  <label for="box1">보낸이</label>
							  <br>
							  <div class="col-sm-4">
							      <input id='f-sender' type='text' name='sender.nickname' class="form-control" value="${loginUser.nickname}" readOnly>
							  </div>
							  <br>
							</div>
							<div class="content2">
							  <label for="f-receiver">받는이</label>
							  <br>
							  <div class="col-sm-4">
							      <input id='f-receiver' type='text' name='receiver.nickname' class="form-control">
							      <!-- <input type="button" name='findId' value="아이디확인"> -->
							      
							  </div>
							  <!-- <textarea id="f-receiver" name="receiver" rows=1 cols=45></textarea>
							  <input type="button" name = 'idCheck' value="존재하는 회원인지 체크">  -->
							  <br>
							</div>
							<div class="content3">
							  <label for="f-title">제목</label>
							  <br>
							  <textarea id="f-title"  name="title" rows=1 cols=73 class="form-control"></textarea>
							  <br>
							</div>
							<div class="content4">
							  <label for="f-content">내용</label>
							  <br>
							  <textarea id="f-content" name="content" rows=5 cols=73 class="form-control"></textarea>
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
          <c:if test='${mailBox.receivedTime == null}'>
            <div class="mail-box">
              <div class="box-photo">
                <span class="box-img">${mailBox.receiver.photo}</span>
              </div>
               <b class="box-id">${mailBox.sender.nickname}</b>
               <div class="box-title">
                 <a href='detail?no=${mailBox.no}'>${mailBox.title}</a>
               </div>
              <span class="box-time">${mailBox.sentTime}</span>
            </div>
           </c:if>
        </c:if>
      </c:forEach>
    </div>
		</div>
		
	
    
    


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

<script >
$(document).ready(function(){
	$('#btnsub').click(function() {
		var result = confirm('정말 쪽지를 보내시겠습니까?');
		if(result) {//yes
			location.replace('/mailbox/MailBoxList.jsp');
		} else { //no
			}
		});
	});

</script>









