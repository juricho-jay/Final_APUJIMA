<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>관리자 승인 요청</title>
  
  <link rel="stylesheet" href="${contextPath}/node_modules/bootstrap/dist/css/bootstrap.css">
  
  
      <!-- Bootstrap core CSS -->
	<link href="/docs/5.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	
	    <!-- Favicons -->
	<link rel="apple-touch-icon" href="/docs/5.1/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
	<link rel="icon" href="/docs/5.1/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
	<link rel="icon" href="/docs/5.1/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
	<link rel="manifest" href="/docs/5.1/assets/img/favicons/manifest.json">
	<link rel="mask-icon" href="/docs/5.1/assets/img/favicons/safari-pinned-tab.svg" color="#7952b3">
	<link rel="icon" href="/docs/5.1/assets/img/favicons/favicon.ico">
	<meta name="theme-color" content="#7952b3">
  
  
  
  <script src="${contextPath}/node_modules/@popperjs/core//dist/umd/popper.js"></script>
  <script src="${contextPath}/node_modules/bootstrap/dist/js/bootstrap.js"></script>
  
  <style>
		  .container {
		    width: 300px;
		    height: 160px;
		    position: absolute;
		    top: 30%;
		    left: 57%;
		    margin: -150px;
		  }
		  
		  .container2 {
		    xborder: 1px solid skyblue;
		    width: 300px;
		    height: 160px;
		    position: absolute;
		    top: 40%;
		    left: 56%;
		    margin: -150px;
		  }
  
			    .main-content{
			  width: 50%;
			  border-radius: 20px;
			  box-shadow: 0 5px 5px rgba(0,0,0,.4);
			  margin: 5em auto;
			  display: flex;
			}
			.company__info{
			  background-color: #008080;
			  border-top-left-radius: 20px;
			  border-bottom-left-radius: 20px;
			  display: flex;
			  flex-direction: column;
			  justify-content: center;
			  color: #fff;
			}
			.fa-android{
			  font-size:3em;
			}
			@media screen and (max-width: 640px) {
			  .main-content{width: 90%;}
			  .company__info{
			    display: none;
			  }
			  .login_form{
			    border-top-left-radius:20px;
			    border-bottom-left-radius:20px;
			  }
			}
			@media screen and (min-width: 642px) and (max-width:800px){
			  .main-content{width: 70%;}
			}
			.row > h2{
			  color:#008080;
			  margin-top: 10%;
			}
			.login_form{
			  background-color: #fff;
			  border-top-right-radius:20px;
			  border-bottom-right-radius:20px;
			  border-top:1px solid #ccc;
			  border-right:1px solid #ccc;
			}
			form{
			  padding: 0 2em;
			}
			.form__input{
			  width: 100%;
			  border:0px solid transparent;
			  border-radius: 0;
			  border-bottom: 1px solid #aaa;
			  padding: 1em .5em .5em;
			  padding-left: 2em;
			  outline:none;
			  margin:1.5em auto;
			  transition: all .5s ease;
			}
			.form__input:focus{
			  border-bottom-color: #008080;
			  box-shadow: 0 0 5px rgba(0,80,80,.4); 
			  border-radius: 4px;
			}
			.btn{
			  transition: all .5s ease;
			  width: 70%;
			  border-radius: 30px;
			  color:#008080;
			  font-weight: 600;
			  background-color: #fff;
			  border: 1px solid #008080;
			  margin-top: 1.5em;
			  margin-bottom: 1em;
			}
			.btn:hover, .btn:focus{
			  background-color: #008080;
			  color:#fff;
			}
			
			 btn > b {
			  margin-left: 50%;
			} 

    .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
  
  </style>
</head>
<body>
<div class="container">

<table class="table table-hover">
      <thead>
        <tr>
          <th>번호</th>
          <th>이름</th>
          <th>별명</th>
          <th>아이디</th>
          <th>생년월일</th>
          <th>연락처</th>
          <th>이메일</th>
          <th>성별</th>
          <th>포인트</th>
          <th>회원등급</th>
          <th>등록일</th>
          <th>활동여부</th>
          
        </tr>
      </thead>
    <tbody>
      <c:forEach items="${memberList}" var="member">
	      <tr>
	          <td>${member.no}</td>
	          <td><a href='detail?no=${member.no}'>${member.name}</a></td> 
	          <td>${member.nickname}</td> 
	          <td>${member.id}</td> 
	          <td>${member.birthday}</td> 
	          <td>${member.tel}</td> 
	          <td>${member.email}</td> 
	          <td>${member.sex}</td> 
	          <td>${member.point}</td> 
	          <c:if test='${member.doctorOrNot == 1}'>
	          <td>일반회원</td> 
	          </c:if>
	          <c:if test='${member.doctorOrNot == 2}'>
	          <td>의사회원</td> 
	          </c:if>
	          <c:if test='${member.doctorOrNot == 3}'>
	          <td>관리자</td> 
	          </c:if>
	          <td>${member.registeredDate}</td>
	           <c:if test='${member.active == 1}'>
	          <td>활동중</td> 
	          </c:if>
	            <c:if test='${member.active == 0}'>
	          <td>회원탈퇴</td> 
	          </c:if>
	      </tr>
      </c:forEach>
    </tbody>
</table>

<!-- 
<h1>회원가입</h1>
</div>

<div class="container2">
<a href='MemberForm.jsp' class="btn btn-outline-primary">일반회원가입</a>
<a href='DoctorForm.jsp' class="btn btn-outline-primary" style="margin-left: 30px;">의사</a>
</div> -->

  <div class="container-fluid">
    <div class="row main-content bg-success text-center">
      <div class="col-md-4 text-center company__info">
        <!-- <span class="company__logo"><h2><span class="fa fa-android"></span></h2></span> -->
        <h4 class="company_title">APUJIMA</h4>
      </div>
      <div class="col-md-8 col-xs-12 col-sm-12 login_form ">
        <div class="container-fluid">
          <div class="row">
            <h2>관리자 승인 요청건</h2>
          </div>
        <div class="row">
           <b><a href='approvalReport' class="btn">게시판 신고 승인</a></b>
        </div>
        <div class="row">
           <b><a href='approvalMedicine' class="btn">약품 승인</a></b>
        </div>
       </div>
    
      </div>
    </div>
  </div>
  
  
  <!-- 
    <div class="row">
      <div class="col-lg-4">
        <svg class="bd-placeholder-img rounded-circle" width="140" height="140" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 140x140" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#777"/><text x="50%" y="50%" fill="#777" dy=".3em">140x140</text></svg>

        <h2>Heading</h2>
        <p>Some representative placeholder content for the three columns of text below the carousel. This is the first column.</p>
        <p><a class="btn btn-secondary" href="#">View details &raquo;</a></p>
      </div>/.col-lg-4
      <div class="col-lg-4">
        <svg class="bd-placeholder-img rounded-circle" width="140" height="140" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 140x140" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#777"/><text x="50%" y="50%" fill="#777" dy=".3em">140x140</text></svg>

        <h2>Heading</h2>
        <p>Another exciting bit of representative placeholder content. This time, we've moved on to the second column.</p>
        <p><a class="btn btn-secondary" href="#">View details &raquo;</a></p>
      </div>/.col-lg-4
      <div class="col-lg-4">
        <svg class="bd-placeholder-img rounded-circle" width="140" height="140" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 140x140" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#777"/><text x="50%" y="50%" fill="#777" dy=".3em">140x140</text></svg>

        <h2>Heading</h2>
        <p>And lastly this, the third column of representative placeholder content.</p>
        <p><a class="btn btn-secondary" href="#">View details &raquo;</a></p>
      </div>/.col-lg-4
    </div>/.row
     -->
    
</div>
</body>
</html>









