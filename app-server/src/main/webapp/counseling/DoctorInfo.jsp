<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="/apus/css/DoctorInfo.css">

<title>APUJIMA 의사</title>
	<!-- <h1>APUJIMA 의사</h1> -->
	<div id="box1">
	<h1 style=" height: 100px;text-align: center; margin-top:50px;"><img src="../img/doctor/logo1.png" width="50px;">  나에게 딱 맞는 HEALER</h1> 
 <!--  <div class="text-step"> -->
    <p class="text-one">간편한 상담신청</p>
    <p class="text-two">모든 정보는 보호</p>
    <p class="text-three">안심하고 상담진행 GO</p>
  <!-- </div> -->
  </div>
  
	<div>
		<c:forEach items="${memberList}" var="member">
		 <c:if test="${member.doctorOrNot eq '2'}">
			 <div id='doctor'> 
				<!-- <form action='../counseling/CounselingMemberForm.jsp' method='post' > -->
				  <div class="containerBox">
						<div class='doctorjpg'>
						  <img src="../img/doctor/doctor1.jpg" width="auto" height="400">
					  </div>
						<div class='doctorinfo'>
							<p class='title'>"${member.doctor.introduction}"</p>
							<p>이름 : ${member.name}</p>
							<%-- <p>전문분야 : ${member.doctor.major}</p> --%>
							<p>메일 : ${member.email}</p>
							<p>${member.doctor.introduction} 일상 생활에서의 스트레스가 쌓여 건강을 해칠 수 있습니다. 편한 마음으로 이야기 하실 수 있는 상담사가 되겠습니다.</p>
							<%-- <p>연락처 : ${member.tel}</p> --%>
						<form action='../counseling/form' method="get">
								<input type="hidden" name="counselorNo" value="${member.no}">
					        <button class="btn btn-primary btn-sm" >전화예약신청</button>
					        <button type="submit" class="btn btn-primary btn-sm" >온라인상담신청</button>
			      </form>
						</div>
					</div>
	      <!-- </form> -->
			  </div> 
		  <hr>
		</c:if>
	</c:forEach>
 </div>






<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<div class="container">

  <div class="row">
    <div class="col-md-12 pt-5 pb-2 ourTeam-hedding text-center">
      <h1>Our Team</h1>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,<strong> sed do eiusmod
      tempor incididunt </strong>ut labore et dolore magna aliqua. Ut enim ad minim veniam,
      quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. 
      </p>
    </div>
  </div>
  <c:forEach items="${memberList}" var="member">
     <c:if test="${member.doctorOrNot eq '2'}">
  <div class="row">
    <div class="col-md-4 col-sm-4 col-xs-12">
      <div class="row section-success ourTeam-box text-center">
        <div class="col-md-12 section1">
          <img src="../img/doctor/doctor1.jpg">
        </div>
        <div class="col-md-12 section2 pb-3">
          <p>${member.name}</p>
          <p>${member.email}</p>
          <span>${member.doctor.introduction} <br>consectetur.</span>
        </div>
        <div class="col-md-12 section3">
          <form action='../counseling/CounselingMemberForm.jsp'>
            <input type="hidden" name="member.name" value="${member.name}">
            <button class="btn btn-primary btn-sm" >전화예약신청</button>
            <button type="submit" class="btn btn-primary btn-sm" >온라인상담신청</button>
          </form>
        </div>
      </div>
    </div>
    </div>
    </c:if>
  </c:forEach>
    <!-- <div class="col-md-4 col-sm-4 col-xs-12">
      <div class="row section-info ourTeam-box text-center">
        <div class="col-md-12 section1">
          <img src="https://www.wrappixel.com/demos/ui-kit/wrapkit/assets/images/team/t1.jpg">
        </div>
        <div class="col-md-12 section2 pb-3">
          <p>NOEL FLANTIER</p>
          <span>Lorem ipsum dolor sit amet, <br>consectetur.</span>
        </div>
        <div class="col-md-12 section3">
          <i class="fa fa-facebook" aria-hidden="true"></i>
          <i class="fa fa-twitter" aria-hidden="true"></i>
          <i class="fa fa-dribbble" aria-hidden="true"></i>
        </div>
      </div>
    </div>
    <div class="col-md-4 col-sm-4 col-xs-12">
      <div class="row section-danger ourTeam-box text-center">
        <div class="col-md-12 section1">
          <img src="https://www.wrappixel.com/demos/ui-kit/wrapkit/assets/images/team/t3.jpg">
        </div>
        <div class="col-md-12 section2 pb-3">
          <p>JIM JONES</p>
          <span>Lorem ipsum dolor sit amet, <br>consectetur.</span>
        </div>
        <div class="col-md-12 section3">
          <i class="fa fa-facebook" aria-hidden="true"></i>
          <i class="fa fa-twitter" aria-hidden="true"></i>
          <i class="fa fa-dribbble" aria-hidden="true"></i>
        </div>
      </div>
    </div>
  </div> -->
 
</div>

