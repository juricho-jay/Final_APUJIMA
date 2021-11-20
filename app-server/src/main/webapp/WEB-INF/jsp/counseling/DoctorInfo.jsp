<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="/apus/css/DoctorInfo.css">

<title>APUJIMA 의사</title>
	<!-- <h1>APUJIMA 의사</h1> -->
	<div id="big-box">
			<div class="box1">
				<!-- <h1 style=" height: 100px;text-align: center; margin-top:50px;"><img src="../img/doctor/logo1.png" width="50px;">  나에게 딱 맞는 HEALER</h1> --> 
			  <h1 style=" height: 100px;text-align: center; margin-top:50px;">
			   <img src="${contextPath}/img/doctor/logo1.png" class="rounded-circle">
			    나에게 딱 맞는 HEALER</h1>
			  <div class="text-step"> 
			    <p class="text-one">간편한 상담신청</p>
			    <p class="text-two">모든 정보는 보호</p>
			    <p class="text-three">안심하고 상담진행</p>
			    </div> 
		  </div>
		  
		  
		  
		  <section class="" style="background-color: #fff;">
  <div class="container ">
    <div class="row d-flex">
		  <c:forEach items="${memberList}" var="member">
           <c:if test="${member.doctorOrNot eq '2'}">
      <div class="col-md-12 col-xl-4">

        <div class="card" style="border-radius: 15px;">
          <div class="card-body text-center">
            <div class="mt-3 mb-4">
              <img
                src="${contextPath}/upload/member/${member.photo}"
                class="rounded-circle img-fluid" style="width: 100px;"
              />
            </div>
            <h4 class="mb-2">${member.name}</h4>
            <p class="text-muted mb-4">${member.doctor.major}<span class="mx-2">| ${member.email}</span> <a href="#!">${member.doctor.homepage}</a></p>
            <div class="mb-4 pb-2">
              <button type="button" class="btn btn-outline-primary btn-floating">
                <i class="fab fa-facebook-f fa-lg"></i>
              </button>
              <button type="button" class="btn btn-outline-primary btn-floating">
                <i class="fab fa-twitter fa-lg"></i>
              </button>
              <button type="button" class="btn btn-outline-primary btn-floating">
                <i class="fab fa-skype fa-lg"></i>
              </button>
            </div>
            
            <form action='../counseling/form' method="get">
                <input type="hidden" name="counselorNo" value="${member.no}">
                <!-- <button class="btn btn-primary btn-sm" >전화예약신청</button>
                <button type="submit" class="btn btn-primary btn-sm" >온라인상담신청</button> -->
		           <button type="submit" class="btn btn-primary btn-rounded btn-lg">
		             상담신청하기
		           </button>
                      </form>
            
          </div>
        </div>

      </div>
  </c:if>
        </c:forEach>
    </div>
  </div>
</section>
		  
		
				<%-- <div class='doctor'> 
					<c:forEach items="${memberList}" var="member">
					 <c:if test="${member.doctorOrNot eq '2'}">
								<!-- <form action='../counseling/CounselingMemberForm.jsp' method='post' > -->
								  <div class="containerBox">
											<div class='doctorjpg'>
											   <a href="${contextPath}/upload/member/${member.photo}" >
											     <img src="${contextPath}/upload/${member.photo}" class="rounded-circle">
										        <img id="f-photo-image" src="${contextPath}/upload/member/${member.photo}_100x100.jpg">
										     </a>
											  <!-- <img src="../img/doctor/doctor1.jpg" width="auto" height="400"> -->
										  </div>
											<div class='doctorinfo'>
												<p class='title'>"${member.doctor.introduction}"</p>
												<p>이름 : ${member.name}</p>
												<p>전문분야 : ${member.doctor.major}</p>
												<p>메일 : ${member.email}</p>
												<p>${member.doctor.introduction} 일상 생활에서의 스트레스가 쌓여 건강을 해칠 수 있습니다. 편한 마음으로 이야기 하실 수 있는 상담사가 되겠습니다.</p>
												<p>연락처 : ${member.tel}</p>
											<form action='../counseling/form' method="get">
													<input type="hidden" name="counselorNo" value="${member.no}">
										        <button class="btn btn-primary btn-sm" >전화예약신청</button>
										        <button type="submit" class="btn btn-primary btn-sm" >온라인상담신청</button>
								      </form>
											</div>
									</div>
					      <!-- </form> -->
					  <hr>
					</c:if>
				</c:forEach>
			</div>  --%>
		







 
</div>

