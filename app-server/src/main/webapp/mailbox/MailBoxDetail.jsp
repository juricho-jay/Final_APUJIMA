<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<link rel="stylesheet" href="/apus/css/MailBoxDetail.css">


<style>
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
        
      /* 쪽지함 모달안 디자인 */
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

<div class="container">
<h1>쪽지함 상세보기</h1>

<div id="modal" class="modal-overlay">
    <div class="modal-window">
        <div class="box">
          <div class="title">
              쪽지함
          </div>
          <div class="close-area">X</div>
        </div>
        <div class="content-in">
            <form name = "usersend" action='resend' method="post" onsubmit="return checkValue()">
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
                    <input id='f-receiver' type='hidden' name='no' class="form-control"value="${sender.no}">
                    <input id='f-receiver' type='text' name='receiver.nickname' class="form-control"value="${sender.nickname}" readOnly>
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
    
    
<form action='../mailbox/resend' method="get">
	<input type="hidden" name="senderNo" value="${mailBox.sender.no}">
	<button id="btn-modal" class="btn btn-outline-primary btn-sm">답장</button>
</form>

<div class="big-box">
  <div class="box2">
      <span class="box-sender">보낸이 : ${mailBox.sender.nickname}</span><br>
      <span class="box-sendtime">보낸시간 : ${mailBox.sentTime}</span><br>
      <span class="box-receivedtime">확인시간 : ${mailBox.receivedTime}</span><br>
  </div>
  <hr>
  <div class="box3">
      <span class="box-title"><b>제목</b> : ${mailBox.title}</span><br>
      <b>내용</b><br>
      <span class="box-content">${mailBox.content}</span>
  </div>
</div>
<button class="btn btn-outline-secondary btn-sm" onclick="location.href='list'">목록</button>
  <a href='delete?no=${mailBox.no}' class="btn btn-outline-secondary btn-sm">삭제</a> 


</div><!-- .container -->

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




