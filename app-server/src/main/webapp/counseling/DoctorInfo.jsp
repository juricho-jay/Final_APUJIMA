<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>APUJIMA 의사</title>
<link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core//dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  <style>
  .container {
    xborder: 1px solid red;
    width: 640px;
    xtext-align: left;
  }
  .doctor {
    width: 640px;
    height:500px; 
    margin-top: 100px;
    xtext-align: center;
  }
 .doctorjpg {
    xborder: 1px solid red;
    float:left;
    width:300px;
    height:400px; 
  }
 .doctorinfo {
	  display:inline-block; 
	  width:200px; 
	  height:300px; 
	  margin-left:100px;
	  margin-top: 100px;
	  margin-bottom: 100px;
  }  
  .title {
    font-size: 20px;
    font-weight: 200px;
    font-style: italic;
    font-family: 'Nanum Myeongjo', serif;
  }
  
  
</style>


</head>
<body>
<div class="container">
	<h1>APUJIMA 의사</h1>
		<c:forEach items="${memberList}" var="member">
		 <c:if test="${member.doctorOrNot eq '2'}">
			<!-- <div class='doctor'> -->
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
				<form action='../counseling/CounselingMemberForm.jsp'>
						<input type="hidden" name="member.name" value="${member.name}">
			        <button class="btn btn-primary btn-sm" >전화예약신청</button>
			        <button type="submit" class="btn btn-primary btn-sm" >온라인상담신청</button>
	      </form>
					</div>
					
			<!-- </div> -->
		<hr>
			</c:if>
		</c:forEach>
</div><!-- .container -->

</body>
</html>