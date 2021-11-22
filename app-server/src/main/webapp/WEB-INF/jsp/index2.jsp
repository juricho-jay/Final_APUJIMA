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
  
  <link rel="stylesheet" href="${contextPath}/css/main/ftco-section/owl.carousel.min.css">
  <link rel="stylesheet" href="${contextPath}/css/main/ftco-section/owl.theme.default.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/4.5.6/css/ionicons.min.css">
  <link rel="stylesheet" href="${contextPath}/css/main/ftco-section/style.css">
  
  <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500&display=swap" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Playfair+Display:400,900&display=swap" rel="stylesheet">
  
  <link rel="stylesheet" href="${contextPath}/css/main/select/owl.carousel.min.css">
  <link rel="stylesheet" href="${contextPath}/css/main/select/animate.css">
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="${contextPath}/css/main/select/bootstrap.min.css">
  <!-- Style -->
  <link rel="stylesheet" href="${contextPath}/css/main/select/style.css">
  
  <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
 
  
  
  <script>
window.onload = function() {
  $("#welcome").hide().fadeIn(3500);
  };
</script>
  
  
  <style>
  body {
    background-image: linear-gradient( rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5) ),url(${contextPath}/img/header/main_bg1.png);
    /* background-image: url('/apus/img/header/opacity.svg'), url('/apus/img/header/main_bg.png'); */
    
    background-repeat: no-repeat;
    background-size : cover;
    margin: 0;
    width: 100%;
    height: 100%;
    
  }
  
  .sed-body{
  }
  
  /* text-center {
  color: white;
  margin:0 auto;
  margin-top: 40px;
  text-align:center;
  } */
  
  
  </style>
  
			<!-- <div class="text" style="margin-bottom:500px;">
			      <span class="text-center fs-1">We are here for friends in need.</span><br>
			      <span class="text-center fs-2">We are here for friends in need.</span><br>
			      <span class="text-center fs-3">We are here for friends in need.</span><br>
			      <span class="text-center fs-4">We are here for friends in need.</span><br>
			      <span class="text-center fs-5">We are here for friends in need.</span><br>
			</div> -->
			         
			         
			  <!-- <div style="width: 85%; height:500px; margin-top: 100px; background-color: #2C473E; position:absolute;">
			  </div> -->
			  
			  <div class="position-absolute top-50 start-50 translate-middle text-end" id="welcome" style="margin-bottom:100px;">
			  <h1 style="color:white;"><b>We are here for friends in need</b></h1>
			  <h3 style="color:white;">APUJIMA에 오신 것을 환영합니다</h3>
			  <p class="fs-5" style="color:white;">당신의 심적 안정을 위한 플랫폼</p>
			  <h6 style="color:white;">-with APUs-</h6>
			  </div>
			  
			  
<div class="content1" style="margin-top:1000px;">
    
    <div class="container">
      <!-- <h2 class="my-5 text-center">Carousel #8</h2> -->

      <div class="d-flex carousel-nav">
        <a href="#" class="col active">APUJIMA</a>
        <a href="#" class="col">무료자가진단</a>
        <a href="#" class="col">상담을빠르게</a>
      </div>


      <div class="owl-carousel owl-1">

        <div class="media-29101 d-md-flex w-100">
          <div class="img">
            <img src="${contextPath}/img/open.jpg" alt="Image" class="img-fluid">
          </div>
          <div class="text">
            <a class="category d-block mb-4" href="#">Travel &mdash; First Tab</a>
            <h2><a href="#">APUJIMA OPEN</a></h2>
            <p></p>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Repudiandae fuga optio dolorem, fugit voluptates sint ducimus praesentium iste!</p>
          </div>
        </div> <!-- .item -->

        <div class="media-29101 d-md-flex w-100">
          <div class="img">
            <img src="${contextPath}/img/teb1.jpg" alt="Image" class="img-fluid">
          </div>
          <div class="text">
            <a class="category d-block mb-4" href="#">심리검사 &mdash; Second Tab</a>
            <h2><a href="#">심리검사를 무료로 진단받아보세요.</a></h2>
            <p>마음이 아픈 사람들 10명 8명은 '나는 괜찮다'고 생각해요. 지금 나의 마음 건강도 궁금하다면 심리검사를 통해 알아보세요.</p>
          </div>
        </div> <!-- .item -->

        <div class="media-29101 d-md-flex w-100">
          <div class="img">
            <img src="${contextPath}/img/teb2.jpg" alt="Image" class="img-fluid">
          </div>
          <div class="text">
            <a class="category d-block mb-4" href="#">Travel &mdash; Third Tab</a>
            <h2><a href="#">빠르게 상담 받는 꿀팁</a></h2>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Repudiandae fuga optio dolorem, fugit voluptates sint ducimus praesentium iste!</p>
          </div>
        </div> <!-- .item -->

        

      </div>
    </div>
</div>
			  
			  
			         
			<!-- carausel -->    
			<section class="ftco-section">
			      <div class="container">
			        <div class="row">
			          <div class="col-md-12 text-center">
			             <h2 class="heading-section mb-5">APUJIMA 상담사를 통해 <br><br>좀 더 단단한 내가 될 수 있도록 희망합니다.</h2> 
			             
			          </div>
			          <div class="col-md-12">
			            <div class="featured-carousel owl-carousel">
			              <div class="item">
			                <div class="work">
			                  <div class="img d-flex align-items-end justify-content-center" style="background-image: url(${contextPath}/img/main_doctor_1.jpg);">
			                    <div class="text w-100">
			                      <span class="cat">오은영 상담사</span>
			                      <h3><a href="#">안녕하세요 APUJIMA입니다.</a></h3>
			                    </div>
			                  </div>
			                </div>
			              </div>
			              <div class="item">
			                <div class="work">
			                  <div class="img d-flex align-items-end justify-content-center" style="background-image: url(${contextPath}/img/main_doctor_4.jpg);">
			                    <div class="text w-100">
			                      <span class="cat">James Park 상담사</span>
			                      <h3><a href="#">정신건강에 도움을..</a></h3>
			                    </div>
			                  </div>
			                </div>
			              </div>
			              <div class="item">
			                <div class="work">
			                  <div class="img d-flex align-items-end justify-content-center" style="background-image: url(${contextPath}/img/main_doctor_3.jpg);">
			                    <div class="text w-100">
			                      <span class="cat">강모연 상담사</span>
			                      <h3><a href="#">오늘날 4명중에 1명이 마음병이 생깁니다.</a></h3>
			                    </div>
			                  </div>
			                </div>
			              </div>
			              <div class="item">
			                <div class="work">
			                  <div class="img d-flex align-items-end justify-content-center" style="background-image: url(${contextPath}/img/main_doctor_2.jpg);">
			                    <div class="text w-100">
			                      <span class="cat">이익준 상담사</span>
			                      <h3><a href="#">APUJIMA가 도와드리겠습니다.</a></h3>
			                    </div>
			                  </div>
			                </div>
			              </div>
			              <div class="item">
			                <div class="work">
			                  <div class="img d-flex align-items-end justify-content-center" style="background-image: url(${contextPath}/img/main_doctor_4.jpg);">
			                    <div class="text w-100">
			                      <span class="cat">채송화 상담사</span>
			                      <h3><a href="#">힘든 일이 있을때는 언제든 말씀주세요.</a></h3>
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
			
			<div class="noticeBoardTab" style="margin-top: 100px;">
					<div class="board-view">
					    <h4 style="margin-left:13%; color: #2C473E; float:left; vertical-align:buttom; ">공지사항  </h4>
					    <span class="text-info" style="float:right; margin-right:13%; font-size:15px; margin-top:6px;"> <a href="${contextPath}/app/board/noticeBoardList" style="color: #2C473E;">더보기</a></span>
					</div>
					<br>
					<div class="board-table" style="margin-top: 50px; clear:both;">
							<table class="table table-sm" style="margin: 0 auto; width: 850px; color: #2C473E">
							  <thead>
							  </thead>
							  <tbody>
							  <c:forEach items="${boardList}" var="board">
							    <c:if test='${board.whichBoard == 3}'>
							    <tr>
							      <%-- <td style= "width: 10%" class="text-center">${board.no}</td> 
							   <c:if test='${board.whichBoard == 1}'>
							    <td style= "width: 10%" class="text-center">자유게시판</td> 
							    </c:if>
							    <c:if test='${board.whichBoard == 2}'>
							     <td style= "width: 10%" class="text-center">Healer지식in</td> 
							    </c:if> --%>
							     <!-- <td style= "width: 10%" class="text-center">공지사항</td>  -->
							     <td style= "width: 60%" class="text-center"><a href='board/detail?no=${board.no}' style="color: #000;">${board.title}</a></td> 
							     <%-- <td style= "width: 40%" class="text-center">${board.content}</td> 
							     <td style= "width: 10%" class="text-center">${board.writer.nickname}</td>  --%>
							     <td style= "width: 20%" class="text-center">${board.registeredDate}</td> 
							    </tr>
							    </c:if>
							    </c:forEach>
							  </tbody>
							</table>
					</div>
			</div>


<!-- <div class="homebottombtn" style="margin-left: 45%; border-color: #2C473E">

<a class="btn btn-light" href="/apus/board/list" role="button">자세히 보기&nbsp;&nbsp;<i class="bi bi-arrow-bar-right"></i></a>
</div> -->

    <script src="${contextPath}/js/main/ftco-section/jquery.min.js"></script>
    <script src="${contextPath}/js/main/ftco-section/popper.js"></script>
    <script src="${contextPath}/js/main/ftco-section/bootstrap.min.js"></script>
    <script src="${contextPath}/js/main/ftco-section/owl.carousel.min.js"></script>
    <script src="${contextPath}/js/main/ftco-section/main.js"></script>

    <script src="${contextPath}/js/main/select/jquery-3.3.1.min.js"></script>
    <script src="${contextPath}/js/main/select/popper.min.js"></script>
    <script src="${contextPath}/js/main/select/bootstrap.min.js"></script>
    <script src="${contextPath}/js/main/select/owl.carousel.min.js"></script>
    <script src="${contextPath}/js/main/select/main.js"></script>
    
    

