<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<link rel="stylesheet" href="/apus/css/darkHeader.css">
<style>


.btn4 {
  padding: 10px;
  color: #2C473E;
  font-family: sans-serif;
  text-transform: uppercase;
  text-align: center;
  position: relative;
  text-decoration: none !important;
  display:inline-block;
}

  .btn4::before {
  content: '';
  position: absolute;
  bottom: 0%;
  left: 0px;
  width: 100%;
  height: 1px;
  background: #2C473E;
  display: block;
  -webkit-transform-origin: right top;
  -ms-transform-origin: right top;
  transform-origin: right top;
  -webkit-transform: scale(0, 1);
  -ms-transform: scale(0, 1);
  transform: scale(0, 1);
  -webkit-transition: transform 0.4s cubic-bezier(1, 0, 0, 1);
  transition: transform 0.4s cubic-bezier(1, 0, 0, 1);
}

.btn4:hover::before {
  -webkit-transform-origin: left top;
  -ms-transform-origin: left top;
  transform-origin: left top;
  -webkit-transform: scale(1, 1);
  -ms-transform: scale(1, 1);
  transform: scale(1, 1)
}

input.img-button {
        background: url("/apus/img/header/green_logo.svg") no-repeat;
        border: none;
        width: 185px;
        height: 31px;
        cursor: pointer;
      }
      
.searchbar {
    margin-bottom: auto;
    margin-top: auto;
    height: 40px;
    background-color: #91929281;
    border-radius: 30px;
    padding: 10px;
    }

    .search_input{
    color: #2C473E;
    border: 0;
    outline: 0;
    background: none;
    width: 0;
    caret-color:transparent;
    line-height: 8px;
    transition: width 0.4s linear;
    }

    .searchbar:hover > .search_input{
    padding: 0 10px;
    width: 200px;
    caret-color:#2C473E;
    transition: width 0.4s linear;
    }

    .searchbar:hover > .search_icon{
    background: white;
    /* color: #253629; */
    color: #2C473E;
    }

    .searchbar> .s_icon {
    height: 28px;
    width: 28px;
    float: right;
    display: flex;
    justify-content: center;
    align-items: center;
    border-radius: 50%;
    color:white;
    text-decoration:none;
    }
    
    input::placeholder {color:#2C473E;}
    input::-webkit-input-placeholder {color:#2C473E;}
    input:-ms-input-placeholder {color:#2C473E;}
     
    textarea::placeholder {color:#2C473E;}
    textarea::-webkit-input-placeholder {color:#2C473E;}
    textarea:-ms-input-placeholder {color:#2C473E;}

    .no-italics {
    font-style: normal;   
}

.dropdown {
  display: inline-block;
  position: relative;
}

.dropdown-menu {
display: none;
position: absolute;
width: 100%;
overflow: auto;
box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
}

.dropdown:hover .dropdown-menu {
display: block;
}

.dropdown-menu a {
  display: block;
  color: #2C473E;
  padding: 5px;
  text-decoration: none;
}
.dropdown-menu a:hover {
  color: #2C473E;
  background-color: #dcdcdc;
}

</style>

<header>
<nav class="navbar navbar-expand-lg navbar-light bg-transparent" style="border-bottom: solid 1px #25362977; margin-bottom: 30px; padding: 33px 16px;">
  <div class="container-fluid">
    <a class="navbar-brand" href="${contextPath}/home">
    <input type="button" class="img-button"></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
        <li class="nav-item" style="margin-left: 20px;">
          <a class="btn4" aria-current="page" href="#" style="color: #2c473e;">소개</a>
        </li>
        <li class="nav-item" style="margin-left: 20px;">
          <a class="btn4" aria-current="page" href="${contextPath}/medicine/list" style="color: #2c473e;">약국</a>
        </li>
        <li class="nav-item" style="margin-left: 20px;">
          <a class="btn4" aria-current="page" href="${contextPath}/app/doctorinfo/list" style="color: #2c473e;">HEALER</a>
        </li>
        <li class="nav-item dropdown" style="margin-left: 20px;">
          <a class="btn4" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            커뮤니티
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="${contextPath}/board/freeBoardList">자유게시판</a></li>
            <li><a class="dropdown-item" href="${contextPath}/board/doctorBoardList">HEALER 지식in</a></li>
            <!-- <li><hr class="dropdown-divider"></li> -->
            <li><a class="dropdown-item" href="${contextPath}/board/noticeBoardList">공지사항</a></li>
          </ul>
        </li>
      </ul>
          <div style="margin-left: 20px; margin-right: 20px;">
             <div class="d-flex justify-content-center h-50">
               <div class="searchbar">
                 <input class="search_input" type="text" placeholder="Search...">
                 <a href="#" class="s_icon"><i class="bi bi-search" style="margin-bottom: 10px;"></i></a>
               </div>
             </div>
          </div>
      <!-- 로그인 x -->
        <c:if test="${empty sessionScope.loginUser}">
        <div>
        <a href="/${contextPath}/auth/LogIn.jsp" class="btn4" style="color: #2C473E;">
         로그인
        </a>
        <a href="/${contextPath}/member/MemberForm.jsp" class="btn4" style="color: #2C473E;">
         회원가입
        </a>
        </div>
        </c:if>
        <!-- 로그인 o -->
        <c:if test="${not empty sessionScope.loginUser}">
        <div>
        <c:if test="${uncheckedMail != 0}">
        <%-- <i class="bi bi-envelope" style="color: white; zoom: 1.5">
        <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
           ${uncheckedMail}
        </span>
        </i> --%>
        <a type="button" class="position-relative" 
        style="outline: 0; background-color: transparent; border:0; color: #2C473E; padding:0; margin-left: 5px"
        href="${contextPath}/mailbox/list">
          <i class="bi bi-envelope" style="zoom: 1.8; color: #2c473e;"></i>
          <span class="position-absolute top-0 start-100 translate-middle-x badge rounded-pill bg-danger"
          style="">
            ${uncheckedMail}
          </span>
        </a>
        </c:if>
        <c:if test="${uncheckedMail eq 0}">
        <a type="button" class="position-relative" style="outline: 0; background-color: transparent; border:0; color: white;"
        href="${contextPath}/mailbox/list">
          <i class="bi bi-envelope" style="zoom: 1.8; color: #2c473e;"></i>
        </a>
        </c:if>
        </div> 
        <!-- 내 정보 아이콘 -->
        <li class="nav-item dropdown" style="margin-left: 30px">
          <a class="nav-item d-sm-flex align-items-sm-center" href="${contextPath}/auth/userInfoList"
        style="text-decoration-line: none; color: #2c473e;">
        <img
            src="${contextPath}/upload/member/${member.photo}_20x20.jpg"
            class="rounded-circle"
            height="30"
            alt=""
          />
          <b style="color: #2c473e;; text-size: 1.4em">&nbsp;${loginUser.nickname}</b>
        </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="${contextPath}/auth/userInfoList">내 정보</a></li>
            <li><a class="dropdown-item" href="${contextPath}/bucket/list">버킷 리스트</a></li>
            <li><a class="dropdown-item" href="${contextPath}/plant/list">나의 정원</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><form name="logout2" action= '${contextPath}/auth/logout' method = "get">
            <a class="dropdown-item" href="javascript:logout.submit();">로그아웃</a>
              </form></li>
          </ul>
        </li>
        <div>
        <form name="logout" action= '${contextPath}/auth/logout' method = "get">
        <a class="btn4" href="javascript:logout.submit();" style="color: #2c473e;; margin-left:10px;">로그아웃</a>
        </form>
        </div>
        </c:if>
    </div>
  </div>
</nav>
</header>