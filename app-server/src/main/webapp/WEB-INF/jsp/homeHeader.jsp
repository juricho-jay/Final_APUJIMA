<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!-- <link rel="stylesheet" href="/apus/css/darkHeader.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script> -->
<link rel="stylesheet" href="/apus/node_modules/bootstrap/dist/css/bootstrap.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
<!-- <link rel="stylesheet" href="/apus/css/darkHeader.css"> -->
  
  <script src="/apus/node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="/apus/node_modules/bootstrap/dist/js/bootstrap.js"></script>


<style>


.btn4 {
  padding: 10px;
  color: #fff;
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
  background: white;
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
        background: url("/apus/img/header/white_logo.svg") no-repeat;
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
    color: white;
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
    caret-color:#253629;
    transition: width 0.4s linear;
    }

    .searchbar:hover > .search_icon{
    background: white;
    color: #253629;
    }

    .searchbar> .s_icon {
    height: 28px;
    width: 28px;
    float: right;
    display: flex;
    justify-content: center;
    align-items: center;
    border-radius: none;
    border-color: transparent;
    color:white;
    text-decoration:none;
    background-color: transparent;
    }
    
    input::placeholder {color:#ccc;}
    input::-webkit-input-placeholder {color:#ccc;}
    input:-ms-input-placeholder {color:#ccc;}
     
    textarea::placeholder {color:#ccc;}
    textarea::-webkit-input-placeholder {color:#ccc;}
    textarea:-ms-input-placeholder {color:#ccc;}

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
  color: #000000;
  padding: 5px;
  text-decoration: none;
}
.dropdown-menu a:hover {
  color: #FFFFFF;
  background-color: #dcdcdc;
}

.dropdown-menu-center {
right: auto;
left: 50%;
-webkit-transform: translate(-50%, 0);
-o-transform: translate(-50%, 0);
transform: translate(-50%, 0);}

</style>

<header>
<nav class="navbar navbar-expand-lg navbar-light bg-transparent" style="border-bottom: solid 1px #ffffff61; margin-bottom: 30px; padding: 33px 16px;">
  <div class="container-fluid">
    <a class="navbar-brand" href="${contextPath}/app/home">
    <input type="button" class="img-button"></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
        <li class="nav-item" style="margin-left: 20px;">
          <a class="btn4 text-white" aria-current="page" href="${contextPath}/app/introduce">소개</a>
        </li>
        <li class="nav-item" style="margin-left: 20px;">
          <a class="btn4 text-white" aria-current="page" href="${contextPath}/app/medicine/list">약국</a>
        </li>
        <li class="nav-item" style="margin-left: 20px;">
          <a class="btn4 text-white" aria-current="page" href="${contextPath}/app/doctorinfo/list">HEALER</a>
        </li>
        <li class="nav-item dropdown" style="margin-left: 20px;">
          <a class="btn4 text-white" href="${contextPath}/app/board/list" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            커뮤니티
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="${contextPath}/app/board/freeBoardList">자유게시판</a></li>
            <li><a class="dropdown-item" href="${contextPath}/app/board/doctorBoardList">HEALER 지식in</a></li>
            <!-- <li><hr class="dropdown-divider"></li> -->
            <li><a class="dropdown-item" href="${contextPath}/app/board/noticeBoardList">공지사항</a></li>
          </ul>
        </li>
      </ul>
          <form action="${contextPath}/app/board/search">
            <div style="margin-left: 20px; margin-right: 20px;">
               <div class="d-flex justify-content-center h-50">
                 <div class="searchbar">
                   <input class="search_input" name="keyword" type="text" placeholder="Search...">
                   <button type="submit"class="s_icon"><i class="bi bi-search" style="margin-bottom: 10px;"></i></button>
                 </div>
               </div>
            </div>
            </form>
      <!-- 로그인 x -->
        <c:if test="${empty sessionScope.loginUser}">
        <div>
        <a href="${contextPath}/app/auth/loginForm" class="btn4" style="color: white;">
         로그인
        </a>
        <a href="${contextPath}/app/member/form" class="btn4" style="color: white;">
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
        style="outline: 0; background-color: transparent; border:0; color: white; padding:0; margin-left: 5px"
        href="${contextPath}/app/mailbox/list">
              <i class="bi bi-envelope" style="zoom: 1.8"></i>
              <span class="position-absolute top-0 start-100 translate-middle-x badge rounded-pill bg-danger"
              style="">
                ${uncheckedMail}
              </span>
            </a>
        </c:if>
        <c:if test="${uncheckedMail eq 0}">
        <a type="button" class="position-relative" style="outline: 0; background-color: transparent; border:0; color: white;"
        href="${contextPath}/app/mailbox/list">
          <i class="bi bi-envelope" style="zoom: 1.8"></i>
        </a>
        </c:if>
        </div> 
        <!-- 내 정보 아이콘 -->
        <li class="nav-item dropdown" style="margin-left: 30px">
          <a class="nav-item d-sm-flex align-items-sm-center text-white" href="${contextPath}/app/auth/userInfoList"
        style="text-decoration-line: none;">
        <img
            src="${contextPath}/upload/member/${member.photo}_20x20.jpg"
            class="rounded-circle"
            height="30"
            alt=""
          />
          <b style="color: white; text-size: 1.4em">&nbsp;${loginUser.nickname}</b>
        </a>
          <ul class="dropdown-menu dropdown-menu-center" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="${contextPath}/app/auth/userInfoList">내 정보</a></li>
            <li><a class="dropdown-item" href="${contextPath}/app/bucket/list">버킷 리스트</a></li>
            <li><a class="dropdown-item" href="${contextPath}/app/plant/list">나의 정원</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><form name="logout2" action= '${contextPath}/app/auth/logout' method = "get">
            <a class="dropdown-item" href="javascript:logout.submit();">로그아웃</a>
              </form></li>
          </ul>
        </li>
        <div>
        <form name="logout" action= '${contextPath}/app/auth/logout' method = "get">
        <a class="btn4" href="javascript:logout.submit();" style="color: white; margin-left:10px;">로그아웃</a>
        </form>
        </div>
        </c:if>
    </div>
  </div>
</nav>
</header>