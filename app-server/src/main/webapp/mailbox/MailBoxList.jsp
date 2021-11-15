<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="/apus/css/MailBoxList.css">
  

  
  <style>
  
    /* 전체 틀 */
    .container2 {
	    xborder: 1px solid red;
	    xwidth: 640px;
	    
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
    
    /* 쪽지함 리스트 */
    .th{
      font-size:13px;
    }
    
    .mail-box {
      xborder: 1px solid red;
      width: 700px;
      height: 100px;
      margin: 0 auto;
      margin-top:50px;
      border-left: 0.5em;
      border-left-style:solid;
      border-left-color: lightgray;
      xborder-bottom: 0.1em solid gray;
    }
    
    .box-photo{
      xborder: 1px solid blue;
      width: 100px;
      height: 100px;
      float: left;
      padding-left: 20px;
      padding-top: 20px;
    }
    
    .box-img{
      xborder: 1px solid blue;
      width: 60px;
      height: 60px;
      float: left;
      line-height:100px;
      background:darkgray;
      border-radius:50%;
      vertical-align: middle;
    }
    
    .box-id{
      xborder: 1px solid gray;
      padding-top: 35px;
      float: left;
      margin-left: 35px;
      font-size: 16px;
    }
    
    .box-title{
      xborder: 1px solid skyblue;
      padding-top: 35px;
      float: left;
      margin-left: 35px;
      color:darkgray;
    }
    
    .box-time{
      xborder: 1px solid green;
      float:right;
      padding-right: 20px;
      padding-top: 35px;
      
    }
    .hr{
      width: 700px;
      margin: 0 auto;
    }
    
  </style>
  
</head>


    
<body>
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
							  <br><hr>
							</div>
							<div class="col-12 sendbtn">
							<button type="submit" class="btn btn-outline-secondary">보내기</button>
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
	            <span class="box-img">${mailBox.receiver.photo}</span> <!-- 이게 될까? -->
	          </div>
             <b class="box-id">${mailBox.sender.nickname}</b>
             <div class="box-title">
               <a href='detail?no=${mailBox.no}'>${mailBox.title}</a>
             </div>
            <span class="box-time">${mailBox.sentTime}</span>
          </div>
         
        </c:if>
      </c:forEach>
		</div>
		
		</div>
		
		
	<%-- <table class="table table-hover">
		<thead>
		  <tr>
		    
		    <th>번호</th>
		    <th>보낸이</th>
		    <!-- <th>받는이</th> -->
		    <th>제목</th>
		    <th>내용</th>
		    <th>날짜</th>
		  </tr>
		</thead>
		
		<tbody>
			<c:forEach items="${mailBoxList}" var="mailBox">
				<c:if test='${loginUser.id eq mailBox.receiver.id}'>
					<tr>
					  
					  <td>${mailBox.no}</td>
					  <td>${mailBox.sender.id}</td> 
					  <td>${mailBox.receiver.id}</td>
					  <td><a href='detail?no=${mailBox.no}'>${mailBox.title}</a></td>
					  <td>${mailBox.content}</td>
					  <td>${mailBox.sentTime}</td>
					</tr>
				</c:if>
			</c:forEach>
		</tbody>
	</table> --%>
<!-- .container -->


<!-- <a href='MailBoxForm.jsp' id="btn-modal" class="btn btn-outline-primary btn-sm">보내기</a><br> -->


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
</script>









