<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인회원 회원가입</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<style>
  label {
    margin-right: 5px;
    text-align: center;
    display: inline;
    width: 60px;
  }
  legend {
  text-align: center;
  }
</style>
</head>

<body>
  <h5>개인 회원 회원가입</h5>
  <form action='add'>
    <label for='f-name' class='form-label' size='100px'>이름</label>
    <input id='f-name' type='text' name='name' placeholder="이름"><br>
    
    <label for='f-email' class='form-label' size='100px'>이메일</label>
    <input id='f-email' type='email' name='email' placeholder="이메일"><br>
    
    <label for='f-password' class='form-label' size='100px'>암호</label>
    <input id='f-password' type='password' name='password' placeholder="암호"><br>

    <label for='f-nickname' class='form-label' size='100px'>닉네임</label>
    <input id='f-nickname' type='name' name='nickname' placeholder='닉네임' size='20'><br>
         
    <label for='f-photo' class='form-label' size='100px'>사진</label>
    <input id='f-photo' type='text' name='photo' placeholder="사진"><br>

    <label for='f-tel' class='form-label' size='100px'>전화번호</label>
    <input id='f-tel' type='tel' name='tel' placeholder="전화번호"><br>

    <button type="submit" class="btn btn-primary">
      회원가입
    </button>
   </form>
 </body>
</html>
