<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>내 화분 목록</title>
   <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core//dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  
   <style>
  .container {
    xborder: 1px solid red;
    width: 640px;
    text-align: left;
  }
  .doctor {
    width: 640px;
    height:500px; 
    margin-top: 100px;
    text-align: center;
  }
 .plantpic{
    border: 1px solid red;
    float:left;
    width:300px;
    height:400px; 
  }
 .plantinfo {
    display:inline-block; 
    width:200px; 
    height:100px; 
    margin-left:100px;
    margin-top: 100px;
  }  
  .plantName {
    font-size: 20px;
    font-weight: 200px;
    font-style: italic;
    font-family: 'Nanum Myeongjo', serif;
  }
</style>

</head>
<body>
<div class="container">
  <h1>화분 목록</h1>
    <c:forEach items="${plantList}" var="plant">
     
      <div class='plant'>
          <img src=../img/${plant.shape} width="150" height="200">
        
        <div class='plantinfo'>
          <p class='plantName'>  화분 이름 : <a href ='detail?no=${plant.no}' >${plant.plantName}</a></p>
          <p>화분 주인: ${loginUser.nickname}</p>
          <p>화분 레벨 : ${plant.level}</p>
        </div>
       
      </div>
    </c:forEach>
 <a href='PlantForm.jsp' class="btn btn-outline-primary btn-sm">화분 생성</a><br>   
 <a href= "../index.jsp" class ="btn btn-outline-primary btn-sm">메인 페이지</a>
</div><!-- .container -->
</body>
</html>








