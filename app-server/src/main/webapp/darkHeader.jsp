<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<link rel="stylesheet" href="/apus/css/darkHeader.css">

<header>
<div class="wrapperH">
<nav class="navbar navbar-expand-lg navbar-light bg-transparent" style="height: 120px; border-bottom: solid 1px #25362977; margin-bottom: 30px;">
  <div class="container-fluid">
    <a class="navbar-brand" href="/apus/home"><input type="button" class="img-button"></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
      <ul class="navbar-nav">
        <li class="nav-item">
          <a href="#" class="btn4" style="color: #2c473e;">소개</a>
        </li>
        <li class="nav-item">
          <a class="btn4" href="/apus/medicine/list" style="color: #2c473e">약국</a>
        </li>
        <li class="nav-item">
          <a class="btn4" href="/apus/doctorinfo/list" style="color: #2c473e">HEALER</a>
        </li>
        <li class="nav-item">
          <a class="btn4" href="/apus/board/list" style="color: #2c473e">커뮤니티</a>
        </li>
        <li class="nav-item">
				     <div class="container h-100">
					      <div class="d-flex justify-content-center h-100">
					        <div class="searchbar">
					          <input class="search_input" type="text" placeholder="Search...">
					          <a href="#" class="s_icon"><i class="bi bi-search" style="margin-bottom: 10px;"></i></a>
					        </div>
					      </div>
					    </div>
				</li>
				  <!-- 로그인 x -->
        <c:if test="${empty sessionScope.loginUser}">
        <li>
        <a href="/apus/auth/LogIn.jsp" class="btn4" style="color: #2c473e;">
         로그인
        </a>
        <a href="/apus/member/MemberForm.jsp" class="btn4" style="color: #2c473e;">
         회원가입
        </a>
        </li>
        </c:if>
        <!-- 로그인 o -->
        <c:if test="${not empty sessionScope.loginUser}">
        <li>
        <a href="/apus/auth/userInfoList" class="button" style="color:#2c473e"><b>${loginUser.nickname}</b></a>
        <i class="no-italics" style="color: #2c473e">&nbsp;님 <br>환영합니다!</i>
        </li>
        <li>
        <form name="logout" action='/apus/auth/logout' method = "get">
        <a class="btn4 "href="javascript:logout.submit();" style="color: #2c473e">로그아웃</a>
        </form>
        </li>
        </c:if>
      </ul>
  </div>
</nav>
</div>
</header>