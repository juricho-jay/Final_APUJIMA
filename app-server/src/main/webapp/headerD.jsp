<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
<style>

header {
  position: relative;
}

	li {
		margin-left: 40px;
	}
	
	.btn4 {
  padding: 10px;
  color: #2c473e;
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
  background: #2c473e;
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
    color: #2c473e;
    border: 0;
    outline: 0;
    background: none;
    width: 0;
    caret-color: white;
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
    border-radius: 50%;
    color:white;
    text-decoration:none;
    }
    
    input::placeholder {color:#2c473e;}
		input::-webkit-input-placeholder {color:#2c473e;}
		input:-ms-input-placeholder {color:#c2c473ecc;}
		 
		textarea::placeholder {color:#2c473e;}
		textarea::-webkit-input-placeholder {color:#2c473e;}
		textarea:-ms-input-placeholder {color:#2c473e;}

    
</style>
</head>
<header>
<nav class="navbar navbar-expand-lg navbar-light bg-transparent" style="height: 120px; border-bottom: solid 1px #25362977; margin-bottom: 30px;">
  <div class="container-fluid">
    <a class="navbar-brand" href="/apus/home">
    <input type="button" class="img-button"></a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div id="navbarNavDropdown">
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
        <li>
				    <!-- <form class="d-flex">
				      <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
				      <button type="button" class="btn" style="color: white">search</button>
				    </form>
				     -->
				     
				     <div class="container h-100">
					      <div class="d-flex justify-content-center h-100">
					        <div class="searchbar">
					          <input class="search_input" type="text" placeholder="Search...">
					          <a href="#" class="s_icon"><i class="bi bi-search" style="margin-bottom: 10px;"></i></a>
					        </div>
					      </div>
					    </div>
				</li>
				<li>
				<a href="/apus/auth/LogIn.jsp" class="btn4" style="color: 25362977;">
			   로그인
			  </a>
			  <a href="/apus/member/MemberForm.jsp" class="btn4" style="color: 25362977;">
         회원가입
        </a>
			  </li>
				<!-- <li>
				<a href = "/apus/member/MemberSignUp.jsp">
        <input class="btn btn-primary" type="button" value="회원가입" onclick="MemberForm.jsp" style="background-color: transparent; color: grey; border-color: white;">
        </a>
        </li> -->
      </ul>
    </div>
  </div>
</nav>
</header>