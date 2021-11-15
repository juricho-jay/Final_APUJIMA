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
  <!-- <a href='MailBoxForm.jsp' class="btn btn-outline-primary btn-sm">보내기</a><br> -->
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








