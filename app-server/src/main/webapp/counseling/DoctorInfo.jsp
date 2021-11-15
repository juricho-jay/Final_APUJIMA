<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>APUJIMA 의사</title>
<!-- <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core//dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script> -->
  
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
  <link rel="stylesheet" href="userInfo.css">
  
  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
  <script src="https://unpkg.com/@popperjs/core@2/dist/umd/popper.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  
  <style>
  .container {
    xborder: 1px solid red;
    width: 640px;
    xtext-align: left;
  }
  /* .doctor {
    width: 640px;
    height:500px; 
    margin-top: 100px;
    xtext-align: center;
  } */
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
	
	<h1 style="text-align: center; margin-bottom:50px;"><img src="../img/doctor/logo1.png" width="50px;">  나에게 딱 맞는 HEALER</h1> 
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