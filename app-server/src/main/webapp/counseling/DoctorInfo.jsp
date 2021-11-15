<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="/apus/css/DoctorInfo.css">

<title>APUJIMA 의사</title>
	<h1>APUJIMA 의사</h1>
	<div>
		<c:forEach items="${memberList}" var="member">
		 <c:if test="${member.doctorOrNot eq '2'}">
			<!-- <div class='doctor'> -->
				<form action='../counseling/CounselingMemberForm.jsp' method='post' >
				  <div class="container">
					<div class='doctorjpg'>
					  <img src="../img/doctor/doctor1.jpg" width="auto" height="400">
				</div>
					<div class='doctorinfo'>
						<p class='title'>"${member.doctor.introduction}"</p>
						<input type="hidden" name="member.name" value="${member.name}">
						<p>이름 : ${member.name}</p>
						<%-- <p>전문분야 : ${member.doctor.major}</p> --%>
						<p>메일 : ${member.email}</p>
						<p>${member.doctor.introduction} 일상 생활에서의 스트레스가 쌓여 건강을 해칠 수 있습니다. 편한 마음으로 이야기 하실 수 있는 상담사가 되겠습니다.</p>
						<%-- <p>연락처 : ${member.tel}</p> --%>
			        <button type="submit" class="btn btn-primary btn-sm" >전화예약신청</button>
			        <button type="submit" class="btn btn-primary btn-sm" >온라인상담신청</button>
					</div>
					</div>
	      </form>
			 <!-- </div> -->
		  <hr>
		</c:if>
	</c:forEach>
 </div>

