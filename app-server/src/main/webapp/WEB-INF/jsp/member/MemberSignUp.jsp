<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>회원가입 유형</title>
  <link rel="stylesheet" href="${contextPath}/node_modules/bootstrap/dist/css/bootstrap.css">
  
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

  
  </style>
</head>
<body>
<!-- <div class="container">
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
            <h2>회원가입</h2>
          </div>
        <div class="row">
           <b><a href='/apus/auth/LogIn.jsp' class="btn">로그인</a></b>
        </div>
        <div class="row">
           <b><a href='MemberForm.jsp' class="btn">회원가입</a></b>
        </div>
        </div>
    
        </div>
      </div>
    </div>

</body>
</html>









