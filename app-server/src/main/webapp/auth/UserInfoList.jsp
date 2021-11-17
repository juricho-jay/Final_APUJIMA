<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
  <title>내 정보</title>
  
  
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
 <link rel="stylesheet" href="/apus/css/UserInfo.css">
 <link rel="sytlesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.3.1/css/all.min.css">
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
 
 
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
  <style>
  
  
  </style>
</head>

<body>
   <div class="main-content">
    <!-- Top navbar -->
    <nav class="navbar navbar-top navbar-expand-md navbar-dark" id="navbar-main">
      <div class="container-fluid">
        <!-- Brand -->
        <a class="h2 mb-0 text-white text-uppercase d-none d-lg-inline-block" href="/apus/home">APUJIMA</a>
        <!-- Form -->
        <form class="navbar-search navbar-search-dark form-inline mr-3 d-none d-md-flex ml-lg-auto">
          <div class="form-group mb-0">
            <div class="input-group input-group-alternative">
              <div class="input-group-prepend">
                <span class="input-group-text"><i class="bi bi-search"></i></span>
              </div>
              <input class="form-control" placeholder="Search" type="text">
            </div>
          </div>
        </form>
        <!-- User -->
         <ul class="navbar-nav align-items-center d-none d-md-flex">
          <li class="nav-item dropdown">
            <a class="nav-link pr-0" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <!-- <div class="media align-items-center">
                <span class="avatar avatar-sm rounded-circle">
                  <img alt="Image placeholder" src="https://demos.creative-tim.com/argon-dashboard/assets/img/theme/team-4.jpg">
                </span>
                <div class="media-body ml-2 d-none d-lg-block">
                  <span class="mb-0 text-sm  font-weight-bold">Jessica Jones</span>
                </div>
              </div> -->
              <span>${loginUser.nickname}님</span>
            </a>
            <div class="dropdown-menu dropdown-menu-arrow dropdown-menu-right">
              <div class=" dropdown-header noti-title">
                <h6 class="text-overflow m-0">Welcome!</h6>
              </div>
              <a href="./examples/profile.html" class="dropdown-item">
                <i class="ni ni-single-02"></i>
                <span>My profile</span>
              </a>
              <a href="./examples/profile.html" class="dropdown-item">
                <i class="ni ni-settings-gear-65"></i>
                <span>Settings</span>
              </a>
              <a href="./examples/profile.html" class="dropdown-item">
                <i class="ni ni-calendar-grid-58"></i>
                <span>Activity</span>
              </a>
              <a href="./examples/profile.html" class="dropdown-item">
                <i class="ni ni-support-16"></i>
                <span>Support</span>
              </a>
              <div class="dropdown-divider"></div>
              <a href="#!" class="dropdown-item">
                <i class="ni ni-user-run"></i>
                <span>Logout</span>
              </a>
            </div>
          </li>
        </ul>
      </div>
    </nav>
    <!-- Form : member update -->
     <form id="member-detail" action="userUpdate">
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
                    <img src="../img/ruby.jpg" class="rounded-circle">
                  </a>
                </div>
              </div>
            </div>
            <div class="card-header text-center border-0">
              <div class="justify-content-between">
                <a href="../mailbox/list" class="btn btn-sm btn-info mr-4" style="float: left">쪽지함</a>
                <a href="#" class="btn btn-sm btn-default" style="float: middle; margin-top: 80px;">수정</a>
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
                      <span class="heading">10</span>
                      <span class="description">Posts</span>
                    </div>
                    <div>
                      <span class="heading">35</span>
                      <span class="description">Comments</span>
                    </div>
                  </div>
                </div>
              </div>
              <div class="text-center">
                <h3>
                  <span class="font-weight-light"><b>${loginUser.nickname}</b></span>
                </h3>
                <div class="h5 font-weight-300">
                  <i class="ni location_pin mr-2"></i>${loginUser.nickname}님의 화분은 총 -개입니다.
                </div>
                <!-- <div class="h5 mt-4">
                  <i class="ni business_briefcase-24 mr-2"></i>나의 정원
                </div> -->
                <div>
                  <a href="/apus/plant/list">
                  <i class="bi bi-flower1"></i><br>
                  <span>나의 정원</span>
                  </a>
                </div>
                <hr class="my-4">
                
                
                <button type="button" id="openModalBtn" class="btn btn-secondary" style="background-color: #5e72e4 !important;">출석 체크</button>
                <button type="button" id="openModalBtn2" class="btn btn-secondary" style="background-color: #5e72e4 !important;">버킷리스트</button>
                
                  <%-- <c:if test='${not empty dateCheck}'>
                  alert()
                  </c:if> --%>
                <p>여기에는 뭘 써야할까요. 알아맞춰 보세요. 뚱땅뚱땅</p>
                <a href="#">Show more</a>
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
                <!-- <hr class="my-4"> -->
                <!-- Address -->
                <!-- <h6 class="heading-small text-muted mb-4">Contact information</h6> -->
                <!-- <div class="pl-lg-4">
                  <div class="row">
                    <div class="col-md-12">
                      <div class="form-group focused">
                        <label class="form-control-label" for="input-address">Address</label>
                        <input id="input-address" class="form-control form-control-alternative" placeholder="Home Address" value="Bld Mihail Kogalniceanu, nr. 8 Bl 1, Sc 1, Ap 09" type="text">
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-lg-4">
                      <div class="form-group focused">
                        <label class="form-control-label" for="input-city">City</label>
                        <input type="text" id="input-city" class="form-control form-control-alternative" placeholder="City" value="New York">
                      </div>
                    </div>
                    <div class="col-lg-4">
                      <div class="form-group focused">
                        <label class="form-control-label" for="input-country">Country</label>
                        <input type="text" id="input-country" class="form-control form-control-alternative" placeholder="Country" value="United States">
                      </div>
                    </div>
                    <div class="col-lg-4">
                      <div class="form-group">
                        <label class="form-control-label" for="input-country">Postal code</label>
                        <input type="number" id="input-postal-code" class="form-control form-control-alternative" placeholder="Postal code">
                      </div>
                    </div>
                  </div>
                </div> -->
                <c:if test='${loginUser.doctorOrNot eq "2"}'>
                <hr class="my-4">
                <!-- Description -->
                <h6 class="heading-small text-muted mb-4">의사 정보</h6>
                <div class="pl-lg-4" style="padding-right: 24px">
                  <div class="form-group focused">
                    <label class="form-control-label" for="u-license">면허</label>
                    <input name="license" class="form-control form-control-alternative" value="${member.doctor.license}">
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
          <p>Made by <a href="/apus/home">APUJIMA </a>team</p>
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
          <iframe class="embed-responsive-item" src="/apus/bucket/list"></iframe>
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
  
  
  <script>

  // 내 정보의 모달은 iFrame
	
	// 출석체크 버튼에 이벤트를 건다. 
	$('#openModalBtn').on('click', function(){
	$('#checkModal').modal('show');

	});
	
	// 출석체크 안의 취소 버튼에 이벤트를 건다. 
	$('#closeCModal').on('click', function(){
	$('#checkModal').modal('hide');
	});

	// 버킷 리스트 버튼에 이벤트를 건다. 
	$('#openModalBtn2').on('click', function(){
		  $('#bucketModal').modal('show');
	});
	
	// 버킷 리스트 안의 취소 버튼에 이벤트를 건다. 
	$('#closeBModal').on('click', function(){
	$('#bucketModal').modal('hide');
	});

	
	
	// 드롭다운 메뉴
	

$(".nav li").hover(function() {

        $(this).children("ul").stop().slideToggle(500)

      })

  </script>
  
  
  
  
  
  
</body>
</html>