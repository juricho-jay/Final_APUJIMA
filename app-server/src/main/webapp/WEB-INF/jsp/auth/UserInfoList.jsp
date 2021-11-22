<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
  <title>내 정보</title>
 <!--  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script> -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
  <link rel="stylesheet" href="/apus/css/UserInfo.css">
  
  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
  <script src="https://unpkg.com/@popperjs/core@2/dist/umd/popper.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  
  <style>
  
.btn4 {
  padding: 10px;
  color: white;
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
    background-color: white;
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
    border-radius: none;
    border-color: transparent;
    color: #525F7F;
    text-decoration:none;
    background-color: transparent;
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
  color: #525F7F;
  padding: 5px;
  text-decoration: none;
}
.dropdown-menu a:hover {
  color: #2C473E;
  background-color: #dcdcdc;
}

.dropdown-menu-center {
right: auto;
left: 50%;
-webkit-transform: translate(-50%, 0);
-o-transform: translate(-50%, 0);
transform: translate(-50%, 0);}

  </style>
</head>

<body>
  <div class="main-content">
    <!-- Top navbar -->
    <nav class="navbar navbar-expand-lg navbar-light" style="padding-top:20px; padding-right: 32px; background-color:#343f5a">
  <div class="container-fluid">
    <a class="navbar-brand" href="${contextPath}/app/home">
    <input type="button" class="img-button"></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
        <li class="nav-item" style="margin-left: 20px;">
          <a class="btn4" aria-current="page" href="${contextPath}/app/introduce" style="color: white;">소개</a>
        </li>
        <li class="nav-item" style="margin-left: 20px;">
          <a class="btn4" aria-current="page" href="${contextPath}/app/medicine/list" style="color: white;">약국</a>
        </li>
        <li class="nav-item" style="margin-left: 20px;">
          <a class="btn4" aria-current="page" href="${contextPath}/app/doctorinfo/list" style="color: white;">HEALER</a>
        </li>
        <li class="nav-item dropdown" style="margin-left: 20px;">
          <a class="btn4 text-white" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
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
        <a href="${contextPath}/app/auth/loginForm" class="btn4" style="color: #2C473E;">
         로그인
        </a>
        <a href="${contextPath}/app/member/form" class="btn4" style="color: #2C473E;">
         회원가입
        </a>
        </div>
        </c:if>
        <!-- 로그인 o -->
        <c:if test="${not empty sessionScope.loginUser}">
        <div>
        <c:if test="${uncheckedMail != 0}">
        <a type="button" class="position-relative" 
        style="outline: 0; background-color: transparent; border:0; color: #2C473E; padding:0; margin-left: 5px"
        href="${contextPath}/app/mailbox/list">
          <i class="bi bi-envelope" style="zoom: 1.8; color: white;"></i>
          <span class="position-absolute top-0 start-100 translate-middle-x badge rounded-pill bg-danger"
          style="">
            ${uncheckedMail}
          </span>
        </a>
        </c:if>
        <c:if test="${uncheckedMail eq 0}">
        <a type="button" class="position-relative" style="outline: 0; background-color: transparent; border:0; color: white;"
        href="${contextPath}/app/mailbox/list">
          <i class="bi bi-envelope" style="zoom: 1.8; color: white;"></i>
        </a>
        </c:if>
        </div> 
        <!-- 내 정보 아이콘 -->
        <li class="nav-item dropdown" style="margin-left: 30px">
          <a class="nav-item d-sm-flex align-items-sm-center" href="${contextPath}/app/auth/userInfoList"
        style="text-decoration-line: none; color: #2c473e;">
        <img src="${contextPath}/upload/member/${member.photo}_20x20.jpg" class="rounded-circle" width="30" height="30">
          <b style="color: white; text-size: 1.4em">&nbsp;${loginUser.nickname}</b>
        </a>
          <ul class="dropdown-menu dropdown-menu-center" style="" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="${contextPath}/app/auth/userInfoList">내 정보</a></li>
            <li><a class="dropdown-item" href="${contextPath}/app/bucket/list">버킷 리스트</a></li>
            <li><a class="dropdown-item" href="${contextPath}/app/plant/list">나의 정원</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><form name="logout" action= '${contextPath}/app/auth/logout' method = "get">
            <a class="dropdown-item" href="javascript:logout.submit();">로그아웃</a>
              </form></li>
          </ul>
        </li>
        <div>
        </div>
        </c:if>
    </div>
  </div>
</nav>
    <!-- Form : member update -->
    <form id="member-detail" action="updateUserInfo">
    <input type="hidden" name="no" value="${loginUser.no}">
    <!-- Header -->
    <div class="header pb-8 pt-5 pt-lg-8 d-flex align-items-center" style="min-height: 600px; background-image:); background-size: cover; background-position: center top;">
      <!-- Mask -->
      <span class="mask bg-gradient-default opacity-8"></span>
      <!-- Header container -->
      <div class="container-fluid d-flex align-items-center">
        <div class="row">
          <div class="col-lg-7 col-md-10">
            <h1 class="display-2 text-white">Hello <b>${loginUser.nickname}</b></h1>
            <p class="text-white mt-0 mb-5">개인 정보 페이지입니다. 개인 정보를 수정하거나 쪽지, 게시글, 화분을 확인할 수 있습니다.</p>
            <button type="submit" class="btn btn-info">정보 수정</button>
          </div>
        </div>
      </div>
    </div>
    <!-- Page content -->
    <div class="container-fluid mt--7">
      <div class="row">
        <div class="col-xl-4 order-xl-2 mb-5 mb-xl-0">
          <div class="card card-profile shadow">
            <div class="row justify-content-center">
              <div class="col-lg-3 order-lg-2">
                <div class="card-profile-image">
                  <a href="#">
                    <img src="${contextPath}/upload/member/${member.photo}" class="rounded-circle">
                  </a>
                </div>
              </div>
            </div>
            <div class="card-header text-center border-0">
              <div class="justify-content-between">
                <a href="../mailbox/list" class="btn btn-sm btn-info mr-4" style="float: left">쪽지함</a>
                <button type="button" class="btn btn-sm btn-default" name="updatePhotoBtn" style="float: middle; margin-top: 80px;">수정</button>
              </div>
            </div>
            <div class="card-body pt-0 pt-md-4">
              <div style="float: middle !important">
                <div>
                  <div class="card-profile-stats d-flex justify-content-center mt-md-5" style="float: top !important">
                    <div>
                      <span class="heading">${loginUser.point}</span>
                      <span class="description">Points</span>
                    </div>
                    <div>
                      <span class="heading">${myPosts}</span>
                      <a href="${contextPath}/app/board/searchMyPosts"><span class="description">Posts</span></a>
                    </div>
                    <div>
                      <span class="heading">${myComments}</span>
                      <a href="${contextPath}/app/comment/searchMyComments"><span class="description">Comments</span></a>
                    </div>
                  </div>
                </div>
              </div>
              <div class="text-center">
                <h3>
                  <span class="font-weight-light"><b>${loginUser.nickname}</b></span>
                </h3>
                <div class="h5 font-weight-300">
                  <i class="ni location_pin mr-2"></i>반갑습니다.
                </div>
                <div>
                  <a href="../plant/list">
                  <i class="bi bi-flower1"></i><br>
                  <span>나의 정원</span>
                  </a>
                </div>
                <hr class="my-4">
                
                
                <button type="button" id="openModalBtn" class="btn btn-secondary" style="background-color: #5e72e4 !important;">출석 체크</button>
                <button type="button" id="openModalBtn2" class="btn btn-secondary" style="background-color: #5e72e4 !important;">버킷리스트</button>
                <c:if test = "${loginUser.doctorOrNot eq 1}">
                <a href='${contextPath}/app/counseling/list' class="btn btn-secondary" style="background-color: #5e72e4 !important;">상담신청리스트</a><br>
                </c:if>
                <c:if test = "${loginUser.doctorOrNot eq 2}">
                <a href='${contextPath}/app/counseling/doctorlist' class="btn btn-secondary" style="background-color: #5e72e4 !important;">상담요청리스트</a><br>
                </c:if>
                  <%-- <c:if test='${not empty dateCheck}'>
                  alert()
                  </c:if> --%>
              </div>
            </div>
          </div>
        </div>
        <div class="col-xl-8 order-xl-1">
          <div class="card bg-secondary shadow">
            <div class="card-header bg-white border-0">
              <div class="row align-items-center">
                <div class="col-8">
                  <h3 class="mb-0">${loginUser.nickname} 계정</h3>
                </div>
                <div class="col-4 text-right">
                  <!-- <a href="#!" class="btn btn-sm btn-primary">Settings</a> -->
                </div>
              </div>
            </div>
            <div class="card-body">
                <h6 class="heading-small text-muted mb-4">내 정보</h6>
                <div class="pl-lg-4">
                  <div class="row">
                    <div class="col-lg-4">
                      <div class="form-group focused">
                        <label class="form-control-label" for="u-username">이름</label>
                        <input type="text" id="u-username" name="name" class="form-control form-control-alternative" value="${loginUser.name}" style="background-color: #f7f7f7;" readonly >
                      </div>
                      <div class="form-group focused">
                        <label class="form-control-label" for="u-username">아이디</label>
                        <input type="text" id="u-id" name="id" class="form-control form-control-alternative" value="${loginUser.id}" style="background-color: #f7f7f7;" readonly >
                      </div>
                    </div>
                      <div class="col-lg-4">
                      <div class="form-group">
                      <label class="form-control-label"></label>
                      <div class="gender-middle">
                      <c:choose>
                      <c:when test='${loginUser.sex eq "남"}'>
                      <i class="bi bi-gender-male" style="color: purple; zoom: 1.5"></i>
                      </c:when>
                      <c:when test='${loginUser.sex eq "여"}'>
                      <i class="bi bi-gender-female" style="color: purple; zoom: 1.5"></i>
                      </c:when>
                      <c:otherwise><i class="bi bi-gender-ambiguous" style="color: purple; zoom: 1.5"></i></c:otherwise>
                      </c:choose>
                      </div>
                     </div>
                     </div>
                  </div>
                  <div class="row">
                    <div class="col-lg-4">
                      <div class="form-group">
                        <label class="form-control-label" for="u-nickname">별명</label>
                        <input type="text" id="u-nickname" name="nickname" class="form-control form-control-alternative" value="${loginUser.nickname}">
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-lg-5">
                      <div class="form-group focused">
                        <label class="form-control-label" for="u-birthday">생일</label>
                        <input type="text" id="u-birthday" name="birthday" class="form-control form-control-alternative" value="${loginUser.birthday}" style="background-color: #f7f7f7;" readonly>
                      </div>
                    </div>
                    <div class="col-lg-6">
                      <div class="form-group focused">
                        <label class="form-control-label" for="u-email">이메일</label>
                        <input type="text" id="u-email" name="email" class="form-control form-control-alternative" value="${loginUser.email}">
                      </div>
                    </div>
                    <div class="col-lg-5">
                      <div class="form-group focused">
                        <label class="form-control-label" for="u-tel">번호</label>
                        <input type="text" id="u-tel" name="tel" class="form-control form-control-alternative" value="${loginUser.tel}">
                      </div>
                    </div>
                  </div>
                </div>
                <c:if test='${loginUser.doctorOrNot eq "2"}'>
                <hr class="my-4">
                <!-- Description -->
                <h6 class="heading-small text-muted mb-4">의사 정보</h6>
                <div class="pl-lg-4" style="padding-right: 24px">
                  <div class="form-group focused">
                    <label class="form-control-label" for="u-license">면허</label>
                    <input name="license" class="form-control form-control-alternative" value="${member.doctor.license}" readonly>
                  </div>
                  <div class="form-group focused">
                    <label class="form-control-label" for="u-major">전공</label>
                    <input name="major" class="form-control form-control-alternative" value="${member.doctor.major}">
                  </div>
                  <div class="form-group focused">
                    <label class="form-control-label" for="u-homepage">홈페이지</label>
                    <input name="homepage" class="form-control form-control-alternative" value="${member.doctor.homepage}">
                  </div>
                  <div class="form-group focused">
                    <label class="form-control-label" for="u-introduction">소개</label>
                    <input name="introduction" class="form-control form-control-alternative" value="${member.doctor.introduction}">
                  </div>
                </div>
                </c:if>
            </div>
          </div>
        </div>
      </div>
    </div>
          </form> <!-- form id:member detail -->
  </div> <!--  main content  -->
  <footer class="footer">
    <div class="row align-items-center justify-content-xl-between">
      <div class="col-xl-6 m-auto text-center">
        <div class="copyright">
          <p>Made by <a href="../loginindex.jsp" target="_blank">APUJIMA </a>team</p>
        </div>
      </div>
    </div>
  </footer>
  
  
  
<!--Modal: 출첵 모달-->
<div class="modal fade" id="checkModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-md" role="document">
    <!--Content-->
    <div class="modal-content">
      <!--Body-->
      <div class="modal-body mb-0 p-0">
        <div class="embed-responsive embed-responsive-16by9 z-depth-1-half">
          <iframe class="embed-responsive-item" src="dateCheckList"></iframe>
        </div>
      </div>
      <!--Footer-->
      <div class="modal-footer justify-content-center">
        <!-- <span class="mr-4">Spread the word!</span> -->
        <button type="button" id="closeCModal" class="btn btn-outline-primary btn-rounded btn-md ml-4">닫기</button>
      </div>
    </div>
    <!--/.Content-->
  </div>
</div>
<!--Modal: Name-->

<!--Modal: 버킷 리스트 모달-->
<div class="modal fade" id="bucketModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <!--Content-->
    <div class="modal-content">
      <!--Body-->
      <div class="modal-body mb-0 p-0">
        <div class="embed-responsive embed-responsive-16by9 z-depth-1-half">
          <iframe class="embed-responsive-item" src="../bucket/list"></iframe>
        </div>
      </div>
      <!--Footer-->
      <div class="modal-footer justify-content-center">
        <!-- <span class="mr-4">Spread the word!</span> -->
        <button type="button" id="closeBModal" class="btn btn-outline-primary btn-rounded btn-md ml-4">닫기</button>
      </div>
    </div>
    <!--/.Content-->
  </div>
</div>
<!--Modal: Name-->
  
  <!--Modal: 사진 수정 모달-->
<div class="modal fade" id="updatePhotoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-md" role="document">
    <!--Content-->
    <div class="modal-content">
      <!--Body-->
      <div class="modal-body mb-0 p-0">
        <div class="embed-responsive embed-responsive-16by9 z-depth-1-half">
          <iframe name="photoChange" class="embed-responsive-item" src="updatePhotoForm"></iframe>
        </div>
      </div>
      <!--Footer-->
      <div class="modal-footer justify-content-center">
        <!-- <span class="mr-4">Spread the word!</span> -->
        <button type="button" id="closePModal" class="btn btn-outline-primary btn-rounded btn-md ml-4">닫기</button>
      </div>
    </div>
    <!--/.Content-->
  </div>
</div>
<!--Modal: Name-->
  
  <script>

  // 내 정보의 모달은 iFrame
   
   // 출석체크 버튼에 이벤트를 건다. 
   $('#openModalBtn').on('click', function(){
   $('#checkModal').modal('show');

   });
   
   // 출석체크 안의 취소 버튼에 이벤트를 건다. 
   $('#closeCModal').on('click', function(){
   $('#checkModal').modal('hide');
   window.location.reload();
   });


	// 버킷 리스트 버튼에 이벤트를 건다. 
	$('#openModalBtn2').on('click', function(){
	$('#bucketModal').modal('show');
	});
	
	// 버킷 리스트 안의 취소 버튼에 이벤트를 건다. 
	$('#closeBModal').on('click', function(){
	$('#bucketModal').modal('hide');
	});

	
	// 사진 업데이트 모달
  $('button[name=updatePhotoBtn]').on('click', function(){
  $('#updatePhotoModal').modal('show');
  });
   
  // 사진 업데이트 모달 안 취소 버튼에 이벤트를 건다.
  $('#closePModal').on('click', function(){
  $('#updatePhotoModal').modal('hide');
  window.location.reload();
  });
   

$(".nav li").hover(function() {

        $(this).children("ul").stop().slideToggle(500)

      })

  </script>
  
  
  
  
  
  
</body>
</html>