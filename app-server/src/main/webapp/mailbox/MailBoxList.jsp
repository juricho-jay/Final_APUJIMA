<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>쪽지함</title>
   <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
   <link rel="stylesheet" href="/spring/resources/css/style.css" />

  <script src="../node_modules/@popperjs/core//dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  
  <style type="text/css">
  @font-face {
      font-family: 'IBMPlexSansKR-Regular';
      src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/IBMPlexSansKR-Regular.woff') format('woff');
      font-weight: normal;
      font-style: normal;
    } 
    
    body{
      font-family:IBMPlexSansKR-Regular;

    }
  </style>
  
  <style>
  
    /* 전체 틀 */
    .container {
	    xborder: 1px solid red;
	    xwidth: 640px;
	    
    }
    
    /* 모달창 디자인 */
    #modal.modal-overlay {
      width: 100%;
      height: 100%;
      position: absolute;
      left: 0;
      top: 0;
      display: none;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      background: rgba(255, 255, 255, 0.25);
      box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.37);
      backdrop-filter: blur(1.5px);
      -webkit-backdrop-filter: blur(1.5px);
      border-radius: 10px;
      border: 1px solid rgba(255, 255, 255, 0.18);
    }
    
    #modal .modal-window {
      background: rgba( 69, 139, 197, 0.70 );
      box-shadow: 0 8px 32px 0 rgba( 31, 38, 135, 0.37 );
      backdrop-filter: blur( 13.5px );
      -webkit-backdrop-filter: blur( 13.5px );
      border-radius: 10px;
      border: 1px solid rgba( 255, 255, 255, 0.18 );
      width: 750px;
      height: 650px;
      position: relative;
      top: -100px;
      padding: 10px;
    }
    
    #modal .title {
      padding-left: 10px;
      display: inline;
      text-shadow: 1px 1px 2px gray;
      color: white;
      font-size:20px;
      
    }
    
    /* #modal .title h2 {
      display: inline;
      
      
    } */
    
    #modal .close-area {
      display: inline;
      float: right;
      padding-right: 10px;
      cursor: pointer;
      text-shadow: 1px 1px 2px gray;
      color: white;
    }
        
     #modal .content {
      margin-top: 20px;
      padding: 0px 10px;
      text-shadow: 1px 1px 2px gray;
      color: white;
     }
        
      /* 쪽지함 모달안 디자인 */
      .content {
	    xborder: 1px solid gray;
	    width: 640px;
    }
    .sendbtn {
	    xborder: 1px solid red;
	    width: 640px;
	    text-align: center;
	    
    }
    
    /* 쪽지함 리스트 */
    .th{
      font-size:13px;
    }
  </style>
  
</head>


    
<body>
<div class="container">
	<h1>${loginUser.id}님의 쪽지함</h1>
	<!-- <a href='MailBoxForm.jsp' class="btn btn-outline-primary btn-sm">보내기</a><br> -->
<div id="modal" class="modal-overlay">
    <div class="modal-window">
        <div class="title">
            쪽지함
        </div>
        <div class="close-area">X</div>
        <div class="content">
            <form name = "usersend" action='send' method="post" onsubmit="return checkValue()">
							<div class="content1">
							  <br>
							  <label for="box1">보낸이</label>
							  <br>
							  <div class="col-sm-4">
							      <input id='f-sender' type='text' name='sender.id' class="form-control" value="${loginUser.id}" readOnly>
							  </div>
							  <br>
							</div>
							<div class="content2">
							  <label for="f-receiver">받는이</label>
							  <br>
							  <div class="col-sm-4">
							      <input id='f-receiver' type='text' name='receiver.id' class="form-control">
							      <input type="button" name='findId' value="아이디확인">
							      
							  </div>
							  <!-- <textarea id="f-receiver" name="receiver" rows=1 cols=45></textarea>
							  <input type="button" name = 'idCheck' value="존재하는 회원인지 체크">  -->
							  <br>
							</div>
							<div class="content3">
							  <label for="f-title">제목</label>
							  <br>
							  <textarea id="f-title"  name="title" rows=1 cols=73 class="form-control">
							  </textarea>
							  <br>
							</div>
							<div class="content4">
							  <label for="f-content">내용</label>
							  <br>
							  <textarea id="f-content" name="content" rows=5 cols=73 class="form-control">
							  </textarea>
							  <br>
							</div>
							<div class="col-12 sendbtn">
							<button type="submit" class="btn btn-primary btn-sm">보내기</button>
							  </div>
							</form>
							            
		        </div>
		    </div>
		</div>
		<button id="btn-modal" class="btn btn-outline-primary btn-sm">보내기</button>
	<table class="table table-hover">
		<thead>
		  <tr>
		    <th scope="col"><input type="checkbox" name="select_all" id="select_all" value="select_all"></th>
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
					  <td><input type="checkbox" name="select_tch" id="select_tch" value="${list.userno}"></td>
					  <td>${mailBox.no}</td>
					  <td>${mailBox.sender.id}</td> 
					  <%-- <td>${mailBox.receiver.id}</td> --%>
					  <td><a href='detail?no=${mailBox.no}'>${mailBox.title}</a></td>
					  <td>${mailBox.content}</td>
					  <td>${mailBox.sentTime}</td>
					</tr>
				</c:if>
			</c:forEach>
		</tbody>
	</table>
</div><!-- .container -->



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


</body>
</html>








