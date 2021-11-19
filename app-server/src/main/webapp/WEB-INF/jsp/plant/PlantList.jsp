<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <title>내 화분 목록</title>
 <link rel="stylesheet" href="node_modules/bootstrap/dist/css/bootstrap.css">
  <link rel="stylesheet" href="apus/css/PlantList.css"> 
  
<!--      <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" ></script> -->
  
   <style>
 /*  .container {
    xborder: 1px solid red;
    width: 640px;
    text-align: left;
  } */
  
  .square {
  width : 50%;
  }
  .square:after{
  content:"";
  display: block;
  padding-bottom:100%;
  }
  .inner {
  position:absolute;
  width:100%;
  height:100%;
  }
 
</style>

<title>화분 목록</title>
 <div class="container"> 

  <h1>화분 목록</h1>
    <c:forEach items="${plantList}" var="plant">
     
      <div class='plant'>
          <img src=/apus/img/${plant.shape} width="150" height="200">
        
        <div class='plantinfo'>
          <p class='plantName'>  화분 이름 : <a href ='detail?no=${plant.no}'>${plant.plantName}</a></p>
          <p>화분 주인: ${loginUser.nickname}</p>
          <p>화분 레벨 : ${plant.level}</p>
        </div>
       <hr>
      </div>
    </c:forEach>
 <a href='form' class="btn btn-outline-primary btn-sm">화분 생성</a><br>   
 <a href= "/apus/app/home" class ="btn btn-outline-primary btn-sm">메인 페이지</a>
 </div> <!-- .container -->
 
 <!--  new List! -->
 <!-- 
 <div class = "square">
  <div class ="inner">
  <h1>화분 목록</h1>
   <a href='PlantForm.jsp' class="btn btn-outline-primary btn-sm">화분 생성1</a>
 <a href= "/apus/home" class ="btn btn-outline-primary btn-sm">메인 페이지</a> 
  <c:forEach items="${plantList}" var="plant">
     
      <div class='plant'>
          <img src=/apus/img/${plant.shape} width="150" height="200">
        
        <div class='plantinfo'>
          <p class='plantName'> 화분 이름2 : <a href ='detail?no=${plant.no}'>${plant.plantName}</a></p>
          <p>화분 주인: ${loginUser.nickname}</p>
          <p>화분 레벨 : ${plant.level}</p>
        </div>
       <hr>
      </div>
    </c:forEach>
  
  
  </div>
 
 
 </div>
 -->




