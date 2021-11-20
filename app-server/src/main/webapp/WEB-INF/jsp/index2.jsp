<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
     trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <title>메인 화면</title>
   <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <link href="https://fonts.googleapis.com/css?family=Playfair+Display:400,400i,700,700i&display=swap" rel="stylesheet">
  
  <link rel="stylesheet" href="${contextPath}/css/main/owl.carousel.min.css">
  <link rel="stylesheet" href="${contextPath}/css/main/owl.theme.default.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/4.5.6/css/ionicons.min.css">
  <link rel="stylesheet" href="${contextPath}/css/main/style.css">
  
  
  <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
 
  
  
  
  
  
  <style>
  body {
    background-image: linear-gradient( rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5) ),url(${contextPath}/img/header/main_bg1.png);
    /* background-image: url('/apus/img/header/opacity.svg'), url('/apus/img/header/main_bg.png'); */
    background-repeat: no-repeat;
    background-size : cover;

  }
  
  p {
  color: white;
  margin-top: 40px;
  }
  
  </style>
  

<div class="text" style="margin-bottom:500px;">
      <p class="text-center fs-1">We are here for friends in need.</p>
      <p class="text-center fs-2">We are here for friends in need.</p>
      <p class="text-center fs-3">We are here for friends in need.</p>
      <p class="text-center fs-4">We are here for friends in need.</p>
      <p class="text-center fs-5">We are here for friends in need.</p>
</div>
         
         
  <!-- <div style="width: 85%; height:500px; margin-top: 100px; background-color: #2C473E; position:absolute;">
  </div> -->
         
         
<!-- carausel -->    
<section class="ftco-section">
      <div class="container">
        <div class="row">
          <div class="col-md-12 text-center">
            <!-- <h2 class="heading-section mb-5">Carousel #03</h2> -->
          </div>
          <div class="col-md-12">
            <div class="featured-carousel owl-carousel">
              <div class="item">
                <div class="work">
                  <div class="img d-flex align-items-end justify-content-center" style="background-image: url(${contextPath}/img/main_doctor_1.jpg);">
                    <div class="text w-100">
                      <span class="cat">김사사 상담사</span>
                      <h3><a href="#">안녕하세요 APUJIMA입니다.</a></h3>
                    </div>
                  </div>
                </div>
              </div>
              <div class="item">
                <div class="work">
                  <div class="img d-flex align-items-end justify-content-center" style="background-image: url(${contextPath}/img/main_doctor_4.jpg);">
                    <div class="text w-100">
                      <span class="cat">조주리 상담사</span>
                      <h3><a href="#">정신건강에 도움을..</a></h3>
                    </div>
                  </div>
                </div>
              </div>
              <div class="item">
                <div class="work">
                  <div class="img d-flex align-items-end justify-content-center" style="background-image: url(${contextPath}/img/main_doctor_3.jpg);">
                    <div class="text w-100">
                      <span class="cat">김태호 상담사</span>
                      <h3><a href="#">오늘날 4명중에 1명이 마음병이 생깁니다.</a></h3>
                    </div>
                  </div>
                </div>
              </div>
              <div class="item">
                <div class="work">
                  <div class="img d-flex align-items-end justify-content-center" style="background-image: url(${contextPath}/img/main_doctor_2.jpg);">
                    <div class="text w-100">
                      <span class="cat">김진현 상담사</span>
                      <h3><a href="#">APUJIMA가 도와드리겠습니다.</a></h3>
                    </div>
                  </div>
                </div>
              </div>
              <div class="item">
                <div class="work">
                  <div class="img d-flex align-items-end justify-content-center" style="background-image: url(${contextPath}/img/main_doctor_4.jpg);">
                    <div class="text w-100">
                      <span class="cat">신현지 상담사</span>
                      <h3><a href="#">감사합니다.</a></h3>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
<!--Carousel Wrapper-->

<div class="noticeBoardTab" style="margin-top: 300px;">
<h4 style="margin-left:18%; color: #2C473E;">공지사항</h4>
<table class="table table-sm" style="margin-left: 18%; width: 850px; color: #2C473E">
  <thead>
  </thead>
  <tbody>
  <c:forEach items="${boardList}" var="board">
    <tr>
      <td style= "width: 10%" class="text-center">${board.no}</td>
     <c:if test='${board.whichBoard == 1}'>
    <td style= "width: 10%" class="text-center">자유게시판</td> 
    </c:if>
    <c:if test='${board.whichBoard == 2}'>
     <td style= "width: 10%" class="text-center">Healer지식in</td> 
    </c:if>
    <c:if test='${board.whichBoard == 3}'>
     <td style= "width: 10%" class="text-center">공지사항</td> 
    </c:if>
     <td style= "width: 10%" class="text-center"><a href='detail?no=${board.no}'>${board.title}</a></td> 
     <td style= "width: 10%" class="text-center">${board.content}</td> 
     <td style= "width: 10%" class="text-center">${board.writer.nickname}</td> 
     <td style= "width: 30%" class="text-center">${board.registeredDate}</td> 
     <td style= "width: 10%" class="text-center">${board.viewCount}</td>
    </tr>
    </c:forEach>
  </tbody>
</table>
</div>


<div class="homebottombtn" style="margin-left: 45%; border-color: #2C473E">

<a class="btn btn-light" href="/apus/board/list" role="button">자세히 보기&nbsp;&nbsp;<i class="bi bi-arrow-bar-right"></i></a>
</div>

    <script src="${contextPath}/js/jquery.min.js"></script>
    <script src="${contextPath}/js/popper.js"></script>
    <script src="${contextPath}/js/bootstrap.min.js"></script>
    <script src="${contextPath}/js/owl.carousel.min.js"></script>
    <script src="${contextPath}/js/main.js"></script>

